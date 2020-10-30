package com.mallxi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.Zixun;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface ZixunRepository extends PagingAndSortingRepository<Zixun, Long> {

	Page<Zixun> findByIdGreaterThan(Long startid, Pageable p);

}
