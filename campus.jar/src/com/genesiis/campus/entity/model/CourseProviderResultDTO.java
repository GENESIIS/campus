package com.genesiis.campus.entity.model;

//DJ 20161124 c17-provider-criteria-based-filter-search created CourseProviderResultDTO.java

public class CourseProviderResultDTO {
	
	private int courseProviderCode;
	private int category;
	private int level;
	private int major;
	private int courserProviderType;
	public int getCourseProviderCode() {
		return courseProviderCode;
	}
	public void setCourseProviderCode(int courseProviderCode) {
		this.courseProviderCode = courseProviderCode;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getCourserProviderType() {
		return courserProviderType;
	}
	public void setCourserProviderType(int courserProviderType) {
		this.courserProviderType = courserProviderType;
	}

}
