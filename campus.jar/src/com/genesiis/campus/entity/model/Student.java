package com.genesiis.campus.entity.model;

import java.util.Date;

//DJ 20161228 c53-report-registered-students-MP-dj created Student.java

public class Student {
	
	private int code;
	private String firstName;
	private String LastName;	
	private int status;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
