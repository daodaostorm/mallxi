package com.mallxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.BannerInfo;
import com.mallxi.repository.BannersRepository;

/**
 *
 */
@Service
public class BannersServiceImpl implements BannersService {

	private BannersRepository bannersRepository;

	/**
	 * BannersRepository
	 * 
	 * @param bannersRepository
	 */
	@Autowired
	public BannersServiceImpl(BannersRepository bannersRepository) {
		this.bannersRepository = bannersRepository;
	}


	/**
	 * 获取列表
	 */
	@Override
	public Page<BannerInfo> getBannersList(Long startid, Pageable p) {
		Page<BannerInfo> banner = bannersRepository.findByIdGreaterThan(startid, p);
		
		//Page<BannerInfo> banner = bannersRepository.findAll(p);
		return banner;
	}

}
