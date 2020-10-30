package com.mallxi.service;

import java.util.Collection;

import com.mallxi.beandata.Cart;

/**
 * 接口
 *
 */
public interface CartService {


	Collection<Cart> findByUserid(String userid);

	void UpdateCartInfo(Cart info);
	
	void removeCartInfo(Long id);
	
	void removeAllCartInfo(String userid);
}
