package com.genesiis.campus.validation;
//20161127 DJ c51-report-courses-by-course-provider-MP-dj add SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER 
//20161127 DJ c51-report-courses-by-course-provider-MP-dj add REPORT_COURSES_BY_COURSE_PROVIDER entry 
//20161228 DJ c53-report-registered-students-MP-dj add SEARCH_VIEW_REGISTERED_STUDENTS and REPORT_REGISTERED_STUDENTS entries
//20170418 DJ c54-report-course-stats-MP-dj add REPORT_COURSE_STATS entry.

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {	
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),	
	SEARCH_VIEW_REGISTERED_STUDENTS("SEARCH_VIEW_REGISTERED_STUDENTS",ResponseType.JSON," "),
	REPORT_REGISTERED_STUDENTS("REPORT_REGISTERED_STUDENTS",ResponseType.JSON," "),
	REPORT_COURSE_STATS("REPORT_COURSE_STATS",ResponseType.JSON," ");
			
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