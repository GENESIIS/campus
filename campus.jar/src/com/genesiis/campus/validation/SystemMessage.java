package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161027 CW c38-view-update-tutor-profile-cw 

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	USERNAME_EXIST("Username already exists."), 
	ERROR("Unsuccessful."),
	EMAILERROR("Invalid Email Address."),
	EMPTYFIELD("Please fill required data"),
	UPDATED("Details Updated successfully.");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
