/**
 * 
 * //20161027 AS C8-inquiry-form-for-course validation.js  created.
 * //20161108 CM  c9-make-inquiry-for-institute added validateInstituteInquiryFileds() function
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
 * Validate institute inquiry fileds
 * 
 * @returns {Boolean}
 */
function validateInstituteInquiryFileds() {
	var fullname = $("#input-firstName").val();
	var email = $("#eMail").val();
	var countryCode = $("#input-county-code").val();
	var areaCode = $("#input-area-code").val();
	var telephoneNumber = $("#input-tp-no").val();
	var inquiryTitle = $("#input-inquiry-title").val();
	var inquiry = $("#text-userMessage").val();
	var response = grecaptcha.getResponse();
	var wasSubmitted = false;

	if (!isempty(fullname)) {
		document.getElementById('fullNameError').innerHTML = "**Full name cannot be empty.";
		return false;
	} else if (!isempty(email)) {
		document.getElementById('emailError').innerHTML = "**Email cannot be empty.";
		return false;
	} else if (!isempty(countryCode)) {
		document.getElementById('countryCodeError').innerHTML = "**Country code cannot be empty.";
		return false;
	} else if (!isempty(areaCode)) {
		document.getElementById('areaCodeError').innerHTML = "**Area code cannot be empty.";
		return false;
	} else if (!isempty(telephoneNumber)) {
		document.getElementById('telNumError').innerHTML = "**telephone number cannot be empty.";
		return false;
	} else if (!isempty(inquiryTitle)) {
		document.getElementById('"inquiryTitleError"').innerHTML = "**Inquiry title cannot be empty.";
		return false;
	} else if (!isempty(inquiry)) {
		document.getElementById('"inquiryError"').innerHTML = "**Inquiry cannot be empty.";
		return false;
	} else if (response.length == 0) {
		document.getElementById('captchaError').innerHTML = "**Please verify You're not a robot.";
		return false;
	} else if (!isValidEmailFormat(email)) {
		document.getElementById('emailError').innerHTML = "**Invalid Email.";
		return false;
	} else if (isNaN(countryCode)) {
		document.getElementById('countryCodeError').innerHTML = "**Invalid Country code.";
		return false;
	} else if (isNaN(areaCode)) {
		document.getElementById('areaCodeError').innerHTML = "**Invalid Area code.";
		return false;
	} else if (isNaN(telephoneNumber)) {
		document.getElementById('telNumError').innerHTML = "**Invalid telephone number.";
		return false;
	} else {
		// return true;
	}

}