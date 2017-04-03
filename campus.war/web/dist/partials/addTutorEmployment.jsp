<!-- 20170322 c157-add-tutor-employment-details-cw - created addTutorEmployment jsp file -->
<!-- 20170322 c157-add-tutor-employment-details-cw - modified src tags -->
<!-- 20170327 c157-add-tutor-employment-details-cw - modified the file according to UI Mockup design -->
<!-- 20170327 c157-add-tutor-employment-details-cw - modified employeeDetails name into employerDetails -->
<!-- 20170327 c157-add-tutor-employment-details-cw - add tutorCode hidden field & assign a value -->
<!-- 20170327 c157-add-tutor-employment-details-cw - fix some jsp alignment errors -->
<!-- 20170328 c157-add-tutor-employment-details-cw - modified employer details grid -->
<!-- 20170328 c157-add-tutor-employment-details-cw - fixed some jsp errors -->
<!-- 20170328 c157-add-tutor-employment-details-cw - add tutorCode, employerCode hidden items in the table & add validation to the table-->
<!-- 20170329 c157-add-tutor-employment-details-cw - add remove button -->
<!-- 20170330 c157-add-tutor-employment-details-cw - add maxSequence hidden item -->
<!-- 20170330 c157-add-tutor-employment-details-cw - modified table view sequence -->
<!-- 20170331 c157-add-tutor-employment-details-cw - modified tutor code into tutorcodelist & add a checkbox item into table -->
<!-- 20170331 c157-add-tutor-employment-details-cw - removed some table details & add messages into the page -->
<!-- 20170403 c157-add-tutor-employment-details-cw - edit tutorcodelist filling sequence -->

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
				<c:set var="tutorcodelist" value="${tutorList[0]}"/>			
			</c:forEach>
			
				<input type="hidden" name="tutorcodelist" id="tutorcodelist" value="${tutorcodelist}"/>
			
			<table align="center">	
				<tr>
					<td>
						<h2 id="message" style="color: red">${message}</h2>
					</td>
				</tr>
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
				<tr>
					<td>
						<h2 id="message" style="color: red">${message}</h2>
					</td>
				</tr>
				<div>
					<table id="employers" width="80%">
						<tr>
							<th></th>
							<th>Short Name</th>
							<th>Name</th>
							<th>Speciality</th>
							<th>Address</th>
							<th>Select to Remove</th>
							<th></th>
						</tr>
					</table>
					<div style="text-align:right">  
    					<button type="submit" name="CCO" id="CCO" value="REMOVE_SELECTED_EMPLOYMENT" class="pure-button pure-button-primary" align = right>Remove</button>
    					<input type="hidden" name="maxSequence" id="maxSequence"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/tutor/experience.js"></script>
</body>
</html>