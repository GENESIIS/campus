/**
 *20161027 AS C8-inquiry-form-for-course validation.js  created.
 *20161027 DN c10-contacting-us-page amended isValidEmailFormat(),isValidPhoneNumber() method
 *20161109 DN c10-contacting-us-page-MP validateForm() refactor the method
 *20161109 DN c10-contacting-us-page-MP refactor clearField() the method
 *20161116 DN c10-contacting-us-page-MP changed the method isFieldFilled() to write content to inner HTML
 *20161116 DN c10-contacting-us-page-MP included clearField() method 
 *20161122 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
 * cater more phone number styles
 * 20161123 DN c10-contacting-us-page-MP isValidPhoneNumber() regular expression changed to 
 *cater more phone number styles with spaces in between.
 *20161123 DN c10-contacting-us-page-MP  changed the regular expression to accept only +(2 digit)(9-digit)
 *20161128 DN c10-contacting-us-page-MP isempty() changed to validate any field submitting spaces.
 *20161202 DN C18-student-signup-without-using-third-party-application-test-dn add isStringHasValiCharsAndLength() method
 */ 

 

/**
 * isHumanTestPassed(0 is a primary function that 
 * matches the entered value against a hard coded 
 * value.
 * @returns boolean true if the human test passes
 * else false
 */

function isHumanTestPassed(){	
	var response = grecaptcha.getResponse();
	return(response.length!=0)?true:false;
	
	
}

/**
 * isFieldFilled() generate a alert if the passing in 
 * flag is false else the method acts void
 * @param flag expression that evaluates to a boolean
 * @param elementName  string to be append to the produced message
 */

function isFieldFilled(flag, elementName, errorLabelId){		
	if(!flag){	
		document.getElementById(errorLabelId).innerHTML = elementName + " Must Be filled Out Correctly!";		
	}
	return flag;
}

/**
 * 
 * @param fieldValue it is the value of a document element
 * method avoid if a string with spaces are passed in
 * @returns true if has content else false
 */
function isempty(fieldValue) {

	return ((fieldValue.trim() == "") || (fieldValue == null)) ? false : true;
}

/**
 * isValidEmailFormat method validate a email address
 * 
 * @returns boolean if testing email address is a valid one then returns true
 *          else return false
 */
function isValidEmailFormat(email) {
	var emailAddress = email;
	var pattern = /([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})/g;
	return isPatternMatch(pattern, emailAddress);
}

/**
 * isValidPhoneNumber validates if the phone numbers are in correct format
 * +94(9-digit number) eg. +94123456789,+94 123 456 789,
 * 0094(9-digit number)e.g 0094123456789 0094 123 456 789 
 * 777453052 (without the leading zero including spaces within the number
 * 0777453052 (with leading  zero and 9 digit number)
 * <b> if the State rule changes in a such a way that <i>the number of digits </i>for local telephone number to be changed<b>
 * then validation fails
 * @author dushantha DN
 * @param numberToValidate Telephone number to be validated
 * @return boolean if passes true else false
 */
function isValidPhoneNumber(phoneNumber) {
	var isValidPhoneNumber = false;
	if((phoneNumber!=null) | (phoneNumber!="")){
		var phonenumberPattern = /^(00\d{2}|\+\d{2}|0)?\d{9}$/mg;
		isValidPhoneNumber = isPatternMatch(phonenumberPattern, phoneNumber.replace(/\s+/g, ""));
	}
	return isValidPhoneNumber;
}

/**
 * @param regularExpression
 *            pattern
 * @param source
 *            content to act as the source to be matched against the pattern
 * @returns boolean if matches true else false
 */
function isPatternMatch(regularExpression, source) {
	return regularExpression.test(source);

}
/**
 * @param clearField 
 * @param elementId the id of the HTML element
 */

function clearField(elementId){	
	 $(document).find('#' + elementId).text('');
}


/**
 * isValidEmailFormat method validate a email address
 * @returns boolean if testing email address is a valid
 * one then returns true else return false
 */
function validEmailFormat(){	
	var emailAddress = document.forms["contactUsForm"]["emailAddress"].value;	
	var pattern =/([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})/g;	
	return isPatternMatch(pattern,emailAddress);
}

/**
 * method tests if the supply string consists of alpha numeric characters which is
 * 62 case-sensitive characters (A-Z, a-z and 0-9)and "_" character in such a combination that
 * the string contains more than 5 characters,starts with an alphabetic contains any combination of 
 * alphanumeric and _. Further testableInput should not contains any special characters such as "@,#%$" etc
 * @author dushantha DN
 * @param testableInput the sting which is to tested to confirm if it abides the above precondition
 * @returns boolean : true if conditions are met else false.
 */
function isStringHasValiCharsAndLength(testableInput){
	var validCharAndLength= false;
	if(testableInput!=""|testableInput!=null){
		var testableRegularExpression = /^([a-zA-Z]+)([a-zA-Z0-9_]+){5,}$/g;
		validCharAndLength= isPatternMatch(testableRegularExpression,testableInput.trim()); 
	}
	return validCharAndLength;
}



