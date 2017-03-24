package com.genesiis.campus.validation;

//20161028 CM c13-Display-course-details INIT Validator.java
//20170227 CW c37-tutor-update-tutor-profile-cw add Password & confirm Password from old CAM-38
//20170227 CW c37-tutor-update-tutor-profile-cw modified isValidPassword method to add validations for empty values
//20170227 CW c37-tutor-update-tutor-profile-cw modified validateTutorFields(), isValidPassword() to validate password fields
//20170228 CW c37-tutor-update-tutor-profile-cw modified isValidPassword() to check for Old Password, New Password & Confirm Password 
//20170228 CW c37-tutor-update-tutor-profile-cw modified isValidPassword() & remove oldPassword null checking
//20170228 CW c37-tutor-update-tutor-profile-cw modified isValidPassword() to work with encrypted passwords
//20170301 CW c37-tutor-update-tutor-profile-cw modified isValidPassword to fix some errors
//20170301 CW c37-tutor-update-tutor-profile-cw modified passwordOld to passwordFromDb
//20170301 CW c37-tutor-update-tutor-profile-cw modified isValidPassword method & create validations again
//20170301 CW c37-tutor-update-tutor-profile-cw modified isValidUserAndEmail method message variable to isValid
//20170301 CW c37-tutor-update-tutor-profile-cw unit testing & modified isValidPassword() to remove log info messages & commented lines 
//20170301 CW c37-tutor-update-tutor-profile-cw unit testing & add validations to country codes WIP
//20170301 CW c37-tutor-update-tutor-profile-cw unit testing & add validations to validateTutorFields() method country codes & Town codes completed
//20170302 CW c37-tutor-update-tutor-profile-cw modified isValidFirstname, isValidLastname methods to validate for numbers
				//removed isValidLastname & modified isValidFirstname method to isValidName & Change the validateTutorFields method to use isValidName method
				//removed isValidContactNumber & modified isValidNetworkCode method to isValidNetworkOrContactCode & Change the validateTutorFields method to use isValidNetworkOrContactCode method
//20170305 CW c37-tutor-update-tutor-profile-cw modified isValidPassword method & add static modifier to the method declaration. 
//20170306 CW c37-tutor-update-tutor-profile-cw modified isEmptyOrHavingSpace method to validate one or many space characters
//20170306 CW c37-tutor-update-tutor-profile-cw removed isValidUserAndEmail() to remove DAO class calling from this class
				// modified validateTutorFields() to remove calling isValidUserAndEmail()
//20170316 CW c37-tutor-update-tutor-profile-cw modified field max lengths to match database field max size
				// modified isValidNetworkOrContactCode method name to isValidNetworkCode
				// Add isValidContactCode method
				// Modified validateTutorFields method to work with isValidNetworkCode & isValidContactCode methods
//20170321 CW c37-tutor-update-tutor-profile-cw modified validateTutorFields() to validate townDetails & towncodeOld correctly
//20170324 CW c37-tutor-update-tutor-profile-cw modified isValidPassword method & fix password length validation error

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
			
			if (isHavingNullValues(helper)) {
				isValid = false;
			} 			
			
			if (!isValidName(helper.getParameter("firstname"))) {
				helper.setAttribute("firstNameError", SystemMessage.FIRSTNAMEERROR.message());
				isValid = false;
			}
			if (!isValidName(helper.getParameter("lastname"))) {
				helper.setAttribute("lastNameError", SystemMessage.LASTNAMEERROR.message());
				isValid = false;
			}
			
			if(!(isEmptyOrHavingSpace(helper.getParameter("landphonecountrycodeOld")))){
				//landphonecountrycodeOld is not null
				if(!helper.getParameter("countryDetails").equals("0")){
					// Country details are changed
					if(!(helper.getParameter("landphonecountrycodeOld").equals(helper.getParameter("countryDetails")))){
						// old & new country codes are different											
						if(helper.getParameter("towncodeOld").equals(helper.getParameter("townDetails")) || helper.getParameter("townDetails").equals("0")){
							// town codes are same i.e. town details are not changed
							helper.setAttribute("townError", SystemMessage.TOWN_CODE_NOT_CHANGED.message());
							isValid = false;
						}
					}
				}
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
			if (!isValidContactCode(helper.getParameter("mobileNumber"))) {
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
			if (!isValidContactCode(helper.getParameter("landNumber"))) {
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
	 * Check the entered name is a valid one
	 * 
	 * @author Chinthaka
	 * @param name
	 * @return boolean - Returns true if the requested name is a valid one
	 */
	public boolean isValidName(String name) throws Exception {
		boolean valid = false;
		try {
			if(!isEmptyOrHavingSpace(name) && (name.length() < 36) && name.matches( "[a-zA-Z][a-zA-Z]*" )) {
				valid = true;
			}
		} catch (Exception e) {
			log.error("isValidName:  Exception" + e.toString());
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
			
			if ((isNotEmpty(countryCode)) && (countryCode.length() < 11) && !(countryCode.equals("0"))) {
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
	public boolean isValidNetworkCode(String code) throws Exception {
		boolean valid = false;
		try {

			int val = Integer.parseInt(code);
			
			if (!isEmptyOrHavingSpace(code) && (code.length() < 11)) {
				valid = true;
			}

		} catch (NumberFormatException e) {
			valid = false;
		}  catch (Exception e) {
			log.error("isValidNetworkOrContactCode:  Exception" + e.toString());
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
	public boolean isValidContactCode(String code) throws Exception {
		boolean valid = false;
		try {

			int val = Integer.parseInt(code);
			
			if (!isEmptyOrHavingSpace(code) && (code.length() < 16)) {
				valid = true;
			}

		} catch (NumberFormatException e) {
			valid = false;
		}  catch (Exception e) {
			log.error("isValidNetworkOrContactCode:  Exception" + e.toString());
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

			if ((isNotEmpty(addressLine1)) && (addressLine1.length() < 51) && addressLine1 != " ") {
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
	 * @param IDataHelper helper
	 * @return boolean - Returns boolean value False if the old Password is not same as the database value or requested new password & confirmPassword are not same & not valid in lengths
	 */
	public static boolean isValidPassword(IDataHelper helper) throws Exception {
		boolean isValid = true;
		
		String oldPassword = helper.getParameter("oldPassword");
		String newPassword = helper.getParameter("newPassword");
		String confirmPassword = helper.getParameter("confirmPassword");

		Encryptable passwordEncryptor = new TripleDesEncryptor(oldPassword);
		String encryptedOldPassword = passwordEncryptor.encryptSensitiveDataToString();
						
		try {
			
			if(!(isEmptyOrHavingSpace(oldPassword))){
				// Need to change the password
				if(!(isEmptyOrHavingSpace(oldPassword)) && (encryptedOldPassword.equals(helper.getParameter("passwordFromDb")))){
					// encryptedOldPassword & passwordFromDb are same
					if(!(oldPassword.equals(newPassword))){
						// old password & new password are not same
						if((isEmptyOrHavingSpace(newPassword)) && !(newPassword.equals(confirmPassword))){
							//New password & confirm password are not same
							helper.setAttribute("newPasswordError", SystemMessage.NEW_CONFIRM_PASSWORD_NOTSAME.message());
							helper.setAttribute("confirmPasswordError", SystemMessage.NEW_CONFIRM_PASSWORD_NOTSAME.message());
							isValid = false;
						}else{
							if (isEmptyOrHavingSpace(newPassword)){ // check for null fields
								helper.setAttribute("newPasswordError", SystemMessage.EMPTYPASSWORD.message());
								isValid = false;
							}
							
							if(isEmptyOrHavingSpace(confirmPassword)){ // check for null fields
								helper.setAttribute("confirmPasswordError", SystemMessage.EMPTYCONFIRMPASSWORD.message());
								isValid = false;
							}
							
							//check for the length of the newPassword
							if (isEmptyOrHavingSpace(newPassword) || (newPassword.length() < 5) || (newPassword.length() > 51)){ 
								helper.setAttribute("newPasswordError", SystemMessage.PASSWORDLENGTHERROR.message());
								isValid = false;
							}
							
							// Compare password & confirm password fields
							if (!isEmptyOrHavingSpace(newPassword) && !isEmptyOrHavingSpace(confirmPassword) && !(newPassword.equals(confirmPassword))){ 
								helper.setAttribute("confirmPasswordError", SystemMessage.PASSWORDCONFIRMERROR.message());
								isValid = false;
							}
						}
					}else{						
						// old password & new password are same
						helper.setAttribute("oldPasswordError", SystemMessage.OLD_NEW_PASSWORD_SAME.message());
						helper.setAttribute("newPasswordError", SystemMessage.OLD_NEW_PASSWORD_SAME.message());
						isValid = false;
					}
				}else{
					// encryptedOldPassword & passwordFromDb are not same - Old password error
					helper.setAttribute("oldPasswordError", SystemMessage.INCORRECT_PASSWORD.message());
					isValid = false;
				}
			}
		} catch (Exception e) {
			log.error("isValidPassword:  Exception" + e.toString());
			throw e;
		}
		return isValid;
	}
}
