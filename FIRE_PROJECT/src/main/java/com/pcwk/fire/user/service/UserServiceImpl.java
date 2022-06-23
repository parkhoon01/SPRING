package com.pcwk.fire.user.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.pcwk.fire.TestUserServiceException;
import com.pcwk.fire.cmn.DTO;
import com.pcwk.fire.user.dao.UserDao;
import com.pcwk.fire.user.domain.Level;
import com.pcwk.fire.user.domain.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	final Logger LOG = LogManager.getLogger(getClass());
	@Autowired
	private UserDao userDao;

	public UserServiceImpl() {
	}



	@Override
	public List<UserVO> doRetrieve(DTO dto) throws SQLException {
		
		return this.userDao.doRetrieve(dto);
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {
		
		return this.userDao.doDelete(inVO);
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException {
		
		return this.userDao.doSelectOne(inVO);
	}

	@Override
	public int doInsert(UserVO inVO) throws SQLException {
		
		return this.userDao.doInsert(inVO);
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		
		return this.userDao.doUpdate(inVO);
	}



	@Override
	public void upgradeLevels(UserVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int add(UserVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int idCheck(UserVO inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
