<!-- 20170322 c157-add-tutor-employment-details-cw - created addTutorEmployment jsp file -->
<!-- 20170322 c157-add-tutor-employment-details-cw - modified src tags -->
<!-- 20170327 c157-add-tutor-employment-details-cw - modified the file according to UI Mockup design -->
<!-- 20170327 c157-add-tutor-employment-details-cw - modified employeeDetails name into employerDetails -->
<!-- 20170327 c157-add-tutor-employment-details-cw - add tutorCode hidden field & assign a value -->
<!-- 20170327 c157-add-tutor-employment-details-cw - fix some jsp alignment errors -->
<!-- 20170328 c157-add-tutor-employment-details-cw - modified employer details grid -->
<!-- 20170328 c157-add-tutor-employment-details-cw - fixed some jsp errors -->

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
			<h1>Add Tutor Employee</h1>
		</header>
		
		<form action="/TutorController" method="POST" align="center">
			<c:forEach var="tutorList" items="${result.collection}">			
				<c:set var="tutorcode" value="${tutorList[0]}"/>			
			</c:forEach>
			
				<input type="hidden" name="tutorCode" id="tutorCode" value="${tutorcode}"/>
			
			<table align="center">	
				<tr>
					<td>Employer</td>	
					<td><span id="employerError"></span> 
						<select name="employerDetails" id="employerDetails" onchange="clearField('employerError')">
							<option></option>
						</select><span id="employerError" style="color: red"> ${employerError} </span>
					</td>		
					<td><button type="submit" name="CCO" id="CCO" value="ADD_EMPLOYMENT_DETAILS"
						class="pure-button pure-button-primary">Add Employment Request</button></td>
				</tr>	
			</table>
		</form>
		<br />
		<br />
		<div align="center">
			<form action="/TutorController" method="POST">
				<h1>Manage Employment Details</h1>
				<span style="color: red"><c:out value="${errorMessage}" /></span>
				<div>
					<table id="employers" width="80%">
						<tr>
							<th></th>
							<th>Short Name</th>
							<th>Name</th>
							<th>Speciality</th>
							<th>Address</th>
							<th></th>
						</tr>
						<c:forEach var="companies" items="${result.collection}" varStatus="loop">
							<tr>
								<td><c:out value="${loop.index+1}"></c:out></td>
								<td><c:out value="${companies[1]}"></c:out></td>
								<td><c:out value="${companies[2]}"></c:out></td>
								<td><c:out value="${companies[4]}"></c:out></td>
								<td><c:out value="${companies[5]}"></c:out></td>
								<td>									
									<input id="orgCode" name="orgCode" value="<c:out value="${orgCode}"/>" hidden="true" />
									<input id="comCode" name="comCode" value="<c:out value="${companies[0]}"/>" hidden="true" />
									<button type="submit" name="CCO" id="CCO" value="UCO" class="pure-button pure-button-primary">Remove</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/tutor/experience.js"></script>
</body>
</html>