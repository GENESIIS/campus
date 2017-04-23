/**
 * 20170420 CW c159-courseprovider-accept-tutor-request-cw created tutorList.js file to work on tutor related details for course providers
 * 20170423 CW c159-courseprovider-accept-tutor-request-cw created ready, displayTutorList, populateTuterData functions
 */

$(document).ready(function() {
    cpCode = request.getParameter("courseProviderCode");
	displayTutorList(cpCode);  
});

/**
 * This method used to query featured course provider details from the database
 * @author CW
 */
function displayTutorList(cpCode) {
	$.ajax({
		url : '/TutorController',
		data : {
			CCO : 'VIEW_TUTORS_FOR_CP',
			courseProviderCode : cpCode
		},
		dataType : "json",
		success : function(response) {
			populateTuterData(response);
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

function populateTuterData(response){
	
}