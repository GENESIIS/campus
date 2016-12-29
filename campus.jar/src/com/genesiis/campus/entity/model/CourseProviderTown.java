package com.genesiis.campus.entity.model;

import java.util.Date;

//20161229 JH c39-add-course-provider CourseProviderTown.java class created

public class CourseProviderTown {

	private int code;
	private int courseProvider;
	private int town;
	
	private String crtBy;
	private String modBy;
	
	private boolean isActive;
	
	private Date crtOn;
	private Date modOn;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCourseProvider() {
		return courseProvider;
	}
	public void setCourseProvider(int courseProvider) {
		this.courseProvider = courseProvider;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public String getModBy() {
		return modBy;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Date getCrtOn() {
		return crtOn;
	}
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	public Date getModOn() {
		return modOn;
	}
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	
}
