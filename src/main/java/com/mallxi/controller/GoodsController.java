package com.mallxi.controller;

import java.util.Collection;

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

import com.mallxi.beandata.GoodInfo;
import com.mallxi.message.Message;
import com.mallxi.service.GoodsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "GoodsAPI")
@Api(description = "GoodsAPI")

public class GoodsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
	private Message message = new Message();

	private GoodsService goodsService;

	@Autowired
	public GoodsController(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	@GetMapping(value = "good/getGoodsList")
	@ApiOperation(value = "获取物品列表接口", notes = "获取物品列表，接口startid默认0，下拉时根据最大值获取后续列表，可分页查询")
	public ResponseEntity<Message> getGoodsList(@RequestParam(defaultValue = "0") Long startid, @PageableDefault(value  = 0, size=20, sort = { "updateby" }, direction = Sort.Direction.DESC) Pageable p) {
		Page<GoodInfo> goods = goodsService.getGoodsList(startid, p);
		message.setMsg(0, "获取物品列表成功", goods);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@GetMapping(value = "good/getdetail")
	@ApiOperation(value = "获取物品详情", notes = "获取物品详情")
	public ResponseEntity<Message> getGoodDetail(@RequestParam Long id) {
		Collection<GoodInfo> goods = goodsService.findById(id);
		if (goods != null && goods.size() >= 1) {
			message.setMsg(0, "获取物品列表成功", goods.toArray()[0]);
		} else {
			message.setMsg(0, "获取物品列表成功", "");
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
