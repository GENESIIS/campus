/**
 * 20170421 DN c88-admin-manage-advertiser-add-new-advertiser-dn created the initial js script for the issue.
 * 20170424 DN c88-admin-manage-advertiser-add-new-advertiser-dn . created the global variable adminControllerUrl 
 * 				and add to the ajax calls which requests Prerequisite data.
 * 				populateDataList() is created to so that the IE browser shows the data list correctly
 * 				The method getPreRequisitPageData() has been modified to list town, country,and Course provider,
 * 				and their code are included to hidden input fields accurately.
 * 				Removed the method manageTownListing() from the script.
 */


var theNewScript = document.createElement("script");
var theSecondScript = document.createElement("script");
theNewScript.type = "text/javascript";
theSecondScript.type = "text/javascript"; 
theNewScript.src = "../../dist/js/institute/validation/validation.js";
theSecondScript.src="../../dist/js/jsonDataExchanger.js";

$(document).ready(function() {
	displaySignUpPrerequisitDetails();
});


var mobilePhoneNumber 			= "";
var mobilePhoneCountryCode		= "";
var mobilePhoneNetWorkCode		= "";
var selectedCountryCode 		= "";
var selectedTownCode			= "";
var selectedCourseProviderCode 	= "";
var userTypeCode 				= "";
var adminControllerUrl 			= '../../../AdminController';

/**
 * displaySignUpPrerequisitDetails() function is meant to pass an ajax 
 * request to Servlet under the CCO DPRD-Display PreRequisit Data in order to extract
 * town details from the back end data repository and display on page on load
 */

function displaySignUpPrerequisitDetails(){
	$.ajax({
		url:adminControllerUrl,
		data:{
			CCO:'DPRD' //DISPLAY_PREREQUISITE_DATA
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
	
	// populating the available active course provider list.
	populateDataList(preRequistData.advertisers,'courseProviderList');	
	
	// get the selected code of the course provider
	$('#courseProvider').on('input', function(){
		var status = false;
		var val = this.value; //select what is changed from the input field.
		var dValue =$('#courseProviderList option').
									filter( function(){
												if($(this).val()===val){
													status = true;
													return $(this).val();													
												}
									 		}).attr("data-code");
		selectedCourseProviderCode = dValue;
	//Setting the course provider code to the hidden field.
	if(status){		
		$('#sCourseProviderCode').val(selectedCourseProviderCode); // set the country code in the none editable field
	  }
	});
	
	
// Set Country details 
 populateDataList(preRequistData.result,'countryList');
	
//getting the selected country code 
	$('#country').on('input', function(){
		var status = false;
		var val = this.value; //select what is changed from the input field.
		//if($('#countryList option').filter()
		var dValue =$('#countryList option').
									filter( function(){
												if($(this).val()===val){
													status = true;
													return $(this).val();													
												}
									 		}).attr("data-code");
	selectedCountryCode = dValue;
	//populating the town list 
	if(status){
		
		$('#mobileCountryCode').val("+"+selectedCountryCode); // set the country code in the none editable field
		$('#landCountryCode').val("+"+selectedCountryCode); // set the country code in none editable fields 20170421-DN CAM-88
		extractRelaventTownList(selectedCountryCode);
		}
	});
	
	
	/**
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
									 		}).attr("data-code");
		selectedTownCode = dValue;
		//setting the hidden field with the town value in the input field
	if(status){
		//selectedTownCode = dValue;
		// set the hidden field of town code.
		$('#sTownCode').val(selectedTownCode);
	}
});
	
	/** checking if the leading value is a zero if an error message will be popped out
	 * input text field will be erased
	*/
	
	$('#contactNumber').on("change",function(){
		var firstDigit = this.value;
		if(isNotvalidMobileFormat(firstDigit)){
			$('#phoneError').text("Leading Zero, Alpha Numeric Combination Or '+' Is Not Alloved!");
			$('#contactNumber').val("");
		}
	});
	
	
	/**
	 * function will triggers when input field gets changed.
	 * the purpose of the function is to match the users selection
	 * or the type in text against the list that has already been
	 * selected by the system. If the value that user entered does not
	 * confirm to the requirement, then an error message will be displayed,
	 * and the text will be cleared off.
	 */
	$('#town').on("change",function(){
		if(selectedTownCode ===""|| selectedTownCode ===undefined){
			$('#townError').html("Please select a valid Town");
			$('#town').val("");
		}
		
	});

	/**
	 * function will triggers when input field gets changed.
	 * the purpose of the function is to match the users selection
	 * or the type in text against the list that has already been
	 * selected by the system. If the value that user entered does not
	 * confirm to the requirement, then an error message will be displayed,
	 * and the text will be cleared off.
	 */
	
	$('#country').on("change",function(){
		if(selectedCountryCode ==="" || selectedCountryCode=== undefined){
			$('#countryError').html("Please select a valid Country from the populated list");
			$('#country').val("");
		} else {
			$("#town").val(""); // clear the fields
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
 * Populates the data list, The data list to be populate must accept a response 
 * which is of the form {[a,b],[a1,b1],[a2,b2]} where first element being the code which
 * is the text of the <option> and second 
 * element being the value of the <option> part of the data list.
 * @param response what comes as the ajax success call
 * @param elementId data list 'id'.
 */

function populateDataList(responseAttribute,elementId){
	
	// get the  data list element wrapper set
	var elementWrapperSet = $('#'+elementId);
	// visually remove all the option element
	elementWrapperSet.find('option').remove();
	// visually remove all the input element
	elementWrapperSet.find('input').remove();
	
	/* use the response and process the banner slot list
	* this brings the collection that contains the Collection<collection<String>>
	*which sends from the server as an Array, then iterate over, the array gets the form 
	* {[a,b],[a1,b1],[a2,b2]}
	*/
	$.each(responseAttribute,function(index,value){
		var record= value.toString();
		var recordArray =record.split(","); // convert the string to an Array
		var recordNameCompornentArray = null;
		
		var recordCode = recordArray[0].toString();
		var recordName = recordArray[1].toString();
		
		/*
		 * 	create option list on the fly and attach to the data list 
		 	create a new custom attribute called data-code and data-value then attach the code and treated name to the option list as attributes
		 	later these values can be used to extract the code
		 	e.g. <option data-code="1" value="Advertiser 0" data-value="Advertiser0"></option>
		 */
		$("<option></option>")
								.attr({
									 "data-code": recordCode,
									 "value" : recordName ,
									 "data-value":recordName.replace(/\s+/g, "").split(".")
									 })
									 .appendTo(elementWrapperSet);	
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
		
		url:adminControllerUrl,
		data:{
			country : countryCode,
			CCO:'DPTWN'
		},
		dataType:"json",
		success: function(townObject){
			//manageTownListing(townObject);
			populateDataList(townObject.result,'townList');
		},
		error: function(townObject){
			alert("Error: "+townObject);
		}
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
	
//	if(!(isFieldFilled(isStringHasValiCharsAndLength($('#firstName').val(),/^([a-zA-Z]+)([a-zA-Z]+){2,}$/g),"First Name Field","firstNameError"))) {
//		return !validationPass;
//	} else if (!(isFieldFilled(isStringHasValiCharsAndLength($('#lastName').val(),/^([a-zA-Z]+)([a-zA-Z]+){2,}$/g),"Last Name Field","lastNameError"))) {
//		return !validationPass;
//	} else if(!(isFieldFilled(isempty($('input[type=radio][name=gender]:checked').val()),"Gender Field","genderError"))){
//		return !validationPass;
//	} else if (!isFieldFilled(isValidEmailFormat($('#emailAddress').val()),"Email Field","emailError")) {
//		return !validationPass;
//	}else if (!(isFieldFilled(isStringHasValiCharsAndLength($('#country').val(),/^([a-zA-Z]+)([a-zA-Z]+){0,}$/g),"Country Field","countryError"))) {
//		return !validationPass;
//	} else if (!(isFieldFilled(isStringHasValiCharsAndLength($('#town').val(),/^([a-zA-Z]+)([a-zA-Z]+){0,}$/g),"Town Field","townError"))) {
//		return !validationPass;
//	} else if(!(isFieldFilled($('#mobileCountryCode').val(),"Phone Number Country Code Field","phoneError"))) {
//		return !validationPass;
//	} else if (!(isFieldFilled(isValidPhoneNumber($('#contactNumber').val()),"Phone Number Field","phoneError"))){
//		return !validationPass;
//	} else if(isNotvalidMobileFormat($('#contactNumber').val())){
//		$('#phoneError').text("Leading Zero, Alpha Numeric Combination Or '+' Is Not Alloved!");
//		return !validationPass;
//	}  else if (!(isFieldFilled(isempty($('#userName').val()),"User Name Field","usernameError"))) {
//		return !validationPass;
//	} else if (!(isFieldFilled(isStringHasValiCharsAndLength($('#userName').val(),/^([a-zA-Z]+)([a-zA-Z0-9_]+){5,}$/g),"Check Field Contains Invalid Characters Or Should Be > 5 Characters and ","usernameError"))) {
//		return !validationPass;
//	}else if (!(isFieldFilled(isStringHasValiCharsAndLength($('#passWord').val(),/^([a-zA-Z0-9]+)([a-zA-Z0-9_]+){7,}$/g),"Check Field Contains Invalid Characters Or Should Be > 7 Characters and ","passWordError"))) {
//		return !validationPass;
//	}else if (!(isFieldFilled(isempty($('#passWord').val()),"Password Field","passWordError"))) {
//		return !validationPass;
//	} else if (!(isFieldFilled(isempty($('#confrmpsw').val()),"Confirm Password Field","confPassWordError"))){
//		return !validationPass;
//	} else if(!(isFieldFilled(passwordAndConfirmPassword($('#passWord').val(),$('#confrmpsw').val()),"PassWords Does Not Match ,The Field(s)","confPassWordError"))){
//		return !validationPass;
//	} else if (!(isFieldFilled($('#policyConfirm').prop('checked'),"Policy Check box","policyConfirmError"))) {
//		return !validationPass;
//	} 
		return validationPass;
	
}



/**
 * the purpose of the method is to create the java script object
 * @author dushantha DN
 * @returns {___anonymous2641_2981}
 */
//  this should be changed according to the page data.Add Advertiser.jsp
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
 * starts with zero or with "+" sign if so the return valuewill be 
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

/**
 * this events gets triggered every time the courseprovider
 * input is changed. If the hidden field if field with a value
 * then trigger the call to server to get the recirds related to
 * selected id of the course provider
 */
$(document).on('change','#courseProvider',function(event){	
 if(sCourseProviderCode !=""||sCourseProviderCode!=undefined){
	 //code the ajax call to retrieve the Courseprovider data
	 // and populate the page.
 }	
});

