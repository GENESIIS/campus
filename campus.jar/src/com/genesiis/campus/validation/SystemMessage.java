package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161101 DN c10-contacting-us SystemMessage() modified and added 
//		FAIL_REQUEST_SUBMISSION,PASS_REQUEST_SUBMISSION.
//20161116 DN DN c10-contacting-us-page-MP removed unnecessary enum values.
//20170212 PN CAM-72: added SYSTEM_EXCEPTION, SYSTEM_ERROR, ERROR404 and ERROR500 values to the Enum.

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	UPDATED("Details updated successfully."), 
	DELETED("Details deleted successfully."), 
	IDFOUND("ID found."), 
	IDNOTFOUND("ID not found."), 
	ERROR("Unsuccessful."), 	
	UNKNOWN(""),
	EMPTYFIELD("Field is Empty."),
	SUCCESS("Successfull"),
	PHONENUMBERERROR("Invalid Telephone Number."),
	MOBILENUMBERERROR("Invalid Mobile Number."),
	EMAILERROR("Invalid Email."),
	INVALIDDATE("Invalid DateFormat."),
	EMPTYVALUES("Empty data Fields."),
	NOTADDED("Problem when inserting data."), 	
	NOTUPDATED("Problem when updating data."), 	
	MANDATORYFIELDREQUIRED("Fields marked with asterisk (*) are required"), 
	FAIL_REQUEST_SUBMISSION("Request submission is not successfull!"),
	PASS_REQUEST_SUBMISSION("Request successfully submitted! "),
	RECAPTCHAVERIFICATION("Please verify You're not a robot "), 
	SYSTEM_EXCEPTION("SYSTEM EXCEPTION"),
	SYSTEM_ERROR("SYSTEM ERROR"),
	ERROR404("Page Not Found..!!"),
	ERROR500("Internal Server Error..!!");
	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
