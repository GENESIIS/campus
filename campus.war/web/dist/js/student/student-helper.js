/**
 * 20161125 PN this file contains all the functions to handle the student details.
 * 			   implemented getAjaxData(response), displayDetails() methods.
 * 			   implemented addEducationDetails(), clearSchoolEducationForm() methods.
 */

$(document).ready(function() {
	displayDetails();
});

function displayDetails() {
	$.ajax({
		url : '../../StudentController',
		data : {
			CCO : 'GSD'
		},
		dataType : "json",
		success : function(response) {
			alert(response);
			getStudentData(response);
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
}

function getStudentData(response) {
	//Set Scheme details
	var sseStream = $("#sseStream");
	sseStream.find('option').remove();
	$.each(response.majorCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseStream);
	});
	
	//Set Qualification details
 	var sseQualification = $("#sseQualification");
 	sseQualification.find('option').remove();
	$.each(response.schoolGradeCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseQualification);
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
		$('<option>').val(x).text(y).appendTo(sseMedium);
	});
	
	if(!response.result) {
        alert("I'm Here");
        $.each(response.result, function(index, value) {
        	var res = value.toString();
    		var data = res.split(",");
        	$('#sseQualification$').val(data[2]);
        	$('#sseStream').val(data[3]);
        	$('#sseResult').val(data[5]);
        	$('#sseMedium').val(data[10]);
        	$('#sseIndexNo').val(data[6]);
        	$('#sseSchool').val(data[7]);
        	$('#sseAchievedon').val(data[8]);
        	$('#sseCountry').val(data[5]);
        	$('#sseDescription').val(9);
        });
    }
}

//Get data and sent to DepartmentController.java.
function addEducationDetails() {
	var schoolGrade = $('#sseQualification$').get(0).selectedIndex = 0;
	var major = $('#sseStream').val();
	var result = $('#sseResult').val();
	var medium = $('#sseMedium').val();
	var indexNo = $('#sseIndexNo').val();
	var schoolName = $('#sseSchool').val();
	var achievedOn = $('#sseAchievedon').val();
	var countrycountry = $('#sseCountry').val();
	var description = $('#description').val();

	var jsonData = {
		"schoolGrade" : schoolGrade,
		"major" : major,
		"result" : result,
		"medium" : medium,
		"indexNo" : indexNo,
		"schoolName" : schoolName,
		"achievedOn" : achievedOn,
		"countrycountry" : countrycountry,
		"description" : description
	};

	$.ajax({
		type : "POST",
		url : '../../StudentController',
		data : {
			jsonData : JSON.stringify(jsonData),
			CCO : "ASD"
		},
		dataType : "json",
		success : function(data) {
			alert(data);
		},
		error : function(e) {
			alert("Error " + e);
		}
	});
}

function clearSchoolEducationForm() {
	$('#sseQualification$').get(0).selectedIndex = 0;
	$('#sseStream').get(0).selectedIndex = 0;
	$('#sseResult').get(0).selectedIndex = 0;
	$('#sseMedium').get(0).selectedIndex = 0;
	$('#sseIndexNo').val("");
	$('#sseSchool').val("");
	$('#sseAchievedon').val("");
	$('#sseCountry').val("");
	$('#sseDescription').val("");
}