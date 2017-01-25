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
	EMPTYFIELD("Please fill required data"),
	UPDATED("Details updated successfully."), 
	DELETED("Details deleted successfully."), 
	IDFOUND("ID found."), 
	IDNOTFOUND("ID not found."), 
	UNKNOWN(""),
	SUCCESS("Successfull"),
	PHONENUMBERERROR("Invalid Telephone Number."),
	MOBILENUMBERERROR("Invalid Mobile Number."),
	INVALIDDATE("Invalid DateFormat."),
	EMPTYVALUES("Empty data Fields."),
	NOTADDED("Problem when inserting data."), 	
	NOTUPDATED("Problem when updating data."), 	
	MANDATORYFIELDREQUIRED("Fields marked with asterisk (*) are required"), 
	FAIL_REQUEST_SUBMISSION("Request submission is not successfull!"),
	PASS_REQUEST_SUBMISSION("Request successfully submitted! "),
	ACCOUNT_CREATED("User Account Created You Are Good To Go!"),
	ACCOUNT_NOT_CREATED("User Account Creation Fail"),
	USER_NAME_EXISTS("Chosen User Name Has Allready Taken. Please Choose Another!"),
	MAIL_SUCCESS("Mail successfully submited to your email"),
	MAIL_UNSUCCESS("Mail Sending failed"),
	STUDENT_ACCOUNT_CREATE_MAIL_SUBJECT("STUDENT ACCOUNT CREATION"),
	SUCCESSFULL_CREATTION("Your User account is successfully created. "),
	SUPERADMIN_NAME("Administrator ,Campus.lk."), // THIS HAS TO CHANGE 
	RECAPTCHAVERIFICATION("Please verify You're not a robot ");
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
