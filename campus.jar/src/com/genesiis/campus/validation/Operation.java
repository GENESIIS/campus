package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161109 PN c11-criteria-based-filter-search changed ResponseType.JSP into ResponseType.JSON. 
//20161121 PN c27-upload-user-image: added UPLOAD_USER_PROFILE attribute to operation Enum.
//20161124 PN c26-add-student-details: added GET_SCHOOLEDUCATION_DATA, GET_SCHOOLEDUCATION_DATA, UPDATE_SCHOOLEDUCATION_DATA attributes
//20161129 PN c26-add-student-details: added ADD_PROFESSIONAL_EXP, UPDATE_PROFESSIONAL_EXP and DELETE_PROFESSIONAL_EXP attributes.
//20161205 PN c26-add-student-details: added GET_TOWN_DETAILS and ADD_STUDENT_DETAILS attributes.
//20161206 PN c26-add-student-details: added GET_SKILL_DETAILS, ADD_STUDENT_SKILL attributes
//20161215 PN CAM-28: added ADD_HIGHER_EDUCATION, UPDATE_HIGHER_EDUCATION, DELETE_HIGHER_EDUCATION attributes


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
	GET_USER_PROFILE("GUP", ResponseType.JSON, "index.jsp"),
	GET_SCHOOLEDUCATION_DATA("GSD", ResponseType.JSON, "index.jsp"),
	ADD_SCHOOLEDUCATION_DATA("ASD", ResponseType.JSON, "index.jsp"),
	UPDATE_SCHOOLEDUCATION_DATA("USD", ResponseType.JSON, "index.jsp"),
	ADD_PROFESSIONAL_EXP("APE", ResponseType.JSON, "index.jsp"),
	UPDATE_PROFESSIONAL_EXP("UPE", ResponseType.JSON, "index.jsp"),
	DELETE_PROFESSIONAL_EXP("DPE", ResponseType.JSON, "index.jsp"),
	GET_TOWN_DETAILS("GTD", ResponseType.JSON, "index.jsp"),
	ADD_STUDENT_DETAILS("APD", ResponseType.JSON, "index.jsp"),
	GET_SKILL_DETAILS("GSL", ResponseType.JSON, "index.jsp"),
	ADD_STUDENT_SKILL("ASS", ResponseType.JSON, "index.jsp"),
	ADD_HIGHER_EDUCATION("AHE", ResponseType.JSON, "index.jsp"),
	UPDATE_HIGHER_EDUCATION("UHE", ResponseType.JSON, "index.jsp"),
	DELETE_HIGHER_EDUCATION("DHE", ResponseType.JSON, "index.jsp");
	
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