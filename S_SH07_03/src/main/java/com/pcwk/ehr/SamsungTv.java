package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SamsungTv implements Tv {
	final Logger LOG = LogManager.getLogger(getClass());
	final String brand = "삼성Tv";
	private SonySpeaker speaker;
	
	public SamsungTv() {
		LOG.debug(brand + "default 생성자 SamsungTv()");
	}
	// 생성자 인젝션
	public SamsungTv(SonySpeaker speaker) {
		super();
		this.speaker = speaker;
	}
	
	// 생성자 다음 초기화
	public void initMethod() {
		LOG.debug(brand + " initMethod()");
	}
	
	// 자원반납, applicationContext가 종료시 수행
	public void destroyMethod() {
		LOG.debug(brand + " destroyMethod()");
	}
	
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
//		speaker = new SonySpeaker();
//		LOG.debug(brand + " 볼륨을 높인다.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
//		speaker = new SonySpeaker();
//		LOG.debug(brand + " 볼륨을 낮춘다.");
		speaker.volumeDown();
	}

}
