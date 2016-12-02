<!-- 20161119 DN C18-student-signup-without-using-third-party-application-dn  created the page SignUpWoThirdParty.jsp -->
<!-- 20161123 DN C18-student-signup-without-using-third-party-application-dn /dist/js/jsonDataExchanger.js included to the page -->
<!-- 20161123 DN C18-student-signup-without-using-third-party-application-dn tool tip information for mobile number introduced -->
<!-- 20161201 DN C18-student-signup-without-using-third-party-application-dn add a tool tip to mobile number field -->


<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    Bootstrap & CSS Style
    <link href="../../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../bower-components/bootstrap/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/image-slides.css" rel="stylesheet">
    <link href="../../css/button-effect.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" media="all">

    W3-Include
    <script src="../../bower-components/w3/w3data.js"></script>

    jQuery & Other js
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
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
                    <a href="#">Login</a>

                </div>
            </div>
            <!-- End keyword search -->
        </div>
    </div>
</header>
<!--< End header -->

<!-- SignUp Screen -->
<div class="sign-up-screen clearfix">
    <div class="page-inner-header col-md-12 col-lg-12 col-sm-12">
        <h1>| Sign Up</h1>
        <h2>Give Us Your Basic Details.</h2>
    </div>
    <!-- End inner header -->

    <div class="page-content col-md-12 col-lg-12 col-sm-12 clearfix">

        <div class="social-link-panel col-md-6 col-lg-6 col-sm-12 clearfix">
            <div class="lbl-or">
                <label>OR</label>
            </div>
            <!-- End OR label -->
            <div class="signup-btn">
                <button class="btn-sign-up btn-fb fa-3x btn-image-change hvr-icon-float-away"><span>Join with Facebook</span></button>
            </div>
            <!-- End btn - Facebook -->
            <div class="signup-btn">
                <button class="btn-sign-up btn-gplus fa-3x btn-image-change hvr-icon-float-away"><span>Join with Google</span></button>
            </div>
            <!-- End btn- Google-plus -->
            <div class="signup-btn">
                <button class="btn-sign-up btn-in fa-3x btn-image-change hvr-icon-float-away"><span>Join with LinkedIn</span></button>
            </div>
            <!-- End btn- LinkedIn -->
            <div class="signup-btn">
                <button class="btn-sign-up btn-twitter fa-3x btn-image-change hvr-icon-float-away"><span>Join with Twitter</span></button>
            </div>
            <!-- End btn - Twitter -->


        </div>
        <!-- End Sign-up : with social media -->

        <div class="input-panel col-md-6 col-lg-6 col-sm-12 clearfix">
            <div class="input-field">
                <label>First Name :</label><br>
                <input class="text-field" type="text">
            </div>
            <!-- End first name -->

            <div class="input-field">
                <label>Last Name :</label><br>
                <input class="text-field" type="text">
            </div>
            <!-- End first name -->

            <div class="input-field gender-field col-lg-12 clearfix">
                <label class="lbl-gender col-lg-4 pull-left">Gender :</label>
                <div class="col-lg-4 pull-left"><input class="btn-radio" type="radio"><span>Male</span></div>
                <div class="col-lg-4 pull-left"><input class="btn-radio" type="radio"><span>Female</span></div>
            </div>
            <!-- End first name -->

            <div class="input-field">
                <label>Email :</label><br>
                <input class="text-field" type="text">
            </div>
            <!-- End first name -->

            <div class="input-field">
                <label>Phone :</label><br>
                <input class="text-field" type="text">
            </div>
            <!-- End first name -->

            <div class="input-field">
                <label>Town :</label><br>
                <input list="town" class="text-field" type="text">
                <datalist id="town">
                    <option >- Select Your Town -</option>
                    <option >Kurunegala</option>
                    <option >Kandy</option>
                </datalist>
            </div>
            <!-- End Town -->

            <div class="input-field">
                <label>Username :</label><br>
                <input class="text-field" type="text">
            </div>
            <!-- End first name -->

            <div class="input-field">
                <label>Password :</label><br>
                <input class="text-field" type="password">
                <div class="pull-right show-pwrd">
                    <span class="check-box"><input type="checkbox"></span>Show Password
                </div>
            </div>
            <!-- End first name -->

            <div class="input-field">
                <label>Confirm Password :</label><br>
                <input class="text-field" type="password">
            </div>
            <!-- End first name -->

            <div class="input-field">
                <span class="check-box"><input type="checkbox"></span>
                <p>I have read privacy policy and accept the terms and conditions.</p>
            </div>
            <!-- End Condition agreement -->

            <div class="input-field">
                <button class="btn-create">Create Account</button>
            </div>
            <!-- End submit button -->

        </div>
        <!-- End Sign-up : with input form -->

    </div>
</div>
<!-- End SignUp Screen  -->

<!-- Footer -->
<!--<footer w3-include-html="../layout/footer.html"></footer>-->
<footer>
    <div class="ft-top"></div>
    <div class="ft-bottom text-center">
        <label>Copyright © Campus.lk</label>
    </div>
</footer>

</body>
</html>