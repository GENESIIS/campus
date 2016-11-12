<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- 20161027 TR c11 start styling courses filter result page --%>
<%-- 20161110 MM c5-corporate-training-landing-page-MP - Put-in pagination container --%>
<%-- 20161111 MM c5-corporate-training-landing-page-MP - Changed imported file to have absolute paths --%>
<%-- 20161111 MM c5-corporate-training-landing-page-MP - Added hidden fields with names: categoryCode and categoryIdentifierString --%>

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
<header class="header col-lg-12 col-md-12 col-sm-12 clearfix">
    <div class="top">
        <div class="logo-brand">
            <h1 class="logo-txt">Campus.lk</h1>
        </div>
    </div>
    <div class="bottom">
        <div class="menu-bar">
            <div class="home pull-left">
                <a href="../../../index.html" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">
                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="../courses.html">All Courses</a></li>
                        <li><a href="../about-us.html">About Us</a></li>
                        <li><a href="../contact-us.html">Contact Us</a></li>
                        <li><a href="../news.html">News</a></li>
                        <li><a href="../f-and-q.html">F & Q</a></li>
                        <li><a href="../rss.html">Rss</a></li>
                    </ul>
                </div>
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <div class="bottom-menus">
                    <ul class="list-inline">
                        <li><a href="javascript:">Pre Education</a></li>
                        <li><a href="javascript:">School Education</a></li>
                        <li><a href="javascript:">Higher Education</a></li>
                        <li><a href="javascript:">Corporate Training</a></li>
                        <li><a href="javascript:">Vocational Training</a></li>
                        <li><a href="javascript:">Talent & Skill</a></li>
                    </ul>
                </div>
                <!-- End Course Category tabs -->
            </div>
            <div class="keyword-search pull-right">
                <div class="search-bar">
                    <input type="text" placeholder="Keyword Search">
                    <a href="javascript:" class="colr-white">Enter</a>
                </div>
                <!-- End Keyword Search -->
                <div class="login-link">
                    <a href="javascript:" class="colr-white">Login</a>
                </div>
            </div>
            <!-- End keyword search -->
        </div>
    </div>
</header>
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
                <div class="paginator-div text-center">
                	<nav aria-label="">
                		<ul class="pagination pagination-lg">
				  		</ul>
					</nav>                	
                </div>
            </div>
            <!-- End filter result table -->

        </div>

<%-- Values in the following hidden fields are hard-coded for the moment. At integration, they are to be dynamically 
	assigned. 
	IMPORTANT: The value for categoryIdentifierString must correspond to categoryCode as present in the DB 
--%>
		<input type="hidden" name="categoryCode" id="categoryCode" value="3"/>
		<input type="hidden" name="categoryIdentifierString" id="categoryIdentifierString" value="CORPORATE_TRAINING"/>
    </div>
    <!-- End page content  -->
</div>
<!-- End Main Container -->

<!-- Footer -->
<!-- <footer w3-include-html="layout/footer.html"></footer>  -->

<footer>
    <div class="ft-top">

    </div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright © Campus.lk</label>
    </div>
</footer>

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery.min.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/categoryLandingPage/categoryLandingPage.js"></script>

</body>
</html>