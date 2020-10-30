package com.mallxi.service;

import java.util.Collection;

import com.mallxi.beandata.Orders;

/**
 * 接口
 *
 */
public interface OdersService {


	Collection<Orders> findByUserid(String userid);

	void UpdateOrderInfo(Orders info);
	
	void cancelOrderInfo(Long id);
	
}
