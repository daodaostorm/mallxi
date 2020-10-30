package com.mallxi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mallxi.beandata.Notice;
import com.mallxi.message.Message;
import com.mallxi.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "NoticeAPI")
@Api(description = "NoticeAPI")

public class NoticeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);
	private Message message = new Message();

	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@GetMapping(value = "notice/getNoticeList")
	@ApiOperation(value = "获取物品列表接口", notes = "获取物品列表，接口startid默认0，下拉时根据最大值获取后续列表，可分页查询")
	public ResponseEntity<Message> getNewsList(@RequestParam(defaultValue = "0") Long startid, @PageableDefault(value  = 0, size=20, sort = { "updateby" }, direction = Sort.Direction.DESC) Pageable p) {
		Page<Notice> stores = noticeService.getNoticeList(startid, p);
		message.setMsg(0, "获取物品列表成功", stores);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
