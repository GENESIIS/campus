package com.genesiis.campus.entity.model;
//20161128 PN c26-add-student-details: INIT ProfessionalExperience.java pojo class.

import java.sql.Date;

public class ProfessionalExperience {
	private int code;
	private int student;
	private int industry;
	private int jobCategoty;
	private String designation;
	private String organization;
	private String description;
	private Date commencedOn;
	private Date completionOn;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCommencedOn() {
		return commencedOn;
	}
	public void setCommencedOn(Date commencedOn) {
		this.commencedOn = commencedOn;
	}
	public Date getCompletionOn() {
		return completionOn;
	}
	public void setCompletionOn(Date completionOn) {
		this.completionOn = completionOn;
	}
	
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
	public int getIndustry() {
		return industry;
	}
	public void setIndustry(int industry) {
		this.industry = industry;
	}
	public int getJobCategoty() {
		return jobCategoty;
	}
	public void setJobCategoty(int jobCategoty) {
		this.jobCategoty = jobCategoty;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
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
