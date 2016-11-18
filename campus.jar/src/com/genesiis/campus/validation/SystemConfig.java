package com.genesiis.campus.validation;

//20161113 MM c5-corporate-training-landing-page-MP Initialised file

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
	PROVIDER_LOGO_PATH,
	BANNER_PATH,
	BANNER_LINKED_IMAGE_PATH,
	COURSE_IMAGE_PATH,
	STUDENT_PROFILE_IMAGE_PATH,
	TUTOR_PROFILE_IMAGE_PATH;
}