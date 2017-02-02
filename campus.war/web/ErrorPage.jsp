<!-- 20170112 PN CAM-72 INIT test page to display error/exception details to the user. -->
<!-- 20170202 TR CAM-72 comment old error loading h1 -->
<!-- 20170202 TR CAM-72 update all body content -->
<!-- 20170202 TR CAM-72 added error-screen content and css -->
<!-- 20170202 TR CAM-94 added new error directory to i (i/error): for including all error images and icons -->
<!-- 20170202 TR CAM-94 added error-404.png to i/error -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="dist/css/style.css" rel="stylesheet">
    <link href="dist/css/image-slides.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="dist/js/image-slides.js"></script>

</head>
<body>
    <!-- start error screen -->
	<div class="error-screen clearfix">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 clearfix">
            <div class="error-number">
                <h1 class="text-center show">4<span class="">0</span>4</h1>
            </div>
            <!-- End error number  -->

            <div class="error-image text-center">
                <img src="dist/i/error/error-404.png">
            </div>
            <!-- End error image  -->

            <div class="default-msg">
                <h2 class="text-center show">Oops!</h2>
            </div>
            <!-- End error image  -->

            <div class="error-msg">
                <h3 class="text-center show">Page Not Found.</h3>
            </div>
            <!-- End error image  -->

            <div class="home-btn text-center">
                <button>Back to Home</button>
            </div>
            <!-- End error image  -->
		</div>
	</div>
	<!-- End error screen -->

	<!--  
	<h1>Hi....!!!! I'm your default Error Page. Seems like something
		went wrong.</h1>
	<c:forEach var="errorDetails" items="${result.collection}">
		<c:out value="${errorDetails[0]}"></c:out><br>
		<c:out value="${errorDetails[1]}"></c:out><br>
		<c:out value="${errorDetails[2]}"></c:out><br>
		<c:out value="${errorDetails[3]}"></c:out><br>
		<c:out value="${errorDetails[4]}"></c:out><br>
		<c:out value="${errorDetails[5]}"></c:out><br>
		<c:out value="${errorDetails[6]}"></c:out><br>
	</c:forEach>
	-->
	
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer --> 
	
	<!-- Other js -->
	<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<!-- 
	<script src="dist/js/main.js"></script>
	<script src="dist/js/header/ui-populate-helper.js"></script> 
	-->
</body>
</html>