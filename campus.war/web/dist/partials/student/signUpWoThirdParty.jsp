<!-- 20161119 DN C18-student-signup-without-using-third-party-application-dn  created the page SignUpWoThirdParty.jsp -->
<!-- 20161123 DN C18-student-signup-without-using-third-party-application-dn /dist/js/jsonDataExchanger.js included to the page -->
<!-- 20161123 DN C18-student-signup-without-using-third-party-application-dn tool tip information for mobile number introduced -->
<!-- 20161201 DN C18-student-signup-without-using-third-party-application-dn add a tool tip to mobile number field -->
<!-- 20161204 DN C18-student-signup-without-using-third-party-application-dn integrated with GUI complete 60% -->
<!-- 20161205 DN C18-student-signup-without-using-third-party-application-dn Country field added -->
<!-- 20161206 DN C18-student-signup-without-using-third-party-application-dn town field and other nodes  added -->
<!-- 20161214 DN CAMP:18 rearranged the fields and add a input box to capture country code -->
<!-- 20161217 DN CAMP:18 Clear Data button is created-->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="../../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../bower-components/bootstrap/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/image-slides.css" rel="stylesheet">
    <link href="../../css/button-effect.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" media="all">

    <!-- W3-Include -->
    <!--<script src="../../bower-components/w3/w3data.js"></script> -->

    <!-- jQuery & Other js -->
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
	<script src="/dist/js/jsonDataExchanger.js"></script>
	<script src="/dist/js/signUpWoThirdParty.js"></script>
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
                        <li><a href="/dist/partials/contactUs.jsp">Contact Us</a></li>
                        <li><a href="/dist/partials/student/student-dashboard.html">News</a></li>
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
		<label id="displayLabel" style="color:#F39C12;" ></label>
        <div class="input-panel col-md-6 col-lg-6 col-sm-12 clearfix">
            <div class="input-field">
                <label for="input-firstName" id="firstNameLabel">First Name :<span>*</span></label><label id="firstNameError" style="color:#C70039;"></label><br>
                <input class="text-field" type="text" id="firstName" name="firstName" onclick="clearField('firstNameError')">
            </div>
            <!-- End first name -->

            <div class="input-field">
                <label for="input-lastName" id="lastNameLabel">Last Name :<span>*</span></label><label id="lastNameError" style="color:#C70039;"></label><br>
                <input class="text-field" type="text" id="lastName" name="lastName" onclick="clearField('lastNameError')" >
            </div>
            <!-- End Last name -->

            <div class="input-field gender-field col-lg-12 clearfix">
                <label class="lbl-gender col-lg-4 pull-left" for="input-gender" id ="genderLabel">Gender :</label><label id="genderError" style="color:#C70039;"></label><br>
                <div class="col-lg-4 pull-left"><input class="btn-radio" type="radio"  name="gender" value="1" checked ><span>Male</span></div>
                <div class="col-lg-4 pull-left"><input class="btn-radio" type="radio"  name="gender" value="0"><span>Female</span></div>
            </div>
            <!-- End Gender -->

            <div class="input-field">
                <label for="eMail" id="emilAddressLabel" >Email :<span>*</span></label><label id="emailError" style="color:#C70039;"></label></label><br>
                <input class="text-field" type="text" id="emailAddress" name="emailAddress"  onclick="clearField('emailError')">
            </div>
            <!-- End Email -->

		 	<div class="input-field">
		         <label>Country :</label><label id="countryError" style="color:#C70039;"></label><br><br>
		         <input list="countryList" id="country" name="country" class="text-field" type="text" placeholder="-- Select City --" onclick="clearField('countryError');" >
		          <datalist id="countryList" name="countryist">
		          </datalist>
		         </div>
            <!-- End Country -->
            
            <div class="input-field">
                <label>Town :</label><label id="townError" style="color:#C70039;"></label><br><br>
                <input id="town" name="town" list="townList" class="text-field" type="text" placeholder="-- Select Town --" onclick="clearField('townError')" >
                <datalist id="townList" name="townList">
                </datalist>
                <input type="hidden"  id="sTownCode" name="sTownCode"/>
            </div>
            <!-- End Town -->
            
            <div class="input-field">
                <label for="input-phoneNumber" id="contactNumberLabel">Mobile :<span>*</span></label><label id="phoneError" style="color:#C70039;"></label><br>
                <input class="text-field" type="text" id="mobileCountryCode" name="mobileCountryCode"  onclick="clearField('phoneError')" readonly title ="Country Code ,It Automatically Gets Filled Once You Select Your Contry" >
                <input class="text-field" type="text" id="contactNumber" name="contactNumber"  onclick="clearField('phoneError')" data-toggle="tooltip" title="Type Your Mobile Numebr Without The Leading Zero e.g 771234567 format" >
            </div>
            <script>
				$(document).ready(function(){
				    $('[data-toggle="tooltip"]').tooltip();   
				});
			</script>
            <!-- End phone -->

            <div class="input-field">
                <label id="usernameLabel" >Username :<span>*</span></label><label id="usernameError" style="color:#C70039;"></label><br>
                <input class="text-field" type="text" name="username" id="userName" onclick="clearField('usernameError')">
            </div>
            <!-- End User name -->

            <div class="input-field">
                <label id="passWordLabel">Password :<span>*</span></label><label id="passWordError" style="color:#C70039;"></label><br>
                <input class="text-field" type="password" name="psw" id="passWord" onclick="clearField('passWordError')">
                 <input type="hidden"  id="userTypeCode" name="userTypeCode" />
                <div class="pull-right show-pwrd">
                    <span class="check-box"><input type="checkbox" id="showpasscheckbox" title="Show the password as plain text" onclick="convertPassWordToString('showpasscheckbox','passWord','confrmpsw')" ></span>Show Password
                </div>
            </div>
            <!-- End Password name -->

            <div class="input-field">
                <label id="confPassWordLabel">Confirm Password :<span>*</span></label><label id="confPassWordError" style="color:#C70039;" ></label><br>
                <input class="text-field" type="password" name="confrmpsw" id="confrmpsw" onclick="clearField('confPassWordError')">
            </div>
            <!-- End Confirm password  -->

            <div class="input-field">
            	<label id="policyConfirmError" style="color:#C70039;" ></label><br>
                <span class="check-box"><input type="checkbox" id="policyConfirm" onclick="clearField('policyConfirmError');"></span>
                <p>I have read privacy policy and accept the terms and conditions.</p>
            </div>
            <!-- End Condition agreement -->

            <div class="input-field">
                <button class="btn-create" onclick=" return (sendSignUpCredentialsToBckEnd()); clearField('displayLabel');">Create Account</button>
                <button class="btn-create" onclick=" clearField('displayLabel');clearAllFields();">Clear Data</button>
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