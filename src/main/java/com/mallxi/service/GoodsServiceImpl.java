package com.mallxi.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.GoodInfo;
import com.mallxi.repository.GoodsRepository;

/**
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Override
	public Collection<GoodInfo> findById(Long id) {
		// TODO Auto-generated method stub
		 Collection<GoodInfo> goodinfo = goodsRepository.findById(id);
		return goodinfo;
	}


	private GoodsRepository goodsRepository;

	/**
	 * 注入NoticeRepository
	 * 
	 * @param noticeRepository
	 */
	@Autowired
	public GoodsServiceImpl(GoodsRepository goodsRepository) {
		this.goodsRepository = goodsRepository;
	}


	/**
	 * 获取列表
	 */
	@Override
	public Page<GoodInfo> getGoodsList(Long startid, Pageable p) {
		Page<GoodInfo> good = goodsRepository.findByIdGreaterThan(startid, p);
		return good;
	}

}
