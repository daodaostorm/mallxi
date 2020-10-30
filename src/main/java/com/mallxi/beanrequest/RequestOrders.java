package com.mallxi.beanrequest;


/**
 *
 */

public class RequestOrders {

	
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
	
		
	
}
