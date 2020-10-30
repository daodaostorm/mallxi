package com.mallxi.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.Payinfo;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface PayRepository extends PagingAndSortingRepository<Payinfo, Long> {

	Page<Payinfo> findByIdGreaterThan(Long startid, Pageable p);

	Collection<Payinfo> findByUserid(String userid);

}
