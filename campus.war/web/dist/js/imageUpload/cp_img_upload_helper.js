/**
 * 20170221 PN CAM-48: INIT file to implement the javascript helping methods to
 * cp image uploading functionality. 
 * 			   CAM-48: implemented setCPImgData(response) method to populate cp_img_type dropdown with DB values. 
 *             CAM-48: implemented a jquery for cp_img_desc dropdown onchange method. 
 * 20170222 PN CAM-48: modified ajax method call by setting values to pass into the backend.
 * 20170223 PN CAM-48: implemented checkFileSize() method, checkFileType() method and validateFile() method. modified cp_img_upload_btn click function to perform client side validations.
 *			PN CAM-48: createFileName() implemented for format new file name from the selected values. modified cp_img_upload_btn click function to display uploaded image.
 */

var dataSet = null;
var courseProviderCode = null;
// array with allowed extensions
var extensions = [ "jpeg", "jpg", "png", "gif", "GPEG", "JPG", "PNG", "GIF" ];

// A $( document ).ready() block.
$(document).ready(function() {
	displayImgDetails();

	// According to the selection of drop box, image description will be appear.
	$('#cp_img_type').on('change', function() {
		document.getElementById("cp_img_upload_btn").disabled = false;
		var sysConfCode = this.value;
		$.each(dataSet.result, function(index, val) {
			if (val[0] === sysConfCode) {
				$('#cp_img_desc').html(val[2]);
				var cp_img_type = $("#cp_img_type option:selected").text();
			}
		});
	})
	
	$(document).on('click','#cp_img_upload_btn',function(event){		
		event.stopPropagation(); 
	    event.preventDefault(); 
	    
	    var courseProviderCode = 1;// This will be get assigned from UI element later.
	    var uploadPathConf = $("#cp_img_type option:selected").text();	    
	    var uploadPathConfId = $("#cp_img_type").val();
	    
	    var name = $("#cp_img_upload").val();
		var fileExt = name.split('.');
	    
		var cpImgUpload = $('input[type="file"]')[0].files[0] ;// get the files from file input file
		var formData = new FormData();
		formData.append("file", cpImgUpload);
		formData.append("courseProviderCode", courseProviderCode);
		formData.append("uploadPathConf", uploadPathConf);
		document.getElementById("cp_img_upload_btn").disabled = true;
		if((courseProviderCode != "") && (uploadPathConfId != "") && (cpImgUpload != null) && (cpImgUpload != "") && (cpImgUpload != undefined)){
			$.ajax({
			    url: '/AdminController?CCO=UCPI',
			    type: 'POST',
			    dataType: 'json',
			    data:formData,
	            processData: false,
	            cache : false ,
	    	    contentType : false,
			    success:function(response){
			    	alert("Success");
			    	if(response.fileUploadError != ""){
			    		$('#cp_img_err').html(response.fileUploadError);
			    		$('#cp_img_err').css('color', 'red');
			    	}else if(response.fileUploadSuccess != ""){
			    		alert(response.fileUploadSuccess);
			    		$('#cp_img_upload').val('');
			    		$('#cp_img_type').find('option:first').attr('selected', 'selected');
			    		$('#cp_img_err').html(response.fileUploadSuccess);
			    		$('#cp_img_err').css('color', 'green');
			    		
			    		//Display uploaded image on img tag.
			    		var newName = createFileName(courseProviderCode,uploadPathConf)+"."+fileExt[1];
			    		$('#cp_img_display').attr("src","/education/provider/logo/"+courseProviderCode+"/"+newName+"?"+Math.random());
			    	}
				},
				error:function(response,error,errorThrown) {
					alert("Error");
				}
			});
		}else{
			if((cpImgUpload == null) || (cpImgUpload == "") || (cpImgUpload == undefined)){
				$('#cp_img_err').html("Please choose a file to upload.");
	    		$('#cp_img_err').css('color', 'red');
			}else if((uploadPathConfId == "")||(uploadPathConfId == null)){
				$('#cp_img_err').html("Please select file type.");
	    		$('#cp_img_err').css('color', 'red');
			}else if((courseProviderCode == "")||(courseProviderCode == null)){
				$('#cp_img_err').html("Invalid Course provider code.");
	    		$('#cp_img_err').css('color', 'red');
			}
		}
	});

});

/**
 * This method will be handle the data displaying on 'cp_img_type' dropdown
 * element and image box.
 * 
 * @returns
 */
function displayImgDetails() {
	$.ajax({
		url : '/AdminController',
		data : {
			CCO : 'GCPID'
		},
		dataType : "json",
		success : function(response) {
			if (response && response.result != "NO-DATA") {
				dataSet = response;
				courseProviderCode = response.courseProviderCode;
				setCPImgData(response);
			}
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

/**
 * This method will set details and populate cp_img_type select element.
 * 
 * @param response -
 *            response which comes from the backend.
 * @returns
 */
function setCPImgData(response) {
	var cp_img_type = $("#cp_img_type");
	cp_img_type.find('option').remove();
	$('<option>').val("").text("--Select Type--").appendTo(cp_img_type);
	$.each(response.result, function(index, value) {
		if (value[4] == 'cp_img') {
			var x = value[0].toString();
			var y = value[1].toString();
			$('<option>').val(x).text(y).appendTo(cp_img_type);
		}
	});
}

/**
 * This method prevent user from uploading large files or invalid type of files.
 * 
 * @param fileuploadelm -
 *            file uploading element
 * @param to -
 *            input element which should fires the element
 * @param submitbtn -
 *            upload button id
 * @returns
 */
function validateFile(fileuploadelm, to, submitbtn){
	// get the file name and split it to separate the extension
	var name = fileuploadelm.value;
	var fileExt = name.split('.');
	var fileName = name.split('\\').pop();
	
	var fileTypeErr = checkFileType(fileuploadelm, to, submitbtn);
	var fileSizeErr = checkFileSize(fileuploadelm, to, submitbtn);
	var errorMsg = "";
	if((fileTypeErr != "") || (fileSizeErr != "")){
		// delete the file name, disable Submit, set error message
		fileuploadelm.value = '';
		document.getElementById(submitbtn).disabled = true;
		errorMsg = fileTypeErr + fileSizeErr;
		$('#cp_img_err').html(fileName+errorMsg);
		$('#cp_img_err').css('color', 'red');
	}else{
		// enable submit
		document.getElementById(submitbtn).disabled = false;
		$('#cp_img_err').html("");
	}	
}

/**
 * This method prevent user from uploading invalid type of files.
 * 
 * @param fileuploadelm -
 *            file uploading element
 * @param to -
 *            input element which should fires the element
 * @param submitbtn -
 *            upload button id
 * @returns
 */
function checkFileType(fileuploadelm, to, submitbtn) {
	// get the file name and split it to separate the extension
	var name = fileuploadelm.value;
	var fileExt = name.split('.');
	var fileName = name.split('\\').pop();
	var errorMessage = "";
	// check the file extension
	var hasExt = 0;
	for (var i = 0; i < extensions.length; i++) {
		if (extensions[i] == fileExt[1]) {
			hasExt = 1;
			break;
		}
	}
	
	var errorMessage = "";
	// if re is 1, the extension is in the allowed list	
	if (hasExt == 1) {
		// Valid file type.
	} else {
		// Check if the file with 'no extension' or 'invalid extension'.	
		if (fileExt.length > 1) {
			errorMessage = " file type is invalid. Please upload a file with valid file type.";
		} else {
			errorMessage = " file has no extension. Please upload a file with valid file type.";
		}	
	}
	return errorMessage;
}

/**
 * This method prevent user from uploading large files which is invalid.
 * 
 * @param fileuploadelm -
 *            file uploading element
 * @param to -
 *            input element which should fires the element
 * @param submitbtn -
 *            upload button id
 * @returns
 */
function checkFileSize(fileuploadelm, to, submitbtn){
	// Valid Size is 2MB.
	var validFileSize = 2;
	var fileSize = fileuploadelm.files[0].size/1024/1024;

	// Check if the file exceeds the valid file size.
	var errorMessage = "";
	if(fileSize > validFileSize){
		errorMessage = " file type is invalid. Please upload a file with valid file type.";
	}	
	return errorMessage;
}

/**
 * Format new file name from the selected values.
 * @param courseProviderCode -
 *            course provider code.
 * @param uploadPathConf -
 *            uploaded image type.
 * @returns String - new file name without extension.
 */
function createFileName(courseProviderCode,uploadPathConf){
	var fileTypes = uploadPathConf.split("_");
	var newName = [courseProviderCode.toString() ,fileTypes[1].toString()];
	return newName.join("_").toLowerCase(); 
}
