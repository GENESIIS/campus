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
	
	public Map<String, Object> validateCourseProvider(IDataHelper helper){
		boolean isValid = true;
		ArrayList<String> errorString = new ArrayList<String>();
		
		final CourseProvider courseProvider = new CourseProvider();
		final CourseProviderAccount courseProviderAccount = new CourseProviderAccount();
		final CourseProviderTown courseProviderTown = new CourseProviderTown();
		
		if(isEmpty(helper.getParameter("courseProvider"))){
			helper.setAttribute("errorCourseProvider", "Please select the course provider type");
			isValid = false;
		}if(isEmpty(helper.getParameter("providerName"))){
			helper.setAttribute("errorProviderName", "Name can't be empty");
			errorString.add("Provider Name ");
			isValid = false;
		}if(isEmpty(helper.getParameter("shortName"))){
			helper.setAttribute("errorShortName", "Give a short name");
			errorString.add("Short Name ");
			isValid = false;
		}if(isEmpty(helper.getParameter("uniquePrefix"))){
			helper.setAttribute("errorUniquePrefix", "Unique name can't be empty");
			errorString.add("Unique Name ");
			isValid = false;
		}if(isEmpty(helper.getParameter("aboutMe"))){
			helper.setAttribute("errorAboutMe", "Say something about you");
			errorString.add("About Me ");
			isValid = false;
		}if(isEmpty(helper.getParameter("specialFeatures"))){
			courseProvider.setSpeciality("-");
		}if(!isEmpty(helper.getParameter("specialFeatures"))){
			courseProvider.setSpeciality(helper.getParameter("specialFeatures"));
		}if(isEmpty(helper.getParameter("inquiryMail"))){
			helper.setAttribute("errorInquiryMail", "Empty email address");
			errorString.add("Inquiry Mail ");
			isValid = false;
		}if(isEmpty(helper.getParameter("generalEmail"))){
			helper.setAttribute("errorGeneralEmail", "Empty email address");
			errorString.add("General Email ");
			isValid = false;
		}if(isEmpty(helper.getParameter("expirationDate"))){
			helper.setAttribute("errorExpirationDate", "Select an expiration date");
			errorString.add("Expiration Date ");
			isValid = false;
		}if(isEmpty(helper.getParameter("selectedCountry"))){
			helper.setAttribute("errorSelectedCountry", "Select a country");
			errorString.add("Country ");
			isValid = false;
		}if(isEmpty(helper.getParameter("selectedTown"))){
			helper.setAttribute("errorSelectedTown", "Select a town");
			errorString.add("Town ");
			isValid = false;
		}if(isEmpty(helper.getParameter("providerStatus"))){
			helper.setAttribute("errorProviderStatus", "Select a status");
			errorString.add("Course Provider status ");
			isValid = false;
		}if(isEmpty(helper.getParameter("areaCode"))){
			helper.setAttribute("errorAreaCode", "Give an area code");
			errorString.add("Area Code ");
			isValid = false;
		}if(isEmpty(helper.getParameter("land1")) || !isInteger(helper.getParameter("land1"))){
			helper.setAttribute("errorLand1", "Phone number 1 is empty or invalid");
			errorString.add("Land number 1 ");
			isValid = false;
		}if(isEmpty(helper.getParameter("land2"))){
			courseProvider.setLandPhpneNo2("-");
		}if(!isInteger(helper.getParameter("land2"))){
			helper.setAttribute("errorLand2", "Phone number 2 is invalid");
			errorString.add("land number 2");
			isValid = false;
		}if(isEmpty(helper.getParameter("networkCode")) || !isInteger(helper.getParameter("networkCode"))){
			helper.setAttribute("errorNetworkCode", "Network code is empty or invalid");
			errorString.add("Network code ");
			isValid = false;
		}if(isEmpty(helper.getParameter("mobile")) || !isInteger(helper.getParameter("mobile"))){
			helper.setAttribute("errorMobile", "Mobile number is empty or invalid");
			errorString.add("Mobile code ");
			isValid = false;
		}if(isEmpty(helper.getParameter("selectedProviderType"))){
			helper.setAttribute("errorProviderType", "Select a course provier type");
			errorString.add("Course Provider Type ");
			isValid = false;
		}if(isEmpty(helper.getParameter("fax"))){
			courseProvider.setFaxNo("-");
		}if(isEmpty(helper.getParameter("address1"))){
			errorString.add("Address Line 1");
			isValid = false;
		}if(isEmpty(helper.getParameter("address2"))){
			courseProvider.setAddress2("-");
		}if(isEmpty(helper.getParameter("address3"))){
			courseProvider.setAddress3("-");
		}if(isEmpty(helper.getParameter("accountStatus"))){
			errorString.add("Account Status");
			isValid = false;
		}if(isEmpty(helper.getParameter("providerPrivateName"))){
			errorString.add("Provider Name");
			isValid = false;
		}if(isEmpty(helper.getParameter("providerEmail"))){
			errorString.add("Private Email");
			isValid = false;
		}if(isEmpty(helper.getParameter("providerUsername"))){
			errorString.add("Username");
			isValid = false;
		}if(isEmpty(helper.getParameter("providerPassword")) || 
				isEmpty(helper.getParameter("cProviderPassword"))){
			errorString.add("Password fields are empty");
			isValid = false;
		}if(isEmpty(helper.getParameter("accountDescription"))){
			courseProviderAccount.setDescription("-");
		}if(!helper.getParameter("providerPassword").equals(helper.getParameter("cProviderPassword"))){
			helper.setAttribute("errorPassword", "Password fields does not match");
			errorString.add("Password fields does not match");
			isValid = false;
		}if(!validateEmail(helper.getParameter("inquiryMail"))){
			helper.setAttribute("errorInquiryMail", "Empty email address");
			errorString.add("Invalid email");
		}
		
		return null;
		
	}
}