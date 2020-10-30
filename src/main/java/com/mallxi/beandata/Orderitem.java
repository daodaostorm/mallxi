package com.mallxi.beandata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */

@Entity
public class Orderitem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String orderid;
	private String userid;
	private String shoppingid;
	private String label;
	private String receivername;
	private String receiverphone;
	private String receiverprovince;
	private String receivercity;
	private String receiverdistrict;
	private String receiveraddress;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	public String getReceiverphone() {
		return receiverphone;
	}
	public void setReceiverphone(String receiverphone) {
		this.receiverphone = receiverphone;
	}
	public String getReceiverprovince() {
		return receiverprovince;
	}
	public void setReceiverprovince(String receiverprovince) {
		this.receiverprovince = receiverprovince;
	}
	public String getReceivercity() {
		return receivercity;
	}
	public void setReceivercity(String receivercity) {
		this.receivercity = receivercity;
	}
	public String getReceiverdistrict() {
		return receiverdistrict;
	}
	public void setReceiverdistrict(String receiverdistrict) {
		this.receiverdistrict = receiverdistrict;
	}
	public String getReceiveraddress() {
		return receiveraddress;
	}
	public void setReceiveraddress(String receiveraddress) {
		this.receiveraddress = receiveraddress;
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
	
	
	
}
