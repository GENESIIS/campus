package com.genesiis.campus.validation;

//20161028 CM c13-Display-course-details INIT Validator.java
//20170227 CW c37-tutor-update-tutor-profile-cw add Password & confirm Password from old CAM-38
//20170227 CW c37-tutor-update-tutor-profile-cw modified isValidPassword method to add validations for empty values
//20170227 CW c37-tutor-update-tutor-profile-cw modified validateTutorFields(), isValidPassword() to validate password fields

import com.genesiis.campus.entity.TutorDAO;
import com.genesiis.campus.util.IDataHelper;
import com.genesiis.campus.util.security.Encryptable;
import com.genesiis.campus.util.security.TripleDesEncryptor;

import org.jboss.logging.Logger;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * this class is used to validate all the tutor fields before update 
 * @author CW
 *
 */
public class Validator {

	static Logger log = Logger.getLogger(Validator.class.getName());
	int totalDays = 0;
	
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
	
	/**
	 * Check the given value is not having only a space
	 * @author Chinthaka
	 * @param value
	 * @return boolean returns true if the text is empty or contains only a space value.
	 **/
	public static boolean isEmptyOrHavingSpace(String text) {
		boolean status = false;
		
		if ((text == null) || (text.isEmpty() == true) || text.equals(" ")) {
			status = true;
		}
		
		return status;
}

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
						
			if(isEmptyOrHavingSpace(helper.getParameter("firstname"))) {
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

			if (!isValidUserAndEmail(helper)) {
				isValid = false;
			}
			
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
			if (!isValidPassword(helper)) {
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
			
			int code = Integer.parseInt(countryCode);
			
			if ((isNotEmpty(countryCode)) && (countryCode.length() < 6) && !(countryCode.equals("0"))) {
				valid = true;
			}

		} catch (NumberFormatException e) {
			valid = false;
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

			int code = Integer.parseInt(networkCode);
			
			if ((isNotEmpty(networkCode)) && (networkCode.length() < 11) && networkCode != " ") {
				valid = true;
			}

		} catch (NumberFormatException e) {
			valid = false;
		}  catch (Exception e) {
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

			int code = Integer.parseInt(contactNumber);
			
			if ((isNotEmpty(contactNumber)) &&  (contactNumber.length() < 11) && contactNumber != " ") {
				valid = true;
			}

		} catch (NumberFormatException e) {
			valid = false;
		}   catch (Exception e) {
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
			if((isNotEmpty(number)) && !(number.equals("-"))){
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
	 * Check the entered old password is a valid one & new password is same with confirmPassword value & lengths are acceptable
	 * 
	 * @author Chinthaka
	 * @param password, confirmPassword
	 * @return boolean - Returns boolean value False if the old Password is not same as the database value or requested new password & confirmPassword are not same & not valid in lengths
	 */
	public boolean isValidPassword(IDataHelper helper) throws Exception {
		int validityNumber = 0; 
		boolean message = true;
		
		String oldPassword = helper.getParameter("oldPassword");
		String newPassword = helper.getParameter("newPassword");
		String confirmPassword = helper.getParameter("confirmPassword");

		Encryptable passwordEncryptor = new TripleDesEncryptor(oldPassword);
		
		try {

			if (isEmptyOrHavingSpace(oldPassword)){ // check for null fields
				validityNumber = 1;
				helper.setAttribute("oldPasswordError", SystemMessage.EMPTYPASSWORD.message());
				message = false;
			}
			
			if(!(isEmptyOrHavingSpace(oldPassword)) && !(helper.getParameter("password").equals(passwordEncryptor.encryptSensitiveDataToString()))){
				validityNumber = 1;
				helper.setAttribute("oldPasswordError", SystemMessage.INCORREST_PASSWORD.message());
				message = false;
			}

			if (!(isEmptyOrHavingSpace(newPassword))){ // check for null fields
				validityNumber = 1;
				helper.setAttribute("newPasswordError", SystemMessage.EMPTYPASSWORD.message());
				message = false;
			}
			
			if(!(isEmptyOrHavingSpace(confirmPassword))){ // check for null fields
				helper.setAttribute("confirmPasswordError", SystemMessage.EMPTYCONFIRMPASSWORD.message());
				message = false;
				validityNumber = 2;
			}
			
			if (validityNumber != 1 && !isEmptyOrHavingSpace(password) && (password.length() < 5) && (password.length() > 21)){ //check for the length of the password
				helper.setAttribute("passwordError", SystemMessage.PASSWORDLENGTHERROR.message());
				message = false;
			}
			
			if (validityNumber != 1 && !isEmptyOrHavingSpace(password) && !isEmptyOrHavingSpace(confirmPassword) && validityNumber != 2 && !(password.equals(confirmPassword))){ // Compare password & confirm password fields
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
	public boolean isValidUserAndEmail(IDataHelper helper) throws SQLException, Exception{

		boolean message = true; 
		int type = 0;
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
			
			if(!((helper.getParameter("email")).equals(helper.getParameter("emailOld").toString()))){		
				type = TutorDAO.validateUsernameEmailFields(helper.getParameter("username"), helper.getParameter("email"));
			
				// Commented this code to use later if the username validations required. - CW
/*				if(type == 1){
					helper.setAttribute("usernameError", SystemMessage.USERNAME_EXIST.message());
					message = false;
				} */
				
				if(type == 2){
					helper.setAttribute("emailError", SystemMessage.EMAIL_USED.message());
					message = false;
				}
			}
			
		} catch (SQLException sqlException) {
			log.info("isValidUserAndEmail(): SQLException " + sqlException.toString());
			throw sqlException;
		} catch (Exception e) {
			log.info("isValidUserAndEmail(): Exception " + e.toString());
			throw e;
		} 
		return message;
	}
}
