<!-- 20161218 DN c47-tutor-add-tutor-information-upload-image-dn created the upload image page  -->
<!-- 20170106 DN c47-tutor-add-tutor-information-upload-image-dn created the upload image page  -->
<!-- 20170130 DN c47-tutor-add-tutor-information-upload-image-dn imported Bootstrap & CSS Style -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Image Upload</title>

<!-- Bootstrap & CSS Style-->
	<link href="/dist/bower-components/bootstrap/bootstrap.min.css"	rel="stylesheet">
	<link href="/dist/css/style.css" rel="stylesheet">
	<link href="/dist/css/image-slides.css" rel="stylesheet">

 <!-- jQuery & Other js -->
 	<script src="/dist/js/header/ui-populate-helper.js"></script>
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
	<script src="/dist/js/tutor/tutorUploadImage.js"></script>
	<script src="/dist/bower-components/w3/w3data.js"></script>
</head>
<body>

<!--  logout-popup window message -->
<div class="modal fade" id="messagePopUp" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"> <b>System Message</b>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">Close</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-6">
							<label class="" id="displayLabel"></label> <!-- messages to be displayed -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Form image submit -->

	<form  id="file-from" >
		  <input type="file" id="file-select" name="file-select" accept="image/gif, image/jpeg, image/png"><br><Br>
		  <button type="submit" id="upload-button" >Upload</button>	
		  <input type="hidden"	value="xyz">
	</form>
		<div align="center">
		  	<img id="profileImage"  src="#"   alt="Profile-Picture" style="width:600px;hight:400px"> 
	  </div>


</body>
</html>