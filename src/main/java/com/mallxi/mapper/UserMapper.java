package com.mallxi.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mallxi.bean.User;


@Mapper
public interface UserMapper {

	public void insert(User user);

	public void update(User user);
	
	public void delete(int id);
	
	public User find(int id);
	
	public List<User> findAll();
	
	public User getuserbyname(String strName);
}
