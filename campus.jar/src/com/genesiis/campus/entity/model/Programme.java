package com.genesiis.campus.entity.model;

//20161026 PN c11-criteria-based-filter-search: INIT Program.java class
//20161024 CM  c13 display course details INIT Prgramme.java.
//20161024 CM  c13 display course details Create variables, getters and setters.
//20160102 PN CAM-116: added private access modifier to the fields.

import java.sql.Date;

public class Programme {

	private int code;
	private String name;
	private String email;
	private String image;
	private String description;
	private String duration;
	private String entryRequiremtns;
	private String counselerName;
	private String counselerPhone;
	private Date displayDatrtDate;
	private Date expirationDate;
	private int programmeStatus;
	private int courseProvider;
	private int major;
	private int category;
	private int level;
	private int town;
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
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
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
		return displayDatrtDate;
	}
	/**
	 * @param displayDatrtDate the displayDatrtDate to set
	 */
	public void setDisplayDatrtDate(Date displayDatrtDate) {
		this.displayDatrtDate = displayDatrtDate;
	}
	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}
	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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
	
	
}
