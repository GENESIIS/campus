package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161109 PN c11-criteria-based-filter-search changed ResponseType.JSP into ResponseType.JSON. 
//20161121 PN c27-upload-user-image: added UPLOAD_USER_PROFILE attribute to operation Enum.
//20161130 PN c27-upload-user-image: GET_USER_PROFILE enum values are changed.
/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {	
	GET_SEARCH_DATA("GET_SEARCH_DATA", ResponseType.JSON, "index.jsp"),
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_CATEGORY_DATA("LIST_CATEGORY_DATA", ResponseType.JSON, ""),
	LIST_LEVEL_DATA("LIST_LEVEL_DATA", ResponseType.JSON, "index.jsp"),
	LIST_TOWN_DATA("LIST_TOWN_DATA", ResponseType.JSON, "index.jsp"),
	LIST_MAJOR_DATA("LIST_MAJOR_DATA", ResponseType.JSON, "index.jsp"),
	LIST_DISTRICT_DATA("LIST_DISTRICT_DATA", ResponseType.JSON, "index.jsp"),
	LIST_INSTITUTE_DATA("LIST_INSTITUTE_DATA", ResponseType.JSON, "index.jsp"),
	UPLOAD_USER_PROFILE("UUP", ResponseType.JSON, "index.jsp"), 
	GET_USER_PROFILE("GUP", ResponseType.JSP, "dist/partials/student/student-dashboard.jsp");
			
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