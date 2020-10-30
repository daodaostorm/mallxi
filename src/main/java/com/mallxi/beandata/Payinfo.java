package com.mallxi.beandata;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 */

@Entity
public class Payinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String payid;
	private String orderid;
	private String userid;
	private Long payplatform;
	private String platformnumber = "";
	private String platformstatus = "";
	private int status = 0;
	private String createtime;
	private String updatetime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Long getPayplatform() {
		return payplatform;
	}
	public void setPayplatform(Long payplatform) {
		this.payplatform = payplatform;
	}
	public String getPlatformnumber() {
		return platformnumber;
	}
	public void setPlatformnumber(String platformnumber) {
		this.platformnumber = platformnumber;
	}
	public String getPlatformstatus() {
		return platformstatus;
	}
	public void setPlatformstatus(String platformstatus) {
		this.platformstatus = platformstatus;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
