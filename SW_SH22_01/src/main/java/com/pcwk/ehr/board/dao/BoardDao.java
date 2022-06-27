/**
* <pre>
* com.pcwk.ehr.board.dao
* Class Name : BoardDao.java
* Description:
* Author: ITSC
* Since: 2022/06/24
* Version 0.1
* Copyright (C) by KandJang All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/24 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;

/**
 * @author ITSC
 *
 */
public interface BoardDao {
	
	/**
	 * 조회 건수 증가
	 * @param inVO
	 * @return 1(성공) / 0(실패)
	 * @throws SQLException
	 */
	int updateReadCnt(BoardVO inVO) throws SQLException;
	
	/**
	 * 게시글 목록조회
	 * @param dto
	 * @return List<BoardVO>
	 * @throws SQLException
	 */
	List<BoardVO> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 게시글 삭제
	 * @param inVO
	 * @return 1(성공) / 0(실패)
	 * @throws SQLException
	 */
	int doDelete(BoardVO inVO) throws SQLException;
	
	/**
	 * 게시글 수정 가능
	 * @param inVO
	 * @return 1(성공) / 0(실패)
	 * @throws SQLException
	 */
	int doUpdate(BoardVO inVO) throws SQLException;

	// 총 건수
	int getCount(BoardVO inVO) throws SQLException;

	/**
	 * 게시글 등록
	 * @param inVO
	 * @return 1(성공) / 0(실패)
	 * @throws ClassCastException
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	int doInsert(BoardVO inVO) throws SQLException;//수정

	/**
	 * 게시글 단건 return
	 * @param inVO
	 * @return BoardVO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	BoardVO doSelectOne(BoardVO inVO) throws SQLException;
}
