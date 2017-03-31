//20161121 DN c47-tutor-add-tutor-information-upload-image-dn created the tutorUploadImage.js
//20170112 DN c47-tutor-add-tutor-information-upload-image-dn changed the $(document).ready() function and it's inner logic
//20170117 DN c47-tutor-add-tutor-information-upload-image-dn postFilesData() method refactored
//20170117 DN c47-tutor-add-tutor-information-upload-image-dn refactor the displayLabelMessage()
//20170125 DN c47-tutor-add-tutor-information-upload-image-dn add method comments , refactor $(document).on('click') function
//20170130 DN c47-tutor-add-tutor-information-upload-image-dn added delaying function for displaying messages
// 		   DN c47-tutor-add-tutor-information-upload-image-dn implemented modal window for displaying messages.
var globalFlag=true;

$(document).ready(function() {
	
		displayTutorProfileImageAtPageLoad();
		// disable the upload button
		$('#upload-button').prop('disabled', true);
	
});

/**
 * displayTutorProfileImageAtPageLoad method displays the 
 * tutor profile image which has already been uploaded will be displayed
 * If there is no such a image then default image will be displayed depend on the 
 * gender 
 */

function displayTutorProfileImageAtPageLoad(){
	
	jQuery.ajax({
		
		url:"../../../TutorController",
		dataType: "json",
		data: {
			CCO:"UETPP" //UPLOAD EXISTING TUTOR PROFILE PICTURE
		},
		success:function(response){
			
			if(response['successCode']===1){
				// to remove cash add Math.random() to the end else the image does not get refreshed
				jQuery('#profileImage').attr('src',"../../../"+response['profilePicture']+'?'+Math.random());   
				
			} else{
				// if the execution success but logically generated error application vice
				setTimeout(displayLabelMessage('displayLabel','red',response['message']),3000);
				}
			
		},
		error:function(response,error,errorThrown) {
			var msg = ajaxErorMessage(response,error,errorThrown);
			setTimeout(displayLabelMessage('displayLabel','red',msg),3000);
		        
		  }
	});
	
}

/**
 * displayLabelMessage(): displays an user define message text in the a modal window
 * This message either can be an error or success message.
 * @author dushantha DN
 * @param cssColour required color theme for the message to be displayed
 * @param message the required message to be displayed
 */
function displayLabelMessage(labelid,cssColour,message){
	$('#messagePopUp').modal('show'); // display the modal window
	jQuery('#'+labelid).css({'color':cssColour,'font-weight':'bold'}).html("<h2>"+message+"</h2>");
	
	
}


/**
 * this function enables the upload button once 
 * the file input fires a change event
 */

$(document).on('change','#file-select',function(){	
	//enabling the 'submit' button once the image selection gets changed
	$('#upload-button').prop('disabled', false);
});


/**
 * @author dushantha DN
 * this function manages the transferring of multi_part form data
 * to the server side when the onclick event triggers. The uploaded (selected) image will be processed and
 * will be stored to the physical location given by the system
 **/

$(document).on('click','#upload-button',function(event){
	
	event.stopPropagation(); 
    event.preventDefault(); 
    
	//var reportUpload = $("#file-select").prop("files")[0]; // get the files from file input file
	var reportUpload = $('input[type="file"]')[0].files[0];
	var formData = new FormData();
	formData.append("file", reportUpload);
	
	$.ajax({
	    url: '../../../TutorController?CCO=USTIMG',
	    type: 'POST',
	    dataType : "JSON",
	    data:formData,
	    cache : false ,
	    contentType : false,
		processData : false,
		//headers:{'Content-Type': 'multipart/form-data; boundary='+boundary1+"'"}, // added
		complete:function(){
			globalFlag=true;
			setTimeout(
			displayTutorProfileImageAtPageLoad(),3000);
		},
	    success:function(response){
				
			if(response['successCode']===1){
				
				setTimeout(displayLabelMessage('displayLabel','green',response['message']),3000);
				
			} else{
				// if the execution success but logically generated error application vice
				setTimeout(displayLabelMessage('displayLabel','red',response['message']),3000);
				
				}
		},
		error:function(response,error,errorThrown) {
			var msg = ajaxErorMessage(response,error,errorThrown);
			setTimeout(displayLabelMessage('displayLabel','red',msg),3000);
		    globalFlag=false;  //do not load the image
		   }
	});
	
});

/**
 * ajaxErorMessage
 * @author DJ
 * @param response response that comes from the server
 * @error error  error message comes from the server when ajax call fails
 * @errorThrown actual error thrown once ajax response fails
 */

function ajaxErorMessage(response,error,errorThrown){
	  var msg = '';
    if (response.status === 0) {
        msg = 'Not connect.\n Verify Network.';
        
    } else if (response.status == 404) {
        msg = 'Requested page not found. [404]';
    } else if (response.status == 500) {
        msg = 'Internal Server Error [500].';
    } else if (error === 'parsererror') {
        msg = 'Requested JSON parse failed.';
    } else if (error === 'timeout') {
        msg = 'Time out error.';
    } else if (error === 'abort') {
        msg = 'Ajax request aborted.';
    } else {
        msg = 'Uncaught Error.\n' + response.responseText;
    }
    return msg;
}

