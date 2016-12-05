package com.genesiis.campus.entity.model;

import java.sql.Date;

public class Programme {
	private int code;
	private String name;
	private int courseProvider;
	private Date displayStartDate;
	private Date expiryDate;
	private int programmeStatus;
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
	public int getCourseProvider() {
		return courseProvider;
	}
	public void setCourseProvider(int courseProvider) {
		this.courseProvider = courseProvider;
	}
	public Date getDisplayStartDate() {
		return displayStartDate;
	}
	public void setDisplayStartDate(Date displayStartDate) {
		this.displayStartDate = displayStartDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getProgrammeStatus() {
		return programmeStatus;
	}
	public void setProgrammeStatus(int programmeStatus) {
		this.programmeStatus = programmeStatus;
	}
	
	
	

}
