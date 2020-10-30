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

import com.mallxi.beandata.Address;
import com.mallxi.message.Message;
import com.mallxi.service.AddressService;
import com.mallxi.utils.DateTimeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/api/v1", name = "AddressAPI")
@Api(description = "AddressAPI")

public class AddressController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	private Message message = new Message();

	private AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping(value = "address/getAddressinfo")
	@ApiOperation(value = "获取地址", notes = "获取地址")
	public ResponseEntity<Message> getAllAddress(@RequestHeader Map heads, @RequestParam String userid) {

		if (checkToken(heads, userid)) {

			Collection<Address> address = addressService.findByUserid(userid);

			message.setMsg(0, "获取地址", address);

		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	
	@RequestMapping(value = "address/addAddressinfo", method = RequestMethod.POST)
	@ApiOperation(value = "添加地址", notes = "添加地址")
	public ResponseEntity<Message> addinfo(@RequestHeader Map heads, @RequestBody Map params) {

		String userid = params.get("userid").toString();
		
		if (checkToken(heads, userid)) {

			Address address = new Address();
			JSONObject obj = JSONObject.fromObject(params);
			address.setReceiveraddress(obj.getString("receiveraddress"));
			address.setLabel(obj.getString("label"));
			address.setReceivercity(obj.getString("receivercity"));
			address.setReceiverdistrict(obj.getString("receiverdistrict"));
			address.setReceivername(obj.getString("receivername"));
			address.setReceiverphone(obj.getString("receiverphone"));
			address.setReceiverprovince(obj.getString("receiverprovince"));
			address.setUserid(userid);
			address.setCreatetime(DateTimeUtils.getNowDateTime());
			address.setUpdatetime(DateTimeUtils.getNowDateTime());
			
			addressService.UpdateAddressInfo(address);

			message.setMsg(0, "添加地址成功", address);

		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "address/editAddressinfo", method = RequestMethod.POST)
	@ApiOperation(value = "添加地址", notes = "添加地址")
	public ResponseEntity<Message> editinfo(@RequestHeader Map heads, @RequestBody Map params) {

		String userid = params.get("userid").toString();
		
		if (checkToken(heads, userid)) {

			String strid = params.get("id").toString();
			//Address editaddress = new Address();
			Long id = Long.parseLong(strid);
			Address address = addressService.findByid(id);
			if (address == null) {
				address = new Address();
				address.setCreatetime(DateTimeUtils.getNowDateTime());
			}
			JSONObject obj = JSONObject.fromObject(params);
			address.setId(id);
			address.setReceiveraddress(obj.getString("receiveraddress"));
			address.setLabel(obj.getString("label"));
			address.setReceivercity(obj.getString("receivercity"));
			address.setReceiverdistrict(obj.getString("receiverdistrict"));
			address.setReceivername(obj.getString("receivername"));
			address.setReceiverphone(obj.getString("receiverphone"));
			address.setReceiverprovince(obj.getString("receiverprovince"));
			address.setUserid(userid);
			address.setUpdatetime(DateTimeUtils.getNowDateTime());
			addressService.UpdateAddressInfo(address);
			message.setMsg(0, "添加地址成功", address);

		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "address/removeAddressinfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除地址", notes = "删除地址")
	public ResponseEntity<Message> removeinfo(@RequestHeader Map heads, @RequestBody Map params) {

		String userid = params.get("userid").toString();
		long deleteid = Long.parseLong(params.get("id").toString());

		if (checkToken(heads, userid)) {

			addressService.removeAddressInfo(deleteid);

			message.setMsg(0, "删除地址成功", userid);
		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "address/removeAllAddress", method = RequestMethod.POST)
	@ApiOperation(value = "删除地址", notes = "删除地址")
	public ResponseEntity<Message> removeAllinfo(@RequestHeader Map heads, @RequestBody Map params) {

		String userid = params.get("userid").toString();

		if (checkToken(heads, userid)) {

			addressService.removeAllAddressInfo(userid);

			message.setMsg(0, "删除地址成功", userid);
		} else {
			message.setMsg(-3, "Token错误，请重新登录", userid);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
}
