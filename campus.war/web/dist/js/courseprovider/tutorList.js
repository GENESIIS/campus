/**
 * 20170420 CW c159-courseprovider-accept-tutor-request-cw created tutorList.js file to work on tutor related details for course providers
 * 20170423 CW c159-courseprovider-accept-tutor-request-cw created ready, displayTutorList, populateTuterData functions
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw complete ready & displayTutorList methods to send url parameters
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw modify populateTuterData function to populate data in the Tutors table
 * 20170425 CW c159-courseprovider-accept-tutor-request-cw finalize the populateTuterData function after adding check boxes & hidden items
 * 20170425 CW c159-courseprovider-accept-tutor-request-cw add confirmationStatus into the table & fillListItems method
 * 20170426 CW c159-courseprovider-accept-tutor-request-cw modify fillListItems method to fill employmentCode WIP
 * 20170427 CW c159-courseprovider-accept-tutor-request-cw modify fillListItems method to fill employmentCode 
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
    
    fillListItems(maxIndex, response); 
}

function fillListItems(maxIndex, response){
	for(i = 1; i <= maxIndex + 1; i++){
		
		listId = "confirmationStatus" + i;
		var status = $("#" + listId);
		status.find('option').remove();
		
		$('<option>').val("0").text("--- Select Status ---").appendTo(status);
		
		$.each(response.applicationStatusMap, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = index;
			var y = data[0].toString();
			
			//if(z == selected){
				$('<option>').val(x).text(y).appendTo(status);
			//}
		});		
	}
}

/*
function getTownData(response, selected) {
	var categories = $("#townDetails");
	categories.find('option').remove();
	$('<option>').val("0").text("--- Select town ---").appendTo(categories);
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
}*/










