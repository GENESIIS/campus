<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="dist/css/style.css" rel="stylesheet">
    <link href="dist/css/image-slides.css" rel="stylesheet">

    <!-- W3-Include -->
    <script src="dist/bower-components/w3/w3data.js"></script>
    <script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="dist/js/image-slides.js"></script>

</head>
<body>

<!-- Header-->
<!--<header w3-include-html="dist/partials/layout/header.jsp"></header>-->

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
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="">All Courses</a></li>
                        <li><a href="">About Us</a></li>
                        <li><a href="">Contact Us</a></li>
                        <li><a href="dist/partials/student/student-dashboard.jsp">News</a></li>
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
                        <li>
                            <a style="color: #fff;" href="dist/partials/student/student-profile.jsp" class="button">Student Profile</a>
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
<!-- End Header -->
<!-- Main Container - Landing -->
<!--<div w3-include-html="dist/partials/landing.jsp"></div>-->

<div class="main-screen clearfix">
    <div class="content-panel clearfix">
        <div class="main-slider clearfix">
            <div class="slider-mask clearfix"></div>
            <!--<ul class="rslides" id="slider1">-->
                <!--<li><img src="dist/i/slider/slide-1.jpg" alt=""></li>-->
                <!--<li><img src="dist/i/slider/slide-2.jpg" alt=""></li>-->
                <!--<li><img src="dist/i/slider/slide-3.jpg" alt=""></li>-->
            <!--</ul>-->
            <div class="callbacks_container">
                <ul class="rslides" id="slider1">
                    <li><img src="dist/i/slider/slide-1.jpg" alt=""></li>
                    <li><img src="dist/i/slider/slide-2.jpg" alt=""></li>
                    <li><img src="dist/i/slider/slide-7.jpg" alt=""></li>
                    <li><img src="dist/i/slider/slide-8.jpg" alt=""></li>
                    <li><img src="dist/i/slider/slide-5.jpg" alt=""></li>
                    <li><img src="dist/i/slider/slide-6.jpg" alt=""></li>
                </ul>
                <!--<a href="#" class="callbacks_nav callbacks4_nav prev">Previous</a>-->
                <!--<a href="#" class="callbacks_nav callbacks4_nav next">Next</a>-->
            </div>
            <!-- End main intro slider -->
        </div>
        <!-- End Main slider -->
        <div class="search-area clearfix">
            <div class="main-search-bar center-block clearfix">
                <div class="bar-holder center-block clearfix">
                    <input class="center-block" type="text" placeholder="Search : Program, Course, or Career   e.g: accounting, accountant ">
                </div>
            </div>
            <!-- End Main search bar -->

            <div class="filter-boxes clearfix">
                <div class="box-holder center-block">
                    <ul class="list-inline">
                        <!-- Pre Education -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Pre Education</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- School Education -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">School Education</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Higher Education -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Higher Education</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Corporate Training -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Corporate Training</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Vocational Training -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Vocational Training</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <!-- Talent & Skill -->
                        <li>
                            <div class="category-box clearfix">
                                <div class="category-name text-center clearfix">
                                    <a href="javascript:">Talent & Skill</a>
                                </div>
                                <div class="course-info clearfix">
                                    <div class="image pull-left">
                                        <img class="center-block" src="dist/i/pre-school.jpg" alt="">
                                    </div>
                                    <div class="description pull-right">
                                        <p class="text-justify">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy </p>
                                        <a href="javascript:" class="pull-right">Show More</a>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
            <!-- End category boxes area -->
        </div>
    </div>

    <div class="banner-panel pull-right clearfix">
        <!--Advertise Here-->
    </div>
</div>

<!-- Footer -->
<!--<footer w3-include-html="dist/partials/layout/footer.jsp"></footer>-->
<footer>
    <div class="ft-top">

    </div>
    <div class="ft-bottom text-center">
        <label >Copyright � Campus.lk</label>
    </div>
</footer>

<!-- jQuery & Other js -->
<!--<script src="dist/bower-components/jquery/jquery.min.js"></script>-->
<!--<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>-->
<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="dist/js/main.js"></script>
<!--<script src="dist/js/header/ui-populate-helper.js"></script>-->

</body>
</html>