<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - created addTutorEmployment jsp file-->
<!-- 20170421 c159-courseprovider-accept-tutor-request-cw - testing coding to get the courseProviderCode-->
<!-- 20170424 c159-courseprovider-accept-tutor-request-cw - remove un wanted scriptlets & add cpCode field -->

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Tutor Employment details</title>
</head>
<body>
	<div class="container" align="center">
		<header align="center">
			<h1>View Tutor List</h1>
		</header>

		<div align="center">
			<form action="/TutorController" method="POST">
				<h1>View Tutor Details</h1>
				<tr>
					<td>
						<h2 id="tablemessage" style="color: red">${tablemessage}</h2>
					</td>
				</tr>
				
				<input type="hidden" name="cpCode" id="cpCode" value="${param.courseProviderCode}"/>
								
				<div>
					<table id="Tutors" width="80%">
						<tr>
							<th></th>
							<th>Name</th>
							<th>Gender</th>
							<th>Email</th>
							<th>Land Number</th>
							<th>Mobile Number</th>
							<th>Status</th>
							<th>Approve</th>
							<th></th>
						</tr>
					</table>
					<div style="text-align:right">  
						<input type="hidden" name="tutorCodeTable" id="tutorCodeTable"/>
    					<button type="submit" name="CCO" id="CCO" value="REMOVE_SELECTED_EMPLOYMENT" class="pure-button pure-button-primary" align = right>Remove</button>
    					<input type="hidden" name="maxSequence" id="maxSequence"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/courseprovider/tutorList.js"></script>
</body>
</html>