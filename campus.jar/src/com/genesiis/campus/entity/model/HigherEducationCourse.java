package com.genesiis.campus.entity.model;

import java.sql.Date;

//20161025 JH c7-list-higher-education-courses entity class HigherEducationCourses.java created
//20161025 JH c7-list-higher-education-courses entity class coding 

public class HigherEducationCourse {

	private int code;
	private String name;
	private String description;
	private double duration;
	private String entryRequirements;
	private String counselorName;
	private String counselorPhone;
	private Date displayStartDate;
	private Date expireDate;
	private int programmeStatus;
	private int courseProvider;
	private int major;
	private int category;
	private int numberOfSemesters;
	private int level;
	private long town;
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
	 * @return the entryRequirements
	 */
	public String getEntryRequirements() {
		return entryRequirements;
	}
	/**
	 * @param entryRequirements the entryRequirements to set
	 */
	public void setEntryRequirements(String entryRequirements) {
		this.entryRequirements = entryRequirements;
	}
	/**
	 * @return the counselorName
	 */
	public String getCounselorName() {
		return counselorName;
	}
	/**
	 * @param counselorName the counselorName to set
	 */
	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	/**
	 * @return the counselorPhone
	 */
	public String getCounselorPhone() {
		return counselorPhone;
	}
	/**
	 * @param counselorPhone the counselorPhone to set
	 */
	public void setCounselorPhone(String counselorPhone) {
		this.counselorPhone = counselorPhone;
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
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	 * @return the numberOfSemesters
	 */
	public int getNumberOfSemesters() {
		return numberOfSemesters;
	}
	/**
	 * @param numberOfSemesters the numberOfSemesters to set
	 */
	public void setNumberOfSemesters(int numberOfSemesters) {
		this.numberOfSemesters = numberOfSemesters;
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
	public long getTown() {
		return town;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(long town) {
		this.town = town;
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
