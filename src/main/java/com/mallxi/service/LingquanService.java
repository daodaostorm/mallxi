package com.mallxi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mallxi.beandata.Lingquan;


/**
 * 接口
 *
 */
public interface LingquanService {


	Page<Lingquan> getLingquanList(Long startid, Pageable p);

}
