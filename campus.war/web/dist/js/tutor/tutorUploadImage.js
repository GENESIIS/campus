//20161121 DN c47-tutor-add-tutor-information-upload-image-dn created the tutorUploadImage.js
//20170112 DN c47-tutor-add-tutor-information-upload-image-dn changed the $(document).ready() function and it's inner logic
//20170117 DN c47-tutor-add-tutor-information-upload-image-dn postFilesData() method refactored
//20170117 DN c47-tutor-add-tutor-information-upload-image-dn refactor the displayLabelMessage()

var globalFlag=true;

$(document).ready(function() {
	
	if(globalFlag){
		
		displayTutorProfileImageAtPageLoad();
		// disable the upload button
		$('#upload-button').prop('disabled', true);
		globalFlag=false;
	}
	
});



function displayTutorProfileImageAtPageLoad(){
	
	jQuery.ajax({
		
		url:"../../../TutorController",
		dataType: "json",
		data: {
			CCO:"UETPP" //UPLOAD EXISTING TUTOR PROFILE PICTURE
		},
		success:function(response){
			
			if(response['successCode']===1){
				
				displayLabelMessage('displayLabel','green',response['message']);
				jQuery('#profileImage').attr('src',"../../../"+response['profilePicture']);
				
			} else{
				// if the execution success but logically generated error application vice
				displayLabelMessage('displayLabel','red',response['message']);
				}
			
		},
		error:function(response,error,errorThrown) {
			var msg = ajaxErorMessage(response,error,errorThrown);
		    displayLabelMessage('displayLabel','red',msg);
		        
		  }
	});
	
}

/**
 * displayLabelMessage(): displays an user define text in the label
 * designated.
 * @author dushantha DN
 * @param cssColour required color theme for the message to be displayed
 * @param message the required message to be displayed
 */
function displayLabelMessage(labelid,cssColour,message){
	jQuery('#'+labelid).css({'color':cssColour,'font-weight':'bold'}).html("<h2>"+message+"</h2>");
}



$(document).on('change','#file-select',function(){	
	//enabling the 'submit' button once the image selection gets changed
	$('#upload-button').prop('disabled', false);
	
});




$(document).on('click','#upload-button',function(){

	//var reportUpload = $("#file-select").prop("files")[0]; // get the files from file input file
	var reportUpload = $('input[type="file"]')[0].files[0];
	var unknown = $("#file-select").prop("files");
	var formData = new FormData();
	formData.append("file", reportUpload);
	var boundary1= Math.random().toString().substr(2);
	$.ajax({
	    url: '../../../TutorController?CCO=USTIMG',
	    type: 'POST',
	    dataType : "JSON",
	    data:formData,
	    cache : false ,
	    contentType : false, //---originally
		processData : false,
		//headers:{'Content-Type': 'multipart/form-data; boundary='+boundary1+"'"}, // added
	    success:function(response){
				
			if(response['successCode']===1){
				alert("Inside success call");
				displayLabelMessage('displayLabel','green',response['message']);
				jQuery('#profileImage').attr('src',"../../../"+response['profilePicture']);
				globalFlag=true;
				displayTutorProfileImageAtPageLoad(); // since the onload function has been this is been written 
				
			} else{
				// if the execution success but logically generated error application vice
				alert("success but successCode<>1");
				displayLabelMessage('displayLabel','red',response['message']);
				}
			
		},
		error:function(response,error,errorThrown) {
			var msg = ajaxErorMessage(response,error,errorThrown);
		    displayLabelMessage('displayLabel','red',msg);
		    globalFlag=false;  //do not load the image
		   }
	});
	
});

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

