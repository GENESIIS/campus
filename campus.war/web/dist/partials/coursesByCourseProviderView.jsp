<!-- 20161127 DJ  c51-report-courses-by-course-provider-MP-dj  search view for courses by course providers -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>

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
					<a href="/index.jsp" class="btn-home center-block"></a>
				</div>
				<!-- End home button -->
				<div class="menu-tabs clearfix">
					<!-- Main menu tabs -->
					<div class="top-menus">
						<ul class="list-inline">
							<li><a href="courses.html">All Courses</a></li>
							<li><a href="/dist/partials/topCourseProviders.jsp">Course
									Providers</a></li>
							<li><a href="about-us.html">About Us</a></li>
							<li><a href="javascript:">Contact Us</a></li>
							<li><a href="news.html">News</a></li>
							<li><a href="f-and-q.html">F & Q</a></li>
							<li><a href="rss.html">Rss</a></li>
						</ul>
					</div>
					<!-- End Main menu tabs -->

					<!-- Course Category tabs -->
					<div class="bottom-menus">
						<ul class="list-inline">
							<li><a href="javascript:">Pre Education</a></li>
							<li><a href="javascript:">School Education</a></li>
							<li><a href="/dist/partials/category/higher-education.jsp">Higher
									Education</a></li>
							<li><a href="javascript:">Corporate Training</a></li>
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

	<!-- Main Container - Contact-US -->
	<div class="top-providers-screen clearfix">
		<div class="inner-header">
			<div class="main-topic">
				<h1>Find your future with us</h1>
			</div>
		</div>
		<div class="page-topic">
			<h2 class="page-topic-t1">| Courses by Course Providers</h2>
		</div>

		<!-- image header -->
		<div class="inner-image-header">
			<div class="bg-image"></div>
		</div>
		<!-- END inner-image-header -->

		<!-- Contact Form -->
		<div class="provider-list-holder clearfix">
			<div class="top-list clearfix">
				<div class="search clearfix">
					<form action="PublicController" method="post">
						<select name="providers">
							<option value="1">Course provider1</option>
							<option value="2">Course provider2</option>
							<option value="3">Course provider3</option>
						</select> <input type="date" name="search">
						<button type="submit" name="CCO"
							value="REPORT_COURSES_BY_COURSE_PROVIDER">Search</button>
					</form>
				</div>
				<!-- End list header -->
				<c:if test='${not empty  result.collection}'>
					<div class="top-rated top-viewed clearfix">
						<h1>Top Viewed</h1>
						<div class="providers-grid center-block clearfix">
							<ul class="list-inline clearfix">
								<c:forEach var="provider" items="${result.collection}">
									<li class="col-md-3 col-lg-3 col-sm-4"><c:forEach
											var="pvAttribute" items="${provider}" varStatus="count">
											<c:if test="${count.index == 0}">
												<c:set var="folder" value="${pvAttribute}" />
												<c:set var="format" value=".png" />
												<c:set var="image" value="${pvAttribute}${format}" />
											</c:if>
											<c:if test="${count.index == 1}">
												<c:set var="prefix" value="${pvAttribute}" />
											</c:if>
											<c:if test="${count.index == 2}">
												<div class="item-holder">
													<div class="provider-logo text-center">
														<c:set var="slash" value="/" />
														<img
															src="${contextDeployLogoPath}${slash}${folder}${slash}${image}" />
													</div>
													<div class="provider-name text-center">
														<h2>
															<c:out value="${prefix}" />
														</h2>
													</div>
												</div>
											</c:if>
										</c:forEach></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
				<!-- End top viewed list -->
				<div class="btn-show-all pull-right">
					<a
						href="dist/partials/viewMoreCourseProviders.jsp?categoryCode=${categoryCode}">
						View More </a>
				</div>
			</div>
			<!-- End top-rated-list -->
		</div>
		<!-- END provider-list-holder -->
	</div>
	<!-- End Container - Top Providers list -->

	<!-- Footer -->
	<footer w3-include-html="layout/footer.jsp"></footer>
</body>
</html>