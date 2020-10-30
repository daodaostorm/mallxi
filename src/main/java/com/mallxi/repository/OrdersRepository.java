package com.mallxi.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.Orders;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {

	Page<Orders> findByIdGreaterThan(Long startid, Pageable p);

	Collection<Orders> findByUserid(String userid);

}
