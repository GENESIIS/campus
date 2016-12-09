package com.genesiis.campus.util.security;
//20161205 AS C19-student-login-without-using-third-party-application-test-as student interface and button action privilege handling 

public enum RolePrivilege {

	ADMIN("ADMIN"),
	FEATURED_COURSE_PROVIDER("FEATURED_COURSE_PROVIDER"),
	TUTOR("TUTOR"),
	STUDENT("STUDENT");
	
	private final String userPrivilegeInterface;
	
	
	private RolePrivilege(final String userPrivilegeInterface) {
		this.userPrivilegeInterface = userPrivilegeInterface;
	}
	
	
	/**
	 * @return the user Privilege Interface URL
	 */
	public String getUserPrivilegeInterface() {
		return userPrivilegeInterface;
	}
	
	
}
