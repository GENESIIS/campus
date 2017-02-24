package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161101 DN c10-contacting-us SystemMessage() modified and added 
//		FAIL_REQUEST_SUBMISSION,PASS_REQUEST_SUBMISSION.
//20161116 DN  c10-contacting-us-page-MP removed unnecessary enum values.
//20161125 DN  C18-student-signup-without-using-third-party-application-dn added ACCOUNT_CREATED,
//			ACCOUNT_NOT_CREATED,USER_NAME_EXISTS enums
//20161227 DN CAM 18: added STUDENT_ACCOUNT_CREATE_MAIL_SUBJECT,STUDENT_ACCOUNT_CREATE_MAIL_SUBJECT, RECAPTCHAVERIFICATION enum values.
//201701180 DN CAM 18 corrected typo in USER_NAME_EXISTS enum string 
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
	ACCOUNT_CREATED("User Account Created You Are Good To Go!"),
	ACCOUNT_NOT_CREATED("User Account Creation Fail"),
	USER_NAME_EXISTS("Chosen User Name Or The Eamil Has been Already Taken. Please Choose Another!"),
	MAIL_SUCCESS("Mail successfully submited to your email"),
	MAIL_UNSUCCESS("Mail Sending failed"),
	STUDENT_ACCOUNT_CREATE_MAIL_SUBJECT("STUDENT ACCOUNT CREATION"),
	STUDENT_SUCCESSFULL_CREATTION("Your User account is successfully created. "),
	SUPERADMIN_NAME("Administrator ,Campus.lk."), // THIS HAS TO CHANGE 
	RECAPTCHAVERIFICATION("Please verify You're not a robot ");
	
	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
