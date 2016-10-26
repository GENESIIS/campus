package com.genesiis.campus.entity.model;

//20161025 JH c7-list-higher-education-courses entity class Programme.java created 
//20161025 JH c7-list-higher-education-courses create getters and setter methods
//20161025 JH c7-list-higher-education-courses add new fields 
//20161026 JH c7-list-higher-education-courses setExpiryDate method modified

import java.sql.Date;

public class Programme {

	
	private int code;
	private String name;
	private String email;
	private String image;
	private String description;
	private double duration;
	private String entryRequiremtns;
	private String counselerName;
	private String counselerPhone;
	private Date displayStartDate;
	private Date expiryDate;
	private int programmeStatus;
	private int courseProvider;
	private int major;
	private int category;
	private int level;
	private int town;
	private boolean isActive;
	private Date crtOn;
	private String crtBy;
	private Date modOn;
	private String modBy;
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the duration
	 */
	public double getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	/**
	 * @return the entryRequiremtns
	 */
	public String getEntryRequiremtns() {
		return entryRequiremtns;
	}
	/**
	 * @param entryRequiremtns the entryRequiremtns to set
	 */
	public void setEntryRequiremtns(String entryRequiremtns) {
		this.entryRequiremtns = entryRequiremtns;
	}
	/**
	 * @return the counselerName
	 */
	public String getCounselerName() {
		return counselerName;
	}
	/**
	 * @param counselerName the counselerName to set
	 */
	public void setCounselerName(String counselerName) {
		this.counselerName = counselerName;
	}
	/**
	 * @return the counselerPhone
	 */
	public String getCounselerPhone() {
		return counselerPhone;
	}
	/**
	 * @param counselerPhone the counselerPhone to set
	 */
	public void setCounselerPhone(String counselerPhone) {
		this.counselerPhone = counselerPhone;
	}
	/**
	 * @return the displayDatrtDate
	 */
	public Date getDisplayDatrtDate() {
		return expiryDate;
	}
	/**
	 * @param displayDatrtDate the displayDatrtDate to set
	 */
	public void setDisplayDatrtDate(Date displayDatrtDate) {
		this.expiryDate = displayDatrtDate;
	}
	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expiryDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expiryDate = expirationDate;
	}
	/**
	 * @return the programmeStatus
	 */
	public int getProgrammeStatus() {
		return programmeStatus;
	}
	/**
	 * @param programmeStatus the programmeStatus to set
	 */
	public void setProgrammeStatus(int programmeStatus) {
		this.programmeStatus = programmeStatus;
	}
	/**
	 * @return the courseProvider
	 */
	public int getCourseProvider() {
		return courseProvider;
	}
	/**
	 * @param courseProvider the courseProvider to set
	 */
	public void setCourseProvider(int courseProvider) {
		this.courseProvider = courseProvider;
	}
	/**
	 * @return the major
	 */
	public int getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(int major) {
		this.major = major;
	}
	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the town
	 */
	public int getTown() {
		return town;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(int town) {
		this.town = town;
	}
	/**
	 * @return the displayStartDate
	 */
	public Date getDisplayStartDate() {
		return displayStartDate;
	}
	/**
	 * @param displayStartDate the displayStartDate to set
	 */
	public void setDisplayStartDate(Date displayStartDate) {
		this.displayStartDate = displayStartDate;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param date the expiryDate to set
	 */
	public void setExpiryDate(java.util.Date date) {
		this.expiryDate = (Date) date;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the crtOn
	 */
	public Date getCrtOn() {
		return crtOn;
	}
	/**
	 * @param crtOn the crtOn to set
	 */
	public void setCrtOn(Date crtOn) {
		this.crtOn = crtOn;
	}
	/**
	 * @return the crtBy
	 */
	public String getCrtBy() {
		return crtBy;
	}
	/**
	 * @param crtBy the crtBy to set
	 */
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}
	/**
	 * @return the modOn
	 */
	public Date getModOn() {
		return modOn;
	}
	/**
	 * @param modOn the modOn to set
	 */
	public void setModOn(Date modOn) {
		this.modOn = modOn;
	}
	/**
	 * @return the modBy
	 */
	public String getModBy() {
		return modBy;
	}
	/**
	 * @param modBy the modBy to set
	 */
	public void setModBy(String modBy) {
		this.modBy = modBy;
	}
	
}
