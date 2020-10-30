package com.mallxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.ClientUser;
import com.mallxi.beandata.Zixun;
import com.mallxi.repository.ZixunRepository;


/**
 *
 */
@Service
public class ZixunServiceImpl implements ZixunService {

	private ZixunRepository zixunRepository;

	/**
	 * ZixunRepository
	 * 
	 * @param zixunRepository
	 */
	@Autowired
	public ZixunServiceImpl(ZixunRepository zixunRepository) {
		this.zixunRepository = zixunRepository;
	}

	@Override
	public Page<Zixun> getZixunList(Long startid, Pageable p) {
		// TODO Auto-generated method stub
		Page<Zixun> zixun = zixunRepository.findByIdGreaterThan(startid, p);
		return zixun;
	}




}
