/**
 * 20170323 CW c157-add-tutor-employment-details-cw created experience.js file to work on tutor related experience details
 * 				//add displayCourseProviders, getCourseProviderData & ready functions
 */

$(document).ready(function() {
	displayCourseProviders();
});

function displayCourseProviders() {
	
	$.ajax({
		url : '/TutorController',
		data : {
			CCO : 'LIST_COURSE_PROVIDERS_FOR_TUTORS'
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

function getCourseProviderData(response) {
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