package com.mallxi.service;

import java.util.Collection;

import com.mallxi.beandata.Address;
import com.mallxi.beandata.Cart;

/**
 * 接口
 *
 */
public interface AddressService {


	Collection<Address> findByUserid(String userid);

	Address findByid(Long id);
	
	void UpdateAddressInfo(Address info);
	
	void removeAddressInfo(Long id);
	
	void removeAllAddressInfo(String userid);
}
