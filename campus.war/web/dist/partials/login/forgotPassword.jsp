<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--     20170127 AS CAM-22 forgotPassword.jsp created -->
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
	<!-- URL binded data split and assign to fields   -->
	<script>
		$(document)
				.ready(
						function() {

							var sPageURL = decodeURIComponent(window.location.search
									.substring(1)), sURLVariables = sPageURL
									.split('?'), sParameterName, i;
							var firstName = "";
							var lastName = "";
							var emailAdd = "";
							var scode = "";
							var decode = "";
							for (i = 0; i < sURLVariables.length; i++) {
								sParameterName = sURLVariables[i].split('&');
								firstName = sParameterName[1];
								lastName = sParameterName[2];
								emailAdd = sParameterName[3];
								scode = sParameterName[4];
							}

							decode = hashDecode(scode);
							document.getElementById("fullName").value = firstName
									+ " " + lastName;
							document.getElementById("emailaddress").innerHTML = emailAdd;
							document.getElementById("userTypeCode").value = decode;
							

							//document.write('<input type="text" value="firstName+" "+lastName" disabled>');
						});
	</script>
	<header> <jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
	<jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>
	<jsp:include page="/dist/partials/login/loginPopup.jsp"></jsp:include>
	</header>
	<div align="center">
		<div class="input-panel col-md-6 col-lg-6 col-sm-12 clearfix">
			<label for="error-msg" class="error-msg"><span
										class="error-txt" id="message"></span></label>
			<div
				class="display-info col-lg-7 col-sm-12 col-md-6 col-xs-12 clearfix">
				<div class="display-only">
					<label class="">Name : </label> <input type="text" id="fullName"
						name="fullName" disabled>
				</div>
				<div class="display-only">
					<label class="">Email : </label> <input type="text" id="emailaddress"
						name="email" disabled> 
						<input type="hidden" id="userTypeCode" name="userTypeCode" />
				</div>
			</div>



			<div class="input-field">
				<label id="passWordLabel">Password :<span>*</span></label><label
					id="passWordError" style="color: #C70039;"
					class="validationInputFields"></label><br> <input
					class="text-field" type="password" name="psw" id="passWord"
					onclick="clearField('passWordError')"
					onkeypress="validatePasswordResetData()"> 
				<div class="pull-right show-pwrd">
					<span class="check-box"><input type="checkbox"
						id="showpasscheckbox" title="Show the password as plain text"
						onclick="convertPassWordToString('showpasscheckbox','passWord','confrmpsw')"></span>Show
					Password
				</div>
			</div>
			<!-- End Password name -->

			<div class="input-field">
				<label id="confPassWordLabel">Confirm Password :<span>*</span></label><label
					id="confPassWordError" style="color: #C70039;"
					class="validationInputFields"></label><br> <input
					class="text-field" type="password" name="confrmpsw" id="confrmpsw"
					onclick="clearField('confPassWordError')">
			</div>
			<!-- End Confirm password  -->
			<div class="input-field">
				<button class="btn-create" onclick="changedPassword()" name="CCO"
					id="CCO" value="RESETPASS">Changed Password</button>

			</div>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>