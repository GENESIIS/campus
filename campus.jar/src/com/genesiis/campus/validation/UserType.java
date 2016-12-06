package com.genesiis.campus.validation;

//20161206 JH c39-add-course-provider UserType.java created

/**
 * UserType enum class used to handle default user types of 
 * the application.
 */

public enum UserType {

	ADMIN("ADMIN"),
	FEATURED_COURSE_PROVIDER("FEATURED_COURSE_PROVIDER"),
	TUTOR("TUTOR"),
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
