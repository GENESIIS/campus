package com.genesiis.campus.entity.model;

//DJ 20161026 c6-list-available-institutes-on-the-view created Institutes.java

public class CourseProvider {
	
	private int code;
	private int category;
	private String name;
	private String uniqueprefix;
	
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
	
	

}
