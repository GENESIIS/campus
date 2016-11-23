<!-- 20161121 PN c27-upload-user-image: INIT fileUpload.jsp class for test bootstrap image upload. -->
<!-- 20161122 PN c27-upload-user-image: modified Ajax call to pass values to the serlvlet and catch response coming from the servlet. -->
<!-- 20161124 PN c27-upload-user-image: modified Ajax call to display validation messages comes from the servlet. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>Krajee JQuery Plugins - &copy; Kartik</title>
<link href="dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="dist/css/imageUpload/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<script	src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/js/imageUpload/fileinput.js" type="text/javascript"></script>
<script	src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js" type="text/javascript"></script>
</head>
<!-- some CSS styling changes and overrides -->
<style>
.kv-avatar .file-preview-frame,.kv-avatar .file-preview-frame:hover {
    margin: 0;
    padding: 0;
    border: none;
    box-shadow: none;
    text-align: center;
}
.kv-avatar .file-input {
    display: table-cell;
    max-width: 220px;
}
</style>
 
<!-- the avatar markup -->
<div id="kv-avatar-errors-1" class="center-block" style="width:400px;display:none"></div>
<div id="kv-error-1" class="center-block" style="width:400px;display:none"></div>
<div id="kv-success-1" class="alert alert-success fade in" style="width:400px;display:none"></div>

<form class="text-center" action="/PublicController" method="post" enctype="multipart/form-data">
    <div class="kv-avatar center-block" style="width:200px">
        <input id="avatar-1" name="avatar-1" type="file" class="file-loading" accept="image/*">
    </div>
    <!-- include other inputs if needed and include a form submit (save) button -->
</form>
<!-- your server code `avatar_upload.php` will receive `$_FILES['avatar']` on form submission -->
 
<!-- the fileinput plugin initialization -->
<script  type="text/javascript">
var profileImage = "education/student/Male.jpg";

$(document).ready(function(){
    if(profileImage==""){
    	$.ajax({
			url: "../StudentController",
			data : {
				CCO : 'GUP'
			},
			dataType : "json",
			success: function(response){
				profileImage=response.proPicName;
        	}});
    }
});



$("#avatar-1").fileinput({
	uploadUrl : "/StudentController?CCO=UUP",
    overwriteInitial: true,
    autoReplace : true,
    maxFileSize: 1500,
    showUploadedThumbs : false,
    showClose: false,
    showCaption: false,
    browseLabel: '',
    removeLabel: '',
    browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
    removeTitle: 'Cancel or reset changes',
    elErrorContainer: '#kv-avatar-errors-1',
    msgErrorClass: 'alert alert-block alert-danger',
    defaultPreviewContent: '<img src="'+profileImage+'" alt="ProfilePicture.jpg" style="width:160px">',
    layoutTemplates: {main2: '{preview}{browse}'},
    allowedFileExtensions: ["jpg", "png", "gif"]
}).on('filebatchpreupload', function(event, data, id, index) {
	alert(data.response);
    $('#kv-success-1').html('<h4>'+data.proPicName+'</h4><ul></ul>').hide();
}).on('fileuploaded', function(event, data, id, index) {
	var formdata = data.form, files = data.files, 
    extradata = data.extra, responsedata = data.response;

	var propicDetails = responsedata.result;
	
	var res = propicDetails.toString();
	var data = res.split(",");
	
	var imgname = data[0].toString();
	var success = data[1].toString();
	var error = data[2].toString();
	
	if(data[1] != ""){
		$('#kv-success-1').append('<h4>'+data[1]+'</h4><ul></ul>');
   		$('#kv-success-1').fadeIn('slow');
	}
	if(data[2] != ""){
    	$('#kv-error-1').append('<h4>'+data[2]+'</h4><ul></ul>');
       	$('#kv-error-1').fadeIn('slow');
	}
	defaultPreviewContent: '<img src="'+data[0]+'" alt="ProfilePicture.jpg" style="width:160px">'
});

</script>
</html>