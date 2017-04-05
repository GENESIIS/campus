package com.genesiis.campus.validation;
//20161024 DN c10-contacting-us created the initial Operation.java version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC to the class
//20161027 AS C8-inquiry-form-for-course add SEND_COURSE_INQUIRY to the class
//20161123 AS C19-student-login-without-using-third-party-application-test-as add STUDENT_LOGIN to the class
//20170202 AS C22 forgot password, added EMAIL_VERIFICATION to the class.
//20170307 CW c147-tutor-reset-password-cw added CHECK_EMAIL to the class.
//20170307 CW c147-tutor-reset-password-cw added TUTOR_EMAIL_VERIFICATION to the class.
//20170308 CW c147-tutor-reset-password-cw removed CHECK_EMAIL command
//20170313 CW c148-tutor-verify-hashcode-reset-password-cw add TUTOR_HASH_VERIFICATION command
//20170314 CW c148-tutor-verify-hashcode-reset-password-cw add TUTOR_RESET_PASSWORD command

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {
	
	GET_SEARCH_DATA("GET_SEARCH_DATA", ResponseType.JSP, "index.jsp"),
	SEND_COURSE_INQUIRY("SCI",ResponseType.JSON, ""),
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_CATEGORY_DATA("LIST_CATEGORY_DATA", ResponseType.JSON, ""),
	LIST_LEVEL_DATA("LIST_LEVEL_DATA", ResponseType.JSP, "index.jsp"),
	LIST_TOWN_DATA("LIST_TOWN_DATA", ResponseType.JSP, "index.jsp"),
	LIST_MAJOR_DATA("LIST_MAJOR_DATA", ResponseType.JSP, "index.jsp"),
	LIST_DISTRICT_DATA("LIST_DISTRICT_DATA", ResponseType.JSP, "index.jsp"),
	STUDENT_LOGIN("SLOG", ResponseType.JSON, ""),
	STUDENT_LOGOUT("SLGOUT", ResponseType.JSON, ""),
	SESSIONEXPIR("EXP",ResponseType.JSON, ""),
	EMAIL_VERIFICATION("EMAILV",ResponseType.JSON,""),
	HASH_VERIFICATION("HASHV",ResponseType.JSON,""),
	CHANGED_PASSWORD("RESETPASS",ResponseType.JSON,""),
	LIST_INSTITUTE_DATA("LIST_INSTITUTE_DATA", ResponseType.JSP, "index.jsp"),
	TUTOR_EMAIL_VERIFICATION("TUTOR_EMAIL_VERIFICATION", ResponseType.JSON, ""),	
	TUTOR_HASH_VERIFICATION("TUTOR_HASH_VERIFICATION", ResponseType.JSON, ""),	
	TUTOR_RESET_PASSWORD("TUTOR_RESET_PASSWORD", ResponseType.JSON, ""),
	LIST_CATEGORY_LANDING_PAGE("LIST_CATEGORY_LANDING_PAGE", ResponseType.JSP, "/dist/partials/category-landing-page.jsp");
			
	private final String commandString;
	private final ResponseType responseType;
	private final String viewJspUrl;


	Operation(final String newValue, final ResponseType responseType, 
			final String viewJspUrl) {
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