package com.genesiis.campus.validation;

//20160811 PN x25-list-employees SystemMessage enum Created
//20161024 DN c10-contacting-us extracted to campus project
//20161101 DN c10-contacting-us SystemMessage() modified and added 
//		FAIL_REQUEST_SUBMISSION,PASS_REQUEST_SUBMISSION.
//20161116 DN DN c10-contacting-us-page-MP removed unnecessary enum values.
//20161122 PN c27-upload-user-image: added new attributes to the Enum
//20161124 PN c27-upload-user-image: added new attributes to the Enum FILEADDED, FILEUPDATED, FILEDELETED, FILEUPLOADED
//20170226 PN CAM-48: declared FILEDELETEFAILED attribute.

public enum SystemMessage {	
	ADDED("Details added successfully."), 
	UPDATED("Details updated successfully."), 
	DELETED("Details deleted successfully."),
	FILEADDED("File added successfully."), 
	FILEUPDATED("File updated successfully."), 
	FILEDELETED("File deleted successfully."),
	FILEUPLOADED("File uploaded successfully."),
	FILEDELETEFAILED("File delete faild."),
	IDFOUND("ID found."), 
	IDNOTFOUND("ID not found."), 
	ERROR("Unsuccessful."), 	
	UNKNOWN(""),
	EMPTYFIELD("Field is Empty."),
	SUCCESS("Successfull"),
	PHONENUMBERERROR("Invalid Telephone Number."),
	MOBILENUMBERERROR("Invalid Mobile Number."),
	EMAILERROR("Invalid Email."),
	INVALIDDATE("Invalid DateFormat."),
	EMPTYVALUES("Empty data Fields."),
	NOTADDED("Problem when inserting data."), 	
	NOTUPDATED("Problem when updating data."), 	
	MANDATORYFIELDREQUIRED("Fields marked with asterisk (*) are required"), 
	FAIL_REQUEST_SUBMISSION("Request submission is not successfull!"),
	PASS_REQUEST_SUBMISSION("Request successfully submitted! "),
	RECAPTCHAVERIFICATION("Please verify You're not a robot "),	
	MEDICALHISTORYEMPLOYEEID("Medical History Employee id not valid."),
	MEDICALHISTORYAILMENT("Medical History Ailment not valid."),
	MEDICALHISTORYDESCRIPTION("Medical History Description not valid"),
	MEDICALHISTORYPASS("pass"),
	MEDICALHISTORYSUCCESS("success"),
	MEDICALHISTORYNOID("No Medical History ID obtained."),		
	MEDICALREPORTDESCRIPTIONNOTVALID("Medical Report Description not valid."),
	MEDICALREPORTPATHNOTVALID("Medical Report path not valid."),
	MEDICALREPORTNOTVALID("Medical Report not valid"),
	INSERT_SUCCESS("Insert Successful"),
	INSERT_UNSUCCESSFULL("Insert Unsuccessful"),	
	UPDATE_SUCCESSFUL("UPDATED SUCCESSFULLY"),
	UPDATE_UNSUCCESSFUL("UPDATE UNSUCCESSFUL"),
	FUNCTION_FAILED("FF"),
	FAILED("F"),	
	PASSWORD_MISMATCH("PASSWORDS DO NOT MATCH"),
	EMAIL_SEND_FAIL("EMAIL WAS NOT SENT"),
	EMAIL_SEND_SUCCESS("EMAIL SENT"),	
	LOGIN_FAIL("INVALID LOGIN"),
	NOT_A_USER("USERNAME MAY NOT EXIST"),	
	EMPTY_FIELDS("PLEASE FILL IN ALL FIELDS"),
	FILE_SIZE_OVER_MAX("PLEASE UPLOAD AN IMAGE SMALLER THAN 750KB"),	
	EXISTS("EXISTS"),
	DOESNOTEXIST("DOES NOT EXIST"),	
	CANNOT_SUBSCRIBE("SORRY CANNOT SUBSCRIBE A.T.M"),
	COURSE_ALERT_SUBSCRIPTION_REACHED("CANNOT SUBSCRIBE TO MORE THAN 5."),	
	SUBSCRIBED("SUBSCRIBED"),
	HAS_NOT_SUBSCRIBED("HAS NOT SUBSCRIBED"),	
	HAS_SUBSCRIBED("YOU HAVE ALREADY SUBSCRIBED TO THIS PROFESSION"),
	INVALID_EMAIL_FORMAT("INVALID EMAIL"),	
	INVALID_LOGIN("INVALID LOGIN"),
	ACCOUNT_TEMP_DISABLED("YOUR ACCOUNT IS TEMPORARILY DISABLED"),	
	ACTIVATED("ACTIVATED"),
	DEACTIVATED("DEACTIVATED"),
	INVALID_SEARCH_STRING("PLEASE TRY AGAIN"),
	FILE_NOT_FOUND("FILE NOT FOUND"),
	EXTERNAL_DIRECTORY_MISSING("EXTERNAL DIRECTORY IS MISSING"),
	COURSE_PUBLISHED_FILE_NOT_FOUND("COURSE PUBLISHED. ADVERTISEMENT IMAGE WAS NOT SENT WITH THE MAIL"),
	COURSE_UPDATED_FILE_NOT_FOUND("COURSE UPDATED. ADVERTISEMENT IMAGE WAS NOT SENT WITH THE MAIL"),
	COURSE_PUBLISHED_MAIL_NOT_SENT("COURSE PUBLISHED. MAIL FAILED"),
	COURSE_UPDATED_MAIL_NOT_SENT("COURSE UPDATED. MAIL FAILED "), 
	SEARCH_FAILED("Sorry, but nothing matched your search criteria. "),
	PAGE_NOT_FOUND("PAGE NOT FOUND"),
	ONLY_WHOLE_NUMBERS("only whole numbers are allowed."),
	MISSING_PARAMETERS("MISSING PARAMETERS"),
	COURSE_UPDATED_DOESNT_EXIST("THE COURSE UPDATED, DOESN'T EXIST"),
	MISSING_KEYWORD("MISSING_KEYWORD"),
	EMPTY_SEARCH_RESULT("EMPTY_SEARCH_RESULT"),
	NOT_KEYED_IN("Please key in a value"),
	LENGTH_EXCEEDED("Please try a shorter length of characters"),
	INVALID_FILE_TYPE("Uploaded file type is invalid. Please upload a file with valid file type."),
	FILE_SIZE_EXCEEDED("Uploaded file is too Large. Please upload a smaller file."),
	IS_NOT_NUMERIC("Enter in a numeric value please"),
	INVALID_DURATION_FORMAT("Enter in a duration with format: 4y 3m 2d"),	
	SUCCESSFUL("successful"),
	FAILURE("failure"),
	ERR("err"),
	MISSING_DATA("missingData"),
	UNEXPECTED("unexpected"),
	INSERT_FAIL("insertFail"),
	UPDATE_FAIL("updateFail"),
	INACTIVE("inactive"),
	SELECT_FAIL("selectFail"),
	LIST_NOT_RETRIEVED("listNotRetrieved"),
	UPDATE_SUCCESS("updateSuccess"),
	INSERT_SUCCESSFUL("insertSuccess"),
	ENQUIRY_SENT("enquirySent"),
	ENQUIRY_FAILED("enquiryFailed"),
	INVALID_EMAIL("invalidEmail"),
	CANNOT_DEACTIVATE("cannotDeactivate"),
	DELETE_FAILED("deleteFailed"),
	FK_VIOLATION("fkViolation"),
	INVALID_DATE("invalidDate"),
	AUTO_COMPLETE_ERR("autoCompleteError"),
	REPORT_GENERATION_FAILED("reportGenerationFailed"),
	NOT_AVAILABLE("notAvailable"), 
	FILE_UPLOAD_FAILED("FILE UPLOAD FAILED"), 
	INVALID_INFORMATION("Invalid Information");

	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
