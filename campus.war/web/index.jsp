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

</head>
<body>
	<!-- Header-->
	<header w3-include-html="dist/partials/layout/header.jsp"></header>

	<!-- Main Container - Landing -->
	<div w3-include-html="dist/partials/landing.jsp"></div>

	<!-- Footer -->
	<footer w3-include-html="dist/partials/layout/footer.jsp"></footer>

	<!-- jQuery & Other js -->
	<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="dist/js/main.js"></script>
</body>
</html>