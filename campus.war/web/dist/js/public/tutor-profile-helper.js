/**
 * 20170427 JH c135-public-display-tutor-profile tutor-profile-helper.js created, added loadTutor() method
 * 20170428 JH c135-public-display-tutor-profile get tutor details from the back end 
 */

$( document ).ready(function() {
	var code = $('#tutorCode').val();
	loadTutor(code);
});

function loadTutor(code){
	
	
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'DISPLAY_PUBLIC_TUTOR_PROFILE',
			'tutorCode' : code
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
			
				 var tutorRecord = response.tutor;
				 if (tutorRecord !== undefined && tutorRecord !== null) {
					 
					 $('#fullName').val(tutorRecord[3] + tutorRecord[4] + tutorRecord[5]);
				 }
			}

//			if (response !== undefined && response !== null) {
//				window.tutorList = response.result;
//				window.tutorProfileImagePath = response.tutorProfileImagePath;
//				window.majorList =  response.majorMap;
//				window.categoryList = response.categoryMap;
//				window.qualificationList = response.qualificationMap;
//
//				DisplayTutorTable();
//
//			}
			
			alert("sdfjkldsf");
		},
		error : function(x, status, error) {
			var err = displayErrorMessage(x, status, error);
			document.getElementById("userMessage").style.display = "block";
			$("#userMessage").html(err);
		}
	});
}