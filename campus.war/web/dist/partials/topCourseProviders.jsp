<!-- 20161111 DJ  c6-list-available-institutes-on-the-view view top viewed/top rated course providers -->

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

<!-- W3-Include -->
<script src="../bower-components/w3/w3data.js"></script>

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
					<a href="../../index.html" class="btn-home center-block"></a>
				</div>
				<!-- End home button -->
				<div class="menu-tabs clearfix">
					<!-- Main menu tabs -->
					<div class="top-menus">
						<ul class="list-inline">
							<li><a href="courses.html">All Courses</a></li>
							<li><a href="../topCourseProviders.jsp">Course Providers</a></li>
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
							<li><a href="category/higher-education.html">Higher
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
			<h2 class="page-topic-t1">| Top Course Providers</h2>
		</div>

		<!-- image header -->
		<div class="inner-image-header">
			<div class="bg-image"></div>
		</div>
		<!-- END inner-image-header -->

		<!-- Contact Form -->
		<div class="provider-list-holder clearfix">
			<div class="top-list clearfix">
				<div class="list-header clearfix">
					<div class="btn-show-all pull-right">
						<form action="PublicController" method="post">
							<input type="hidden" name="categoryCode" value="${categoryCode}" />
							<button type="submit" name="CCO"
								value="LIST_ALL_COURSE_PROVIDERS">Show ALL</button>
						</form>
					</div>
				</div>
				<!-- End list header -->

				<c:if test='${not empty tRCProviders}'>
					<div class="top-rated clearfix">
						<h1>Top Rated</h1>
						<div class="providers-grid center-block clearfix">
							<ul class="list-inline clearfix">
								<c:forEach var="provider" items="${tRCProviders}">
									<li class="col-md-3 col-lg-3 col-sm-4"><c:forEach
											var="pvAttribute" items="${provider}" varStatus="count">
											<c:if test="${count.index == 1}">
												<c:set var="prefix" value="${pvAttribute}" />
											</c:if>
											<c:if test="${count.index == 2}">
												<div class="item-holder">
													<div class="provider-logo text-center">
														<c:set var="slash" value="/" />
														<img
															src="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" />
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
				<!-- End top rated list -->
				<c:if test='${not empty  result.collection}'>
					<div class="top-rated top-viewed clearfix">
						<h1>Top Viewed</h1>
						<div class="providers-grid center-block clearfix">
							<ul class="list-inline clearfix">
								<c:forEach var="provider" items="${result.collection}">
									<li class="col-md-3 col-lg-3 col-sm-4"><c:forEach
											var="pvAttribute" items="${provider}" varStatus="count">
											<c:if test="${count.index == 1}">
												<c:set var="prefix" value="${pvAttribute}" />
											</c:if>
											<c:if test="${count.index == 2}">
												<div class="item-holder">
													<div class="provider-logo text-center">
														<c:set var="slash" value="/" />
														<img
															src="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" />
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
					<form action="PublicController" method="post">
						<input type="hidden" name="categoryCode" value="${categoryCode}" />
						<button type="submit" name="CCO" value="LIST_ALL_COURSE_PROVIDERS">Show
							ALL</button>
					</form>
				</div>
			</div>
			<!-- End top-rated-list -->
		</div>
		<!-- END provider-list-holder -->
	</div>
	<!-- End Container - Top Providers list -->

	<!-- Footer -->
	<footer w3-include-html="layout/footer.html"></footer>

	<!-- jQuery & Other js -->
	<script src="../bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="../bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="../js/main.js"></script>

</body>
</html>