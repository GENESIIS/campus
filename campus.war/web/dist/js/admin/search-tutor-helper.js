/**
 * This java script file is to support admin list tutor function
 * 20170117 JH c134-admin-list-new-tutor-requests tutorDataTable() modified: used ApplicationStatus enum class values to add css styles to datatable 
 * 				removed displayTutotList() unwanted method, added AJAX on error method
 * 20170321 JH c134-admin-list-new-tutor-requests remove code used to select table record details and added new event to select tutor code
 * 				on click, show error messages on AJAX call error 
 * 20170425 JH c134-admin-list-new-tutor-requests added styles for approval status and tutor status when they are null or not defined
 * 20170427 JH c134-admin-list-new-tutor-requests added tooltips for approval status
 */

window.tutorList = null;
window.ApplicationStatus = null;

/**
 * list all tutors on page load
 */
window.onload = function() {
	listAllTutors();	
};

/**
 * listAllTutors() used to list all tutors in the tutor table. 
 * This will list tutors in descending order.
 * @author JH
 */
function listAllTutors(){
	
	$("#button-all").addClass("active");
	$("#button-new").removeClass("active");
	
	$.ajax({
		url : '/AdminController',
		method : 'POST',
		data : {
			'CCO' : 'LIST_TUTORS'
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				window.tutorList = response.result;
				window.ApplicationStatus = response.applicationStatus;

				tutorDataTable();
			}
		},
		error : function(x, status, error) {
			var err = displayErrorMessage(x, status, error);
			$("#userMessage").html(err);
		}
	});
}

/**
 * This listTutorRequests() method will list all the new 
 * tutor requests. ( where, ApprovalStatus = PENDING)
 * @author JH
 */
function listTutorRequests(){
	
	$("#button-new").addClass("active");
	$("#button-all").removeClass("active");
	
	$.ajax({
		url : '/AdminController',
		method : 'POST',
		data : {
			'CCO' : 'LIST_NEW_TUTOR_REQUESTS'
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				window.tutorList = response.result;
				window.ApplicationStatus = response.applicationStatus;

				tutorDataTable();
			}
		},
		error : function(x, status, error) {
			var err = displayErrorMessage(x, status, error);
			$("#userMessage").html(err);
		}
	});
}


/**
 * tutorDataTable() used to display tutor details into a datatable
 * @author JH
 */
function tutorDataTable(){
	var t =  $('#tutors-table').DataTable();
	var tutors = window.tutorList;
	var rowCount = 0;
	t.clear().draw();
	var statusValues = window.ApplicationStatus;

	if (tutors !== undefined & tutors !== null) {
		$
				.each(
						tutors,
						function(index, value) {

							var res = value.toString();
							var data = res.split(",");
							rowCount++;
							var value11 = null;
							/* 
							 * compare tutor request status with the application status values
							 */
							if (value[11] == statusValues["ACTIVE"]) {
								
								value11 = '<a href="#" data-toggle="tooltip" title="ACTIVE">';
								value11 += '<span class="glyphicon glyphicon-ok" style="color:green;"></a>';
								value11 += '<label class="hide-value">ACTIVE</label></span>';
							
							} 
							else if (value[11] == statusValues["INACTIVE"]) {
								
								value11 = '<a href="#" data-toggle="tooltip" title="INACTIVE">';
								value11 += '<span class="glyphicon glyphicon-remove" style="color:red;"></a>';
								value11 += '<label class="hide-value">INACTIVE</label></span>';
								
								 
							}
							else if (value[11] == statusValues["PENDING"]) {
								
								value11 = '<a href="#" data-toggle="tooltip" title="PENDING">';
								value11 += ' <span class="glyphicon glyphicon-asterisk" style="color:blue;"></a>';
								value11 += '<label class="hide-value">PENDING</label></span>';

							}else{
								value11 = '<a href="#" data-toggle="tooltip" title="Null or Empty value">';
								value11 += ' <span class="glyphicon glyphicon-question-sign" style="color:red;"></a>';
								value11 += '<label class="hide-value"> NULL </label></span>';

							}
							
							var value17 = null; 
							var element = value[17];
							if( value[17] == statusValues["ACTIVE"]){
								value17 = ' <span style="color:green;">Active</span></td>';
							}else if( value[17] == statusValues["INACTIVE"]){
								value17 = ' <span style="color:red;">Inactive </span></td>';
							}else if( value[17] == statusValues["PENDING"]){
								value17 = ' <span style="color:blue;">Pending </span></td>';
							}else if( value[17] == statusValues["EXPIRED"]){
								value17 = ' <span style="color:red;">Expired </span></td>';
							}else{
								value17 = ' <span style="color:red;"> -  </span></td>';
							}
							
t.row.add(
									[
									 		rowCount,
											value[0],
											value[1] + '&nbsp;' + value[2]
													+ '&nbsp;' + value[3],
											value[4],
											value[5],
											value[6] + value[7] + '&nbsp;'
													+ value[8],
											value[6] + value[9] + '&nbsp;'
													+ value[10],
											value[15],
											value[16],
											value11,
											value17

									]).draw(false);

			
		});
	}
	/* scripts related to tool tip */
    $('[data-toggle="tooltip"]').tooltip(); 
	
}

/**
 * To select the tutor code on table row selection
 */
$(document).ready(function() {
	
	/* scripts related to tool tip */
    $('[data-toggle="tooltip"]').tooltip(); 

    var table = $('#tutors-table').DataTable();
     
    $('#tutors-table tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        alert( 'The tutor code is '+data[1]+'.' );
    } );
} );
	