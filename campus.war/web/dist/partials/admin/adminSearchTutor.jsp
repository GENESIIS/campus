
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="applicationStatusBean"
	class="com.genesiis.campus.validation.ApplicationStatusBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->

<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
</head>
<style type="text/css">
.main-category .content-holder .course-filter-panel .filter-result-table .course-info
	{
	display: block;
	padding-top: 3px;
	padding-bottom: 3px;
}

.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name
	{
	display: inline-block;
	width: 25%;
	float: left;
	border-right: 1px solid #e1e1e1;
}

.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name .pro-name
	{
	display: block;
	text-align: center;
	font-family: "Roboto", sans-serif;
	font-size: 20px;
	color: #193949;
	line-height: 0px;
	font-weight: 600;
}

.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name .input
	{
	display: block;
	text-align: center;
	font-family: "Roboto", sans-serif;
	font-size: 20px;
	color: gray;
	line-height: 0px;
	font-style: italic;
	font-weight: 100;
}

.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name .textarea
	{
	font-family: "Roboto", sans-serif;
	font-size: 20px;
	color: gray;
	font-style: italic;
	font-weight: 100;
}

.main-category .content-holder .course-filter-panel .filtering-area .bottom ul>li
	{
	width: 16.66%;
	text-align: center;
	float: left;
}
.error-message{
	color : red;
}
</style>

<body>
	<!-- include Header-->
	<header class="header"> <jsp:include
		page="/dist/partials/layout/header.jsp"></jsp:include> </header>
	<!-- End Header -->


	<!-- Main Container - Higher-Education -->
	<div class="main-category clearfix">

		<!-- page inner header -->
		<div class="inner-header">
			<div class="category-name">
				<h1>| Search Tutor</h1>
			</div>
		</div>
		<!-- end inner header -->
		
		<!-- Page content -->
		<form action="/AdminController" method="POST" id="basicForm">
			

		</form>

	</div>
	<!-- End page content  -->
	</div>
	<!-- End Main Container -->

	<!-- Footer -->
	<footer> <jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	</footer>

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="/dist/js/main.js"></script>

	<!-- W3-Include -->
	<script src="/dist/bower-components/w3/w3data.js"></script>

	<!-- custom javascript -->
	<script src="/dist/js/admin/search-tutor-helper.js"></script>

</body>
</html>
