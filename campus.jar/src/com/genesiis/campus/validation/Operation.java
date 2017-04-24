package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20170302 JH c96-public-list-all-tutors added PUBLIC_LIST_ALL_TUTORS enum
//20170424 JH c135-public-display-tutor-profile added DISPLAY_PUBLIC_TUTOR_PROFILE

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_CATEGORY_DATA("LIST_CATEGORY_DATA", ResponseType.JSON, ""), 
	PUBLIC_LIST_ALL_TUTORS("PUBLIC_LIST_ALL_TUTORS", ResponseType.JSON, "/dist/public/display-tutors.jsp"),
	DISPLAY_PUBLIC_TUTOR_PROFILE("DISPLAY_PUBLIC_TUTOR_PROFILE", ResponseType.JSON, "/dist/public/display-tutor-profile.jsp");
			
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