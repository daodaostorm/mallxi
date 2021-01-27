package com.mallxi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

	public static String getNowDate() {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static String getNowDateHour() {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("MMddHH");
		return df.format(date);
	}
	
	public static String getNowDateTime() {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		return df.format(date);
	}

	public static String createNowTimeId() {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(date);
	}
}
