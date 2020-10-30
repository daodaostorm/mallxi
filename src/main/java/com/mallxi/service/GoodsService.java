package com.mallxi.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mallxi.beandata.GoodInfo;

/**
 * 接口
 *
 */
public interface GoodsService {


	Page<GoodInfo> getGoodsList(Long startid, Pageable p);
	Collection<GoodInfo> findById(Long id);
}
