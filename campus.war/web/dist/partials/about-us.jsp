<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 20161027 TR c11 start styling courses filter result page -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <!-- W3-Include -->
    <script src="../bower-components/w3/w3data.js"></script>

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
                <a href="../../index.html" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">
                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="courses.html">All Courses</a></li>
                        <li><a href="javascript:">About Us</a></li>
                        <li><a href="contact-us.html">Contact Us</a></li>
                        <li><a href="news.html">News</a></li>
                        <li><a href="f-and-q.html">F & Q</a></li>
                        <li><a href="rss.html">Rss</a></li>
                    </ul>
                </div>
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <div class="bottom-menus">
                    <ul class="list-inline">
                        <li><a href="javascript:">Pre Education</a></li>
                        <li><a href="javascript:">School Education</a></li>
                        <li><a href="category/higher-education.html">Higher Education</a></li>
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

<!-- Main Container - About-Us -->
<div> About-Us </div>

<!-- Footer -->
<footer w3-include-html="layout/footer.html"></footer>

<!-- jQuery & Other js -->
<script src="../bower-components/jquery/jquery.min.js"></script>
<script src="../bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="../bower-components/bootstrap/bootstrap.min.js"></script>
<script src="../js/main.js"></script>

</body>
</html>