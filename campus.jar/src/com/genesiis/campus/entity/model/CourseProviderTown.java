package com.genesiis.campus.entity.model;

//20161229 JH c39-add-course-provider CourseProviderTown.java class created
//20170102 JH c39-add-course-provider data type string to long for town attribute
//20170103 JH c39-add-course-provider added new attributes for address field 
//20170126 JH c39-add-course-provider added new attribute contact number

import java.util.Date;

public class CourseProviderTown {

	private int code;
	private int courseProvider;
	private Long town;
	
	private String address1;
	private String address2;
	private String address3;
	private String crtBy;
	private String modBy;
	private String contactNumber;
	
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
	public Long getTown() {
		return town;
	}
	public void setTown(Long town) {
		this.town = town;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
