<!-- 20161218 DN c47-tutor-add-tutor-information-upload-image-dn created the upload image page  -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!--CSS Style-->
  <style>
		 .box__dragndrop,
		.box__uploading,
		.box__success,
		.box__error {
		  display: none;
		}
  
  </style>


 <!-- jQuery & Other js -->
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
	<script src="/dist/js/tutor/tutorUploadImage.js"></script>
	

</head>
<body>
<label id="displayLabel" style="color:#F39C12;" ></label>
<form action="" method="post" enctype="multipart/form-data">

  
  <input type="file" name="filename" accept="image/gif, image/jpeg, image/png">
  <div align="center">
  	<img id="profileImage" style="width:200px;hight:auto" src="#"   alt="Profile-Picture">
  	<!-- use a button to change the image -->
  	
  </div>
</form>
</html>