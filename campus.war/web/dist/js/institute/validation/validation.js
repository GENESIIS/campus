/**
 * 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * 20170228 TR C22 added .removeClass jquery line into clearField function 
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
	$('#emailveryMessage').removeClass("fp-msg-error");
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
		var testableRegularExpression = /^([a-zA-Z]+)([a-zA-Z0-9_]+){7,}$/g;
		validCharAndLength= isPatternMatch(testableRegularExpression,testableInput.trim()); 
	}
	return validCharAndLength;
}
