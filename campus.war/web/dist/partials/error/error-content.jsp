<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 	20170124 AS CAM-20 Error handle for created this page. This page created for view unauthorized access -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Content</title>
<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">

<script src="/dist/js/header/ui-populate-helper.js"></script>
<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/header/ui-populate-helper.js"></script>
</head>
<body>
	<header> <jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
	<jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>

	</header>
	<script type="text/javascript">
		$(window).scrollTop(0);
		$('#msg-popup').modal('show');
		setTimeout(
				function() {

					window.location.href = 'http://www.campus.dev:8080/index.jsp?showLogin=true'; //this name may have to change depend on actual location of the page "Student Login or public index page"

				}, 5000);
	</script>
	Sorry, you are not allowed to view content ......
</body>
</html>