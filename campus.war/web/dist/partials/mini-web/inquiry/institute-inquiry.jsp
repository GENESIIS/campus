<!-- 20161026 CM  c9-make inquiry for institute Created instituteInquiry.jsp -->
<!-- 20161027 CM  c9-make inquiry for institute Edited instituteInquiry.jsp -->
<!-- 20161028 CM  c9-make inquiry for institute Edited instituteInquiry.jsp -->
<!-- 20161028 CM  c9-make inquiry for institute added message attribute -->
<!-- 20161108 CM  c9-make inquiry for institute Edited instituteInquiry.jsp-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="/dist/js/institute/validation/validation.js"></script>
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
				<a href="../../../../index.html" class="btn-home center-block"></a>
			</div>
			<!-- End home button -->
			<div class="menu-tabs clearfix">
				<!-- Main menu tabs -->
				<div class="top-menus">
					<ul class="list-inline">
						<li><a href="../../courses.html">All Courses</a></li>
						<li><a href="../../about-us.html">About Us</a></li>
						<li><a href="../../contact-us.html">Contact Us</a></li>
						<li><a href="javascript:">News</a></li>
						<li><a
							href="dist/partials/mini-web/inquiry/institute-inquiry.jsp">F
								& Q</a></li>
						<li><a href="../../rss.html">Rss</a></li>
					</ul>
				</div>
				<!-- End Main menu tabs -->

				<!-- Course Category tabs -->
				<div class="bottom-menus">
					<ul class="list-inline">
						<li><a href="javascript:">Pre Education</a></li>
						<li><a href="javascript:">School Education</a></li>
						<li><a href="../../category/higher-education.html">Higher
								Education</a></li>
						<li><a href="javascript:">Corporate Training</a></li>
						<li><a href="javascript:">Vocational Training</a></li>
						<li><a href="javascript:">Talent & Skill</a></li>
					</ul>
				</div>
				<!-- End Course Category tabs -->
			</div>
			<div class="keyword-search pull-right">
				<div class="search-bar">
					<input type="text" placeholder="Keyword Search"> <a
						href="javascript:" class="colr-white">Enter</a>
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

	<!-- Main Container - Course-Inquiry -->
	<!-- Inquiry Form Screen -->
	<div class="inquiry-screen clearfix">
		<div class="inner-header">
			<div class="main-topic">
				<h1>Feel free to talk to us</h1>
			</div>
		</div>
		<div class="page-topic">
			<h2 class="page-topic-t1">| Course Inquiry Form</h2>
		</div>

		<!-- image header -->
		<div class="inner-image-header">
			<div class="bg-image"></div>
		</div>
		<!-- END inner-image-header -->

		<!-- Inquiry Form -->
		<div class="inquiry-form-holder clearfix">
			<div class="inquiry-form clearfix">
				<!-- Inquiry form - Filing Area -->
				<div class="submit-area center-block clearfix">
				<!-- 	<h1 class="page-topic-t2">
						You are making inquiry about <span class="colr-white">ICBT</span>
					</h1> -->
					<form class="submit-form" method="POST"
						onsubmit="return (validateInstituteInquiryFileds())"
						action="../../../../InstituteController">
						<input type="hidden" value="1" name="courseProviderCode">
						<%-- <input type="hidden" value="${param.courseProviderCode}" name="courseProviderCode"> --%>
						<input type="hidden" value="1" name="studentCode">
						<div class="f-name">
							<label for="message"><c:out value="${message }"></c:out></label><br>
						</div>
						<!-- First Name -->
						<div class="f-name">
							<label for="input-firstName">Full Name <span>*</span>
							</label> <label id="fullNameError"></label><br> <input type="text"
								name="fullname" id="input-firstName" required>
						</div>
						<!-- Email -->
						<div class="email">
							<label for="eMail">Email <span>*</span></label><label
								id="emailError"></label><br> <input type="text"
								name="email" id="eMail" required>
						</div>
						<!--Country Code -->
						<div class="county-code">
							<label for="input-county-code">Country Code <span>*</span></label><label
								id="countryCodeError"></label><br> <input type="text"
								name="countryCode" id="input-county-code" required>
						</div>
						<!-- Area Code -->
						<div class="area-code">
							<label for="input-area-code">Area Code <span>*</span></label><label
								id="areaCodeError"></label><br> <input type="text"
								name="areaCode" id="input-area-code" required>
						</div>
						<!-- Telephone -->
						<div class="tp-number">
							<label for="input-tp-no">Telephone No <span>*</span></label><label
								id="telNumError"></label><br> <input type="text"
								name="telNum" id="input-tp-no">
						</div>
						<!-- Enquiry Title -->
						<div class="inquiry-title">
							<label for="input-inquiry-title">Inquiry Title <span>*</span></label><label
								id="inquiryTitleError"></label><br> <input type="text"
								name="inquiryTitle" id="input-inquiry-title" required>
						</div>
						<!-- Message -->
						<div class="inquiry-message">
							<label for="text-userMessage">Message <span>*</span></label><label
								id="inquiryError"></label><br>
							<textarea name="inquiry" id="text-userMessage" rows="10" required></textarea>
							<p class="pull-right">
								<span>*</span> Required fields
							</p>
						</div>
						<!-- ReCaptcha -->
						<div class="re-captcha">
							<span id="captchaError"
								style="color: red; font-weight: normal !important;"></span>
							<div class="g-recaptcha"
								data-sitekey="6LfDaQoUAAAAAJ9EWto6h6Dsd3TtQC1PcGFhc__c"></div>
						</div>
						<!-- btn Submit -->
						<button class="btn-submit" type="submit" name="CCO" id="CCO"
							value="SII" class="pure-button pure-button-primary">Send
							Inquiry</button>

					</form>
					<!-- End Submit form -->
				</div>
				<!-- End Inquiry form - Filing Area -->
			</div>
		</div>
		<!-- END inquiry-form-holder -->
	</div>
	<!-- End -->
	<!-- End main container -->

	<!-- Footer -->
	<footer w3-include-html="layout/footer.jsp"></footer>

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>

</body>

</html>