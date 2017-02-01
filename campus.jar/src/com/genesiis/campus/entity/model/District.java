package com.genesiis.campus.entity.model;

import java.sql.Date;

//20161026 PN c11-criteria-based-filter-search: INIT District.java class
//20161028 PN c11-criteria-based-filter-search: added class attributes and data access methods.
//20160102 PN CAM-116: added private access modifier to the fields.

public class District {
	private int code;
	private int province;
	private String name;
	private int sort;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	public Date getCrtOn() {
		return crtOn;
	}
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public Date getModOn() {
		return modOn;
	}
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	public String getModBy() {
		return modBy;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
}
