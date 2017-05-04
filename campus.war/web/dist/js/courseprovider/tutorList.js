/**
 * 20170420 CW c159-courseprovider-accept-tutor-request-cw created tutorList.js file to work on tutor related details for course providers
 * 20170423 CW c159-courseprovider-accept-tutor-request-cw created ready, displayTutorList, populateTuterData functions
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw complete ready & displayTutorList methods to send url parameters
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw modify populateTuterData function to populate data in the Tutors table
 * 20170425 CW c159-courseprovider-accept-tutor-request-cw finalize the populateTuterData function after adding check boxes & hidden items
 * 20170425 CW c159-courseprovider-accept-tutor-request-cw add confirmationStatus into the table & fillListItems method
 * 20170426 CW c159-courseprovider-accept-tutor-request-cw modify fillListItems method to fill employmentCode WIP
 * 20170427 CW c159-courseprovider-accept-tutor-request-cw modify fillListItems method to fill employmentCode 
 * 20170427 CW c159-courseprovider-accept-tutor-request-cw modify fillListItems method to fill List Item conditionally
 * 20170428 CW c159-courseprovider-accept-tutor-request-cw add validations to cpCode variable in ready function
 * 20170428 CW c159-courseprovider-accept-tutor-request-cw add Requested date value into the table & removed the commented lines
 * 20170504 CW c159-courseprovider-accept-tutor-request-cw removed validation for cpCode in ready function
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
    	
        trHTML += '<tr><td>' + sequence + '</td><td>' + value[10].toString() + '</td><td>' + value[1].toString() + '</td><td>'
        + value[2].toString() + '</td><td>' + value[3].toString() + '</td><td>' 
        + value[4].toString() + '</td><td>' + value[5].toString() + '</td><td>' + value[7].toString() + '</td><td>' 
        + '<select name=confirmationStatus'+sequence+' id=confirmationStatus'+sequence+'> <option></option></td>'
		+ '<input type=hidden name=employmentCode'+sequence+' id=employmentCode'+sequence+' value=' + value[6].toString() + '>'
        
    });
    
    $('#Tutors').append(trHTML);	    
    document.getElementById('maxSequence').value = maxIndex;
    
    fillListItems(maxIndex, response); 
}

function fillListItems(maxIndex, response){
	$.each(response.result, function (index, resultValue) {
		
		var sequence = index + 1;
		
		listId = "confirmationStatus" + sequence;
		var status = $("#" + listId);
		status.find('option').remove();
		
		$('<option>').val("-99").text("--- Select Status ---").appendTo(status);
		
		$.each(response.applicationStatusMap, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = index;
			var y = data[0].toString();
			
			var val1 = resultValue[9].toString(); // added for the testing purposes
			var val2 = resultValue[7].toString(); // added for the testing purposes
			
			if(resultValue[9].toString() == "COURSEPROVIDER" && resultValue[7].toString() == "Active" && y == "Delete"){
				$('<option>').val(x).text(y).appendTo(status);
			}
			
			if(resultValue[9].toString() == "COURSEPROVIDER" && resultValue[7].toString() == "Pending" && y == "InActive"){
				y = "Remove";
				$('<option>').val(x).text(y).appendTo(status);
			}

			if(resultValue[9].toString() == "TUTOR" && resultValue[7].toString() == "Active" && y == "Delete"){
				$('<option>').val(x).text(y).appendTo(status);
			}

			if(resultValue[9].toString() == "TUTOR" && resultValue[7].toString() == "Pending" && (y == "InActive" || y == "Active")){
				if(y == "InActive"){
					y = "Remove";
				}else{
					y = "Approve";
				}
				$('<option>').val(x).text(y).appendTo(status);
			}
		});		
	});
}










