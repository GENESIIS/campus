package com.genesiis.campus.entity.model;

import java.util.Date;

//20170221 DJ c145-add-enhanced-programme created IntakeDTO.java

public class IntakeDTO {
	
	private String intakeName;
	private String description;
	private Date openDate;
	private Date commencementDate;
	private String fee;
	private int intakeStatus;
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
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
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
	public int getIntakeStatus() {
		return intakeStatus;
	}
	public void setIntakeStatus(int intakeStatus) {
		this.intakeStatus = intakeStatus;
	}
	
	

}
