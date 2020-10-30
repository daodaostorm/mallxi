package com.mallxi.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.Cart;
import com.mallxi.beandata.ClientUser;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {

	Page<Cart> findByIdGreaterThan(Long startid, Pageable p);

	Collection<Cart> findByUserid(String userid);

}
