package com.genesiis.campus.entity.model;

//20161025 JH c7-list-higher-education-courses entity class Programme.java created 
//20161025 JH c7-list-higher-education-courses create getters and setter methods
//20161025 JH c7-list-higher-education-courses add new fields 
//20161026 JH c7-list-higher-education-courses setExpiryDate method modified

public class Programme {

	private int code;
	private String name;
	private String email;
	private String image;
	private String description;
	private String duration;
	private String entryRequirements;
	private String counselerName;
	private String counselerPhone;
	private java.sql.Date displayStartDate;
	private java.sql.Date expiryDate;
	private int programmeStatus;
	private int courseProvider;
	private int major;
	private int category;
	private int level;
	private int town;
	private boolean isActive;
	private java.sql.Date crtOn;
	private String crtBy;
	private java.sql.Date modOn;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getEntryRequirements() {
		return entryRequirements;
	}
	public void setEntryRequiremtns(String entryRequiremtns) {
		this.entryRequirements = entryRequiremtns;
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
	public java.sql.Date getDisplayStartDate() {
		return displayStartDate;
	}
	public void setDisplayStartDate(java.sql.Date displayStartDate) {
		this.displayStartDate = displayStartDate;
	}
	public java.sql.Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(java.sql.Date expiryDate) {
		this.expiryDate = expiryDate;
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
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTown() {
		return town;
	}
	public void setTown(int town) {
		this.town = town;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public java.sql.Date getCrtOn() {
		return crtOn;
	}
	public void setCrtOn(java.sql.Date crtOn) {
		this.crtOn = crtOn;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	public java.sql.Date getModOn() {
		return modOn;
	}
	public void setModOn(java.sql.Date modOn) {
		this.modOn = modOn;
	}
	public String getModBy() {
		return modBy;
	}
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	
	
}
