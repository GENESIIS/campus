<!-- 20161027 TR c11 start styling courses filter result page -->
<!-- 20161109 JH c7-higher-education-landing-page-MP display featured course providers -->

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src="/dist/bower-components/w3/w3data.js"></script>
<style type="text/css">
.limit {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	max-width: 400px;
	max-height: 30px;
}
</style>
</head>
<body>

	<!-- Header-->
	<header class="header col-lg-12 col-md-12 col-sm-12 clearfix">
		<jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
	</header>
	<!-- End Header -->

	<!-- Main Container - Higher-Education -->
	<div class="main-category clearfix">

		<!-- page inner header -->
		<div class="inner-header">
			<c:forEach items="${result.collection}" var="category">
				<div class="category-image">
					<img src="${category[3] } " alt="">
				</div>
				<div class="category-name">
					<h1>
						|
						<c:out value="${category[1] }" />
					</h1>
				</div>
			</c:forEach>
		</div>
		<!-- end inner header -->


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
				<div class="info-slider-holder">
					<div class="featured-institute clearfix">
						<div class="slider-heading clearfix">
							<div class="topic">
								<h2>Featured Institutes</h2>
							</div>
							<div class="move-btn">
								<a href="javascript:">Left</a> <a href="javascript:">Right</a>
							</div>
						</div>
						<div id="featured-panel">
							<c:forEach var="featuredInstitute" items="${featuredInstitutes}">

								<div class="institute-info clearfix">
									<div class="inst-logo">
										<img src="${featuredInstitute[16] }" alt="Institute Logo">
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

								<div class="institute-description clearfix">
									<p class="limit">
										<c:out value="${featuredInstitute[4] }"></c:out>
									</p>
									<div class="btn-more clearfix">
										<a href="javascript:">Show More</a>
									</div>
								</div>

							</c:forEach>

						</div>
					</div>
				</div>
				<!-- Eng featured institute info slider -->
				<div class="most-viewed-panel clearfix">
					<h1>| Most Viewed</h1>
					<div class="banner-holder">
						<div class="banner clearfix">
							<div class="logo-image">
								<img src="../../i/icbt.png" alt="Logo">
							</div>
							<div class="description">
								<h1>
									ICBT <span>@ Colombo</span>
								</h1>
								<p>Lorem Ipsum is simply dummy text of the printing and
									typesetting</p>
							</div>
						</div>
						<!-- End Banner -->
					</div>
					<!-- Banner holder -->
				</div>
				<!-- End Most Viewed Panel -->
			</div>
			<!-- End right panel -->
		</div>
		<!-- End page content  -->
	</div>
	<!-- End Main Container -->


	<!-- Footer -->
	<footer>
		<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	</footer>

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
	<script src="/dist/js/category/ui-populate-helper.js"></script>

</body>
</html>