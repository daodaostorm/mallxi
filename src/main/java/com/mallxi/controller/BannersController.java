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

import com.mallxi.beandata.BannerInfo;
import com.mallxi.message.Message;
import com.mallxi.service.BannersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "BannersAPI")
@Api(description = "BannersAPI")

public class BannersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BannersController.class);
	private Message message = new Message();

	private BannersService bannersService;

	@Autowired
	public BannersController(BannersService bannersService) {
		this.bannersService = bannersService;
	}

	@GetMapping(value = "banners/getBannersList")
	@ApiOperation(value = "获取Banner列表接口", notes = "获取Banner列表，接口startid默认0，下拉时根据最大值获取后续列表，可分页查询")
	public ResponseEntity<Message> getBannersList(@RequestParam(defaultValue = "0")  Long startid, @PageableDefault(value  = 0, size=20, sort = { "updateby" }, direction = Sort.Direction.DESC) Pageable p) {
		
		//Sort sort = new Sort(Sort.Direction.DESC,"updateby");
		//Iterable<BannerInfo> bannerlist = bannerRepository.findAll(sort);
		Page<BannerInfo> banners = bannersService.getBannersList(startid, p);
		message.setMsg(0, "获取Banner列表成功", banners);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
