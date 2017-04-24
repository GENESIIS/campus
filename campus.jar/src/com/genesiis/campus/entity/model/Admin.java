package com.genesiis.campus.entity.model;

import java.sql.Timestamp;

//20170315 AS CAM-23-admin-login-logout-function-as Admin pojo class created and attributes  
//20170316 AS CAM-23-admin-login-logout-function-as LoginHistory pojo extended
//20170321 AS CAM-23-admin-login-logout-function-as code and isActive Attributes changed to adminCode and adminIsActive
//20170404 AS CAM-23-admin-login-logout-function-as - userTypeString attribute added
//20170424 AS CAM-154-admin-privilege-handling-as - attempts,lastAttempt attribute added

public class Admin extends LoginHistory {

	private int adminCode;
	private String name;
	private String userKey;
	private String username;
	private String email;
	private String password;
	private boolean adminisActive;
	private String userType;
	private String userTypeString;
	private String discription;
	

	private java.sql.Date crtOn;
	private String crtBy;
	private java.sql.Date modOn;
	private String modBy;
	private boolean valid;
	private boolean remember;
	private int attempts;
	private Timestamp lastAttempt;
	
	
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public Timestamp getLastAttempt() {
		return lastAttempt;
	}
	public void setLastAttempt(Timestamp lastAttempt) {
		this.lastAttempt = lastAttempt;
	}
	
	public String getUserTypeString() {
		return userTypeString;
	}
	public void setUserTypeString(String userTypeString) {
		this.userTypeString = userTypeString;
	}
	
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	public int getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(int adminCode) {
		this.adminCode = adminCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdminisActive() {
		return adminisActive;
	}
	public void setAdminisActive(boolean adminisActive) {
		this.adminisActive = adminisActive;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
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
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	
	
	
}
