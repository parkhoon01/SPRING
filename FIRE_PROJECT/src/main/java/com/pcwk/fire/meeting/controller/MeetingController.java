package com.pcwk.fire.meeting.controller;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("meetingController")   
@RequestMapping("meeting")  
public class MeetingController {
	final Logger LOG = LogManager.getLogger(getClass());
	
	public MeetingController() {
		
	}
	
	@RequestMapping(value = "/meetingView.do",method = RequestMethod.GET)
	public String meetingView() throws SQLException{
		LOG.debug("========================");
		LOG.debug("=meetingView()=");
		LOG.debug("========================");
		return "meeting/meeting";
	}
}
