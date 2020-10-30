package com.mallxi.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.ClientUser;
import com.mallxi.repository.ClientUserRepository;


/**
 *
 */
@Service
public class ClientUserServiceImpl implements ClientUserService {


	private ClientUserRepository clientuserRepository;

	/**
	 * ClientUserRepository
	 * 
	 * @param clientuserRepository
	 */
	@Autowired
	public ClientUserServiceImpl(ClientUserRepository clientuserRepository) {
		this.clientuserRepository = clientuserRepository;
	}


	/**
	 * 获取列表
	 */
	@Override
	public Page<ClientUser> getClientUserList(Long startid, Pageable p) {
		Page<ClientUser> user = clientuserRepository.findByIdGreaterThan(startid, p);
		return user;
	}


	@Override
	public Collection<ClientUser> findByUserame(String username) {
		Collection<ClientUser> user = clientuserRepository.findByUsername(username);
		
		return user;
		
	}


	@Override
	public Collection<ClientUser> findByPhone(String tel) {
		// TODO Auto-generated method stub
		Collection<ClientUser> user = clientuserRepository.findByPhone(tel);
		
		return user;
	}

	@Override
	public Collection<ClientUser> findByUserid(String userid) {
		Collection<ClientUser> user = clientuserRepository.findByUserid(userid);
		
		return user;
	}
	

	@Override
	public void UpdateInfo(ClientUser userinfo) {
		// TODO Auto-generated method stub
		clientuserRepository.save(userinfo);
	}
	
	

}
