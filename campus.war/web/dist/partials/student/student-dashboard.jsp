<%-- 20161114 TR c25 start styling user profile page container  --%>
<%-- 20161114 TR c25 page header - done  --%>
<%-- 20161114 TR c25 profile-image-box - done  --%>
<%-- 20161203 MM c25-student-create-dashboard-MP Converted to JSP and converted relative paths to absolute paths --%>

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

    <!-- jQuery & Other js -->
    <script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="/dist/js/main.js"></script>
    <script src="/dist/js/image-slides.js"></script>
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
                        <h2>Kalana Perera</h2>
                        <h3>University of Colombo</h3>
                        <h4>Kandy</h4>
                    </div>
                    <!-- End profile name -->
                    <!--<div class="follow-me">-->
                        <!--<button class="btn-follow">Follow</button>-->
                    <!--</div>-->
                    <!-- End follow-me button -->
                </div>
                <!-- end profile picture box -->

                <div class="short-info-holder">
                    <table class="tbl-left-info">
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Senior Software Engineer at <span>Genesiis Software pvt Ltd.</span></p></td>
                        </tr>
                        <!-- End works at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Studied at <span>ICBT Colombo Campus</span></p></td>
                        </tr>
                        <!-- End studied at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Lives in <span>Colombo</span></p></td>
                        </tr>
                        <!-- End lives in -->
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>From <span>Katugasthota, Kandy</span></p></td>
                        </tr>
                        <!-- End from -->
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Notes <br> <span class="sp-note">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</span></p></td>
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

                                    <!-- Quote 1 -->
                                    <div class="item active">
                                        <blockquote>
                                            <div class="row">
                                                <div class="col-sm-8 col-sm-offset-2">
                                                    <label>BSc in Computer Science</label>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. !</p>
                                                    <small>ICBT</small>
                                                </div>
                                            </div>
                                        </blockquote>
                                    </div>
                                    <!-- Quote 2 -->
                                    <div class="item">
                                        <blockquote>
                                            <div class="row">
                                                <div class="col-sm-8 col-sm-offset-2">
                                                    <label>BSc in Electronic Engineering</label>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. </p>
                                                    <small>NIBM</small>
                                                </div>
                                            </div>
                                        </blockquote>
                                    </div>
                                    <!-- Quote 3 -->
                                    <div class="item">
                                        <blockquote>
                                            <div class="row">
                                                <div class="col-sm-8 col-sm-offset-2">
                                                    <label>BSc in Software Engineering</label>
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. .</p>
                                                    <small>SLIIT</small>
                                                </div>
                                            </div>
                                        </blockquote>
                                    </div>
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
                                <li class="col-lg-12 col-sm-12 col-md-12">
                                    <div class="inst-logo col-lg-3 col-sm-12 col-md-4"><img src="/dist/i/icbt.png" alt=""></div>
                                    <a href="javascript:" class="col-lg-9 col-sm-12 col-md-8">ICBT - International Campus of Bussiness and Technology</a>
                                </li>
                                <!-- End li-->
                                <li class="col-lg-12 col-sm-12 col-md-12">
                                    <div class="inst-logo col-lg-3 col-sm-12 col-md-4"><img src="/dist/i/icbt.png" alt=""></div>
                                    <a href="javascript:" class="col-lg-9 col-sm-12 col-md-8">ICBT - International Campus of Bussiness and Technology</a>
                                </li>
                                <!-- End li-->
                                <li class="col-lg-12 col-sm-12 col-md-12">
                                    <div class="inst-logo col-lg-3 col-sm-12 col-md-4"><img src="/dist/i/icbt.png" alt=""></div>
                                    <a href="javascript:" class="col-lg-9 col-sm-12 col-md-8">ICBT - International Campus of Bussiness and Technology</a>
                                </li>
                                <!-- End li-->
                                <li class="col-lg-12 col-sm-12 col-md-12">
                                    <div class="inst-logo col-lg-3 col-sm-12 col-md-4"><img src="/dist/i/icbt.png" alt=""></div>
                                    <a href="javascript:" class="col-lg-9 col-sm-12 col-md-8">ICBT - International Campus of Bussiness and Technology</a>
                                </li>
                                <!-- End li-->
                                <li class="col-lg-12 col-sm-12 col-md-12">
                                    <div class="inst-logo col-lg-3 col-sm-12 col-md-4"><img src="/dist/i/icbt.png" alt=""></div>
                                    <a href="javascript:" class="col-lg-9 col-sm-12 col-md-8">ICBT - International Campus of Bussiness and Technology</a>
                                </li>
                                <!-- End li-->
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
                            <li>Applied to BSc in Software Engineering at "Esoft Metro Campus" <br><span class="act-time">~ 20 March 2012 - Now </span></li>
                            <li>Applied to BSc in Software Engineering at "ICBT" <br><span class="act-time">~ 20 March 2012 - 18:52</span></li>
                            <li>Applied to BSc in Software Engineering at "Esoft Metro Campus" <br><span class="act-time">~ 20 March 2012 - 16:45</span></li>
                            <li>Applied to BSc in Software Engineering at "ICBT" <br><span class="act-time">~ 20 March 2012 - 10:50</span></li>
                            <li>Applied to BSc in Software Engineering at "Esoft Metro Campus" <br><span class="act-time">~ 20 March 2012 - 08:35</span></li>
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