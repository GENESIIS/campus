package com.genesiis.campus.validation;
//20161220 as c19-student-login-without-using-third-party-application-as removed unwanted comments and loggers
//20170317 AS c23-admin-login-logout-function-as validateLogin(Admin data) and dataSeparator(Admin data) methods added
//20170508 AS c99-tutor-login-function-as-back end validation WIP, dataSeparator(Tutor data) completed
import com.genesiis.campus.entity.model.Admin;
import com.genesiis.campus.entity.model.Student;
import com.genesiis.campus.entity.model.Tutor;

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
	private static boolean validateEmail(String email) {
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
	 * validate admin login data
	 * 
	 * @author anuradha
	 * @param data
	 * @return String to validate is given strings contains a null value
	 *         (message)
	 * @throws ParseException
	 * 
	 */

	public static String validateLogin(Admin data) throws ParseException {
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
	
	/**
	 * @author anuradha
	 * @param data
	 * @return data object
	 * @throws ParseException
	 */
	public static Tutor dataSeparator(Tutor data) throws Exception {
		try {
			if (validateEmail(data.getUserKey())) {
				
				data.setEmailAddress(data.getUserKey());
			} else if (!validateEmail(data.getUserKey())) {
				data.setUsername(data.getUserKey());
				
			}
		} catch (Exception e) {
			log.error("dataSeparator : " + e);

		}

		return data;
	}
	
	/**
	 * @author anuradha
	 * @param data
	 * @return data object
	 * @throws ParseException
	 */
	public static Admin dataSeparator(Admin data) throws Exception {
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
