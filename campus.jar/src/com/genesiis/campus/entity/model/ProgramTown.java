package com.genesiis.campus.entity.model;

import java.sql.Date;

//20161026 PN c11-criteria-based-filter-search: INIT ProgramTown.java class
//20161028 PN c11-criteria-based-filter-search: added class attributes and data access methods.

public class ProgramTown {
	int code;
	int IsActive;
	int program;
	int town;
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
	public int getIsActive() {
		return IsActive;
	}
	public void setIsActive(int isActive) {
		IsActive = isActive;
	}
	public int getProgram() {
		return program;
	}
	public void setProgram(int program) {
		this.program = program;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	
	
}
