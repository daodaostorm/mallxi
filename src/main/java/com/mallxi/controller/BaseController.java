package com.mallxi.controller;

import java.util.Map;

import com.mallxi.utils.TokenUtils;

public class BaseController {

	public boolean checkToken(Map heads, String userid) {

		boolean isReturnValue = heads.containsKey("token");

		if (isReturnValue) {
			String token = "";
			token = heads.get("token").toString();
			String tokenname = TokenUtils.getUserName(token);
			if (tokenname.equals(userid)) {
				isReturnValue = true;
			} else {
				isReturnValue = false;
			}
		}

		return isReturnValue;
	}

}
