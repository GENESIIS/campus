package com.genesiis.campus.validation;

//20161028 CM c9-make-inquiry-for-institute INIT Validator.java
//20161028 CM c9-make-inquiry-for-institute Created validateEmail, validateInquiry, isNotEmpty methods
//20161028 CM c9-make-inquiry-for-institute Created validateInquiry() methods
//20161031 CM c9-make-inquiry-for-institute Modified validateInquiry() methods
//20161031 CM c9-make-inquiry-for-institute Renamed validateInquiry() methods as validateInstituteInquiry() method

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.genesiis.campus.util.IDataHelper;

public class Validator {
	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Check the given value is empty or not empty
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate is given string contains a null value.
	 * **/
	public static boolean isNotEmpty(String text) {
		boolean status = false;
		if ((text != null) && (text.isEmpty() == false)) {
			status = true;
		}
		return status;
	}

	/**
	 * Check the given mail address is valid email or not
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate email address.
	 * **/
	public static boolean validateEmail(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();

	}

	/**
	 * Validate institute course provider inquiry form data
	 * 
	 * @author Chathuri
	 * @param value
	 * @return String to validate is given strings contains a null value.
	 * **/
	public static String validateInstituteInquiry(IDataHelper helper)
			throws ParseException {
		String message = "True";

		if (!(isNotEmpty(helper.getParameter("fullname"))
				|| isNotEmpty(helper.getParameter("email"))
				|| isNotEmpty(helper.getParameter("inquiryTitle")) || isNotEmpty(helper
					.getParameter("inquiry")))) {
			message = SystemMessage.EMPTYFIELD.message();
		} else if (!validateEmail(helper.getParameter("email"))) {
			message = SystemMessage.EMAILERROR.message();
		}
		// } else if (!isInteger(helper.getParameter("countryCode"))) {
		// message = SystemMessage.INVALIDECODE.message();
		// } else if (!isInteger(helper.getParameter("areaCode"))) {
		// message = SystemMessage.INVALIDEAREA.message();
		// } else if (!isInteger(helper.getParameter("telNum"))) {
		// message = SystemMessage.PHONENUMBERERROR.message();
		// }
		return message;
	}

	/**
	 * Check the given number is valid number or not
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate is given string contains a numaric value.
	 * **/
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
}
