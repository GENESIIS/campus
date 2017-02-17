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
	
	/**
	 * @author DJ dumani
	 * @param fromDateString
	 * @param toDateString
	 * @return if fromDate is after ToDate method returns true, else false.
	 * **/	
	public static boolean isFromDateAfterTodate(String fromDateString, String toDateString) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			// if not valid, it will throw ParseException
			final Date fromDate = sdf.parse(fromDateString);
			final Date toDate = sdf.parse(toDateString);

			if (fromDate.after(toDate)) {
				 return true;
			}

		} catch (ParseException e) {
			log.error("validateProgrammeDetails() : ParseException " + e.toString());	
			throw e;
		}
		return false;
	}

	/**
	 * @author DJ dumani
	 * @param sourceString
	 * @param regularExpression
	 * @return if pattern match with the source returns true else returns false.
	 * **/
	public static boolean isPatternMatch(String sourceString, String regularExpression) {
		return (sourceString.matches(regularExpression) ? true : false);
	}

	/**
	 * @author DJ dumani
	 * @param email	 
	 * @return if email is a valid email returns true else returns false.
	 * **/	
	public static boolean isValidEmailFormat(String email) {				
		String reg = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";		
		return isPatternMatch(email,reg);	    
	}
	
	/**
	 * @author DJ dumani
	 * @param dateString	 
	 * @return if the date is valid returns true else returns false.
	 * **/
	public static boolean isThisDateValid(String dateString) {		
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		
		try {
			// if not valid, it will throw ParseException
			final Date date = sdf.parse(dateString);			

		} catch (ParseException e) {
			log.error("isThisDateValid() : ParseException " + e.toString());
			return false;
		}
		return true;
	}

}
