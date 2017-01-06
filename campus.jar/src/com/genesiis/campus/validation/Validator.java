package com.genesiis.campus.validation;

//20161102 JH c7-higher-education-landing-page Validator.java created
//20161102 JH c7-higher-education-landing-page getDuration() created
//20161115 JH c7-higher-education-landing-page isEmpty()method modified to support any string validation
//20161115 JH c7-higher-education-landing-page add method comments
//20161229 JH c39-add-course-provider isEmpty() method modified
//20170105 JH c39-add-course-provider added validateCourseProvider(IDataHelper) method

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.genesiis.campus.entity.model.CourseProvider;
import com.genesiis.campus.entity.model.CourseProviderAccount;
import com.genesiis.campus.entity.model.CourseProviderTown;
import com.genesiis.campus.util.IDataHelper;

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
	public boolean isEmpty(String parameter) {
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
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();

	}
	
	public Map<String, Object> validateCourseProvider(IDataHelper helper){
		
		Validator validator = new Validator();
		boolean isValid = true;
		ArrayList<String> errorString = new ArrayList<String>();
		
		final CourseProvider courseProvider = new CourseProvider();
		final CourseProviderAccount courseProviderAccount = new CourseProviderAccount();
		final CourseProviderTown courseProviderTown = new CourseProviderTown();
		
		if(validator.isEmpty(helper.getParameter("courseProvider"))){
			helper.setAttribute("errorCourseProvider", "Please select the course provider type");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("providerName"))){
			helper.setAttribute("errorProviderName", "Name can't be empty");
			errorString.add("Provider Name ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("shortName"))){
			helper.setAttribute("errorShortName", "Give a short name");
			errorString.add("Short Name ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("uniquePrefix"))){
			helper.setAttribute("errorUniquePrefix", "Unique name can't be empty");
			errorString.add("Unique Name ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("aboutMe"))){
			helper.setAttribute("errorAboutMe", "Say something about you");
			errorString.add("About Me ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("specialFeatures"))){
			helper.setAttribute("errorSpecialFeatures", "Empty fields");
			errorString.add("SpecialFeatures ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("inquiryMail"))){
			helper.setAttribute("errorInquiryMail", "Empty email address");
			errorString.add("Inquiry Mail ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("generalEmail"))){
			helper.setAttribute("errorGeneralEmail", "Empty email address");
			errorString.add("General Email ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("expirationDate"))){
			errorString.add("Expiration Date ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("selectedCountry"))){
			errorString.add("Country ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("selectedTown"))){
			errorString.add("Town ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("providerStatus"))){
			errorString.add("Course Provider status ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("areaCode"))){
			errorString.add("Area Code ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("land1"))){
			errorString.add("Land number 1 ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("networkCode"))){
			errorString.add("Network code ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("mobile"))){
			errorString.add("Mobile code ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("selectedProviderType"))){
			errorString.add("Course Provider Type ");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("fax"))){
			errorString.add("Fax number");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("address1"))){
			errorString.add("Address Line 1");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("address2"))){
			errorString.add("Address Line 2");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("address3"))){
			errorString.add("Address Line 3");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("accountStatus"))){
			errorString.add("Account Status");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("providerPrivateName"))){
			errorString.add("Provider Name");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("providerEmail"))){
			errorString.add("Private Email");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("providerUsername"))){
			errorString.add("Username");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("providerPassword")) || 
				validator.isEmpty(helper.getParameter("cProviderPassword"))){
			errorString.add("Password fields are empty");
			isValid = false;
		}if(validator.isEmpty(helper.getParameter("accountDescription"))){
			errorString.add("Account Description");
			isValid = false;
		}if(!helper.getParameter("providerPassword").equals(helper.getParameter("cProviderPassword"))){
			errorString.add("Password fields does not match");
			isValid = false;
		}if(!validator.validateEmail(helper.getParameter("inquiryMail"))){
			errorString.add("Invalid email");
		}
		
		return null;
		
	}
}