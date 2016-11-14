package com.genesiis.campus.validation;

//20161114 MM c5-corporate-training-landing-page-MP Initialised file

/**
* EducationCategory enum is for the purpose of making the connection between 
* a conceptual category type and the specific record in the DB table 
* CATEGORY that is related to that conceptual category type. The "name"
* of an enum constant below will appear as the value in the column 
* CATEGORYIDENTIFIERSTRING in the CATEGORY table for the specific 
* record in that table that corresponds to this enum. 
* 
* This is to be used when certain things need to happen based on a 
* certain category type in the code. For example, for Corporate Training
* category type, the list of Majors must be shown as filters whereas 
* for other category types (such as School Education, Higher Education 
* etc.) its the list of Levels that should be shown. 
* 
* Without this enum, identifying the particular record in CATEGORY table 
* that corresponds to the conceptual Corporate Training type is impossible 
* with the current design. Value for CODE column in CATEGORY table is 
* inappropriate to be used directly in code because then the programme 
* would expect the value for the auto-incrementing CODE column of the 
* particular CATEGORY record that represents the conceptual Corporate Training 
* category to have the exact value as hard-coded in programme logic. 
* 
* This enum is expected to provide the link between a conceptual category 
* type and the record in the CATEGORY table that corresponds to that type 
* by having the "name" of the particular enum constant (that each 
* represents a conceptual category type) stored against that record, 
* as the value for the column named CATEGORYIDENTIFIERSTRING.  
* 
* IMPORTANT: This design requires that the following 
* enum constants and records in the CATEGORY table are in sync.
*
*/

public enum EducationCategory {
	
	SCHOOL_EDUCATION,
	HIGHER_EDUCATION,
	CORPORATE_TRAINING;

}
