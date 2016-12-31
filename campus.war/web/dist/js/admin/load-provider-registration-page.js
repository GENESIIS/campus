/**
 * This Java script file used to help with admin functions author JH 20161201
 * 
 */

window.countryCollection = null;
window.countryCode = null;
window.townCollection = null;
window.usernameValidation = true;
window.courseProviderTypes  = null;

$(document).ready(function() {
	arrangeUI();
	getCourseProviderTypes();
});


function arrangeUI() {
	document.getElementById("logoPanel").style.display = "none";

}


window.onload = function() {
	getProviderPageLoadData();
//	getCourseProviderTypes();
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
			singleTownElement += '<option value="' + value[2] + '">';
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
				var countryNames = $("#usermessage");
				countryNames.html(response.userMessage);
				alert(response.userMessage);
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
					window.UserMessage = response.userMessage;
					var prefixMessage = $('#usermessage').val;
					prefixMessage.val = (response.userMessage);
					alert(response.userMessage);
				}
			},
		});
	}

}

function getProviderType() {

	var status = false;
	var radioValue = $("input[name='featured-oneoff']:checked").val();
	var statusValue = $("input[name='providerStatus']:checked").val();

	var form = $('#basicForm');
	var formDate = $(form).serialize();
	$.ajax({
		url : '/AdminController',
		method : 'POST',
		data : formData,
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				// message = response.userMessage;
				var registerId = response.result;

				alert("success" + registerId);
				document.getElementById("usermessage").html = message;
				// document.getElementById("logoPanel").style.display
				// = "block";
				// document.getElementById("basicForm").style.display
				// = "none";

			}
		},
	});
}