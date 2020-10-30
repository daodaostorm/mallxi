package com.mallxi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mallxi.bean.User;
import com.mallxi.controller.UserController;
import com.mallxi.mapper.UserMapper;
import com.mallxi.utils.CommonStringUtils;

import java.util.ArrayList;
import java.util.List;
 
/**
 *  自定义UserService
 */
@Service
public class MyUserService<T extends User> implements UserDetailsService {
    @Autowired
    private UserMapper userDao;
 
    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserService.class);
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDao.getuserbyname(username);
            if (user == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            LOGGER.info("user:" + user.getUsername() + " " + user.getPassword());
            LOGGER.info("role:" + user.getRole());
            //用户权限
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority(user.getRole()));
            //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (CommonStringUtils.isNotBlank(user.getRole())) {
                String[] roles = user.getRole().split(",");
                for (String role : roles) {
                    if (CommonStringUtils.isNotBlank(role)) {
                        authorities.add(new SimpleGrantedAuthority(role.trim()));
                    }
                }
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
}
