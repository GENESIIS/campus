<%-- 20161114 TR c25 start styling user profile page container  --%>
<%-- 20161114 TR c25 page header - done  --%>
<%-- 20161114 TR c25 profile-image-box - done  --%>
<%-- 20161227 MM c25-student-create-dashboard-MP Converted to JSP and converted relative paths to absolute paths --%>
<%-- 20161227 MM c25-student-create-dashboard-MP Moved JS files to the bottom of the page for better performance included the call to studentDashboard.js file --%>
<%-- 20161228 MM c25-student-create-dashboard-MP Removed static carousel template code that is replaced at runtime with dynamically produced element list --%>
<%-- 20161228 MM c25-student-create-dashboard-MP Removed static template code for institute list which, when running, 
				is replaced with dynamically produced institute list --%>
<%-- 20161229 MM c25-student-create-dashboard-MP Made minor modification to make it possible to get details from the session  --%>
<%-- 20161228 MM c25-student-create-dashboard-MP Removed static template code for activity list --%>
<%-- 20170104 MM c25-student-dashboard-MP Assigned dynamic content to items that display student biographical info --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="/dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/dist/css/style.css" rel="stylesheet">
    <link href="/dist/css/image-slides.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- W3-Include -->
    <!--<script src="/dist/bower-components/w3/w3data.js"></script> -->

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
                <a href="index.html" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">

                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="">All Courses</a></li>
                        <li><a href="">About Us</a></li>
                        <li><a href="">Contact Us</a></li>
                        <li><a href="dist/partials/student/student-dashboard.html">News</a></li>
                        <li><a href="">F & Q</a></li>
                        <li><a href="">Rss</a></li>
                    </ul>
                </div>
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <div class="bottom-menus" id="category-list">
                    <ul class="list-inline">
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" class="" value="LIST_CATEGORY_LANDING_PAGE">Pre School</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">School Education</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" class="" value="LIST_CATEGORY_LANDING_PAGE">Higher Education</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Corporate Training</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Vocational Training</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Talent & Skill</button>
                            </form>
                        </li>
                    </ul>
                </div>
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

<div class="dashboard">
    <div class="stud-dashboard clearfix">
       <div class="prf-page-header col-md-12 col-lg-12 col-sm-12">
           <h1>| User Profile </h1>
       </div>
        <!-- End page header -->

        <div class="prf-page-container col-md-12 col-lg-12 col-sm-12 col-xs-12 clearfix">
            <div class="left-side col-md-3 col-lg-3 col-sm-12 clearfix">
                <div class="prf-pic-holder">
                    <div class="prf-image">
                        <img src="/dist/i/student/prf-pic.jpg" alt="Profile-Picture">
                    </div>
                    <!-- End profile image -->

                    <div class="prf-name">
                    	<h2>{result.collection[0]} {result.collection[1]} <%-- {sessionScope.userFirstName} {sessionScope.userLastName} --%></h2>
                        <h3>{result.collection[4]} via {result.collection[5]}</h3>
                        <h4>{result.collection[3]}</h4>
                    </div>
                    <!-- End profile name -->
                    <div class="follow-me">
                        <button class="btn-follow">View Profile</button>
                    </div>
                    <!-- End follow-me button -->
                </div>
                <!-- end profile picture box -->

                <div class="short-info-holder">
                    <table class="tbl-left-info">
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>{result.collection[9]} at <span>{result.collection[8]}</span></p></td>
                        </tr>
                        <!-- End works at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Studied at <span>{result.collection[6]}</span></p></td>
                        </tr>
                        <!-- End studied at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Lives in <span>{result.collection[3]}</span></p></td>
                        </tr>
                        <!-- End lives in -->
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>From <span>Katugasthota, Kandy</span></p></td>
                        </tr>
                        <!-- End from -->
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Notes <br> <span class="sp-note">{result.collection[2]}</span></p></td>
                        </tr>
                        <!-- End Notes -->
                    </table>
                </div>
            </div>
            <!-- End Left side panel -->

            <div class="right-side col-md-9 col-lg-9 col-sm-12 clearfix">
                <div class="widget w-rec-courses clearfix">
                    <div class="widget-header">
                        <label>Recommended Courses</label>
                    </div>
                    <!-- End widget header -->

                    <div class="widget-content clearfix">

                        <div class="col-md-12" data-wow-delay="0.2s">
                            <div class="carousel slide" data-ride="carousel" id="quote-carousel">
                                <!-- Bottom Carousel Indicators -->
                                <!--<ol class="carousel-indicators">-->
                                    <!--<li data-target="#quote-carousel" data-slide-to="0" class="active"><img class="img-responsive " src="../../i/icbt.png" alt="">-->
                                    <!--</li>-->
                                    <!--<li data-target="#quote-carousel" data-slide-to="1"><img class="img-responsive" src="../../i/icbt.png" alt="">-->
                                    <!--</li>-->
                                    <!--<li data-target="#quote-carousel" data-slide-to="2"><img class="img-responsive" src="../../i/icbt.png" alt="">-->
                                    <!--</li>-->
                                <!--</ol>-->

                                <!-- Carousel Slides / Quotes -->
                                <div class="carousel-inner text-center">                                    
                                </div>

                                <!-- Carousel Buttons Next/Prev -->
                                <a data-slide="prev" href="#quote-carousel" class="left carousel-control"><i class="fa fa-chevron-left"></i></a>
                                <a data-slide="next" href="#quote-carousel" class="right carousel-control"><i class="fa fa-chevron-right"></i></a>
                            </div>
                        </div>


                        <!-- End about-me : right side -->
                    </div>
                    <!-- End widget content -->
                </div>
                <!-- End Widget : ABOUT -->

                <div class="widget w-rec-institute clearfix">
                    <div class="widget-header">
                        <label>Recommended Institutes</label>
                    </div>
                    <!-- End widget header -->

                    <div class="widget-content clearfix">
                        <div class="rec-institute clearfix">
                            <ul>
                            </ul>
                        </div>
                        <!-- End about-me : left side -->


                        <!-- End about-me : right side -->
                    </div>
                    <!-- End widget content -->
                </div>
                <!-- End Widget : ABOUT -->

                <div class="widget w-recent-activity clearfix">
                    <div class="widget-header">
                        <label>Recent Activity</label>
                    </div>
                    <!-- End widget header -->

                    <div class="widget-content">
                        <ul class="ul-activity">
						 </ul>
                    </div>
                    <!-- End widget content -->
                </div>
                <!-- End Widget : ABOUT -->


            </div>
            <!-- End Right side -->


        </div>
        <!-- End page container -->
    </div>
</div>
<!-- End Dashboard  -->

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/js/student/studentDashboard.js"></script>

<!-- Footer -->
<!--<footer w3-include-html="../layout/footer.html"></footer>-->
<footer>
    <div class="ft-top">

    </div>
    <div class="ft-bottom text-center">
        <label >Copyright © Campus.lk</label>
    </div>
</footer>

</body>
</html>