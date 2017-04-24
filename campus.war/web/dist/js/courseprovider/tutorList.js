/**
 * 20170420 CW c159-courseprovider-accept-tutor-request-cw created tutorList.js file to work on tutor related details for course providers
 * 20170423 CW c159-courseprovider-accept-tutor-request-cw created ready, displayTutorList, populateTuterData functions
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw complete ready & displayTutorList methods to send url parameters
 * 20170424 CW c159-courseprovider-accept-tutor-request-cw modify populateTuterData function to populate data in the Tutors table
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

function populateTuterData(response){


    var trHTML = '';
    var maxIndex = 0;
    var tutorCD = 0;
        
    $.each(response.result, function (index, value) {
    	
    	maxIndex = index;
    	var sequence = index + 1;
    	
        trHTML += '<tr><td>' + sequence + '</td><td>' + value[1].toString() + '</td><td>' + value[2].toString() + '</td><td>' 
        + value[3].toString() + '</td><td>' + value[4].toString() + '</td><td>' + value[5].toString() + '</td></tr>';
/*		+ '<input type=hidden name=employmentCode'+sequence+' id=employmentCode'+sequence+'  value=' + value[6].toString() + '>'
		+ '<input type=hidden name=tutorCode'+sequence+' id=tutorCode'+sequence+'  value=' + value[0].toString() + '>'
		+ '<input type=hidden name=employerCode'+sequence+' id=employerCode'+sequence+' value=' + value[1].toString() + '>'
		+ '<input type=checkbox id=isSelected'+sequence+' name=isSelected'+sequence+' value=1></td></tr>';*/
        
    });
    
    $('#Tutors').append(trHTML);	    
    document.getElementById('maxSequence').value = maxIndex;
    /*
	<th>Name</th>
	<th>Gender</th>
	<th>Email</th>
	<th>Land Number</th>
	<th>Mobile Number</th>
	<th>Status</th>
	<th>Approve</th>*/
    /*
	singleEmploymentTutorsList.add(rs.getString("TUTORCODE"));
				singleEmploymentTutorsList.add(rs.getString("NAME"));
				singleEmploymentTutorsList.add(rs.getString("GENDER"));
				singleEmploymentTutorsList.add(rs.getString("EMAIL"));
				singleEmploymentTutorsList.add(rs.getString("LANDNUMBER"));
				singleEmploymentTutorsList.add(rs.getString("MOBILENUMBER"));
				singleEmploymentTutorsList.add(rs.getString("EMPCODE"));
				singleEmploymentTutorsList.add(rs.getString("VERIFSTATUS"));
				singleEmploymentTutorsList.add(rs.getString("CPCODE"));
     */
    
    
    
}