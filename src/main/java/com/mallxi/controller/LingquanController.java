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

import com.mallxi.beandata.Lingquan;
import com.mallxi.message.Message;
import com.mallxi.service.LingquanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "LingquanAPI")
@Api(description = "LingquanAPI")

public class LingquanController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LingquanController.class);
	private Message message = new Message();

	private LingquanService lingquanService;

	@Autowired
	public LingquanController(LingquanService lingquanService) {
		this.lingquanService = lingquanService;
	}

	@GetMapping(value = "lingquan/getLingquanList")
	@ApiOperation(value = "获取lingquan列表接口", notes = "获取lingquan列表，接口startid默认0，下拉时根据最大值获取后续列表，可分页查询")
	public ResponseEntity<Message> getNewsList(@RequestParam(defaultValue = "0") Long startid, @PageableDefault(value  = 0, size=20, sort = { "updateby" }, direction = Sort.Direction.DESC) Pageable p) {
		Page<Lingquan> lingquan = lingquanService.getLingquanList(startid, p);
		message.setMsg(0, "获取lingquan列表成功", lingquan);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
