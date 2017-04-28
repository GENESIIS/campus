package com.genesiis.campus.entity.model;

//20170428 DJ c145-add-enhanced-programme-MP-dj INT-SemesterDTO.java

import java.util.Date;

public class SemesterDTO {
	private int code;
	private String name;	
	private String description;
	private int yearNum;
	private int semesterNum;
	private int isActive;
	private int programmeCode;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getYearNum() {
		return yearNum;
	}
	public void setYearNum(int yearNum) {
		this.yearNum = yearNum;
	}
	public int getSemesterNum() {
		return semesterNum;
	}
	public void setSemesterNum(int semesterNum) {
		this.semesterNum = semesterNum;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getProgrammeCode() {
		return programmeCode;
	}
	public void setProgrammeCode(int programmeCode) {
		this.programmeCode = programmeCode;
	}
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
}
