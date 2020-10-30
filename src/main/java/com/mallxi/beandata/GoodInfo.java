package com.mallxi.beandata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */

@Entity
public class GoodInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
		
	private String proid;
	private String cateid;
	private String name;
	private String subtitle;
	private String mainimage;
	private String detailpic1;
	private String detailpic2;
	private String detailpic3;
	private String detailpic4;
	private String detail;
	private float price;
	private float orginprice;
	private Long stock;
	private Long status;
	private String recommend;
	private String createtime;
	private String updatetime;
	private Long updateby;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	public String getCateid() {
		return cateid;
	}
	public void setCateid(String cateid) {
		this.cateid = cateid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getMainimage() {
		return mainimage;
	}
	public void setMainimage(String mainimage) {
		this.mainimage = mainimage;
	}
	public String getDetailpic1() {
		return detailpic1;
	}
	public void setDetailpic1(String detailpic1) {
		this.detailpic1 = detailpic1;
	}
	public String getDetailpic2() {
		return detailpic2;
	}
	public void setDetailpic2(String detailpic2) {
		this.detailpic2 = detailpic2;
	}
	public String getDetailpic3() {
		return detailpic3;
	}
	public void setDetailpic3(String detailpic3) {
		this.detailpic3 = detailpic3;
	}
	
	public String getDetailpic4() {
		return detailpic4;
	}
	public void setDetailpic4(String detailpic4) {
		this.detailpic4 = detailpic4;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getOrginprice() {
		return orginprice;
	}
	public void setOrginprice(float orginprice) {
		this.orginprice = orginprice;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
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
	public Long getUpdateby() {
		return updateby;
	}
	public void setUpdateby(Long updateby) {
		this.updateby = updateby;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	

}
