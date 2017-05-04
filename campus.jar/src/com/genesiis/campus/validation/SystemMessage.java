package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161201 JH c39-add-course-provider removed unwanted enums
//20161208 JH c39-add-course-provider added USERNAME_VALID, USERNAME_INVALID
//20161209 JH c39-add-course-provider added EMPTY_USERNAME
//20161227 JH c39-add-course-provider added PREFIX_INVALID, PREFIX_VALID
//20170115 JH c39-add-course-provider added EMAIL_EXIST
//20170503 JH c141-ui-integration-for-add-course-provider added new enums to validate course provider registration page

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	ERROR("Unsuccessful."), 	
	UNKNOWN(""),
	SUCCESS("Successfull"),
	NOTADDED("Problem when inserting data."),
	USERNAME_VALID("valid username"),
	USERNAME_INVALID("Username already exist"),
	EMPTY_USERNAME("Please give a username"),
	EMPTY_FIELD("Empty field(s)"),		
	PREFIX_INVALID("Prefix is not available"),
	PREFIX_VALID("Valid prefix"),
	EMAIL_EXIST("Email already exist."),
	MANDATORYFIELDREQUIRED("Fields marked with asterisk (*) are required"),
	EMPTY_PROVIDER_TYPE("Please select the course provider type"),
	EMPTY_OR_TOO_LONG("Empty or too long value."),
	ABOUT_ME("Say something about you."),
	TOO_LONG_FIELD("Field value is too long."),
	EMPTY_COUNTRY("Select your country."),
	EMPTY_TOWN("Select your town."),
	EMPTY_OPTION("Select an option. "),
	INVALID_AREA_CODE("Area code is empty, too long(max 4 characters) or invalid. Only numbers allowed."),
	EMPTY_LANDPHONE_NUMBER("Phone number is empty, too long(max 12 characters) or invalid. Only numbers allowed."),
	TOO_LONE_PHONE_NUMBER("Phone number is too long(max 12 characters)."),
	INVALID_PHONE_NUMBER("Invalid number. Only numbers allowed"),
	INVALID_NETWORK_CODE("Network code is empty, too long(max 4 characters) or invalid. Only numbers allowed."),
	EMPTY_OR_INVALID_EMAIL("Empty or invalid email address. (only 255 characters allowed)."),
	INVALID_SOCIAL_MEDIA_NUMBER( "Maximum length exceed or invalid."),
	ERROR_CONTACT_NAME("Contact name is empty or too long. (only 100 characters)."),
	INVALID_EMAIL("Invalid contact Email address"),
	TOO_LONG_USERNAME("Username too small or exceed the max length. It must have min 5 and max 100 characters"),
	ERROR_ONLY_NUMBERS( "Only numbers are not allowed. "),
	PASSWORD_NOT_MATCH("Password fields does not match"),
	WEAK_PASSWORD("Password filed(s) should have 6 to 100 characters."),
	INVALID_URL(" URL invalid.");
	
	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
