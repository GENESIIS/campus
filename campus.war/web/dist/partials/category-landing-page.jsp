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
		<div class="content-holder center-block">
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

				<!-- featured courses -->
				<c:forEach var="featuredInstitute" items="${featuredInstitutes}">
					<li class="course-info clearfix">
						<div class="col-name">
							<h1 class="pro-name">${featuredInstitute[2] }</h1>
							<div class="pro-logo">
								<img src="${featuredInstitute[16] }" alt="" width="75px;" height="75px;">
							</div>
						</div>
						<div class="col-description">
							<p>${featuredInstitute[4] }</p>
						</div>
						<div class="col-location">
							<div></div>
							<div></div>
							<div></div>
						</div>
						<div class="col-duration">
							<label>Degree<br> <Span>4 Years</Span></label>
						</div>
					</li>
				</c:forEach>


				</ul>
			</div>
			<!-- End filter result table -->

		</div>


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

</body>
</html>