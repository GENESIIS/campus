<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--     20170127 AS CAM-22 emailVarification.jsp created -->
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
<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/header/ui-populate-helper.js"></script>
<script src='/dist/js/login.js'></script>
</head>
<body>
	<!-- Header-->

	<header> <jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
	<jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>
	<jsp:include page="/dist/partials/login/loginPopup.jsp"></jsp:include>
	</header>
<div align="center">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6">

				<div class="form-group has-error">
					<label for="username"> Email <span class="error-txt"
						id="emailtbError"></span></label> <input type="email" class="form-control"
						id="verifiemail" placeholder="Email address" required
						onclick="clearField('emailtbError')">
				</div>

				<div class="form-group">
					<button type="button" class="btn btn-primary btn-block"
						onclick="forgotPassword()" name="CCO" id="CCO" value="EMAILV">Email
						Verification</button>
				</div>
			</div>
		</div>
	</div>
</div>
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
<!-- 	email verifications popup message -->
	
	<div class="modal fade" id="verifications-popup" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">Close</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-6">

							<label class="" id="popupMessage">Your email verified  </label>

							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>