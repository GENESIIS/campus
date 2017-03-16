package com.genesiis.campus.entity.model;

//20170316 DJ c152-add-enhanced-programme-insertion- Initiate SemesterDTO.java

import java.util.ArrayList;
import java.util.Date;

public class SemesterDTO {
	private int semesterCode;
	private String semesterName;	
	private String description;
	private int yearNumber;
	private int semesterNumber;
	private int isActive;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	private ArrayList<ModuleDTO> moduleList;
	
	
	public int getSemesterCode() {
		return semesterCode;
	}
	public void setSemesterCode(int semesterCode) {
		this.semesterCode = semesterCode;
	}
	public String getSemesterName() {
		return semesterName;
	}
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getYearNumber() {
		return yearNumber;
	}
	public void setYearNumber(int yearNumber) {
		this.yearNumber = yearNumber;
	}
	public int getSemesterNumber() {
		return semesterNumber;
	}
	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	public ArrayList<ModuleDTO> getModuleList() {
		return moduleList;
	}
	public void setModuleList(ArrayList<ModuleDTO> moduleList) {
		this.moduleList = moduleList;
	}
	
}
