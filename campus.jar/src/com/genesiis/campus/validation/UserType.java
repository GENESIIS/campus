package com.genesiis.campus.validation;

//20161206 JH c39-add-course-provider UserType.java created
//20170317 AS c23-admin-login-logout-function-as admin, super admin and company DEO attributes added
//20170404 AS c23-admin-login-logout-function-as added admin,tutor, course provider default values and getDefaultValue()
/**
* UserType enum class used to handle default user types of 
* the application.
*/

public enum UserType {

	ADMIN("ADMIN","-1"),
	FEATURED_COURSE_PROVIDER("FEATURED_COURSE_PROVIDER","-1"),
	TUTOR("TUTOR","-1"),
	SUPER_ADMIN("SUPER_ADMIN","-1"),
	COMPANY_DEO("COMPANY_DEO",""),
	STUDENT("STUDENT","");
	
	private final String userType;
	private  String defaultValue;
	
	

	private UserType(final String userType, String defaultValue) {
		this.userType = userType;
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * @return the userType default value
	 */
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
}