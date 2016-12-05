<!-- 20161109 JH index.jsp created  -->
<!-- 20161117 JH code modified to include footer and landing pages -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="dist/css/style.css" rel="stylesheet">

<!-- W3-Include -->
<script src="dist/bower-components/w3/w3data.js"></script>

<!-- custom javascript -->
<script src="/dist/js/header/ui-populate-helper.js"></script>
</head>
<body>

	<!-- Header-->
	<header>
		<jsp:include page="dist/partials/layout/header.jsp"></jsp:include>
	</header>

	<!-- Main Container - Landing -->
	<div>
		<jsp:include page="dist/partials/landing.jsp"></jsp:include>
	</div>

	<!-- Footer -->
	<footer>
		<jsp:include page="dist/partials/layout/footer.jsp"></jsp:include>
	</footer>

	<!-- jQuery & Other js -->
	<!--<script src="dist/bower-components/jquery/jquery.min.js"></script>-->
	<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="dist/js/main.js"></script>

</body>
</html>