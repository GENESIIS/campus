package com.genesiis.campus.entity.model;
//DJ 20161117 c17-provider-criteria-based-filter-search created CourseProviderSearchDTO.java
//DJ 20161123 c17-provider-criteria-based-filter-search add isGetAllCPTypes,isGetAllMajors,isGetAllLevels,cpTypeList attributes

import java.util.List;



public class CourseProviderSearchDTO {
	
	private int courseProviderCode;
	private int category;
	private int level;
	private int major;
	private int courserProviderType;
	private int district;	
	private boolean isGetAll;
	private boolean isGetAllCPTypes;
	private boolean isGetAllMajors;
	private boolean isGetAllLevels;
	private List<Integer> cpTypeList;
	
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
	public boolean isGetAllCPTypes() {
		return isGetAllCPTypes;
	}
	public void setGetAllCPTypes(boolean isGetAllCPTypes) {
		this.isGetAllCPTypes = isGetAllCPTypes;
	}
	public boolean isGetAllMajors() {
		return isGetAllMajors;
	}
	public void setGetAllMajors(boolean isGetAllMajors) {
		this.isGetAllMajors = isGetAllMajors;
	}
	public boolean isGetAllLevels() {
		return isGetAllLevels;
	}
	public void setGetAllLevels(boolean isGetAllLevels) {
		this.isGetAllLevels = isGetAllLevels;
	}
	public List<Integer> getCpTypeList() {
		return cpTypeList;
	}
	public void setCpTypeList(List<Integer> cpTypeList) {
		this.cpTypeList = cpTypeList;
	}

}
