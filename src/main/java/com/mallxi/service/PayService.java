package com.mallxi.service;

import java.util.Collection;

import com.mallxi.beandata.Payinfo;

/**
 * 接口
 *
 */
public interface PayService {


	Collection<Payinfo> findByUserid(String userid);

	void UpdatePayInfo(Payinfo info);
	
	void cancelPayInfo(Long id);
	
}
