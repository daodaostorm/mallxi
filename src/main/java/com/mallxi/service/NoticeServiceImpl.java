package com.mallxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mallxi.beandata.Notice;
import com.mallxi.repository.NoticeRepository;

/**
 *
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	private NoticeRepository noticeRepository;

	/**
	 * 注入NoticeRepository
	 * 
	 * @param noticeRepository
	 */
	@Autowired
	public NoticeServiceImpl(NoticeRepository noticeRepository) {
		this.noticeRepository = noticeRepository;
	}


	/**
	 * 获取列表
	 */
	@Override
	public Page<Notice> getNoticeList(Long startid, Pageable p) {
		Page<Notice> notice = noticeRepository.findByIdGreaterThan(startid, p);
		return notice;
	}

}
