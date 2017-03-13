package com.genesiis.campus.validation;


//20161111 DN c10-contacting-us-page-MP ContactUsValidation.java is created
//20161122 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
//cater more phone number styles
//20161123 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
//cater more phone number styles with spaces in between.
//20161124 DN c10-contacting-us-page-MP  changed the regular expression to accept only +(2 digit)(9-digit)
//20161128 DN isValidPhoneNumber(),validateEmail() and isNotEmpty() methods changed to use trim() on passing parameter.
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if ((text != null) & (text.trim().isEmpty() == false)) {
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
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.trim());
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
	
	/**
	 * isValidPhoneNumber validates if the phone numbers are in correct format
	 * +94(9-digit number) eg. +94123456789,+94 123 456 789,
	 * 0094(9-digit number)e.g 0094123456789 0094 123 456 789 
	 * 777453052 (without the leading zero including spaces within the number
	 * 0777453052 (with leading  zero and 9 digit number)
	 * <b> if the State rule changes in a such a way that <i>the number of digits </i>for local telephone number to be changed<b>
	 * then validation fails.
	 * @author dushantha DN
	 * @param numberToValidate Telephone number to be validated
	 * @return boolean if passes true else false
	 */
	@Override
	public boolean isValidPhoneNumber(String numberToValidate) throws Exception {
		boolean validPhoneNumber = false;
			if((numberToValidate!=null) | (numberToValidate.trim()!="")){
				Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^(00\\d{2}|\\+\\d{2}|0)?\\d{9}$");
				Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(numberToValidate.replaceAll("\\s+",""));
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
	public void throwCustomError(boolean test,String errorMessage) throws Exception {
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