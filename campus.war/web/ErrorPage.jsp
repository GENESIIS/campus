<!-- 20170112 PN CAM-72 INIT test page to display error/exception details to the user. -->
<!-- 20170202 TR CAM-72 comment old error loading h1 -->
<!-- 20170202 TR CAM-72 update all body content -->
<!-- 20170202 TR CAM-72 added error-screen content and css -->
<!-- 20170202 TR CAM-94 added new error directory to i (i/error): for including all error images and icons -->
<!-- 20170202 TR CAM-94 added error-404.png to i/error -->
<!-- 20170202 PN CAM-72 integrated JSTL code into new UI design. -->
<!-- 20170214 PN CAM-72 implemented 'Back to Home' button. Modified error displaying JSTL coding. -->
<!-- 20170215 PN CAM-72 implemented 'Back to Previous Page' button, JS method redirectIntoPreviousPage()-->
<!-- 20170216 TR CAM-72 added all styles as internal -->
<!-- 20170216 PN CAM-72 error image path modified to load from C:\sdb\ctxdeploy\education.war\general\error\error-404.png. -->

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
<link href="../bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="dist/css/style.css" rel="stylesheet">
<link href="dist/css/image-slides.css" rel="stylesheet">

<!-- jQuery -->
<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/js/image-slides.js"></script>

<style>
body, html, h1, h2, h3, h4, h5, h6 {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
}

.clearfix {
	*zoom: 1;
}

.clearfix:before, .clearfix:after {
	display: table;
	content: "";
	line-height: 0;
}

.clearfix:after {
	clear: both;
}
/* Start: Error Page : 404 */
.error-screen {
	display: block;
	position: relative;
	background-color: #f7fcff;
	min-height: 850px;
}

.error-screen .error-container {
	text-align: center;
}

.error-screen .error-container .error-number {
	display: block;
	padding-top: 40px;
}

.error-screen .error-container .error-number h1 {
	margin: 0 !important;
	font-family: "Roboto", sans-serif;
	font-size: 280px;
	color: #e8f3f8;
	text-shadow: 14px 14px 0px rgba(0, 0, 0, 0.7);
	font-weight: 600;
}

.error-screen .error-container .error-number h1 span {
	color: #b7ced7;
}

.error-screen .error-container .error-image img {
	width: 155px;
	margin-top: 25px;
	margin-bottom: 20px;
}

.error-screen .error-container .default-msg {
	font-family: "Roboto", sans-serif;
	font-size: 70px;
	color: #e4bd06;
	line-height: 110px;
}

.error-screen .error-container .error-msg {
	font-family: "Abel", sans-serif;
	font-size: 45px;
	color: #7b7e7f;
}

.error-screen .error-container .home-btn {
	display: block;
	margin-bottom: 50px;
}

.error-screen .error-container .home-btn button {
	background-color: #1794c8;
	border: none;
	font-family: "Roboto", sans-serif;
	font-size: 22px;
	padding: 15px 40px;
	color: #fff;
	border-radius: 11px;
	margin-top: 30px;
}

.error-screen .error-container .home-btn button:hover {
	background-color: #09729e;
}

/* END: Error Page : 404 */

/* Styling Footer */
footer .ft-top {
	display: block;
	height: 175px;
	width: 100%;
	background: #193949;
}

footer .ft-bottom {
	display: block;
	width: 100%;
	height: 50px;
	background: #092a3b;
}

footer .ft-bottom label {
	font-size: 18px;
	line-height: 50px;
	color: #d6d6d6;
}
/* End Footer styling */
</style>

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
		<div
			class="error-container col-lg-12 col-md-12 col-sm-12 col-xs-12 clearfix">
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
				<img src="/education/general/error/error-404.png">
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
				<button onclick="redirectBacktoHome()">Back to Home</button>
				&nbsp;
				<button onclick="redirectIntoPreviousPage()">Back to
					Previous Page</button>
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