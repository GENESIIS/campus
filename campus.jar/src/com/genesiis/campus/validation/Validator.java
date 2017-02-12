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
//20170209 CW c38-view-update-tutor-profile add validateUserAndEmail() method.
//20170209 CW c38-view-update-tutor-profile modified isHavingNullValues() method.
//20170209 CW c38-view-update-tutor-profile modified validatePassword() method name to isValidPassword().
//20170212 CW c38-view-update-tutor-profile modified isValidNetworkCode(), isValidLastname(), isValidContactNumber(), isValidAddressLine1() methods & validateTutorFields() - modify comment


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import com.genesiis.campus.util.IDataHelper;

public class Validator {

	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Check the given value is empty or not empty
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate is given string contains a null value. Returns true if String is not empty.
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
	 * Validate helper fields for null values.
	 * 
	 * @author Chathuri, Chinthaka
	 * @param helper
	 * @return boolean : Returns true if helper is having any null values for required fields
	 * @throws Exception
	 */
	public boolean isHavingNullValues(IDataHelper helper) throws Exception {

		boolean isHavingNull = false; 
		try {		
			
			System.out.println("helper.getParameter(firstname) = "+helper.getParameter("firstname"));
			if (!((Validator.isNotEmpty(helper.getParameter("firstname"))) || (helper.getParameter("firstname") == " "))) {
				helper.setAttribute("firstNameError", SystemMessage.EMPTYFIRSTNAME.message());
				isHavingNull = true; 
			}

			System.out.println("helper.getParameter(lastname) = "+helper.getParameter("lastname"));
			if (!((Validator.isNotEmpty(helper.getParameter("lastname"))) || (helper.getParameter("lastname") == " "))) {
				helper.setAttribute("lastNameError", SystemMessage.EMPTYLASTNAME.message());
				isHavingNull = true; 
			}

			System.out.println("helper.getParameter(mobileCountryCode) = "+helper.getParameter("mobileCountryCode"));
			if (!((Validator.isNotEmpty(helper.getParameter("mobileCountryCode"))) || (helper.getParameter("mobileCountryCode") == "0"))) {
				helper.setAttribute("mobileError", SystemMessage.EMPTYMOBILECOUNTRYCODE.message());
				isHavingNull = true; 
			}

			System.out.println("helper.getParameter(mobileNetworkCode) = "+helper.getParameter("mobileNetworkCode"));
			if (!((Validator.isNotEmpty(helper.getParameter("mobileNetworkCode"))) || (helper.getParameter("mobileNetworkCode") == " "))) {
				helper.setAttribute("mobileNetworkError", SystemMessage.EMPTYMOBILENETWORKCODE.message());
				isHavingNull = true; 
			}

			System.out.println("helper.getParameter(mobileNumber) = "+helper.getParameter("mobileNumber"));
			if (!((Validator.isNotEmpty(helper.getParameter("mobileNumber"))) || (helper.getParameter("mobileNumber") == " "))) {
				helper.setAttribute("mobileNumberError", SystemMessage.EMPTYMOBILENUMBER.message());
				isHavingNull = true; 
			}
			
			System.out.println("helper.getParameter(landCountryCode) = "+helper.getParameter("landCountryCode"));
			if (!((Validator.isNotEmpty(helper.getParameter("landCountryCode"))) || (helper.getParameter("landCountryCode") == " "))) {
				helper.setAttribute("landError", SystemMessage.EMPTYLANDCOUNTRYCODE.message());
				isHavingNull = true; 
			}

			System.out.println("helper.getParameter(landAreaCode) = "+helper.getParameter("landAreaCode"));
			if (!((Validator.isNotEmpty(helper.getParameter("landAreaCode"))) || (helper.getParameter("landAreaCode") == " "))) {
				helper.setAttribute("landAreaCodeError", SystemMessage.EMPTYLANDAREACODE.message());
				isHavingNull = true; 
			}
			
			System.out.println("helper.getParameter(landNumber) = "+helper.getParameter("landNumber"));
			if (!((Validator.isNotEmpty(helper.getParameter("landNumber"))) || (helper.getParameter("landNumber") == " "))) {
				helper.setAttribute("landNumberError", SystemMessage.EMPTYLANDNUMBER.message());
				isHavingNull = true; 
			}

			System.out.println("helper.getParameter(address1) = "+helper.getParameter("address1"));
			if (!((Validator.isNotEmpty(helper.getParameter("address1"))) || (helper.getParameter("address1") == " "))) {
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
			isValid = validateUserAndEmail(helper);
			
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

			if ((isNotEmpty(firstName)) && (firstName.length() < 21) && firstName == " ") {
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
	 * @return String - Returns boolean value False if the requested password & confirmPassword are not same & not valid in lengths
	 */
	public boolean isValidPassword(String password, String confirmPassword, IDataHelper helper) throws Exception {
		int validityNumber = 0; 
		boolean message = true;
		try {

			if (!(isNotEmpty(password))){ // check for null fields
				validityNumber = 1;
				helper.setAttribute("passwordError", SystemMessage.EMPTYPASSWORD.message());
				message = false;
			}
			
			if(!(isNotEmpty(confirmPassword))){ // check for null fields
				helper.setAttribute("passwordError", SystemMessage.EMPTYCONFIRMPASSWORD.message());
				message = false;
				validityNumber = 2;
			}
			
			if (validityNumber != 1 && (password.length() < 5) && (password.length() > 21)){ //check for the length of the password
				helper.setAttribute("passwordError", SystemMessage.PASSWORDLENGTHERROR.message());
				message = false;
			}
			
			if (validityNumber != 1 && validityNumber != 2 && !(password.equals(confirmPassword))){ // Compare password & confirm password fields
				helper.setAttribute("passwordError", SystemMessage.PASSWORDCONFIRMERROR.message());
				message = false;
			}

		} catch (Exception e) {
			log.error("isValidPassword:  Exception" + e.toString());
			throw e;
		}
		return message;
	}
	
	/**
	 * Validate Tutor username & email given. 
	 * @author Chinthaka
	 * @param helper
	 * @return boolean : return false if user name or email is having an error
	 * @throws Exception
	 */
	public boolean validateUserAndEmail(IDataHelper helper) throws SQLException, Exception{

		boolean message = true; 
		try {		

			if (!(Validator.isNotEmpty(helper.getParameter("username"))) && (helper.getParameter("username") == " ") ){
				helper.setAttribute("usernameError", SystemMessage.EMPTYUSERNAME.message());
				message = false;
			}
			
			if (!(Validator.isNotEmpty(helper.getParameter("email")))){
				helper.setAttribute("emailError", SystemMessage.EMPTYEMAIL.message());
				message = false;
			}
			
			if (!Validator.isValidUserNameLength(helper.getParameter("username"))) {
				helper.setAttribute("usernameError", SystemMessage.USERNAME_LENGTH.message());
				message = false;
			} 
			
		} catch (SQLException sqlException) {
			log.info("validateUserAndEmail(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("validateUserAndEmail(): Exception " + e.toString());
			throw e;
		} 
		return message;
	}
	
	/*
	var usernameExist = ValidateUsername(username);
	if (usernameExist.message == '0') {
		document.getElementById('usernameError').innerHTML = "**Username Already exists.";
		document.getElementById('username').focus();
		flag = false;
	}*/

/*
function ValidateUsername(username) {
	var resp = null;
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'CHECK_USERNAME',
			USERNAME : username
		},
		dataType : "json",
		success : function(response) {
			resp = response;
		},
		error : function(response) {
			resp = response;
		}
	});

	return resp;
}
*/
	
	
	
	
	
	
}
