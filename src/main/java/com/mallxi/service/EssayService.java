package com.mallxi.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mallxi.beandata.Essayinfo;

/**
 * 接口
 *
 */
public interface EssayService {


	Page<Essayinfo> getEssayList(Long startid, Pageable p);
	Collection<Essayinfo> findById(Long id);
}
