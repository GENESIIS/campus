//20161121 DN C18-student-signup-without-using-third-party-application-dn
//create the signUpWoThirdParty.js file to facilitate signUpWoThirdParty.jsp
//20161121 DN C18-student-signup-without-using-third-party-application-dn splitPhoneNumber() method created

var theNewScript = document.createElement("script");
var theSecondScript = document.createElement("script");
theNewScript.type = "text/javascript";
theSecondScript.type = "text/javascript"; 
theNewScript.src = "../../dist/js/institute/validation/validation.js";
theSecondScript.src="../../dist/js/jsonDataExchanger.js";

var mobilePhoneNumber ="";
var mobilePhoneCountryCode="";
var mobilePhoneNetWorkCode="";

/**
 * method validates if the user inputs are correct and according to agreed
 * format, then collect the data and forms a javas cript object finally
 * the JASON data is passed to the server end.
 * @author dushantha DN
 * @returns {Boolean} if validation fails returns false
 */
function sendSignUpCredentialsToBckEnd() {	
	var postaSessation = false;
	if (validateSignUpWoThirdPartyPageEmbedData()) {
		var jsonDataObject = createJasonObject();
		
		
				jsonDataExchange(
						jsonDataObject,
						"post",
						"../../StudentController",
						"SIWOTP",
						"json");
	//call function on the return function how to operate on it	
		
		} else {
		return postaSessation;
	}
}



/**
 * validateSignUpWoThirdPartyPageEmbedData() validates all the current  critical fields
 * placed on /dist/partials/signUpWoThirdParty.jsp page. It's the custom field validator
 * dedicated for the page above.
 * @author dushantha DN
 * @returns {Boolean}
 */
function validateSignUpWoThirdPartyPageEmbedData(){
	var validationPass = true;
	
	if(!(isFieldFilled(isempty($('#firstName').val()),"First Name Field","firstNameError"))){
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#lastName').val()),"Last Name Field","lastNameError"))) {
		return !validationPass;
	} else if(!(isFieldFilled(isempty($('input[type=radio][name=gender]:checked').val()),"gender Field","genderError"))){
		return !validationPass;
	} else if (!isFieldFilled(isValidEmailFormat($('#emailAddress').val()),"Email Field","emailError")) {
		return !validationPass;
	} else if (!(isFieldFilled(isValidPhoneNumber($('#contactNumber').val()),"Phone Number Field","phoneError"))){
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#pathway').val()),"Pathway Field","pathwayError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#userName').val()),"User Name Field","usernameError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#passWord').val()),"Password Field","passWordError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#confrmpsw').val()),"Confirm Password Field","confPassWordError"))){
		return !validationPass;
	} else if(!(isFieldFilled(passwordAndConfirmPassword($('#passWord').val(),$('#confrmpsw').val()),"PassWords Does Not Match ,The Field(s)","confPassWordError"))){
		return !validationPass;
	} else if (!(isFieldFilled($('#policyConfirm').prop('checked'),"policy Check box","policyConfirmError"))) {
		return !validationPass;
	} 
		return validationPass;
	
}

/**
 * the purpose of the method is to create the java script object
 * @author dushantha DN
 * @returns {___anonymous2641_2981}
 */

function createJasonObject(){
	splitPhoneNumber($('#contactNumber').val());
	var jsonData ={
			"firstName" :$('#firstName').val(),
			"lastName"  :$('#lastName').val(),
			"gender"	: $('input[type=radio][name=gender]:checked').val(),
			"email"		:$('#emailAddress').val(),
			"mobilePhoneNo":mobilePhoneNumber,
			"mobileCountryCode":mobilePhoneCountryCode,
			"mobileNetworkCode":mobilePhoneNetWorkCode,
			"pathway"	:$('#pathway').val(),
			"userName"	:$('#userName').val(),
			"passWord"	:$('#passWord').val(),
			"confirmPw"	:$('#confrmpsw').val(),
			"isPolicyConfirm"	:$('#policyConfirm').prop('checked')
	};
	return jsonData;
}

/**
 * convertPassWordToString() method displays the pass word to text
 * @author dushantha DN 
 * @param checkboxId check box id which used to toggle the command
 * @param passWordElementId password field element id 
 * @param confirmWordElementId confirming field element id
 * @returns void
 */

function convertPassWordToString(checkboxId,passWordElementId,confirmWordElementId){
	var value =($('#'+checkboxId).is(':checked'))?"text":"password";
	$('#'+passWordElementId).attr("type",value);
	$('#'+confirmWordElementId).attr("type",value);
}

/**
 * Here the method confirms if both fields contain logically
 * equal string values.
 * @author dushantha DN
 * @param password password field element id
 * @param reconfirmPassWord confirming field element id
 * @returns {Boolean} if the both fields are logically equal, then,
 * returns true else false.
 */
function passwordAndConfirmPassword(password, reconfirmPassWord){
	var passwordTrimed = password.trim();
	var isBothValueAreIdentical= false;
	if(passwordTrimed!=null|passwordTrimed !=""){
		isBothValueAreIdentical= (passwordTrimed === reconfirmPassWord.trim()) ;
	}
	return isBothValueAreIdentical;
}


/**
 * splitPhoneNumber() separate the phone
 * number in to country code; network code an phone number
 * if the phone number is of the form 01234567898 or 123456789 format
 * the country code will not be populated.
 * @param phoneNumber
 */
function splitPhoneNumber(phoneNumber){
		mobilePhoneNumber="";
		mobilePhoneNetWorkCode="";
		mobilePhoneCountryCode="";
	 var trimedPhoneNumber = phoneNumber.trim().replace(/\s+/g, "");
	 var length = trimedPhoneNumber.length;
	 switch(length){
	 case 9: 
		 mobilePhoneNumber = trimedPhoneNumber.substr(2);
		 mobilePhoneNetWorkCode = trimedPhoneNumber.substr(0, 2);
		 break;
	 case 10:
		 mobilePhoneNumber = trimedPhoneNumber.substr(3);
		 mobilePhoneNetWorkCode = trimedPhoneNumber.substr(0, 3);
		 break;
	 case 12:
		 var phonenumberPattern=/^\+/g;
		 if(isPatternMatch(phonenumberPattern,phoneNumber)){
			 mobilePhoneNumber = trimedPhoneNumber.substr(5);
			 mobilePhoneNetWorkCode = trimedPhoneNumber.substr(3,2);
			 mobilePhoneCountryCode = trimedPhoneNumber.substr(0, 3);
		 }
		 break;
	 case 13:
		 var phonenumberPattern=/(^00\d{2})/g;
		 if(isPatternMatch(phonenumberPattern,phoneNumber)){
			 mobilePhoneNumber = trimedPhoneNumber.substr(6);
			 mobilePhoneNetWorkCode = trimedPhoneNumber.substr(4,2);
			 mobilePhoneCountryCode = trimedPhoneNumber.substr(0, 4);
		 }
		 break;
	 
	 }
	
}

