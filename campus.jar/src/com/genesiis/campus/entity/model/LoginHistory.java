package com.genesiis.campus.entity.model;
//20170316 AS CAM-23 c23-admin-login-logout-function-as LoginHistory pojo class created and attributes 
public class LoginHistory {

	private int code;

	private String lastLoggedInUserAgent;
	private String lastLoggedInSessionid;
	private String lastLoggedInDate;
	private String lastLoggedInTime;
	private String lastLoggedInIpAddress;
	private String lastLoggedOutDate;
	private String lastLoggedOutTime;
	private String lastLoginAuthenticatedBy;
	private String loginSource;
	private int admin;
	private int courseProvider;
	private int tutor;
	private boolean isActive;

	public String getLastLoggedInUserAgent() {
		return lastLoggedInUserAgent;
	}

	public void setLastLoggedInUserAgent(String lastLoggedInUserAgent) {
		this.lastLoggedInUserAgent = lastLoggedInUserAgent;
	}

	public String getLastLoggedInSessionid() {
		return lastLoggedInSessionid;
	}

	public void setLastLoggedInSessionid(String lastLoggedInSessionid) {
		this.lastLoggedInSessionid = lastLoggedInSessionid;
	}

	public String getLastLoggedInDate() {
		return lastLoggedInDate;
	}

	public void setLastLoggedInDate(String lastLoggedInDate) {
		this.lastLoggedInDate = lastLoggedInDate;
	}

	public String getLastLoggedInTime() {
		return lastLoggedInTime;
	}

	public void setLastLoggedInTime(String lastLoggedInTime) {
		this.lastLoggedInTime = lastLoggedInTime;
	}

	public String getLastLoggedInIpAddress() {
		return lastLoggedInIpAddress;
	}

	public void setLastLoggedInIpAddress(String lastLoggedInIpAddress) {
		this.lastLoggedInIpAddress = lastLoggedInIpAddress;
	}

	public String getLastLoggedOutDate() {
		return lastLoggedOutDate;
	}

	public void setLastLoggedOutDate(String lastLoggedOutDate) {
		this.lastLoggedOutDate = lastLoggedOutDate;
	}

	public String getLastLoggedOutTime() {
		return lastLoggedOutTime;
	}

	public void setLastLoggedOutTime(String lastLoggedOutTime) {
		this.lastLoggedOutTime = lastLoggedOutTime;
	}

	public String getLastLoginAuthenticatedBy() {
		return lastLoginAuthenticatedBy;
	}

	public void setLastLoginAuthenticatedBy(String lastLoginAuthenticatedBy) {
		this.lastLoginAuthenticatedBy = lastLoginAuthenticatedBy;
	}

	public String getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getCourseProvider() {
		return courseProvider;
	}

	public void setCourseProvider(int courseProvider) {
		this.courseProvider = courseProvider;
	}

	public int getTutor() {
		return tutor;
	}

	public void setTutor(int tutor) {
		this.tutor = tutor;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
