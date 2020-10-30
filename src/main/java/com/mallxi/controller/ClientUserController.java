package com.mallxi.controller;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mallxi.beandata.ClientUser;
import com.mallxi.message.ContentInfo;
import com.mallxi.message.Message;
import com.mallxi.service.ClientUserService;
import com.mallxi.utils.DateTimeUtils;
import com.mallxi.utils.TokenUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", name = "ClientUserAPI")
@Api(description = "ClientUserAPI")

public class ClientUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientUserController.class);
	private Message message = new Message();

	private ClientUserService userService;

	@Autowired
	public ClientUserController(ClientUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "clientuser/createuser", method = RequestMethod.POST)
	@ApiOperation(value = "获取登录接口", notes = "登录接口")
	public ResponseEntity<Message> clientUserCreate(@RequestBody Map params) {

		String strUserName = params.get("username").toString();
		// String strPass = params.get("password").toString();

		ClientUser user = new ClientUser();

		Collection<ClientUser> clientuser = userService.findByUserame(strUserName);
		if (clientuser != null && clientuser.size() >= 1) {
			message.setMsg(-1, "用户名已存在", "");
		} else {

			user.setUsername(strUserName);
			if (params.get("password") != null) {
				user.setPassword(params.get("password").toString());
			}
			if (params.get("phone") != null) {
				user.setPhone(params.get("phone").toString());
			}
			if (params.get("question") != null) {
				user.setQuestion(params.get("question").toString());
			}
			if (params.get("answer") != null) {
				user.setAnswer(params.get("answer").toString());
			}

			user.setLastlogintime("");
			user.setCreatetime(DateTimeUtils.getNowDateTime());

			user.setUserid(DateTimeUtils.createNowTimeId());
			user.setUpdatetime(DateTimeUtils.getNowDateTime());
			userService.UpdateInfo(user);
			message.setMsg(0, "创建成功", "");
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "clientuser/login", method = RequestMethod.POST)
	@ApiOperation(value = "获取登录接口", notes = "登录接口")
	public ResponseEntity<Message> clientUserLogin(@RequestBody Map params) {

		String strUserName = params.get("username").toString();
		String strPass = params.get("password").toString();

		ContentInfo content = new ContentInfo();
		content.setUser(strUserName);
		content.setToken("");

		Collection<ClientUser> clientuser = userService.findByUserame(strUserName);
		if (clientuser != null && clientuser.size() >= 1) {
			ClientUser user = (ClientUser) clientuser.toArray()[0];
			if (strPass.equals(user.getPassword())) {
				String strToken = TokenUtils.getToken(user.getUserid(), user.getPassword());

				content.setUserid(user.getUserid());
				content.setToken(strToken);
				message.setMsg(0, "登录成功", content);
				user.setLastlogintime(DateTimeUtils.getNowDateTime());
				userService.UpdateInfo(user);
			} else {
				message.setMsg(-1, "登录失败,用户名密码不匹配", content);
			}
		} else {
			message.setMsg(-1, "登录失败,用户不存在", content);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "clientuser/editpassword", method = RequestMethod.POST)
	@ApiOperation(value = "获取登录接口", notes = "登录接口")
	public ResponseEntity<Message> clientUserEditPassword(@RequestHeader Map heads, @RequestBody Map params) {

		String strUserId = params.get("userid").toString();
		String strPass = params.get("password").toString();

		/*
		 * ContentInfo content = new ContentInfo(); content.setUser(strUserName);
		 * content.setToken("");
		 */

		boolean isContainTokey = heads.containsKey("token");

		if (isContainTokey) {
			String token = heads.get("token").toString();
			LOGGER.info("token:" + token);

			String tokenname = TokenUtils.getUserName(token);
			LOGGER.info("tokenname:" + tokenname);

			if (tokenname.equals(strUserId)) {
				Collection<ClientUser> clientuser = userService.findByUserid(strUserId);
				if (clientuser != null && clientuser.size() >= 1) {
					ClientUser user = (ClientUser) clientuser.toArray()[0];

					if (strPass.length() < 8) {
						message.setMsg(-2, "密码太短", strUserId);
					} else {
						user.setPassword(strPass);
						user.setLastlogintime(DateTimeUtils.getNowDateTime());
						userService.UpdateInfo(user);
						message.setMsg(0, "修改成功", strUserId);
					}
				} else {
					message.setMsg(-1, "修改失败,用户不存在", strUserId);
				}
			} else {
				message.setMsg(-3, "Token过期，请重新登录", strUserId);
			}
		} else {
			message.setMsg(-3, "Token过期，请重新登录", strUserId);
		}

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
