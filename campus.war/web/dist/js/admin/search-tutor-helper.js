/**
 * This java script file is to support admin list tutor function
 * 20170117 JH C133-admin-list-tutor
 */

window.tutorList = null;

window.onload = function() {
	listAllTutors();
};

function listAllTutors(){
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

				displayTutotList();
			}
		},
	});
}

function displayTutotList(){
	
	var tutors = window.tutorList;
	var singleTutorElement = '';

	if (tutors !== undefined & tutors !== null) {
		$.each(tutors, function(index, value) {
			
			singleTutorElement += '<tr>';
			singleTutorElement += '<td>' + value[0] + '</td>';
			singleTutorElement += '<td>' + value[1] + value[2] + value[3] + '</td>';
			singleTutorElement += '<td>' + value[4] + '</td>';
			singleTutorElement += '<td>' + value[5] + '</td>';
			singleTutorElement += '<td>' + value[6] + + value[7] + value[8] + '</td>';
			singleTutorElement += '<td>' + value[6] + + value[9] + value[10] + '</td>';
			singleTutorElement += '<td>' + value[12] + + value[13] + value[14] + '</td>';
			singleTutorElement += '<td>' + value[15] + '</td>';
			singleTutorElement += '<td>' + value[6] + '</td>';
			singleTutorElement += '<td>' + value[16] + '</td>';
			
			if( value[11] == 1){
				singleTutorElement += '<td>' + value[11] + ' <span class="glyphicon glyphicon-ok"></span></td>';
			}else if( value[11] == 0){
				singleTutorElement += '<td>' + value[11] + ' <span class="glyphicon glyphicon-remove"></span></td>';
			}
			if( value[17] == 1){
				singleTutorElement += '<td>' + value[17] + ' <span>Active</span></td>';
			}else if( value[17] == 0){
				singleTutorElement += '<td>' + value[17] + ' <span>Inactive </span></td>';
			}
//			singleTutorElement += '<td>' + value[17] + '</td>';
			singleTutorElement += '</tr>';
			
		});
	}

	var tableElement = $("#tutor-list");
	tableElement.html(singleTutorElement);
	
}