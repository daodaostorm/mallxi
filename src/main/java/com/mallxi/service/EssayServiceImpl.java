package com.mallxi.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.Essayinfo;
import com.mallxi.repository.EssayRepository;

/**
 *
 */
@Service
public class EssayServiceImpl implements EssayService {

	@Override
	public Collection<Essayinfo> findById(Long id) {
		// TODO Auto-generated method stub
		 Collection<Essayinfo> essays = essayRepository.findById(id);
		return essays;
	}


	private EssayRepository essayRepository;

	/**
	 * 注入EssayRepository
	 * 
	 * @param essayRepository
	 */
	@Autowired
	public EssayServiceImpl(EssayRepository essayRepository) {
		this.essayRepository = essayRepository;
	}


	/**
	 * 获取列表
	 */
	@Override
	public Page<Essayinfo> getEssayList(Long startid, Pageable p) {
		Page<Essayinfo> essays = essayRepository.findByIdGreaterThan(startid, p);
		return essays;
	}

}
