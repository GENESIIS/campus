package com.genesiis.campus.validation;

//20161102 JH c7-higher-education-landing-page Validator.java created
//20161102 JH c7-higher-education-landing-page getDuration() created
//20161116 MM c5-corporate-training-landing-page-MP Added isNumber(CharSequence) method
//20170105 MM c25-student-dashboard-MP - Added JavaDoc for isNumber and isEmpty() methods and removed unused methods. 

public class Validator {

	/**
	 * Checks if a string passed into the method represents a number.
	 * 
	 * @param cs, of type CharSequence that represents the string to check if it represents a number.	  
	 * @return A boolean primitive that indicates whether the string that was passed is in fact a number. 
	 */
	public static boolean isNumber(final CharSequence cs) {
		if (cs == null || cs.toString().isEmpty()) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}