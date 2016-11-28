package com.genesiis.campus.validation;

//20161028 CM c9-make-inquiry-for-institute INIT Validator.java
//20161028 CM c9-make-inquiry-for-institute Created validateEmail, validateInquiry, isNotEmpty methods
//20161028 CM c9-make-inquiry-for-institute Created validateInquiry methods
//20161031 CM c9-make-inquiry-for-institute Modified validateInquiry methods

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.entity.model.StudentProgrammeInquiry;
import com.genesiis.campus.util.IDataHelper;

public class Validator {
	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * Check the given value is empty or not empty
	 * 
	 * @author Chathuri
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
	 * Check the given mail address is valid email or not
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate email address.
	 * **/
	public static boolean validateEmail(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
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
	 * **/
	public static String validateInquiry(IDataHelper helper)
			throws ParseException {
		String message = "True";

		if (!(isNotEmpty(helper.getParameter("fullname"))
				|| isNotEmpty(helper.getParameter("email"))
				|| isNotEmpty(helper.getParameter("countryCode"))
				|| isNotEmpty(helper.getParameter("areaCode"))
				|| isNotEmpty(helper.getParameter("telNum"))
				|| isNotEmpty(helper.getParameter("inquiryTitle")) || isNotEmpty(helper
					.getParameter("inquiry")))) {
			message = SystemMessage.EMPTYFIELD.message();
		} else if (!validateEmail(helper.getParameter("email"))) {
			message = SystemMessage.EMAILERROR.message();

		} else if (!isInteger(helper.getParameter("countryCode"))) {
			message = SystemMessage.INVALIDECODE.message();
		} else if (!isInteger(helper.getParameter("areaCode"))) {
			message = SystemMessage.INVALIDEAREA.message();
		} else if (!isInteger(helper.getParameter("telNum"))) {
			message = SystemMessage.PHONENUMBERERROR.message();
		}
		return message;
	}

	/**
	 * Validate institute course program inquiry form data and course inquiry
	 * 
	 * @author anuradha
	 * @param value
	 * @return String to validate is given strings contains a null value.
	 * **/

	public static String validateCourseInquiry(StudentProgrammeInquiry data)
			throws ParseException {
		String message = "True";

		if (!(isNotEmpty(data.getStudentName())
				|| isNotEmpty(data.getStudentEmail())
				|| isNotEmpty(data.getTelephoneCountryCode())
				|| isNotEmpty(data.getTelephoneAreaCode())
				|| isNotEmpty(data.getTelephone())
				|| isNotEmpty(data.getInquiryTitle()) || isNotEmpty(data
					.getInquiry()))) {
			message = SystemMessage.EMPTYFIELD.message();
		} else if (!validateEmail(data.getStudentEmail())) {
			message = SystemMessage.EMAILERROR.message();
		} else if (!isInteger(data.getTelephoneCountryCode())) {
			message = SystemMessage.INVALIDECODE.message();
		} else if (!isInteger(data.getTelephoneAreaCode())) {
			message = SystemMessage.INVALIDEAREA.message();
		} else if (!isInteger(data.getTelephone())) {
			message = SystemMessage.PHONENUMBERERROR.message();
		}
	
		return message;
	}

	/**
	 *  validate student login data
	 * @author anuradha
	 * @param data 
	 * @return String to validate is given strings contains a null value (message)
	 * @throws ParseException
	 * 
	 */
	
	
	public static String validateLogin(Student data) throws ParseException {
		String message = "True";
		log.info(message +""+ data);
		if (!(isNotEmpty(data.getUsername()) 
				|| isNotEmpty(data.getEmail()) 
				|| isNotEmpty(data.getPassword()))) {
			message = SystemMessage.EMPTYFIELD.message();
			log.info(message);
		} else if (!validateEmail(data.getEmail())){
			message = SystemMessage.EMAILERROR.message();
		}
		log.info(message);
		return message;
	}

	/**
	 * Check the given number is valid number or not
	 * 
	 * @author Chathuri
	 * @param value
	 * @return boolean to validate is given string contains a numaric value.
	 * **/
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
}