package com.mallxi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mallxi.beandata.BannerInfo;

/**
 * 接口
 *
 */
public interface BannersService {


	Page<BannerInfo> getBannersList(Long startid, Pageable p);

}
