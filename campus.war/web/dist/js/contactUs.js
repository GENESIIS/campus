// 20161025 DN c10-contacting-us-page created contactUs.jsp 
//20161027 DN c10-contacting-us-page amended isValidEmailFormat(),isValidPhoneNumber() method
//20161109 DN c10-contacting-us-page-MP validateForm() refactor the method
//20161109 DN c10-contacting-us-page-MP refactor clearField() the method
//20161116 DN c10-contacting-us-page-MP methods are exported to /dist/js/institute/validation/validation.js

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

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
 * isValidEmailFormat method validate a email address
 * @returns boolean if testing email address is a valid
 * one then returns true else return false
 */
function isValidEmailFormat(){	
	var emailAddress = document.forms["contactUsForm"]["emailAddress"].value;	
	var pattern =/([\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4})/g;	
	return isPatternMatch(pattern,emailAddress);
}

