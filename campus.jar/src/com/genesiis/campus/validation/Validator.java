package com.genesiis.campus.validation;

//20161028 CM c13-Display-course-details INIT Validator.java
//20161115 CM c13-Display-course-details added calculateYears(String duration),calculateMonths() ,calculateWeeks(),calculateDays() methods.
//20161201 CW c36-Display-course-details modified method exception log errors
//20161201 CW c36-Display-course-details removed un wanted comments
//20170106 CW c36-add-tutor-details Added isValidUserName() method
//20170108 CW c36-add-tutor-details Added isValidFirstname() method
//20170108 CW c36-add-tutor-details Added isValidLastname() method
//20170108 CW c36-add-tutor-details Added isValidMobileCountryCode() method
//20170108 CW c36-add-tutor-details Added isValidMobileNetworkCode() method
//20170108 CW c36-add-tutor-details Added isValidMobileNumber() method
//20170108 CW c36-add-tutor-details Added isValidLandCountryCode() method
//20170108 CW c36-add-tutor-details Added isValidLandAreaCode() method
//20170108 CW c36-add-tutor-details Added isValidLandNumber() method
//20170108 CW c36-add-tutor-details Added isValidAddressLine1() method

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.util.IDataHelper;

public class Validator {

	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Check the given value is empty or not empty
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate is given string contains a null value.
	 **/
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
	public int calculateYears(String duration) throws ArithmeticException, Exception {
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
			log.error("calculateYears:  arithmeticException" + arithmeticException.toString());
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
			log.error("calculateYears:  arithmeticException" + arithmeticException.toString());
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
			log.error("calculateYears:  arithmeticException" + arithmeticException.toString());
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
			log.error("calculateYears:  arithmeticException" + arithmeticException.toString());
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
	 **/
	public static boolean validateEmail(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})",
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
//gender, country, town, mobile, land, address line1,email, confirmpassword,  
		try {
			if (!(Validator.isNotEmpty(helper.getParameter("username"))
					|| (Validator.isNotEmpty(helper.getParameter("password")))
					|| (Validator.isNotEmpty(helper.getParameter("confirmPassword")))
					|| (Validator.isNotEmpty(helper.getParameter("firstname")))
					|| (Validator.isNotEmpty(helper.getParameter("lastname")))
					|| (Validator.isNotEmpty(helper.getParameter("email")))
					|| (Validator.isNotEmpty(helper.getParameter("mobileCountryCode")))
					|| (Validator.isNotEmpty(helper.getParameter("mobileNetworkCode")))
					|| (Validator.isNotEmpty(helper.getParameter("mobileNumber")))
					|| (Validator.isNotEmpty(helper.getParameter("landCountryCode")))
					|| (Validator.isNotEmpty(helper.getParameter("landAreaCode")))
					|| (Validator.isNotEmpty(helper.getParameter("landNumber")))
					|| (Validator.isNotEmpty(helper.getParameter("addressLine1"))))) {
				message = SystemMessage.EMPTYFIELD.message();
			} else if (!isValidUserName(helper.getParameter("username"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidPassword(helper.getParameter("password"), helper.getParameter("confirmPassword"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidFirstname(helper.getParameter("firstname"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidLastname(helper.getParameter("lastname"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!validateEmail(helper.getParameter("email"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidMobileCountryCode(helper.getParameter("mobileCountryCode"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidMobileNetworkCode(helper.getParameter("mobileNetworkCode"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidMobileNumber(helper.getParameter("mobileNumber"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidLandCountryCode(helper.getParameter("landCountryCode"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidLandAreaCode(helper.getParameter("landAreaCode"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidLandNumber(helper.getParameter("landNumber"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidAddressLine1(helper.getParameter("addressLine1"))) {
				message = SystemMessage.EMAILERROR.message();
			}

		} catch (Exception e) {
			log.error("validateTutorFields: Exception" + e.toString());
			throw e;
		}
		return message;
	}

	/**
	 * Check the entered username is a valid one
	 * 
	 * @author Chinthaka
	 * @param username
	 * @return boolean - Returns true if the requested username is a valid one
	 */
	public boolean isValidUserName(String userName) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered password is a valid one & is it same with confirmPassword value
	 * 
	 * @author Chinthaka
	 * @param password, confirmPassword
	 * @return boolean - Returns true if the requested password & confirmPassword are valid
	 */
	public boolean isValidPassword(String password, String confirmPassword) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered firstName is a valid one
	 * 
	 * @author Chinthaka
	 * @param firstName
	 * @return boolean - Returns true if the requested firstName is a valid one
	 */
	public boolean isValidFirstname(String firstName) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered lastName is a valid one
	 * 
	 * @author Chinthaka
	 * @param lastName
	 * @return boolean - Returns true if the requested lastName is a valid one
	 */
	public boolean isValidLastname(String lastName) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered mobileCountryCode is a valid one
	 * 
	 * @author Chinthaka
	 * @param mobileCountryCode
	 * @return boolean - Returns true if the requested mobileCountryCode is a valid one
	 */
	public boolean isValidMobileCountryCode(String mobileCountryCode) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered mobileNetworkCode is a valid one
	 * 
	 * @author Chinthaka
	 * @param mobileNetworkCode
	 * @return boolean - Returns true if the requested mobileNetworkCode is a valid one
	 */
	public boolean isValidMobileNetworkCode(String mobileNetworkCode) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered mobileNumber is a valid one
	 * 
	 * @author Chinthaka
	 * @param mobileNumber
	 * @return boolean - Returns true if the requested mobileNumber is a valid one
	 */
	public boolean isValidMobileNumber(String mobileNumber) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered landCountryCode is a valid one
	 * 
	 * @author Chinthaka
	 * @param landCountryCode
	 * @return boolean - Returns true if the requested landCountryCode is a valid one
	 */
	public boolean isValidLandCountryCode(String landCountryCode) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered landAreaCode is a valid one
	 * 
	 * @author Chinthaka
	 * @param landAreaCode
	 * @return boolean - Returns true if the requested landAreaCode is a valid one
	 */
	public boolean isValidLandAreaCode(String landAreaCode) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered landNumber is a valid one
	 * 
	 * @author Chinthaka
	 * @param landNumber
	 * @return boolean - Returns true if the requested landNumber is a valid one
	 */
	public boolean isValidLandNumber(String landNumber) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered addressLine1 is a valid one
	 * 
	 * @author Chinthaka
	 * @param addressLine1
	 * @return boolean - Returns true if the requested addressLine1 is a valid one
	 */
	public boolean isValidAddressLine1(String addressLine1) throws ArithmeticException, Exception {
		boolean valid = false;
		try {

			System.out.println("isValidUserName = ");
			if ((isNotEmpty(userName)) && (userName.length() > 6) && (userName.length() < 50)) {
				final TutorDAO tutorDAO = new TutorDAO();
				valid = tutorDAO.isAvailableUserName(userName);
			}

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
}
