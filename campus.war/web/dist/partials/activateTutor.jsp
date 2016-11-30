<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Profile</title>
</head>
<body>
	<div class="container">

		<article>
<!--			<header>
				<h1>Manage Organization Details</h1>
				<%@include file="header.jsp"%>
			</header> -->
			<section>
				<nav>
					<ul>
						<li><form action="index.jsp" method="POST">
								<button type="submit" name="CCO" id="CCO" value="LED"
									class="pure-button pure-button-primary">HOME</button>
							</form></li>
						<br />
						<li><form action="EmployeeController" method="POST">
								<button type="submit" name="CCO" id="CCO" value="LED"
									class="pure-button pure-button-primary">List Employees</button>
							</form></li>
						<br />
					
					</ul>
				</nav>
			</section>
			<section>
				<div align="center">
					<h1>Add Organization Details</h1>
					<span style="color: red"><c:out value="${errorMessage}" /></span>
					<form class="pure-form pure-form-aligned" method="POST"
						action="CompanyController">
						<table border="0">
							<tr>
								<td>Organization Name:</td>
								<td><input required id="organizationName"
									name="organizationName" type="text"
									value="<c:out	value="${orgName}" />"
									onfocus="{ this.value = "; }"></td>
							</tr>
							<tr>
								<td>CEO (Owner):</td>
								<td><input required id="ceoName" name="ceoName" type="text"
									value="<c:out	value="${orgOwner}" />"
									onfocus="{ this.value = "; }"></td>
							</tr>
							<tr>
								<td>Status:</td>
								<td><select name="orgStatus" id="orgStatus">
									<c:choose>
										<c:when test="${((empty orgStatus) || (orgStatus == '1'))}">
											<option value="1" selected="selected">Active</option>
											<option value="0">Inactive</option>
										</c:when>
										<c:otherwise>
											<option value="1">Active</option>
											<option value="0" selected="selected">Inactive</option>
										</c:otherwise>
									</c:choose>
								</select></td>
							</tr>
							<tr>
								<td></td>
								<td><c:choose>
										<c:when test="${orgCode == null}">
											<button type="submit" name="CCO" id="CCO" value="AOR"
												class="pure-button pure-button-primary">ADD</button>
										</c:when>
										<c:otherwise>
											<input hidden="true" id="orgCode" name="orgCode" type="text"
												value="<c:out	value="${orgCode}" />">
											<button type="submit" name="CCO" id="CCO" value="UOR"
												class="pure-button pure-button-primary">UPDATE</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</table>
					</form>
					<br /> <br />
					<div>
						<table id="customers" width="100%">
							<tr>
								<th></th>
								<th>Code</th>
								<th>Name</th>
								<th>Owner</th>
								<th>Status</th>
								<th></th>
							</tr>
							<c:forEach var="organizations" items="${result.collection}"
								varStatus="loop">

								<tr>
									<td><c:out value="${loop.index+1}" /></td>
									<td><c:out value="${organizations[0]}"></c:out></td>
									<td><c:out value="${organizations[1]}"></c:out></td>
									<td><c:out value="${organizations[2]}"></c:out></td>
									<td><c:out value="${organizations[3]}"></c:out></td>

									<td>
										<form method="POST" action="CompanyController">
											<input id="orgCode" name="orgCode"
												value="<c:out value="${organizations[0]}"/>" hidden="true" />

											<c:set var="activestatus" value="INACTIVE"></c:set>
											<c:choose>
												<c:when test="${organizations[3] eq activestatus}">
													<button type="submit" name="CCO" id="CCO" value="LCO"
														class="pure-button pure-button-primary" disabled="true">Add
														Company</button>
												</c:when>
												<c:otherwise>
													<button type="submit" name="CCO" id="CCO" value="LCO"
														class="pure-button pure-button-primary">Add
														Company</button>
												</c:otherwise>
											</c:choose>

											<button type="submit" name="CCO" id="CCO" value="UOR"
												class="pure-button pure-button-primary">EDIT</button>
										</form>
									</td>
								</tr>

							</c:forEach>
						</table>
					</div>

				</div>
			</section>
		</article>
		<!-- <%@include file="footer.jsp"%> -->
	</div>

</body>
</html>