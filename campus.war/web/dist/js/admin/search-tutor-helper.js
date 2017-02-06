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

				tutorDataTable();
				    $('#example tbody').on('click', 'tr', function () {
				    	   var tutorCode = $(this).find("td:first").html();
				    	   
				    	   var form = document.createElement('form');
				    	   form.method = 'post';
				    	   form.action = '/dist/partials/admin/updateTutorProfile.jsp';
				    	   var input = document.createElement('input');
				    	   input.type = 'text';
				           input.name = 'tutorCode';
				           input.value = tutorCode;
				           form.appendChild(input);
				           form.submit();
				    	   
				    });
			}
		},
	});
}

function tutorDataTable(){
	var t =  $('#example').DataTable();
	var tutors = window.tutorList;
	var rowCount = 0;
	t.clear().draw();

	if (tutors !== undefined & tutors !== null) {
		$
				.each(
						tutors,
						function(index, value) {

//							var res = value.toString();
//							var data = res.split(",");
							// rowCount++;
							var value11 = null;
							if (value[11] == 1) {
								value11 = ' <span class="glyphicon glyphicon-ok" style="color:green;"></span>';
							} else if (value[11] == 0) {
								value11 = ' <span class="glyphicon glyphicon-info-sign" style="color:blue;"></span>';
							} else if (value[11] == 2) {
								value11 = ' <span class="glyphicon glyphicon-remove" style="color:red;"></span>';
							}
							
							var value17 = null; 
							if( value[17] == 1){
								value17 = ' <span style="color:green;">Active</span></td>';
							}else if( value[17] == 0){
								value17 = ' <span style="color:red;">Inactive </span></td>';
							}else if( value[17] == 2){
								value17 = ' <span style="color:blue;">Pending </span></td>';
							}else if( value[17] == 3){
								value17 = ' <span style="color:red;">Expired </span></td>';
							}
							
t.row.add(
									[

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
	
}


function displayTutotList(){
	
	var tutors = window.tutorList;
	$('#example').DataTable();
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

	var tableElement = $("#example");
	tableElement.html(tableElement);
	
	
}	