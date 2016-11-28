package com.genesiis.campus.entity.model;

//20161123 JH c39-add-course-provider CourseProviderAccount.java class created
//20161123 JH c39-add-course-provider entity class coding : create getters and setters
//20161128 JH c39-add-course-provider change data type int into boolean for isActive 

import java.util.Date;

public class CourseProviderAccount {
	
	private int code;
	private String name;
	private String email;
	private String username;
	private String password;
	private String description;
	private String crtBy;
	private String mobBy;
	
	private boolean isActive;
	private int courseProvider;
	private int userType;
	
	private Date crtOn;
	private Date modOn;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the mobBy
	 */
	public String getMobBy() {
		return mobBy;
	}
	/**
	 * @param mobBy the mobBy to set
	 */
	public void setMobBy(String mobBy) {
		this.mobBy = mobBy;
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
	 * @return the userType
	 */
	public int getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(int userType) {
		this.userType = userType;
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
	
	

}
