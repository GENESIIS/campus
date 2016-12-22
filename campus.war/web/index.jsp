<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="dist/css/style.css" rel="stylesheet">

<!-- W3-Include -->
    <script src="dist/bower-components/w3/w3data.js"></script>
    <script type="text/javascript" src="dist/bower-components/jquery/jquery.min.js"></script>
	<script src="dist/js/landingPage/landing-page-ui-helper.js"></script>
	
<style>
body {background-color: powderblue;}
h1   {color: red ;}
p    {color: blue;}
</style>

</head>
<body>	
<!-- Header-->
	<header> <jsp:include page="/dist/partials/layout/header.jsp" /></header>

	<div>
		<h1>Report Generation</h1><p>Please Select the report type</p>
	</div>
	<hr>
	
	<div>		
		<a href="dist/partials/reportCoursesByCourseProvider.jsp"><u>Courses By
			Course Provider </u></a>
	</div>
	<hr>
	<div>		
		<a href="/dist/partials/bannerStatisticsView.jsp"><u>Banner statistics</u></a>
	</div>
	<hr>

	<!-- Footer -->
	<footer> <jsp:include page="/dist/partials/layout/footer.jsp" /></footer>

	<!-- jQuery & Other js -->
	<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="dist/js/main.js"></script>
</body>
</html>