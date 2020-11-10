package com.mallxi.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.Essayinfo;
import com.mallxi.beandata.GoodInfo;

/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface EssayRepository extends PagingAndSortingRepository<Essayinfo, Long> {

	Page<Essayinfo> findByIdGreaterThan(Long startid, Pageable p);

	Collection<Essayinfo> findById(Long id);
	
}
