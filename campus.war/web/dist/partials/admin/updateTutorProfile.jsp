<!-- c133-admin-list-tutors removed unwanted commented codes -->
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

<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">

	<!--     Data Table CSS -->
<link href="/dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/dist/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.css">

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="/dist/js/main.js"></script>


<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.13/js/jquery.dataTables.js"></script>
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

		<div class="content-holder center-block clearfix">
		<!-- Page content -->
		<form action="/AdminController" method="POST" id="basicForm">

			<table id="example" class="display" width="100%">
				<thead>
					<tr>
						<th>Code</th>
						<th>Name</th>
						<th>Username</th>
						<th>Email</th>
						<th>Land Phone number</th>
						<th>Mobile phone number</th>
						<th>Town</th>
						<th>Country</th>
						<th>Approval status</th>
						<th>Status</th>
					</tr>
					</tr>
				</thead>
			</table>
		</form>
		</div>

	</div>
	<!-- End page content  -->
	</div>
	<!-- End Main Container -->

	<!-- Footer -->
	<footer> <jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	</footer>


	<!-- W3-Include -->
	<script src="/dist/bower-components/w3/w3data.js"></script>

	<!-- custom javascript -->
	<script src="/dist/js/admin/search-tutor-helper.js"></script>
	<script src="/dist/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
