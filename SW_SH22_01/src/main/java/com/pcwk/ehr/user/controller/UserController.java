package com.pcwk.ehr.user.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.user.domain.UserVO;
import com.pcwk.ehr.user.service.UserService;

@Controller("userController")
@RequestMapping("user") // 모든 메소드 user + /doSelectOne.do
public class UserController {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	final String VIEW_NAME = "user/user_mng";
	
	@Autowired
	UserService userService;
	
	public UserController() {
		
	}
	
	//http://localhost:8081/ehr/user/idCheck.do?uId=p03
	@RequestMapping(value="/idCheck.do", method=RequestMethod.GET
			, produces = "application/json;charset=UTF-8")
	@ResponseBody //스프링에서 비동기 처리를 하는 경우, HTTP 요청의 본문 body부분이 전달된다.
	public String idCheck(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("====================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("====================");
		
		String resultMsg = "";
		int flag = userService.idCheck(inVO);
		if(flag == 1) {
			resultMsg += inVO.getuId() + "가 존재합니다.";
		}else {
			resultMsg += inVO.getuId() + "는 사용할 수 있습니다.";
		}
		
		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		jsonString = new Gson().toJson(message);
		LOG.debug("====================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("====================");
		
		return jsonString;
	}
	
	@RequestMapping(value = "/userView.do", method = RequestMethod.GET)
	public String userView(Model model, SearchVO inVO) throws SQLException {
		LOG.debug("===================");
		LOG.debug("=userView()=");
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
		LOG.debug("====================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("====================");
		
		List<UserVO> list = userService.doRetrieve(inVO);
		
		int totalCnt = 0;
		//총 글수: paging사용
		//pageTotal : 총 페이지 수
		double pageTotal = 0;
		if(list.size() > 0 && list != null) {
			totalCnt = list.get(0).getTotalCnt();
			
			pageTotal = Math.ceil(totalCnt / (inVO.getPageSize() * 1.0));
			LOG.debug("====================");
			LOG.debug("=pageTotal=" + pageTotal);
			LOG.debug("====================");
		}
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageTotal", pageTotal);
		model.addAttribute("list", list);
		
		// /WEB-INF/views/user/user_mng.jsp
		return VIEW_NAME;
	}
	
	// method생략하면 get방식
	@RequestMapping(value="/doRetrieve.do", produces = "application/json;charset=UTF-8")
	@ResponseBody //스프링에서 비동기 처리를 하는 경우, HTTP 요청의 본문 body부분이 전달된다.
	public String doRetrieve(SearchVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("====================");
		LOG.debug("=inVO=" + inVO);
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
		LOG.debug("====================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("====================");
		
		List<UserVO> list = userService.doRetrieve(inVO);
		Gson gson = new Gson();
		jsonString = gson.toJson(list);
		
		LOG.debug("====================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("====================");
		
		
		return jsonString;
	}

	
	@RequestMapping(value="/doUpdate.do", method=RequestMethod.POST
			, produces = "application/json;charset=UTF-8")
	@ResponseBody //스프링에서 비동기 처리를 하는 경우, HTTP 요청의 본문 body부분이 전달된다.
	public String doUpdate(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("====================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("====================");
		
		String resultMsg = "";
		int flag = userService.doUpdate(inVO);
		if(flag == 1) {
			resultMsg = inVO.getuId() + "가 수정되었습니다";
		}else {
			resultMsg = inVO.getuId() + "수정 실패!";
		}
		
		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		Gson gson = new Gson();
		jsonString = gson.toJson(message);
		LOG.debug("====================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("====================");
		
		return jsonString;
	}

	
	@RequestMapping(value="/add.do", method=RequestMethod.POST
			, produces = "application/json;charset=UTF-8")
	@ResponseBody //스프링에서 비동기 처리를 하는 경우, HTTP 요청의 본문 body부분이 전달된다.
	public String add(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("====================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("====================");
		
		int flag = userService.add(inVO);
		String resultMessage = "";
		
		if(flag == 1) {
			resultMessage = inVO.getuId() + "가 등록 되었습니다.";
		}else {
			resultMessage = inVO.getuId() + "등록 실패!";
		}
		
		MessageVO message = new MessageVO(String.valueOf(flag), resultMessage);
		Gson gson = new Gson();
		jsonString = gson.toJson(message);
		LOG.debug("====================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("====================");
		
		return jsonString;
	}
	
	//http://localhost:8081/ehr/user/doDelete.do?uId=p03
	@RequestMapping(value="/doDelete.do", method=RequestMethod.GET
			, produces = "application/json;charset=UTF-8")
	@ResponseBody //스프링에서 비동기 처리를 하는 경우, HTTP 요청의 본문 body부분이 전달된다.
	public String doDelete(HttpServletRequest req, Model model, UserVO inVO) throws SQLException{
		String jsonString = "";
		
		// HttpServletRequest param 읽기
		String uId = req.getParameter("uId");
		LOG.debug("====================");
		LOG.debug("=uId=" + uId);
		// Command로 param 읽기(from name값이 VO member변수와 이름이 일치, setter로 값이 설정됨)
		LOG.debug("=inVO=" + inVO);
		LOG.debug("====================");
		
		String resultMsg = "";
		int flag = userService.doDelete(inVO);
		if(flag == 1) {
			resultMsg += inVO.getuId() + "가 삭제 되었습니다.";
		}else {
			resultMsg = inVO.getuId() + "가 삭제 실패!";
		}
		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		Gson gson = new Gson();
		
		jsonString = gson.toJson(message);
		LOG.debug("====================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("====================");
		
		
		return jsonString;
	}
	
	//GET방식으로 : http://localhost:8081/ehr/user/doSelectOne.do?uId=p03
	@RequestMapping(value="/doSelectOne.do", method = RequestMethod.GET
			, produces = "application/json;charset=UTF-8")
	@ResponseBody //스프링에서 비동기 처리를 하는 경우, HTTP 요청의 본문 body부분이 전달된다.
	//UserVO inVO : form name VO 멤버변수명이 동일하면 자동으로 매핑한다.
	public String doSelectOne(UserVO inVO) throws SQLException{
		LOG.debug("====================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("====================");
		
		UserVO outVO = userService.doSelectOne(inVO);
		Gson gson = new Gson();
		String jsonString = gson.toJson(outVO);
		LOG.debug("====================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("====================");
		
		return jsonString;
	}

}
