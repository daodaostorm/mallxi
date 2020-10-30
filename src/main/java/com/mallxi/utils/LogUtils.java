package com.mallxi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);
	
	public static String TAGNAME = "ranchulog--";

	public static void info(String strLog){
		
		LOGGER.info(TAGNAME + strLog);
	}

}
