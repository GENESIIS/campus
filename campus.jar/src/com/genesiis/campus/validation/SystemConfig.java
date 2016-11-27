package com.genesiis.campus.validation;

//20161113 MM c5-corporate-training-landing-page-MP Initialised file
//20161118 MM c5-corporate-training-landing-page-MP Added fields value1, 
//				value2 and value3 to match fields in SystemConfig table
//20161127 MM c5-corporate-training-landing-page-MP Made value1, value2, 
//				value3 final.

/**
* SystemConfig enum is intended to be used for the purpose of identifying 
* the String to use to fetch a specific record from the SYSTEMCONFIG DB table. 
* 
* For example, if the path to the course provider logo directory is needed, 
* use the "name" of the enum constant COURSE_PROVIDER_LOGO_PATH in the 
* WHERE clause of the query (against column name SYSTEMCONFIGCODE in 
* SYSTEMCONFIG table) to select the value for that record.
* e.g.
* String providerLogoRecordId = SystemConfig.COURSE_PROVIDER_LOGO_PATH.name();
* String query =  "SELECT VALUE1 FROM SYSTEMCONFIG WHERE SYSTEMCONFIGCODE = " + providerLogoRecordId;
* 
* IMPORTANT: This design requires that names of the following 
* enum constants and the values provided for column SYSTEMCONFIGCODE 
* in the SYSTEMCONFIG table are in sync.
*
*/
public enum SystemConfig {
	
	PROVIDER_LOGO_PATH("education\\provider\\logo", "", ""),
	BANNER_PATH("education\\banner", "", ""),
	BANNER_LINKED_IMAGE_PATH("","",""),
	COURSE_IMAGE_PATH("","",""),
	STUDENT_PROFILE_IMAGE_PATH("","",""),
	TUTOR_PROFILE_IMAGE_PATH("","","");
	
	private final String value1;
	private final String value2;
	private final String value3;
	
	SystemConfig(String value1, String value2, String value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}

	public String getValue1() {
		return value1;
	}

	public String getValue2() {
		return value2;
	}

	public String getValue3() {
		return value3;
	}
}