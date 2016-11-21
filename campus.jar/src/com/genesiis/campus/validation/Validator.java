package com.genesiis.campus.validation;

//20161028 CM c13-Display-course-details INIT Validator.java
//20161115 CM c13-Display-course-details added calculateYears(String duration),calculateMonths() ,calculateWeeks(),calculateDays() methods.


import org.apache.log4j.Logger;

public class Validator {

	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Calculate number of years in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
	 */
	int totalDays = 0;

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
			log.error("calculateYears:  e" + e.toString());
			throw e;
		}
		return years;
	}

	/**
	 * Calculate number of months in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
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
			log.error("calculateYears:  e" + e.toString());
			throw e;
		}
		return months;

	}

	/**
	 * Calculate number of weeks in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
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
			log.error("calculateYears:  e" + e.toString());
			throw e;
		}
		return weeks;
	}

	/**
	 * Calculate number of days in the Duration
	 * 
	 * @author Chathuri
	 * @param duration
	 * @return void
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
			log.error("calculateYears:  e" + e.toString());
			throw e;
		}
		return days;
	}
}
