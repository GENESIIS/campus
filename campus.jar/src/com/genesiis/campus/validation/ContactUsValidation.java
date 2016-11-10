package com.genesiis.campus.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.genesiis.campus.command.CmdGenerateEmail;
import org.apache.log4j.Logger;

public class ContactUsValidation implements Validatory {
	static Logger log = Logger.getLogger(ContactUsValidation.class.getName());
	@Override
	public boolean isNotEmpty(String text) throws Exception{
		boolean status = false;
		if ((text != null) & (text.isEmpty() == false)) {
			status = true;
		}
		throwCustomError(status," Text Field Validation(s) faied!");
		return status;
	}
	
	@Override
	public boolean validateEmail(String email) throws Exception {
		boolean validEmail = false;
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		validEmail = matcher.find();
		throwCustomError(validEmail," Email Validation faied!");
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
			Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^0\\d{9}$");
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(number);
			validPhoneNumber= matcher.find();
			throwCustomError(validPhoneNumber," Phone number Validation faied!");
			return validPhoneNumber;
		
	}
	
	private void throwCustomError(boolean test,String errorMessage) throws Exception {
		if(!test){
			Exception vslidationException = new FailedValidationException(errorMessage);
			log.error("throwCustomError(): FailedValidationException: "+vslidationException.toString() );
			throw vslidationException;
		}
	}
	
	
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
