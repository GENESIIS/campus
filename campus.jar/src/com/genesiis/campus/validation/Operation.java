package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161108 DN, JH, DJ, AS, CM, PN, MM Added field responseType to enum.
//20161111 PN c1-campus-landing-page modified ResponseType.JSP into ResponseType.JSON.
//20161025 CM c13-Display-course-details added a new attribute to the enum
//20161108 DN, JH, DJ, AS, CM, PN, MM Added field responseType to enum.
//20161116 CM c13-Display-course-details Modified VIEW_PROGRAMME viewJSpUrl value.

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {

	BAD_OPERATION("BO", ResponseType.INAPPLICABLE,"commandNotFound.jsp"),
	VIEW_PROGRAMME("VPD", ResponseType.JSP,"/dist/partials/courses/course-details.jsp"),
	GET_SEARCH_DATA("GET_SEARCH_DATA", ResponseType.JSON, "index.jsp"),
	LIST_CATEGORY_DATA("LCD", ResponseType.JSON, "index.jsp"),
	LIST_LEVEL_DATA("LIST_LEVEL_DATA", ResponseType.JSON, "index.jsp"),
	LIST_TOWN_DATA("LIST_TOWN_DATA", ResponseType.JSON, "index.jsp"),
	LIST_MAJOR_DATA("LIST_MAJOR_DATA", ResponseType.JSON, "index.jsp"),
	LIST_DISTRICT_DATA("LIST_DISTRICT_DATA", ResponseType.JSON, "index.jsp"),
	LIST_INSTITUTE_DATA("LIST_INSTITUTE_DATA", ResponseType.JSON, "index.jsp");
			
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