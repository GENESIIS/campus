package com.genesiis.campus.entity.model;

import java.util.Date;

//20170221 DJ c145-add-enhanced-programme created IntakeDTO.java
//20170502 DJ c145-add-enhanced-programme modify IntakeDTO.java

public class IntakeDTO {
	
	private int code;
	private String intakeName;
	private String description;
	private Date openingDate;
	private Date closingDate;
	private Date commencementDate;
	private String fee;
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
	public String getIntakeName() {
		return intakeName;
	}
	public void setIntakeName(String intakeName) {
		this.intakeName = intakeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	public Date getCommencementDate() {
		return commencementDate;
	}
	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
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
