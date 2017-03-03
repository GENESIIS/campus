package com.genesiis.campus.validation;

//20161111 DN c10-contacting-us-MP created the Validatory.java interface
//20170303 DN c131-admin-manage-banner-upload-banner-image-dn typo in doc comment
//         has been corrected
//20170303 DN c131-admin-manage-banner-upload-banner-image-dn declare 
//         compareDates(Date date,Date otherDate) throws Exception method signature.



import java.util.Date;

	/**
	 * Validatory interface provides the necessary contracts 
	 * for validating and asserting the passed values
	 * @author DN
	 *
	 */

public interface Validatory {
	
	/**
	 * isNotEmpty  method validate if the passed String is
	 * null or empty.
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
	 * format ,method successfully passes the test
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
	 * isFloat() tests if the passed parameter is an Float
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
	
	/**
	 * isStringContainsAlphaNumericAndUnderscore method checks if the 
	 * parameter string contains alphanumeric and _ combination with 
	 * minimum length as agreed. there is no restriction for maximum 
	 * length that could be.
	 * @param testableString
	 * @param requiredMinimumLength
	 * @return boolean if the test passes else false
	 * @throws Exception
	 */
	
	public boolean isStringContainsAlphaNumericAndUnderscore(String testableString,
			int requiredMinimumLength) throws Exception;
	
	/**
	 * compareDates() compares two dates respectively <I>date</I> and <i>otherDate</i>
	 *  and returns an integer based on the comparison 
	 * date > otherDate  then , returns +ve int
	 * date < otherDate then , returns -ve int
	 * date == otherDate then, returns 0
	 * @param date : java.util.Date the initial date
	 * @param otherDate : java.util.Date the secondary date 
	 * @return int value as explained above
	 * @throws Exception 
	 */
	public int compareDates(Date date,Date otherDate) throws Exception;
	
	/**
	 * isUrlValid() expects a url string and a long option
	 * and if the url is accordance with the validity, the method provides a true,
	 * else false
	 * @param url : String url
	 * @param options : long value  
	 * 			ALLOW_2_SLASHES - [FALSE] Allows double '/' characters in the path component.
	 * 			NO_FRAGMENT- [FALSE] By default fragments are allowed, if this option is included then fragments are flagged as illegal.
	 * 			ALLOW_ALL_SCHEMES - [FALSE] By default only http, https, and ftp are considered valid schemes. Enabling this option will
	 * 			let any scheme pass validation
	 * 			 To set multiple options you simply add them together. For example, ALLOW_2_SLASHES + NO_FRAGMENTS enables both of those options.
	 * options are used from org.apache.commons.validator.routines.UrlValidator. Thus should be used with the name qualifier such as 
	 * UrlValidator.NO_FRAGMENT,UrlValidator.ALLOW_ALL_SCHEMES etc
	 * 
	 * @return boolean true if the url accordance with the set requirement by the option, else returns false.
	 * @throws Exception
	 */
	public boolean isUrlValid(String url,long options) throws Exception;
		
	
}