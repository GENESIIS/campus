/**
 * 20170221 PN CAM-48: INIT file to implement the javascript helping methods to
 * cp image uploading functionality. 
 * 			   CAM-48: implemented setCPImgData(response) method to populate cp_img_type dropdown with DB values. 
 *             CAM-48: implemented a jquery for cp_img_desc dropdown onchange method. 
 * 20170222 PN CAM-48: modified ajax method call by setting values to pass into the backend.
 * 20170223 PN CAM-48: implemented checkFileSize() method, checkFileType() method and validateFile() method. modified cp_img_upload_btn click function to perform client side validations.
 *			PN CAM-48: createFileName() implemented for format new file name from the selected values. modified cp_img_upload_btn click function to display uploaded image.
 * 20170226 PN CAM-48: getActualFileName(valuesArr,nameToCheck) method implemented. error messages declaration moved into one place. display uploaded image on dropdown change function completed. 
 * cp_img_delete_btn button onclick function implemented. changed the code to disable cp_img_delete_btn button if there's no image to delete in the disk. 
 * 20170308 PN CAM-48: displayErrorMessage() method implemented. default image displaying jQuery code modified.
 * 20170308 PN CAM-48: modified 'cp_img_upload_btn' upload event to set selected image type back to the drop down. 
 * 					   cp_img_type on change event modified to make button enable and disable if an image exists/not exists for the selected type
 * 20170419 PN CAM-48: modified the implementation of createFileName() method, 'cp_img_type on' change event and 'cp_img_upload_btn' upload event to match the functionality with new UI.
 * 20170428 PN CAM-163: populateImageTable(details,courseProviderCode) method implemented to print all the image on the JSP page.
 */

var dataSet = null;
var courseProviderCode = null;
// array with allowed extensions
var extensions = [ "jpeg", "jpg", "png", "gif", "GPEG", "JPG", "PNG", "GIF" ];
// array with file names list.
var listOfFiles = [];
//array with file names list.
var listOfImageFileDetails = [];
//Physical path of the images.
var diskImgPath = "/education/provider/logo/";
//Error messages to display. 
var noImagetoDisplayErr = "No image found.";
var sizeExceededErr = " file size exceeded. Please upload a file with size less than %size.";
var invalidFileTypeErr = " file type is invalid. Please upload a file with valid file type.";
var invalidFileExtErr = " file has no extension. Please upload a file with valid file type.";
var fileNotSelectedErr = "Please choose a file to upload.";
var fileTypeNotSelectedErr = "Please select image file type to upload.";
var invalidCourseProviderCode = "Invalid Course provider code.";

// A $( document ).ready() block.
$(document).ready(function() {
	displayImgDetails();
	$('#img-info-lbl').hide();
	$('#img-err-lbl').hide();
	// According to the selection of drop box, image description will be appear.
	$('#cp_img_type').on('change', function() {
		$('#img-info-lbl').hide();
		$('#img-err-lbl').hide();
		$('#cp_img_err').html("");
		document.getElementById("cp_img_upload_btn").disabled = false;
		document.getElementById("cp_img_delete_btn").disabled = false;
		var sysConfCode = this.value;
		var cp_img_type = "";
		var courseProviderCode = 1; // ToBe assigned later.
		$.each(dataSet.result, function(index, val) {
			if (sysConfCode === val[0]) {
				$('#img-info-lbl').show();
				$('#cp_img_desc').html(val[2]);
				cp_img_type = $("#cp_img_type option:selected").text();
				var fileName = createFileName(courseProviderCode,cp_img_type);
				var actFileName = getActualFileName(listOfFiles,fileName);
				if(actFileName != noImagetoDisplayErr){
					//$('#cp_img_display').attr("src",diskImgPath+courseProviderCode+"/"+actFileName+"?"+Math.random());
					//$('#thumb-img_display').attr("href",diskImgPath+courseProviderCode+"/"+actFileName+"?"+Math.random());
					//$('#cp_img_name').html(actFileName);
					document.getElementById("cp_img_upload_btn").disabled = true;
				}else{
					//$('#cp_img_display').attr("src",diskImgPath+"/"+createFileName("default",cp_img_type)+".jpg?"+Math.random());
					//$('#thumb-img_display').attr("href",diskImgPath+"/"+createFileName("default",cp_img_type)+".jpg?"+Math.random());
					//$('#cp_img_name').html(createFileName("default",cp_img_type)+".jpg");
					document.getElementById("cp_img_delete_btn").disabled = true;
					$('#img-err-lbl').show();
					$('#cp_img_err').html(noImagetoDisplayErr);
					$('#cp_img_err').css('color', 'red');
				}
			}		
		});
	})
	
	// Handle image upload button click.
	$(document).on('click','#cp_img_upload_btn',function(event){		
		event.stopPropagation(); 
	    event.preventDefault(); 
	    $('#img-err-lbl').hide();
	    var cpImgUpload = $('input[type="file"]')[0].files[0] ;// get the files from file input file		
	    var courseProviderCode = 1;// This will be get assigned from UI element later.
	    var uploadPathConf = $("#cp_img_type option:selected").text();	    
	    var uploadPathConfId = $("#cp_img_type").val();
	    
	    var name = $("#cp_img_upload").val();
		var fileExt = name.split('.');
	    
		document.getElementById("cp_img_upload_btn").disabled = true;
		if((courseProviderCode != "") && (uploadPathConfId != "") && (cpImgUpload != null) && (cpImgUpload != "") && (cpImgUpload != undefined)){
			var formData = new FormData();
			formData.append("file", cpImgUpload);
			formData.append("courseProviderCode", courseProviderCode);
			formData.append("uploadPathConf", uploadPathConf);
			
			$.ajax({
			    url: '/AdminController?CCO=UCPI',
			    type: 'POST',
			    dataType: 'json',
			    data:formData,
	            processData: false,
	            cache : false ,
	    	    contentType : false,
			    success:function(response){
			    	listOfFiles = response.listOfFiles;
			    	listOfImageFileDetails = response.cpImageData;
			    	populateImageTable(listOfImageFileDetails,courseProviderCode);
			    	if(response.fileUploadError != ""){
			    		$('#img-err-lbl').show();
			    		$('#cp_img_err').html(response.fileUploadError);
			    		$('#cp_img_err').css('color', 'red');
			    	}else if(response.fileUploadSuccess != ""){
			    		alert(response.fileUploadSuccess);  		
			    		$('#cp_img_upload').val('');
			    		$("#cp_img_type").val(uploadPathConfId);
			    		$('#img-err-lbl').show();
			    		$('#cp_img_err').html(response.fileUploadSuccess);
			    		$('#cp_img_err').css('color', 'green');
			    		
			    		//Display uploaded image on img tag.
			    		var newName = createFileName(courseProviderCode,uploadPathConf)+"."+fileExt[1];
//			    		$('#cp_img_display').attr("src",diskImgPath+courseProviderCode+"/"+newName+"?"+Math.random());
//			    		$('#thumb-img_display').attr("href",diskImgPath+courseProviderCode+"/"+newName+"?"+Math.random());
//			    		$('#cp_img_name').html(newName);
			    	}
				},
				error : function(x, status, error) {
					//Modified the error handling.
					var err = displayErrorMessage(x, status, error);
					alert(err);
				}
			});
		}else{
			$('#img-err-lbl').show();
			if((cpImgUpload == null) || (cpImgUpload == "") || (cpImgUpload == undefined)){
				$('#cp_img_err').html(fileNotSelectedErr);
	    		$('#cp_img_err').css('color', 'red');
			}else if((uploadPathConfId == "")||(uploadPathConfId == null)){
				$('#cp_img_err').html(fileTypeNotSelectedErr);
	    		$('#cp_img_err').css('color', 'red');
			}else if((courseProviderCode == "")||(courseProviderCode == null)){
				$('#cp_img_err').html(invalidCourseProviderCode);
	    		$('#cp_img_err').css('color', 'red');
			}
		}
	});
	
	// Handle image delete button click.
	$(document).on('click','#cp_img_delete_btn',function(event){	
		event.stopPropagation(); 
	    event.preventDefault(); 
	    $('#img-err-lbl').hide();
	    var courseProviderCode = 1;// This will be get assigned from UI element later.
	    var uploadPathConf = $("#cp_img_type option:selected").text();	    
	    var uploadPathConfId = $("#cp_img_type").val();
	    document.getElementById("cp_img_delete_btn").disabled = true;
	    
	    if((courseProviderCode != "") && (uploadPathConfId != "")){
	    	var delete_cp_img = createFileName(courseProviderCode,uploadPathConf);
		    var formData = new FormData();    
			formData.append("delete_cp_img", delete_cp_img);
			formData.append("courseProviderCode", courseProviderCode);
			formData.append("uploadPathConf", uploadPathConf);
			
			$.ajax({
			    url: '/AdminController?CCO=DCPI',
			    type: 'POST',
			    dataType: 'json',
			    data: formData,
	            processData: false,
	            cache : false ,
	    	    contentType : false,
			    success:function(response){
			    	listOfFiles = response.listOfFiles;
			    	listOfImageFileDetails = response.cpImageData;
			    	populateImageTable(listOfImageFileDetails,courseProviderCode);
			    	if(response.fileDeleteError != ""){
			    		$('#img-err-lbl').show();
			    		$('#cp_img_err').html(response.fileDeleteError);
			    		$('#cp_img_err').css('color', 'red');
			    	}else if(response.fileDeleteSuccess != ""){
			    		$('#img-err-lbl').show();
			    		$("#cp_img_type").val(uploadPathConfId);
			    		$('#cp_img_err').html(response.fileDeleteSuccess);
			    		$('#cp_img_err').css('color', 'green');
			    		
			    		//Display uploaded image on img tag.  		
//			    		$('#cp_img_display').attr("src",diskImgPath+"/"+createFileName("default",uploadPathConf)+".jpg?"+Math.random());
//			    		$('#thumb-img_display').attr("href",diskImgPath+"/"+createFileName("default",uploadPathConf)+".jpg?"+Math.random());
//			    		$('#cp_img_name').html(createFileName("default",uploadPathConf)+".jpg");
			    	}
				},
				error : function(x, status, error) {
					//Modified the error handling.
					var err = displayErrorMessage(x, status, error);
					alert(err);
				}
			});
		}else{
			$('#img-err-lbl').show();
			if((uploadPathConfId == "")||(uploadPathConfId == null)){
				$('#cp_img_err').html(fileTypeNotSelectedErr);
	    		$('#cp_img_err').css('color', 'red');
			}else if((courseProviderCode == "")||(courseProviderCode == null)){
				$('#cp_img_err').html(invalidCourseProviderCode);
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
				listOfFiles = response.listOfFiles;
				listOfImageFileDetails = response.cpImageData;
				populateImageTable(listOfImageFileDetails,courseProviderCode);
				setCPImgData(response);
			}
		},
		error : function(x, status, error) {
			//Modified the error handling.
			var err = displayErrorMessage(x, status, error);
			alert(err);
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
	$('#img-err-lbl').hide();
	if((fileuploadelm != null) && (fileuploadelm != "") && (fileuploadelm != undefined)){
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
			$('#img-err-lbl').show();
			$('#cp_img_err').html(fileName+errorMsg);
			$('#cp_img_err').css('color', 'red');
		}else{
			// enable submit
			document.getElementById(submitbtn).disabled = false;
			$('#img-err-lbl').show();
			$('#cp_img_err').html("");
		}	
	}else{
		$('#img-err-lbl').show();
		$('#cp_img_err').html("Selected file is not available to upload.");
	}
	$('#img-err-lbl').hide();
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
			errorMessage = invalidFileTypeErr;
		} else {
			errorMessage = invalidFileExtErr;
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
		errorMessage = sizeExceededErr.replace("%size",validFileSize+"MB");
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
	var newName = [];
	
	if(uploadPathConf.indexOf('CP') >= 0){
		newName = [courseProviderCode.toString() ,fileTypes[1].toString()];
	}else{
		newName = [courseProviderCode.toString() ,fileTypes[0].toString()];
	}
	return newName.join("_").toLowerCase(); 
}

/**
 * This method only to use in display image according to the dropdown selection.
 * It check's for file name existence in disk.
 * 
 * @param valuesrr
 * @param nameToCheck
 * @returns
 */
function getActualFileName(valuesArr,nameToCheck) {
	if(valuesArr.length != 0 && valuesArr != null){
		for (var int = 0; int < valuesArr.length; int++) {
			var nameOnly = valuesArr[int].toString().split(".");
			if(nameOnly[0]===nameToCheck){
				return valuesArr[int].toString();
			}
		}
	}else{
		return noImagetoDisplayErr;
	}
	return noImagetoDisplayErr;
}

/**
 * This method will format the error message and will display it to the user.
 * @param x - error details as a JSON object
 * @param status - error status
 * @param error - actual error. 
 */
function displayErrorMessage(x, status, error) {
	var errorMessage = 'Unexpected error encountered.';
	if(status.toString() == 'error' && error.toString() == ''){
		errorMessage = errorMessage + ":Selected file is not available to upload.";
	}else {
		errorMessage = errorMessage + " Error: " + error +" Status: "+ status;
	}
	return errorMessage;
}

/**
 * Print the CP image details on the table.
 * @param details - details list of given folder containing images.
 * @param courseProviderCode -
 *            course provider code.
 * @param uploadPathConf -
 *            uploaded image type.
 */
function populateImageTable(details,courseProviderCode){
	alert(details);
	var html = '';

	$.each(details, function(index, value) {
//		$('#cpImageData tbody').append('<tr>'+
//		'<td><a class="thumb-img" href="http://www.gettyimages.com/gi-resources/images/Embed/new/embed2.jpg" title=""><img alt="" src="http://www.gettyimages.com/gi-resources/images/Embed/new/embed2.jpg" /></a></td>'+
//		'<td><span>' + value[1]+ '</span></td>'+
//		'<td><span>' + value[3]+ '</span></td>'+
//		'<td><button type="button" class="btn-default btn-sm"><i class="remove-item action-item fa fa-trash-o" aria-hidden="true"></i></button></td>'+
//		'</tr>');
		
		
		$('#cpImageData tbody').append('<tr>'+
		'<td>'+
			'<a class="thumb-img" id="thumb-img_display" name="thumb-img_display" href="http://www.gettyimages.com/gi-resources/images/Embed/new/embed2.jpg" title="">'+
				'<img id="cp_img_display" name="cp_img_display" alt="" src ="http://www.gettyimages.com/gi-resources/images/Embed/new/embed2.jpg"/>'+
			'</a>'+
		'</td>'+
		'<td><span id="cp_img_name" name="cp_img_name"></span></td>'+
		'<th>Added</th>'+
		'<td class="">'+
			'<button type="button" class="btn-default btn-sm" id="cp_img_delete_btn" name="cp_img_delete_btn">'+
				'<i class="remove-item action-item fa fa-trash-o" aria-hidden="true"></i>'+
			'</button>'+
		'</td>'+
		'</tr>');		
		
		
	});
	
	            
	//$('#cpImageData tbody').append(html);
}
