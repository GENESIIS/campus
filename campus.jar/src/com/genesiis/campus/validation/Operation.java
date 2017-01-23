package com.genesiis.campus.validation;

//20161229 JH c39-add-course-provider : added LIST_COUESE_PROVIDER_TYPES
//20170123 JH c135-admin-tutor-keyword-search : added SEARCH_TUTORS

/**
 * Operation class facilitate in binding the CCO
 *  
 */
public enum Operation {
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_CATEGORY_DATA("LIST_CATEGORY_DATA", ResponseType.JSON, ""),
	LIST_CATEGORY_LANDING_PAGE("LIST_CATEGORY_LANDING_PAGE", ResponseType.JSP, "/dist/partials/category-landing-page.jsp"),
	LIST_TUTORS("LIST_TUTORS", ResponseType.JSON, ""),
	SEARCH_TUTORS("SEARCH_TUTORS", ResponseType.JSON, "");
	
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