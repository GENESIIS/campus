package com.genesiis.campus.entity.model;
//DJ 20161117 c17-provider-criteria-based-filter-search created CourseProviderSearchDTO.java

public class CourseProviderSearchDTO {
	
	private int courseProviderCode;
	private int category;
	private int level;
	private int major;
	private int courserProviderType;
	private int district;	
	private boolean isGetAll;
	
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
	public int getDistrict() {
		return district;
	}
	public void setDistrict(int district) {
		this.district = district;
	}
	public boolean isGetAll() {
		return isGetAll;
	}
	public void setGetAll(boolean isGetAll) {
		this.isGetAll = isGetAll;
	}
	

}
