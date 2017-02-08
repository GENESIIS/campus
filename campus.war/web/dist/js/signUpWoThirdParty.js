//20161121 DN C18-student-signup-without-using-third-party-application-dn
//create the signUpWoThirdParty.js file to facilitate signUpWoThirdParty.jsp
//20161121 DN C18-student-signup-without-using-third-party-application-dn splitPhoneNumber() method created
//20161121 DN C18-student-signup-without-using-third-party-application-dn refactor the validateSignUpWoThirdPartyPageEmbedData
// 		according to CREV comments.
//20161205 DN C18-student-signup-without-using-third-party-application-dn displaySignUpPrerequisitDetails()/
// getPreRequisitPageData() created
//20161206 DN C18: getPreRequisitPageData(),extractRelaventTownList() added
//20161207 DN C18:student : signup : without using third party application modified the getPreRequisitPageData() to display country
// 		and the Town within Country
//20161214 DN CAMP:18 changed createJasonObject() method and getPreRequisitPageData() to include usercode and avoid leading zero for the phone number field
//20161215 DN CAMP:18 refactor splitPhoneNumber() introduced new client method managePhoneNumber() and 
//         utility methods :extractInternationalPhoneNumber()/resetTheMobileNumberRelatedGlobalVariables().
//20161217 DN CAMP:18 create a separate method isNotvalidMobileFormat()for reusing the logic on validating phone number.
//				clearAllFields()created to clear all fields

var theNewScript = document.createElement("script");
var theSecondScript = document.createElement("script");
theNewScript.type = "text/javascript";
theSecondScript.type = "text/javascript"; 
theNewScript.src = "../../dist/js/institute/validation/validation.js";
theSecondScript.src="../../dist/js/jsonDataExchanger.js";

$(document).ready(function() {
	displaySignUpPrerequisitDetails();
});


var mobilePhoneNumber ="";
var mobilePhoneCountryCode="";
var mobilePhoneNetWorkCode="";
var selectedCountryCode ="";
var selectedTownCode = "";
var userTypeCode = "";
/**
 * displaySignUpPrerequisitDetails() function is meant to pass an ajax 
 * request to Servlet under the CCO DPRD-Display PreRequisit Data in order to extract
 * town details from the back end data repository and display on page on load
 */

function displaySignUpPrerequisitDetails(){
	$.ajax({
		url:"../../../StudentController",
		data:{
			CCO:'DPRD'
		},
		dataType: "json",
		success: function(preRequistData){
			getPreRequisitPageData(preRequistData);
			
		},
		error: function(preRequistData){
			alert("Error: "+preRequistData);
		}
	});
	
}
/**
 * getPreRequisitPageData() suppose to manage the page view
 * and decide on what content should be displayed on page load time.
 * currently the cities will be displayed at page load time
 * This method can be used to operate on response data at page load time.
 * @param preRequistData response send from the server side
 */
function getPreRequisitPageData(preRequistData){
	// Set Country details 
	var countryList = $('#countryList');
	countryList.find('option').remove();
	$.each(preRequistData.result, function(index, value){
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(countryList);
	});
	
	
	// set the USERTYPE CODE to the hidden field
	$('#userTypeCode').text(preRequistData.userType[0][0]);
	
	//getting the selected country code 
	$('#country').on('input', function(){
		var status = false;
		var val = this.value; //select what is changed from the input field.
		var dValue =$('#countryList option').
									filter( function(){
												if($(this).val()===val){
													status = true;
													return $(this).val();
												}
									 		}).text();
	//populating the town list 
	if(status){
		selectedCountryCode = dValue;
		$('#mobileCountryCode').val("+"+selectedCountryCode); // set the country code in the none editable field
		extractRelaventTownList(selectedCountryCode);
		}
	});
	
	
	/*
	 * getting the selected town and place it on the text box
	 */
	
	$('#town').on('input', function(){
		var status = false;
		var val = this.value; //select what is changed from the input field.
		var dValue =$('#townList option').
									filter( function(){
												if($(this).val()===val){
													status = true;
													return $(this).val();
												}
									 		}).text();
	//setting the hidden field with the town value in the input field
	if(status){
		selectedTownCode = dValue;
		$('#sTownCode').val(selectedTownCode);
	}
});
	
	/* checking if the leading value is a zero if an error message will be popped out
	 * input text field will be erased
	*/
	
	$('#contactNumber').on("change",function(){
		var firstDigit = this.value;
		if(isNotvalidMobileFormat(firstDigit)){
			$('#phoneError').text("Leading Zero, Alpha Numeric Combination Or '+' Is Not Alloved!");
			$('#contactNumber').val("");
		}
	});
	
	/*
	 * this function is available when the document is ready and
	 * it clears any Main error message sent by the server 
	 * in the on click event on text-field class
	 */
	$('input.text-field').on("click",function(){
		$('#displayLabel').text(""); // clear if the ERROR message there is any.
	});
	

}
/**
 * accepts the country code and extract the available town information
 * that belongs to the country and bring it to the client side form 
 * server end
 * @author dushantha DN
 * @param countryCode
 */
function extractRelaventTownList(countryCode){
	$.ajax({
		
		url:"../../../StudentController",
		data:{
			country : countryCode,
			CCO:'DPTWN'
		},
		dataType:"json",
		success: function(townObject){
			manageTownListing(townObject);
		},
		error: function(townObject){
			alert("Error: "+townObject);
		}
	});
	
}

/**
 * manageTownListing supposes to populate the town data list
 * from the response sent from the server
 * @param townObject the response received from the server.
 */
function manageTownListing(townObject){
	$('#townList').find('option').remove();
	$.each(townObject.result,function(index,town){
		var townString = town.toString();
		var data = townString.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo($('#townList'));
	});
	
}


/**
 * method validates if the user inputs are correct and according to agreed
 * format, then collect the data and forms a java script object finally
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
						"../../../StudentController", // path is provided related to the jsp page
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
	
	if(!(isFieldFilled(isempty($('#firstName').val()),"First Name Field","firstNameError"))){
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#lastName').val()),"Last Name Field","lastNameError"))) {
		return !validationPass;
	} else if(!(isFieldFilled(isempty($('input[type=radio][name=gender]:checked').val()),"gender Field","genderError"))){
		return !validationPass;
	} else if (!isFieldFilled(isValidEmailFormat($('#emailAddress').val()),"Email Field","emailError")) {
		return !validationPass;
	} else if(!(isFieldFilled($('#mobileCountryCode').val(),"Phone Number Country Code Field","phoneError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isValidPhoneNumber($('#contactNumber').val()),"Phone Number Field","phoneError"))){
		return !validationPass;
	} else if(isNotvalidMobileFormat($('#contactNumber').val())){
		$('#phoneError').text("Leading Zero, Alpha Numeric Combination Or '+' Is Not Alloved!");
		return !validationPass;
	} else if (!(isFieldFilled(isempty(selectedCountryCode,"country Field","countryError")))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty(selectedTownCode,"Town Field","townError")))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty($('#userName').val()),"User Name Field","usernameError"))) {
		return !validationPass;
	} else if (!(isFieldFilled(isStringHasValiCharsAndLength($('#userName').val()),"Check Field Contains Invalid Characters Or Should Be > 5 Characters and ","usernameError"))) {
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
	managePhoneNumber($('#contactNumber').val(),$('#mobileCountryCode').val());
	var jsonData ={
			"firstName" :$('#firstName').val(),
			"lastName"  :$('#lastName').val(),
			"gender"	: $('input[type=radio][name=gender]:checked').val(),
			"email"		:$('#emailAddress').val(),
			"mobilePhoneNo":mobilePhoneNumber,
			"mobileCountryCode":mobilePhoneCountryCode,
			"mobileNetworkCode":mobilePhoneNetWorkCode,
			"town"		:selectedTownCode, 
			"userName"	:$('#userName').val(),
			"userCode"	:$('#userTypeCode').text(),
			"passWord"	:$('#passWord').val(),
			"confirmPw"	:$('#confrmpsw').val(),
			"isPolicyConfirm"	:$('#policyConfirm').prop('checked')
	};
	return jsonData;
}

/**
 * isNotvalidMobileFormat(): checks if the content submited
 * is startswith zero or with "+" sign if so the return valuewill be 
 * true else false.
 * @author Dushantha DN
 * @param firstDigit the content passed , method starts
 * to check from the first digit.
 * @returns boolean true if the content is a valid else false,if the 
 * content starts with a leading 0, + or contains 
 * alpha numeric combination ( not a number).
 */
function isNotvalidMobileFormat(firstDigit){
	var patern = /^[0|\+]([a-zA-Z0-9])*/g; 		//matches the leading zero and leading + sin with alpha numeric character combinations
	return(isPatternMatch(patern,firstDigit));
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
 * Method :managePhoneNumber sets the mobilephone number,mobile phone
 * networknumber and the country codedepending on the contry the user selects.
 * 
 * @author dushantha DN
 * @param phoneNumber
 * @param countryCode
 */

function managePhoneNumber(phoneNumber,countryCode){
	resetTheMobileNumberRelatedGlobalVariables();
	var trimedPhoneNumber = phoneNumber.trim().replace(/\s+/g, "");
	var length = trimedPhoneNumber.length;
	mobilePhoneCountryCode= countryCode;
	if(mobilePhoneCountryCode === '+94'){
		splitPhoneNumber(trimedPhoneNumber,length);
	} else {
		extractInternationalPhoneNumber(trimedPhoneNumber);
	}
	
}

/**
 * resetTheMobileNumberRelatedGlobalVariables():method 
 * resets the globall parameters associates with the
 * mobilephone number mobilePhoneNumber,mobilePhoneNetWorkCode,mobilePhoneCountryCode
 * @author dushantha DN
 */
function resetTheMobileNumberRelatedGlobalVariables(){
	mobilePhoneNumber="";
	mobilePhoneNetWorkCode="";
	mobilePhoneCountryCode = "";
}

/**
 * extractInternationalPhoneNumber: method uses the given number as it's. It desn't 
 * split the number in to network provider and the phone
 * number fields. further if the supplied phone number contains
 * internal spaces this method does not handle those. hence it's the clients'
 * responsibility to provide a trimed string as the parameter phoneNumber
 * @author dushantha DN
 * @param phoneNumber 
 */
function extractInternationalPhoneNumber(phoneNumber){
	mobilePhoneNumber=phoneNumber;
	
}

/**
 * splitPhoneNumber() separate the phone
 * number in network code an phone number
 * if the phone number is of the form 01234567898 or 123456789 format
 * the country code will not be populated.
 * @author dushantha DN
 * @param phoneNumber
 */
function splitPhoneNumber(phoneNumber,length){
	
	 switch(length){
	 case 9: 
		 mobilePhoneNumber = phoneNumber.substr(2);
		 mobilePhoneNetWorkCode = phoneNumber.substr(0, 2);
		 break;
	 case 10: // mobile number starts with a leading zero
		 mobilePhoneNumber = phoneNumber.substr(3);
		 mobilePhoneNetWorkCode = phoneNumber.substr(0, 3);
		 break;
	 case 12:
		 var phonenumberPattern=/^\+/g;
		 if(isPatternMatch(phonenumberPattern,phoneNumber)){
			 mobilePhoneNumber = phoneNumber.substr(5);
			 mobilePhoneNetWorkCode = phoneNumber.substr(3,2);
			// mobilePhoneCountryCode = phoneNumber.substr(0, 3);
		 }
		 break;
	 case 13:
		 var phonenumberPattern=/(^00\d{2})/g;
		 if(isPatternMatch(phonenumberPattern,phoneNumber)){
			 mobilePhoneNumber = phoneNumber.substr(6);
			 mobilePhoneNetWorkCode = phoneNumber.substr(4,2);
			 //mobilePhoneCountryCode = phoneNumber.substr(0, 4);
		 }
		 break;
	 
	 }
}

/**
 * clearAllFields willclear all the data input text fields
 * once it'scalled
 */
function clearAllFields(){
	$('input.text-field').val("");
}




