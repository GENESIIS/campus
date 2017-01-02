package com.genesiis.campus.validation;
//20161024 DN c10-contacting-us created the initial Operation.java version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC to the class
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161109 DN c10-contacting-us-MP change the path of ContactUs.jsp page
//20161123 DN C18-student-signup-without-using-third-party-application-dn add SIGN_UP_WO_THRD_PARTY enum
// 20161205 DN C18-student-signup-without-using-third-party-application-dn DISPLAY_PREREQUISITE_DATA enume added
//20161206 DN C18-student-signup-without-using-third-party-application-dn DISPLAY_TOWN_DATA enume added
//20170102 DN CAM:47 add UPLOAD_EXISTING_PROFILE_PIC enum to the list


/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {

	
	CONTACT_US_PUBLC("FBTSA",ResponseType.JSP, "dist/partials/contactUs.jsp"),
	GET_SEARCH_DATA("GET_SEARCH_DATA", ResponseType.JSP, "index.jsp"),
	SIGN_UP_WO_THRD_PARTY("SIWOTP",ResponseType.JSON,""),
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_CATEGORY_DATA("LIST_CATEGORY_DATA", ResponseType.JSON, ""),
	LIST_LEVEL_DATA("LIST_LEVEL_DATA", ResponseType.JSP, "index.jsp"),
	LIST_TOWN_DATA("LIST_TOWN_DATA", ResponseType.JSP, "index.jsp"),
	LIST_MAJOR_DATA("LIST_MAJOR_DATA", ResponseType.JSP, "index.jsp"),
	LIST_DISTRICT_DATA("LIST_DISTRICT_DATA", ResponseType.JSP, "index.jsp"),
	DISPLAY_PREREQUISITE_DATA("DPRD", ResponseType.JSON,""),
	DISPLAY_TOWN_DATA("DPTWN", ResponseType.JSON,""),
	UPLOAD_EXISTING_PROFILE_PIC("UEPP",ResponseType.JSON,""),
	LIST_INSTITUTE_DATA("LIST_INSTITUTE_DATA", ResponseType.JSP, "index.jsp");
			
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
