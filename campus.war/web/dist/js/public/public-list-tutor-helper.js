/**
 * 20170302 JH c96 public-list-tutor-helper.js created to help list tutors for the public
 * 20170306 JH c96 removed unwanted lines and display tutor image with static system config file path, added new method selectTutorRecord(code),
 * 					load tutor images using system config file path with loading default image on error source,show error messages on AJAX calls
 * 20170309 JH c96 changed the tutor table element id 'example' into 'tutorListTable' due to changes in jsp page
 * 20170310 JH c96 selectTutorRecord() method modified to display tutor private details with mobile, office, email and the town details wip
 */

window.tutorList = null;
window.tutorProfileImagePath = null;

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
				window.tutorProfileImagePath = response.tutorProfileImagePath;
				

				DisplayTutorTable();
			    $('#tutorListTable tbody').on('click', 'tr', function () {
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
			    });

			}
		},
		error : function(x, status, error) {
			var err = displayErrorMessage(x, status, error);
			document.getElementById("userMessage").style.display = "block";
			$("#userMessage").html(err);
		}
	});
}



/**
 * used to select a specific record of a tutor and pass the value to the next page
 * @param code
 * @returns
 * @author JH
 */
function selectTutorRecord(){
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
	var t =  $('#tutorListTable').DataTable();
	var tutors = window.tutorList;
	var rowCount = 0;
	var tutorImagePath = window.tutorProfileImagePath;
	var fileSeparator = "/";
	var extension = ".jpg";
	t.clear().draw();
	var imageFile = null;
	var defaultImage  = "default";

	if (tutors !== undefined & tutors !== null) {
		$
				.each(
						tutors,
						function(index, value) {

							 rowCount++;
							var value1 = null;
							var hiddenCode = '<input type="hidden" name="tutorCode" id="tutorCode" value="'+ value[0] +'">';
							
							imageFile = fileSeparator + tutorImagePath + fileSeparator + value[0] + fileSeparator + value[0]+extension;
							onErroImage = fileSeparator + tutorImagePath + fileSeparator + defaultImage + extension;
							value1 = '  <img src="' + imageFile + '" alt="" onerror="this.src = \'' + onErroImage + '\'">';
							value2 = '<div> <div style="color:blue;"> ' +value[1]+ ' ' +  value[2]+ ' ' + value[3] +'</div>'
							+ '<br/> <div> <label>Mobile : </label> ' + value[5]+ value[6]+ ' '+  value[7]+'</div>'
							+ '<div> <label>Office : </label> ' + value[5]+ value[8]+ ' '+  value[9]+'</div>'
							+ '<div> <label>Email : </label> ' + value[4] +'</div>'
							+ '<div> <label>Town : </label> ' + value[19]+ '</div><div>';							
	
t.row.add(
									[
									 
value[0],
											value1,
											value2,
											value[4],
											value[5]

									]).draw(false);

			
		});
	}
	
}