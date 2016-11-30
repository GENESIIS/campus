package com.genesiis.campus.validation;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.genesiis.campus.entity.model.Student;

public class LoginValidator {
	static Logger log = Logger.getLogger(LoginValidator.class.getName());
	
	/**
	 * @author anuradha
	 * @param email
	 * @return boolean (validEmail)
	 * email validation method 
	 */
	 private static boolean validateEmail(String email) {
		 boolean validEmail = false;
			Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
					"^([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})$");
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.trim());
			validEmail = matcher.find(); 
			return validEmail;
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
		
		try{
			
		PrevalentValidation pv = new PrevalentValidation();
//		if(validateEmail(data.getUserKey())){
//			log.info("Email is : "+data.getUserKey());
//			
//		}else if(!validateEmail(data.getUserKey())){
//			data.setUsername(data.getUserKey());
//			log.info("Login Username is : "+data.getUsername());
//			
//		}
		
		if(pv.isNotEmpty(data.getUserKey()) || pv.isNotEmpty(data.getPassword())){
			message = SystemMessage.EMPTYFIELD.message();
			log.info(message);
		}
		
//		log.info(message +""+ data);
//		if (!(isNotEmpty(data.getUserKey()) 
//				|| isNotEmpty(data.getEmail()) 
//				|| isNotEmpty(data.getPassword()))) {
//			message = SystemMessage.EMPTYFIELD.message();
//			log.info(message);
//		} else if (!validateEmail(data.getEmail())){
//			message = SystemMessage.EMAILERROR.message();
//		}
		
		}catch(Exception e){
			log.info("validateLogin : "+e);
		}
		
		log.info(message);
		return message;
	}
	
	/**
	 * @author anuradha
	 * @param data
	 * @return data object
	 * @throws ParseException
	 */
	public static Student dataSeparator(Student data) throws ParseException {
		
		if(validateEmail(data.getUserKey())){
			log.info("Email is : "+data.getUserKey());
			
		}else if(!validateEmail(data.getUserKey())){
			data.setUsername(data.getUserKey());
			log.info("Login Username is : "+data.getUsername());
			
		}
		
		return data;
	}


}
