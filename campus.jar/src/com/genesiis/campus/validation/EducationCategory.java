package com.genesiis.campus.validation;

import java.util.ArrayList;
import java.util.Collection;

//20161114 MM c5-corporate-training-landing-page-MP Initialised file
//20161115 MM c5-corporate-training-landing-page-MP Added PRE_EDUCATION constant

//20170302 PN CAM-137: init CategoryList enum class to return categories associated with the landing page, if an error thrown when accessing the DB. 
//			           implemented getEnumAsCollection(String code) method to select an enum value from the given code and returns the attribute set as a collection.
//					   modified Enum by adding category string value into it.
//20170403 PN CAM-137: set category string value to the collection, when it choose values according to the give category code.

/**
* EducationCategory enum is for the purpose of making the connection between 
* a conceptual category type and the specific record in the DB table 
* CATEGORY that is related to that conceptual category type. The "name"
* of an enum constant below will appear as the value in the column 
* CATEGORYIDENTIFIERSTRING in the CATEGORY table for the specific 
* record in that table that corresponds to this enum. 
* 
* This is to be used when certain actions must be taken in the code 
* based on a specific category type. For example, for Corporate Training
* category type, the list of Majors must be shown as filters whereas 
* for other types (such as School Education, Higher Education 
* etc.) it is the list of Levels that should be shown. 
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
* represents a conceptual category type) stored as part of that record, 
* as the value for the column named CATEGORYIDENTIFIERSTRING.  
* 
* IMPORTANT: This design requires that the following 
* enum constants and records in the CATEGORY table are in sync.
*
*/

public enum EducationCategory {
	
	PRE_EDUCATION("1","School Education","Education in Sri Lanka has a long history that dates back two millennia. The Constitution of Sri Lanka provides for education as a fundamental right. Sri Lanka's population had an adult literacy rate of 92.63% in 2015, which is above average by world and regional standards.[note 1] Education plays a major part in the life and culture of the country and dates back to 543 BC. Sri Lanka's modern educational system was brought about by its integration into the British Empire in the 19th century. Education currently falls under the control of both the Central Government and the Provincial Councils, with some responsibilities lying with the Central Government and the Provincial Council having autonomy for others.","SCHOOL_EDUCATION"),
	SCHOOL_EDUCATION("2","Higher Education","Higher Education Description","HIGHER_EDUCATION"),
	HIGHER_EDUCATION("3","Corporate Training","Corporate Training Education Description","CORPORATE_TRAINING"),
	CORPORATE_TRAINING("4","Pre Education","Pre Education Description","PRE_EDUCATION");
	
	private final String code;
	private final String name;
	private final String description;
	private final String categoryString;
	
	private EducationCategory(String code, String name, String description, String categoryString) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.categoryString = categoryString;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public String getCategoryString() {
		return categoryString;
	}

	/**
	 * CategoryList emun values will convert into Collection<Collection<String>> and returns
	 * @return
	 */
	public static Collection<Collection<String>> getEnumAsCollection(){
		final Collection<Collection<String>> allCategoryList = new ArrayList<Collection<String>>();
		for(EducationCategory list: EducationCategory.values()){
			final ArrayList<String> singleCategoryList = new ArrayList<String>();
			singleCategoryList.add(list.getCode());
			singleCategoryList.add(list.getName());
			singleCategoryList.add(list.getDescription());
			singleCategoryList.add(list.getCategoryString());
			allCategoryList.add(singleCategoryList);
		}		
		return allCategoryList;
	}
	
	/**
	 * Select an enum value from the given code and returns the attribute set as a collection.
	 * @param code
	 * @return
	 */
	public static Collection<Collection<String>> getEnumAsCollection(String code){
		final Collection<Collection<String>> allCategoryList = new ArrayList<Collection<String>>();
		for(EducationCategory list: EducationCategory.values()){
			if(list.getCode().trim().equalsIgnoreCase(code)){
				final ArrayList<String> singleCategoryList = new ArrayList<String>();
				singleCategoryList.add(list.getCode());
				singleCategoryList.add(list.getName());
				singleCategoryList.add(list.getDescription());
				singleCategoryList.add(list.getCategoryString());
				allCategoryList.add(singleCategoryList);
			}		
		}		
		return allCategoryList;
	}
}
