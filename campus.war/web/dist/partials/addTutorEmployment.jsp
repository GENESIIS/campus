<!-- 20170322 c157-add-tutor-employment-details-cw - created addTutorEmployment jsp file -->
<!-- 20170322 c157-add-tutor-employment-details-cw - modified src tags -->
<!-- 20170327 c157-add-tutor-employment-details-cw - modified the file according to UI Mockup design -->
<!-- 20170327 c157-add-tutor-employment-details-cw - modified employeeDetails name into employerDetails -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Tutor Employment details</title>
</head>
<body>
	<div class="container">
		<header align="center">
			<h1>Add Tutor Employee</h1>
		</header>
		
		<section>
			<nav>
				<ul>
					<form action="/TutorController" method="POST" align="center">
						<tr>
							<td>Employer</td>	
							<td><span id="employerError"></span> 
								<select name="employerDetails" id="employerDetails" onchange="clearField('employerError')">
									<option></option>
								</select><span id="employerError" style="color: red"> ${employerError} </span>
							</td>
						</tr>
						<button type="submit" name="CCO" id="CCO" value="ADD_EMPLOYMENT_DETAILS"
							class="pure-button pure-button-primary">Add Employment Request</button>
					</form>
				</ul>
			</nav>
		</section>
		<br />
		<br />
		<section>
			<div align="center">
				<h1>Manage Employment Details</h1>
				<span style="color: red"><c:out value="${errorMessage}" /></span>
				<div>
					<table id="customers" width="100%">
						<tr>
							<th></th>
							<th>Short Name</th>
							<th>Name</th>
							<th>Description</th>
							<th>Speciality</th>
							<th>Address</th>
							<th></th>
						</tr>
						<c:forEach var="companies" items="${result.collection}" varStatus="loop">
							<tr>
								<td><c:out value="${loop.index+1}" /></td>
								<td><c:out value="${companies[0]}"></c:out></td>
								<td><c:out value="${companies[1]}"></c:out></td>
								<td><c:out value="${companies[2]}"></c:out></td>
								<td><c:out value="${companies[3]}"></c:out></td>
								<td><c:out value="${companies[4]}"></c:out></td>
								<td><c:out value="${companies[7]}"></c:out></td>
								<td>
									<form method="POST" action="CompanyController">
										<input id="orgCode" name="orgCode"
											value="<c:out value="${orgCode}"/>" hidden="true" /> <input
											id="comCode" name="comCode"
											value="<c:out value="${companies[0]}"/>" hidden="true" />
										<button type="submit" name="CCO" id="CCO" value="UCO"
											class="pure-button pure-button-primary">EDIT</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
		</section>
	</div>
	<script type="text/javascript" src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/tutor/experience.js"></script>
</body>
</html>