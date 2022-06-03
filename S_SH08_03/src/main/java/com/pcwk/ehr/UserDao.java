package com.pcwk.ehr;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface UserDao {

	List<UserVO> getAll();

	int getCount(UserVO inVO) throws SQLException;

	// 메서드를 통해 변하는 부분(SQL, SQL수행 부분)
	/**
	 * 사용자 등록
	 * 
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	int add(UserVO inVO) throws SQLException;

	/**
	 * 전체 데이터 삭제
	 * @throws SQLException
	 */
	void deleteAll() throws SQLException;

	/**
	 * 회원단건 return
	 * 
	 * @param inVO
	 * @return outVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	UserVO get(UserVO inVO) throws SQLException;

}