<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161130 CW c13 c13-display-course-details- modified bootstrap source path -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>

</head>
<body>
	 <header class="header col-lg-12 col-md-12 col-sm-12 clearfix"> 
		
	<div class="top">
		<div class="logo-brand"> 
			<h1 class="logo-txt">Campus.lk</h1>
		</div>
	</div>
	<div class="bottom">
		<div class="menu-bar">
			<div class="home pull-left">
				<a href="../../../index.html" class="btn-home center-block"></a>
			</div>
			<!-- End home button -->
			<div class="menu-tabs clearfix">
				<!-- Main menu tabs -->
				<div class="top-menus">
					<ul class="list-inline">
						<li><a href="../courses.html">All Courses</a></li>
						<li><a href="../about-us.html">About Us</a></li>
						<li><a href="../contact-us.html">Contact Us</a></li>
						<li><a href="../news.html">News</a></li>
						<li><a href="../f-and-q.html">F & Q</a></li>
						<li><a href="../rss.html">Rss</a></li>
					</ul>
				</div>
				<!-- End Main menu tabs -->

				<!-- Course Category tabs -->
				<div class="bottom-menus">
					<ul class="list-inline">
						<li><a href="javascript:">Pre Education</a></li>
						<li><a href="javascript:">School Education</a></li>
						<li><a href="javascript:">Higher Education</a></li>
						<li><a href="javascript:">Corporate Training</a></li>
						<li>
							<!-- add Programme Id here for test course preview -->
							<form method="Post" action="/PublicController">
								<input type="hidden" name="programmeCode" value="8" />
								<button type="submit" name="CCO" id="CCO" value="VPD"
									class="btn btn-info navbar-btn">View Programme Details</button>

							</form>
						</li>
						<li><a href="javascript:">Vocational Training</a></li>
						<li><a href="javascript:">Talent & Skill</a></li>
					</ul>
				</div>
				<!-- End Course Category tabs -->
			</div>
			<div class="keyword-search pull-right">
				<div class="search-bar">
					<input type="text" placeholder="Keyword Search"> <a
						href="javascript:" class="colr-white">Enter</a>
				</div>
				<!-- End Keyword Search -->
				<div class="login-link">
					<a href="javascript:" class="colr-white">Login</a>
				</div>
			</div>
			<!-- End keyword search -->
		</div>
	</div>
	</header>
	<!-- End Header -->

	<!-- Main Container - Higher-Education -->
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
		<c:set var="imagePath" value="${programme[16]}" />
	</c:forEach>
	<div class="course-view  clearfix">

	<c:set var="image_extension" value=".jpg" />
	
		<!-- Start Page content -->
		<!-- Rating box and image : left side -->
		<div class="left-panel">
			<div class="category-image">
				<img src="${imagePath}${image_extension}" alt="Programme View"
					style="width: 304px; height: 228px;">
			</div>
			<div class="rating-box">
				<div class="rate-us">
					<a href="javascript:">Rate Us</a>
				</div>
				<!-- End Rate Us-->
				<div class="inquire-online">
					<a href="javascript:">Inquire Online </a>
				</div>
				<!-- End Online Inquire -->
				<div class="apply-now">
					<a href="javascript:">Apply Now </a>
				</div>
				<!-- End Apply now -->
				<div class="social-media-links">Facebook</div>
			</div>

		</div>

		<!-- Course details : right-side -->
		<div class="details-view-panel">
			<div class="course-info">
				<div class="info-header">
					<div class="course-name">
						<a href="javascript:">${ProgrammeName}</a>
					</div>
					<h2 class="degree-level">${ProgrammeLevel}
						<span class="duration"> Duration : <c:if
								test="${years ne '0' }">
								<c:out value="${years}"></c:out> Years
						</c:if> <c:if test="${months ne '0' }">
								<c:out value="${months}"></c:out> months
						</c:if> <c:if test="${weeks ne '0' }">
								<c:out value="${weeks}"></c:out> weeks
						</c:if> <c:if test="${days ne '0' }">
								<c:out value="${days}"></c:out> days
						</c:if></span>
					</h2>
					<h3 class="degree-type">Major: ${ProgrammeMajor}</h3>
					<h4 class="degree-provider">
						<c:if test="${courseProviderWebLink eq '#' }">
									${courseProviderName}
								</c:if>
						<c:if test="${courseProviderWebLink ne '#' }">
							<a href="<c:out value="${courseProviderWebLink}"></c:out>"
								target="_blank">${courseProviderName}</a>
						</c:if>
					</h4>
					<p class="degree-description">${ProgrammeDescription}</p>
				</div>
				<div class="info-table">

					<!-- Section : Course Details -->
					<div class="info-section">
						<div class="section-header flip">
							<a href="javascript:">> Course Details </a>
						</div>
						<div class="section-content slideable">
							<!-- 1st Row -->


							<!-- 2nd Row -->
							<c:forEach var="semester" items="${semesterView}"
								varStatus="loop">
								<c:set var="semesters" value="${semester[4]}" />
								<div class="sub-row flip">
									<a href="javascript:">> <c:out value="${semester[0]}"></c:out></a>
								</div>
								<div class="sub-row-content slideable">
									<p class="row-main-description">
										<c:out value="${semester[3]}"></c:out>
									</p>
									<table>
										<tr>
											<th>Unit</th>
											<th>Description</th>
											<th>Unit Code</th>
											<th>Credit Point</th>
											<th>Lectured By</th>
										</tr>
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
								</div>
							</c:forEach>
							<!-- -->
						</div>
					</div>

					<!-- Section : Entry Requirements -->

					<div class="info-section">
						<div class="section-header flip">
							<a href="javascript:">> Entry Requirements</a>
						</div>
						<div class="section-content slideable">
							<ul>
								<li>${entryRequirements}</li>
							</ul>
						</div>
					</div>

					<!-- Section : Entry Requirements -->
					<div class="info-section">
						<div class="section-header flip">
							<a href="javascript:">> Intakes </a>
						</div>
						<div class="section-content slideable">

							<!-- 1st Row -->
							<!-- Load Intakes  details relevant to programme   -->
							<c:forEach var="intake" items="${intakeView}" varStatus="loop">
								<div class="sub-row flip">
									<a href="javascript:">> <c:out value="${intake[0]}"></c:out>
									</a>
								</div>
								<div class="sub-row-content slideable">
									<p class="row-main-description">
										<c:out value="${intake[1]}"></c:out>
									</p>
									<table>
										<tr>
											<td>Open Date :</td>
											<td><c:out value="${intake[2]}"></c:out></td>
										</tr>
										<!-- end tr-->
										<tr>
											<td>Close Date :</td>
											<td><c:out value="${intake[3]}"></c:out></td>
										</tr>
										<!-- end tr -->
										<tr>
											<td>Commencement Date :</td>
											<td><c:out value="${intake[4]}"></c:out></td>
										</tr>
										<!-- end tr -->
										<tr>
											<td>Course Fee(Rs)</td>
											<td><c:out value="${intake[5]}"></c:out></td>
										</tr>
										<!-- end tr -->
									</table>
								</div>
							</c:forEach>
							<!-- End Row -->
						</div>
					</div>
					<!-- End Section -->

					<!-- Section : Entry Requirements -->
					<div class="info-section">
						<div class="section-header flip">
							<a href="javascript:">> Contact Details </a>
						</div>
						<div class="section-content slideable">
							<h2>Counselor Name: <c:out value="${counselorName}"></c:out></h2>
							<h2>Counselor Contact No : <c:out value="${counselorPhone}"></c:out></h2>
							<h2>Email : <c:out value="${emailAddress}"></c:out></h2>
							<h2>Progamme Locations</h2>
							<!-- Load Location  details relevant to programme   -->
								<c:forEach var="location" items="${locationView}"
									varStatus="loop">
									<tr>
										<td><c:out value="${location[0]}"></c:out> - <c:out
												value="${location[1]}"></c:out>,</td>
									
									</tr>
								</c:forEach>
						</div>
					</div>
					<!-- End Section -->

				</div>
			</div>
		</div>

		<!-- End page content -->

	</div>
	<!-- End Main Container -->

	<!-- Footer -->
	<!-- <footer w3-include-html="/dist/partials/layout/footer.jsp"></footer> --> 
	<footer>
    <div class="ft-top">

    </div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright © Campus.lk</label>
    </div>
	</footer>

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="/dist/js/main.js"></script>
</body>
</html>