package com.genesiis.campus.entity.model;

//DJ 20161026 c6-list-available-institutes-on-the-view created Institutes.java
//DJ 20161030 c6-list-available-institutes-on-the-view  identified get all institutes 

public class CourseProvider {
	
	private int code;
	private int category;
	private String name;
	private String uniqueprefix;
	private int courseProviderType;
	private int courseProviderStatus;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUniqueprefix() {
		return uniqueprefix;
	}
	public void setUniqueprefix(String uniqueprefix) {
		this.uniqueprefix = uniqueprefix;
	}	
	public int getCourseProviderType() {
		return courseProviderType;
	}
	public void setCourseProviderType(int courseProviderType) {
		this.courseProviderType = courseProviderType;
	}
	public int getCourseProviderStatus() {
		return courseProviderStatus;
	}
	public void setCourseProviderStatus(int courseProviderStatus) {
		this.courseProviderStatus = courseProviderStatus;
	}	
	
}
