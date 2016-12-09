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
	$('#userTypeCode').Text(function(index,preRequistData){
		var collection = preRequistData.userType[0]; //get the first collection of the collection of collection
		return collection[0];
	});
	
	
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
		extractRelaventTownList(selectedCountryCode);
		}
	});
	
	//getting the selected town and place it on the text box
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
	alert("town code value"+dValue);
	if(status){
		selectedTownCode = dValue;
		$('#sTownCode').val(selectedTownCode);
	}
	
});
	

	
	
	
//###############################################################################	
	//Set country code prefixed to the phone numbers.
//	$("#country").on('input', function () {
//	    var val = this.value;
//	    extractRelaventTownList('94');
//        var dValue = $('#countryList option').filter(function() {
//            return this.value === val;
//        }).data('value'); //PROBLEM WITH THE FUNCTION TEST

//		var msg = dValue;
//        	if(msg){
//        		//$("span[class='input-group-addon']").text("+("+msg+")");
//        		//$('#sCountryCode').val(msg);
//        		extractRelaventTownList(msg);
//        	}
//	});

}
/**
 * accepts the country code and extract the available town information
 * that belongs to the country and bring it to the client side form 
 * server end
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
			alert(townObject.result);
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
 * @param townObject the response recieved from the server.
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
						"../../../StudentController", // path is provided related to the jsp page
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
	} 
//	else if (!(isFieldFilled(isempty($('#town:selected').text()),"Pathway Field","pathwayError"))) {
//		return !validationPass;
//	}
	else if (!(isFieldFilled(isempty(selectedCountryCode,"country Field","countryError")))) {
		return !validationPass;
	} else if (!(isFieldFilled(isempty(selectedTownCode,"Town Field","townError")))) {
		return !validationPass;
	}else if (!(isFieldFilled(isempty($('#userName').val()),"User Name Field","usernameError"))) {
		return !validationPass;
	}else if (!(isFieldFilled(isStringHasValiCharsAndLength($('#userName').val()),"Check Field Contains Invalid Characters Or Should Be > 5 Characters and ","usernameError"))) {
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
			"town"		:selectedTownCode, 
			"userName"	:$('#userName').val(),
			"userCode"	:$('#userTypeCode'),
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

