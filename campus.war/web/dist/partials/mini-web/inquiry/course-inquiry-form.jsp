<!-- Inquiry Form Screen -->

<%-- <%@ include file="../../../../index.jsp" > --%>

<div class="inquiry-screen clearfix">
    <div class="inner-header">
        <div class="main-topic">
            <h1>Feel free to talk to us</h1>
        </div>
    </div>
    <div class="page-topic">
        <h2 class="page-topic-t1">| Course Inquiry Form </h2>
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
                <h1 class="page-topic-t2">You are making inquiry about <span class="colr-white">ICBT</span></h1>
                 <form class="submit-form" action=""> 
                    <!-- First Name -->
                    <div class="f-name">
                        <label for="input-firstName">Full Name <span>*</span></label><br>
                        <input type="text" name="fullname" id="fullname">
                    </div>
                    <!-- Email -->
                    <div class="email">
                        <label for="eMail">Email <span>*</span></label><br>
                        <input type="text" name="email" id="email">
                    </div>
                    <!--Country Code -->
                    <div class="county-code">
                        <label for="input-county-code">Country Code <span>*</span></label><br>
                        <input type="text" name="countryCode" id="countryCode">
                    </div>
                    <!-- Area Code -->
                    <div class="area-code">
                        <label for="input-area-code">Area Code <span>*</span></label><br>
                        <input type="text" name="areaCode" id="areaCode">
                    </div>
                    <!-- Telephone -->
                    <div class="tp-number">
                        <label for="input-tp-no">Telephone No <span>*</span></label><br>
                        <input type="text" name="telNum" id="telNum">
                    </div>
                    <!-- Enquiry Title -->
                    <div class="inquiry-title">
                        <label for="input-inquiry-title">Inquiry Title <span>*</span></label><br>
                        <input type="text"name="inquiryTitle" id="inquiryTitle">
                    </div>
                    <!-- Message -->
                    <div class="inquiry-message">
                        <label for="text-userMessage">Message <span>*</span></label><br>
                        <textarea name="inquiry" id="inquiry" rows="10"></textarea>
                        <p class="pull-right"><span>*</span> Required fields</p>
                    <input type="hidden" value="1" name="student" id="student">
					<input type="hidden" value="7" name="programmeCode" id="programmeCode">
			
                    </div>
                    <!-- ReCaptcha -->
                    <div class="re-captcha">
                        <div class="g-recaptcha" data-sitekey="6LdsagoUAAAAALS1tjjqyHe-7EvIIJF1kaKo-Pmw"></div>
                    </div>
                    <!-- btn Submit -->
                    <button class="btn-submit" type="button" name="" onclick="sendCourseInquiry()" value="Send Message">Send Message</button>
                 </form> 
                <!-- End Submit form -->
            </div>
            <!-- End Inquiry form - Filing Area -->
        </div>
    </div>
    <!-- END inquiry-form-holder -->
</div>
<!-- End -->