package com.mallxi.controller;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mallxi.beandata.Orders;
import com.mallxi.beandata.Payinfo;
import com.mallxi.message.Message;
import com.mallxi.service.OdersService;
import com.mallxi.service.PayService;
import com.mallxi.utils.DateTimeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/api/v1", name = "OrdersAPI")
@Api(description = "OrdersAPI")

public class OrdersController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);
	private Message message = new Message();

	private OdersService ordersService;
	private PayService payService;

	@Autowired
	public OrdersController(OdersService ordersService, PayService payService) {
		this.ordersService = ordersService;
		this.payService = payService;
	}

	@GetMapping(value = "order/getOrdersinfo")
	@ApiOperation(value = "获取地址", notes = "获取地址")
	public ResponseEntity<Message> getAllAddress(@RequestHeader Map heads, @RequestParam String userid) {

		if (checkToken(heads, userid)) {

			Collection<Orders> orders = ordersService.findByUserid(userid);

			message.setMsg(0, "获取订单", orders);

		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "order/createOrdersinfo", method = RequestMethod.POST)
	@ApiOperation(value = "创建订单", notes = "创建订单")
	public ResponseEntity<Message> createinfo(@RequestHeader Map heads, @RequestBody Map params) {

		String userid = params.get("userid").toString();

		if (checkToken(heads, userid)) {

			Orders order = new Orders();
			JSONObject obj = JSONObject.fromObject(params);

			order.setUserid(userid);
			order.setFulladdress(obj.getString("fulladdress"));
			order.setShoppingid(obj.getString("shoppingid"));
			order.setProdid(obj.getString("prodid"));
			order.setProdname(obj.getString("prodname"));
			order.setProdmainpic(obj.getString("prodmainpic"));
			order.setProdnum(obj.getInt("prodnum"));
			Double value = obj.getDouble("prodvalue");
			order.setProdvalue(value.floatValue());
			
			order.setOrderid(DateTimeUtils.createNowTimeId());
			order.setCreatetime(DateTimeUtils.getNowDateTime());
			order.setUpdatetime(DateTimeUtils.getNowDateTime());

			ordersService.UpdateOrderInfo(order);

			message.setMsg(0, "创建订单成功", order);

		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "order/cancelOrdersinfo", method = RequestMethod.POST)
	@ApiOperation(value = "取消订单", notes = "取消订单")
	public ResponseEntity<Message> cancelinfo(@RequestHeader Map heads, @RequestBody Map params) {

		String userid = params.get("userid").toString();
		long deleteid = Long.parseLong(params.get("id").toString());

		if (checkToken(heads, userid)) {

			ordersService.cancelOrderInfo(deleteid);

			message.setMsg(0, "取消订单成功", userid);
		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "order/payOrder", method = RequestMethod.POST)
	@ApiOperation(value = "支付订单", notes = "支付订单")
	public ResponseEntity<Message> payinfo(@RequestHeader Map heads, @RequestBody Map params) {

		String userid = params.get("userid").toString();

		if (checkToken(heads, userid)) {

			Payinfo pay = new Payinfo();
			JSONObject obj = JSONObject.fromObject(params);

			pay.setPayid(DateTimeUtils.createNowTimeId());
			pay.setUserid(userid);
			pay.setOrderid(obj.getString("orderid"));
			
			pay.setPayplatform(obj.getLong("payplatform"));
		
			pay.setCreatetime(DateTimeUtils.getNowDateTime());
			pay.setUpdatetime(DateTimeUtils.getNowDateTime());

			payService.UpdatePayInfo(pay);

			message.setMsg(0, "支付成功", pay);

		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
}
