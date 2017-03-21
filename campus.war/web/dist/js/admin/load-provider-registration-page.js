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
//20170320 JH c141-ui-integration-add-course-provider added isempty() method to validate input for whitespaces
//20170321 JH c141-ui-integration-add-course-provider display error messages on country selection wip

window.countryCollection = null;
window.courseProviderTypes = null;
window.accountType = null;
window.courseProviderAcoountStatus = null;
window.responseErrorMessage = null;

/**
 * load data, arrange elements when the document is ready
 */
$(document).ready(function() {
	/*commented until the logo panel confirms*/
	//arrangeUI();
});


/* this will be removed in the final stages */
/*
 * hides the logo panel and clear the user message
 */
function arrangeUI() {
	document.getElementById("logoPanel").style.display = "none";
	
	publishPrograms();
	
	var message =	$('#userMessage').val();
	
	if (!isempty(message)) {
		document.getElementById("userMessage").style.display = "none";
	}else{
		document.getElementById("userMessage").style.display = "block";
	}

}


/**
 * this method used to show or hide the expiration date depending on whether the
 * course provider will publish programs or not.
 */
function publishPrograms(){
	var publishProgram = $('input[name=publishProgram]:checked').val();
	
	if(publishProgram == 0){
		var d = new Date();
		$('#expirationDate').val(d);
		document.getElementById("expire-date").style.display = "block";

	}else if(publishProgram == 1){
		document.getElementById("expire-date").style.display = "none";
		
		/**
		 * this assigned value is not used in the application to save the data. but to avoid the JavaScript 
		 * warning message of invalid date format.
		 */
		$('#expirationDate').val("2040-12-31");
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
	window.accountType = typeValue;

	/**
	 * here methods are created to hidden input fields that are not needed for
	 * the the of the course provider
	 */
	if (window.accountType == 1) {

		$('#providerPrivateName').val("");
		$('#providerContactNumber').val("");
		$('#providerEmail').val("");
		$('#providerUsername').val("");
		$('#providerPassword').val("");
		$('#cProviderPassword').val("");
		$('#accountStatus').val("");
		$('#accountDescription').val("");

		document.getElementById("accountInfo").style.display = "block";

	}
	if (window.accountType == 2) {

		document.getElementById("accountInfo").style.display = "none";

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

	singleTypeElement += '<select id="selectedProviderType" name="selectedProviderType" ><option value="">--Default--</option>';
	if (providerTypeCollection !== undefined & providerTypeCollection !== null) {
		$.each(providerTypeCollection, function(index, value) {
			singleTypeElement += '<option value="' + value[0] + '">';
			singleTypeElement += value[1];
			singleTypeElement += '</option>';

		});
	}
	singleTypeElement += '';
	var providerTypeNames = $("#providerTypeList");
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
					}
				},
				error : function(x, status, error) {
					var err = displayErrorMessage(x, status, error);
					document.getElementById("userMessage").style.display = "block";
					$("#userMessage").html(err);
				},
			});
}

/**
 * display all countries
 */
function displayProviderCountries() {
	var countryCollection = window.countryCollection;
	var singleCountryElement = '';

	singleCountryElement += '<datalist id="selectedCountry" name="selectedCountry" class="select-country form-control"><option value="">--Default--</option>';
	if (countryCollection !== undefined & countryCollection !== null) {
		$.each(countryCollection, function(index, value) {
			singleCountryElement += '<option data-value="' + value[0] + '">';
			singleCountryElement += value[1];
			singleCountryElement += '</option>';

		});
	}
	singleCountryElement += '';
	var countryNames = $("#country-List");
	countryNames.html(singleCountryElement);

}

/**
 * display country code and list town data
 */
function getDataOnCountrySelection() {
//var selectedCountry = $("#selectedCountry").val();

	var selectedCountry = document.getElementById("selectedCountry").value;
	if (!isempty(selectedCountry)) {
		
		$("#errorSelectedCountry").attr({ "title" : "Select a country to proceed.","data-original-title" : "Select a country to proceed."});
		$("#country-List").addClass("has-error");
		document.getElementById('landNumber1').innerHTML = "";
		document.getElementById('landNumber2').innerHTML = "";
		document.getElementById('lastMobileNumber').innerHTML = "";

	} else {
		landPhoneNubmerHelper();
		getProviderTownListData();
	}

}

/**
 * get course provider town list for the selected country
 */		
function getProviderTownListData() {
	clearErrorMessage();
	var selectedCountry = $("#selectedCountry").val();

	if (selectedCountry == '' || selectedCountry == null) {
		$("#errorSelectedTown").attr({ "title" : "Select a country to proceed.","data-original-title" : "Select a country to proceed."});
		$("#town-List").addClass("has-error");
	} else {
		document.getElementById('errorSelectedTown').innerHTML = "";
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
	var singleTownElement = '';

	singleTownElement += '<select id="selectedTown" name="selectedTown" class="select-city form-control"><option value=""></option>';
	if (townCollection !== undefined & townCollection !== null) {
		$.each(townCollection, function(index, value) {
			singleTownElement += '<option value="' + value[0] + '">';
			singleTownElement += value[1];
			singleTownElement += '</option>';

		});
	}
	singleTownElement += '</select>';
	var townNames = $("#town-List");
	townNames.html(singleTownElement);
}

/**
 * landPhoneNubmerHelper() method will show relevant error messages. 
 * It will display the last telephone number for  compulsory phone number fields. 
 */
function landPhoneNubmerHelper() {
	var country = $("#country-List :selected").val();
	var areaCode = $("#areaCode").val();
	var land1 = $("#land1").val();
	var land2 = $("#land2").val();
	var networkCode = $("#networkCode").val();
	var mobile = $("#mobile").val();
	
	var integerPattern = /^[0-9]+$/;

	var errorMessageList = document.getElementsByClassName('number-helper');

	// clear all previous error messages related to phone number
	for (var i = 0; i < errorMessageList.length; i++) {
		errorMessageList[i].innerHTML = "";
	}
	
	if (!isempty(country)) {
		
		document.getElementById('errorLand1').innerHTML = "**Please select your country.";
		document.getElementById('errorLastMobileNumber').innerHTML = "**Please select your country.";
		
	}else{
//		 if (!isempty(areaCode)) {
//			document.getElementById('errorLand1').innerHTML = "**Area code is empty.";
//
//		}if(!isempty(networkCode)){
//			document.getElementById('errorNetworkCode').innerHTML = "**Network code is empty.";
//			document.getElementById('errorLastMobileNumber').innerHTML = "**Network code is empty.";
//		
//		} if (isempty(areaCode)) {
//			if (isPatternMatch(integerPattern, areaCode)) {
//				var lastLandNumber1 = "+" + country + " " + areaCode + " "
//						+ land1;
//				var lastLandNumber2 = "+" + country + " " + areaCode + " "
//						+ land2;
//
//				document.getElementById('landNumber1').innerHTML = lastLandNumber1;
//				document.getElementById('landNumber2').innerHTML = lastLandNumber2;
//
//				if (isempty(land1) && !isPatternMatch(integerPattern, land1)) {
//					document.getElementById('landNumber1').innerHTML = "";
//					document.getElementById('errorLand1').innerHTML = "Phone number 1 is invalid.";
//				}
//				if (isempty(land2) && !isPatternMatch(integerPattern, land2)) {
//					document.getElementById('landNumber2').innerHTML = "";
//					document.getElementById('errorLand2').innerHTML = "Phone number 2 is invalid.";
//
//				}
//			} else {
//				document.getElementById('errorLand1').innerHTML = "Area code invalid.";
//			}
//
//
//		} if(isempty(networkCode)){
//			 if(!isPatternMatch(integerPattern, networkCode)){
//
//				document.getElementById('errorNetworkCode').innerHTML = "**Only numbers allowed.";
//				document.getElementById('errorLastMobileNumber').innerHTML = "**Invlide network code.";
//
//							
//			 } else if (isPatternMatch(integerPattern, networkCode)) {
//
//				var lastMobilNumber = "+" + country + " " + networkCode + " "
//						+ mobile;
//
//				document.getElementById('lastMobileNumber').innerHTML = lastMobilNumber;
//				if (isempty(mobile) && !isPatternMatch(integerPattern, mobile)) {
//					document.getElementById('lastMobileNumber').innerHTML = "**Invalid mobile number.";
//				}
//			}
//
//		}
		
	}

}

/**
 * save course provider details into database on course provider username and prefix validation
 */
function saveCourseProvider() {

	var errorMessageList = document.getElementsByClassName('error-message');
	var flag = true;

	// clear all previous error messages
	for (var i = 0; i < errorMessageList.length; i++) {
		errorMessageList[i].innerHTML = "";
	}

	if (providerPrefixValidation() === false) {
		flag = false;
	}
	if (providerUsernameValidation() === false) {
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
		}
	}
}

/**
 * @author JH
 * @param fieldValue
 *            it is the value of a document element
 * @returns true if has content else false. (used to validate string values)
 */
function isempty(fieldValue) {
	return (($.trim(fieldValue) == "") || (fieldValue == null)) ? false : true;
}
	
function clearErrorMessage(){
	$("#town-List").removeClass("has-error");
}
