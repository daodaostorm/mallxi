package com.mallxi.beandata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String orderid;
	private String userid;
	private String shoppingid = "";
	private String fulladdress = "";
	
	private String prodid;
	private String prodname;
	private String prodmainpic;
	private int prodnum;
	private float prodvalue;
	
	private float payment;
	private int paymenttype = -1;
	private int postage = 0;
	private int status = 10;
	private String payid = "";
	private String paymenttime = "";
	private String sendtime = "";
	private String endtime = "";
	private String closetime = "";
	private String createtime;
	private String updatetime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getShoppingid() {
		return shoppingid;
	}
	public void setShoppingid(String shoppingid) {
		this.shoppingid = shoppingid;
	}
	public float getPayment() {
		return payment;
	}
	public void setPayment(float payment) {
		this.payment = payment;
	}
	public int getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(int paymenttype) {
		this.paymenttype = paymenttype;
	}
	public int getPostage() {
		return postage;
	}
	public void setPostage(int postage) {
		this.postage = postage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPaymenttime() {
		return paymenttime;
	}
	public void setPaymenttime(String paymenttime) {
		this.paymenttime = paymenttime;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getClosetime() {
		return closetime;
	}
	public void setClosetime(String closetime) {
		this.closetime = closetime;
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
	public String getFulladdress() {
		return fulladdress;
	}
	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProdmainpic() {
		return prodmainpic;
	}
	public void setProdmainpic(String prodmainpic) {
		this.prodmainpic = prodmainpic;
	}
	public int getProdnum() {
		return prodnum;
	}
	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
	}
	public float getProdvalue() {
		return prodvalue;
	}
	public void setProdvalue(float prodvalue) {
		this.prodvalue = prodvalue;
	}
	public String getPayid() {
		return payid;
	}
	public void setPayid(String payid) {
		this.payid = payid;
	}
	
	
	
	
}
