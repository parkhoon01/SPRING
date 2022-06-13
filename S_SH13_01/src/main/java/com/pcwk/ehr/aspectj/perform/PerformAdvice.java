package com.pcwk.ehr.aspectj.perform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class PerformAdvice {
	final Logger LOG = LogManager.getLogger(getClass());
	
	/**
	 * 메소드 측정
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	
	public Object performLog(ProceedingJoinPoint pjp) throws Throwable{
		// 메소드 name
		String method = pjp.getSignature().getName();
		
		// 시작시간
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object returnObj = pjp.proceed();
		
		// 종료시간
		stopWatch.stop();
		LOG.debug("["+method+"]메소드 경과시간: " + stopWatch.getTotalTimeMillis()+"(ms)초");
		
		return returnObj;
	}
	
	
}
