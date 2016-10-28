package com.genesiis.campus.entity.model;

import java.sql.Date;

//20161027 AS C8-inquiry-form-for-course CmdSendCourseInquiry class created.
//20161027 AS C8-inquiry-form-for-course attributes modification.
public class StudentProgrammeInquiry {

	private int code;
	private String studentName;
	private String studentEmail;
	private String telephoneCountryCode;
	private String telephoneAreaCode;
	private String telephone;
	private String inquiryTitle;
	private String inquiry;
	private int student;
	private int programme;
	private String crtBy;
	private Date crtOn;
	private Date modOn;
	private String modBy;

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

	public int getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(int studentEmail) {
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getInquiryTitle() {
		return inquiryTitle;
	}

	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}

	public String getInquiry() {
		return inquiry;
	}

	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getCrtBy() {
		return crtBy;
	}

	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
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

	public String getModBy() {
		return modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

}
