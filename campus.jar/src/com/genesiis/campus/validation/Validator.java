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
//20170109 CW c36-add-tutor-details modified isValidUserName() method
//20170109 CW c36-add-tutor-details modified isValidFirstname() method
//20170109 CW c36-add-tutor-details modified isValidLastname() method
//20170109 CW c36-add-tutor-details modified isValidMobileCountryCode() method
//20170109 CW c36-add-tutor-details modified isValidMobileNetworkCode() method
//20170109 CW c36-add-tutor-details modified isValidMobileNumber() method
//20170109 CW c36-add-tutor-details modified isValidLandCountryCode() method
//20170109 CW c36-add-tutor-details modified isValidLandAreaCode() method
//20170109 CW c36-add-tutor-details modified isValidLandNumber() method
//20170109 CW c36-add-tutor-details modified isValidAddressLine1() method
//20170109 CW c36-add-tutor-details modified validateTutorFields() method
//20170109 CW c36-add-tutor-details added isValidUserNameLength() method
//20170111 CW c36-add-tutor-details modified validateTutorFields() method, isValidURL(), isValidWhatsappViber(), isValidUserNameLength() methods added
//20170117 CW c36-add-tutor-details added validateEmailAvailability() method


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.entity.TutorUserNameDAO;
import com.genesiis.campus.entity.model.Tutor;
import com.genesiis.campus.util.IDataHelper;

public class Validator {

	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Check the given value is empty or not empty
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean returns true if the text contains some value.
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
	 * Validate Tutor fields before values go to database.
	 * 
	 * @author Chathuri, Chinthaka
	 * @param helper
	 * @return String
	 * @throws Exception
	 */
	public String validateTutorFields(IDataHelper helper) throws Exception {

		String message = "True"; 
		try {		
			if (!((Validator.isNotEmpty(helper.getParameter("firstname")))
					&& (Validator.isNotEmpty(helper.getParameter("lastname")))
					&& ((Validator.isNotEmpty(helper.getParameter("mobileCountryCode"))) || (!((helper.getParameter("countryDetails")).equals("0"))))
					&& (Validator.isNotEmpty(helper.getParameter("mobileCountryCode")))
					&& (Validator.isNotEmpty(helper.getParameter("mobileNetworkCode")))
					&& (Validator.isNotEmpty(helper.getParameter("mobileNumber")))
					&& (Validator.isNotEmpty(helper.getParameter("landCountryCode")))
					&& (Validator.isNotEmpty(helper.getParameter("landAreaCode")))
					&& (Validator.isNotEmpty(helper.getParameter("landNumber")))
					&& (Validator.isNotEmpty(helper.getParameter("address1")))
					&& (Validator.isNotEmpty(helper.getParameter("email")))
					&& (Validator.isNotEmpty(helper.getParameter("username")))
					&& (Validator.isNotEmpty(helper.getParameter("password")))
					&& (Validator.isNotEmpty(helper.getParameter("confirmPassword"))))) {
				message = SystemMessage.EMPTYFIELD.message();
			} else if (!isValidFirstname(helper.getParameter("firstname"))) {
				message = SystemMessage.FIRSTNAMEERROR.message();
			} else if (!isValidLastname(helper.getParameter("lastname"))) {
				message = SystemMessage.LASTNAMEERROR.message();
			} else if (!isValidCountryCode(helper.getParameter("mobileCountryCode"))) {
				message = SystemMessage.MOBILECOUNTRYCODEERROR.message();
			} else if (!isValidNetworkCode(helper.getParameter("mobileNetworkCode"))) {
				message = SystemMessage.NETWORKCODEERROR.message();
			} else if (!isValidContactNumber(helper.getParameter("mobileNumber"))) {
				message = SystemMessage.MOBILENUMBERERROR.message();
			} else if (!isValidCountryCode(helper.getParameter("landCountryCode"))) {
				message = SystemMessage.LANDCOUNTRYCODEERROR.message();
			} else if (!isValidNetworkCode(helper.getParameter("landAreaCode"))) {
				message = SystemMessage.LANDAREACODEERROR.message();
			} else if (!isValidContactNumber(helper.getParameter("landNumber"))) {
				message = SystemMessage.LANDNUMBERERROR.message();
			} else if (!isValidAddressLine1(helper.getParameter("address1"))) {
				message = SystemMessage.ADDRESSLINE1ERROR.message();
			} else if (!isValidURL(helper.getParameter("weblink"))) {
				message = SystemMessage.WEBLINKERROR.message();
			} else if (!isValidURL(helper.getParameter("facebook"))) {
				message = SystemMessage.FACEBOOKERROR.message();
			} else if (!isValidURL(helper.getParameter("linkedin"))) {
				message = SystemMessage.LINKEDINERROR.message();
			} else if (!isValidURL(helper.getParameter("twitter"))) {
				message = SystemMessage.TWITTERERROR.message();
			} else if (!isValidURL(helper.getParameter("instagram"))) {
				message = SystemMessage.INSTAGRAMERROR.message();
			} else if (!isValidURL(helper.getParameter("myspace"))) {
				message = SystemMessage.MYSPACEERROR.message();
			} else if (!isValidWhatsappViber(helper.getParameter("whatsapp"))) {
				message = SystemMessage.WHATSAPPERROR.message();
			} else if (!isValidWhatsappViber(helper.getParameter("viber"))) {
				message = SystemMessage.VIBERERROR.message();
			} else if (!validateEmailAvailability(helper.getParameter("email"))) {
				message = SystemMessage.EMAIL_USED.message();
			}  else if (!validateEmail(helper.getParameter("email"))) {
				message = SystemMessage.EMAILERROR.message();
			} else if (!isValidUserName(helper)) {
				message = SystemMessage.USERNAME_EXIST.message();
			} else if (!isValidUserNameLength(helper.getParameter("username"))) {
				message = SystemMessage.USERNAME_LENGTH.message();
			} else if (!isValidPassword(helper.getParameter("password"), helper.getParameter("confirmPassword"))) {
				message = SystemMessage.PASSWORDERROR.message();
			} 

		} catch (Exception e) {
			log.error("validateTutorFields: Exception" + e.toString());
			throw e;
		}
		return message;
	}	
	
	/**
	 * Check the entered firstName is a valid one
	 * 
	 * @author Chinthaka
	 * @param firstName
	 * @return boolean - Returns true if the requested firstName is a valid one
	 */
	public boolean isValidFirstname(String firstName) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(firstName)) && (firstName.length() < 21)) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidFirstname:  Exception" + e.toString());
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
	public boolean isValidLastname(String lastName) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(lastName)) && (lastName.length() < 21)) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidLastname:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered countryCode is a valid one
	 * 
	 * @author Chinthaka
	 * @param countryCode
	 * @return boolean - Returns true if the requested countryCode is a valid one
	 */
	public boolean isValidCountryCode(String countryCode) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(countryCode)) && (countryCode.length() < 6)) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidCountryCode:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered networkCode is a valid one
	 * 
	 * @author Chinthaka
	 * @param networkCode
	 * @return boolean - Returns true if the requested networkCode is a valid one
	 */
	public boolean isValidNetworkCode(String networkCode) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(networkCode)) && (networkCode.length() < 11)) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidNetworkCode:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered contactNumber is a valid one
	 * 
	 * @author Chinthaka
	 * @param contactNumber
	 * @return boolean - Returns true if the requested contactNumber is a valid one
	 */
	public boolean isValidContactNumber(String contactNumber) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(contactNumber)) &&  (contactNumber.length() < 11)) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidContactNumber:  Exception" + e.toString());
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
	public boolean isValidAddressLine1(String addressLine1) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(addressLine1)) && (addressLine1.length() < 31)) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidAddressLine1:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}

	/**
	 * Check the entered url is a valid one
	 * 
	 * @author Chinthaka
	 * @param url
	 * @return boolean - Returns true if the requested url is a valid one
	 */
	public boolean isValidURL(String url) {  

	    URL u = null;
	    
	    if ((isNotEmpty(url)) && !(url.equals("-"))){
		    try {  
		        u = new URL(url);  
		    } catch (MalformedURLException e) {  
		        return false;  
		    }
	
		    try {  
		        u.toURI();  
		    } catch (URISyntaxException e) {  
		        return false;  
		    }  
	    }
	    return true;  
	}
	
	/**
	 * Check the entered viber or whatsapp numbers are valid
	 * 
	 * @author Chinthaka
	 * @param number
	 * @return boolean - Returns true if the requested number is a valid one
	 */
	public boolean isValidWhatsappViber(String number) throws Exception {
		boolean valid = false;
		
		if(!(isNotEmpty(number))){
			return true;
		}
		
		try{
			if((isNotEmpty(number))){
				double newNumber = Double.parseDouble(number);
			}
		} catch (Exception e){
			return false;
		}		
		
		if ((isNotEmpty(number)) && (number.length() < 21)) {
			valid = true;
		}
		return valid;
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
	 * Check the entered email is already entered one
	 * 
	 * @author Chinthaka
	 * @param email
	 * @return boolean - Returns true if the requested email is a is not used to create tutor account
	 */	
	public boolean validateEmailAvailability(String email) throws Exception{
		try {

			Collection<Collection<String>> tutorCollection= new ArrayList<Collection<String>>();
	
			if (Validator.isNotEmpty(email)){
				tutorCollection = new TutorDAO().getAll();		
			}
			
			for(Collection<String> tutorList : tutorCollection){
				if(tutorList.toArray()[7].equals(email)){
					return false;
				}			
			}
			
		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}		
		return true;
	}	
	
	/**
	 * Check the entered username is a valid one
	 * 
	 * @author Chinthaka
	 * @param username
	 * @return boolean - Returns true if the requested username is a valid one
	 */
	public boolean isValidUserName(IDataHelper helper) throws Exception {
		boolean valid = false;
		try {

			Collection<Collection<String>> tutorCollection= new ArrayList<Collection<String>>();
	
			if (Validator.isNotEmpty(helper.getParameter("username"))){
				final Tutor tutor = new Tutor();
				tutor.setUsername(helper.getParameter("username"));
				tutorCollection = new TutorUserNameDAO().findById(tutor);		
			}
			
			if (tutorCollection.isEmpty()) {
				valid = true; // user name does not exist
			} else {
				valid = false; // user name Already exists
			}
			

		} catch (Exception e) {
			log.error("isValidUserName:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered user name is having a valid size
	 * user name should have at least 6 characters & should be not more than 20 characters
	 * @author Chinthaka
	 * @param username
	 * @return boolean - Returns true if the requested username is having valid lengths
	 */
	public boolean isValidUserNameLength(String username) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(username)) && (username.length() > 5) && (username.length() < 21)) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidUserNameLength:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
	
	/**
	 * Check the entered password is a valid one & is it same with confirmPassword value
	 * 
	 * @author Chinthaka
	 * @param password, confirmPassword
	 * @return boolean - Returns true if the requested password & confirmPassword are same & valid in lengths
	 */
	public boolean isValidPassword(String password, String confirmPassword) throws Exception {
		boolean valid = false;
		try {

			if ((isNotEmpty(password)) && (isNotEmpty(confirmPassword)) && (password.length() > 5) && (password.length() < 21) && (password.equals(confirmPassword))) {
				valid = true;
			}

		} catch (Exception e) {
			log.error("isValidPassword:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
}
