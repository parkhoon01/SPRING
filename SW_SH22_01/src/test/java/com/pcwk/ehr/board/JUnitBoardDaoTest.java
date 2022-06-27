/**
* <pre>
* com.pcwk.ehr.board
* Class Name : JUnitBoardDaoTest.java
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
package com.pcwk.ehr.board;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;

/**
 * @author ITSC
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // JUnit기능을 스프링 프레임으로 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
									"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // applicationContext.xml loading
public class JUnitBoardDaoTest {

	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired //컨텍스트 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고, 변수에 주입
	ApplicationContext context;
	
	@Autowired
	BoardDao dao;
	
	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	
	SearchVO search;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		LOG.debug("========================");
		LOG.debug("=0.setUp()=");
		LOG.debug("========================");
		search = new SearchVO(10, 1,"", "");
		
		board01 = new BoardVO(30, "제목_30", "내용_30", 0, "10", "날짜_사용안함", "김동호", "날짜_사용안함", "김동호");
		board02 = new BoardVO(300, "제목_300", "내용_300", 0, "10", "날짜_사용안함", "김동호", "날짜_사용안함", "김동호");
		board03 = new BoardVO(3000, "제목_3000", "내용_3000", 0, "10", "날짜_사용안함", "김동호", "날짜_사용안함", "김동호");
		
		
		LOG.debug("context : " + context);
		LOG.debug("dao : " + dao);
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	
	@Test
	public void updateReadCnt() throws SQLException {
		LOG.debug("========================");
		LOG.debug("=1.updateReadCnt()=");
		LOG.debug("========================");
		
		// 1. 데이터 삭제
		// 2. 한건 입력
		// 3. 단건 조회
		// 4. 조회 카운트 증가
		// 5. 단건 조회 비교
		
		
		// 1.
		dao.doDelete(board01);
		dao.doDelete(board02);
		dao.doDelete(board03);
		assertEquals(0, dao.getCount(board01));
		
		// 2.
		dao.doInsert(board01);
		assertEquals(1, dao.getCount(board01));
		
		// 3.
		dao.doSelectOne(board01);
		
		// 4.
		dao.updateReadCnt(board01);
		
		BoardVO vsVO = dao.doSelectOne(board01);
		assertEquals(1, vsVO.getReadCnt());
		
	}
	
	@Test
//	@Ignore
	public void doRetrieve() throws SQLException {
		search.setSearchDiv("10");
		search.setSearchWord("제목_30");
		search.setDiv("10");
		List<BoardVO> list = dao.doRetrieve(search);
//		assertEquals(10, list.size());
	}
	
	
	@Test
//	@Ignore
	public void update() throws SQLException{
		//1. 삭제
		//2. 1건등록
		//3. 1건등록 데이터조회
		//4. 1건조회 데이터수정
		//5. 수정
		//6. 비교
		LOG.debug("========================");
		LOG.debug("=1.update()=");
		LOG.debug("========================");
		
		//1.
		dao.doDelete(board01);
		dao.doDelete(board02);
		dao.doDelete(board03);
		assertEquals(0, dao.getCount(board01));
		
		//2. 
		dao.doInsert(board01);
		assertEquals(1, dao.getCount(board01));
		
		//3.
		BoardVO vsVO = dao.doSelectOne(board01);
		
		//4.
		String upStr = "_U";
		vsVO.setTitle(vsVO.getTitle() + upStr);
		vsVO.setContents(vsVO.getContents() + upStr);
		vsVO.setDiv("20");
		vsVO.setModId(vsVO.getModId() + upStr);
		
		//5.
		dao.doUpdate(vsVO);
		
		//6.
		BoardVO resultVO = dao.doSelectOne(vsVO);
		isSameData(vsVO, resultVO);
	}

	@Test
//	@Ignore
	public void addAndGet() throws SQLException {
		LOG.debug("========================");
		LOG.debug("=1.addAndGet()=");
		LOG.debug("========================");
		
		//1. 삭제
		dao.doDelete(board01);
		dao.doDelete(board02);
		dao.doDelete(board03);
		
		//2. 등록
		dao.doInsert(board01);
		
		//3. 단건 조회
		BoardVO outVO = dao.doSelectOne(board01);
		
		//4. 비교
		isSameData(outVO, board01);
		
		dao.doInsert(board02);
		BoardVO outVO2 = dao.doSelectOne(board02);
		isSameData(outVO2, board02);
		
		assertEquals(2, dao.getCount(board01));
		
	}
	
	private void isSameData(BoardVO vsVO, BoardVO orgVO) {
		assertEquals(vsVO.getSeq(), orgVO.getSeq());
		assertEquals(vsVO.getTitle(), orgVO.getTitle());
		assertEquals(vsVO.getContents(), orgVO.getContents());
		assertEquals(vsVO.getReadCnt(), orgVO.getReadCnt());
		assertEquals(vsVO.getDiv(), orgVO.getDiv());
		assertEquals(vsVO.getRegId(), orgVO.getRegId());
		assertEquals(vsVO.getModId(), orgVO.getModId());
	}
}
