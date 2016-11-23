package com.genesiis.campus.entity.model;

//20161123 JH c39-add-course-provider CourseProviderAccount.java class created
//20161123 JH c39-add-course-provider entity class coding : create getters and setters

import java.util.Date;

public class CourseProviderAccount {
	
	private int code;
	private String name;
	private String email;
	private String username;
	private String password;
	private String description;
	private String crtBy;
	private String mobBy;
	
	private int isActive;
	private int courseProvider;
	private int userType;
	
	private Date crtOn;
	private Date modOn;

}
