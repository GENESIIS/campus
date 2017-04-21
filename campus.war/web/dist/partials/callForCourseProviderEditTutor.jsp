<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - created callForCourseProviderEditTutor jsp file-->
<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - add courseProviderCode text item-->
<!-- 20170421 c159-courseprovider-accept-tutor-request-cw - add View Tutor URL -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Profile</title>
</head>
<body>
		
	<table align="center">
		<tr>
			<td>Course Provider Code <span id="courseProviderCode"></span></td>
			<td><input type="text" name="courseProviderCode"
				id="courseProviderCode" maxlength="10"/></td>
		</tr>
		<td><button type="button"><a href="/dist/partials/listTutorsForCourseprovider.jsp?courseProviderCode=44">View Tutor</a></button></td>
	</table>
	
	<script type="text/javascript" src="\dist\bower-components\jquery\jquery.min.js"></script>
	
</body>
</html>