package com.pcwk.ehr;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("tv")
@Component // 안넣으면 lgTv(클래스이름의 첫글자만 소문자)
public class LgTv implements Tv {
	final Logger LOG = LogManager.getLogger(getClass());
	final String brand = "엘지Tv";
	
	@Resource(name = "sonySpeaker")
	private Speaker speaker;
	
	public LgTv() {}
	
	@Override
	public void powerOn() {
		LOG.debug(brand + " 전원을 켠다.");
	}

	@Override
	public void powerOff() {
		LOG.debug(brand + " 전원을 끈다.");
	}

	@Override
	public void volumeUp() {
//		LOG.debug(brand + " 볼륨을 높인다.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
//		LOG.debug(brand + " 볼륨을 낮춘다.");
		speaker.volumeDown();
	}

}
