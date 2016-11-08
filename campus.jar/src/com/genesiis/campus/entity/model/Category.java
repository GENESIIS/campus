package com.genesiis.campus.entity.model;

//20161026 PN c11-criteria-based-filter-search: INIT Category.java class
//20161028 PN c11-criteria-based-filter-search: added class attributes and data access methods.

import java.sql.Date;

public class Category {
	int code;
	String serialKey;
	String name;
	String description;
	String image;
	int isActive;
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

	public String getSerialKey() {
		return serialKey;
	}

	public void setSerialKey(String serialKey) {
		this.serialKey = serialKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
}