package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20170109 c36-add-tutor-details-added few more enum fields cw
//20170111 c36-add-tutor-details-added USERNAME_LENGTH, WEBLINKERROR, FACEBOOKERROR, LINKEDINERROR, TWITTERERROR, INSTAGRAMERROR, MYSPACEERROR, WHATSAPPERROR, VIBERERROR cw
//20170117 c36-add-tutor-details-added EMAIL_USED CW

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	USERNAME_EXIST("Username already exists."),
	USERNAME_LENGTH("Username should have at least6 characters & shorter than 20 characters."),
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
	WEBLINKERROR("Web link error."),	
	FACEBOOKERROR("Facebook link error."),	
	LINKEDINERROR("LinkedIn link error."),	
	TWITTERERROR("Twitter link error."),	
	INSTAGRAMERROR("Instagram link error."),	
	MYSPACEERROR("MySpace link error."),	
	WHATSAPPERROR("WhatsApp number error."),	
	VIBERERROR("Viber error."),	
	EMAIL_USED("E mail already used to create a tutor profile."),
	EMPTYFIELD("Please fill required data");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
