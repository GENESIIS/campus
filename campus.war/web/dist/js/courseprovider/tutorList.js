/**
 * 20170420 CW c159-courseprovider-accept-tutor-request-cw created tutorList.js file to work on tutor related details for course providers
 * 20170423 CW c159-courseprovider-accept-tutor-request-cw created ready, displayTutorList, populateTuterData functions
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw complete ready & displayTutorList methods to send url parameters
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw modify populateTuterData function to populate data in the Tutors table
 * 20170425 CW c159-courseprovider-accept-tutor-request-cw finalize the populateTuterData function after adding check boxes & hidden items
 * 20170425 CW c159-courseprovider-accept-tutor-request-cw add confirmationStatus into the table & fillListItems method
 */

$(document).ready(function() {

	var cpCode = $("#cpCode").val();
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
			cpCode : cpCode
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
/**
 * This function is used to populate the response data in the table dynamically
 * @author CW
 * @param response
 */
function populateTuterData(response){

    var trHTML = '';
    var maxIndex = 0;
    var tutorCD = 0;
        
    $.each(response.result, function (index, value) {
    	
    	maxIndex = index;
    	var sequence = index + 1;
    	
        trHTML += '<tr><td>' + sequence + '</td><td>' + value[1].toString() + '</td><td>'
        + value[2].toString() + '</td><td>' + value[3].toString() + '</td><td>' 
        + value[4].toString() + '</td><td>' + value[5].toString() + '</td><td>' + value[7].toString() + '</td><td>' 
        + '<select name=confirmationStatus'+sequence+' id=confirmationStatus'+sequence+'> <option></option></td>'
		+ '<input type=hidden name=employmentCode'+sequence+' id=employmentCode'+sequence+' value=' + value[6].toString() + '>'
		+ '<input type=checkbox id=isRemove'+sequence+' name=isRemove'+sequence+' value=1></td><td>' 
        + '<input type=checkbox id=isApprove'+sequence+' name=isApprove'+sequence+' value=1></td></tr>';
        
    });
    
    $('#Tutors').append(trHTML);	    
    document.getElementById('maxSequence').value = maxIndex;
    
    fillListItems(maxIndex); 
}

function fillListItems(maxIndex, confirmationStatus){
	var test  = new com.genesiis.campus.validation.ApplicationStatus.ACTIVE();
	log(test.testEnum.TEST_VALUE)
}