package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20170109 c36-add-tutor-details-added few more enum fields cw

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	USERNAME_EXIST("Username already exists."), 
	ERROR("Unsuccessful."),
	EMAILERROR("Invalid Email Address."),
	PASSWORDERROR("Password should have at least 6 characters & shorter than 20 characters."),
	FIRSTNAMEERROR("Invalid Email Address."),
	LASTNAMEERROR("First name should not be empty."),
	MOBILECOUNTRYCODEERROR("Mobile Country code error."),
	NETWORKCODEERROR("Mobile network code error."),
	MOBILENUMBERERROR("Mobile number error."),
	LANDCOUNTRYCODEERROR("Land phone country code error."),
	LANDAREACODEERROR("Land phone area code error."),
	LANDNUMBERERROR("Land phone number error."),
	ADDRESSLINE1ERROR("Address Line 1 error."),
	EMPTYFIELD("Please fill required data");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
