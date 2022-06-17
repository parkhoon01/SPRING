package com.pcwk.fire.user.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.fire.cmn.DTO;
import com.pcwk.fire.user.domain.UserVO;

public interface UserService {

	/**
	 * 회원목록 조회
	 * @param dto
	 * @return List<UserVO>
	 * @throws SQLException
	 */
	public List<UserVO> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 회원 정보 삭제
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doDelete(UserVO inVO) throws SQLException;
	
	/**
	 * 회원 정보 단건 조회
	 * @param inVO
	 * @return UserVO
	 * @throws SQLException
	 */
	public UserVO doSelectOne(UserVO inVO) throws SQLException;
	
	/**
	 * 회원 등록
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doInsert(final UserVO inVO) throws SQLException;
	
	/**
	 * 회원 정보 수정
	 * @param inVO
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	public int doUpdate(UserVO inVO) throws SQLException;
	
	
}
