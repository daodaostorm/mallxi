package com.mallxi.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mallxi.beandata.ClientUser;


/**
 * 接口
 *
 */
public interface ClientUserService {


	Page<ClientUser> getClientUserList(Long startid, Pageable p);
	
	Collection<ClientUser> findByUserid(String userid);
	
	Collection<ClientUser> findByUserame(String username);
	
	Collection<ClientUser> findByPhone(String phone);
	
	void UpdateInfo(ClientUser userinfo);

}
