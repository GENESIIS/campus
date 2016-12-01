package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161201 JH c39-add-course-provider removed unwanted enums

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	ERROR("Unsuccessful."), 	
	UNKNOWN(""),
	SUCCESS("Successfull"),
	NOTADDED("Problem when inserting data."), 	
;

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
