// 20161025 DN c10-contacting-us-page created contactUs.jsp 
//20161027 DN c10-contacting-us-page amended isValidEmailFormat(),isValidPhoneNumber() method


function validateForm(){
	isFieldFilled(isempty(document.contactUsForm.firstName.value),"First Name Field");
	isFieldFilled(isempty(document.contactUsForm.lastName.value),"Last Name Field");
	isFieldFilled(isValidEmailFormat(),"Email Field");
	isFieldFilled(isValidPhoneNumber(document.contactUsForm.contactNumber.value),"Phone Number Field");	
	isFieldFilled(isempty(document.contactUsForm.subject.value),"Subject Field");
	isFieldFilled(isempty(document.contactUsForm.message.value),"message Field");
	isFieldFilled(isHumanTestPassed(),"Correctly 10-4");
}

/**
 * isHumanTestPassed(0 is a primary function that 
 * matches the entered value against a hard coded 
 * value.
 * @returns boolean true if the human test passes
 * else false
 */

function isHumanTestPassed(){	
	return (document.contactUsForm.humanTest.value == 6)?true : false;
	
}
/**
 * isFieldFilled() generate a alert if the passing in 
 * flag is false else the method acts void
 * @param flag expression that evaluates to a boolean
 * @param elementName  string to be append to the produced message
 */

function isFieldFilled(flag, elementName){	
	if(!flag){
		alert(elementName+ "must be filled out Correctly");
	}
}

/**
 * 
 * @param fieldValue it is the value of a document element
 * @returns true if has content else false
 */
function isempty(fieldValue){	
	return ((fieldValue == "") ||(fieldValue == null) )?false : true;
}

/**
 * isValidEmailFormat method validate a email address
 * @returns boolean if testing email address is a valid
 * one then returns true else return false
 */
function isValidEmailFormat(){	
	var emailAddress = document.forms["contactUsForm"]["emailAddress"].value;	
	var pattern =/([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})/g;	
	return isPatternMatch(pattern,emailAddress);
}
/**
 * 
 * @param phoneNumber it's the value of phone number field
 * phone number should be 10 character long.amd start from 0
 * method does not test for starting sequence of the phone number
 * @returns {Boolean}
 */
function isValidPhoneNumber(phoneNumber){
	var phonenumberPattern= /^0\d{9}$/mg;
	return isPatternMatch(phonenumberPattern,phoneNumber);
}

/**
 * @param regularExpression pattern
 * @param source content to act as the source to be matched against the pattern
 * @returns boolean if matches true else false
 */
function isPatternMatch(regularExpression,source){	
	return regularExpression.test(source);
	
}