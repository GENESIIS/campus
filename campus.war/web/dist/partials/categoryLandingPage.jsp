<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- 20161027 TR c11 start styling courses filter result page --%>
<%-- 20161110 MM c5-corporate-training-landing-page-MP - Put-in pagination container --%>
<%-- 20161111 MM c5-corporate-training-landing-page-MP - Changed imported file to have absolute paths --%>
<%-- 20161111 MM c5-corporate-training-landing-page-MP - Added hidden inputs with names: categoryCode and categoryIdentifierString --%>
<%-- 20161125 MM c5-corporate-training-landing-page-MP - Removed the message container div that was put to hold messages, so there will 
														be minimal conflicts when integrating with c7 --%>
<%-- 20161125 MM c5-corporate-training-landing-page-MP - Added a div element with class 'programme-results-stat-info-div' just above 
														the pagination container to act as the container of a message that displays 
														what part of the result set is currently being displayed --%>
<%-- 20161126 MM c5-corporate-training-landing-page-MP - Modified code in <body> to remove header and footer code from this file and 
														instead used <jsp:include...> to include them --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="/dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/dist/css/style.css" rel="stylesheet">

    <!-- W3-Include -->
    <script src="/dist/bower-components/w3/w3data.js"></script>

</head>
<body>

<!-- Header-->
<jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
<!-- End Header -->

<!-- Main Container - Higher-Education -->
<div class="main-category clearfix">

    <!-- page inner header -->
    <div class="inner-header">
        <div class="category-image">
            <img src="/dist/i/higher-education/higher-edu.png" alt="">
        </div>
        <div class="category-name">
            <h1>| Higher Education</h1>
        </div>
    </div>
    <!-- end inner header -->

    <!-- Page content -->
    <div class="content-holder center-block">
        <div class="course-filter-panel">
            <div class="filtering-area">
                <div class="top"></div>
                <div class="bottom clearfix">
                    <ul class="list-inline">
                    </ul>
                </div>
            </div>

            <!-- Filter result table -->
            <div class="filter-result-table">
                <ul class="result-row">
                </ul>
                <div class="programme-results-stat-info-div text-center">
                </div>
                <div class="paginator-div text-center">
                	<nav aria-label="">
                		<ul class="pagination pagination-lg">
				  		</ul>
					</nav>                	
                </div>
            </div>
            <!-- End filter result table -->

        </div>

<%-- 
	For both of the following hidden fields, the values must be fetched from the DB and assigned.
	IMPORTANT: The value for categoryIdentifierString must correspond to categoryCode as present in the DB 
--%>
		<input type="hidden" name="categoryCode" id="categoryCode" value="${categoryCode}"/>
		<input type="hidden" name="categoryIdentifierString" id="categoryIdentifierString" value="${categoryIdentifierString}"/>
    </div>
    <!-- End page content  -->
</div>
<!-- End Main Container -->

<!-- Footer -->
<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
<!-- End Footer -->

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery.min.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/categoryLandingPage/categoryLandingPage.js"></script>

</body>
</html>