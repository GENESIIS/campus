// 20161025 DN c10-contacting-us-page created contactUs.jsp 
//20161027 DN c10-contacting-us-page amended isValidEmailFormat(),isValidPhoneNumber() method
//20161109 DN c10-contacting-us-page-MP validateForm() refactor the method


function validateForm(){

	 if(!(isFieldFilled(isempty(document.contactUsForm.firstName.value),"First Name Field","firstNameError"))){
		return false;
	}else if (!(isFieldFilled(isempty(document.contactUsForm.lastName.value),"Last Name Field","lastNameError"))) {
		return false
	} else if(!(isFieldFilled(isValidPhoneNumber(document.contactUsForm.contactNumber.value),"Phone Number Field","phoneNumberError"))){
		return false;
	} else if(!isFieldFilled(isValidEmailFormat(),"Email Field","emailError")) {
		return false;
	} else if (!isFieldFilled(isempty(document.contactUsForm.subject.value),"Subject Field","subjectError")){
		return false;
	}else if (!isFieldFilled(isempty(document.contactUsForm.message.value),"message Field","userMessageError")) {
		return false;
	}else if (!isFieldFilled(isHumanTestPassed(),"I'm Not A Robot","captureError")) {
		
	} else {
		
	}
}

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