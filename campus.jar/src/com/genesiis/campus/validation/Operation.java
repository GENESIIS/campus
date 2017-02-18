package com.genesiis.campus.validation;
//20161024 DN c10-contacting-us created the initial Operation.java version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC to the class
//20161030 DJ c6-list-available-institutes-on-the-view add LIST_ALL_INSTITUTES
//20161030 DJ c6-list-available-institutes-on-the-view add LIST_TOP_COURSE_PROVIDERS
//20161110 DJ c6-list-available-institutes-on-the-view INT-Adjust the implementation to newly changed framework
//20161115 DJ c17-provider-criteria-based-filter-search set viewMoreCourseProviders.jsp path.
//20161121 DJ c17-provider-criteria-based-filter-search add  LIST_FILTER_SEARCH_COURSE_PROVIDERS.
//20161124 DJ c17-provider-criteria-based-filter-search add  LIST_FILTER_SEARCH_CATEGORY_TYPES.
//20170113 DJ c123-general-filter-search-course-provider-MP-dj add FILTER_SEARCH_COURSE_PROVIDERS.
//20170202 DJ c138-add-basic-programme-MP-dj Add LIST_PROGRAMME_ADD_VIEW.
//20170206 DJ c138-add-basic-programme-MP-dj Add ADD_PROGRAMME_DETAILS.

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {	
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	LIST_PROGRAMME_ADD_VIEW("LIST_PROGRAMME_ADD_VIEW",ResponseType.JSON," "),
	ADD_PROGRAMME_DETAILS("ADD_PROGRAMME_DETAILS",ResponseType.JSON," ");
			
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