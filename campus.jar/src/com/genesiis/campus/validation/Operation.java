package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161121 CM c36-add-tutor-information added a new attribute to the enum
//20161122 CM c36-add-tutor-information added a new attribute to the enum
/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {

	BAD_OPERATION("BO", ResponseType.INAPPLICABLE,"commandNotFound.jsp"),
	LIST_TOWN_DATA("LIST_TOWN_DATA", ResponseType.JSON, "addTutorDetails.jsp"),
	LIST_COUNTRY_DATA("LIST_COUNTRY_DATA", ResponseType.JSON, "addTutorDetails.jsp"),
	CHECK_USERNAME("CHECK_USERNAME", ResponseType.JSON, "addTutorDetails.jsp"),
	ADD_TUTOR_PROFILE_DETAILS("ATPD", ResponseType.JSP,"addTutorDetails.jsp");
	
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