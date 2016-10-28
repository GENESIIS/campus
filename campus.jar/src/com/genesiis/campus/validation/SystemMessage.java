package com.genesiis.campus.validation;


public enum SystemMessage {	
	ADDED("Details added successfully."), 
	UPDATED("Details updated successfully."), 
	DELETED("Details deleted successfully."), 
	IDFOUND("ID found."), 
	IDNOTFOUND("ID not found."), 
	ERROR("Unsuccessful."), 	
	UNKNOWN(""),
	EMPTYFIELD("Field is Empty."),
	SUCCESS("Successfull"),
	INQUIRYSENT("Inquiry sent Successfully"),
	FAILED_TO_CREATE("Object not created"),
	NICERROR("Invalid NIC Number."),
	PHONENUMBERERROR("Invalid Telephone Number."),
	MOBILENUMBERERROR("Invalid Mobile Number."),
	EMAILERROR("Invalid Email."),
	INVALIDDATE("Invalid DateFormat."),
	EMPTYVALUES("Empty data Fields."),
	INVALIDBIRTDAY("Invalid Birthdate."),
	INVALIDENDDATE("Invalid End date."),
	INVALIDAMOUNT("Invalid Amount."),
	NOTADDED("Problem when inserting data."), 	
	NOTUPDATED("Problem when updating data."), 	
	MANDATORYFIELDREQUIRED("Fields marked with asterisk (*) are required"), 	
	MEDICALHISTORYEMPLOYEEID("Medical History Employee id not valid."),
	MEDICALHISTORYAILMENT("Medical History Ailment not valid."),
	MEDICALHISTORYDESCRIPTION("Medical History Description not valid"),
	MEDICALHISTORYPASS("pass"),
	MEDICALHISTORYSUCCESS("success"),
	MEDICALHISTORYNOID("No Medical History ID obtained."),		
	MEDICALREPORTDESCRIPTIONNOTVALID("Medical Report Description not valid."),
	MEDICALREPORTPATHNOTVALID("Medical Report path not valid."),
	MEDICALREPORTNOTVALID("Medical Report not valid");

	
	private String message;

	SystemMessage(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}
	
}
