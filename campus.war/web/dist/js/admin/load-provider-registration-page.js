/**
 * This Java script file used to help with admin functions author JH 20161201
 * 
 */

window.countryCollection = null;
window.countryCode = null;
window.townCollection = null;
window.usernameValidation = true;
window.courseProviderTypes = null;
window.accountType = null;
window.registerId = null;
window.courseProviderAcoountStatus = null;
window.responseErrorMessage = null;

$(document).ready(function() {
	arrangeUI();
	// getCourseProviderTypes();
});

function arrangeUI() {
	document.getElementById("logoPanel").style.display = "none";
	publishPrograms();
	if (window.responseErrorMessage == null) {
		document.getElementById("userMessage").style.display = "none";
	}

}


/**
 * this method used to show or hide the expiration date depending on whether the
 * course provider will publish programs or not.
 */
function publishPrograms(){
	var publishProgram = $('input[name=publishProgram]:checked').val();
	
	if(publishProgram == 0){
		document.getElementById("expire-date").style.display = "block";

	}else if(publishProgram == 1){
		document.getElementById("expire-date").style.display = "none";
		$('#expirationDate').val("0000-00-00");
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
		$('#providerEmail').val("common");
		$('#providerUsername').val("common");
		$('#providerPassword').val("common");
		$('#cProviderPassword').val("common");
		$('#accountStatus').val("common");
		$('#accountDescription').val("common");

	}
}

window.onload = function() {
	getProviderPageLoadData();
};

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
	});
}

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

function displayAccountStatusList() {
	var accountList = courseProviderAcoountStatus;
	var singleTypeElement = '';

	singleTypeElement += '';
	if (accountList !== undefined & accountList !== null) {
		$
				.each(
						accountList,
						function(index, value) {
							singleTypeElement += '<input type="radio" name="accountStatus" id="accountStatus" value="'
									+ value[0] + '"/>';
							singleTypeElement += value[1];
							singleTypeElement += '&nbsp;&nbsp;';

						});
	}
	singleTypeElement += '';
	var providerAccountStatus = $("#accountStatusList");
	providerAccountStatus.html(singleTypeElement);

}

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
			});
}

/**
 * display all countries
 */
function displayProviderCountries() {
	var countryCollection = window.countryCollection;
	var singleCountryElement = '';

	singleCountryElement += '<select id="selectedCountry" name="selectedCountry" onchange="getDataOnCountrySelection()"><option value="">--Default--</option>';
	if (countryCollection !== undefined & countryCollection !== null) {
		$.each(countryCollection, function(index, value) {
			singleCountryElement += '<option value="' + value[0] + '">';
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

	if (!isempty(document.getElementById('selectedCountry').value)) {
		document.getElementById('errorSelectedCountry').innerHTML = "**Select a country to proceed.";
		document.getElementById('selectedCountry').focus();
		document.getElementById('landNumber1').innerHTML = "";
		document.getElementById('landNumber2').innerHTML = "";
		document.getElementById('lastMobileNumber').innerHTML = "";

	} else {
		landPhoneNubmerHelper();
		getProviderTownListData();
	}

}

// var selectedCountry = $("#selectedCountry");
// alert(selectedCountry);
function getProviderTownListData() {
	var selectedCountry = document.getElementById('selectedCountry').value;

	if (selectedCountry == '' || selectedCountry == null) {
		document.getElementById('errorSelectedTown').innerHTML = "**Select your country first.";
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
		});
	}
}

function displayProviderTownList() {

	var countryCollection = window.townCollection;
	var singleTownElement = '';

	singleTownElement += '<select id="selectedTown" name="selectedTown"><option value="">--Default--</option>';
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
		document.getElementById('landNumber2').innerHTML = "**Please select your country.";
		document.getElementById('lastMobileNumber').innerHTML = "**Please select your country.";
		
	}else{
		 if (!isempty(areaCode)) {
			document.getElementById('landNumber1').innerHTML = "**Area code is empty.";
			document.getElementById('landNumber2').innerHTML = "**Area code is empty.";

		}if(!isempty(networkCode)){
			document.getElementById('lastMobileNumber').innerHTML = "**Network code is empty.";
		
		} if (isempty(areaCode)) {
			if (isPatternMatch(integerPattern, areaCode)) {
				var lastLandNumber1 = "+" + country + " " + areaCode + " "
						+ land1;
				var lastLandNumber2 = "+" + country + " " + areaCode + " "
						+ land2;

				document.getElementById('landNumber1').innerHTML = lastLandNumber1;
				document.getElementById('landNumber2').innerHTML = lastLandNumber2;

				if (isempty(land1) && !isPatternMatch(integerPattern, land1)) {
					document.getElementById('errorLand1').innerHTML = "Phone number 1 is invalid.";
				}
				if (isempty(land2) && !isPatternMatch(integerPattern, land2)) {
					document.getElementById('errorLand2').innerHTML = "Phone number 2 is invalid.";

				}
			} else {
				document.getElementById('errorLand1').innerHTML = "Area code invalid.";
				document.getElementById('errorLand2').innerHTML = "Area code invalid.";
			}


		} if(isempty(networkCode)){
			 if(!isPatternMatch(integerPattern, networkCode)){

				document.getElementById('errorNetworkCode').innerHTML = "**Only numbers allowed.";
				document.getElementById('lastMobileNumber').innerHTML = "**Invlide network code.";

							
			 } else if (isPatternMatch(integerPattern, networkCode)) {

				var lastMobilNumber = "+" + country + " " + networkCode + " "
						+ mobile;

				document.getElementById('lastMobileNumber').innerHTML = lastMobilNumber;
				if (isempty(mobile) && !isPatternMatch(integerPattern, mobile)) {
					document.getElementById('lastMobileNumber').innerHTML = "**Invalid mobile number.";
				}
			}

		}
		
	}

}

function getProviderType() {

	var errorMessageList = document.getElementsByClassName('error-message');
	var flag = true;

	// clear all previous error messages
	for (var i = 0; i < errorMessageList.length; i++) {
		errorMessageList[i].innerHTML = "";
	}

//	if (providerPrefixValidation() === false) {
//		flag = false;
//	}
//	if (providerUsernameValidation() === false) {
//		flag = false;
//	}

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
									$( "#basicForm" ).submit();
									
									window.responseErrorMessage = response.userMessage;
//									 $
//									 .ajax({
//									 url :
//									 '/dist/partials/admin/courseProviderManagement.jsp',
//									 method : 'POST',
//									 data : {
//														'generatedKey' : response.registerId,
//														'userMessage' : response.userMessage
//													}
//												});
									
									   var form = document.createElement('form');
							    	   form.method = 'post';
							    	   form.action = '/dist/partials/admin/courseProviderManagement.jsp';
							    	   var input = document.createElement('input');
							    	   input.type = 'hidden';
							           input.name = 'courseProviderCode';
							           input.value = response.registerId;
							    	   var inputUserMessage = document.createElement('input');
							    	   input.type = 'hidden';
							           input.name = 'userMessage';
							           input.value = response.userMessage;
							           form.appendChild(input);
							           form.appendChild(inputUserMessage);
							           form.submit();
									
									}
							}
						},
					});
		}
	}
}