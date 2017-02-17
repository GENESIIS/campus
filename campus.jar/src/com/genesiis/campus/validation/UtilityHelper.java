package com.genesiis.campus.validation;

//20161030 DJ c6-list-available-institutes-on-the-view INIT UtilityHelper class. implemented isNotEmpty() method
//20161030 DJ c6-list-available-institutes-on-the-view INIT UtilityHelper class. implemented isInteger() method
//20170217 DJ c138-add-basic-programme-MP-dj Implemented isPatternMatch(), isFromDateAfterTodate(), isValidEmailFormat(),isThisDateValid().

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author DJ Utility Helper class to  server side validate. Class name can be change when merging to master branch.
 * @since 20161030
 */
public class UtilityHelper {
	
	static Logger log = Logger.getLogger(FormValidator.class.getName());	
	static String dateFormat="yyyy-MM-dd";
	
	/**
	 * @author DJ
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
	 * @author DJ
	 * @param value
	 * @return boolean to validate is given string contains a null value.
	 * **/
	public static boolean isNotEmptyObject(Object ob) {
		boolean status = false;
		if ((ob != null)) {
			status = true;
		}
		return status;
	}
	
	/**
	 * @author DJ
	 * @param string
	 * @return boolean to validate is given string contains a numeric value.
	 * **/
	public static boolean isInteger(String string) {		
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return false;
		} 
		return true;
	}
	
	public static boolean isFromDateAfterTodate(String commencementDate,
			String expirationDate) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			// if not valid, it will throw ParseException
			final Date commencement = sdf.parse(commencementDate);
			final Date expiration = sdf.parse(expirationDate);

			if (commencement.after(expiration)) {
				 return true;
			}

		} catch (ParseException e) {
			log.error("validateProgrammeDetails() : ParseException " + e.toString());	
			throw e;
		}
		return false;
	}

	public static boolean isPatternMatch(String sourceString,String regularExpression) {
		return (sourceString.matches(regularExpression)?true:false);
	}

	
	public static boolean isValidEmailFormat(String email) {				
		String reg = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";		
		return isPatternMatch(email,reg);	    
	}
	
	public static boolean isThisDateValid(String dateToValidate) {		
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		
		try {
			// if not valid, it will throw ParseException
			final Date date = sdf.parse(dateToValidate);			

		} catch (ParseException e) {
			log.error("isThisDateValid() : ParseException " + e.toString());
			return false;
		}
		return true;
	}

}
