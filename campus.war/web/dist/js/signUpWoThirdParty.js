//20161121 DN C18-student-signup-without-using-third-party-application-dn
//create the signUpWoThirdParty.js file to facilitate signUpWoThirdParty.jsp


var theNewScript = document.createElement("script");
var theSecondScript = document.createElement("script");
theNewScript.type = "text/javascript";
theSecondScript.type = "text/javascript"; 
theNewScript.src = "../../dist/js/institute/validation/validation.js";
theSecondScript.src="../../dist/js/jsonDataExchanger.js";



/**
 * method validates if the user inputs are correct and according to agreed
 * format, then collect the data and forms a javas cript object finally
 * the JASON data is passed to the server end.
 * @author dushantha DN
 * @returns {Boolean} if validation fails returns false
 */
function sendSignUpCredentialsToBckEnd() {
	alert("Transfer");	
	var postaSessation = false;
	if (validateSignUpWoThirdPartyPageEmbedData()) {
		var jsonDataObject = createJasonObject();
		jsonDataExchange(
				jsonDataObject,
				"post",
				"../../StudentController",
				"SIWOTP",
				"json");
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
	
	if(!(isFieldFilled(isempty($('#firstName').val()),"First Name Field","firstNameLabel"))){
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#lastName').val()),"Last Name Field","lastNameLabel"))) {
		return !validationPass;
	} else if(!(isFieldFilled(isempty($('input[type=radio][name=gender]:checked').val()),"gender Field","genderLabel"))){
		return !validationPass;
	} else if (!isFieldFilled(isValidEmailFormat($('#emailAddress').val()),"Email Field","emilAddressLabel")) {
		return !validationPass;
	} else if (!(isFieldFilled(isValidPhoneNumber($('#contactNumber').val()),"Phone Number Field","contactNumberLabel"))){
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#pathway').val()),"Pathway Field","pathwayLabel"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#userName').val()),"User Name Field","usernameError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#passWord').val()),"Password Field","passWordError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#confrmpsw').val()),"Confirm Password Field","confPassWordError"))){
		return !validationPass;
	} else if(!passwordAndConfirmPassword($('#passWord').val(),$('#confrmpsw').val())){
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#policyConfirm').val()),"policy Check box","policyConfirmError"))) {
		return !validationPass;
	} else{
		return validationPass;
	}	
}

/**
 * the purpose of the method is to create the java script object
 * @author dushantha DN
 * @returns {___anonymous2641_2981}
 */
function createJasonObject(){
	var jsonData ={
			"firstName" :$('#firstName').val(),
			"lastName"  :$('#lastName').val(),
			"gender"	: $('input[type=radio][name=gender]:checked').val(),
			"emailAdress":$('#emailAddress').val(),
			"contactNumber":$('#contactNumber').val(),
			"pathway"	:$('#pathway').val(),
			"userName"	:$('#userName').val(),
			"passWord"	:$('#passWord').val(),
			"confirmPw"	:$('#confrmpsw').val(),
			"showPassWordChkBxVal":$('#showpasscheckbox').is(':checked'),
			"isPolicyConvirm"	:$('#policyConfirm').val()
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
	var isBothValueAreIdentical= false;
	if(password!=null|password !=""){
		isBothValueAreIdentical= ( password === reconfirmPassWord) ;
	}
	return isBothValueAreIdentical;
}
///**
// * jsonDataExchange method handles method uses to transfer the data to the server
// * asynchronously.
// * @author Dushantha DN
// * @param httpMethod method to be used in data transferring
// * @param transferPageUrl is the back end destination the request is meant to sent
// * @param commandCode Command Code for the Command Factory
// * @param dataCategory e.g. "jason" type of the data been sent
// */
//
//function jsonDataExchange(jsonObject,httpMethod,transferPageUrl,commandCode,dataCategory){
//
//	$.ajax({
//		type: httpMethod,
//		url : transferPageUrl,
//		data:{
//			jsonData : JSON.stringify(jsonObject),
//			CCO : commandCode
//		},
//		dataType : dataCategory,
//		success : function(response) {
//			//alert(response.result);
//			document.getElementById('messsage').innerHTML = response.result;
//		},
//		error : function(e) {
//			 alert("Error " + e);
//			console.log(e);
//		}
//	});	
//}
