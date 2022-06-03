package com.pcwk.ehr;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)//JUnit 기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = "/applicationContext.xml") // applicationContext.xml loading
public class JAnnotaionTest {
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired // Context 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고, 변수에 주입
	ApplicationContext context;
	
	Tv tv;

	@Before
	public void setUp() throws Exception {
		LOG.debug("========================");
		LOG.debug("0. =setUp()=");
		LOG.debug("========================");
		tv = (Tv) context.getBean("lgTv");
		
		LOG.debug("context: " + context);
		LOG.debug("tv: " + tv);
		assertNotNull(context);
		assertNotNull(tv);
	}

	@After
	public void tearDown() throws Exception {
		LOG.debug("========================");
		LOG.debug("9. =tearDown()=");
		LOG.debug("========================");
	}

	@Test
	public void test() {
		LOG.debug("========================");
		LOG.debug("=test()=");
		LOG.debug("========================");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
	}

}
