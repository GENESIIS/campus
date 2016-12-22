package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161108 DN, JH, DJ, AS, CM, PN, MM Added field responseType to enum.
//20161111 PN c1-campus-landing-page modified ResponseType.JSP into ResponseType.JSON.
//20161116 MM c2-integrate-google-banners Added LIST_GOOGLE_ADVERTS enum
//20161117 MM c2-integrate-google-banners Changed LIST_GOOGLE_ADVERTS to LOAD_BANNER_PAGE 
//20161217 MM c2-integrate-google-banners Removed LOAD_BANNER_PAGE constant (before 
//				merging with sprint branch) that was there for testing the banner 
//				displaying functionality 
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC to the class
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161109 DN c10-contacting-us-MP change the path of ContactUs.jsp page

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {

	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"), LIST_CATEGORY_DATA(
			"LIST_CATEGORY_DATA", ResponseType.JSON, ""), ADD_BANNER_STAT(
			"ADD_BANNER_STAT", ResponseType.JSON, ""),

	CONTACT_US_PUBLC("FBTSA", ResponseType.JSP, "dist/partials/contactUs.jsp"), GET_SEARCH_DATA(
			"GET_SEARCH_DATA", ResponseType.JSP, "index.jsp"),

	LIST_LEVEL_DATA("LIST_LEVEL_DATA", ResponseType.JSP, "index.jsp"), LIST_TOWN_DATA(
			"LIST_TOWN_DATA", ResponseType.JSP, "index.jsp"), LIST_MAJOR_DATA(
			"LIST_MAJOR_DATA", ResponseType.JSP, "index.jsp"), LIST_DISTRICT_DATA(
			"LIST_DISTRICT_DATA", ResponseType.JSP, "index.jsp"), LIST_INSTITUTE_DATA(
			"LIST_INSTITUTE_DATA", ResponseType.JSP, "index.jsp");

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
