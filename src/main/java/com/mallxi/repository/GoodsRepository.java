package com.mallxi.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.GoodInfo;

/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface GoodsRepository extends PagingAndSortingRepository<GoodInfo, Long> {

	Page<GoodInfo> findByIdGreaterThan(Long startid, Pageable p);

	Collection<GoodInfo> findById(Long id);
	
}
