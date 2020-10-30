package com.mallxi.service;

import com.mallxi.beandata.Sms;

public interface SendSMSService {

	String send(String mobile);

	Sms findByUsernameAndVcode(String mobile, String vcode);

	void saveSmsConfig(String username, String password);

	Boolean checkSmsAccountStatus(String account, String password);

}
