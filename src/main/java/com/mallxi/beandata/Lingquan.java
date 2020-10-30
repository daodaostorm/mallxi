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
public class Lingquan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private Long man;
	private Long jian;
	private String leftpic;
	private String rightpic;
	private String dateupdate;
	private Long status;
	private String statusstr;
	private String name;
	
	private Long type;
	private String typestr;
	
	private Long updateby;
	
	public Long getUpdateby() {
		return updateby;
	}


	public void setUpdateby(Long updateby) {
		this.updateby = updateby;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getTypestr() {
		return typestr;
	}
	public void setTypestr(String typestr) {
		this.typestr = typestr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMan() {
		return man;
	}
	public void setMan(Long man) {
		this.man = man;
	}
	public Long getJian() {
		return jian;
	}
	public void setJian(Long jian) {
		this.jian = jian;
	}
	public String getLeftpic() {
		return leftpic;
	}
	public void setLeftpic(String leftpic) {
		this.leftpic = leftpic;
	}
	public String getRightpic() {
		return rightpic;
	}
	public void setRightpic(String rightpic) {
		this.rightpic = rightpic;
	}
	public String getDateupdate() {
		return dateupdate;
	}
	public void setDateupdate(String dateupdate) {
		this.dateupdate = dateupdate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getStatusstr() {
		return statusstr;
	}
	public void setStatusstr(String statusstr) {
		this.statusstr = statusstr;
	}
	

}
