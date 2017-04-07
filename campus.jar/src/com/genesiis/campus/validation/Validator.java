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
//				validation pattern changed

import com.genesiis.campus.command.CmdAddFeaturedProvider;
import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.util.IDataHelper;
import com.sun.org.apache.regexp.internal.recompile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
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
	
	/**
	 * @author JH
	 * @param helper
	 * @return ArryList of type of String
	 * 
	 * used to validate course provider details.
	 * @throws ParseException 
	 */
	public static ArrayList<String> validateCourseProvider(IDataHelper helper) throws ParseException{
		ArrayList<String> errorString = new ArrayList<String>();
		
		if(isEmptyString(helper.getParameter("courseProvider"))){
			helper.setAttribute("errorCourseProvider", "Please select the course provider type");
		}
		
		if(!isValidLength(helper.getParameter("providerName"), 200, 1 )){
			helper.setAttribute("errorProviderName", "Name is empty or too long");
			errorString.add("Provider Name ");
		}
		
		if(!isValidLength(helper.getParameter("shortName"), 30, 0)){
			helper.setAttribute("errorShortName", "Short name is empty or too long");
			errorString.add("Short Name ");
		}	
		
		if(!isValidLength(helper.getParameter("uniquePrefix"), 20, 2)){
				helper.setAttribute("errorUniquePrefix", "Unique name is empty or too long");
		}
		
		if(isEmptyString(helper.getParameter("aboutMe"))){
			helper.setAttribute("errorAboutMe", "Say something about you");
			errorString.add("About Me ");
		}
		
		if(!isValidLength(helper.getParameter("specialFeatures"), 100, 0)){
			helper.setAttribute("errorSpecialFeatures", "Description is too long.");
			errorString.add("About Me ");
		}
		
		if(isEmptyString(helper.getParameter("selectedCountry"))){
			helper.setAttribute("errorSelectedCountry", "Select a country");
			errorString.add("Country ");
		}
		
		if(isEmptyString(helper.getParameter("selectedTown"))){
			helper.setAttribute("errorSelectedTown", "Select a town");
			errorString.add("Town ");
		}
		
		if(isEmptyString(helper.getParameter("providerStatus"))){
			helper.setAttribute("errorProviderStatus", "Select a status");
			errorString.add("Course Provider status ");
		}
		
		if(!isValidLength(helper.getParameter("areaCode"), 4, 1) || !isInteger(helper.getParameter("areaCode")) ){
			
			helper.setAttribute("errorLand1", "Area code is empty, too long(max 4 characters) or invalid. Only numbers allowed.");
			helper.setAttribute("errorLand2", "Area code is empty, too long(max 4 characters) or invalid. Only numbers allowed.");
			
			if(isValidLength(helper.getParameter("fax"), 12, 0) ){
				
				if(!isEmptyString(helper.getParameter("fax"))){
					helper.setAttribute("errorFax", "Area code is empty, too long(max 4 characters) or invalid. Only numbers allowed.");
				}
			}
			
			errorString.add("Area Code ");
		}
		
		// proceed with phone number validations only if the area code is valid
		if(isValidLength(helper.getParameter("areaCode"), 4, 1) && isInteger(helper.getParameter("areaCode")) ){

			if((!isValidLength(helper.getParameter("land1"), 12, 1)) || !isInteger(helper.getParameter("land1"))){
				helper.setAttribute("errorLand1", "Phone number 1 is empty, too long(max 12 characters) or invalid. Only numbers allowed.");
				errorString.add("Land number 1 ");
			}
			
			if(isValidLength(helper.getParameter("land2"), 12, 0) && !isInteger(helper.getParameter("land2"))){
				helper.setAttribute("errorLand2", "Phone number 2 is invalid. Only numbers allowed.");
				errorString.add("land number 2");
			}
			
			if( !isValidLength(helper.getParameter("land2"), 12, 0)){
				helper.setAttribute("errorLand2", "Phone number 2 is too long(max 212 characters).");
				errorString.add("land number 2");
			}
			
			if(!isValidLength(helper.getParameter("fax"), 12, 0) || !isInteger(helper.getParameter("fax"))){
				helper.setAttribute("errorFax", "Fax number is invalid or too long (max 12 characters). Only numbers allowed.");
				errorString.add("fax number");
			}
			
		}
	
		if(!isValidLength(helper.getParameter("networkCode"), 4, 1) || !isInteger(helper.getParameter("networkCode"))){
			helper.setAttribute("errorMobile", "Network code is empty, too long(max 4 characters) or invalid. Only numbers allowed.");
			errorString.add("Network code ");
		}
		//if network code is valid, validate the mobile number
		if(isValidLength(helper.getParameter("networkCode"), 4, 1) && isInteger(helper.getParameter("networkCode"))){
			
			if(!isValidLength(helper.getParameter("mobile"), 12, 1) || !isInteger(helper.getParameter("mobile"))){
				helper.setAttribute("errorMobile", "Mobile number is empty or invalid. Only numbers allowed.");
				errorString.add("Mobile code ");
			}
		}
		
		if(isEmptyString(helper.getParameter("selectedProviderType"))){
			helper.setAttribute("errorProviderType", "Select a course provier type");
			errorString.add("Course Provider Type ");
		}
		
		if(!isValidLength(helper.getParameter("address1"), 50, 1)){
			helper.setAttribute("errorAddress1", "Empty or too long address line.");
			errorString.add("Address Line ");
		}
		
		if(!isValidLength(helper.getParameter("address2"), 50, 0)){
			helper.setAttribute("errorAddress1", "Too long address line.");
			errorString.add("Address Line 2");
		}
		
		if(!isValidLength(helper.getParameter("address3"), 50, 0)){
			helper.setAttribute("errorAddress1", "Too long address line.");
			errorString.add("Address Line 3");
		}
		
		if(!isValidLength(helper.getParameter("inquiryMail"), 50, 1) || !validateEmail(helper.getParameter("inquiryMail"))){
			helper.setAttribute("errorInquiryMail", "Empty or invalid email address. (only 255 characters allowed)");
			errorString.add("Empty or invalid email address");
		}if(!isValidLength(helper.getParameter("generalEmail"), 50, 1) || !validateEmail(helper.getParameter("generalEmail"))){
			helper.setAttribute("errorGeneralEmail", "Empty or invalid email address. (only 255 characters allowed)");
			errorString.add("Empty or invalid email address");
		}
		
		//validte course provider url's
		errorString = courseProviderURLValidation(helper, errorString, 200, 0, "webLink", "errorWebLink");
		errorString = courseProviderURLValidation(helper, errorString, 200, 0, "facebook", "errorFacebook");
		errorString = courseProviderURLValidation(helper, errorString, 100, 0, "twitter", "errorTwitter");
		errorString = courseProviderURLValidation(helper, errorString, 100, 0, "linkdedIn", "errorLinkedIn");
		errorString = courseProviderURLValidation(helper, errorString, 100, 0, "instagram", "errorInstagram");
		errorString = courseProviderURLValidation(helper, errorString, 100, 0, "mySpace", "errorMyspace");

		//viber and whatsapp validations
		if(!isValidLength(helper.getParameter("whatsapp"), 20, 0) ){
			helper.setAttribute("errorWhatsapp", "Maximum length exceed or invalid.");
			errorString.add("Whatsapp ");
		}
		if(!isValidLength(helper.getParameter("viber"), 20, 0)){
			helper.setAttribute("errorViber", "Maximum length exceed or invalid.");
			errorString.add("Viber ");
		}
		
		/*
		 * The course provider account validation needs only for the featured course provider
		 * type. Therefore, depending on the course provider type it will relevant validations
		 */
		if(!isEmptyString(helper.getParameter("courseProvider"))){	
			String courseProviderType =helper.getParameter("courseProvider");
			
			//validate details related to featured course provider account
			if(courseProviderType.equals(AccountType.FEATURED_COURSE_PROVIDER.name())){
				
				errorString = featuredAccountValidation(helper, errorString);
			}
		}
	
	
		return errorString;
		
	}
	
	
	/**
	 * Used to validate featured course provider account details
	 * @param helper
	 * @param errorString
	 * @return
	 * @author JH
	 */
	public static ArrayList<String> featuredAccountValidation(IDataHelper helper, ArrayList<String> errorString){
		
		if(!isValidLength(helper.getParameter("providerPrivateName"), 100, 1)){
			helper.setAttribute("errorPrivateName", "Contact name is empty or too long. (only 20 characters).");
			errorString.add("Provider Name");
		}
		
		// validate course provider private email
		if (isValidLength(helper.getParameter("providerEmail"), 255, 1)) {
			if (!validateEmail(helper.getParameter("providerEmail"))) {
				helper.setAttribute("errorPrivateEmail", "Invalid contact Email address");
				errorString.add("Private Email");
			}

		}
		if (!isValidLength(helper.getParameter("providerEmail"), 255, 1)){
			helper.setAttribute("errorPrivateEmail", "Email address is empty of too long (Only 255 characters allowed). ");
			errorString.add("Private Email");
		}
		
		//validate  username of the course provider
		if(!isValidLength(helper.getParameter("providerUsername"), 100, 5)){
			helper.setAttribute("errorUsername", "Username too small or exceed the max length. It must have min 5 and max 100 characters");
			errorString.add("Username");
		}
		if(isValidLength(helper.getParameter("providerUsername"), 100, 5)){
			//check if the username has only numbers
			if(isInteger(helper.getParameter("providerUsername"))){
				helper.setAttribute("errorUsername", "Only numbers are not allowed for username. ");
				errorString.add("Username");				
			}
		}
		
		//validate password
		if(isValidLength(helper.getParameter("providerPassword"), 100, 6)){
			if(!helper.getParameter("providerPassword").equals(helper.getParameter("cProviderPassword"))){
				helper.setAttribute("errorProviderPassword", "Password fields does not match");
				errorString.add("Password fields does not match");
			}
		}
		
		if(!isValidLength(helper.getParameter("providerPassword"), 100, 6) || 
				!isValidLength(helper.getParameter("cProviderPassword"), 100, 6)){ 				
			errorString.add("Password fields are empty");
			helper.setAttribute("errorProviderPassword", "Password filed(s) should have 6 to 100 characters.");
		}
			
		if(!isValidLength(helper.getParameter("providerContactNumber"), 12, 1)  || !isInteger(helper.getParameter("providerContactNumber"))){
			helper.setAttribute("errorContactNumber", "Empty or invalid contact number.");
			errorString.add("Empty, too long or invalid contact number. Maximum 12 characters.");
		}
		
		if(isEmptyString(helper.getParameter("accountStatus"))){
			helper.setAttribute("errorStatus", "Select the account status");
			errorString.add("Account Status");
		}
		
		return errorString;
	}
	
	
	/**
	 * Used to validate URL's related to course provider form URLs
	 * @param helper
	 * @param errorString
	 * @return error string
	 * @author JH
	 */
	public static ArrayList<String> courseProviderURLValidation(IDataHelper helper, ArrayList<String> errorString,
			int maxLength, int minLength, String element, String errorElement){
		element = helper.getParameter(element);
		
		if(!isValidLength(element, maxLength, minLength)){
			helper.setAttribute( errorElement, "URL exceeds the maximum length. Only " + minLength +" to " + maxLength + " characters allowed");
			errorString.add( element + " URL invalid.");
		}else if(!isEmptyString(element) && isInteger(element)){
			helper.setAttribute( errorElement, "Only numbers are not allowed.");
			errorString.add( element + " URL invalid.");
		}
		
		return errorString;
	}
	
}