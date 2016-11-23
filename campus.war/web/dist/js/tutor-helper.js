/**
 * 20161122 CM c36-add-tutor-information INIT tutor-helper.jsp
 */
$(document).ready(function() {

	displayTownDetails();
	displayCountryDetails();

});

function displayCountryDetails() {
	
	$.ajax({
		url : 'TutorController',
		data : {
			CCO : 'LIST_COUNTRY_DATA'
		},
		dataType : "json",
		success : function(response) {
			getCountryData(response);
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

function displayTownDetails() {
	
	$.ajax({
		url : 'TutorController',
		data : {
			CCO : 'LIST_TOWN_DATA'
		},
		dataType : "json",
		success : function(response) {
			getTownData(response);
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

function getTownData(response) {
	var categories = $("#townDetails");
	categories.find('option').remove();
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(categories);
	});
}
function getCountryData(response) {
	var categories = $("#countryDetails");
	categories.find('option').remove();
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(categories);
	});
}


