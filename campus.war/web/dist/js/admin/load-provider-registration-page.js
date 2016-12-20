/**
 * This Java script file used to help with admin functions author JH 20161201
 * 
 */

window.countryCollection = null;
window.townCollection = null;

$(document).ready(function() {
	arrangeUI();
});

function arrangeUI() {
	document.getElementById("logoPanel").style.display = "none";

}

window.onload = function() {
	getProviderPageLoadData();
};

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
			}
		},
	});
}

function displayProviderCountries() {
	var countryCollection = window.countryCollection;
	var singleCountryElement = '';

	singleCountryElement += '<select><option>--Default--</option>';
	if (countryCollection !== undefined & countryCollection !== null) {
		$.each(countryCollection, function(index, value) {
			singleCountryElement += '<option value="' +value[0]+'">';
			singleCountryElement += value[1];
			singleCountryElement += '</option>';

		});
	}
	singleCountryElement += '</select>';
	var countryNames = $("#country-List");
	countryNames.html(singleCountryElement);

}

function getProviderTownListData() {

	$.ajax({
		url : '/AdminController',
		method : 'POST',
		data : {
			'CCO' : 'DISPLAY_TOWN_DATA'
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				window.townCollection = response.requestData;
				displayProviderCountries();
			}
		},
	});
}

function displayProviderTownList(){
var 
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