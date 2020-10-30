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

import com.mallxi.beandata.Cart;
import com.mallxi.message.Message;
import com.mallxi.service.CartService;
import com.mallxi.utils.DateTimeUtils;
import com.mallxi.utils.TokenUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/api/v1", name = "CartAPI")
@Api(description = "CartAPI")

public class CartController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
	private Message message = new Message();

	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping(value = "cart/getCartinfo")
	@ApiOperation(value = "获取购物车", notes = "获取购物车")
	public ResponseEntity<Message> getCartinfo(@RequestHeader Map heads, @RequestParam String userid) {

		boolean isContainTokey = heads.containsKey("token");

		if (isContainTokey) {
			String token = "";

			token = heads.get("token").toString();

			LOGGER.info("token:" + token);

			String tokenname = TokenUtils.getUserName(token);
			LOGGER.info("tokenname:" + tokenname);

			if (tokenname.equals(userid)) {
				Collection<Cart> cart = cartService.findByUserid(userid);

				message.setMsg(0, "获取购物车成功", cart);
			} else {
				message.setMsg(-3, "Token错误，请重新登录", userid);
			}
		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	
	@RequestMapping(value = "cart/addCartinfo", method = RequestMethod.POST)
	@ApiOperation(value = "添加购物车", notes = "添加购物车")
	public ResponseEntity<Message> addinfo(@RequestHeader Map heads, @RequestBody Map params) {

		boolean isContainTokey = heads.containsKey("token");
		String userid = params.get("userid").toString();
		
		if (isContainTokey) {
			String token = "";

			token = heads.get("token").toString();

			LOGGER.info("token:" + token);

			String tokenname = TokenUtils.getUserName(token);
			LOGGER.info("tokenname:" + tokenname);

			if (tokenname.equals(userid)) {
				
				JSONObject jsonObject = JSONObject.fromObject(params);
				Cart newcart = new Cart();
				newcart.setName(jsonObject.getString("name"));
				newcart.setUserid(jsonObject.getString("userid"));
				newcart.setProid(jsonObject.getString("proid"));
				newcart.setQuantity(jsonObject.getInt("quantity"));
				newcart.setMainimage(jsonObject.getString("mainimage"));
				
				newcart.setCreatetime(DateTimeUtils.getNowDateTime());

				newcart.setUpdatetime(DateTimeUtils.getNowDateTime());
				
				cartService.UpdateCartInfo(newcart);

				message.setMsg(0, "添加购物车成功", newcart.getUserid());
			} else {
				message.setMsg(-3, "Token错误，请重新登录", userid);
			}
		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "cart/removeCartinfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除购物车", notes = "删除购物车")
	public ResponseEntity<Message> removeinfo(@RequestHeader Map heads, @RequestBody Map params) {

		boolean isContainTokey = heads.containsKey("token");
		String userid = params.get("userid").toString();
		long deleteid = Long.parseLong(params.get("id").toString());
		
		if (isContainTokey) {
			String token = "";

			token = heads.get("token").toString();

			LOGGER.info("token:" + token);

			String tokenname = TokenUtils.getUserName(token);
			LOGGER.info("tokenname:" + tokenname);

			if (tokenname.equals(userid)) {
				
				
				cartService.removeCartInfo(deleteid);

				message.setMsg(0, "删除购物车成功", userid);
			} else {
				message.setMsg(-3, "Token错误，请重新登录", userid);
			}
		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "cart/removeAllCart", method = RequestMethod.POST)
	@ApiOperation(value = "删除购物车", notes = "删除购物车")
	public ResponseEntity<Message> removeAll(@RequestHeader Map heads, @RequestBody Map params) {

		boolean isContainTokey = heads.containsKey("token");
		String userid = params.get("userid").toString();
		
		if (isContainTokey) {
			String token = "";

			token = heads.get("token").toString();

			LOGGER.info("token:" + token);

			String tokenname = TokenUtils.getUserName(token);
			LOGGER.info("tokenname:" + tokenname);

			if (tokenname.equals(userid)) {
				
				
				cartService.removeAllCartInfo(userid);

				message.setMsg(0, "删除购物车成功", userid);
			} else {
				message.setMsg(-3, "Token错误，请重新登录", userid);
			}
		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
