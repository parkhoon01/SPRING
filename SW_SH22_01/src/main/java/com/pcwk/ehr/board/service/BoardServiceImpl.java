package com.pcwk.ehr.board.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	public BoardServiceImpl() {
		
	}
	
	@Override
	public List<BoardVO> doRetrieve(DTO dto) throws SQLException {
		return boardDao.doRetrieve(dto);
	}

	@Override
	public int doDelete(BoardVO inVO) throws SQLException {
		return boardDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(BoardVO inVO) throws SQLException {
		return boardDao.doUpdate(inVO);
	}

	@Override
	public int doInsert(BoardVO inVO) throws SQLException {
		return boardDao.doInsert(inVO);
	}

	@Override
	public BoardVO doSelectOne(BoardVO inVO) throws SQLException {
		// 단건 조회
		// 조회 카운트 증가
		BoardVO outVO = boardDao.doSelectOne(inVO);
		// 2.1. 본인이 등록한 글은 조회 count가 증가 되지 않게 처리
		
//		if(outVO != null && !outVO.getRegId().equals(inVO.getModId())) {
		if(outVO != null) {
			boardDao.updateReadCnt(inVO);
		}
		return outVO;
	}

}
