package com.pcwk.ehr.login.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.user.domain.UserVO;
import com.pcwk.ehr.user.service.UserService;

@Controller("loginController")
@RequestMapping("login")
public class LoginController {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	public LoginController() {
		LOG.debug("==============================");
		LOG.debug("=LoginController()=");
		LOG.debug("==============================");
	}
	
	@RequestMapping(value = "/doLogin.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doLogin(UserVO inVO, HttpSession session) throws SQLException{
		String jsonString = "";
		LOG.debug("==============================");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("==============================");
		
		MessageVO message = userService.idPassCheck(inVO);
		// msgId; // 메시지ID
		// 1. id 확인 : 10
		// 2. pass 확인  : 20
		// 3. id/비번 확인  : 30
		if(null != message && "30".equals(message.getMsgId())) {
			UserVO loginUser = userService.doSelectOne(inVO);
			if(null != loginUser) {
				session.setAttribute("user", loginUser);
				
				message.setMsgContents(loginUser.getName() + "님이 로그인 되었습니다.");
			}
		}
		
		jsonString = new Gson().toJson(message);
		
		LOG.debug("==============================");
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("==============================");
		
		return jsonString;
	}
	
	@RequestMapping(value = "/loginView.do", method = RequestMethod.GET)
	public String loginView() throws SQLException{
		LOG.debug("==============================");
		LOG.debug("=loginView()=");
		LOG.debug("==============================");
		///WEB-INF/views/login/login.jsp
		return "login/login";
	}
	
}
