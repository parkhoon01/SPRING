package com.pcwk.ehr.user.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.pcwk.ehr.TestUserServiceException;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

import org.springframework.transaction.support.TransactionSynchronization;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceImpl implements UserService {

	final Logger LOG = LogManager.getLogger(getClass());
	
	// 상수 도입: 30, 50
	// BASIC  -> SILVER로 가는 최소 로그인 수
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	
	// SILVER에서 GOLD로 가는 추첨 수
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	
	private UserDao userDao;
	
	private DataSource dataSource;
	
	// 외부에서 DI 할 수 있도록 JDBD 트랜잭션 추상 Object
	private PlatformTransactionManager transactionManager;
	
	
	// mail
	private MailSender mailSender;
	
	public UserServiceImpl() {}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void upgradeLevels(UserVO inVO) throws SQLException {
		
		try {
			List<UserVO> list = userDao.getAll(inVO);
		
			for(UserVO user : list) {
				if(canUpgradeLevel(user) == true) {
					upgradeLevel(user);
				}
			}
		}catch (Exception e) {
			LOG.debug("=====================");
			LOG.debug("=rollback***********=");
			LOG.debug("=====================");
			
			throw e;
		}
	}
	
	/**
	 * 레벨 업그레이드 작업
	 * @param user
	 * @throws SQLException 
	 */
	public void upgradeLevel(UserVO user) throws SQLException {
		
		//p77000
//		if("p77000".equals(user.getuId())) {
//			throw new TestUserServiceException("트랜잭션 테스트: " + user.getuId());
//		}
		
//		다음 레벨 up
		user.upgradeLevel();
		this.userDao.doUpdate(user);
		// 등업되면 메일 전송
		sendupgradeMail(user);
		
	}
	
	/**
	 * 등업되면 메일 전송
	 * 2번(BASIC -> SILVER), 4번 사용자(SILVER -> GOLD)
	 * @param user
	 */
	public void sendupgradeMail(UserVO user) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setFrom("qkrgns0514@naver.com");
		message.setSubject("등업 안내");
		message.setText("사용자의 등급이 " + user.getLevel().name() + "로 업그레이드 되었습니다.");
		
		mailSender.send(message);
		
	}
	
	/**
	 * User가 업그레이드 대상인지 확인
	 * @param user
	 * @return 대상(true)/대상이 아니면(false)
	 */
	private boolean canUpgradeLevel(UserVO user) {
		Level currentLevel = user.getLevel();
		
		switch (currentLevel) {
			case BASIC:  return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
			case GOLD:   return false;
				
			default: throw new IllegalArgumentException("Unknown Level: " + currentLevel);
		}
	}

	@Override
	public int add(UserVO inVO) throws SQLException {
		
		if(null == inVO.getLevel()) {
			inVO.setLevel(Level.BASIC);
		}
		
		return this.userDao.doInsert(inVO);
	}


}
