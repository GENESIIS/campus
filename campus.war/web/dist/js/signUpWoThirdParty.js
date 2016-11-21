//20161121 DN  DN C18-student-signup-without-using-third-party-application-dn
//create the signUpWoThirdParty.js file to facilitate signUpWoThirdParty.jsp


var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";
theNewScript.src = "../../dist/js/jsonDataExchanger.js";



//Global variables for signUpWoThirdParty.js file declared
var firstName = $('#firstName').val();
var lastName= jQuery('#lastName').text();
var gender = jQuery('input:radio[anme="gender"]:checked').val();
var emailAdress = jQuery('#emailAddress').text();
var contactNumber = jQuery('#contactNumber').text();
var pathway = jQuery('#pathway option:selected').text();
var userName = jQuery('#username').text();
var passWord = jQuery('#psw').text();
var confirmPw = jQuery('#confrmpsw').text();
var showPassWordChkBxVal = jQuery('#showpasscheckbox').val();
var isPolicyConvirm = jQuery('#policyConfirm').val();


function sendSignUpCredentialsToBckEnd(){
	alert("Transfer");
	//get the page specific data and set the values
	
	//form validation prior to call the jason, ajax data transform
	
	// create the jason data object
	var jsonDataObject = createJasonObject();
	//call the ajax call to transe=fer the jason data
	jsonDataExchange(jsonDataObject,httpMethod,transferPageUrl,"SIWOTP","json");
}


function validateSignUpWoThirdPartyPageEmbedData(){
	alert("Validation");
	if(!(isFieldFilled(isempty(firstName),"First Name Field","firstNameLabel"))){
		alert("inside loop");
		return false;
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