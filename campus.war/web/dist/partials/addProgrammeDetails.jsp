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
</head>
<body>
	<!-- Header-->
	<jsp:include page="/dist/partials/layout/header.jsp" />
	<!-- End Header-->
	<!-- Main Container - Contact-US -->
	<div class="top-providers-screen clearfix">
		<div class="inner-header">
			<div class="main-topic">
				<h1>Find your future with us</h1>
			</div>
		</div>
		<div class="page-topic">
			<h2 class="page-topic-t1">| Add a Course</h2>
		</div>

		<!-- image header -->
		<div class="inner-image-header">
			<div class="bg-image"></div>
		</div>
		<!-- END inner-image-header -->

		<!-- END provider-list-holder -->
	</div>
	<!-- End Container - Top Providers list -->

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
	
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!-- End Footer -->
</body>
</html>