<!-- 20161121 PN INIT fileUpload.jsp class for test bootstrap image upload. -->

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
	<div class="row">
		<div class="kv-main col-sm-4">
			<hr>
			<input id="input-repl-1" type="file" class="file-loading"
				accept="image/*"> <br>
		</div>
	</div>
</body>
<script>
	$("#input-repl-1").fileinput({
						uploadUrl : "/PublicController",
						CCO :"ABC",
						autoReplace : true,
						maxFileSize : 2800,
						overwriteInitial : true,
						showUploadedThumbs : false,
						maxFileCount : 1,
						initialPreview : [
								"<img style='height:160px' src='dist/css/img/icon-user-default.png'>", ],
						initialCaption : 'ProfilePicture.jpg',
						initialPreviewShowDelete : true,
						showClose : false,
						showRemove : false,
						layoutTemplates : {
							actionDelete : '',
							actionUpload : ''
						}, // disable thumbnail deletion
						allowedFileExtensions : [ "jpg", "png" ],
						uploadAsync: false,
						uploadExtraData: function (previewId, index) {
				            var info = {"tags": "somestuff"};
				            return info;
				        }
	});
</script>
</html>