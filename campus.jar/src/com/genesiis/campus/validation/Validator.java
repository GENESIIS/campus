package com.genesiis.campus.validation;

//20161102 JH c7-higher-education-landing-page Validator.java created
//20161102 JH c7-higher-education-landing-page getDuration() created
//20161115 JH c7-higher-education-landing-page isEmpty()method modified to support any string validation
//20161115 JH c7-higher-education-landing-page add method comments
//20161116 MM c5-corporate-training-landing-page-MP Added isNumber(CharSequence) method
//20161223 PN CAM-112: getSubDescription() method implement to substring the category description.

import java.util.ArrayList;

public class Validator {

	/**
	 * Programme durations are stored as days which is to be converted into years and months.
	 * getDuration method used to calculate the duration of a programme in years, months and days. 
	 * @param duration
	 * @return object 
	 * @author JH
	 */
	public Object getDuration(int duration) {
		ArrayList<String> programDuration = new ArrayList<String>();

		int years = duration / 365;
		duration = duration % 365;
		int month = duration / 30;
		duration = duration % 30;

		programDuration.add(String.valueOf(years));
		programDuration.add(String.valueOf(month));
		programDuration.add(String.valueOf(duration));

		return programDuration;
	}

	/**
	 * isEmpty method used to validate string parameters. 
	 * @param parameter
	 * @return boolean true, false value
	 */
	public boolean isEmpty(String parameter) {
		boolean valid = false;
		if (parameter == null) {
			valid = true;
		}

		return valid;
	}
	
	
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
	
	/**
	 * Substring the category description after 500 characters
	 * @param description
	 * @return sub-description
	 */
	public static String getSubDescription(String description){
		if(description.length() > 500){
			return description.substring(0, 500)+" ... ";
		}
		return description;
	}

	
}
