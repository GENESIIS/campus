package com.genesiis.campus.entity.model;

import java.sql.Date;

//20161027 CM c9-make-inquiry-for-institute INIT InstituteInquiry.java

public class InstituteInquiry {

	private int code;
	private String studentName;
	private String studentEmail;
	private String telephoneCountryCode;
	private String telephoneAreaCode;
	private String telNo;
	private String inquiryTitle;
	private String inquiryText;
	private Date inquiryDate;
	private Date inquiryTime;
	private int isActive;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	int student;
	int courseProvider;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getTelephoneCountryCode() {
		return telephoneCountryCode;
	}
	public void setTelephoneCountryCode(String telephoneCountryCode) {
		this.telephoneCountryCode = telephoneCountryCode;
	}
	public String getTelephoneAreaCode() {
		return telephoneAreaCode;
	}
	public void setTelephoneAreaCode(String telephoneAreaCode) {
		this.telephoneAreaCode = telephoneAreaCode;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getInquiryTitle() {
		return inquiryTitle;
	}
	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}
	public String getInquiryText() {
		return inquiryText;
	}
	public void setInquiryText(String inquiryText) {
		this.inquiryText = inquiryText;
	}
	public Date getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}
	public Date getInquiryTime() {
		return inquiryTime;
	}
	public void setInquiryTime(Date inquiryTime) {
		this.inquiryTime = inquiryTime;
	}
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public int getCourseProvider() {
		return courseProvider;
	}
	public void setCourseProvider(int courseProvider) {
		this.courseProvider = courseProvider;
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
