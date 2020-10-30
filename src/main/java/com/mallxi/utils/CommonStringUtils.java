package com.mallxi.utils;


public class CommonStringUtils {

	public static boolean isNotBlank(String strArg) {

		return strArg != null && strArg.length() > 0 && strArg.trim().length() > 0;
	}

	public static boolean isNotEmpty(String strArg) {

		return strArg != null && strArg.length() > 0;
	}

}
