package com.genesiis.campus.validation;

//20161111 DN c10-contacting-us-MP created the Validatory.java interface

/**
* Validatory interface provides the necessary contracts 
* for validating and asserting the passed values
* @author DN
*
*/

public interface Validatory {
	
	/**
	 * isNotEmpty  method validate if the passed String is
	 * null ot empty.
	 * @author DN
	 * @param text to be tested for null or empty
	 * @return boolean if "text" contains an alpha numeric value
	 * method returns true else false. 
	 * @throws Exception
	 */
	public boolean isNotEmpty(String text) throws Exception;
	
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
	public boolean validateEmail(String email) throws Exception;
	
	/**
	 * isInteger() tests if the passed parameter is an Integer
	 * @author DN
	 * @param value String value representing the Integer and
	 * required to validate
	 * @return true if the parameter passed in is an Integer
	 * @throws Exception
	 */
	public boolean isInteger(String value) throws Exception;
	/**
	 * isInteger() tests if the passed parameter is an Float
	 * @author DN
	 * @param value String value representing the Float and
	 * required to validate
	 * @return true if the parameter passed in is an Float
	 * @throws Exception
	 */
	public boolean isFloat(String value) throws Exception;
	
	/**
	 * isValidPhoneNumber validates the phone number which should be
	 * consisted of 10 digits and starts with 0 and it's a must.
	 * can't contain any alpha numeric values
	 * @author DN
	 * @param number the phone number to be validate as a String
	 * @return true if the parameter adhere to the above requirement.
	 * @throws Exception
	 */
	public boolean isValidPhoneNumber(String number) throws Exception;

}