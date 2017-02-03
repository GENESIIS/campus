package com.genesiis.campus.validation;
//20161220 as c19-student-login-without-using-third-party-application-as removed unwanted comments and loggers
//20170202 AS C22 validateEmail(String email) private method changed to public
import com.genesiis.campus.entity.model.Student;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {
	static Logger log = Logger.getLogger(LoginValidator.class.getName());

	/**
	 * @author anuradha
	 * @param email
	 * @return boolean (validEmail) email validation method
	 */
	public static boolean validateEmail(String email) {
		boolean validEmail = false;
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
				.compile("^([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})$");
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.trim());
		validEmail = matcher.find();
		return validEmail;
	}

	/**
	 * validate student login data
	 * 
	 * @author anuradha
	 * @param data
	 * @return String to validate is given strings contains a null value
	 *         (message)
	 * @throws ParseException
	 * 
	 */

	public static String validateLogin(Student data) throws ParseException {
		String message = "True";

		try {

			Validatory vl = new PrevalentValidation();

			if (!vl.isNotEmpty(data.getUserKey())
					|| !vl.isNotEmpty(data.getPassword())) {
				message = SystemMessage.EMPTYFIELD.message();

			} else {
				message = "True";

			}

		
			
		} catch (Exception e) {
			log.error("validateLogin : " + e);
		
		}

		return message;
	}

	/**
	 * @author anuradha
	 * @param data
	 * @return data object
	 * @throws ParseException
	 */
	public static Student dataSeparator(Student data) throws Exception {
		try {
			if (validateEmail(data.getUserKey())) {
				
				data.setEmail(data.getUserKey());
			} else if (!validateEmail(data.getUserKey())) {
				data.setUsername(data.getUserKey());
				
			}
		} catch (Exception e) {
			log.error("dataSeparator : " + e);

		}

		return data;
	}

}
