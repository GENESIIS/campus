<!-- 20161024 CM  c13-display course details-cm Created programeDetails.jsp -->
<!-- 20161024 CM  c13-display course details-cm Add jstl codes to programeDetails.jsp -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Program Details</title>
</head>
<body>
	<table>
		<tr>
			<td>
				<table>
					<tr>
						<td><img
							src="http://nces.ed.gov/programs/coe/images/nav/coe_hp_new.png"
							alt="Programme View" style="width: 304px; height: 228px;">

						</td>
					</tr>
					<tr>
						<td><a href="#">Rate Us</a></td>
					</tr>
					<tr>
						<td><a href="#">Enquire Online</a></td>
					</tr>
					<tr>
						<td><a href="#">Apply Now</a></td>
					</tr>
					<tr>
						<td><a href="#">Social Media Panel</a></td>
					</tr>
				</table>

			</td>
			<td>
				<table>
				<c:forEach var="programmeView" items="${programmeView}" varStatus="loop">
					<c:set var="name" value="${programmeView[0] }" />
				<%-- 	<c:set var="image" value="${programmeView[1] }" /> --%>
					<c:set var="description" value="${programmeView[1] }" />
					<c:set var="duration" value="${programmeView[2] }" />
					<c:set var="entryRequirements" value="${programmeView[3] }" />
					<c:set var="counselorName" value="${programmeView[4] }" />
					<c:set var="counselorPhone" value="${programmeView[5] }" />
				</c:forEach>
					<tr>
						<td>
							<h2><c:out value="${name}"></c:out></h2>
						</td>
						<td></td>
					</tr>
					<tr>
						<td><p><c:out value="${description}"></c:out></p></td>
						<td></td>
					</tr>
					<tr>
						<td><h3>Course Details</h3></td>
						<td></td>
					</tr>
					<tr>
						<td><strong>1st Year - 1st Semester</strong></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<th>Unit</th>
									<th>Unit code</th>
									<th>Credit Point</th>
								</tr>
								<tr>
									<td>Object Oriented Programming</td>
									<td>1003</td>
									<td>12.5</td>
								</tr>
								<tr>
									<td>Database Management</td>
									<td>1004</td>
									<td>12.5</td>
								</tr>

							</table>
						</td>
					</tr>
					<tr>
						<td><h3>Entry Requirements</h3></td>
						<td></td>
					</tr>

					<tr>
						<td><p>Completion of GCE A/L.</p></td>
						<td></td>
					</tr>

					<tr>
						<td><h3>Intakes</h3></td>
						<td></td>
					</tr>
					<tr>
						<td>Intake description here</td>
						<td></td>
					</tr>
						<td>
							<table>
								<tr>
									<td>Open Date</td>
									<td>2016-01-08</td>
								</tr>
								<tr>
									<td>Close Date</td>
									<td>2016-04-08</td>
								</tr>

								<tr>
									<td>Commencement Date</td>
									<td>2016-04-30</td>
								</tr>
								<tr>
									<td>Fee</td>
									<td>Rs. 532000.00</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td><h3>Contact Details</h3></td>
						<td></td>
					</tr>
						<tr>
						<td>
							<table>
								<tr>
									<td>Counselor Name</td>
									<td>Chathuri Mahanayake</td>
								</tr>
								<tr>
									<td>Counselor Contact Number</td>
									<td>077-7777777</td>
								</tr>

							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>