package com.genesiis.campus.validation;

//20161102 JH c7-higher-education-landing-page Validator.java created
//20161102 JH c7-higher-education-landing-page getDuration() created
//20161115 JH c7-higher-education-landing-page isEmpty()method modified to support any string validation
//20161115 JH c7-higher-education-landing-page add method comments
//20161229 JH c39-add-course-provider isEmpty() method modified
//20170105 JH c39-add-course-provider added validateCourseProvider(IDataHelper) method
//20170106 JH c39-add-course-provider modified isEmpty() method to a static method and renamed as isEmptyString()
//20170201 JH c39-add-course-provider arranged imports according to the style guide
//20170209 JH c39-add-course-provider added expiration date back end validation 
//20170221 JH c141-add-course-provider-issue-improvements created separate featuredAccountValidation() method to validate featured course provider account details,
// 			removed unwanted comments with code review fixes
//20170223 JH c141-add-course-provider-issue-improvements featuredAccountValidation(): username length error message changed, validate
//			fields with database length
//20170224 JH c141-add-course-provider-issue-improvements courseProviderURLValidation(): created to validate course provider URL's
//20170226 JH c141-add-course-provider-issue-improvements featuredAccountValidation(): password validation methods changed
//20170228 JH c141-add-course-provider-issue-improvements commented implementation until one off course provider implementation completed
//20170301 JH c141-add-course-provider-issue-improvements username and other validation methods changed
//20170404 JH c141-ui-integration-for-add-course-provider validateCourseProvider() method changed to use AccountType enum class name instead of the enum value
//20170405 JH c141-ui-integration-for-add-course-provider validateCourseProvider(IDataHelper) changes due to element id changes after UI integration
//20170407 JH c141-ui-integration-for-add-course-provider change validation messages due to phone number max length changes, password validation methods changed, email address
//				validation pattern changed, added method validNumber() to validate a string parameter for numbers
//20170420 JH c141-ui-integration-for-add-course-provider validateCourseProvider(): fax number validation method changed to separately validate for null and integer
//20170424 JH c135-public-display-tutor-profile removed course provider related validation methods validateCourseProvider(), featuredAccountValidation() and courseProviderURLValidation()
//				methods removed
//20170505 JH c135-public-display-tutor-profile removed unwanted imports 

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	/**
	 * Programme durations are stored as days which is to be converted into years and months.
	 * getDuration method used to calculate the duration of a programme in years, months and days. 
	 * @param duration
	 * @return object 
	 * @author JH
	 */
	public Object getDuration(int duration) {
		ArrayList<String> programDuration = new ArrayList<String>();

		int years = duration / 365;
		duration = duration % 365;
		int month = duration / 30;
		duration = duration % 30;

		programDuration.add(String.valueOf(years));
		programDuration.add(String.valueOf(month));
		programDuration.add(String.valueOf(duration));

		return programDuration;
	}

	/**
	 * isEmpty method used to validate string parameters. 
	 * @param parameter
	 * @return boolean true, false value
	 */
	public static boolean isEmptyString(String parameter) {
		boolean valid = false;
		if ((parameter == null) || (parameter == "")) {
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
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();

	}
	
	/**
	 * Check if the given string is a number
	 * @param number
	 * @return true if string is a number, else false
	 * @author JH
	 */
	public static boolean validNumber(String number){
		try {
			Long.parseLong(number);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check the given number is valid number or not
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate is given string contains a numaric value.
	 **/
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * isValidLength method created to validate the length of the parameter
	 * against the length value passed to the method
	 * @param parameter
	 * @param length
	 * @return boolean true if the length is valid else false
	 * @author JH
	 */
	public static boolean isValidLength(String  parameter, int maxLength, int minLength){
		boolean flag = true;
		if((parameter.length() > maxLength) || parameter.length() < minLength){
			flag = false;
		}		
		return flag;
	}


	
}