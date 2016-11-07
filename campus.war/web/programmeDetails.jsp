<!-- 20161024 CM  c13-display course details-cm Created programeDetails.jsp -->
<!-- 20161024 CM  c13-display course details-cm Add jstl codes to programeDetails.jsp -->
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

	<!-- Load Programme details and course provider details-->
	<c:forEach var="programme" items="${programmeView}" varStatus="loop">

		<c:set var="ProgrammeName" value="${programme[0] }" />
		<c:set var="ProgrammeDescription" value="${programme[1]  }" />
		<c:set var="ProgrammeDuration" value="${programme[2]  }" />
		<c:set var="entryRequirements" value="${programme[3]  }" />
		<c:set var="counselorName" value="${programme[4] }" />
		<c:set var="counselorPhone" value="${programme[5] }" />
		<c:set var="courseProviderName" value="${programme[6] }" />
		<c:set var="courseProviderWebLink" value="${programme[7]}" />
		<c:set var="ProgrammeImage" value="${programme[8]}" />
		<c:set var="ProgrammeLevel" value="${programme[9]}" />
		<c:set var="ProgrammeMajor" value="${programme[10]}" />
		<c:set var="years" value="${programme[11]}" />
		<c:set var="months" value="${programme[12]}" />
		<c:set var="weeks" value="${programme[13]}" />
		<c:set var="days" value="${programme[14]}" />
		<c:set var="emailAddress" value="${programme[15]}" />
	</c:forEach>
	<table>
		<tr>
			<td>
				<table>
					<tr>
						<td valign="top"><img src="${ProgrammeImage}"
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

					<tr>
						<td>
							<h3>${ProgrammeName}</h3>

						</td>
						<td></td>
					<tr>
						<td>
							<h4>${ProgrammeLevel}</h4>

						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<h4>Major: ${ProgrammeMajor}</h4>

						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<h4>
								<c:if test="${courseProviderWebLink eq '#' }">
									${courseProviderName}
								</c:if>
								<c:if test="${courseProviderWebLink ne '#' }">
									<a href="<c:out value="${courseProviderWebLink}"></c:out>"
										target="_blank">${courseProviderName}</a>
								</c:if>
							</h4>
						</td>
						<td></td>
					</tr>
					<tr>
						<td><p>${ProgrammeDescription}</p></td>
						<td></td>
					</tr>
					<tr>
						<td>Duration : <c:if test="${years ne '0' }">
								<c:out value="${years}"></c:out> Years
						</c:if> <c:if test="${months ne '0' }">
								<c:out value="${months}"></c:out> months
						</c:if> <c:if test="${weeks ne '0' }">
								<c:out value="${weeks}"></c:out> weeks
						</c:if> <c:if test="${days ne '0' }">
								<c:out value="${days}"></c:out> days
						</c:if>

						</td>
						<td></td>
					</tr>
					<tr>
						<td><h3>Course Details</h3></td>
						<td></td>
					</tr>

					<!-- Load Semester details relevant to above programme  -->
					<c:forEach var="semester" items="${semesterView}" varStatus="loop">
						<tr>
							<c:set var="semesters" value="${semester[4]}" />
							<td><strong><c:out value="${semester[0]}"></c:out></strong></td>
							<td></td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>

										<th>Unit</th>
										<th>Description</th>
										<th>Unit code</th>
										<th>Credit Point</th>
										<th>Lectured By</th>
									</tr>
									<!-- Load module details relevant to programme and semesters  -->
									<c:forEach var="modules" items="${result.collection}"
										varStatus="loop">
										<tr>
											<c:forEach items="${modules}" var="item" varStatus="count">
												<c:choose>
													<c:when test="${count.index ==1}">
														<%-- <c:if test="${(status.index - 1) eq ${item}"></c:if> --%>
														<c:set var="semesterInM" value="${item}" />
														<%--  <c:out value="${item}"></c:out> --%>
													</c:when>
												</c:choose>

												<c:if test="${semesters eq semesterInM }">
													<c:choose>
														<c:when test="${count.index == 2}">
															<td><c:out value="${item}"></c:out></td>
														</c:when>
														<c:when test="${count.index == 3}">
															<td><c:out value="${item}"></c:out></td>
														</c:when>
														<c:when test="${count.index == 4}">
															<td><c:out value="${item}"></c:out></td>
														</c:when>
														<c:when test="${count.index == 5}">
															<td><c:out value="${item}"></c:out></td>
														</c:when>
														<c:when test="${count.index == 6}">
															<td><c:out value="${item}"></c:out></td>
														</c:when>
													</c:choose>
												</c:if>

											</c:forEach>
										<tr>
									</c:forEach>

								</table>
							</td>
						</tr>
					</c:forEach>

					<tr>
						<td><h3>Entry Requirements</h3></td>
						<td></td>
					</tr>
					<tr>

						<td>${entryRequirements}</td>
						<td></td>
					</tr>

					<tr>
						<td><h3>Intakes</h3></td>
						<td></td>
					</tr>
					<!-- Load Intakes  details relevant to programme   -->
					<c:forEach var="intake" items="${intakeView}" varStatus="loop">
						<tr>
							<td><strong><c:out value="${intake[0]}"></c:out></strong></td>
							<td></td>
						</tr>
						<tr>
							<td><c:out value="${intake[1]}"></c:out></td>
							<td></td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<td>Open Date</td>
										<td><c:out value="${intake[2]}"></c:out></td>
									</tr>
									<tr>
										<td>Close Date</td>
										<td><c:out value="${intake[3]}"></c:out></td>
									</tr>

									<tr>
										<td>Commencement Date</td>
										<td><c:out value="${intake[4]}"></c:out></td>
									</tr>
									<tr>
										<td>Fee</td>
										<td><c:out value="${intake[5]}"></c:out></td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>


					<tr>
						<td><h3>Contact Details</h3></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td>Counselor Name</td>
									<td><c:out value="${counselorName}"></c:out></td>
								</tr>
								<tr>
									<td>Counselor Contact Number</td>
									<td><c:out value="${counselorPhone}"></c:out></td>
								</tr>
								<tr>
									<td>Email</td>
									<td><c:out value="${emailAddress}"></c:out></td>
								</tr>
							</table>
						</td>
					</tr>


					<tr>
						<td><h3>Find More</h3></td>
						<td></td>
					</tr>

					<!-- Load Location  details relevant to programme   -->
					<c:forEach var="location" items="${locationView}" varStatus="loop">
						<tr>
							<td><c:out value="${location[0]}"></c:out> - <c:out
									value="${location[1]}"></c:out></td>
							<td></td>
						</tr>
					</c:forEach>

					<!-- Load Intakes  details relevant to programme   -->
					<c:forEach var="classType" items="${classTypeView}"
						varStatus="loop">
						<tr>
							<td>Class type: <c:out value="${classType[0]}"></c:out></td>
							<td></td>
						</tr>
						<tr>
							<td><c:out value="${classType[3]}"></c:out></td>
							<td></td>
						</tr>
						<c:if test="${classType[1] eq '0' }">

						</c:if>
						<c:if test="${classType[1] ne '0' }">
							<tr>
								<td>Minimum Count participant: <c:out
										value="${classType[1]}"></c:out></td>
								<td></td>
							</tr>
							<c:if test="${classType[2] eq '0' }">

							</c:if>
						</c:if>
						<c:if test="${classType[2] ne '0' }">
							<tr>
								<td>Maximum Count participant: <c:out
										value="${classType[2]}"></c:out>
								</td>
								<td></td>
							</tr>
						</c:if>


					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>