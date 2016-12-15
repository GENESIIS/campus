package com.genesiis.campus.entity.model;

//20161215 PN CAM-28: INIT HigherEducation.java POJO class.

import java.sql.Date;

public class HigherEducation {
	private int code;
	private int student;
	private String institute;
	private String affiliatedInstitute;
	private int major;
	private int level;
	private int award;
	private int country;
	private int medium;
	private String studentId;
	private String result;
	private String description;
	private int isActive;
	private Date commencedOn;
	private Date CompletedOn;
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
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getAffiliatedInstitute() {
		return affiliatedInstitute;
	}
	public void setAffiliatedInstitute(String affiliatedInstitute) {
		this.affiliatedInstitute = affiliatedInstitute;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getAward() {
		return award;
	}
	public void setAward(int award) {
		this.award = award;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public Date getCommencedOn() {
		return commencedOn;
	}
	public void setCommencedOn(Date commencedOn) {
		this.commencedOn = commencedOn;
	}
	public Date getCompletedOn() {
		return CompletedOn;
	}
	public void setCompletedOn(Date completedOn) {
		CompletedOn = completedOn;
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
	public int getMedium() {
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	
}
