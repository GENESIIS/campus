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
	var fullname = $("#fullname").val();
	var email = $("#email").val();
	var countryCode = $("#countryCode").val();
	var areaCode = $("#areaCode").val();
	var telephoneNumber = $("#telNum").val();
	var inquiryTitle = $("#inquiryTitle").val();
	var inquiry = $("#inquiry").val();
	var courseProvider = $("#courseProviderCode").val();
	var student = $("#studentCode").val();
	var response = grecaptcha.getResponse();
	var wasSubmitted = false;

	if (!isempty(fullname)) {
		document.getElementById('fullNametbError').innerHTML = "**Full name cannot be empty.";
		return false;
	} else if (!isempty(email)) {
		document.getElementById('emailtbError').innerHTML = "**Email cannot be empty.";
		return false;
	} else if (!isempty(countryCode)) {
		document.getElementById('emailtbError').innerHTML = "**Country code cannot be empty.";
		return false;
	} else if (!isempty(areaCode)) {
		document.getElementById('countryCodetbError').innerHTML = "**Area code cannot be empty.";
		return false;
	} else if (!isempty(telephoneNumber)) {
		document.getElementById('areaCodetbError').innerHTML = "**telephone number cannot be empty.";
		return false;
	} else if (!isempty(inquiryTitle)) {
		document.getElementById('"inquiryTitletbError"').innerHTML = "**Inquiry title cannot be empty.";
		return false;
	} else if (!isempty(inquiry)) {
		document.getElementById('"inquirytbError"').innerHTML = "**Inquiry cannot be empty.";
		return false;
	} else if (response.length == 0) {
		alert("You can't leave Captcha Code empty");
		return false;
	} else if (!isValidEmailFormat(email)) {
		document.getElementById('emailtbError').innerHTML = "**Invalid Email.";
		return false;
	} else if (isNaN(countryCode)) {
		document.getElementById('countryCodetbError').innerHTML = "**Invalid Country code.";
		return false;
	} else if (isNaN(areaCode)) {
		document.getElementById('areaCodetbError').innerHTML = "**Invalid Area code.";
		return false;
	} else if (isNaN(telephoneNumber)) {
		document.getElementById('telNumtbError').innerHTML = "**Invalid telephone number.";
		return false;
	} else {
	//	return true;
	}

}