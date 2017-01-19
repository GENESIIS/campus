
<!-- 20161028 TR c1 setup project structure -->
<!-- 20161028 TR c1 setup project structure - push to c1 -->
<!-- 20161103 DN c10-contacting-us-page added the java_script to the page  -->
<!-- 20161103 DN c10-contacting-us-page added the java_script to the page  -->
<!-- 20161109 DN c10-contacting-us-page-MP added the java_script to the page onsubmit fom function refactor recapture inserted  -->
<!-- 20161111 DN c10-contacting-us-page-MP added clearField(elementId) method-->
<!--20161116 DN c10-contacting-us-page-MP included validation.js file to view -->
<!--20161122 DN c10-contacting-us-page-MP included mailto and jstl code to fill the fields -->
<!--20161128 DN c10-contacting-us-page-MP  clearField('warningLabel') added to submit button and message displaying label has been given an id='warningLabel'  -->
<!-- 20161128 DN c10-contacting-us-page-MP removed hard coded footer and header and use jsp include to include those jsps -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">

<!-- JQUERY & OTHER JS -->

<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/image-slides.js"></script>


<!-- W3-Include -->
<!--     <script src="/dist/bower-components/w3/w3data.js"></script> -->
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="/dist/js/institute/validation/validation.js"></script>
<script src="/dist/js/contactUs.js"></script>





</head>
<body>
	<!-- End Header -->
	<header>
	
		<jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
		<%-- <jsp:include page="layout/header.jsp"></jsp:include> --%>
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
			<h2 class="page-topic-t1">| Contact Us</h2>
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
					<label id="warningLabel" style="color: #F39C12;">${requestScope.message}</label>
					<c:forEach var="contactListValues" items="${result.collection}">
						<c:forEach var="individualElement" items="${contactListValues}"
							varStatus="loop">
							<c:choose>
								<c:when test="${loop.index eq 0}">
									<c:set var="fistName" value="${individualElement}" />
								</c:when>
								<c:when test="${loop.index eq 1}">
									<c:set var="lastName" value="${individualElement}" />
								</c:when>
								<c:when test="${loop.index eq 2}">
									<c:set var="emailNumber" value="${individualElement}" />
								</c:when>
								<c:when test="${loop.index eq 3}">
									<c:set var="phoneNumber" value="${individualElement}" />
								</c:when>
								<c:when test="${loop.index eq 4}">
									<c:set var="subjectText" value="${individualElement}" />
								</c:when>
								<c:when test="${loop.index eq 5}">
									<c:set var="messageText" value="${individualElement}" />
								</c:when>
							</c:choose>
						</c:forEach>
					</c:forEach>
					<form class="submit-form" method="post" name="contactUsForm"
						onsubmit="return (validateForm())" action="../../PublicController">

						<div class="f-name">
							<label for="input-firstName">First Name <span>*</span></label><br>
							<label class="msg-warning" id="firstNameError""></label><br>
							<input type="text" id="firstName" name="firstName"
								onclick="clearField('firstNameError')" value="${fistName}">
						</div>
						<div class="l-name">
							<label for="input-lastName">Last Name <span>*</span></label><br>
							<label class="msg-warning" id="lastNameError""></label><br>
							<input type="text" id="lastName" name="lastName"
								onclick="clearField('lastNameError')" value="${lastName}">
						</div>
						<div class="tp">
							<label for="input-phoneNumber">Phone Number<span>*</span></label><br>
							<label class="msg-warning" id="phoneNumberError""></label><br>
							<input type="text" id="contactNumber" name="contactNumber"
								onclick="clearField('phoneNumberError')" value="${phoneNumber}"
								placeholder="+94123445678|123456789|0777123456 formats are only accepted">
						</div>
						<div class="email">
							<label for="eMail">Email <span>*</span></label><br> <label
								class="msg-warning" id="emailError""></label><br> <input
								type="text" id="emailAddress" name="emailAddress"
								onclick="clearField('emailError')" value="${emailNumber}">
						</div>
						<div class="email-subject">
							<label for="input-eMailSubject">Subject <span>*</span></label><br>
							<label class="msg-warning" id="subjectError""></label><br> <input
								type="text" id="subject" name="subject"
								onclick="clearField('subjectError')" value="${subjectText}">
						</div>
						<div class="user-message">
							<label for="text-userMessage">Message <span>*</span></label><br>
							<label class="msg-warning" id="userMessageError""></label><br>
							<textarea id="message" rows="10" name="message"
								onclick="clearField('userMessageError')">${messageText}</textarea>
							<p class="pull-right">
								<span>*</span> Required fields
							</p>
						</div>
						<!--ReCaptcha -->
						<div class="re-captcha">
							<div class="g-recaptcha"
								data-sitekey="6LfDaQoUAAAAAJ9EWto6h6Dsd3TtQC1PcGFhc__c"></div>
							<br> <label class="msg-danger" id="captureError""></label>
						</div>
						<button class="btn-submit" type="submit" name="CCO" id="CCO"
							value="FBTSA" onclick="clearField('warningLabel');">Submit
							Query</button>
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
							<p>Plaese be kind enough to read out FAQ before sending us a
								message</p>
						</div>
						<div class="number-of-questions">
							<div class="question flip">
								<h3>
									<span>+</span>Question 01
								</h3>
							</div>
							<div class="answer slideable">
								<p>It is a long established fact that a reader will be
									distracted by the readable content of a page when looking at
									its layout. The point of using Lorem Ipsum is that it has a
									more-or-less normal distribution of letters, as opposed to
									using 'Content here, content here', making it look like
									readable English.</p>
							</div>
						</div>
						<!-- End Number-of-questions -->

						<div class="number-of-questions">
							<div class="question flip">
								<h3>
									<span>+</span>Question 02
								</h3>
							</div>
							<div class="answer slideable">
								<p>It is a long established fact that a reader will be
									distracted by the readable content of a page when looking at
									its layout. The point of using Lorem Ipsum is that it has a
									more-or-less normal distribution of letters, as opposed to
									using 'Content here, content here', making it look like
									readable English.</p>
							</div>
						</div>
						<!-- End Number-of-questions -->

						<div class="number-of-questions">
							<div class="question flip">
								<h3>
									<span>+</span>Question 03
								</h3>
							</div>
							<div class="answer slideable">
								<p>It is a long established fact that a reader will be
									distracted by the readable content of a page when looking at
									its layout. The point of using Lorem Ipsum is that it has a
									more-or-less normal distribution of letters, as opposed to
									using 'Content here, content here', making it look like
									readable English.</p>
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
							<p>
								Genesiis Software (pvt) Ltd. <br>Park Street <br>Colombo
								07 <br>Sri Lanka.
							</p>
						</div>

						<div class="location" id="map">
							<iframe
								src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3960.7755901309197!2d79.85629321530695!3d6.917411095001903!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae2596c8f0d9dd1%3A0xd40004ef11f25f9c!2sGenesiis+Software+(Pvt)+Ltd!5e0!3m2!1sen!2slk!4v1478002827125"
								frameborder="0" allowfullscreen></iframe>
						</div>

						<div class="contact-info">
							<a class="email"
								href="mailto:info@genesiis.com?subject=Mail%20from%20campuslk%20contact%20us%20page"
								target="_top">info@genesiis.com</a>
							<p class="tp">
								+94 11 7765 600<br>+94 11 2385 872
							</p>
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
	<jsp:include page="layout/footer.jsp"></jsp:include>
	<!-- End Footer -->


</body>
</html>