<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - created callForCourseProviderEditTutor jsp file-->
<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - add courseProviderCode text item-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Profile</title>
</head>
<body>
	<form action="/TutorController" method="post">
		<table align="center">
			<tr>
				<td>Course Provider Code <span id="tutorCode"></span></td>
				<td><input type="text" name="courseProviderCode"
					id="courseProviderCode" maxlength="10"/></td>
			</tr>

			<tr>
				<td>
					<button type="submit" name="CCO" id="CCO" value="VIEW_TUTORS_FOR_CP"
						class="btn btn-info navbar-btn">View Tutor</button>
				</td>
			</tr>
		</table>

	</form>
	
	
	<script type="text/javascript"
		src="\dist\bower-components\jquery\jquery.min.js"></script>
	
</body>
</html>