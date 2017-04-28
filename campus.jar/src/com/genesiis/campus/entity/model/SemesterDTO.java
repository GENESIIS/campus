package com.genesiis.campus.entity.model;

//20170428 DJ c145-add-enhanced-programme-MP-dj INT-SemesterDTO.java

public class SemesterDTO {
	int code;
	String name;	
	String description;
	int yearNum;
	int semesterNum;
	int isActive;
	
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
}
