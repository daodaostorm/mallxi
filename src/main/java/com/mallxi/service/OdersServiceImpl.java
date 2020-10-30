package com.mallxi.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mallxi.beandata.Address;
import com.mallxi.beandata.Cart;
import com.mallxi.beandata.Orders;
import com.mallxi.repository.AddressRepository;
import com.mallxi.repository.OrdersRepository;

/**
 *
 */
@Service
public class OdersServiceImpl implements OdersService {


	private OrdersRepository ordersRepository;

	/**
	 * OrdersRepository
	 * 
	 * @param ordersRepository
	 */
	@Autowired
	public OdersServiceImpl(OrdersRepository ordersRepository) {
		this.ordersRepository = ordersRepository;
	}

	
	@Override
	public Collection<Orders> findByUserid(String userid) {
		// TODO Auto-generated method stub
		
		Collection<Orders> order = ordersRepository.findByUserid(userid);
		
		return order;
	
	}

	@Override
	public void UpdateOrderInfo(Orders info) {
		// TODO Auto-generated method stub
		ordersRepository.save(info);
	}

	@Override
	public void cancelOrderInfo(Long id) {
		// TODO Auto-generated method stub
		Orders order = ordersRepository.findOne(id);
		order.setStatus(0);
		ordersRepository.save(order);
	}

	
}
