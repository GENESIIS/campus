<!-- 20161109 JH index.jsp created  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<body onload="displayCategory()">

<!-- Header-->
<header> <jsp:include page="dist/partials/layout/header.jsp"></jsp:include> </header>

<!-- Main Container - Landing -->
<div w3-include-html="dist/partials/landing.html"></div>

<!-- Footer -->
<footer w3-include-html="dist/partials/layout/footer.html"></footer>

<!-- jQuery & Other js -->
<script src="dist/js/header/ui-populate-helper.js"></script>
<!--<script src="dist/bower-components/jquery/jquery.min.js"></script>-->
<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="dist/js/main.js"></script>

</body>
</html>