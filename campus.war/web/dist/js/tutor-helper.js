/**
 * 20161122 CM c36-add-tutor-information INIT tutor-helper.jsp
 * 20161216 CW c36-add-tutor-details Modified displayTownDetails(), getTownData() methods. 
 * 20170116 CW c36-add-tutor-details add loadTutorWithDummyData() method. 
 * 20170116 CW c36-add-tutor-details removed loadTutorWithDummyData() & add displayTownDefaults() method.
 * 20170124 CW c36-add-tutor-details modified according to the 201701201215 DJ crev modification request.
 */
$(document).ready(function() {
	displayCountryDetails();
	displayTownDefaults();
});

$("#countryDetails").on("change", function(){
		var selected = $(this).val();
		displayTownDetails(selected);
		$("#mobileCountryCode").val("+" + selected);
		$("#landCountryCode").val("+" + selected);
	});

	
function displayCountryDetails() {
	
	$.ajax({
		url : '/TutorController',
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

function displayTownDetails(selected) {
	
	$.ajax({
		url : '/TutorController',
		data : {
			CCO : 'LIST_TOWN_DATA'
		},
		dataType : "json",
		success : function(response) {
			getTownData(response, selected);
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

function getTownData(response, selected) {
	var categories = $("#townDetails");
	categories.find('option').remove();
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		var z = data[2].toString();
		if(z == selected){
			$('<option>').val(x).text(y).appendTo(categories);
		}
	});
}

function getCountryData(response) {
	var categories = $("#countryDetails");
	categories.find('option').remove();
	$('<option>').val("0").text("--- Select Country ---").appendTo(categories);
	
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(categories);
	});
}

function displayTownDefaults() {
	var categories = $("#townDetails");
	categories.find('option').remove();
	$('<option>').val("0").text("--- Select Country before select the town ---").appendTo(categories);

}
