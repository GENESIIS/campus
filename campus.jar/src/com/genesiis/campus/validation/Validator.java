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
//20170125 CW c36-add-tutor-details modify isValidUserNameLength().
//20170125 CW c36-add-tutor-details removed validateEmailAvailability(), isValidUserName() methods.
//20170125 CW c36-add-tutor-details added validateForNull() method
//20170126 CW c36-add-tutor-details changed the name of validateForNull() method to isHavingNullValues() & modified validateTutorFields
//20170130 CW c36-add-tutor-details modified import statements & removed un-used methods
//20170131 CW c36-add-tutor-details add validatePassword() method & modified validateTutorFields() method
//20170130 CW c36-add-tutor-details modified validateTutorFields() method
//20170203 CW c36-add-tutor-details modified isHavingNullValues(), validateTutorFields() methods
//20170206 CW c36-add-tutor-details cleaning the code by removing commented lines.
//20170220 CW c36-add-tutor-details modified isHavingNullValues(), validateTutorFields(), changed the name of validatePassword() to isValidPassword() & Add isValidUserAndEmailBeforeAddTutor()
//20170220 CW c36-add-tutor-details add isNotHavingSpace().
//20170221 CW c36-add-tutor-details modified isNotHavingSpace method name to isEmptyOrHavingSpace() & used to validate for null values & spaces.
//20170221 CW c36-add-tutor-details modified isValidUserAndEmailBeforeAddTutor method to use isEmptyOrHavingSpace method
//20170221 CW c36-add-tutor-details modified variable message into valid in isValidUserAndEmailBeforeAddTutor, isValidPassword methods
//20170222 CW c36-add-tutor-details modified isValidUserAndEmailBeforeAddTutor method to validate errors in both email & username
//20170222 CW c36-add-tutor-details modified validateTutorFields() to validate townDetails correctly
//20170222 CW c36-add-tutor-details modified isValidUserAndEmailBeforeAddTutor to give proper email & username error messages
//20170228 CW c36-add-tutor-details modified validateTutorFields() & removed calling isValidUserAndEmailBeforeAddTutor() & removed isValidUserAndEmailBeforeAddTutor()& validateUsernameEmailFields() methods
//20170314 CW c36-add-tutor-details modified validateEmail method & copied regular expression from CAM-10
				// removed un wanted spaces & cleaned the code
				// modified isEmptyOrHavingSpace method & changed validation for space checking
//20170315 CW c36-add-tutor-details add validateEmail method from CAM-10 & removed existing one
//20170315 CW c36-add-tutor-details modified validateEmail method to validate emails properly

import com.genesiis.campus.util.IDataHelper;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	static Logger log = Logger.getLogger(Validator.class.getName());
	int totalDays = 0;

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
	
	/**
	 * Check the given value is not having only a space
	 * @author Chinthaka
	 * @param value
	 * @return boolean returns true if the text is empty or not contains a space value.
	 **/
	public static boolean isEmptyOrHavingSpace(String text) {
		boolean status = false;
		
		if ((text == null) || (text.isEmpty() == true) || (text.matches("^\\s*$"))) {
			status = true;
		}
		
		return status;
	}

	/**
	 * Validate helper fields for null values.
	 * 
	 * @author Chathuri, Chinthaka
	 * @param helper
	 * @return boolean : Returns true if helper having any null values for required fields
	 * @throws Exception
	 */
	public boolean isHavingNullValues(IDataHelper helper) throws Exception {

		boolean isHavingNull = false; 
		try {	
			
			if(isEmptyOrHavingSpace(helper.getParameter("firstname"))) {
				helper.setAttribute("firstname", "");
				helper.setAttribute("firstNameError", SystemMessage.EMPTYFIRSTNAME.message());
				isHavingNull = true; 
			}
			
			if(isEmptyOrHavingSpace(helper.getParameter("lastname"))){
				helper.setAttribute("lastNameError", SystemMessage.EMPTYLASTNAME.message());
				isHavingNull = true; 
			}

			if(isEmptyOrHavingSpace(helper.getParameter("mobileCountryCode"))){
				helper.setAttribute("mobileError", SystemMessage.EMPTYMOBILECOUNTRYCODE.message());
				isHavingNull = true; 
			}

			if(isEmptyOrHavingSpace(helper.getParameter("mobileNetworkCode"))){
				helper.setAttribute("mobileNetworkError", SystemMessage.EMPTYMOBILENETWORKCODE.message());
				isHavingNull = true; 
			}

			if(isEmptyOrHavingSpace(helper.getParameter("mobileNumber"))){
				helper.setAttribute("mobileNumberError", SystemMessage.EMPTYMOBILENUMBER.message());
				isHavingNull = true; 
			}
			
			if(isEmptyOrHavingSpace(helper.getParameter("landNumber"))){
				helper.setAttribute("landNumberError", SystemMessage.EMPTYLANDNUMBER.message());
				isHavingNull = true; 
			}

			if(isEmptyOrHavingSpace(helper.getParameter("landAreaCode"))){
				helper.setAttribute("landAreaCodeError", SystemMessage.EMPTYLANDAREACODE.message());
				isHavingNull = true; 
			}

			if(isEmptyOrHavingSpace(helper.getParameter("address1"))){
				helper.setAttribute("address1Error", SystemMessage.EMPTYADDRESS1.message());
				isHavingNull = true; 
			}
			
		} catch (Exception e) {
			log.error("isHavingNullValues() : Exception " + e.toString());
			throw e;
		}
		return isHavingNull;
	}	

	/**
	 * Validate Tutor fields before values go to database.
	 * 
	 * @author Chathuri, Chinthaka
	 * @param helper
	 * @return boolean : Returns true if tutor fields are entered correctly
	 * @throws Exception
	 */
	public boolean validateTutorFields(IDataHelper helper) throws Exception {
		
		boolean isValid = true; 
		
		try {	
			if (isHavingNullValues(helper)) {
				isValid = false;
			} 
			
			if (!isValidFirstname(helper.getParameter("firstname"))) {
				helper.setAttribute("firstNameError", SystemMessage.FIRSTNAMEERROR.message());
				isValid = false;
			}
			if (!isValidLastname(helper.getParameter("lastname"))) {
				helper.setAttribute("lastNameError", SystemMessage.LASTNAMEERROR.message());
				isValid = false;
			}
			if (!isValidCountryCode(helper.getParameter("mobileCountryCode"))) {
				helper.setAttribute("mobileError", SystemMessage.MOBILECOUNTRYCODEERROR.message());
				helper.setAttribute("countryError", SystemMessage.COUNTRYCODEERROR.message());
				isValid = false;
			}
			if (!isValidNetworkCode(helper.getParameter("mobileNetworkCode"))) {
				helper.setAttribute("mobileNetworkError", SystemMessage.NETWORKCODEERROR.message());
				isValid = false;
			}
			if (!isValidContactNumber(helper.getParameter("mobileNumber"))) {
				helper.setAttribute("mobileNumberError", SystemMessage.MOBILENUMBERERROR.message());
				isValid = false;
			}
			if (!isValidCountryCode(helper.getParameter("landCountryCode"))) {
				helper.setAttribute("landError", SystemMessage.LANDCOUNTRYCODEERROR.message());
				isValid = false;
			}
			if (!isValidNetworkCode(helper.getParameter("landAreaCode"))) {
				helper.setAttribute("landAreaCodeError", SystemMessage.LANDAREACODEERROR.message());
				isValid = false;
			}
			if (!isValidContactNumber(helper.getParameter("landNumber"))) {
				helper.setAttribute("landNumberError", SystemMessage.LANDNUMBERERROR.message());
				isValid = false;
			}

			if(!Validator.isNotEmpty(helper.getParameter("townHidden"))){ // true if town hidden is empty
				if(!Validator.isNotEmpty(helper.getParameter("townDetails")) || helper.getParameter("townDetails").equals("0")){
					helper.setAttribute("townError", SystemMessage.TOWNCODEERROR.message());
					isValid = false;
				}
			}
			
			if (!isValidAddressLine1(helper.getParameter("address1"))) {
				helper.setAttribute("address1Error", SystemMessage.ADDRESSLINE1ERROR.message());
				isValid = false;
			}
			if (!isValidURL(helper.getParameter("weblink"))) {
				helper.setAttribute("weblinkError", SystemMessage.WEBLINKERROR.message());
				isValid = false;
			}
			if (!isValidURL(helper.getParameter("facebook"))) {
				helper.setAttribute("facebookError", SystemMessage.FACEBOOKERROR.message());
				isValid = false;
			}
			if (!isValidURL(helper.getParameter("linkedin"))) {
				helper.setAttribute("linkedInError", SystemMessage.LINKEDINERROR.message());
				isValid = false;
			}
			if (!isValidURL(helper.getParameter("twitter"))) {
				helper.setAttribute("twitterError", SystemMessage.TWITTERERROR.message());
				isValid = false;
			}
			if (!isValidURL(helper.getParameter("instagram"))) {
				helper.setAttribute("instagramError", SystemMessage.INSTAGRAMERROR.message());
				isValid = false;
			}
			if (!isValidURL(helper.getParameter("myspace"))) {
				helper.setAttribute("mySpaceError", SystemMessage.MYSPACEERROR.message());
				isValid = false;
			}
			if (!isValidWhatsappViber(helper.getParameter("whatsapp"))) {
				helper.setAttribute("whatsappError", SystemMessage.WHATSAPPERROR.message());
				isValid = false;
			}
			if (!isValidWhatsappViber(helper.getParameter("viber"))) {
				helper.setAttribute("viberError", SystemMessage.VIBERERROR.message());
				isValid = false;
			}
			
			if (!isValidPassword(helper.getParameter("password"), helper.getParameter("confirmPassword"), helper)) {
				isValid = false;
			}
						
		} catch (Exception e) {
			log.error("validateTutorFields: Exception" + e.toString());
			throw e;
		}
		return isValid;
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
			if ((isNotEmpty(firstName)) && (firstName.length() < 21) && firstName != " ") {
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

			if ((isNotEmpty(lastName)) && (lastName.length() < 21) && lastName != " ") {
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

			if ((isNotEmpty(countryCode)) && (countryCode.length() < 6) && !(countryCode.equals("0"))) {
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

			if ((isNotEmpty(networkCode)) && (networkCode.length() < 11) && networkCode != " ") {
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

			if ((isNotEmpty(contactNumber)) &&  (contactNumber.length() < 11) && contactNumber != " ") {
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

			if ((isNotEmpty(addressLine1)) && (addressLine1.length() < 31) && addressLine1 != " ") {
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
	 * validateEmail method test for known email patterns.
	 * If the argument in test is confirm to valid email
	 * format method successfully pass the test
	 * @author DN
	 * @param email String 
	 * @return boolean if email confirms to the email format
	 * return true else false.
	 * @throws Exception
	 */
	 public static boolean validateEmail(String email){// throws Exception {
		boolean validEmail = false;
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"^([\\w-+\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})$");
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		validEmail = matcher.find();
		return validEmail;
	}
	
	/**
	 * Check the entered user name is having a valid size
	 * user name should have at least 6 characters & should be not more than 20 characters
	 * @author Chinthaka
	 * @param username
	 * @return boolean - Returns true if the requested username is having valid lengths
	 */
	public static boolean isValidUserNameLength(String username) throws Exception {
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
	 * @return boolean - Returns false if the requested password & confirmPassword are not same & not valid in lengths
	 */
	public boolean isValidPassword(String password, String confirmPassword, IDataHelper helper) throws Exception {
		int validityNumber = 0; 
		boolean valid = true;
		try {

			if (!(isNotEmpty(password))){ // check for null fields
				validityNumber = 1;
				helper.setAttribute("passwordError", SystemMessage.EMPTYPASSWORD.message());
				valid = false;
			}
			
			if(!(isNotEmpty(confirmPassword))){ // check for null fields
				helper.setAttribute("passwordError", SystemMessage.EMPTYCONFIRMPASSWORD.message());
				valid = false;
				validityNumber = 2;
			}
			
			if (validityNumber != 1 && (password.length() < 5) && (password.length() > 21)){ //check for the length of the password
				helper.setAttribute("passwordError", SystemMessage.PASSWORDLENGTHERROR.message());
				valid = false;
			}

			if (validityNumber != 1 && validityNumber != 2 && !(password.equals(confirmPassword))){ // Compare password & confirm password fields
				helper.setAttribute("passwordError", SystemMessage.PASSWORDCONFIRMERROR.message());
				valid = false;
			}

		} catch (Exception e) {
			log.error("isValidPassword:  Exception" + e.toString());
			throw e;
		}
		return valid;
	}
}
