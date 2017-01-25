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
				    $('#example').DataTable({
					data : window.tutorList,
					  columns : [
				                   {data: 0},
				                   {data: 1},
				                   {data: 2},
				                   {data: 3},
				                   {data: 4},
				                   {data: 5},
				                   {data: 8},
				                   {data: 10},
				                   {data: 14},
				                   {data: 15},
				                   {data: 16},
				                   {data: 11},
				                   {data: 17}            
				                   
				                   ]

				});
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
				//alert(window.tutorList[0]);	
//				$.each(window.tutorList, function(index, value) {
//				$.each(value, function(index, value1) {
//					alert(value1);
//				});
//					alert(value[0]);
//				});
//				
//				tutorDataTable();
			}
		},
	});
}

function sad(){
	var t =  $('#tutor-list').DataTable();
	var tutors = window.tutorList;
	var rowCount = 0;
	t.clear().draw();

	if (tutors !== undefined & tutors !== null) {
		$
				.each(
						tutors,
						function(index, value) {

							var res = value.toString();
							var data = res.split(",");
							rowCount++;

t.row.add([
           		'<tr> <td>' + value[0] + '</td>'
           		+ '<td>' + value[1] + value[2] + value[3] + '</td>'
           		+ '<td>' + value[4] + '</td>'
           		+ '<td>' + value[5] + '</td>'
           		+ '<td>' + value[6] + + value[7] + value[8] + '</td>'
           		+ '<td>' + value[6] + + value[9] + value[10] + '</td>'
           		+ '<td>' + value[12] + + value[13] + value[14] + '</td>'
           		+ '<td>' + value[15] + '</td>'
           		+ '<td>' + value[6] + '</td>' 
           		+ '<td>' + value[16] + '</td>'
           		+ '<td>' + value[11] + ' <span class="glyphicon glyphicon-ok"></span></td>'
           		+ '<td>' + value[11] + ' <span class="glyphicon glyphicon-remove"></span></td>'
           		+ '<td>' + value[17] + ' <span>Active</span></td>'
           		+ '<td>' + value[17] + ' <span>Inactive </span></td>'
           		+ '</tr>'
           ]).draw(false);

			
		});
	}
	
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



function tutorDataTable() {

	var t = $('#example').DataTable();

	var counter = 0;
	t.clear().draw();
	$.each(window.tutorList, function(index, value) {


		t.row.add(
				[
						'<tr>'
								+ '<td>' + value[0] + '</td>'
								+ '<td>' + value[1] + value[2] + value[3] + '</td>'
								+ '<td>' + value[4] + '</td>'
								+ '<td>' + value[5] + '</td>'
								+ '<td>' + value[6] + + value[7] + value[8] + '</td>'
								+ '<td>' + value[6] + + value[9] + value[10] + '</td>'
								+ '<td>' + value[12] + + value[13] + value[14] + '</td>'
								+ '<td>' + value[15] + '</td>'
								+ '<td>' + value[6] + '</td>'
								+ '<td>' + value[16] + '</td></tr>'
				]).draw(false);
		});
}