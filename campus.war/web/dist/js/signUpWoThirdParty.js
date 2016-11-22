//20161121 DN  DN C18-student-signup-without-using-third-party-application-dn
//create the signUpWoThirdParty.js file to facilitate signUpWoThirdParty.jsp


var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";
theNewScript.src = "../../dist/js/jsonDataExchanger.js";



//Global variables for signUpWoThirdParty.js file declared
var firstName = jQuery('#firstName').val();
//alert("firstName :"+firstName+" :");
//alert("jQuery('#firstName').text() :"+jQuery('#firstName').text()+" :");
//var lastName= jQuery('#lastName').text();
//var gender = jQuery('input:radio[anme="gender"]:checked').val();
//var emailAdress = jQuery('#emailAddress').text();
//var contactNumber = jQuery('#contactNumber').text();
//var pathway = jQuery('#pathway option:selected').text();
//var userName = jQuery('#username').text();
//var passWord = jQuery('#psw').text();
//var confirmPw = jQuery('#confrmpsw').text();
//var showPassWordChkBxVal = jQuery('#showpasscheckbox').val();
//var isPolicyConvirm = jQuery('#policyConfirm').val();


function sendSignUpCredentialsToBckEnd() {
	alert("Transfer");	
	var postaSessation = false;
	if (validateSignUpWoThirdPartyPageEmbedData()) {
		var jsonDataObject = createJasonObject();
		jsonDataExchange(
				jsonDataObject,
				"post",
				"com/genesiis/campus/studentController/StudentController",
				"SIWOTP",
				"json");
	} else {
		return postaSessation;

	}
}


function validateSignUpWoThirdPartyPageEmbedData(){
	var validationPass = true;
	alert("#firstName "+ document.getElementById("firstName").value);
	alert("#firstName jq "+ $('#firstName').val());
	firstName = $('#firstName').val();
	if(!(isFieldFilled(isempty(firstName),"First Name Field","firstNameLabel"))){	
		alert("firstname fail"+!validationPass);
		return !validationPass;
	} 
//	else if (!(isFieldFilled(isempty(lastName),"Last Name Field","lastNameLabel"))) {
//		alert("last name fail");
//		return !validationPass;
//	} else if(!(isFieldFilled(isempty(gender),"gender Field","genderLabel"))){
//		alert("gender fail");
//		return !validationPass;
//	} else if (!isFieldFilled(isValidEmailFormat(),"Email Field","emilAddressLabel")) {
//		alert("email fail");
//		return !validationPass;
//	} else if (!(isFieldFilled(isValidPhoneNumber(contactNumber),"Phone Number Field","contactNumberLabel"))){
//		alert("phone number fail");
//		return !validationPass;
//	} else if (!(isFieldFilled(isValidPhoneNumber(pathway),"Pathway Field","pathwayLabel"))) {
//		alert("pathway fail");
//		return !validationPass;
//	} else if (!(isFieldFilled(isValidPhoneNumber(userName),"User Name Field","usernameError"))) {
//		alert("userName fail");
//		return !validationPass;
//	} else if (!(isFieldFilled(isValidPhoneNumber(passWordError),"Password Field","passWordError"))) {
//		alert("password fail");
//		return !validationPass;
//	} else if (!(isFieldFilled(isValidPhoneNumber(confirmPw),"Confirm Password Field","confPassWordError"))){
//		alert("Confirm Password fail");
//		return !validationPass;
//	} else if (!(isFieldFilled(isValidPhoneNumber(isPolicyConvirm),"policy Check box","policyConfirmError"))) {
//		alert("policy fail");
//		return !validationPass;
//	} 
		else{
		alert("EVERYTHING PASS");
		return validationPass;
	}	
}


function createJasonObject(){
	var jsonData ={
			"firstName" :firstName,
			"lastName"  :lastName,
			"gender"	: gender,
			"emailAdress":emailAdress,
			"contactNumber":contactNumber,
			"pathway"	:pathway,
			"userName"	:userName,
			"passWord"	:passWord,
			"confirmPw"	:confirmPw,
			"showPassWordChkBxVal":showPassWordChkBxVal,
			"isPolicyConvirm"	:isPolicyConvirm
	};
	return jsonData;
}

function convertPassWordToString(){
	
	var checked = document.getElementById("showpasscheckbox").checked;
	var value =(checked)?"text":"password";
	$('#confrmpsw').attr("type",value);
	$('#passWord').attr("type",value);
 
}