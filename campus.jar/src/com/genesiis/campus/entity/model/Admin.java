package com.genesiis.campus.entity.model;
//20170315 AS CAM-23 c23-admin-login-logout-function-as Admin pojo class created and attributes  
//20170316 AS CAM-23 c23-admin-login-logout-function-as LoginHistory pojo extended
public class Admin extends LoginHistory {

	private int code;
	private String name;
	private String userKey;
	private String username;
	private String email;
	private String password;
	private boolean isActive;
	private String userType;
	private String discription;
	

	private java.sql.Date crtOn;
	private String crtBy;
	private java.sql.Date modOn;
	private String modBy;
	private boolean valid;
	private boolean remember;
	
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
