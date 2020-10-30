package com.mallxi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mallxi.beandata.Zixun;



/**
 * 接口
 *
 */
public interface ZixunService {


	Page<Zixun> getZixunList(Long startid, Pageable p);

}
