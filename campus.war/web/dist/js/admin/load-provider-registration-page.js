/**
 * This Java script file used to help with admin functions author JH 20161201
 * 
 */

window.countryCollection = null;
window.countryCode = null;
window.townCollection = null;
window.usernameValidation = true;
window.courseProviderTypes  = null;
window.accountType = null;
window.registerId = null;
window.courseProviderAcoountStatus = null;

$(document).ready(function() {
	arrangeUI();
	// getCourseProviderTypes();
});


function arrangeUI() {
	document.getElementById("logoPanel").style.display = "none";

}

function changeRequiredData(typeValue){
	window.accountType = typeValue ;
	
	/**
	 * here methods are created to hidden input fields that are not needed for
	 * the the of the course provider
	 */
	if(window.accountType == 1){
		
		$('#providerPrivateName').val("");
		$('#providerEmail').val("");
		$('#providerUsername').val("");
		$('#providerPassword').val("");
		$('#cProviderPassword').val("");
		$('#accountStatus').val("");
		$('#accountDescription').val("");
		
		document.getElementById("accountInfo").style.display = "block";
		
	}if(window.accountType == 2){
		
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

function getCourseProviderTypes(){
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
		$.each(accountList, function(index, value) {
			singleTypeElement += '<input type="radio" name="accountStatus" id="accountStatus" value="' + value[0] + '"/>';
			singleTypeElement += value[1];
			singleTypeElement += '&nbsp;&nbsp;';

		});
	}
	singleTypeElement += '';
	var providerAccountStatus = $("#accountStatusList");
	providerAccountStatus.html(singleTypeElement);

}

function getProviderPageLoadData() {

	$.ajax({
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
function getDataOnCountrySelection(){
	
	if(!isempty(document.getElementById('selectedCountry').value)){
		document.getElementById('errorSelectedCountry').innerHTML = "**Select a country to proceed.";
		document.getElementById('selectedCountry').focus();
		document.getElementById('landNumber1').innerHTML = "";
		document.getElementById('landNumber2').innerHTML = "";
		document.getElementById('lastMobileNumber').innerHTML = "";
		
	}else{
		landPhoneNubmerHelper();
		getProviderTownListData();
	}

}

// var selectedCountry = $("#selectedCountry");
// alert(selectedCountry);
function getProviderTownListData() {
	var selectedCountry = document.getElementById('selectedCountry').value;
	
	if(selectedCountry == '' || selectedCountry == null){
		document.getElementById('errorSelectedTown').innerHTML = "**Select your country first.";
	}else {
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

	singleTownElement += '<select id="selectedTown" name="selectedTown">';
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

//$(document).ready(function(){
//    $('#basicForm').submit(function (e) {
//       e.preventDefault();
//       // Do something...  
////       $('#weber-form').submit();
//    
//     });
//});

function landPhoneNubmerHelper(){
	var country = $("#country-List :selected").val();
	var areaCode = $("#areaCode").val();
	var land1 = $("#land1").val();
	var land2 = $("#land2").val();
	var networkCode = $("#networkCode").val();
	var mobile = $("#mobile").val();
		
	if (!isempty(country)) {
		document.getElementById('errorLand1').innerHTML = "**Please select your country.";
	} else {
		
		document.getElementById('errorLand1').innerHTML = "";
		
		var lastLandNumber1 = "+" + country + " " + areaCode + " " + land1;
		var lastLandNumber2 = "+" + country + " " + areaCode + " " + land2;
		var lastMobilNumber = "+" + country + " " + networkCode + " " + mobile;

		document.getElementById('landNumber1').innerHTML = lastLandNumber1;
		document.getElementById('landNumber2').innerHTML = lastLandNumber2;
		document.getElementById('lastMobileNumber').innerHTML = lastMobilNumber;
	}

}
function getProviderType() {


		alert(providerUsernameValidation());
	
	//alert(vaidateCourseProviderDeatils());
//	
//	if(vaidateCourseProviderDeatils()){
//		
//		var form = $('#basicForm');
//		var formData = $(form).serialize();
//		$.ajax({
//			url : '/AdminController',
//			method : 'POST',
//			data : formData,
//			dataType : "json",
//			async : false,
//			success : function(response) {
//
//				if (response !== undefined && response !== null) {
//					// message = response.userMessage;
//					window.registerId = response.registerId;
//
//				window.responseErrorMessage = response.userMessage;
//				$("#errorProviderName").html(response.errorProviderName);
////					alert(responseErrorMessage);
////					var userErrorMessage = $("#errorMessage");
////					userErrorMessage.html("errors");
//
//
//				}
//			},
//		});
//	}
	
}