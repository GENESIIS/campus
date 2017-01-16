//20161121 DN c47-tutor-add-tutor-information-upload-image-dn created the tutorUploadImage.js
//20170112 DN c47-tutor-add-tutor-information-upload-image-dn changed the $(document).ready() function and it's inner logic
//20170117 DN c47-tutor-add-tutor-information-upload-image-dn postFilesData() method refactored



var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";
//var execute=true;

$(document).ready(function() {
	
		displayTutorProfileImageAtPageLoad();
	
	// disable the upload button
	$('#upload-button').prop('disabled', true);
	
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
		    
		    $('#upload-button').prop('disabled', false);  
		    setTimeout( function(){
		    	alert("Please upload the image");
		    	$('#file-from').on('submit',function(data){
					
		    		alert("NOrmaly it executes");
		    		postFilesData(data);
				    execute = false;
			});	
		    	
			}, 2000);
		
		    alert("time execceds");
   
		}
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


function uploadImage(){
	jQuery('#upload-button').html("Uploading ...").css({'color':'green','font-weight':'bold'});

}



function postFilesData(dataForm)
{
	alert("dataForm hit the Uploading imagepostFilesData and  "+dataForm);
 $.ajax({
    url: '../../../TutorController',
    type: 'POST',
    dataType: 'json',
    data: {
		CCO: "USTIMG" ,//UPLOAD SUBMITED TUTOR IMAGE
		//'formData':	dataForm
		'formData':	JSON.stringify(dataForm)
	},
   cache: false,
   processData: true,
   //contentType: 'multipart/form-data',
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
		var msg = ajaxErorMessage(response,error,errorThrown);
	    displayLabelMessage('displayLabel','red',msg);
	   }
});
}


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

