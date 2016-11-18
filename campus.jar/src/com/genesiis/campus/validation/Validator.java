package com.genesiis.campus.validation;

//20161116 MM c5-corporate-training-landing-page-MP Added isNumber(CharSequence) method

import java.util.ArrayList;

import com.genesiis.campus.util.IDataHelper;

public class Validator {

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