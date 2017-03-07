<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20170127 AS CAM-22 emailVarification.jsp created -->

<!-- 20170214 TR c68 update page content structure -->
<!-- 20170214 TR c68 added bootstrap styles to forgot-pwrd-screen -->
<!-- 20170215 TR c68 added label and heading for main body  -->
<!-- 20170215 TR c68 updated all styles in forgot-pwrd-screen  -->
<!-- 20170215 TR c68 specify email verify modal as evm  -->
<!-- 20170217 TR c68 changed forgot-pwrd-screen layout -->
<!-- 20170217 TR c68 changed email verify modal layout -->
<!-- 20170217 TR c68 added common error msg class  -->
<!-- 20170217 TR c68 validated error msg label hide and show  -->
<!-- 20170217 TR c68 fixed basic responsive issues - apply bootstrap grid sys. -->
<!-- 20170223 AS CAM-22 modify messages span and validation messages -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>
<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">

<script src="/dist/js/header/ui-populate-helper.js"></script>
<!-- jquery and other js -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/header/ui-populate-helper.js"></script>
<script src='/dist/js/login.js'></script>
</head>
<body>
   <script> 
          $( document ).ready(function() {
        	
        	  clearField('verifiemail'); 
        	  clearField('verifiemail');
        	  $("#verifyCode").val("");
			  clearField('verifyMesssage');

          }); 

    </script> 
	<!-- Header-->

	<header>
        <jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
        <jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>
        <jsp:include page="/dist/partials/login/loginPopup.jsp"></jsp:include>
	</header>

    <div class="forgot-pwrd-screen clearfix">
        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 clearfix">
            <h2 class="page-topic-t3">Forgot Your Password ?</h2>
            <h3 class="sub-topic-t1">Enter your email address below to reset your password.</h3>
            <div class="fp-msg">
                <label for="error-msg" class="error-msg">
                    <span class="error-txt" id="emailveryMessage"></span>
                </label>
            </div>
        </div>
        <!-- End page topic and error msg block -->

        <div class="col-lg-6 col-sm-12 col-md-8 col-xs-12 clearfix">
            <div class="input-area form-group has-error clearfix">
<!--                 <div class="col-lg-2 col-md-2 col-sm-12 col-xs-12"> -->
<!--                     <label for="username">Email : <span class="error-txt" id=emailtbError></span></label> -->
<!--                 </div> -->
                <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12">
                    <input type="email" class="" id="verifiemail" placeholder="Type Here" required onclick="clearField('emailveryMessage')">
                </div>
            </div>
            <div class="form-group btn-holder">
                <button type="button" class="btn btn-primary email-submit" onclick="forgotPassword()" name="CCO" id="CCO" value="EMAILV">Submit</button>
               
            </div>
        </div>
    </div>
    <!-- End forgot-password-screen -->

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
<!-- 	email verifications popup message -->
	
	<div class="modal fade" id="verifications-popup" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
        <div class="login-dialog modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">Close</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row verify-modal-container">
                            <div class="col-sm-12 col-lg-12 col-md-12 ">


                                <div class="form-group has-error ">
                                    <div class="fp-msg">
                                        <label for="username">
                                            <span class="error-txt" id="verifyMesssage"></span>
                                        </label>
                                    </div>
                                    <label class="evm-lbl-1 m-t-0 sub-topic-t1">We have sent verification code to your email.please check your inbox to continue.</label>
                                    <input type="text" class="evm-email" id="verifyCode" placeholder="Verification Code"  onclick="clearField('verifyMesssage')" required >
                                </div>
                                <div class="form-group">
                                        <button type="button" class="btn btn-primary btn-block"
                                            name="CCO" id="CCO" value="HASHV"
                                            onclick="verifyCode(); clearField('verifyCode')">Verify</button>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>