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
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue) {

	return ((fieldValue == "") || (fieldValue == null)) ? false : true;
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
 * 
 * @param phoneNumber
 *            it's the value of phone number field phone number should be 10
 *            character long.This test will pass the format
 *            +94xxxxxxxxx
 *            +674xxxxxxxxx
 *            0xxxxxxxxx
 *            xxxxxxxxx
 *            sequence of the phone number
 * @returns {Boolean}
 */
function isValidPhoneNumber(phoneNumber) {
	var phonenumberPattern = /^(\+\d{2,3}|0)?\d{9}$/mg;
	return isPatternMatch(phonenumberPattern, phoneNumber.replace(/\s+/g, ""));
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