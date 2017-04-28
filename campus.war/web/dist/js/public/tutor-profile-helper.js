/**
 * 20170427 JH c135-public-display-tutor-profile tutor-profile-helper.js created, added loadTutor() method
 * 20170428 JH c135-public-display-tutor-profile get tutor details from the back end 
 */

$( document ).ready(function() {
	var code = $('#tutorCode').val();
	loadTutor(hashDecode(code)); //decode the encoded tutor code
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
					 
					 var imagePath = response.tutorProfileImagePath;
					 var fileSeperator = "/";
					 var imageExtension = ".jpg";
					 var tutorImagePath = fileSeperator + imagePath + fileSeperator + tutorRecord[0] + fileSeperator + tutorRecord[0] + imageExtension;
					 var fullName = tutorRecord[3] + " " +  tutorRecord[4] + " " + tutorRecord[5];
					 var address = tutorRecord[24] + ", " + tutorRecord[25] + ", " + tutorRecord[26];
					 					 
					 $('#tutorImage').attr("src", tutorImagePath);
					 $('#fullName').text(fullName);
					 $('#tutorRecord').val(address);
				 }
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
 * String Hash code decode to Sting
 * Added from CAM-22
 * @param data
 * @returns String code
 */
//
function hashDecode(data) {
	// Decode the String
	var decodedString = atob(data);

	return decodedString;
}