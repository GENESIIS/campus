package com.genesiis.campus.entity.model;
//DJ 20161111 c17-provider-criteria-based-filter-search-MP-dj created CourseProviderType.java

public class CourseProviderType {
	private int code;	
	private String name;
	private String description;
	private boolean isActive;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
