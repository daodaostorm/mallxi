package com.mallxi.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mallxi.beandata.Address;
import com.mallxi.beandata.Cart;
import com.mallxi.beandata.Orders;
import com.mallxi.beandata.Payinfo;
import com.mallxi.repository.AddressRepository;
import com.mallxi.repository.OrdersRepository;
import com.mallxi.repository.PayRepository;

/**
 *
 */
@Service
public class PayServiceImpl implements PayService {


	private PayRepository payRepository;

	/**
	 * PayRepository
	 * 
	 * @param payRepository
	 */
	@Autowired
	public PayServiceImpl(PayRepository payRepository) {
		this.payRepository = payRepository;
	}

	
	@Override
	public Collection<Payinfo> findByUserid(String userid) {
		// TODO Auto-generated method stub
		
		Collection<Payinfo> info = payRepository.findByUserid(userid);
		
		return info;
	
	}

	@Override
	public void UpdatePayInfo(Payinfo info) {
		// TODO Auto-generated method stub
		payRepository.save(info);
	}

	@Override
	public void cancelPayInfo(Long id) {
		// TODO Auto-generated method stub
		Payinfo info = payRepository.findOne(id);
		//order.setStatus(0);
		info.setStatus(-1);
		payRepository.save(info);
	}

	
}
