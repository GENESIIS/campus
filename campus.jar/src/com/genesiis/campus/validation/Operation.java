package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161026 JH c7-list-higher-education-courses: added LIST_CATEGORY_LANDING_PAGE attribute 
//20161116 JH c7-higher-education-landing-page: removed unwanted enums
//20161122 JH c39-add-course-provider : added ADD_FEATURED_COURSE_PROVIDER
//20161129 JH c39-add-course-provider : modify response url for ADD_FEATURED_COURSE_PROVIDER	
//20161201 JH c39-add-course-provider : added ADD_ONE_OFF_COURSE_PROVIDER
//20161208 JH c39-add-course-provider : added COURSE_PROVIDER_VALIDATION
//20161220 JH c39-add-course-provider : added LIST_PROVIDER_REGISTRATION_PAGE
//20161220 JH c39-add-course-provider : added DISPLAY_TOWN_DATA

/**
 * Operation class facilitate in binding the CCO
 *  
 */
public enum Operation {
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_CATEGORY_DATA("LIST_CATEGORY_DATA", ResponseType.JSON, ""),
	LIST_CATEGORY_LANDING_PAGE("LIST_CATEGORY_LANDING_PAGE", ResponseType.JSP, "/dist/partials/category-landing-page.jsp"),
	ADD_FEATURED_COURSE_PROVIDER("ADD_FEATURED_COURSE_PROVIDER",  ResponseType.JSON, "" ),
	ADD_ONE_OFF_COURSE_PROVIDER("ADD_ONE_OFF_COURSE_PROVIDER", ResponseType.JSON, "" ),
	COURSE_PROVIDER_VALIDATION("COURSE_PROVIDER_VALIDATION", ResponseType.JSON, "" ),
	LIST_PROVIDER_REGISTRATION_PAGE("LIST_PROVIDER_REGISTRATION_PAGE" , ResponseType.JSON, ""),
	DISPLAY_TOWN_DATA("DISPLAY_TOWN_DATA", ResponseType.JSON, "");		
	
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