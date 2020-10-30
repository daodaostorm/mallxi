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
public class BannerInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private Long updateby;
	private String proid;
	private String picUrl;
	private String createtime;
	private Long status;
	private String updatetime;
	private String title;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getUpdateby() {
		return updateby;
	}


	public void setUpdateby(Long updateby) {
		this.updateby = updateby;
	}


	public String getProid() {
		return proid;
	}


	public void setProid(String proid) {
		this.proid = proid;
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public String getCreatetime() {
		return createtime;
	}


	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}


	public Long getStatus() {
		return status;
	}


	public void setStatus(Long status) {
		this.status = status;
	}


	public String getUpdatetime() {
		return updatetime;
	}


	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void createBannerInfo(String title, String picUrl) {
		this.title = title;
		this.picUrl = picUrl;
	}

}
