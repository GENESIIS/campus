package com.genesiis.campus.validation;

//20161206 JH c39-add-course-provider UserType.java created
//20170317 AS c23-admin-login-logout-function-as admin, super admin and company DEO attributes added
/**
* UserType enum class used to handle default user types of 
* the application.
*/

public enum UserType {

	ADMIN("ADMIN"),
	FEATURED_COURSE_PROVIDER("FEATURED_COURSE_PROVIDER"),
	TUTOR("TUTOR"),
	SUPER_ADMIN("SUPER_ADMIN"),
	COMPANY_DEO("COMPANY_DEO"),
	STUDENT("STUDENT");
	
	private final String userType;
	
	private UserType(final String userType) {
		this.userType = userType;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	
}