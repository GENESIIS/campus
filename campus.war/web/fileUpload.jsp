<!-- 20161121 PN c27-upload-user-image: INIT fileUpload.jsp class for test bootstrap image upload. -->
<!-- 20161122 PN c27-upload-user-image: modified Ajax call to pass values to the serlvlet and catch response coming from the servlet. -->

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
<body>
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
<!-- 	<div class="row"> -->
<!-- 		<div class="kv-main col-sm-4"> -->
<!-- 			<input id="userImg" type="file" class="file-loading" -->
<!-- 				accept="image/*"> <br> -->
<!-- 		</div> -->
		
<!-- 	</div> -->
<!-- 	<div id="kv-avatar-errors-1" class="center-block" style="width:800px;display:none"></div>		 -->

<!-- the avatar markup -->
<div id="kv-avatar-errors-1" class="center-block" style="width:800px;display:none"></div>
<form class="text-center" action="/PublicController" method="post" enctype="multipart/form-data">
    <div class="kv-avatar center-block" style="width:200px">
        <input id="userImg" name="userImg" type="file" class="file-loading">
    </div>
    <!-- include other inputs if needed and include a form submit (save) button -->
</form>

</body>
<script type="text/javascript">
	var userid = "XXX";
	var username = "YYY";
	$("#userImg").fileinput({
						uploadUrl : "/PublicController?CCO=&quot;text&quot;",
						maxFileSize: 1500,
						autoReplace : true,
						overwriteInitial : true,
						showUploadedThumbs : false,
						maxFileCount : 1,
						defaultPreviewContent : [
								"<img style='height:160px' src='education/student/1/1.jpg'>", ],
						defaultCaption : 'ProfilePicture.jpg',
						defaultPreviewShowDelete : true,
						showClose : false,
						showRemove : false,
						layoutTemplates : {
							actionDelete : '',
							actionUpload : ''
						}, // disable thumbnail deletion
						allowedFileExtensions : ["jpg", "png", "gif"],
						uploadAsync: false,
						uploadExtraData: function() {
					           return {
					               userid: $("#userid").val(),
					               username: $("#username").val()
					           };
					    }
	});
	 // CATCH RESPONSE
	$('#userImg').on('filebatchuploaderror', function(event, data, previewId, index) {
	var form = data.form, files = data.files, extra = data.extra, 
	    response = data.response, reader = data.reader;
		alert("HI");
	});


	$('#userImg').on('filebatchuploadsuccess', function(event, data, previewId, index) {
	   alert("HI Pabodha");
	});
	
	
	
	function displayDetails() {
		alert("Start");
		$.ajax({
			url : '/PublicController',
			data : {
				CCO : 'UUP'
			},
			dataType : "json",
			success : function(response) {
				alert(response);
			},
			error : function(response) {
				alert("Error: "+response);
			}
		});
		alert("End");
	}
	
    function onErrorImg() {
    	var element = document.getElementById("userImgErr");
    	if (element.firstChild) {
    	    // It has at least one
    	}else{
    		alert("Error Now");
    	}
	}
	
</script>
</html>