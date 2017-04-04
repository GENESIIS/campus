package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161121 CM c36-add-tutor-information added a new attribute to the enum
//20161122 CM c36-add-tutor-information added a new attribute to the enum
//20161220 CW c38-view-update-tutor-profile added a new attribute to the enum
//20161221 CW c38-view-update-tutor-profile added a new attribute to the enum
//20170116 CM c36-add-tutor-information added a new attribute to the enum
//20170116 CM c36-add-tutor-information removed an un wanted attribute from the enum
//20170117 CM c36-add-tutor-information add CHECK_EMAIL attribute to the enum
//20170227 CW c37-tutor-update-tutor-profile-cw added TUTOR_UPDATE_TUTOR_DETAILS & TUTOR_UPDATE_TUTOR
//20170303 CW c37-tutor-update-tutor-profile-cw added CHECK_PASSWORDS
//20170323 CW c157-add-tutor-employment-details-cw added LIST_COURSE_PROVIDERS_FOR_TUTORS
//20170324 CW c157-add-tutor-employment-details-cw added ADD_EMPLOYMENT
//20170327 CW c157-add-tutor-employment-details-cw added ADD_EMPLOYMENT_DETAILS command
//20170328 CW c157-add-tutor-employment-details-cw command LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS added
//20170328 CW c157-add-tutor-employment-details-cw modified command LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS responseType to JSON
//20170329 CW c157-add-tutor-employment-details-cw added REMOVE_SELECTED_EMPLOYMENT command

/**
 * Operation class facilitate in binding the CCO
 * 
 */
public enum Operation {

	BAD_OPERATION("BO", ResponseType.INAPPLICABLE,"commandNotFound.jsp"),
	LIST_TOWN_DATA("LIST_TOWN_DATA", ResponseType.JSON, "addTutorDetails.jsp"),
	LIST_COUNTRY_DATA("LIST_COUNTRY_DATA", ResponseType.JSON, "addTutorDetails.jsp"),
	CHECK_USERNAME("CHECK_USERNAME", ResponseType.JSON, "addTutorDetails.jsp"),
	ADD_TUTOR_PROFILE_DETAILS("ATPD", ResponseType.JSP,"dist/partials/addTutorDetails.jsp"),
	VIEW_TUTOR_DETAILS("VIEW_TUTOR_DETAILS", ResponseType.JSP,"dist/partials/viewUpdateTutor.jsp"),
	UPDATE_TUTOR("UPDATE_TUTOR", ResponseType.JSP,"dist/partials/viewUpdateTutor.jsp"),
	VIEW_TUTOR_UPDATE_TUTOR_DETAILS("VIEW_TUTOR_UPDATE_TUTOR_DETAILS", ResponseType.JSP,"dist/partials/tutorUpdateTutorProfile.jsp"),
	TUTOR_UPDATE_TUTOR("TUTOR_UPDATE_TUTOR", ResponseType.JSP,"dist/partials/tutorUpdateTutorProfile.jsp"),
	CHECK_PASSWORDS("CHECK_PASSWORDS", ResponseType.JSON, "tutorUpdateTutorProfile.jsp"),
	LIST_COURSE_PROVIDERS_FOR_TUTORS("LIST_COURSE_PROVIDERS_FOR_TUTORS", ResponseType.JSON, ""),
	ADD_EMPLOYMENT("ADD_EMPLOYMENT", ResponseType.JSP,"dist/partials/addTutorEmployment.jsp"),	
	ADD_EMPLOYMENT_DETAILS("ADD_EMPLOYMENT_DETAILS", ResponseType.JSP, "dist/partials/addTutorEmployment.jsp"),
	LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS("LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS", ResponseType.JSON, ""),
	REMOVE_SELECTED_EMPLOYMENT("REMOVE_SELECTED_EMPLOYMENT", ResponseType.JSP, "dist/partials/addTutorEmployment.jsp"),
	CHECK_EMAIL("CHECK_EMAIL", ResponseType.JSON, "addTutorDetails.jsp");	
	
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