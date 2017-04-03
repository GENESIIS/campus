/**
 * 20170323 CW c157-add-tutor-employment-details-cw created experience.js file to work on tutor related experience details
 * 				//add displayCourseProviders, getCourseProviderData & ready functions
 * 20170326 CW c157-add-tutor-employment-details-cw modified getCourseProviderData method to view course provider list correctly
 * 20170327 CW c157-add-tutor-employment-details-cw add method comments & modified getCourseProviderData method data filling item name
 * 20170328 CW c157-add-tutor-employment-details-cw add method displaySelectedCourseProviders
 * 20170328 CW c157-add-tutor-employment-details-cw modified method displaySelectedCourseProviders to fix errors wip
 * 20170328 CW c157-add-tutor-employment-details-cw add fillSelectedCourseProviderData & clearField methods
 * 20170328 CW c157-add-tutor-employment-details-cw modified & done testing on fillSelectedCourseProviderData method to add button to the table
 * 20170329 CW c157-add-tutor-employment-details-cw modified & done testing on fillSelectedCourseProviderData method to add button to the table
 * 20170329 CW c157-add-tutor-employment-details-cw modified the button of the table
 * 20170329 CW c157-add-tutor-employment-details-cw modified fillSelectedCourseProviderData method & remove the button & add a check box into the table
 * 20170330 CW c157-add-tutor-employment-details-cw modified fillSelectedCourseProviderData method & make dynamic field names
 * 20170330 CW c157-add-tutor-employment-details-cw add employmentCode hidden field in fillSelectedCourseProviderData method
 * 20170331 CW c157-add-tutor-employment-details-cw modified displaySelectedCourseProviders methods tutor code into tutorcodelist
 * 20170331 CW c157-add-tutor-employment-details-cw modified ready function & add tutor code, modified displayCourseProviders method & add tutor code as a parameter
 * 				modified displaySelectedCourseProviders method & add tutor code as a parameter to the method & to ajax call as a parameter
 * 20170402 CW c157-add-tutor-employment-details-cw modified fillSelectedCourseProviderData method & remove un wanted commented lines
 * 20170403 CW c157-add-tutor-employment-details-cw modified fillSelectedCourseProviderData method table filling sequence
 */

$(document).ready(function() {
	var tutorCode = $("#tutorcodelist").val();
	displayCourseProviders(tutorCode);
	displaySelectedCourseProviders(tutorCode);
});

/**
 * This method used to query featured course provider details from the database
 * @author CW
 */
function displayCourseProviders(tutorCode) {
	$.ajax({
		url : '/TutorController',
		data : {
			CCO : 'LIST_COURSE_PROVIDERS_FOR_TUTORS',
			tutorCode : tutorCode
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
		if(data[1].toString() != 'Default'){
			var x = data[0].toString(); // code
			var y = data[1].toString(); // name
			$('<option>').val(x).text(y).appendTo(categories);
		}
	});
}

/**
 * This method used to query tutor selected featured course provider details from the database
 * @author CW
 */
function displaySelectedCourseProviders(tutorCode) {
	$.ajax({
		url : '/TutorController',
		method : 'POST',
		async : false,
		data : {
			CCO : 'LIST_SELECTED_COURSE_PROVIDERS_FOR_TUTORS',
			tutorCode : tutorCode
		},
		dataType : "json",
		success : function(response) {
			fillSelectedCourseProviderData(response);
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
function fillSelectedCourseProviderData(response) {

    var trHTML = '';
    var maxIndex = 0;
        
    $.each(response.result, function (index, value) {
    	
    	maxIndex = index;
    	var sequence = index + 1;
    	
        trHTML += '<tr><td>' + sequence + '</td><td>' + value[2].toString() + '</td><td>' 
		+ value[3].toString() + '</td><td>' + value[4].toString() + '</td><td>' + value[5].toString() + '</td><td>'
		+ '<input type=hidden name=employmentCode'+sequence+' id=employmentCode'+sequence+'  value=' + value[6].toString() + '>'
		+ '<input type=hidden name=tutorCode'+sequence+' id=tutorCode'+sequence+'  value=' + value[0].toString() + '>'
		+ '<input type=hidden name=employerCode'+sequence+' id=employerCode'+sequence+' value=' + value[1].toString() + '>'
		+ '<input type=checkbox id=isSelected'+sequence+' name=isSelected'+sequence+' value=1></td></tr>';
        
    });
    
    $('#employers').append(trHTML);	    
    document.getElementById('maxSequence').value = maxIndex;
    
}

function clearField(elementId) {
	$(document).find('#' + elementId).text('');
	$(document).find('#message').text('');
}