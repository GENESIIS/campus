package com.genesiis.campus.validation;

import java.util.HashMap;

//20170424 CW c158-send-email-tutor-employment-confirmation-cw Gender.java enum created
//20170504 CW c159-courseprovider-accept-tutor-request-cw add getGender(String genderValue), getGender(int genderValue), getGenderMap() methods

/**
* Gender enum created to manage all the Gender values
* of the application. 
* @author CW
*/
public enum Gender {
	MALE(1),
	FEMALE(2);
	
	private final int genderValue;

	Gender(final int genderValue){
		this.genderValue = genderValue;
	}

	/**
	 * @return the genderValue
	 */
	public int getGenderValue() {
		return genderValue;
	}	

	/**
	 * @return the PersonBasedOnGender return "his" or "her based on the gender value"
	 */
	public static String getPersonBasedOnGender(String gender) {
		String personBasedOnGender = null;
		if(!Validator.isEmptyOrHavingSpace(gender)){
			if(gender.equals("1")){
				personBasedOnGender = "his";
			}if(gender.equals("2")){
				personBasedOnGender = "her";
			}
		}
		return personBasedOnGender;
	}	
	
	/**
	 * Generic method for map the gender. Able to call from whole campus application
	 * @author CW
	 * @param genderValue selected status Value string
	 * @return int genderInt
	 * @throws Exception
	 */
	public static int getGender(String genderValue){
		int genderInt=0; 
		
		if (Validator.isNotEmpty(genderValue)) {			
			if (genderValue.equalsIgnoreCase("MALE")) {
				genderInt = MALE.getGenderValue();
			}
			if (genderValue.equalsIgnoreCase("FEMALE")) {
				genderInt = FEMALE.getGenderValue();
			}
		}
		
		return genderInt;
	}
	
	/**
	 * Generic method for map the gender int value with String. Purpose of gender view in front end. Able to call from whole campus application
	 * @author CW
	 * @param genderValue selected int gender Value 
	 * @return String genderStr
	 * @throws Exception
	 */
	public static String getGender(int genderValue){
		String genderStr="";				

		if(genderValue==MALE.genderValue){
			genderStr = "Male";
		}
		if(genderValue==FEMALE.genderValue){
			genderStr = "Female";
		}
		
		return genderStr;
	}
	
	/**
	 * Generic method to get the int value with String as key, value pairs in HashMap. 
	 * @author CW
	 * @return HashMap<Integer, String> genderMap
	 */
	public static HashMap<Integer, String> getGenderMap(){
		HashMap<Integer, String> genderMap = new HashMap<Integer, String>();
		
		for (Gender genderValues : Gender.values()) {
			int genderValueInt = Gender.getGender(genderValues.toString());
			genderMap.put(genderValueInt, Gender.getGender(genderValueInt));
		}
		return genderMap;
	}
}