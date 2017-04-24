package com.genesiis.campus.validation;

//20170424 CW c158-send-email-tutor-employment-confirmation-cw Gender.java enum created

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
}
