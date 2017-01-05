package com.genesiis.campus.validation;

//20170105 CW c98-send-email-at-tutor-signup created the initial SystemMail.java version

public enum SystemMail {
	SEND_EMAIL_TUTOR_SIGNUP("Registration Confirmation", "Dear Sir/Madam", "You have successfully registered with the campus.lk. Your details are waiting for the administration approval. On admin approval you will receive an email, confirming your information with campus.lk. If you didn't sign up for this account, or you are having trouble with your account, please contact us at support@campus.lk and we will be happy to help you.");
	
	private final String subject;
	private final String title;
	private final String mailBody;
	
	SystemMail(final String subject, final String title, final String mailBody) {
		this.subject = subject;
		this.title = title;
		this.mailBody = mailBody;
	}	

	public String getSubject() {
		return subject;
	}

	public String getTitle() {
		return title;
	}

	public String getMailBody() {
		return mailBody;
	}


	public static SystemMail getSystemMail(String o) {
		for (SystemMail op : values()) {

			if (op.getCommandString().equalsIgnoreCase(o))
				return op;
		}
		return BAD_OPERATION;
	}
	
}





/*

public enum Operation {

	BAD_OPERATION("BO", ResponseType.INAPPLICABLE,"commandNotFound.jsp"),
	LIST_TOWN_DATA("LIST_TOWN_DATA", ResponseType.JSON, "addTutorDetails.jsp"),
	LIST_COUNTRY_DATA("LIST_COUNTRY_DATA", ResponseType.JSON, "addTutorDetails.jsp"),
	CHECK_USERNAME("CHECK_USERNAME", ResponseType.JSON, "addTutorDetails.jsp"),
	ADD_TUTOR_PROFILE_DETAILS("ATPD", ResponseType.JSP,"dist/partials/addTutorDetails.jsp");
	
	private final String commandString;
	private final ResponseType responseType;
	private final String viewJspUrl;


	Operation(final String newValue, final ResponseType responseType, final String viewJspUrl) {
		this.commandString = newValue;
		this.responseType = responseType;
		this.viewJspUrl = viewJspUrl;

	}

	public String getCommandString() {
		return commandString;
	}

	public ResponseType getResponseType() {
		return responseType;
	}

	public String getPageURL() {
		return viewJspUrl;
	}


	public static Operation getOperation(String o) {
		for (Operation op : values()) {

			if (op.getCommandString().equalsIgnoreCase(o))
				return op;
		}
		return BAD_OPERATION;
	}

}

*/