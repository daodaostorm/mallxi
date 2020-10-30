package com.mallxi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mallxi.beandata.Notice;

/**
 * 接口
 *
 */
public interface NoticeService {


	Page<Notice> getNoticeList(Long startid, Pageable p);

}
