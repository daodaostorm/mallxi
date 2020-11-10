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

import com.mallxi.beandata.Essayinfo;
import com.mallxi.beandata.GoodInfo;
import com.mallxi.message.Message;
import com.mallxi.service.EssayService;
import com.mallxi.service.GoodsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "EssaysAPI")
@Api(description = "EssaysAPI")

public class EssaysController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EssaysController.class);
	private Message message = new Message();

	private EssayService essayService;

	@Autowired
	public EssaysController(EssayService essayService) {
		this.essayService = essayService;
	}

	@GetMapping(value = "essay/getEssaysList")
	@ApiOperation(value = "获取列表接口", notes = "获取列表，接口startid默认0，下拉时根据最大值获取后续列表，可分页查询")
	public ResponseEntity<Message> getEssaysList(@RequestParam(defaultValue = "0") Long startid, @PageableDefault(value  = 0, size=20, sort = { "updateby" }, direction = Sort.Direction.DESC) Pageable p) {
		Page<Essayinfo> infos = essayService.getEssayList(startid, p);
		message.setMsg(0, "获取列表成功", infos);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@GetMapping(value = "essay/getEssaydetail")
	@ApiOperation(value = "获取详情", notes = "获取详情")
	public ResponseEntity<Message> getEssayDetail(@RequestParam Long id) {
		Collection<Essayinfo> infos = essayService.findById(id);
		if (infos != null && infos.size() >= 1) {
			message.setMsg(0, "获取成功", infos.toArray()[0]);
		} else {
			message.setMsg(0, "获取成功", "");
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
