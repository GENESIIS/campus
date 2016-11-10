package com.genesiis.campus.validation;



public interface Validatory {
	
	public boolean isNotEmpty(String text) throws Exception;
	public boolean validateEmail(String email) throws Exception;
	public boolean isInteger(String value) throws Exception;
	public boolean isFloat(String value) throws Exception;
	public boolean isValidPhoneNumber(String number) throws Exception;

}
