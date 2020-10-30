package com.mallxi.repository;

import org.springframework.data.repository.CrudRepository;

import com.mallxi.beandata.Sms;

public interface SmsRepository extends CrudRepository<Sms, Long> {
	
	Sms findFirstByMobileAndVcodeOrderByExpiredDatetimeDesc(String mobile, String vcode);

}
