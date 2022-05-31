package com.pcwk.ehr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JUnitUserDaoTest {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	ApplicationContext context;
	UserDao dao;
	UserVO user01;
	UserVO user02;
	
	@Test
	public void addAndGet() {
		LOG.debug("=====================");
		LOG.debug("=1. addAndGet()=");
		LOG.debug("=====================");
		
		// 전체 삭제
		try {
			dao.deleteAll();
			assertEquals(0, dao.getCount(user01));
			
			// 1건 추가
			dao.add(user01);
			assertEquals(1, dao.getCount(user01));
			
			// 1건 추가
			dao.add(user02);
			assertEquals(2, dao.getCount(user01));
			
			// 단건 조회
			UserVO vsUser01 = dao.get(user01);
			isSameUser(vsUser01, user01);
				
			
		} catch (SQLException e) {
			LOG.debug("=====================");
			LOG.debug("=SQLException=" + e.getMessage());
			LOG.debug("=====================");
			e.printStackTrace();
		}
	}
	
	private void isSameUser(UserVO vsVO, UserVO orgVO) {
		assertEquals(vsVO.getuId(), orgVO.getuId());
		assertEquals(vsVO.getName(), orgVO.getName());
		assertEquals(vsVO.getPasswd(), orgVO.getPasswd());
	}
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("=====================");
		LOG.debug("=0. setUp()=");
		LOG.debug("=====================");
		
		user01 = new UserVO("p77", "박훈", "4321");
		user02 = new UserVO("p770", "박훈0", "4321");
		
		context = new GenericXmlApplicationContext("/applicationContext.xml");
		
		dao = context.getBean("userDao", UserDao.class);
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	@After
	public void tearDown() throws Exception {
		LOG.debug("=====================");
		LOG.debug("=9. tearDown()=");
		LOG.debug("=====================");
	}
	
	

}
