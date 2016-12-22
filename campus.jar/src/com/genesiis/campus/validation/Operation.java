package com.genesiis.campus.validation;
//20161127 DJ c51-report-courses-by-course-provider-MP-dj add SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER 
//20161127 DJ c51-report-courses-by-course-provider-MP-dj add REPORT_COURSES_BY_COURSE_PROVIDER entry 

//20161206 DJ c52-report-banner-statistics-MP-dj add SEARCH_VIEW_BANNER_STATISTICS entry 
//20161210 DJ c52-report-banner-statistics-MP-dj add REPORT_BANNER_STATISTICS entry 
//20161210 DJ c52-report-banner-statistics-MP-dj add LIST_PAGESLOT_WISE_BANNER entry 
//20161210 DJ c52-report-banner-statistics-MP-dj add REPORT_BANNER_STATISTICS entry 

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {	
	
	BAD_OPERATION("BO", ResponseType.INAPPLICABLE, "commandNotFound.jsp"),
	SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER("SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER",ResponseType.JSON," "),
	REPORT_COURSES_BY_COURSE_PROVIDER("REPORT_COURSES_BY_COURSE_PROVIDER",ResponseType.JSON," "),			
	SEARCH_VIEW_BANNER_STATISTICS("SEARCH_VIEW_BANNER_STATISTICS",ResponseType.JSON,""),
	LIST_PAGE_WISE_PAGESLOTS("LIST_PAGE_WISE_PAGESLOTS",ResponseType.JSON,""),
	LIST_PAGESLOT_WISE_BANNER("LIST_PAGESLOT_WISE_BANNER",ResponseType.JSON,""),
	REPORT_BANNER_STATISTICS("REPORT_BANNER_STATISTICS",ResponseType.JSON,"");
	
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