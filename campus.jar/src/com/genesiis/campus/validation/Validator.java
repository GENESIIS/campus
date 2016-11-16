package com.genesiis.campus.validation;

//20161102 JH c7-higher-education-landing-page Validator.java created
//20161102 JH c7-higher-education-landing-page getDuration() created
//20161116 MM c5-corporate-training-landing-page-MP Added isNumber(CharSequence) method

import java.util.ArrayList;

import com.genesiis.campus.util.IDataHelper;

public class Validator {

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

	public boolean isEmpty(IDataHelper helper) {
		boolean valid = false;
		if (helper.getParameter("categoryId") == null) {
			valid = true;
		}

		return valid;
	}

	public static boolean isInteger(String numString) {

		return false;
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
}