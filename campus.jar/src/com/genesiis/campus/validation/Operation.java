package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161108 DN, JH, DJ, AS, CM, PN, MM Added field responseType to enum.
//20161109 MM c5-corporate-training-landing-page-MP - Changed value for 
//				commandString of LIST_CATEGORY_PROGRAMMES enum
//20161122 MM c25-student-login-create-dashboard-MP - Modified code to add constant 
//				for LIST_STUDENT_DASHBOARD
//20161203 MM c25-student-create-dashboard-MP - Modified code to change value for 
//				viewJspUrl name for LIST_STUDENT_DASHBOARD constant

/**
* Operation class facilitate in binding the CCO
* 
*/
public enum Operation {
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_STUDENT_DASHBOARD("LIST_STUDENT_DASHBOARD", ResponseType.JSP, "/dist/partials/student/student-dashboard.jsp");			
	
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