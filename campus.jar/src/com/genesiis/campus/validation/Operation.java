package com.genesiis.campus.validation;
//20161127 DJ c51-report-courses-by-course-provider-MP-dj add SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER 
//20161127 DJ c51-report-courses-by-course-provider-MP-dj add REPORT_COURSES_BY_COURSE_PROVIDER entry 

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {	
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER("SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER",ResponseType.JSP,"dist/partials/coursesByCourseProviderView.jsp"),
	REPORT_COURSES_BY_COURSE_PROVIDER("REPORT_COURSES_BY_COURSE_PROVIDER",ResponseType.JSP,"dist/partials/coursesByCourseProviderView.jsp");
			
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