//20161121 DN c47-tutor-add-tutor-information-upload-image-dn created the tutorUploadImage.js
//20170112 DN c47-tutor-add-tutor-information-upload-image-dn changed the $(document).ready() function and it's inner logic



var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

$(document).ready(function() {
	displayTutorProfileImageAtPageLoad();
	
	$('#file-select').on('change', function(event){
		//
		if(event!=undefined){
			
			event.stopPropagation(); 
		    event.preventDefault(); 
		    var files = event.target.files; 
		    var data = new FormData();
		    $.each(files, function(key, value)
		    {
		        data.append(key, value);
		    });
		}
		
		
	});
	
	
	// assign data to globall variable and call from the bellow function else data becomes undefined at the time it's been called
	$('#file-from').on('submit',function(data){
		 postFilesData(data);
});
	
});


/**
* 
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
				
				displayLabelMessage('displayLabel','green',response['message']);
				jQuery('#profileImage').attr('src',"../../../"+response['profilePicture']);
				
			} else{
				// if the execution success but logically generated error application vice
				
				displayLabelMessage('displayLabel','red',response['message']);
				}
			
		},
		error:function(response,error,errorThrown) {
			
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


function uploadImage(){
	jQuery('#upload-button').html("Uploading ...").css({'color':'green','font-weight':'bold'});

}



function postFilesData(dataForm)
{
	alert("data"+dataForm);
 $.ajax({
    url: '../../../TutorController',
    type: 'POST',
    data: {
		'CCO': 'USTIMG', //UPLOAD SUBMITED TUTOR IMAGE
		'formData': dataForm
	},
//    cache: false,
    dataType: 'json',
    processData: true,
//    processData: false,
    contentType: 'multipart/form-data',
//    contentType: false, 
    success:function(response){
			
		if(response['successCode']===1){
			alert("Inside success call");
			displayLabelMessage('displayLabel','green',response['message']);
//			jQuery('#profileImage').attr('src',"../../../"+response['profilePicture']);
			displayTutorProfileImageAtPageLoad();
			
			
		} else{
			// if the execution success but logically generated error application vice
			alert("success but successCode<>1");
			displayLabelMessage('displayLabel','red',response['message']);
			}
		
	},
	error:function(response,error,errorThrown) {
		
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
	        displayLabelMessage('displayLabel','red',msg);
	        }
	
});
}

