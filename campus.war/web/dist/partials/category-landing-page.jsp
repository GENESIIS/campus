<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161109 JH c7-higher-education-landing-page-MP display featured course providers -->
<!-- 20161110 JH c7-higher-education-landing-page-MP add banner panel to the landing page -->
<!-- 20161115 JH c7-higher-education-landing-page-MP add carousel to display course providers -->
<!-- 20161116 JH c7-higher-education-landing-page-MP load category details from the database for header wip -->
<!-- 20161124 JH c7-higher-education-landing-page-MP QA improvements: display featured institutes when only one is available -->
<!-- 20161125 JH c7-higher-education-lanidng-page-MP change slider UI design -->
<!-- 20161125 JH c7-higher-education-lanidng-page-MP QA improvement: load images using system config enum wip -->
<!-- 20161126 JH c7-higher-education-lanidng-page-MP QA improvement: load images using system config enum -->
<!-- 20161128 JH c7-higher-education-landing-page-MP QA improvement: load course provider common image using system config  -->
<!-- 20161129 JH c7-higher-education-lanidng-page-MP QA improvement: remove header and footer tags -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->

<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">

<!-- custom javascript -->
<script src="/dist/js/header/ui-populate-helper.js"></script>
<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
</head>

<body onload="getCategoryData()">

	<!-- include Header-->
		<jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
	<!-- End Header -->

	<!-- Main Container - Higher-Education -->
	<div class="main-category clearfix">

		<!-- get category data -->
		<c:set var="code" value="" />
		<c:set var="categoryIdentifier" value="" />

		<c:set var="slash" value="/" />
		<c:set var="smallLogo" value="_small.jpg" />
		<c:set var="commonLogo" value="_common.jpg" />

		<!-- page inner header -->
		<div class="inner-header">
			<c:forEach items="${result.collection}" var="category">
				<div class="category-image">

					<img src="${categoryLogoPath }${slash}${category[3] } " alt="">
				</div>
				<div class="category-name">
					<h1>
						|
						<c:out value="${category[1] }" />
					</h1>
				</div>
				<c:set var="code" value="${category[0] }"></c:set>
				<c:set var="categoryIdentifier" value="${category[8] }"></c:set>
			</c:forEach>
		</div>
		<!-- end inner header -->

		<input type="hidden" name="categoryCode" id="categoryCode"
			value="${code}" /> <input type="hidden"
			name="categoryIdentifierString" id="categoryIdentifierString"
			value="${categoryIdentifier}" />
		<!-- Page content -->
		<div class="content-holder center-block clearfix">
			<!-- course filter panel : left side -->
			<div class="course-filter-panel">
				<div class="filtering-area">
					<div class="top"></div>
					<div class="bottom clearfix">
						<ul class="list-inline">
							<li><a href="javascript:">All</a></li>
							<li><a href="javascript:">Cetificate</a></li>
							<li><a href="javascript:">Degree</a></li>
							<li><a href="javascript:">Diploma</a></li>
							<li><a href="javascript:">HND</a></li>
							<li><a href="javascript:">MBA</a></li>
							<li><a href="javascript:">Languages</a></li>
							<li><a href="javascript:">MSc</a></li>
							<li><a href="javascript:">Foreign</a></li>
							<li><a href="javascript:">Short Courses </a></li>
							<li><a href="javascript:">Work Shops </a></li>
							<li><a href="javascript:">Other</a></li>
						</ul>
					</div>
				</div>

				<!-- Filter result table -->
				<div class="filter-result-table">
					<ul class="result-row">

						<c:forEach var="featuredInstitute" items="${featuredInstitutes}">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">SLIIT</h1>
									<div class="pro-logo">
										<img src="/dist/i/sliit-logo.png" alt="">
									</div>
								</div>
								<div class="col-description">
									<p>Lorem Ipsum is simply dummy text of the printing</p>
								</div>
								<div class="col-location">
									<a href="javascript:">Malabe</a>
								</div>
								<div class="col-duration">
									<label>Degree<br> <Span>4 Years</Span></label>
								</div>
							</li>
							<!-- end -->

						</c:forEach>
					</ul>
				</div>
				<!-- End filter result table -->

			</div>
			<!-- End left panel -->

			<!-- slider & banner panel : right side -->
			<div class="sliding-info-panel">
				<c:if test="${not empty featuredInstitutes}">
					<div class="info-slider-holder">
						<div class="featured-institute clearfix">
							<div class="slider-heading clearfix">
								<div class="topic">
									<h2>Most Viewed Institutes</h2>
								</div>
							</div>

							<div id="myCarousel" class="carousel slide" data-ride="carousel">

								<c:set var="provider" value="" />
								<!-- Wrapper for slides -->
								<div class="carousel-inner" role="listbox">

									<c:forEach var="featuredInstitute"
										items="${featuredInstitutes}" varStatus="loopCount">
										<c:choose>
											<c:when test="${loopCount.index == 0 }">
												<div class="item active">
													<div class="institute-info clearfix">
														<div class="inst-logo">
															<img
																src="${providerLogoPath}${slash }${featuredInstitute[0] }${slash}${featuredInstitute[0] }${smallLogo}"
																alt="Institute Logo" style="width: 100px; height: 75px;">
														</div>
														<div class="inst-name">
															<h1 class="short-name">
																<c:out value="${featuredInstitute[2] }"></c:out>
															</h1>
															<h2 class="full-name">
																<c:out value="${featuredInstitute[3] }"></c:out>
															</h2>
														</div>
													</div>
													<div class="institute-description clearfix"
														style="background-image: url('${providerLogoPath}${slash }${featuredInstitute[0] }${slash}${featuredInstitute[0] }${commonLogo}');">
														<p>
															<c:out value="${featuredInstitute[4]}"></c:out>
														</p>
														<div class="btn-more clearfix">
															<!-- 	<a href="javascript:">Show More</a>  -->
															<button type="submit" name="CCO" id="CCO"
																value="LIST_COURSE_PROVIDER_PAGE" class="btn">Show
																More</button>
															<c:set var="provider" value="${featuredInstitute[0]}" />
														</div>
													</div>
												</div>
											</c:when>
										</c:choose>
									</c:forEach>

									<c:forEach var="featuredInstitute"
										items="${featuredInstitutes}" varStatus="loopCount">
										<c:choose>
											<c:when test="${loopCount.index != 0 }">
												<div class="item ">
													<div class="institute-info clearfix">
														<div class="inst-logo">
															<img
																src="${providerLogoPath}${slash }${featuredInstitute[0] }${slash}${featuredInstitute[0] }${smallLogo}"
																alt="Institute Logo" style="width: 100px; height: 75px;">
														</div>

														<div class="inst-name">
															<h1 class="short-name">
																<c:out value="${featuredInstitute[2] }"></c:out>
															</h1>
															<h2 class="full-name">
																<c:out value="${featuredInstitute[3] }"></c:out>
															</h2>
														</div>
													</div>
													<div class="institute-description clearfix"
														style="background-image: url('${providerLogoPath}${slash }${featuredInstitute[0] }${slash}${featuredInstitute[0] }${commonLogo}');">
														<p>
															<c:out value="${featuredInstitute[4]}"></c:out>
														</p>
														<div class="btn-more clearfix">
															<button type="submit" name="CCO" id="CCO"
																value="LIST_COURSE_PROVIDER_PAGE" class="btn">Show
																More</button>
															<c:set var="provider" value="${featuredInstitute[0]}" />
														</div>
													</div>
												</div>
											</c:when>
										</c:choose>
									</c:forEach>

								</div>
								<!-- Left and right controls -->

								<a href="#myCarousel" role="button" data-slide="prev"
									class="pull-left"> Previous </a> <a href="#myCarousel"
									role="button" data-slide="next" class="pull-right"> Next </a>


							</div>

						</div>
					</div>
					<!-- End featured institute info slider -->
				</c:if>


				<c:if test="${not empty institutes }">
					<div class="most-viewed-panel clearfix">
						<h1>| Featured Institutes</h1>

						<c:forEach var="institute" items="${institutes}">
							<div class="banner-holder">
								<div class="banner clearfix">
									<div class="logo-image">
										<img
											src="${providerLogoPath}${slash }${institute[0] }${slash}${institute[0] }${smallLogo}"
											alt="Logo">
									</div>
									<div class="description">
										<h1>
											<c:out value="${institute[2] }"></c:out>
											<span>@ <c:out
													value="${institute[26] }${institute[27] }${institute[28] }"></c:out></span>
										</h1>
										<p>
											<c:out value="${institute[3] }"></c:out>
										</p>
									</div>
								</div>
								<!-- End Banner -->
							</div>
							<!-- Banner holder -->
						</c:forEach>
					</div>
					<!-- End Most Viewed Panel -->
				</c:if>
			</div>
			<!-- End right panel -->
		</div>
		<!-- End page content  -->
	</div>
	<!-- End Main Container -->


	<!-- Footer -->
		<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="/dist/js/main.js"></script>


</body>
</html>