package com.genesiis.campus.entity.model;

//20170428 DJ c145-add-enhanced-programme-MP-dj INT-ModuleDTO.java

import java.util.Date;

public class ModuleDTO {
	private int code;
	private String name;
	private String description;
	private String internalCodeModule;
	private double creditValue;
	private int compulsoryStatus;
	private String tutoredBy;
	private int isActive;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	private int semester;
	private int tutorCode;
	
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
	public String getInternalCodeModule() {
		return internalCodeModule;
	}
	public void setInternalCodeModule(String internalCodeModule) {
		this.internalCodeModule = internalCodeModule;
	}
	public double getCreditValue() {
		return creditValue;
	}
	public void setCreditValue(double creditValue) {
		this.creditValue = creditValue;
	}
	public int getCompulsoryStatus() {
		return compulsoryStatus;
	}
	public void setCompulsoryStatus(int compulsoryStatus) {
		this.compulsoryStatus = compulsoryStatus;
	}
	public String getTutoredBy() {
		return tutoredBy;
	}
	public void setTutoredBy(String tutoredBy) {
		this.tutoredBy = tutoredBy;
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
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getTutor() {
		return tutorCode;
	}
	public void setTutor(int tutor) {
		this.tutorCode = tutor;
	}
	
	
}
