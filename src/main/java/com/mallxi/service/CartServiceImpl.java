package com.mallxi.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.Cart;
import com.mallxi.repository.CartRepository;

/**
 *
 */
@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;

	/**
	 * CartRepository
	 * 
	 * @param cartRepository
	 */
	@Autowired
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public Collection<Cart> findByUserid(String userid) {
		// TODO Auto-generated method stub

		Collection<Cart> cart = cartRepository.findByUserid(userid);

		return cart;

	}

	@Override
	public void UpdateCartInfo(Cart info) {

		cartRepository.save(info);
		
	}

	@Override
	public void removeCartInfo(Long id) {

		cartRepository.delete(id);
		
	}

	@Override
	public void removeAllCartInfo(String userid) {
		// TODO Auto-generated method stub
		Collection<Cart> cart = cartRepository.findByUserid(userid);
		Iterator<Cart> cartIterator = cart.iterator();
		while(cartIterator.hasNext()) {
			removeCartInfo(cartIterator.next().getId());
		}
	}

}
