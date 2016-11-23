package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	USERNAME_EXIST("Username already exists."), 
	ERROR("Unsuccessful."),
	EMPTYFIELD("Please fill required data");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
