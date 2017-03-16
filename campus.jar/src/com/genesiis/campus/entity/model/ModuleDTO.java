package com.genesiis.campus.entity.model;

//20170316 DJ c152-add-enhanced-programme-insertion- Initiate ModuleDTO.java

import java.util.Date;

public class ModuleDTO {
	private int moduleCode;
	private String moduleName;	
	private String description;
	private int semesterCode;
	private String internalCodeOfModule;
	private int creditValue;	
	private int compulsoryStatus;
	private String tutoredBy;
	private int  isTutorRelated;
	private int isActive;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	public int getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(int moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSemesterCode() {
		return semesterCode;
	}
	public void setSemesterCode(int semesterCode) {
		this.semesterCode = semesterCode;
	}
	public String getInternalCodeOfModule() {
		return internalCodeOfModule;
	}
	public void setInternalCodeOfModule(String internalCodeOfModule) {
		this.internalCodeOfModule = internalCodeOfModule;
	}
	public int getCreditValue() {
		return creditValue;
	}
	public void setCreditValue(int creditValue) {
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
	public int getIsTutorRelated() {
		return isTutorRelated;
	}
	public void setIsTutorRelated(int isTutorRelated) {
		this.isTutorRelated = isTutorRelated;
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

}
