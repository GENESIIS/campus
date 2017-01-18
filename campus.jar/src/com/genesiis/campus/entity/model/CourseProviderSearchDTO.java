package com.genesiis.campus.entity.model;

//DJ 20161117 c17-provider-criteria-based-filter-search created CourseProviderSearchDTO.java
//DJ 20161123 c17-provider-criteria-based-filter-search add isGetAllCPTypes,isGetAllMajors,isGetAllLevels,cpTypeList attributes
//DJ 20161124 c17-provider-criteria-based-filter-search add majorList,levelList attributes
//DJ 20170116 c123-general-filter-search-course-provider-MP-dj Deprecated courseProviderCode entity.

import java.util.List;

/**
* The class {@code CourseProviderSearchDTO} is a form of SearchDTO created for the purpose of transfer input parameters to DAO level. 
* @author dumani DJ
*
*/
public class CourseProviderSearchDTO {
	@Deprecated
	private int courseProviderCode;
	private int category;
	private int courseProviderStatus;	
	private int district;
	private List<Integer> categoryList;
	private List<Integer> cpTypeList;
	private List<Integer> majorList;
	private List<Integer> levelList;
	private List<Integer> courseProviderCodeList;
	@Deprecated 
	public int getCourseProviderCode() {
		return courseProviderCode;
	}
	@Deprecated
	public void setCourseProviderCode(int courseProviderCode) {
		this.courseProviderCode = courseProviderCode;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}	
	public int getCourseProviderStatus() {
		return courseProviderStatus;
	}
	public void setCourseProviderStatus(int courseProviderStatus) {
		this.courseProviderStatus = courseProviderStatus;
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
	public List<Integer> getCourseProviderCodeList() {
		return courseProviderCodeList;
	}
	public void setCourseProviderCodeList(List<Integer> courseProviderCodeList) {
		this.courseProviderCodeList = courseProviderCodeList;
	}	
}
