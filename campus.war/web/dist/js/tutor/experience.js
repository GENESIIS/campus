/**
 * 20170323 CW c157-add-tutor-employment-details-cw created experience.js file to work on tutor related experience details
 * 				//add displayCourseProviders, getCourseProviderData & ready functions
 * 20170326 CW c157-add-tutor-employment-details-cw modified getCourseProviderData method to view course provider list correctly
 * 20170327 CW c157-add-tutor-employment-details-cw add method comments & modified getCourseProviderData method data filling item name
 */

$(document).ready(function() {
	displayCourseProviders();
});

/**
 * This method used to query featured course provider details from the database
 * @author CW
 */
function displayCourseProviders() {
	
	$.ajax({
		url : '/TutorController',
		data : {
			CCO : 'LIST_COURSE_PROVIDERS_FOR_TUTORS'
		},
		dataType : "json",
		success : function(response) {
			getCourseProviderData(response);
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

/**
 * This method used to fill list item from the data
 * @author CW
 * @param response
 */
function getCourseProviderData(response) {
	var categories = $("#employerDetails");
	categories.find('option').remove();
	$('<option>').val("-1").text("--- Select employee ---").appendTo(categories);
	
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		if(data[3].toString() != 'Default'){
			var x = data[0].toString(); // code
			var y = data[3].toString(); // name
			$('<option>').val(x).text(y).appendTo(categories);
		}
	});
}