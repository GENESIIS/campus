<!-- 20170112 PN CAM-72 INIT test page to display error/exception details to the user. -->
<!-- 20170202 TR CAM-72 comment old error loading h1 -->
<!-- 20170202 TR CAM-72 update all body content -->
<!-- 20170202 TR CAM-72 added error-screen content and css -->
<!-- 20170202 TR CAM-94 added new error directory to i (i/error): for including all error images and icons -->
<!-- 20170202 TR CAM-94 added error-404.png to i/error -->
<!-- 20170202 PN CAM-72 integrated JSTL code into new UI design. -->
<!-- 20170214 PN CAM-72 implemented 'Back to Home' button. Modified error displaying JSTL coding. -->
<!-- 20170215 PN CAM-72 implemented 'Back to Previous Page' button, JS method redirectIntoPreviousPage()-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="dist/css/style.css" rel="stylesheet">
<link href="dist/css/image-slides.css" rel="stylesheet">

<!-- jQuery -->
<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/js/image-slides.js"></script>

</head>
<body>
	<!-- start error screen -->
	<div class="error-screen clearfix">
		<c:forEach var="errorDetails" items="${result.collection}">
			<c:set var="statusCode" value="${errorDetails[0]}"></c:set>
			<br>
			<c:set var="errorMessage" value="${errorDetails[1]}"></c:set>
			<br>
		</c:forEach>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 clearfix">
			<div class="error-number">
				<h1 class="text-center show">
					<c:out value="${fn:substring(statusCode, 0, 1)}"></c:out>
					<span class=""><c:out
							value="${fn:substring(statusCode, 1, fn:length(statusCode)-1)}"></c:out>
					</span>
					<c:out
						value="${fn:substring(statusCode, fn:length(statusCode)-1, fn:length(statusCode))}"></c:out>
				</h1>
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
				<c:if test="${not empty errorMessage}">
					<h3 class="text-center show">
						<c:out value="${errorMessage}"></c:out>
					</h3>
				</c:if>
			</div>
			<!-- End error image  -->

			<div class="home-btn text-center">
				<button onclick="redirectBacktoHome()">Back to Home</button> &nbsp;	<button onclick="redirectIntoPreviousPage()">Back to Previous Page</button>
			</div>
			<!-- End error image  -->
		</div>
	</div>
	<!-- End error screen -->

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->

	<!-- Other js -->
	<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>

	<!-- CAM-72: Below scripts are to handle page redirections after an error -->
	<script type="text/javascript">
		//Go back to index.jsp page.
		function redirectBacktoHome() {
			window.location = "/index.jsp";
		}
		//Go back to previous page where user located before the error page.
		function redirectIntoPreviousPage() {
			window.history.back();
		}
	</script>
</body>
</html>