package com.mallxi.beandata;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 创建用户字段
 * 
 *
 */
public class UserClientCreateForm {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	@NotEmpty
	private String vcode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	@Override
	public String toString() {
		return "UserCreateForm [username=" + username + ", password=" + password + "]";
	}

}
