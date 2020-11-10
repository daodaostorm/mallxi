package com.mallxi.beandata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */

@Entity
public class Essayinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
		
	private String proid;
	private String name;
	private String subtitle;
	private String author;
	private String detailpic1;
	private String detailpic2;
	private String detailpic3;
	private String detailpic4;
	private String detailtext1;
	private String detailtext2;
	private String detailtext3;
	private String detailtext4;
	private Long status;
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
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getDetailtext1() {
		return detailtext1;
	}
	public void setDetailtext1(String detailtext1) {
		this.detailtext1 = detailtext1;
	}
	public String getDetailtext2() {
		return detailtext2;
	}
	public void setDetailtext2(String detailtext2) {
		this.detailtext2 = detailtext2;
	}
	public String getDetailtext3() {
		return detailtext3;
	}
	public void setDetailtext3(String detailtext3) {
		this.detailtext3 = detailtext3;
	}
	public String getDetailtext4() {
		return detailtext4;
	}
	public void setDetailtext4(String detailtext4) {
		this.detailtext4 = detailtext4;
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
	

}
