package com.mallxi.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mallxi.beandata.Address;
import com.mallxi.beandata.Cart;
import com.mallxi.repository.AddressRepository;

/**
 *
 */
@Service
public class AddressServiceImpl implements AddressService {


	private AddressRepository addressRepository;

	/**
	 * AddressRepository
	 * 
	 * @param bannersRepository
	 */
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	
	@Override
	public Address findByid(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.findOne(id);
	}


	@Override
	public Collection<Address> findByUserid(String userid) {
		// TODO Auto-generated method stub
		
		Collection<Address> address = addressRepository.findByUserid(userid);
		
		return address;
	
	}

	@Override
	public void UpdateAddressInfo(Address info) {
		// TODO Auto-generated method stub
		addressRepository.save(info);
	}

	@Override
	public void removeAddressInfo(Long id) {
		// TODO Auto-generated method stub
		addressRepository.delete(id);
	}

	@Override
	public void removeAllAddressInfo(String userid) {
		// TODO Auto-generated method stub
		Collection<Address> address = addressRepository.findByUserid(userid);
		Iterator<Address> addressIterator = address.iterator();
		while(addressIterator.hasNext()) {
			removeAddressInfo(addressIterator.next().getId());
		}
	}

	

	
}
