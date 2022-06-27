/** 
 * 게시 컨트롤러
 */
package com.pcwk.ehr.board.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.board.service.BoardService;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.user.domain.UserVO;

@Controller("boardController")
@RequestMapping("board")
public class BoardController {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	BoardService boardService;
	
	public BoardController() {
		
	}
	
	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET,
					produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("====================");
		LOG.debug("=doRetrieve=");
		LOG.debug("=inVO= " + inVO);
		LOG.debug("====================");
		
		//페이지 사이즈
		if(inVO.getPageSize() == 0) {
			inVO.setPageSize(10);
		}
		//페이지 번호
		if(inVO.getPageNum() == 0) {
			inVO.setPageNum(1);
		}
		//검색구분
		if(inVO.getSearchDiv() == null) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv(), ""));
		}
		//검색어
		if(inVO.getSearchWord() == null) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord(), ""));
		}
		
		// 게시 구분
		if(null == inVO.getDiv()) {
			inVO.setDiv(StringUtil.nvl(inVO.getDiv(), "10"));
		}
		
		LOG.debug("====================");
		LOG.debug("=inVO= " + inVO);
		LOG.debug("====================");
		
		List<BoardVO> list = boardService.doRetrieve(inVO);
		Gson gson = new Gson();
		
		jsonString = gson.toJson(list);
		
		LOG.debug("====================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("====================");
		
		return jsonString;
	}
	
	// board/board_list
	// board/board_reg
	// board/board_mod
	
	//http://localhost:8081/ehr/board/boardView.do
	@RequestMapping(value = "/boardView.do", method = RequestMethod.GET)
	public String boardView(Model model, SearchVO inVO) throws SQLException{
		LOG.debug("===================");
		LOG.debug("=boardView()=");
		LOG.debug("===================");
		
		//페이지 사이즈
		if(inVO.getPageSize() == 0) {
			inVO.setPageSize(10);
		}
		//페이지 번호
		if(inVO.getPageNum() == 0) {
			inVO.setPageNum(1);
		}
		//검색구분
		if(inVO.getSearchDiv() == null) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv(), ""));
		}
		//검색어
		if(inVO.getSearchWord() == null) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord(), ""));
		}
		
		// 게시 구분
		if(null == inVO.getDiv()) {
			inVO.setDiv(StringUtil.nvl(inVO.getDiv(), "10"));
		}
		
		List<BoardVO> list = boardService.doRetrieve(inVO);
		model.addAttribute("list", list);
		model.addAttribute("vo", inVO);
			
		LOG.debug("===================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("===================");
		
		return "board/board_list";
		}
}
