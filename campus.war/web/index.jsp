<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- to clear cache data -->
<meta Http-Equiv="Cache-Control" Content="no-cache">
<meta Http-Equiv="Pragma" Content="no-cache">
<meta Http-Equiv="Expires" Content="0"> 


<!-- clears browser history and redirects url -->

<script type="text/javascript">
//Check if a new cache is available on page load.
 window.history.forward();
    function noBack() { window.history.forward(); }
</script>

<title>Campus.lk</title>
<!-- Bootstrap & CSS Style-->
<link href="dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="dist/css/style.css" rel="stylesheet">
<link href="dist/css/image-slides.css" rel="stylesheet">

<!-- <script src="/dist/js/header/ui-populate-helper.js"></script>   -->

<!-- W3-Include -->
<script src="dist/bower-components/w3/w3data.js"></script>
<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/js/image-slides.js"></script>

<!-- CAM-1 To load categories. -->
<!-- <script src="dist/js/landingPage/landing-page-ui-helper.js"></script> -->

</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
	<!-- Header-->
	<!-- End Header -->
	<header>
		<jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
		<jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>
			<jsp:include page="/dist/partials/login/loginPopup.jsp"></jsp:include>
	</header>

	<!-- End Header -->

	<!-- Main Container - Landing -->
	<!--<div w3-include-html="dist/partials/landing.jsp"></div>-->

	<script>
		$(document)
				.ready(
						function() {
						
							var sPageURL = decodeURIComponent(window.location.search
									.substring(1)), sURLVariables = sPageURL
									.split('?'), sParameterName, i;
								 for (i = 0; i < sURLVariables.length; i++) {
													        sParameterName = sURLVariables[i].split('=');
						
							if (sParameterName[1] === 'true') {
								
								$('#loginPopup').modal('show');
								
								}
							}
						});
	</script>
	<div class="main-screen clearfix">
		<div class="content-panel clearfix">
		
			<div class="main-slider clearfix">
				<div class="slider-mask clearfix"></div>
				<!--<ul class="rslides" id="slider1">-->
				<!--<li><img src="dist/i/slider/slide-1.jpg" alt=""></li>-->
				<!--<li><img src="dist/i/slider/slide-2.jpg" alt=""></li>-->
				<!--<li><img src="dist/i/slider/slide-3.jpg" alt=""></li>-->
				<!--</ul>-->
				<div class="callbacks_container">
					<ul class="rslides" id="slider1">
						<li><img src="dist/i/slider/slide-1.jpg" alt=""></li>
						<li><img src="dist/i/slider/slide-2.jpg" alt=""></li>
						<li><img src="dist/i/slider/slide-7.jpg" alt=""></li>
						<li><img src="dist/i/slider/slide-8.jpg" alt=""></li>
						<li><img src="dist/i/slider/slide-5.jpg" alt=""></li>
						<li><img src="dist/i/slider/slide-6.jpg" alt=""></li>
					</ul>
					<!--<a href="#" class="callbacks_nav callbacks4_nav prev">Previous</a>-->
					<!--<a href="#" class="callbacks_nav callbacks4_nav next">Next</a>-->
				</div>
				<!-- End main intro slider -->
			</div>
			<!-- End Main slider -->
			<div class="search-area clearfix">
				<div class="main-search-bar center-block clearfix">
					<div class="bar-holder center-block clearfix">
						<input class="center-block" type="text"
							placeholder="Search : Program, Course, or Career">
					</div>
				</div>
				<!-- End Main search bar -->

				<div class="filter-boxes clearfix">
					<div class="box-holder center-block">
						<ul class="list-inline" name="mainCategoryList"
							id="mainCategoryList">

						</ul>
					</div>

				</div>
				<!-- End category boxes area -->
			</div>

		</div>

		<div class="banner-panel pull-right clearfix">
			<!--Advertise Here-->
		</div>
	</div>

	<!-- Footer -->
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->

	<!-- jQuery & Other js -->
	<!--<script src="dist/bower-components/jquery/jquery.min.js"></script>-->
	<!--<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>-->
	<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="dist/js/main.js"></script>
	<script src="dist/js/header/ui-populate-helper.js"></script>

</body>
</html>