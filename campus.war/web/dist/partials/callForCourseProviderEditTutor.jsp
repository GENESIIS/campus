<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - created callForCourseProviderEditTutor jsp file-->
<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - add courseProviderCode text item-->
<!-- 20170421 c159-courseprovider-accept-tutor-request-cw - add View Tutor URL -->
<!-- 20170502 c159-courseprovider-accept-tutor-request-cw - remove text item & button add direct link into the page to work with Firefox -->
<!-- 20170504 c159-courseprovider-accept-tutor-request-cw - add a form submission into the page -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Profile</title>
</head>
<body>		
	
	<form action="/dist/partials/listTutorsForCourseprovider.jsp" method="post">
		<table align="center">
			<input type="hidden" name="courseProviderCode" id="courseProviderCode" value="44">
			<input type="submit" value="view">
		</table>
	</form>			
	
	<script type="text/javascript" src="\dist\bower-components\jquery\jquery.min.js"></script>
	
</body>
</html>