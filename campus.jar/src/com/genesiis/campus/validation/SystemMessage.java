package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161201 JH c39-add-course-provider removed unwanted enums
//20161208 JH c39-add-course-provider added USERNAME_VALID, USERNAME_INVALID
//20161209 JH c39-add-course-provider added EMPTY_USERNAME
//20161227 JH c39-add-course-provider added PREFIX_INVALID, PREFIX_VALID
//20170115 JH c39-add-course-provider added EMAIL_EXIST

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	ERROR("Unsuccessful."), 	
	UNKNOWN(""),
	SUCCESS("Successfull"),
	NOTADDED("Problem when inserting data."),
	USERNAME_VALID("valid username"),
	USERNAME_INVALID("already exist the username"),
	EMPTY_USERNAME("Please give a username"),
	EMPTY_FIELD("Empty field(s)"),		
	PREFIX_INVALID("Prefix is not available"),
	PREFIX_VALID("Valid prefix"),
	EMAIL_EXIST("Email already exist."),
	NO_DATA("No data");
	
	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
