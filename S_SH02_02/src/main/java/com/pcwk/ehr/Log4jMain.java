package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jMain {
	final static Logger LOG = LogManager.getLogger(Log4jMain.class);
	public static void main(String[] args) {
		LOG.debug("==============================");
		LOG.debug("=Log4j2=");
		LOG.debug("==============================");
		
	}

}
