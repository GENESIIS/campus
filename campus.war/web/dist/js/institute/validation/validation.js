/**
 * 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * 
 */ 
 

/**
 * isFieldFilled() generate a alert if the passing in flag is false else the
 * method acts void
 * 
 * @param flag
 *            expression that evaluates to a boolean
 * @param elementName
 *            string to be append to the produced message
 */

function isFieldFilled(flag, elementName) {
	if (!flag) {
		alert(elementName + "must be filled out Correctly");
	}
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
 *            character long.amd start from 0 method does not test for starting
 *            sequence of the phone number
 * @returns {Boolean}
 */
function isValidPhoneNumber(phoneNumber) {
	var phonenumberPattern = /^0\d{9}$/mg;
	return isPatternMatch(phonenumberPattern, phoneNumber);
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

