package com.genesiis.campus.entity.model;

import java.util.Date;



//20170208 DJ c138-add-basic-programme-MP-dj Initiate ProgrammeDTO.java



public class ProgrammeDTO {
	
	int code;
	String name;
	String email;
	String image;
	String description;
	Float duration;
	String entryRequiremtns;
	String counselerName;
	String counselerPhone;
	Date displayDatrtDate;
	Date expirationDate;
	int programmeStatus;
	int courseProvider;
	int major;
	int category;
	int level;
	int classType;
	int town;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	public Float getDuration() {
		return duration;
	}
	public void setDuration(Float duration) {
		this.duration = duration;
	}
	public String getEntryRequiremtns() {
		return entryRequiremtns;
	}
	public void setEntryRequiremtns(String entryRequiremtns) {
		this.entryRequiremtns = entryRequiremtns;
	}
	public String getCounselerName() {
		return counselerName;
	}
	public void setCounselerName(String counselerName) {
		this.counselerName = counselerName;
	}
	public String getCounselerPhone() {
		return counselerPhone;
	}
	public void setCounselerPhone(String counselerPhone) {
		this.counselerPhone = counselerPhone;
	}
	public Date getDisplayDatrtDate() {
		return displayDatrtDate;
	}
	public void setDisplayDatrtDate(Date displayDatrtDate) {
		this.displayDatrtDate = displayDatrtDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getProgrammeStatus() {
		return programmeStatus;
	}
	public void setProgrammeStatus(int programmeStatus) {
		this.programmeStatus = programmeStatus;
	}
	public int getCourseProvider() {
		return courseProvider;
	}
	public void setCourseProvider(int courseProvider) {
		this.courseProvider = courseProvider;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getLevel() {
		return level;
	}	
	public int getClassType() {
		return classType;
	}
	public void setClassType(int classType) {
		this.classType = classType;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
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
