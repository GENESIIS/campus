package com.genesiis.campus.validation;

//20161026 CM c9-make-inquiry-for-institute INIT SystemMessage.java
//20161103 CM c9-make-inquiry-for-institute added RECAPTCHAVERIFICATION attribute.

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	UPDATED("Details updated successfully."), 
	DELETED("Details deleted successfully."),
	ERROR("Unsuccessful."), 	
	UNKNOWN(""),
	EMPTYFIELD("Field is Empty."),
	SUCCESS("Successfull"),
	RECAPTCHAVERIFICATION("Please verify You're not a robot "),
	INQUIRYSENT("Inquiry sent Successfully"),
	INVALIDECODE("Invalid Country Code."),
	INVALIDEAREA("Invalid Area Code."),
	NICERROR("Invalid NIC Number."),
	PHONENUMBERERROR("Invalid Telephone Number.");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
