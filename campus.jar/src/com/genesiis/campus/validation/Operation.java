package com.genesiis.campus.validation;
//20161024 DN c10-contacting-us created the initial Operation.java version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC to the class
//20161027 CM c9-make-inquiry-for-institute add SEND_INSTITUTE_INQUIRY to the enum
//20161027 CM c9-make-inquiry-for-instituteModified viwJspUrl value in SEND_INSTITUTE_INQUIRY.
/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {


	SEND_INSTITUTE_INQUIRY("SII",ResponseType.JSP,"dist/partials/mini-web/inquiry/institute-inquiry.jsp"),
	BAD_OPERATION("BO",ResponseType.INAPPLICABLE, "commandNotFound.jsp");
			
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