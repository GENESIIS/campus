package com.genesiis.campus.validation;

//20161028 CM c9-make-inquiry-for-institute INIT Validator.java
//20161028 CM c9-make-inquiry-for-institute Created validateEmail, validateInquiry, isNotEmpty methods

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

import com.genesiis.campus.util.IDataHelper;

public class Validator {
	static Logger log = Logger.getLogger(Validator.class.getName());

	/**
	 * @author CM
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
	 * @author CM
	 * @param value
	 * @return boolean to validatem email address.
	 * **/
	public static boolean validateEmail(String email) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();

	}

	/**
	 * Validate institute inquiry data
	 * @author CM
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
				|| isNotEmpty(helper.getParameter("inquiryTitle"))
				|| isNotEmpty(helper.getParameter("inquiry")))) {
			message = SystemMessage.EMPTYFIELD.message();
		}else if(!validateEmail(helper.getParameter("email"))){
			message=SystemMessage.EMAILERROR.message();
			
		}
		return message;
	}

}
