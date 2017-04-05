/**
 * This Java script file used to help with admin functions author JH 20161201
 * 
 */

//20170209 JH c39-add-course-provider fixed to clear unique prefix info message when a unique prefix error occurs,
//				default expiration start date set to current date. (as it was getting the default dummy value assigned to avoid the JavaScript warning message,
//				landPhoneNubmerHelper() method changed to clear info message on error
//20170215 JH c141-add-course-provider-issue-improvements send generated course provider code on successful registration
//20170221 JH c141-add-course-provider-issue-improvements removed commented front end validation part, added method comments,removed unwanted codes
//20170223 JH c141-add-course-provider-issue-improvements landPhoneNubmerHelper(): change hints to error messages when they show errors 
//20170224 JH c141-add-course-provider-issue-improvements landPhoneNubmerHelper(): show error messages on error
//20170301 JH c141-add-course-provider-issue-improvements saveCourseProvider(): AJAX error handling, show error message on error
//20170316 JH c141-ui-integration-add-course-provider getDataOnCountrySelection(), displayProviderCountries(), getDataOnCountrySelection() changed to add
//			  	css styles
//20170320 JH c141-ui-integration-for-add-course-provider added isempty() method to validate input for whitespaces
//20170321 JH c141-ui-integration-for-add-course-provider display error messages on country selection wip
//20170324 JH c141-ui-integration-for-add-course-provider added clearErrorMessage(), setErrorMessage(), setSuccessMessage() methods
//20170327 JH c141-ui-integration-for-add-course-provider saveCourseProvider() added error message, saveCourseProvider():changed to clear has-error style class
//20170328 JH c141-ui-integration-for-add-course-provider saveCourseProvider() methods used to clear error messages modified, added clearToolTip() method to 
//				clear error and success message		
//20170329 JH c141-ui-integration-for-add-course-provider added displyPhoneNumber() method to display given phone number or the information message to the user related to the phone number,
//				landPhoneNubmerHelper() method changed to match new UI elements
//20170330 JH c141-ui-integration-for-add-course-provider landPhoneNubmerHelper() modified to show country code and display area code wip, isempty() method refactored as isValidNumber()
//20170331 JH c141-ui-integration-for-add-course-provider datalist implementation for country list wip
//20170702 JH c141-ui-integration-for-add-course-provider displayProviderTypes() modified: removed select tag and load data to selectedProviderType element, displayProviderCountries(): clear input values for search 
//				,errorSelectedTown(): used clearToolTip() method to clear error message
//20170403 JH c141-ui-integration-for-add-course-provider datalist implementation to list towns wip, landPhoneNubmerHelper() fixed errors in mobile phone number fields and remvoed
//				commented unwanted codes, load country code from the country list, added methods to select country code and call functions to display town list, removed clearErrorMessage(),
//				implemented datalist function to display town list
//20170404 JH c141-ui-integration-for-add-course-provider clear success messages before validating username and the prefix

window.countryCollection = null;
window.courseProviderTypes = null;
window.accountType = null;
window.courseProviderAcoountStatus = null;
window.responseErrorMessage = null;
window.selectedCountry = null;

/**
 * load data, arrange elements when the document is ready
 */
$(document).ready(function() {
	/*commented until the logo panel confirms*/
	arrangeUI();
});


/* this will be removed in the final stages */
/*
 * hides the logo panel and clear the user message
 */
function arrangeUI() {
	
	publishPrograms();
}


/**
 * this method used to show or hide the account information section depending on whether the
 * course provider is provided with the login function.
 */
function publishPrograms(){
	var publishProgram = $('input[name=courseProvider]:checked').val();
	
	
	if(publishProgram === "FEATURED_COURSE_PROVIDER"){
	    $("#accountInfoSection").accordion({		 
			collapsible: false
		});
		
		document.getElementById("accountInfoSection").style.visibility = "visible";
		document.getElementById("accountInfoSection").style.display = "block";

	}else if(publishProgram === "ONE_OFF_COURSE_PROVIDER"){
		
		document.getElementById("accountInfoSection").style.visibility = "hidden";
		document.getElementById("accountInfoSection").style.display = "none";
		
		/**
		 * this assigned value is not used in the application to save the data. but to avoid the JavaScript 
		 * warning message of invalid date format.
		 */
	//	$('#expirationDate').val("2040-12-31");
	}
}


/**
 * @param clearField
 * @param elementId
 *            the id of the HTML element
 */

function clearField(elementId) {
	$(document).find('#' + elementId).text('');
}

function changeRequiredData(typeValue) {
	publishPrograms();
	window.accountType = typeValue;

	/**
	 * here methods are created to hidden input fields that are not needed for
	 * the the of the course provider
	 */
	if (window.accountType === "FEATURED_COURSE_PROVIDER") {

		$('#providerPrivateName').val("");
		$('#providerContactNumber').val("");
		$('#providerEmail').val("");
		$('#providerUsername').val("");
		$('#providerPassword').val("");
		$('#cProviderPassword').val("");
		$('#accountStatus').val("");
		$('#accountDescription').val("");

	}
	if (window.accountType === "ONE_OFF_COURSE_PROVIDER") {

		$('#providerPrivateName').val("common name");
		$('#providerContactNumber').val("common contact number");
		$('#providerEmail').val("common");
		$('#providerUsername').val("common");
		$('#providerPassword').val("common");
		$('#cProviderPassword').val("common");
		$('#accountStatus').val("common");
		$('#accountDescription').val("common");

	}
}

/**
 * load page data, on page load
 */
window.onload = function() {
	getProviderPageLoadData();
};

/**
 * load course provider types
 */
function getCourseProviderTypes() {
	$.ajax({
		url : '/AdminController',
		method : 'POST',
		data : {
			'CCO' : 'LIST_COUESE_PROVIDER_TYPES'
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				window.courseProviderTypes = response.providerTypes;
			}
		},
		error : function(x, status, error) {
			var err = displayErrorMessage(x, status, error);
			document.getElementById("userMessage").style.display = "block";
			$("#userMessage").html(err);
		}
	});
}

/**
 * display course provider types
 */
function displayProviderTypes() {
	var providerTypeCollection = window.courseProviderTypes;
	var singleTypeElement = '';

	singleTypeElement += '<option value="">--Default--</option>';
	if (providerTypeCollection !== undefined & providerTypeCollection !== null) {
		$.each(providerTypeCollection, function(index, value) {
			singleTypeElement += '<option value="' + value[0] + '">';
			singleTypeElement += value[1];
			singleTypeElement += '</option>';

		});
	}
	singleTypeElement += '';
	var providerTypeNames = $("#selectedProviderType");
	providerTypeNames.html(singleTypeElement);

}

/**
 * display account status lists
 */
function displayAccountStatusList() {
	var accountList = courseProviderAcoountStatus;
	var singleTypeElement = '';

	singleTypeElement += '';
	if (accountList !== undefined & accountList !== null) {
		$
				.each(
						accountList,
						function(index, value) {
							singleTypeElement += '<label class="radio-inline radio-lbl">';
							singleTypeElement += '<input type="radio" name="accountStatus" id="accountStatus" value="' + value[0] + '">';
							singleTypeElement += value[1];
							singleTypeElement += '</label>&nbsp;&nbsp;';

						});
	}
	singleTypeElement += '';
	var providerAccountStatus = $("#accountStatusList");
	providerAccountStatus.html(singleTypeElement);

}

/**
 * get course provider registration page details
 * This includes displaying course provider types, town data list
 * and course provider account status list 
 */
function getProviderPageLoadData() {

	$
			.ajax({
				url : '/AdminController',
				method : 'POST',
				data : {
					'CCO' : 'LIST_PROVIDER_REGISTRATION_PAGE'
				},
				dataType : "json",
				async : false,
				success : function(response) {

					if (response !== undefined && response !== null) {
						window.countryCollection = response.countryArrayList;
						displayProviderCountries();
						window.courseProviderTypes = response.providerTypes;
						window.courseProviderAcoountStatus = response.accountStatusList;
						displayProviderTypes();
						getProviderTownListData();
						displayAccountStatusList();
						landPhoneNubmerHelper();
					}
				},
				error : function(x, status, error) {
					var err = displayErrorMessage(x, status, error);
				//	document.getElementById("userMessage").style.display = "block";
					$("#userMessage").html(err);
				},
			});
}

/**
 * display all countries
 */
function displayProviderCountries() {
	var countryCollection = window.countryCollection;
	
	// check if the browser supports datalist function before proceeding 
	if(document.createElement("datalist").options) {
		$("#countries").on("click", function(e) {
			$("#countries").val("");
			
			if (countryCollection !== undefined & countryCollection !== null) {
				var dataList = $("#countryresults");
				dataList.empty();
				
				if(countryCollection.length) {
					for(var i=0; i<countryCollection.length; i++) {
						var opt = $("<option></option>").attr({"data-country": countryCollection[i][0], "value" : countryCollection[i][1] });
						dataList.append(opt);
					}
				}
			}
			
		});
	}

}

/**
 * select country code 
 */
$('#countries').on('input', function() {
    var value = $(this).val();
    window.selectedCountry = $('#countryresults [value="' + value + '"]').data('country');
    getDataOnCountrySelection();
    $('#selectedCountry').val(window.selectedCountry);
});

/**
 * display country code and list town data
 */
function getDataOnCountrySelection() {

	clearToolTip("#countries");
	if (!isempty(window.selectedCountry)) {
		
		$("#errorSelectedCountry").attr({ "title" : "Select a country to proceed.","data-original-title" : "Select a country to proceed."});
		$("#country-List").addClass("has-error");

	} else {
		landPhoneNubmerHelper();
		getProviderTownListData();
	}

}

/**
 * get course provider town list for the selected country
 */		
function getProviderTownListData() {
	clearErrorMessage("#country-List");
	var selectedCountry = window.selectedCountry;


	if (selectedCountry == '' || selectedCountry == null) {
		$("#errorSelectedTown").attr({ "title" : "Select a country to proceed.","data-original-title" : "Select a country to proceed."});
		$("#town-List").addClass("has-error");
	} else {

		clearErrorMessage("#town-List");
		$.ajax({
			url : '/AdminController',
			method : 'POST',
			data : {
				'CCO' : 'DISPLAY_TOWN_DATA',
				'country' : selectedCountry
			},
			dataType : "json",
			async : false,
			success : function(response) {

				if (response !== undefined && response !== null) {
					window.townCollection = response.townArrayList;
					displayProviderTownList();
				}
			},
			error : function(x, status, error) {
				var err = displayErrorMessage(x, status, error);
				document.getElementById("userMessage").style.display = "block";
				$("#userMessage").html(err);
			}
		});
	}
}

/**
 * display course provider town list
 */
function displayProviderTownList() {

	var countryCollection = window.townCollection;
	
	// check if the browser supports datalist function before proceeding 
	if(document.createElement("datalist").options) {
		$("#towns").on("click", function(e) {
			$("#towns").val("");
			
			if (countryCollection !== undefined & countryCollection !== null) {
				var dataList = $("#townresults");
				dataList.empty();
				
				if(countryCollection.length) {
					for(var i=0; i<countryCollection.length; i++) {
						var opt = $("<option></option>").attr({"data-town": countryCollection[i][0], "value" : countryCollection[i][1] });
						dataList.append(opt);
					}
				}
			}
			
		});
	}
	
}

/**
 * select town code 
 */
$('#towns').on('input', function() {
    var value = $(this).val();
    var selectedTown = $('#townresults [value="' + value + '"]').data('town');
    $('#selectedTown').val(selectedTown);
});


/**
 * landPhoneNubmerHelper() method will show relevant error messages. 
 * It will display the last telephone number for  compulsory phone number fields. 
 */
function landPhoneNubmerHelper() {
	
	var country = window.selectedCountry ;
	var areaCode = $("#areaCode").val();
	var land1 = $("#land1").val();
	var land2 = $("#land2").val();
	var networkCode = $("#networkCode").val();
	var fax = $("#fax").val();
	var mobile = $("#mobile").val();
	
	var lastLandNumber1 = null;
	var lastLandNumber2 = null;
	var lastFaxNumber = null;
	var lastMobileNumber = null;
	
	var integerPattern = /^[0-9]+$/;

	var errorMessageList = document.getElementsByClassName('phone-no-hint');

	// clear all previous error messages related to phone number
	for (var i = 0; i < errorMessageList.length; i++) {
		var varId = $(errorMessageList[i]).attr('id');
		varId = '#' + varId;
		$(varId).text("");
		
	}

	if (!isempty(country)) {
		
		displyPhoneNumber('#landNumber1',"Please select your country." );
		displyPhoneNumber('#landNumber2',"Please select your country." );
		displyPhoneNumber('#lastMobileNumber',"Please select your country." );
		displyPhoneNumber('#lastFaxNumber',"Please select your country." );
		
	}else{
		var numbers = jQuery("[id=countryCode]");//set country code
		for (var i = 0; i< numbers.length; i++){
			$(numbers[i]).val("+" + country);
		}
	
		lastLandNumber1 = "+" + country;
		lastLandNumber2 = "+" + country;
		lastMobileNumber = "+" + country;
		lastFaxNumber = "+" + country;

		 if (!isValidNumber(areaCode, integerPattern)) {
			 
			displyPhoneNumber('#landNumber1', "Area code is empty or invalid.");
			displyPhoneNumber('#landNumber2', "Area code is empty or invalid.");
			displyPhoneNumber('#lastFaxNumber', "Area code is empty or invalid.");
			
			var areaCodeSet = jQuery("[id=areaCode2]");//set area codes
			for (var i = 0; i< areaCodeSet.length; i++){
				$(areaCodeSet[i]).val("");
			}
			

		}if(!isValidNumber(networkCode, integerPattern)){
			
			displyPhoneNumber('#lastMobileNumber', "Network code is empty or invalid.");
			//setErrorMessage('#mobileDiv', '#errorMobile', "Network code is empty or invalid.");
			
		} if (isValidNumber(areaCode, integerPattern)) {
			
			var areaCodeSet = jQuery("[id=areaCode2]");//set area codes
			for (var i = 0; i< areaCodeSet.length; i++){
				$(areaCodeSet[i]).val(areaCode);
			}
			
			lastLandNumber1 += " " + areaCode;
			lastLandNumber2 += " " + areaCode;
			lastFaxNumber += " " + areaCode;
		
			// check phone number fields and set error messages or hints
				if (isValidNumber(land1, integerPattern)) {
					
					clearToolTip('#land1Div');
					lastLandNumber1 += " " + land1;
					
				}if(isempty(land1) && (!isValidNumber(land1, integerPattern))){
					
					lastLandNumber1 = "Phone number 1 is invalid.";
					setErrorMessage('#land1Div', '#errorLand1', "Phone number 1 is invalid.");
					
				}if (isValidNumber(land2, integerPattern)) {
					
					clearToolTip('#land2Div');
					lastLandNumber2 += " " + land2;
					
				}if(isempty(land2) && (!isValidNumber(land2, integerPattern))){
					
					lastLandNumber2 = "Phone number 2 is invalid.";
					setErrorMessage('#land2Div', '#errorLand2', "Phone number 2 is invalid.");
					
				}if (isValidNumber(fax, integerPattern)) {
					
					clearToolTip('#faxDiv');
					lastFaxNumber += " " + fax;
					
				}if(isempty(fax) && (!isValidNumber(fax, integerPattern))){
					
					lastFaxNumber = "Fax number is invalid.";
					setErrorMessage('#faxDiv', '#errorFax', "Fax number is invalid.");
					
				}
				
				displyPhoneNumber('#landNumber1', lastLandNumber1);
				displyPhoneNumber('#landNumber2', lastLandNumber2);
				displyPhoneNumber('#lastFaxNumber',lastFaxNumber );
			

		} if(isValidNumber(networkCode, integerPattern)){
			
			lastMobileNumber += " " + networkCode;
			
			if (isValidNumber(mobile, integerPattern)) {
				
				clearToolTip('#mobileDiv');
				lastMobileNumber += " " + mobile;
				
			}if(isempty(mobile) && (!isValidNumber(mobile, integerPattern))){
				
				lastMobileNumber = "Mobile number is invalid.";
				setErrorMessage('#mobileDiv', '#errorMobile', "Mobile number is invalid.");
				
			}
			
			displyPhoneNumber('#lastMobileNumber',lastMobileNumber );
	}
		
	}

}

/**
 * save course provider details into database on course provider username and prefix validation
 */
function saveCourseProvider() {

	// get error message list
	var errorMessageList = document.getElementsByClassName('has-error');
	var message = null; 
	var flag = true;

	// clear all previous error messages
	for (var i = 0; i < errorMessageList.length; i++) {
		var varId = $(errorMessageList[i]).attr('id');
		varId = '#' + varId;
		clearToolTip(varId);
		
		
	}

	clearToolTip('#uniquePrefixDiv');
	clearToolTip('#usernameDiv');
	
	if (providerUsernameValidation() === false) {
		message = "Invalid Username";
		flag = false;
	}
	if (providerPrefixValidation() === false) {
		if(flag === false){
			message += " and Prefix";
		}else{
			message = "Invalid Prefix";
		}
		flag = false;
	}

	if (flag === true) {
		if (vaidateCourseProviderDeatils() === true) {

			var form = $('#basicForm');
			var formData = $(form).serialize();
			$
					.ajax({
						url : '/AdminController',
						method : 'POST',
						data : formData,
						dataType : "json",
						async : false,
						success : function(response) {

							if (response !== undefined && response !== null) {

								window.responseErrorMessage = response.userMessage;
								if (response['registerId'] === 0) {
									
									if (response['userMessage'] !== null) {
										document.getElementById("userMessage").style.display = "block";
										$("#userMessage").html(response.userMessage);
										
										for ( var key in response) {
											if (response.hasOwnProperty(key)) {
												var val = response[key];
												var attributeName = "#" + key;
												$(attributeName).html(val);
											}
										}
									}
								} else if (response['registerId'] !== 0) {
									
									if (response['userMessage'] !== null) {
										$("#userMessage").html(response.userMessage);
									}
								
									
									window.responseErrorMessage = response.userMessage;

									$('#userMessage').val(response.userMessage);
									$('#generatedId').val(response.registerId);
							       	$( "#basicForm" ).submit();
									
									}
							}
						},
						error : function(x, status, error) {
							var err = displayErrorMessage(x, status, error);
							document.getElementById("userMessage").style.display = "block";
							$("#userMessage").html(err);
						}
					});
		}else{
			$("#userMessage").html("One or more fields are invalid.");
		}
	}else{
		document.getElementById("userMessage").style.display = "block";
		$("#userMessage").html(message);
	}
}

/**
 * Checks the input value is empty or is not a number
 * @author JH
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false. (used to validate string values)
 */
function isValidNumber(fieldValue, integerPattern) {
	flag = true;
	if(($.trim(fieldValue) === "") || (fieldValue === null) || (!isPatternMatch(integerPattern, fieldValue))){
		flag = false;
	}
	return flag;
}

/**
 * clear error message class
 * @param toolTipElement
 * @author JH
 */
function clearErrorMessage(toolTipElement){
	$(toolTipElement).removeClass("has-error");
}

/**
 * clear both success or error message
 * @param element
 * @author JH
 */
function clearToolTip(element){
	$(element).removeClass("has-error");
	$(element).removeClass("has-success");
}

/**
 * used to display the last phone number as a hint to the user.
 * Then the user can check the final phone number and do any changes to 
 * get the correct final phone number
 * @param hintSpan
 * @param phoneNumber
 * @author JH
 */
function displyPhoneNumber(hintSpan, phoneNumber){
	$(hintSpan).text(phoneNumber);
}

/**
 * Used to set error message
 * @param errorElement
 * @param errorToolTip
 * @param message
 * @author JH
 */
function setErrorMessage(errorElement, errorToolTip, message){
	
	$(errorToolTip).attr({ "title" : message,"data-original-title" : message});
	$(errorElement).addClass("has-error");
}

/**
 * Used to set success messages
 * @param errorElement
 * @param errorToolTip
 * @param message
 */
function setSuccessMessage(successElement, successToolTip, message){
	//has-error style class is used until a style class is created for success messages
	
	$(successToolTip).attr({ "title" : message,"data-original-title" : message});
	$(successElement).addClass("has-success");
	//$(successElement).css("border", "1px solid green !important");
}
