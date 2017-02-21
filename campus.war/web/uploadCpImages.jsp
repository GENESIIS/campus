<!-- 20170221 PN CAM-48: INIT test JSP page to implement CP image upload function. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Course Provider images</title>
</head>
<body>
	<select id="cp_img_type" name="cp_img_type">
		<option value="">--Select Type--</option>
	</select>

	<input type="file" id="cp_img_upload" name="cp_img_upload"
		accept="image/*">
	<input type="submit">

	<img id="cp_img_display" name="cp_img_display" src="" alt="" width=""
		height="">

</body>
</html>