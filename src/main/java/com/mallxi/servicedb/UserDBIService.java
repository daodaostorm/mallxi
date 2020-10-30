package com.mallxi.servicedb;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mallxi.bean.User;



public interface UserDBIService {
	
	public void insert(User user);
	
	public List<User> findAll();
	
	public User getuserbyname(String strName);
}
