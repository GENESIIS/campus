package com.genesiis.campus.validation;

//20161030 DJ c6-list-available-institutes-on-the-view INIT UtilityHelper class. implemented isNotEmpty() method
//20161030 DJ c6-list-available-institutes-on-the-view INIT UtilityHelper class. implemented isInteger() method

/**
 * @author DJ Utility Helper class to  server side validate. Class name can be change when merging to master branch.
 * @since 20161030
 */
public class UtilityHelper {
	
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
		if ((ob != null) && (ob != " ")) {
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
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

}
