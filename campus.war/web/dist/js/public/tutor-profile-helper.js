/**
 * 20170427 JH c135-public-display-tutor-profile tutor-profile-helper.js created, added loadTutor() method
 * 20170428 JH c135-public-display-tutor-profile get tutor details from the back end, added String decoder methods to get the string tutor code, 
 * 				loadTutor() method coding to display profile details wip, display social media details, added methods to redirect back to the tutor
 * 				list page if tutor records are empty or user tries to enter the profile url manually
 * 20170502 JH c135-public-display-tutor-profile fixed default image error, handled empty values in social media information and check optional 
 * 				address lines before concatenating them into a final string
 * 20170505 JH c135-public-display-tutor-profile check if the tutor details are empty before displaying details
 * 20170508 JH c162-public-display-tutor-full-profile display tutor qualifications wip
 */

$( document ).ready(function() {
	var code = $('#tutorCode').val();
	
	if(code !== null || code !== undefined){
		loadTutor(hashDecode(code)); //decode the encoded tutor code
	}
	
	// display tooltip
	    $('[data-toggle="tooltip"]').tooltip(); 
});

/**
 * Load tutor profile details. This will pass the tutor code to the back end and 
 * get the profile details. 
 * @param code
 */
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
				 if (tutorRecord !== undefined && tutorRecord !== null && tutorRecord.length > 0) {
					 
					 var imagePath = response.tutorProfileImagePath;
					 var fileSeperator = "/";
					 var imageExtension = ".jpg";
					 var defaultImage = "default";
					 var tutorImagePath = fileSeperator + imagePath + fileSeperator + tutorRecord[0] + fileSeperator + tutorRecord[0] + imageExtension;
					 var defaultImagePath = fileSeperator + imagePath + fileSeperator + defaultImage + imageExtension;
					 var fullName = tutorRecord[3] + " " +  tutorRecord[4] + " " + tutorRecord[5];
					 var address = tutorRecord[24]
					 if(tutorRecord[25] !== ""){
						 address += ", " + tutorRecord[25] ;
					 }
					 if(tutorRecord[26] != "" ){
						 address += ", " + tutorRecord[26] ;
					 }
					 
					 var landNumber = "+" + tutorRecord[8] + " " + tutorRecord[9] + " " + tutorRecord[10];
					 var mobileNumber = "+" + tutorRecord[8] + " " + tutorRecord[11] + " " + tutorRecord[12];
					 var email = "mailto:" + tutorRecord[7] + "?Subject=Contact%20Tutor%20Page%20@campus.lk";
					 
					 $('#tutorImage').attr("src", tutorImagePath);
					 $('#tutorImage').attr("onerror", 'this.src =\'' + defaultImagePath +'\' ');
					 $('#fullName').text(fullName);
					 $('#list-breadcrumb-item').text(fullName);
					 $('#address').text(address);
					 if(tutorRecord[16] !== null || tutorRecord[16] !== ""){
						 $('#facebookURL').attr({'href' : tutorRecord[16], 'title' : tutorRecord[16] });
					 }
					 if(tutorRecord[16] === null || tutorRecord[16] === ""){
						 $('#facebookURL').attr({'href' : 'javascript:', 'title' : 'Details not shared' });
					 }
					 
					 if(tutorRecord[17] !== null || tutorRecord[17] !== ""){
					 $('#twitterURL').attr({'href' : tutorRecord[17], 'title' : tutorRecord[17]});
					 }
					 if(tutorRecord[17] === null || tutorRecord[17] === ""){
						 $('#twitterURL').attr({'href' : 'javascript:', 'title' : 'Details not shared' });
					 }
					 
					 if(tutorRecord[19] !== null || tutorRecord[19] !== ""){
					 $('#linkedinURL').attr({'href' : tutorRecord[19], 'title' : tutorRecord[19]});
					 }
					 if(tutorRecord[19] === null || tutorRecord[19] === ""){
						 $('#linkedinURL').attr({'href' : 'javascript:', 'title' : 'Details not shared' });
					 }
					 
					 if(tutorRecord[20] !== null || tutorRecord[20] !== ""){
					 $('#instagramURL').attr( {'href' : tutorRecord[20], 'title' : tutorRecord[20]});
					 }
					 if(tutorRecord[20] === null || tutorRecord[20] === ""){
						 $('#instagramURL').attr({'href' : 'javascript:', 'title' : 'Details not shared' });
					 }
					 
					 if(tutorRecord[21] !== null || tutorRecord[21] !== ""){
					 $('#whatsappNumber').attr('title', tutorRecord[21]);
					 }
					 if(tutorRecord[21] === null || tutorRecord[21] === ""){
						 $('#whatsappNumber').attr({'href' : 'javascript:', 'title' : 'Details not shared' });
					 }
					 
					 if(tutorRecord[22] !== null || tutorRecord[22] !== ""){
					 $('#viberNumber').attr('title', tutorRecord[22]);
					 }
					 if(tutorRecord[22] === null || tutorRecord[22] === ""){
						 $('#viberNumber').attr({'href' : 'javascript:', 'title' : 'Details not shared' });
					 }
					 
					 if(tutorRecord[18] !== null || tutorRecord[18] !== ""){
					 $('#myspaceURL').attr({'href' : tutorRecord[18], 'title' : tutorRecord[18]});
					 }
					 if(tutorRecord[18] === null || tutorRecord[18] === ""){
						 $('#myspaceURL').attr({'href' : 'javascript:', 'title' : 'Details not shared' });
					 }
					 $('#landPhoneNumber').text(landNumber);
					 $('#mobilePhoneNubmer').text(mobileNumber);
					 $('#email').text(tutorRecord[7]);
					 $('#email').attr('href', email);
					 $('#webLink').text(tutorRecord[15]);
					 $('#webLink').attr('href', tutorRecord[15]);
					 $('#tutorDescription').text(tutorRecord[13]);
					 
					 
					 /* display tutor qualfications */
					 var tutorQualification = response.qualifications;
					 alert(tutorQualification);
					 if (tutorQualification !== undefined && tutorQualification !== null && tutorQualification.length > 0) {
						 alert(tutorQualification);
						 
						 var qualificationDiv = $('#qualificationDiv');
						 $.each( tutorQualification, function(index, value){
							 
							 qualificationDiv += '<h5>'+ value[1]+'</h5>';
							 qualificationDiv += '<span class="fa fa-bookmark" aria-hidden="true"></span>';
							 qualificationDiv +='<span class="edu-level">' +  + value[3]+ +'</span>';
							 qualificationDiv +='	<p>' + value[2]+'</p>';
						 });
						 $('#qualificationDiv').html(qualificationDiv);
					 }
					 
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