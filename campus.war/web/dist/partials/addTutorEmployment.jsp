<!-- 20170322 c157-add-tutor-employment-details-cw - created addTutorEmployment jsp file -->
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

		<article>
			<header>
				<h1>Add Tutor Employee</h1>
			</header>
			<section>
				<nav>
					<ul>
								<tr>
					<td>Employee</td>	
					<td><span id="employeeError"></span> <select
						name="employeeDetails" id="employeeDetails" onchange="clearField('employeeError')">
							<option></option>
					</select><span id="employeeError" style="color: red"> ${employeeError} </span></td>
				</tr>
						<li><form action="campusController" method="POST">
								<button type="submit" name="CCO" id="CCO" value="SAVE_EMPLOYEE"
									class="pure-button pure-button-primary">Save</button>
							</form></li>
						<br />
						<li><form action="campusController" method="POST">
								<button type="submit" name="CCO" id="CCO" value="ADD_EMPLOYEE"
									class="pure-button pure-button-primary">Add employee Request</button>
							</form></li>
					
						<br />
					</ul>
				</nav>
			</section>
			<section>
				<div align="center">
					<h1>Manage Company Details</h1>
					<span style="color: red"><c:out value="${errorMessage}" /></span>
					<form class="pure-form pure-form-aligned" method="POST"
						action="CompanyController">

						<table border="0">
							<tr>
								<td>Organization Code:</td>
								<td><input id="orgCode" name="orgCode" readonly type="text"
									value="<c:out	value="${orgCode}" />"></td>
							</tr>
							<tr>
								<td>Company Name:</td>
								<td><input required id="companyName" name="companyName" type="text"
									value="<c:out	value="${comName}" />"> <input
									id="comCode" name="comCode" hidden="true" type="text"
									value="<c:out	value="${comCode}" />"></td>
							</tr>
							<tr>
								<td>Company Address:</td>
								<td><input required id="companyAddress" name="companyAddress"
									type="text" value="<c:out	value="${comAddress}" />"></td>
							</tr>
							<tr>
								<td>Contact Person:</td>
								<td><input required id="companyContactPerson"
									name="companyContactPerson" type="text"
									value="<c:out	value="${comConPerson}" />"></td>
							</tr>
							<tr>
								<td>Contact Number:</td>
								<td><input required id="companyContactNum" name="companyContactNum"
									type="text" value="<c:out	value="${comConNumber}" />"></td>
							</tr>
							<tr>
								<td>Status:</td>
								<td><select required name="companyStatus" id="companyStatus">
										<option value="1">Active</option>
										<option value="0">Inactive</option>
								</select></td>
							</tr>
							<tr>
								<td></td>
								<td><c:choose>
										<c:when test="${comCode == null}">
											<button type="submit" name="CCO" id="CCO" value="ACO"
												class="pure-button pure-button-primary">ADD</button>
										</c:when>
										<c:otherwise>
											<button type="submit" name="CCO" id="CCO" value="UCO"
												class="pure-button pure-button-primary">UPDATE</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</table>
					</form>
					<br />
					<br />
					<div>
						<table id="customers" width="100%">
							<tr>
								<th></th>
								<th>Code</th>
								<th>Company Name</th>
								<th>Company Address</th>
								<th>Contact Number</th>
								<th>Contact Person</th>
								<th>Status</th>
								<th></th>
							</tr>
							<c:forEach var="companies" items="${result.collection}"
								varStatus="loop">
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
		</article>
	</div>
	<script type="text/javascript"
		src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/tutor-helper.js"></script>
	<script src="/dist/js/validator.js"></script>
</body>
</html>