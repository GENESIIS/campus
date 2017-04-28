/**
 * 20170427 JH c135-public-display-tutor-profile tutor-profile-helper.js created, added loadTutor() method
 * 20170428 JH c135-public-display-tutor-profile get tutor details from the back end, added String decoder methods to get the string tutor code, 
 * 				loadTutor() method coding to display profile details wip, display social media details, added methods to redirect back to the tutor
 * 				list page if tutor records are empty or user tries to enter the profile url manually
 */

$( document ).ready(function() {
	var code = $('#tutorCode').val();
	
	if(code !== null || code !== undefined){
		loadTutor(hashDecode(code)); //decode the encoded tutor code
	}
	
	// display tooltip
	    $('[data-toggle="tooltip"]').tooltip(); 
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
					 var landNumber = "+" + tutorRecord[8] + " " + tutorRecord[9] + " " + tutorRecord[10];
					 var mobileNumber = "+" + tutorRecord[8] + " " + tutorRecord[11] + " " + tutorRecord[12];
					 var email = "mailto:" + tutorRecord[7] + "?Subject=Contact%20Tutor%20Page%20@campus.lk";
					 					 
					 $('#tutorImage').attr("src", tutorImagePath);
					 $('#fullName').text(fullName);
					 $('#list-breadcrumb-item').text(fullName);
					 $('#address').text(address);
					 $('#facebookURL').attr({'href' : tutorRecord[16], 'title' : tutorRecord[16] });
					 $('#twitterURL').attr({'href' : tutorRecord[17], 'title' : tutorRecord[17]});
					 $('#linkedinURL').attr({'href' : tutorRecord[19], 'title' : tutorRecord[19]});
					 $('#instagramURL').attr( {'href' : tutorRecord[20], 'title' : tutorRecord[20]});
					 $('#whatsappNumber').attr('title', tutorRecord[21]);
					 $('#viberNumber').attr('title', tutorRecord[22]);
					 $('#myspaceURL').attr({'href' : tutorRecord[18], 'title' : tutorRecord[18]});
					 $('#landPhoneNumber').text(landNumber);
					 $('#mobilePhoneNubmer').text(mobileNumber);
					 $('#email').text(tutorRecord[7]);
					 $('#email').attr('href', email);
					 $('#webLink').text(tutorRecord[15]);
					 $('#webLink').attr('href', tutorRecord[15]);
				 }else{ // if no tutor details are found, redirect the list tutor page
					  var url = '/dist/partials/public/display-tutors.jsp';
					  var form = $('<form action="' + url + '" method="post">' +
							  	'<input type="hidden" name="userMessage" value="Please select a Tutor to Proceed." />' +
					  			'</form>');
					  			$('body').append(form);
					  			$(form).submit();
				 }
			}

		},
		error : function(x, status, error) {
			var err = displayErrorMessage(x, status, error);
			document.getElementById("userMessage").style.visibility = "visible";
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