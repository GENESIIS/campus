package com.genesiis.campus.entity.model;

import java.sql.Date;

//20161026 PN c11-criteria-based-filter-search: INIT Major.java class
//20161028 PN c11-criteria-based-filter-search: added class attributes and data access methods.
//20160102 PN CAM-116: added private access modifier to the fields.

public class Major {
	private int code;
	private String name;
	private String serialKey;
	private String description;
	private String isActive;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerialKey() {
		return serialKey;
	}
	public void setSerialKey(String serialKey) {
		this.serialKey = serialKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
