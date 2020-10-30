package com.mallxi.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.ClientUser;


/**
 * 表Repository定义
 *
 */
@RestResource(exported = false)

public interface ClientUserRepository extends PagingAndSortingRepository<ClientUser, Long> {

	Page<ClientUser> findByIdGreaterThan(Long startid, Pageable p);

	Collection<ClientUser> findByCreatetime(String createtime);
	Collection<ClientUser> findByPhone(String phone);
	Collection<ClientUser> findByUsername(String username);
	Collection<ClientUser> findByUserid(String userid);

}
