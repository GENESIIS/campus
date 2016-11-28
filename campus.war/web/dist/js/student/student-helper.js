/**
 * 20161125 PN this file contains all the functions to handle the student
 * details. implemented getAjaxData(response), displayDetails() methods.
 * implemented addEducationDetails(), clearSchoolEducationForm() methods.
 * 20161126 PN c26-add-student-details: implemented validateForm() and modified code in addEducationDetails() method.
 * 20161128 PN c26-add-student-details: implemented addProfessionalExpForm() method, validateProfessionalExpForm() method and clearProfessionalExpForm() method.
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

/**
 * This is to clear form filled data Professional Education.
 * @returns void
 */
function clearProfessionalExpForm() {
	$('#industryoftheOrganization').get(0).selectedIndex = 0;
	$('#jobCategory').get(0).selectedIndex = 0;
	$('#organization').val("");
	$('#designation').val("");
	$('#commencedOn').val("");
	$('#achievedOn').val("");
	$('#jobDescription').val("");
}

function validateProfessionalExpForm(){
	isDropdownSelected(isemptyDropdown(("organization")),"Organization","organizationError");
	isDropdownSelected(isemptyDropdown(("designation")),"Designation","designationError");
	isDropdownSelected(isemptyDropdown(("commencedOn")),"Commenced on date","commencedOnError");
	isDropdownSelected(isemptyDropdown(("achievedOn")),"Achieved on date","achievedOnError");
	
	isDropdownSelected(isemptyDropdown('industryoftheOrganization'),"Industry of the Organization","industryoftheOrganizationError");
	isDropdownSelected(isemptyDropdown('jobCategory'),"Job Category","jobCategoryError");

	if(($('#organizationError').text() != '')||($('#designationError').text() != '')||($('#commencedOnError').text() != '')||
	($('#achievedOnError').text() != '')||($('#industryoftheOrganizationError').text() != '')||($('#jobCategoryError').text() != '')){
		return false;
	}else{
		return true;
	}
}

//Get data and sent to CmdAddSchoolEducationData.java.
function addProfessionalExpForm() {
	if(validateProfessionalExpForm()){
		$('#industryoftheOrganization').val();
		$('#jobCategory').val();
		$('#organization').val();
		$('#designation').val();
		$('#commencedOn').val();
		$('#achievedOn').val();
		$('#jobDescription').val();

	var jsonData = {
	};
}
}

/**
 * All the required functions for Professional Education. 
 * @param table
 * @returns
 */
//
//Updates "Select all" control in a data table
//
function updateDataTableSelectAllCtrl(table){
var $table             = table.table().node();
var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
var chkbox_select_all  = $('thead input[name="select_all"]', $table).get(0);

//If none of the checkboxes are checked
if($chkbox_checked.length === 0){
 chkbox_select_all.checked = false;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = false;
 }

//If all of the checkboxes are checked
} else if ($chkbox_checked.length === $chkbox_all.length){
 chkbox_select_all.checked = true;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = false;
 }

//If some of the checkboxes are checked
} else {
 chkbox_select_all.checked = true;
 if('indeterminate' in chkbox_select_all){
    chkbox_select_all.indeterminate = true;
 }
}
}

$(document).ready(function (){
//Array holding selected row IDs
var rows_selected = [];
var table = $('#example').DataTable({
 'ajax': 'https://api.myjson.com/bins/1us28',
 'columnDefs': [{
    'targets': 0,
    'searchable':false,
    'orderable':false,
    'width':'1%',
    'className': 'dt-body-center',
    'render': function (data, type, full, meta){
        return '<input type="checkbox">';
    }
 }],
 'order': [1, 'asc'],
 'rowCallback': function(row, data, dataIndex){
    // Get row ID
    var rowId = data[0];

    // If row ID is in the list of selected row IDs
    if($.inArray(rowId, rows_selected) !== -1){
       $(row).find('input[type="checkbox"]').prop('checked', true);
       $(row).addClass('selected');
    }
 }
});

//Handle click on checkbox
$('#example tbody').on('click', 'input[type="checkbox"]', function(e){
 var $row = $(this).closest('tr');

 // Get row data
 var data = table.row($row).data();

 // Get row ID
 var rowId = data[0];

 // Determine whether row ID is in the list of selected row IDs 
 var index = $.inArray(rowId, rows_selected);

 // If checkbox is checked and row ID is not in list of selected row IDs
 if(this.checked && index === -1){
    rows_selected.push(rowId);

 // Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
 } else if (!this.checked && index !== -1){
    rows_selected.splice(index, 1);
 }

 if(this.checked){
    $row.addClass('selected');
 } else {
    $row.removeClass('selected');
 }

 // Update state of "Select all" control
 updateDataTableSelectAllCtrl(table);

 // Prevent click event from propagating to parent
 e.stopPropagation();
});

//Handle click on table cells with checkboxes
$('#example').on('click', 'tbody td, thead th:first-child', function(e){
 $(this).parent().find('input[type="checkbox"]').trigger('click');
});

//Handle click on "Select all" control
$('thead input[name="select_all"]', table.table().container()).on('click', function(e){
 if(this.checked){
    $('#example tbody input[type="checkbox"]:not(:checked)').trigger('click');
 } else {
    $('#example tbody input[type="checkbox"]:checked').trigger('click');
 }

 // Prevent click event from propagating to parent
 e.stopPropagation();
});

//Handle table draw event
table.on('draw', function(){
 // Update state of "Select all" control
 updateDataTableSelectAllCtrl(table);
});

//Handle form submission event 
$('#frm-example').on('submit', function(e){
 var form = this;

 // Iterate over all selected checkboxes
 $.each(rows_selected, function(index, rowId){
    // Create a hidden element 
    $(form).append(
        $('<input>')
           .attr('type', 'hidden')
           .attr('name', 'id[]')
           .val(rowId)
    );
    table.row('.selected').remove().draw( false );
 });

 // FOR DEMONSTRATION ONLY     
 
 // Output form data to a console     
 //$('#example-console').text($(form).serialize());
 alert($(form).serialize());
 //console.log("Form submission", $(form).serialize());
  
 // Remove added elements
 $('input[name="id\[\]"]', form).remove();
  
 // Prevent actual form submission
 e.preventDefault();
});
});
/**
 * --------------------------------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------------------------------
 */