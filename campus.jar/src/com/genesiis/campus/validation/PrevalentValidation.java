package com.genesiis.campus.validation;

//20161111 DN c10-contacting-us-page-MP ContactUsValidation.java is created

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.log4j.Logger;

/**
 * PrevalentValidation intended to provide general validation and it has provided
 * the implementation for the Validatory interface.
 * @author dushantha
 *
 */
public class PrevalentValidation implements Validatory {
	static Logger log = Logger.getLogger(PrevalentValidation.class.getName());
	@Override
	public boolean isNotEmpty(String text) throws Exception{
		boolean status = false;
		if ((text != null) & (text.isEmpty() == false)) {
			status = true;
		}
		throwCustomError(status," Text Field Validation(s) Failed!");
		return status;
	}
	
	@Override
	 public boolean validateEmail(String email) throws Exception {
		boolean validEmail = false;
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"^([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})$");
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		validEmail = matcher.find();
		throwCustomError(validEmail," Email Validation Failed!");
		return validEmail;
	}

	@Override
	public boolean isInteger(String value ) throws Exception {
		boolean isTestPassed = false;
		try {
			Integer.parseInt(value);
			isTestPassed = true;
		} catch (NumberFormatException e) {
			isTestPassed= false;
		} catch (NullPointerException e) {
			isTestPassed= false;
		} finally {
			throwCustomError(isTestPassed,"integer validation of input Failed");
			return isTestPassed;
		}
		
	
	}

	@Override
	public boolean isFloat(String value) throws Exception {
		boolean isTestPassed = false;
		try {
			Float.parseFloat(value);
			isTestPassed = true;
		} catch (NumberFormatException e) {
			isTestPassed= false;
		} catch (NullPointerException e) {
			isTestPassed= false;
		} finally {
			throwCustomError(isTestPassed,"float validation of input Failed");
			return isTestPassed;
		}
	}
	
	@Override
	public boolean isValidPhoneNumber(String number) throws Exception {
		boolean validPhoneNumber = false;
			if((number==null) | (number=="")){
				Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^(\\+\\d{2,3}|0)?\\d{9}$");
				Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(number.replaceAll("\\s+",""));
				validPhoneNumber= matcher.find();
				throwCustomError(validPhoneNumber," Phone number Validation Failed!");
			}
			return validPhoneNumber;
	}
	
	/*
	 * throwCustomError class accepts two parameters and based on the test falsity Exception
	 * will be thrown. 
	 * @author DN
	 * @param test boolean value preferably an out put value of a test.
	 * @param errorMessage String message which should be displayed when toString() is called
	 * on the Exception been thrown.
	 * @throws Exception true Exception been thrown is FailedValidationException 
	 * which is in the context of field validation
	 */
	private void throwCustomError(boolean test,String errorMessage) throws Exception {
		if(!test){
			Exception validationException = new FailedValidationException(errorMessage);
			log.error("throwCustomError(): FailedValidationException: "+validationException.toString() );
			throw validationException;
		}
	}
	
	/**
	 * FailedValidationException an Exception type which
	 * extends Exception and intend for field validation errors
	 * @author DN
	 */
	public class FailedValidationException extends Exception
	{
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	public FailedValidationException(String message)
	  {
	    super(message);
	  }
	}

}
