<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campus.lk</title>
<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">

<script src="/dist/js/header/ui-populate-helper.js"></script>
<!-- jquery and other js -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/jquery/jquery.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/header/ui-populate-helper.js"></script>

<script src='/dist/js/institute/validation/validation.js'></script>
<script src='/dist/js/admin/admin-login.js'></script>
</head>
<body>

	<!-- Header-->
	<header> <jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
	<jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>
	<jsp:include page="/dist/partials/login/loginPopup.jsp"></jsp:include>
	</header>

	<div class="forgot-pwrd-screen clearfix">
		<div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 clearfix">
			
			<div class="fp-msg">
				<label for="error-msg" class="error-msg"> <span
					class="error-txt" id="emailveryMessage"></span>
				</label>
			</div>
		</div>
		<!-- End page topic and error msg block -->
<div class="modal-content">
		<div class="modal-body">
			
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-3">

						<form name="adminLogin" class="form-vertical form-user" role="form"	method="" action="">

							<div class="form-group has-error">
								<h5>Sign In</h5>
								<label for="error-msg" class="error-msg"><span
									class="error-txt" id="adminErrorMess"></span></label>
							</div>

							<div class="form-group has-error">
								<label for="username">Username or Email <span class="error-txt" id="usernameError"></span></label> <input type="email"
									class="form-control" id="adminEmail" placeholder="Email address"
									required onclick="clearField('emailtbError')">
							</div>

							<div class="form-group has-error">
								<label for="password">Password <span class="error-txt"
									id="passwordError"></span></label> <input type="password"
									class="form-control" id="adminPassword" placeholder="Password"
									required onclick="clearField('passtbError')">
							</div>

							<div class="form-group">
								<a class="forgot-pwd text-right" href="#"
									title="Forgot Password ?">Forgot Password ?</a>
							</div>

							<div class="form-group">

								<button type="button" class="btn btn-primary btn-block" name="CCO" id="CCO" value="ALOG" onclick="ALogin(); resetLoginLabels();">Sign In</button> 
							
							</div>

							<div class="form-group">
								<div class="checkbox pull-left">
									<label><input type="checkbox" name="remember"
										id="adminRemember"> Keep me logged in</label>
								</div>
								
							</div>

						</form>
					</div>
				</div>
			</div>
			</div>
</div>
	</div>
	<!-- End forgot-password-screen -->

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->

</body>
</html>