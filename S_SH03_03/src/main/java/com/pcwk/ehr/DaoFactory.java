package com.pcwk.ehr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 어플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class DaoFactory {

	@Bean	// 오브젝트 생성을 담당하는 IoC용 메서드
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker());

		return userDao;
	}

	@Bean
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}

}

//public BoardDao boardDao() {
//ConnectionMaker connectionMaker = new NConnectionMaker();
//BoardDao boardDao = new BoardDao(connectionMaker);
//
//return boardDao;
//}