package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20170109 c36-add-tutor-details-added few more enum fields cw
//20170111 c36-add-tutor-details-added USERNAME_LENGTH, WEBLINKERROR, FACEBOOKERROR, LINKEDINERROR, TWITTERERROR, INSTAGRAMERROR, MYSPACEERROR, WHATSAPPERROR, VIBERERROR cw
//20170117 c36-add-tutor-details-added EMAIL_USED CW
//20170130 c36-add-tutor-details-modified FIRSTNAMEERROR & LASTNAMEERROR messages
//20170131 c36-add-tutor-details-added EMPTYEMAIL, EMPTYUSERNAME, PASSWORDLENGTHERROR, EMPTYPASSWORD, PASSWORDCONFIRMERROR, EMPTYCONFIRMPASSWORD, FIRSTNAMEERROR, LASTNAMEERROR message
//20170202 c36-add-tutor-details-added COUNTRYCODEERROR message
//20170203 c36-add-tutor-details-added TOWNCODEERROR message
//20170220 c36-add-tutor-details-added some messages from c38-view-update-tutor-profile
//20170221 c36-add-tutor-details-modified some messages.

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	USERNAME_EXIST("Username already exists."),
	USERNAME_LENGTH("Username should have at least 6 characters & shorter than 20 characters."),
	ERROR("Unsuccessful."),
	EMAILERROR("Invalid Email Address."),
	EMPTYEMAIL("Email Address cannot be empty."),
	EMPTYUSERNAME("Username cannot be empty."),
	PASSWORDLENGTHERROR("Password should have at least 6 characters & shorter than 20 characters."),
	PASSWORDCONFIRMERROR("Password & confirm Password entered must be the same."),
	EMPTYPASSWORD("Password cannot be empty."),
	EMPTYCONFIRMPASSWORD("Password cannot be empty."),
	FIRSTNAMEERROR("Invalid First Name."),
	LASTNAMEERROR("Invalid Last Name."),
	MOBILECOUNTRYCODEERROR("Mobile Country code error."),
	COUNTRYCODEERROR("Please select Country."),
	TOWNCODEERROR("Please select Town."),
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
	EMPTYFIELD("Please fill required data"),
	INCORRECTDATA("Something wrong in the data you have entered..."),
	EMPTYFIRSTNAME("First name cannot be empty."),
	EMPTYLASTNAME("Last name cannot be empty."),
	EMPTYMOBILECOUNTRYCODE("Mobile Country code cannot be empty."),
	EMPTYMOBILENETWORKCODE("Mobile network code cannot be empty."),
	EMPTYMOBILENUMBER("Mobile number cannot be empty."),
	EMPTYLANDCOUNTRYCODE("Land country code cannot be empty."),
	EMPTYLANDAREACODE("Land area code cannot be empty."),
	EMPTYLANDNUMBER("Land number cannot be empty."),
	EMPTYADDRESS1("Address Line 1 cannot be empty."),
	UPDATED("Details Updated successfully."),
	NOMODIFICATIONS("You haven't done any changes."),
	PASSWORDERROR("Password should have at least 6 characters & shorter than 20 characters.");
	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
