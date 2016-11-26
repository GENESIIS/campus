package com.genesiis.campus.validation;

//20161028 CM c9-make-inquiry-for-institute INIT Validator.java
//20161028 CM c9-make-inquiry-for-institute Created validateEmail, validateInquiry, isNotEmpty methods
//20161028 CM c9-make-inquiry-for-institute Created validateInquiry() methods
//20161031 CM c9-make-inquiry-for-institute Modified validateInquiry() methods
//20161031 CM c9-make-inquiry-for-institute Renamed validateInquiry() methods as validateInstituteInquiry() method
//20161126 PN c26-add-student-details: copied Validator.java class from c9 branch and implemented validateSchoolEduData(SchoolEducation education) method. 

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.SchoolEducation;
import com.genesiis.campus.util.IDataHelper;

public class Validator {
	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Check the given value is empty or not empty
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate is given string contains a null value.
	 **/
	public static boolean isNotEmpty(String text) {
		boolean status = false;
		if ((text != null) && (text.isEmpty() == false)) {
			status = true;
		}
		return status;
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
	 * Validate institute course provider inquiry form data
	 * 
	 * @author Chathuri
	 * @param value
	 * @return String to validate is given strings contains a null value.
	 **/
	public static String validateInstituteInquiry(IDataHelper helper) throws ParseException {
		String message = "True";

		if (!(isNotEmpty(helper.getParameter("fullname")) || isNotEmpty(helper.getParameter("email"))
				|| isNotEmpty(helper.getParameter("inquiryTitle")) || isNotEmpty(helper.getParameter("inquiry")))) {
			message = SystemMessage.EMPTYFIELD.message();
		} else if (!validateEmail(helper.getParameter("email"))) {
			message = SystemMessage.EMAILERROR.message();
		}
		return message;
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
	 * This method is to validate SchoolEducation form of a student.
	 * 
	 * @author pabodha
	 * @param education
	 * @return Map<String, Boolean>
	 */
	public static Map<String, Boolean> validateSchoolEduData(SchoolEducation education) {
		Map<String, Boolean> errors = new HashMap<>();
		errors.put("IndexNO", isNotEmpty(education.getIndexNo()));
		errors.put("School name", isNotEmpty(education.getSchoolName()));
		errors.put("Achived on date", isNotEmpty(String.valueOf(education.getAchievedOn())));
		errors.put("Qualification", isNotEmpty(Integer.toString(education.getSchoolGrade())));
		errors.put("Stream", isNotEmpty(Integer.toString(education.getMajor())));
		errors.put("Result", isNotEmpty(Integer.toString(education.getResult())));
		errors.put("Medium", isNotEmpty(Integer.toString(education.getMedium())));
		return errors;
	}
}