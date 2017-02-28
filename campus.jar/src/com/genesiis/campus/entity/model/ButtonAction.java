package com.genesiis.campus.entity.model;
//20161209 AS C19-student-login-without-using-third-party-application-test-as  ButtonAction created.
public class ButtonAction {
	int code;
	private String description;
	private String action;
	private String isActive;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
