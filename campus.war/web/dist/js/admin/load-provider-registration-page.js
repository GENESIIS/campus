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

	singleTypeElement += '<select id="selectedProviderType" name="selectedProviderType" >';
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
				 displayProviderTypes();
			}
		},
	});
}

function displayProviderCountries() {
	var countryCollection = window.countryCollection;
	var singleCountryElement = '';

	singleCountryElement += '<select id="selectedCountry" name="selectedCountry" onchange="getProviderTownListData()"><option>--Default--</option>';
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

// var selectedCountry = $("#selectedCountry");
// alert(selectedCountry);
function getProviderTownListData() {
	var selectedCountry = document.getElementById('selectedCountry').value;

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

function providerUsernameValidation() {

	var selectedUsername = document.getElementById('providerUsername').value;
	var userEmail = document.getElementById('providerEmail').value;

	if (selectedUsername == "" || selectedUsername == null) {
		alert("give a username");
	}

	$.ajax({
		url : '/AdminController',
		method : 'POST',
		data : {
			'CCO' : 'COURSE_PROVIDER_VALIDATION',
			'action' : 'COURSE_PROVIDER_USERNAME_VALIDATION',
			'username' : selectedUsername,
			'email' : userEmail
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				// window.townCollection = response.userMessage;
				var errorUsername = $("#errorUsername");
				errorUsername.html(response.errorUsername);
			//	alert(response.userMessage);
			}
		},
	});
}

function providerPrefixValidation() {

	var selectedPrefix = document.getElementById('uniquePrefix').value;

	if (selectedPrefix == "" || selectedPrefix == null) {
		alert("give a prefix");
	} else if(selectedPrefix != "" || selectedPrefix != null){

		$.ajax({
			url : '/AdminController',
			method : 'POST',
			data : {
				'CCO' : 'COURSE_PROVIDER_VALIDATION',
				'action' : 'COURSE_PROVIDER_PREFIX_VALIDATION',
				'prefix' : selectedPrefix
			},
			dataType : "json",
			async : false,
			success : function(response) {

				if (response !== undefined && response !== null) {
					window.responseErrorPrefix = response.userMessage;
				 var prefixMessage = $('#errorPrefix');
				 prefixMessage.val = (response.userMessage);
					showUserMessage();
				}
			},
		});
	}

}

function showUserMessage(){
	if(window.UserMessage != null){
		var errorPrefix = $("#errorPrefix");
		errorPrefix.html(window.responseErrorMessage);
	}
}
//$(document).ready(function(){
//    $('#basicForm').submit(function (e) {
//       e.preventDefault();
//       // Do something...  
////       $('#weber-form').submit();
//    
//     });
//});

function getProviderType() {

	alert(vaidateCourseProviderDeatils());
	

	//var form = $('#basicForm');
//	var formData = $(form).serialize();
//	$.ajax({
//		url : '/AdminController',
//		method : 'POST',
//		data : formData,
//		dataType : "json",
//		async : false,
//		success : function(response) {
//
//			if (response !== undefined && response !== null) {
//				// message = response.userMessage;
//				window.registerId = response.registerId;
//
//			window.responseErrorMessage = response.userMessage;
//			$("#errorProviderName").html(response.errorProviderName);
////				alert(responseErrorMessage);
////				var userErrorMessage = $("#errorMessage");
////				userErrorMessage.html("errors");
//
//
//			}
//		},
//	});
}