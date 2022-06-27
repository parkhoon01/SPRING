package com.pcwk.ehr.board.service;

import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;

public interface BoardService {
	
	
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
