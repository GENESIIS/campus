// 20161025 DN c10-contacting-us-page created contactUs.jsp 


function validateForm(){
	isFieldFilled(isempty(document.contactUsForm.firstName.value),"First Name Field");
	isFieldFilled(isempty(document.contactUsForm.lastName.value),"Last Name Field");
	//isFieldFilled(isValidEmailFormat(),"Email Field");
	isFieldFilled(isValidPhoneNumber(document.contactUsForm.contactNumber.value),"Phone Number Field");	
	isFieldFilled(isempty(document.contactUsForm.subject.value),"Subject Field");
	isFieldFilled(isempty(document.contactUsForm.message.value),"message Field");
	isFieldFilled(isHumanTest(),"Correctly 10-4");
}


function isHumanTest(){
	return (document.contactUsForm.humanTest.value == 6)?false : true;
	
}


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
	alert("in the isValidEmailFormat()");
	var emailAddress = document.forms["contactUsForm"]["emailAddress"].value;	
	alert("emailAddress the pattern isValidEmailFormat()");
	var pattern = /([\w-\.]+)@(?:[\w]+\.)+)([a-zA-Z]{2,4})/;
	alert("passing the pattern isValidEmailFormat()");
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
	alert("in the isValidPhoneNumber()");
	var phonenumberPattern= /^0\d{9}$/;
	return isPatternMatch(phonenumberPattern,phoneNumber);
}

/**
 * @param regularExpression pattern
 * @param source content to act as the source to be matched against the pattern
 * @returns boolean if matches true else false
 */
function isPatternMatch(regularExpression,source){
	alert("in the isPatternMatch()");
	return regularExpression.test(source);
	
}