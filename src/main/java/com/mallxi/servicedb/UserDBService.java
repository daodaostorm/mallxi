package com.mallxi.servicedb;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mallxi.bean.User;
import com.mallxi.mapper.UserMapper;


@ComponentScan({"com.mallxi.mapper"})
@Service("userDBService")
public class UserDBService implements UserDBIService{


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userMapper.findAll();
	}

	@Override
	public User getuserbyname(String strName) {
		// TODO Auto-generated method stub
		return userMapper.getuserbyname(strName);
	}

	@Resource
	private UserMapper userMapper;
	
	@Override
	public void insert(User user) {
		userMapper.insert(user);
	}

	public void update(User user) {
		userMapper.update(user);
	}

	public User find(int id) {
		return userMapper.find(id);
	}
	
	
	public void delete(int id){
		userMapper.delete(id);
	}
}
