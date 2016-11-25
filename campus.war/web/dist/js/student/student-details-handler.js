/**
 * 20161030 PN this file contains all the functions to handle the student details.
 * 			   implemented getAjaxData(response), displayDetails() methods.
 */

$(document).ready(function() {
	
}

function displayDetails() {
	$.ajax({
		url : '../../StudentController',
		data : {
			CCO : 'GSD'
		},
		dataType : "json",
		success : function(response) {
			getStudentData(response);
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
}

function getAjaxData(response) {
	//Set Scheme details
	var sseStream = $("#sseStream");
	sseStream.find('option').remove();
	$.each(response.majorCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(sseStream);
	});
	
	//Set Qualification details
 	var sseQualification = $("#sseQualification");
 	sseQualification.find('option').remove();
	$.each(response.schoolGradeCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(sseQualification);
	});
	
	//Set Country details
 	var sseCountry = $("#sseCountryList");
 	sseCountry.find('option').remove();
	$.each(response.country2Collection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(sseCountry);
	});
	
	//Set medium details
 	var sseMedium = $("#sseMedium");
 	sseMedium.find('option').remove();
	$.each(response.mediumCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(sseMedium);
	});
	
	if(!response.result) {
        
    }
}