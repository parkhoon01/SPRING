package com.pcwk.ehr.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.jdbc.proxy.annotation.GetCreator;

public class ReflectionTest {
	final static Logger LOG = LogManager.getLogger(ReflectionTest.class);
	
	
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 리플렉션: 자바코드를 추상화해서 접근
		// 모든 자바 클래스는 Class 타입의 오브젝트를 하나씩 가지고 있다.
		
		// 기존 방법 vs 리플렉션
		
		String name = "Spring";
		// 기존 방법
		LOG.debug("name의 length: " + name.length());
		
		// 리플렉션
		Method lengthMethod = String.class.getMethod("length");
		int nameLength = (int) lengthMethod.invoke(name, args);
		
		LOG.debug("리플렉션의 name의 length: " + nameLength);
		LOG.debug("======================");
		
		// 기존 방법 vs 리플렉션
		// 기존 방법
		LOG.debug("charAt: " + name.charAt(0));
		
		// 리플렉션
		Class sClass = String.class;
		Method charAtMethod = sClass.getMethod("charAt", int.class);
		LOG.debug("리플렉션의 charAtMethod: " + charAtMethod.invoke(name, 0));
	}

}
