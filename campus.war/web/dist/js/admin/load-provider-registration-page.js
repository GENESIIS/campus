/**
 * This Java script file used to help with admin functions author JH 20161201
 * 
 */


window.countryCollection = null;

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
				window.countryCollection = response.requestData;
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
		$
				.each(
						countryCollection,
						function(index, value) {
							singleCountryElement += '<option>';
							//singleCountryElement += '<button type="submit" name="CCO" id="CCO" class="btn btn-info navbar-btn" value="LIST_CATEGORY_LANDING_PAGE">'
							//		+ value[1] + '</button>';
							singleCountryElement +=  value[1] ;
							singleCountryElement += '</option>';

						});
	}
	singleCountryElement += '</select>';
	var countryNames = $("#country-List");
	countryNames.html(singleCountryElement);
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