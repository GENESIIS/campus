
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- Inquiry Form Screen -->
<div class="inquiry-screen clearfix">
	<div class="inner-header">
		<div class="main-topic">
			<h1>Feel free to talk to us</h1>
		</div>
	</div>
	<div class="page-topic">
		<h2 class="page-topic-t1">| Institute Inquiry Form</h2>
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
				<h1 class="page-topic-t2">
					You are making inquiry about <span class="colr-white">ICBT</span>
				</h1>
				<form class="submit-form" method="POST" action="InstituteController">

					<input type="hidden" value="1" name="courseProviderCode"> <input
						type="hidden" value="1" name="studentCode">

					<!-- First Name -->
					<div class="f-name">
						<label for="input-firstName">Full Name <span>*</span></label><br>
						<input type="text" name="fullname" id="input-firstName">
					</div>
					<!-- Email -->
					<div class="email">
						<label for="eMail">Email <span>*</span></label><br> <input
							type="text" name="email" id="email">
					</div>
					<!--Country Code -->
					<div class="county-code">
						<label for="input-county-code">Country Code <span>*</span></label><br>
						<input type="text" name="countryCode" id="input-county-code">
					</div>
					<!-- Area Code -->
					<div class="area-code">
						<label for="input-area-code">Area Code <span>*</span></label><br>
						<input type="text" name="areaCode" id="input-area-code">
					</div>
					<!-- Telephone -->
					<div class="tp-number">
						<label for="input-tp-no">Telephone No <span>*</span></label><br>
						<input type="text" name="telNum" id="input-tp-no">
					</div>
					<!-- Enquiry Title -->
					<div class="inquiry-title">
						<label for="input-inquiry-title">Inquiry Title <span>*</span></label><br>
						<input type="text" name="inquiryTitle" id="input-inquiry-title">
					</div>
					<!-- Message -->
					<div class="inquiry-message">
						<label for="text-userMessage">Message <span>*</span></label><br>
						<textarea id="text-userMessage" name="inquiry" rows="10"></textarea>
						<p class="pull-right">
							<span>*</span> Required fields
						</p>
					</div>
					<div>
						<div class="g-recaptcha"
							data-sitekey="6LfDaQoUAAAAAJ9EWto6h6Dsd3TtQC1PcGFhc__c"></div>
					</div>
					<button class="btn-submit" type="submit" name="CCO" id="CCO"
						value="SII" class="pure-button pure-button-primary">Send
						Message</button>
					<!-- <input class="btn-submit" type="submit" value="Send Message"> -->
				</form>
				<!-- End Submit form -->
			</div>
			<!-- End Inquiry form - Filing Area -->
		</div>
	</div>
	<!-- END inquiry-form-holder -->
</div>