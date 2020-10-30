package com.mallxi.beandata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 用户User 
 * 
 * @author angkor
 *
 */
@Entity
@Table(name = "clientuser", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class ClientUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userid;
	private String username;
	private String password;
	private String phone;
	private String question;
	private String answer;
	private int role;
	private int status;
	private String createtime;
	private String updatetime;
	private String lastlogintime;
	private String remark;
	

	public String getLastlogintime() {
		return lastlogintime;
	}




	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getUserid() {
		return userid;
	}




	public void setUserid(String userid) {
		this.userid = userid;
	}




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




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getQuestion() {
		return question;
	}




	public void setQuestion(String question) {
		this.question = question;
	}




	public String getAnswer() {
		return answer;
	}




	public void setAnswer(String answer) {
		this.answer = answer;
	}




	public int getRole() {
		return role;
	}




	public void setRole(int role) {
		this.role = role;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public String getCreatetime() {
		return createtime;
	}




	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}




	public String getUpdatetime() {
		return updatetime;
	}




	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	@Override
	public String toString() {
		return "ClientUser [id=" + id + ", userid=" + userid + ", password=" + password + ", username=" + username + "]";
	}

}
