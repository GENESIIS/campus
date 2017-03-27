package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20170228 AS C22 LOGGEDALLREADY attribute added
public enum SystemMessage {	
	ADDED("Details added successfully."), 
	UPDATED("Details updated successfully."), 
	DELETED("Details deleted successfully."), 
	ERROR("Unsuccessful."), 	
	EMPTYFIELD("Field is Empty."),
	SUCCESS("Successfull"),
	RECAPTCHAVERIFICATION("Please verify You're not a robot "),
	INQUIRYSENT("Inquiry sent Successfully"),
	INVALIDECODE("Invalid Country Code."),
	INVALIDEAREA("Invalid Area Code."),
	FAILED_TO_CREATE("Object not created"),
	NICERROR("Invalid NIC Number."),
	PHONENUMBERERROR("Invalid Telephone Number."),
	VALIDUSER("valid Username and Password."),
	LOGINUNSUCCESSFULL("Login Unsuccessfull"),
	LOGGEDSUCCESSFULL("Logged successfull"),
	LOGOUTSUCCESSFULL("Logout successfull"),
	LOGOUTUNSUCCESSFULL("Logout Unsuccessfull"),
	LOGGEDALLREADY("User Already Logged In"),
	LOGGINATTEMPT3("Temporary Your account has been blocked!"),
	NOTREGISTERD("Sorry, you are not a registered user! Please sign up first"),
	INVALIDUSERNAME("The Username or Email you provided is invalid. Please try again!"),
	INVALIDPASSWORD("The password you provided is invalid. Please try again!"),
	ATTEMPS("You have done two attemps. Please try again or reset your password"),
	SESSIONEXPIRED("Session Expired ! Please login again. "),
	EMAILERROR("Invalid Email.");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
