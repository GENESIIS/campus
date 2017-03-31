package com.genesiis.campus.validation;

//20161024 DN c10-contacting-us created the initial Operation.java version
//20161026 DN c10-contacting-us-page add CONTACT_US_PUBLC to the class
//20161026 JH c7-list-higher-education-courses: added LIST_CATEGORY_LANDING_PAGE attribute
//20161028 PN c11-criteria-based-filter-search: added LIST_CATEGORY_DATA attribute
//20161029 PN c11-criteria-based-filter-search: added LIST_LEVEL_DATA,LIST_TOWN_DATA,LIST_MAJOR_DATA,LIST_DISTRICT_DATA attributes
//20161101 PN c11-criteria-based-filter-search: added LIST_INSTITUTE_DATA attribute.
//20161108 DN, JH, DJ, AS, CM, PN, MM Added field responseType to enum.
//20161109 DN c10-contacting-us-MP change the path of ContactUs.jsp page
//20161123 DN C18-student-signup-without-using-third-party-application-dn add SIGN_UP_WO_THRD_PARTY enum
// 20161205 DN C18-student-signup-without-using-third-party-application-dn DISPLAY_PREREQUISITE_DATA enume added
//20161206 DN C18-student-signup-without-using-third-party-application-dn DISPLAY_TOWN_DATA enume added
//20170102 DN CAM:47 add UPLOAD_EXISTING_PROFILE_PIC enum to the list
//20170106 DN CAM:47 add UPLOAD_SUBMITED_TUTOR_IMAGE enum to the list
//20161109 MM c5-corporate-training-landing-page-MP - Changed value for 
//				commandString of LIST_CATEGORY_PROGRAMMES enum
//20161111 PN c1-campus-landing-page modified ResponseType.JSP into ResponseType.JSON.
//20161116 JH c7-higher-education-landing-page: removed unwanted enums
//20161116 MM c2-integrate-google-banners Added LIST_GOOGLE_ADVERTS enum
//20161117 MM c2-integrate-google-banners Changed LIST_GOOGLE_ADVERTS to LOAD_BANNER_PAGE 
//20161217 MM c2-integrate-google-banners Removed LOAD_BANNER_PAGE constant (before 
//				merging with sprint branch) that was there for testing the banner 
//				displaying functionality 
//20161109 PN c11-criteria-based-filter-search changed ResponseType.JSP into ResponseType.JSON. 
//20161121 PN c27-upload-user-image: added UPLOAD_USER_PROFILE attribute to operation Enum.
//20161130 PN c27-upload-user-image: GET_USER_PROFILE enum values are changed.
//20161221 PN CAM-27: modified GET_USER_PROFILE enum values changed.
//20170203 DN CAM-131 modified DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA enum value
//20170209 DN CAM-131 UPLOAD_BANNER_SLOT_ON_BANNER_MANAGER_PAGE enum value is created.
//20170213 DN CAM-131 UPLOAD_BANNER_IMAGE_CREDENTIALS enum value is added.
//20170215 DN CAM-131 BANNER_CREDENTIALS enum value declared.
//            UPLOAD_BANNER_IMAGE_CREDENTIALS -->UPLOAD_BANNER_IMAGE_TO_TEMP_FOLDER

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
	UPLOAD_EXISTING_TUTOR_PROFILE_PIC("UETPP",ResponseType.JSON,""),
	UPLOAD_SUBMITED_TUTOR_IMAGE("USTIMG",ResponseType.JSON,""),
	LIST_INSTITUTE_DATA("LIST_INSTITUTE_DATA", ResponseType.JSP, "index.jsp"),
	ADD_BANNER_STAT("ADD_BANNER_STAT", ResponseType.JSON, ""),
	LIST_CATEGORY_PROGRAMMES("LIST_CATEGORY_PROGRAMMES", ResponseType.JSON, ""),
	LIST_CATEGORY_LANDING_PAGE("LIST_CATEGORY_LANDING_PAGE", ResponseType.JSP, "/dist/partials/category-landing-page.jsp"),
	UPLOAD_USER_PROFILE("UUP", ResponseType.JSON, "index.jsp"),
	DISPLAY_BANNER_MANAGER_ONLOAD_PAGE_DATA("DBPDOL",ResponseType.JSON,""),
	UPLOAD_BANNER_SLOT_ON_BANNER_MANAGER_PAGE("LSPBSLT",ResponseType.JSON,""),
	UPLOAD_BANNER_IMAGE_TO_TEMP_FOLDER("UBIMBA",ResponseType.JSON,""),
	UPLOAD_FULL_BANNER_CREDENTIALS("UFBCR",ResponseType.JSON,""), 
	GET_USER_PROFILE("GUP", ResponseType.JSON, "dist/partials/student/student-dashboard.jsp");
			
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

