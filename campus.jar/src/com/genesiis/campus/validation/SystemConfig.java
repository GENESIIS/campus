package com.genesiis.campus.validation;

//20170102 DN c47-tutor-add-tutor-information-upload-image-dn added value1 to TUTOR_PROFILE_IMAGE_PATH,TUTOR_PROFILE_IMAGE_SIZE
//20170102 DN c47-tutor-add-tutor-information-upload-image-dn added default tutor image path enum value TUTOR_DEFAULT_PROFILE_IMAGE_PATH
//20170105 DN c47-tutor-add-tutor-information-upload-image-dn TUTOR_PROFILE_IMAGE_ABSOLUTE_PATH,TUTOR_DEFAULT_PROFILE_IMAGE_WAR_PATH,TUTOR_DEFAULT_PROFILE_IMAGE_ABSOLUTE_PATH ADDED
//20170106 DN c47-tutor-add-tutor-information-upload-image-dn replaced "education.war" with "education" in enum values
/**
 * SystemConfig enum is intended to be used for the purpose of identifying the
 * String to use to fetch a specific record from the SYSTEMCONFIG DB table.
 * 
 * For example, if the path to the course provider logo directory is needed, use
 * the "name" of the enum constant COURSE_PROVIDER_LOGO_PATH in the WHERE clause
 * of the query (against column name SYSTEMCONFIGCODE in SYSTEMCONFIG table) to
 * select the value for that record. e.g. String providerLogoRecordId =
 * SystemConfig.COURSE_PROVIDER_LOGO_PATH.name(); String query =
 * "SELECT VALUE1 FROM SYSTEMCONFIG WHERE SYSTEMCONFIGCODE = " +
 * providerLogoRecordId;
 * 
 * IMPORTANT: This design requires that names of the following enum constants
 * and the values provided for column SYSTEMCONFIGCODE in the SYSTEMCONFIG table
 * are in sync.
 * 
 */
public enum SystemConfig {

	
	TUTOR_PROFILE_IMAGE_PATH("education/tutor/pro_image", "", ""),
	TUTOR_PROFILE_IMAGE_ABSOLUTE_PATH("C:/sdb/ctxdeploy/education.war/tutor/pro_image","",""),
	TUTOR_DEFAULT_PROFILE_IMAGE_WAR_PATH("education/tutor/default","",""),
	TUTOR_DEFAULT_PROFILE_IMAGE_ABSOLUTE_PATH("C:/sdb/ctxdeploy/education.war/tutor/default","",""),
	TUTOR_PROFILE_IMAGE_SIZE("2","",""),
	CATEGORY_LOGO_PATH("education/general/category/logo","","");

	private String value1;
	private String value2;
	private String value3;

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