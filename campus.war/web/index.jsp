<!-- 20161028 TR c1 setup project structure -->
<!-- 20161028 TR c1 setup project structure - push to c1 -->
<!-- 20161103 DN c10-contacting-us-page added the java_script to the page  -->
<!-- 20161108 DN c10-contacting-us-page-MP-dn added taglib urls  -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
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

</head>
<body>

<!-- Header-->
<header w3-include-html="dist/partials/layout/header.jsp"></header>

<!-- Main Container - Landing -->
<div w3-include-html="dist/partials/landing.html"></div>

<!-- Footer -->
<footer w3-include-html="dist/partials/layout/footer.jsp"></footer>

<!-- jQuery & Other js -->
<!--<script src="dist/bower-components/jquery/jquery.min.js"></script>-->
<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="dist/js/main.js"></script>

</body>
</html>