package com.mallxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.BannerInfo;
import com.mallxi.beandata.ClientUser;
import com.mallxi.beandata.Lingquan;
import com.mallxi.repository.BannersRepository;
import com.mallxi.repository.LingquanRepository;

/**
 *
 */
@Service
public class LingquanServiceImpl implements LingquanService {

	private LingquanRepository lingquanRepository;

	/**
	 * LingquanRepository
	 * 
	 * @param lingquanRepository
	 */
	@Autowired
	public LingquanServiceImpl(LingquanRepository lingquanRepository) {
		this.lingquanRepository = lingquanRepository;
	}

	@Override
	public Page<Lingquan> getLingquanList(Long startid, Pageable p) {
		// TODO Auto-generated method stub
		Page<Lingquan> lingquan = lingquanRepository.findByIdGreaterThan(startid, p);
		return lingquan;
	}



}
