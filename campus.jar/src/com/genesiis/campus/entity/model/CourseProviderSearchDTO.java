package com.genesiis.campus.entity.model;
//DJ 20161117 c17-provider-criteria-based-filter-search created CourseProviderSearchDTO.java
//DJ 20161123 c17-provider-criteria-based-filter-search add isGetAllCPTypes,isGetAllMajors,isGetAllLevels,cpTypeList attributes
//DJ 20161124 c17-provider-criteria-based-filter-search add majorList,levelList attributes

import java.util.List;



public class CourseProviderSearchDTO {
	
	private int courseProviderCode;
	private int category;
	private int level;
	private int major;
	private int courserProviderType;
	private int district;
	private List<Integer> categoryList;
	private List<Integer> cpTypeList;
	private List<Integer> majorList;
	private List<Integer> levelList;
	
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
	
	public List<Integer> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Integer> categoryList) {
		this.categoryList = categoryList;
	}
	public List<Integer> getCpTypeList() {
		return cpTypeList;
	}
	public void setCpTypeList(List<Integer> cpTypeList) {
		this.cpTypeList = cpTypeList;
	}
	public List<Integer> getMajorList() {
		return majorList;
	}
	public void setMajorList(List<Integer> majorList) {
		this.majorList = majorList;
	}
	public List<Integer> getLevelList() {
		return levelList;
	}
	public void setLevelList(List<Integer> levelList) {
		this.levelList = levelList;
	}
}
