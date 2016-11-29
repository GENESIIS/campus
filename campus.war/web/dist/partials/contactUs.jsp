
<!-- 20161028 TR c1 setup project structure -->
<!-- 20161028 TR c1 setup project structure - push to c1 -->
<!-- 20161103 DN c10-contacting-us-page added the java_script to the page  -->
<!-- 20161103 DN c10-contacting-us-page added the java_script to the page  -->
<!-- 20161109 DN c10-contacting-us-page-MP added the java_script to the page onsubmit fom function refactor recapture inserted  -->
<!-- 20161111 DN c10-contacting-us-page-MP added clearField(elementId) method-->
<!--20161116 DN c10-contacting-us-page-MP included validation.js file to view -->

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
    <link href="/dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/dist/css/style.css" rel="stylesheet">

    <!-- W3-Include -->
    <script src="/dist/bower-components/w3/w3data.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
    <script src="/dist/js/contactUs.js"></script>

</head>
<body>

<header class="header col-lg-12 col-md-12 col-sm-12 clearfix">
    <div class="top">
        <div class="logo-brand">
            <h1 class="logo-txt">Campus.lk</h1>
        </div>
    </div>
    <div class="bottom">
        <div class="menu-bar">
            <div class="home pull-left">
                <a href="../../index.jsp" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">
                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="courses.html">All Courses</a></li>
                        <li><a href="about-us.html">About Us</a></li>
                        <li><a href="dist/partials/contactUs.jsp">Contact Us</a></li>
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

<!-- Main Container - Contact-US -->
<div class="contact-us-screen clearfix">
    <div class="inner-header">
        <div class="main-topic">
            <h1>Feel free to talk to us</h1>
        </div>
    </div>
    <div class="page-topic">
        <h2 class="page-topic-t1">| Contact Us </h2>
    </div>

    <!-- image header -->
    <div class="inner-image-header">
        <div class="bg-image"></div>
    </div>
    <!-- END inner-image-header -->

    <!-- Contact Form -->
    <div class="contact-form-holder clearfix">
        <div class="contact-form clearfix">
            <!-- Contact Us form - Filing Area -->
            <div class="submit-area clearfix">
            <label id="" style="color:#F39C12;" >${requestScope.message}</label>
                <form class="submit-form" method="post"  name="contactUsForm"  onsubmit="return (validateForm())"  action="../../PublicController" >
                    <div class="f-name">
                        <label for="input-firstName">First Name <span>*</span></label><br><label id="firstNameError" style="color:#FFFF00;"></label><br>
                        <input type="text" id="firstName" name="firstName" onclick="clearField('firstNameError')">
                    </div>
                    <div class="l-name">
                        <label for="input-lastName">Last Name <span>*</span></label><br><label id="lastNameError" style="color:#FFFF00;"></label><br>
                        <input type="text" id="lastName" name="lastName" onclick="clearField('lastNameError')" >
                    </div>
                    <div class="tp">
                        <label for="input-phoneNumber">Phone Number<span>*</span></label><br><label id="phoneNumberError" style="color:#FFFF00;"></label><br>
                        <input type="text" id="contactNumber" name="contactNumber" onclick="clearField('phoneNumberError')" >
                    </div>
                    <div class="email">
                        <label for="eMail">Email <span>*</span></label><br><label id="emailError" style="color:#FFFF00;"></label><br>
                        <input type="text" id="emailAddress" name="emailAddress" onclick="clearField('emailError')" >
                    </div>
                    <div class="email-subject">
                        <label for="input-eMailSubject">Subject <span>*</span></label><br><label id="subjectError" style="color:#FFFF00;"></label><br>
                        <input type="text" id="subject" name="subject" onclick="clearField('subjectError')">
                    </div>
                    <div class="user-message">
                        <label for="text-userMessage">Message <span>*</span></label><br><label id="userMessageError" style="color:#FFFF00;"></label><br>
                        <textarea id="message" rows="10" name="message" onclick="clearField('userMessageError')" ></textarea>
                        <p class="pull-right"><span>*</span> Required fields</p>
                    </div>
<!--                     ReCaptcha -->
					<div class="re-captcha">
                    	<div class="g-recaptcha"
								data-sitekey="6LfDaQoUAAAAAJ9EWto6h6Dsd3TtQC1PcGFhc__c">
						</div>
						<br><label id="captureError" style="color:#FFFF00;"></label>
					</div>	
                    <button class="btn-submit" type="submit" name="CCO" id="CCO" value="FBTSA" >Submit Query</button>
					 <!--validateForm() FBTSA: FEED BACK TO SUPER ADMIN -->
                </form>
                <!-- End Submit form -->
            </div>
            <!-- End Contact Us form - Filing Area -->

            <!-- Q & A Area -->
            <div class="question-and-info-area clearfix">
                <div class="question-area">
                    <div class="heading clearfix">
                        <h1 class="page-topic-t2">Frequently asked questions</h1>
                        <p>Plaese be kind enough to read out FAQ before sending us a message </p>
                    </div>
                    <div class="number-of-questions">
                        <div class="question flip">
                            <h3><span>+</span>Question 01</h3>
                        </div>
                        <div class="answer slideable">
                            <p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.</p>
                        </div>
                    </div>
                    <!-- End Number-of-questions -->

                    <div class="number-of-questions">
                        <div class="question flip">
                            <h3><span>+</span>Question 02</h3>
                        </div>
                        <div class="answer slideable">
                            <p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.</p>
                        </div>
                    </div>
                    <!-- End Number-of-questions -->

                    <div class="number-of-questions">
                        <div class="question flip">
                            <h3><span>+</span>Question 03</h3>
                        </div>
                        <div class="answer slideable">
                            <p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.</p>
                        </div>
                    </div>
                    <!-- End Number-of-questions -->
                </div>
                <!-- End Question Area -->

                <!-- Information Area -->
                <div class="info-area clearfix">
                    <div class="heading">
                        <h1 class="page-topic-t1 colr-white">Information</h1>
                    </div>
                    <div class="address">
                        <p>Genesiis Software (pvt) Ltd. <br>Park Street <br>Colombo 07 <br>Sri Lanka.</p>
                    </div>

                    <div class="location" id="map">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3960.7755901309197!2d79.85629321530695!3d6.917411095001903!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae2596c8f0d9dd1%3A0xd40004ef11f25f9c!2sGenesiis+Software+(Pvt)+Ltd!5e0!3m2!1sen!2slk!4v1478002827125"  frameborder="0" allowfullscreen></iframe>
                    </div>

                    <div class="contact-info">
                        <a class="email" href="javascript:">info@genesiis.com</a>
                        <p class="tp">+94 11 7765 600<br>+94 11 2385 872</p>
                        <p class="fax">+94 11 2329 868</p>
                    </div>
                </div>
            </div>
            <!-- End Q & A Area -->
        </div>
    </div>
    <!-- END Contact-form-holder -->
</div>
<!-- End Container - Contact-Us -->

<!-- Footer -->
<!-- <footer w3-include-html="layout/footer.jsp"></footer> -->
<footer>
    <div class="ft-top">

    </div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright © Campus.lk</label>
    </div>
</footer>

<!-- jQuery & Other js -->
<script src="../bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="../bower-components/bootstrap/bootstrap.min.js"></script>
<script src="../js/main.js"></script>

</body>
</html>