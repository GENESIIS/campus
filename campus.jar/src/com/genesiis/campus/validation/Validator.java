package com.genesiis.campus.validation;

//20161028 CM c13-Display-course-details INIT Validator.java
//20161115 CM c13-Display-course-details added calculateYears(String duration),calculateMonths() ,calculateWeeks(),calculateDays() methods.
//20161201 CW c36-Display-course-details modified method exception log errors
//20161201 CW c36-Display-course-detailsremoved un wanted comments

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

	int totalDays = 0;

	
	/**
	 * Returns the given time duration in years
	 * 
	 * @author Chathuri, Chinthaka
	 * 
	 * @return the given time duration in years
	 */
	public int calculateYears(String duration) throws ArithmeticException,
			Exception {
		int years = 0;
		try {

			float inputAsFloat = Float.parseFloat(duration);

			// the input is an integral day count, with a possible fractional
			// part representing time as a fraction of one day
			totalDays = (int) inputAsFloat;

			// ignores leap years
			years = (int) totalDays / 365;
			totalDays %= 365;

			// calculateMonth();

		} catch (ArithmeticException arithmeticException) {
			log.error("calculateYears:  arithmeticException"
					+ arithmeticException.toString());
			throw arithmeticException;
		} catch (Exception e) {
			log.error("calculateYears:  Exception" + e.toString());
			throw e;
		}
		return years;
	}

	/**
	 * Calculate number of months in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return int
	 */
	public int calculateMonths() throws ArithmeticException, Exception {
		int months = 0;
		try {

			// assumes all months have 30 days
			months = (int) totalDays / 30;
			totalDays %= 30;

		} catch (ArithmeticException arithmeticException) {
			log.error("calculateYears:  arithmeticException"
					+ arithmeticException.toString());
			throw arithmeticException;
		} catch (Exception e) {
			log.error("calculateYears: Exception" + e.toString());
			throw e;
		}
		return months;

	}

	/**
	 * Calculate number of weeks in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return int
	 */
	public int calculateWeeks() throws ArithmeticException, Exception {
		int weeks = 0;
		try {
			weeks = (int) totalDays / 7;
			totalDays %= 7;

		} catch (ArithmeticException arithmeticException) {
			log.error("calculateYears:  arithmeticException"
					+ arithmeticException.toString());
			throw arithmeticException;
		} catch (Exception e) {
			log.error("calculateYears:  Exception" + e.toString());
			throw e;
		}
		return weeks;
	}

	/**
	 * Calculate number of days in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return int
	 */
	public int calculateDates() throws ArithmeticException, Exception {
		int days = 0;
		try {
			days = (int) totalDays;

		} catch (ArithmeticException arithmeticException) {
			log.error("calculateYears:  arithmeticException"
					+ arithmeticException.toString());
			throw arithmeticException;
		} catch (Exception e) {
			log.error("calculateYears:  Exception" + e.toString());
			throw e;
		}
		return days;
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
	 * Validate Tutor fields before values go to database.
	 * 
	 * @author Chathuri
	 * @param helper
	 * @return String
	 * @throws Exception
	 */
	public String validateTutorFields(IDataHelper helper) throws Exception {
		
		String message = "True";

		try {
			if (!(Validator.isNotEmpty(helper.getParameter("username"))
					|| (Validator.isNotEmpty(helper.getParameter("password")))
					|| (Validator.isNotEmpty(helper.getParameter("firstname")))
					|| (Validator.isNotEmpty(helper.getParameter("lastname")))
					|| (Validator.isNotEmpty(helper.getParameter("email")))
					|| (Validator.isNotEmpty(helper
							.getParameter("mobileNumber"))) || (Validator
						.isNotEmpty(helper.getParameter("addressLine1"))))) {
				message = SystemMessage.EMPTYFIELD.message();
			} else if (!validateEmail(helper.getParameter("email"))) {
				message = SystemMessage.EMAILERROR.message();
			}

		} catch (Exception e) {
			log.error("validateTutorFields: Exception" + e.toString());
			throw e;
		}
		return message;
	}
}
