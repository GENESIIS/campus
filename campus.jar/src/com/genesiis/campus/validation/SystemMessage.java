package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project

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
	FAILED_TO_CREATE("Object not created"),
	NICERROR("Invalid NIC Number."),
	PHONENUMBERERROR("Invalid Telephone Number."),
	VALIDUSER("valid Username and Password."),
	LOGINUNSUCCESSFULL("Login Unsuccessfull"),
	LOGGEDSUCCESSFULL("Logged successfull"),
	LOGOUTSUCCESSFULL("Logout successfull"),
	LOGOUTUNSUCCESSFULL("Logout Unsuccessfull"),
	MAIL_SUCCESS("Mail successfully submited to your email"),
	MAIL_UNSUCCESS("Mail Sending failed"),
	FAIL_REQUEST_SUBMISSION("Request submission is not successfull!"),
	PASS_REQUEST_SUBMISSION("Request successfully submitted! "),
	NOTREGISTERD("Sorry, you are not a registered user! Please sign up first"),
	INVALIDUSERNAME("The Username or Email you provided is invalid. Please try again!"),
	INVALIDPASSWORD("The password you provided is invalid. Please try again!"),
	ATTEMPS("You have done two attemps. Please try again or reset your password"),
	SESSIONEXPIRED("Session Expired ! Please login again. "),
	INVALID_EMAIL("Your email address is invalid. Please enter a valid address ! "),
	INVALID_HASHCODE("Your Varification code is invalid. Please try again ! "),
	HASHCODES("Hash code updated successfull !!"),
	HASHCODEUNS("Hash code not updated successfull !!"),
	EMAILERROR("Invalid Email.");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
