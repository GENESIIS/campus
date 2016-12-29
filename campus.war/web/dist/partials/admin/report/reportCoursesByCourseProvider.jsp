<!-- 20161114 TR c25 start styling user profile page container  -->
<!-- 20161114 TR c25 page header - done  -->
<!-- 20161114 TR c25 profile-image-box - done  -->
<!-- 20161127 DJ  c51-report-courses-by-course-provider-MP-dj  search view for courses by course providers -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="../../../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../../css/style.css" rel="stylesheet">
    <link href="../../../css/image-slides.css" rel="stylesheet">

    <!-- W3-Include -->
    <!--<script src="../../bower-components/w3/w3data.js"></script> -->

    <!-- jQuery & Other js -->
    <script src="../../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="../../../js/main.js"></script>
    <script src="../../../js/image-slides.js"></script>
    <script src="/dist/js/report/courses-by-provider.js"></script>
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
                <a href="index.jsp" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">

                <!-- Main menu tabs -->
                <!--<div class="top-menus">-->
                    <!--<ul class="list-inline">-->
                        <!--<li><a href="">All Courses</a></li>-->
                        <!--<li><a href="">About Us</a></li>-->
                        <!--<li><a href="">Contact Us</a></li>-->
                        <!--<li><a href="dist/partials/student/student-dashboard.html">News</a></li>-->
                        <!--<li><a href="">F & Q</a></li>-->
                        <!--<li><a href="">Rss</a></li>-->
                    <!--</ul>-->
                <!--</div>-->
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <!--<div class="bottom-menus" id="category-list">-->
                    <!--<ul class="list-inline">-->
                        <!--<li>-->
                            <!--<form action="">-->
                                <!--<button type="submit" name="CCO" id="CCO" class="" value="LIST_CATEGORY_LANDING_PAGE">Pre School</button>-->
                            <!--</form>-->
                        <!--</li>-->
                        <!--<li>-->
                            <!--<form action="">-->
                                <!--<button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">School Education</button>-->
                            <!--</form>-->
                        <!--</li>-->
                        <!--<li>-->
                            <!--<form action="">-->
                                <!--<button type="submit" name="CCO" id="CCO" class="" value="LIST_CATEGORY_LANDING_PAGE">Higher Education</button>-->
                            <!--</form>-->
                        <!--</li>-->
                        <!--<li>-->
                            <!--<form action="">-->
                                <!--<button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Corporate Training</button>-->
                            <!--</form>-->
                        <!--</li>-->
                        <!--<li>-->
                            <!--<form action="">-->
                                <!--<button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Vocational Training</button>-->
                            <!--</form>-->
                        <!--</li>-->
                        <!--<li>-->
                            <!--<form action="">-->
                                <!--<button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Talent & Skill</button>-->
                            <!--</form>-->
                        <!--</li>-->
                    <!--</ul>-->
                <!--</div>-->
                <!-- End Course Category tabs -->
            </div>
            <div class="keyword-search pull-right">
                <div class="search-bar">
                    <input type="text" placeholder="Keyword Search">
                    <a href="javascript:" class="colr-white"></a>
                </div>
                <!-- End Keyword Search -->
                <div class="login-link">
                    <a href="javascript:">Login</a>

                </div>
            </div>
            <!-- End keyword search -->
        </div>
    </div>
</header>
<!--< End header -->

<!-- Admin Report :  Courses by course provider -->
<div class="report-screen clearfix">

    <div class="report-inline-header ">
        <h1>|   Courses By Course Provider </h1>
    </div>
    <!-- end inner header -->

    <div class="page-holder clearfix">
        <div class="input-area clearfix">
            <div class="ctg-name col-lg-3 col-sm-12 col-md-3">
                <label for="">Course Provider: </label>
            </div>
            <div class="ctg-input col-lg-9 col-md-9 col-sm-12">
                <!-- <input class="txt-course-provider" type="text"> -->
                
                <input class="txt-course-provider" type="text" name="providerlist" id="providerlist"
									list="providerName" placeholder="-- Select Provider --" />
								<datalist id="providerName" >
								</datalist>
            </div>
            <!-- first row -->

            <div class="ctg-name col-lg-3 col-sm-12 col-md-3">
                <label for="">Status: </label>
            </div>
            <div class="ctg-input col-lg-9 col-md-9 col-sm-12">
                <input class="btn-radio" type="radio"><span>Activate</span>
                <input class="btn-radio" type="radio"><span>Deactivate</span>
            </div>
            <!-- second row -->

            <div class="ctg-name col-lg-3 col-sm-12 col-md-3">
                <label for="">Start Date: </label>
            </div>
            <div class="ctg-input col-lg-9 col-md-9 col-sm-12">
                <input class="txt-date" type="text">
                <a href="javascript:">calender</a>
            </div>
            <!-- Third Row -->

            <div class="ctg-name col-lg-3 col-sm-12 col-md-3">
                <label for="">End Date: </label>
            </div>
            <div class="ctg-input col-lg-9 col-md-9 col-sm-12">
                <input class="txt-date" type="text">
                <a href="javascript:">calender</a>
            </div>
            <!-- Fourth Row -->
        </div>
        <!-- End input area -->

        <div class="courses-data-tbl-area">
            <div class="btn-actions">
                <div class="btn-left col-md-6 col-sm-12 col-lg-6">
                    <div class="btn-clear">
                        <input type="submit" value="Clear">
                    </div>
                    <!-- end btn-clear -->

                    <div class="btn-search">
                        <input type="submit" value="Search">
                    </div>
                    <!-- end btn-search -->
                </div>
                <!-- left side buttons -->

                <div class="btn-right col-md-6 col-sm-12 col-lg-6">
                    <div class="btn-print">
                        <input type="submit" value="Print">
                    </div>
                    <!-- end btn-print -->
                </div>
                <!-- End right side buttons -->
            </div>
            <!-- End action btn area -->

            <div class="results-count col-md-12 col-lg-12 col-sm-12">
                <label for="">50 Results</label>
            </div>

            <div class="data-tbl col-md-12 col-lg-12 col-sm-12">
                <table class="display">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Course Name </th>
                            <th>Description</th>
                            <th>Course Provider </th>
                            <th>Start Date </th>
                            <th>End Date </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>BSc in IT</td>
                            <td>4 Year degree for information technology </td>
                            <td>UOM</td>
                            <td>2017-01-01</td>
                            <td>2021-01-01</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>BSc in Mathematics</td>
                            <td>4 Year degree for information technology </td>
                            <td>UOM</td>
                            <td>2017-01-01</td>
                            <td>2021-01-01</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>BSc in IT</td>
                            <td>4 Year degree for information technology </td>
                            <td>UOM</td>
                            <td>2017-01-01</td>
                            <td>2021-01-01</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>BSc in Mathematics</td>
                            <td>4 Year degree for information technology </td>
                            <td>UOM</td>
                            <td>2017-01-01</td>
                            <td>2021-01-01</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>

    </div>

</div>
<!-- End Dashboard  -->

<!-- Footer -->
<!--<footer w3-include-html="../layout/footer.html"></footer>-->
<footer>
    <div class="ft-top">

    </div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright © Campus.lk</label>
    </div>
</footer>

</body>
</html>