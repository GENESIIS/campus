/**
 * 20170302 JH c96 public-list-tutor-helper.js created to help list tutors for the public
 * 20170306 JH c96 removed unwanted lines and display tutor image with static system config file path, added new method selectTutorRecord(code)
 */

window.tutorList = null;

window.onload = function() {
	listPublicTutors();
};

/**
 * Creates a the database call to retrieve list of tutors 
 * @returns
 * @author JH
 */
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
			//	    $('#example tbody').on('click', 'tr', function () {

			}
		},
	});
}

/**
 * used to select a specific record of a tutor and pass the value to the next page
 * @param code
 * @returns
 * @author JH
 */
function selectTutorRecord(code){
	  var tutorCode = $(this).find("td:first").html();
	   
	   alert("will direct to tutor public profile. and the tutor is " + tutorCode);
	   var form = document.createElement('form');
	   form.method = 'post';
	   form.action = '/dist/partials/public/display-tutors.jsp';
	   var input = document.createElement('input');
	   input.type = 'text';
      input.name = 'tutorCode';
      input.value = tutorCode;
      form.appendChild(input);
      form.submit();
}


/**
 * Used to generate the tutor table using datatables
 * @returns
 * @author JH
 */
function DisplayTutorTable(){
	var t =  $('#example').DataTable();
	var tutors = window.tutorList;
	var rowCount = 0;
	var tutorImagePath = "education/tutor/pro_image/";
	var fileSeparator = "/";
	var extension = ".jpg";
	t.clear().draw();
	var imageFile = null;
	var defaultImage  = '/education/tutor/pro_image/default.jpg';

	if (tutors !== undefined & tutors !== null) {
		$
				.each(
						tutors,
						function(index, value) {

							 rowCount++;
							var value1 = null;
							
							imageFile = fileSeparator + tutorImagePath + value[0] + fileSeparator + value[0]+extension;
							value1 = '  <img src="' + imageFile + '" alt="" onerror="this.src = ' + defaultImage + '">';
	
t.row.add(
									[
									 
rowCount,
											value1,
											value[1] + '&nbsp;' + value[2]
													+ '&nbsp;' + value[3],
											value[4],
											value[5]

									]).draw(false);

			
		});
	}
	
}