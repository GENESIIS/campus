<!-- 20170221 PN CAM-48: INIT test JSP page to implement CP image upload function. -->
<!-- 20170223 PN CAM-48: modifingy UI elements to add front end validations. no script tag added to the JSP page.. -->
<!-- 20170226 PN CAM-48: modified UI elements to display images on change event of cp_img_type dropdown. delete button created.-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Course Provider images</title>

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/imageUpload/cp_img_upload_helper.js" type="text/javascript"></script>

</head>
<body>
<noscript><b>Sorry, your browser does not support JavaScript!</b></noscript>

	<select id="cp_img_type" name="cp_img_type" required>
		<option value="">--Select Type--</option>
	</select>
	
	<span id="cp_img_desc" name="cp_img_desc"></span>

	<input type="file" id="cp_img_upload" name="cp_img_upload" accept="image/*" onchange="validateFile(this, 'cp_img_upload', 'cp_img_upload_btn')">
	<input type="submit" id="cp_img_upload_btn" name="cp_img_upload_btn">
	<input type="submit" id="cp_img_delete_btn" name="cp_img_delete_btn">
	<br>
	<span id="cp_img_err" name="cp_img_err"></span>
	<img id="cp_img_display" name="cp_img_display" src="" alt="" width="" height="">
	
</body>
</html>