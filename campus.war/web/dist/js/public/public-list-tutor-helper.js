/**
 * 20170302 JH c96 public-list-tutor-helper.js created to help list tutors for the public
 */

window.tutorList = null;

window.onload = function() {
	DisplayTutorTable();
};

function listPublicTutors(){
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'PUBLIC_LIST_ALL_TUTORS'
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				window.tutorList = response.result;

				DisplayTutorTable();
				    $('#example tbody').on('click', 'tr', function () {
				    	   var tutorCode = $(this).find("td:first").html();
				    	   
				    	   var form = document.createElement('form');
				    	   form.method = 'post';
				    	   form.action = '/dist/partials/public/display-tutors.jsp';
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
//
//function DisplayTutorTable(){
//	var t =  $('#example').DataTable();
//	var tutors = window.tutorList;
//	var rowCount = 0;
//	t.clear().draw();
//
//	if (tutors !== undefined & tutors !== null) {
//		$
//				.each(
//						tutors,
//						function(index, value) {
//
////							var res = value.toString();
////							var data = res.split(",");
//							// rowCount++;
//							var value11 = null;
//							if (value[11] == 1) {
//								value11 = ' <span class="glyphicon glyphicon-ok" style="color:green;"></span>';
//							} else if (value[11] == 0) {
//								value11 = ' <span class="glyphicon glyphicon-info-sign" style="color:blue;"></span>';
//							} else if (value[11] == 2) {
//								value11 = ' <span class="glyphicon glyphicon-remove" style="color:red;"></span>';
//							}
//							
//							var value17 = null; 
//							if( value[17] == 1){
//								value17 = ' <span style="color:green;">Active</span></td>';
//							}else if( value[17] == 0){
//								value17 = ' <span style="color:red;">Inactive </span></td>';
//							}else if( value[17] == 2){
//								value17 = ' <span style="color:blue;">Pending </span></td>';
//							}else if( value[17] == 3){
//								value17 = ' <span style="color:red;">Expired </span></td>';
//							}
//							
//t.row.add(
//									[
//
//											value[0],
//											value[1] + '&nbsp;' + value[2]
//													+ '&nbsp;' + value[3],
//											value[4],
//											value[5],
//											value[6] + value[7] + '&nbsp;'
//													+ value[8],
//											value[6] + value[9] + '&nbsp;'
//													+ value[10],
//											value[15],
//											value[16],
//											value11,
//											value17
//
//									]).draw(false);
//
//			
//		});
//	}
//	
//}
//


function DisplayTutorTable(){
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
							var value1 = null;
							value1 = '  <img src="../../i/public/tutors/pfr-image.jpg" alt="">';
	
t.row.add(
									[

											value1,
											value[1] + '&nbsp;' + value[2]
													+ '&nbsp;' + value[3],
											value[4],
											value[5]

									]).draw(false);

			
		});
	}
	
}