/**
 * 20161122 CM c36-add-tutor-information INIT tutor-helper.jsp
 * 20161216 CW c36-add-tutor-details Modified displayTownDetails(), getTownData() methods. 
 */
$(document).ready(function() {

	displayCountryDetails();
	//displayTownDetails();
});

$("#countryDetails").on("change", function(){
		var selected = $(this).val();
		displayTownDetails(selected);
	})

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
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(categories);
	});
}


