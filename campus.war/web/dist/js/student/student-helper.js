/**
 * 20161125 PN this file contains all the functions to handle the student
 * details. implemented getAjaxData(response), displayDetails() methods.
 * implemented addEducationDetails(), clearSchoolEducationForm() methods.
 * 20161126 PN c26-add-student-details: implemented validateForm() and modified code in addEducationDetails() method.
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
	// Set Scheme details
	var sseStream = $("#sseStream");
	sseStream.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(sseStream);
	$.each(response.majorCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseStream);
	});
	
	// Set Qualification details
 	var sseQualification = $("#sseQualification");
 	sseQualification.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(sseQualification);
	$.each(response.schoolGradeCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseQualification);
	});
	
	// Set Country details
 	var sseCountry = $("#sseCountryList");
 	sseCountry.find('option').remove();
 //	$('<option>').val("").text("--Select One--").appendTo(sseCountry);
	$.each(response.country2Collection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(sseCountry);
	});
	
	// Set medium details
 	var sseMedium = $("#sseMedium");
 	sseMedium.find('option').remove();
 	$('<option>').val("").text("--Select One--").appendTo(sseMedium);
	$.each(response.mediumCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(sseMedium);
	});
	
	if(response.result) {
        alert("I'm Here");
        alert("response.result "+response.result)
        $.each(response.result, function(index, value) {
        	var res = value.toString();
    		var data = res.split(",");
        	$('#sseQualification').val(data[2]);
        	$('#sseStream').val(data[3]);
        	$('#sseResult').val(data[5]);
        	$('#sseMedium').val(data[10]);
        	$('#sseIndexNo').val(data[6]);
        	$('#sseSchool').val(data[7]);
        	$('#sseAchievedon').val(data[8]);
        	$('#sseCountry').val("Russian Federation");
        	$('#sseDescription').val(data[9]);       	
        });
    }
}

// Get data and sent to CmdAddSchoolEducationData.java.
function addEducationDetails() {
	if(validateForm()){
	var schoolGrade = $('#sseQualification').val();
	var major = $('#sseStream').val();
	var result = $('#sseResult').val();
	var medium = $('#sseMedium').val();
	var indexNo = $('#sseIndexNo').val();
	var schoolName = $('#sseSchool').val();
	var achievedOn = $('#sseAchievedon').val();
	var country = getSelectedData('sseCountry','sseCountryList');
	var description = $('#sseDescription').val();

	var jsonData = {
		"schoolGrade" : schoolGrade,
		"major" : major,
		"result" : result,
		"medium" : medium,
		"indexNo" : indexNo,
		"schoolName" : schoolName,
		"achievedOn" : achievedOn,
		"country" : country,
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
			if(data.saveChangesStatus){	
					if(data.saveChangesStatus === "Unsuccessful."){
						$("#saveChangesStatus").addClass("alert alert-danger").text(data.saveChangesStatus).show();
					}else if(data.saveChangesStatus === "Invalid Information"){
						$("#saveChangesStatus").addClass("alert alert-danger").text("Invalid Information.").show();
					}
				clearSchoolEducationForm();	
				$("#saveChangesStatus").addClass("alert alert-success").text(data.saveChangesStatus).show();
			}
		},
		error : function(e) {
			alert("Error " + e);
			$("#saveChangesStatus").addClass("alert alert-warning").text(e).show();
		}
	});
	}
}

/**
 * This is to clear form filled data
 * 
 * @returns void
 */
function clearSchoolEducationForm() {
	$('#sseQualification').get(0).selectedIndex = 0;
	$('#sseStream').get(0).selectedIndex = 0;
	$('#sseResult').get(0).selectedIndex = 0;
	$('#sseMedium').get(0).selectedIndex = 0;
	$('#sseIndexNo').val("");
	$('#sseSchool').val("");
	$('#sseAchievedon').val("");
	$('#sseCountry').val("");
	$('#sseDescription').val("");
}

function validateForm(){
	isDropdownSelected(isemptyDropdown(("sseIndexNo")),"IndexNO","sseIndexNoError");
	isDropdownSelected(isemptyDropdown(("sseSchool")),"School name","sseSchoolError");
	isDropdownSelected(isemptyDropdown(("sseAchievedon")),"Achived on date","sseAchievedonError");
	
	isDropdownSelected(isemptyDropdown('sseQualification'),"Qualification","sseQualificationError");
	isDropdownSelected(isemptyDropdown('sseStream'),"Stream","sseStreamError");
	isDropdownSelected(isemptyDropdown('sseResult'),"Result","sseResultError");
	isDropdownSelected(isemptyDropdown('sseMedium'),"Medium","sseMediumError");

	if(($('#sseIndexNoError').text() != '')||($('#sseSchoolError').text() != '')||($('#sseAchievedonError').text() != '')||
	($('#sseQualificationError').text() != '')||($('#sseStreamError').text() != '')||($('#sseResultError').text() != '')||
	($('#sseMediumError').text() != '')){
		return false;
	}else{
		return true;
	}
}