package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161027 CW c38-view-update-tutor-profile-cw 
//20170109 c36-add-tutor-details-added few more enum fields cw
//20170111 c36-add-tutor-details-added USERNAME_LENGTH, WEBLINKERROR, FACEBOOKERROR, LINKEDINERROR, TWITTERERROR, INSTAGRAMERROR, MYSPACEERROR, WHATSAPPERROR, VIBERERROR cw
//20170117 c36-add-tutor-details-added EMAIL_USED CW
//20170130 c36-add-tutor-details-modified FIRSTNAMEERROR & LASTNAMEERROR messages
//20170131 c36-add-tutor-details-added EMPTYEMAIL, EMPTYUSERNAME, PASSWORDLENGTHERROR, EMPTYPASSWORD, PASSWORDCONFIRMERROR, EMPTYCONFIRMPASSWORD, FIRSTNAMEERROR, LASTNAMEERROR message
//20170202 c36-add-tutor-details-added COUNTRYCODEERROR message
//20170202 c36-add-tutor-details-added NOMODIFICATIONS message
//20170209 c38-view-update-tutor-profile-added EMPTYFIRSTNAME, EMPTYLASTNAME, EMPTYMOBILECOUNTRYCODE, EMPTYMOBILENETWORKCODE, EMPTYMOBILENUMBER, EMPTYLANDCOUNTRYCODE, EMPTYLANDAREACODE, EMPTYLANDNUMBER, EMPTYADDRESS1 messages
//20170214 c38-view-update-tutor-profile-added INCORRECTDATA.
//20170224 CW c103-send-email-tutor-status-change-modified EMPTYFIRSTNAME, EMPTYLASTNAME, EMPTYMOBILECOUNTRYCODE, EMPTYMOBILENETWORKCODE, EMPTYMOBILENUMBER, EMPTYLANDCOUNTRYCODE, EMPTYLANDAREACODE, EMPTYLANDNUMBER, EMPTYADDRESS1 messages &
				//modified MAIL_SUCCESS message to MAIL_SUCCESS_TUTOR & it's content.

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	USERNAME_EXIST("Username already exists."),
	USERNAME_LENGTH("Username should have at least 6 characters & shorter than 20 characters."),
	ERROR("Unsuccessful."),
	INCORRECTDATA("Something wrong in the data you have entered..."),
	EMAILERROR("Invalid Email Address."),
	EMPTYEMAIL("Email Address cannot be empty."),
	EMPTYUSERNAME("Username cannot be empty."),
	EMPTYFIRSTNAME("First name cannot be empty."),
	EMPTYLASTNAME("Last name cannot be empty."),
	EMPTYMOBILECOUNTRYCODE("Mobile Country code cannot be empty."),
	EMPTYMOBILENETWORKCODE("Mobile network code cannot be empty."),
	EMPTYMOBILENUMBER("Mobile number cannot be empty."),
	EMPTYLANDCOUNTRYCODE("Land country code cannot be empty."),
	EMPTYLANDAREACODE("Land area code cannot be empty."),
	EMPTYLANDNUMBER("Land number cannot be empty."),
	EMPTYADDRESS1("Address Line 1 cannot be empty."),
	PASSWORDLENGTHERROR("Password should have at least 6 characters & shorter than 20 characters."),
	PASSWORDCONFIRMERROR("Password & confirm Password entered must be the same."),
	EMPTYPASSWORD("Password cannot be empty."),
	EMPTYCONFIRMPASSWORD("Password cannot be empty."),
	FIRSTNAMEERROR("Invalid First Name."),
	LASTNAMEERROR("Invalid Last Name."),
	EMPTYFIELD("Please fill required data"),
	UPDATED("Details Updated successfully."),
	NOMODIFICATIONS("You haven't done any changes."),
	PASSWORDERROR("Password should have at least 6 characters & shorter than 20 characters."),
	MOBILECOUNTRYCODEERROR("Mobile Country code error."),
	COUNTRYCODEERROR("Country code error."),
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
	SUCCESSFULL_CREATTION("Your User account is successfully created. "),
	MAIL_SUCCESS_TUTOR("Mail successfully submited to Tutor's Email..."),
	MAIL_UNSUCCESS("Mail Sending failed"),
	UNKNOWN(""),
	EMAIL_USED("E mail already used to create a tutor profile.");
	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
