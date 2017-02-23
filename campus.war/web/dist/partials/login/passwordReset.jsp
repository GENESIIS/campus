<!-- 20170216 TR c68 init passwordReset.jsp  -->
<!-- 20170216 TR c68 design page layout and added body content -->
<!-- 20170217 TR c68 added common error msg class  -->
<!-- 20170217 TR c68 validated error msg label hide and show  -->
<!-- 20170217 TR c68 fixed basic responsive issues - apply bootstrap grid sys. -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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

<!-- jquery and other js  -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src='/dist/js/login.js'></script>
<script>
    $( document ).ready(function() {
        // if span empty, hide the parent label
     //   $('.fp-msg span:empty').parent().hide()
        
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

</head>

<body>
    <header>
        <jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
    </header>

    <!-- Start passwordReset screen -->
    <div class="pwrd-reset-screen show clearfix">
        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12 clearfix">
            <h2 class="page-topic-t3">Reset  Your Password</h2>
            <h3 class="sub-topic-t1">Enter a new password for your account.</h3>
        </div>
        <!-- End page topic block -->

        <div class="display-info col-lg-7 col-sm-12 col-md-6 col-xs-12 pull-right clearfix">
                    <div class="display-only">
                        <label class="">Name : </label>
                        <input type="text" id="fullName" name="fullName" disabled>
                          <input type="hidden" id="userTypeCode" name="userTypeCode" />
                    </div>
                    <div class="display-only">
                        <label class="">Email : </label>
                        <input type="text" id="emailaddress" name="email" disabled>
                      
                    </div>
                </div>
        <!-- End User Info display -->

        <div class="col-lg-5 col-sm-12 col-md-6 col-xs-12 clearfix">

            <div class="fp-msg fp-msg-success">
                <label for="error-msg" class="error-msg">
                    <span class="error-txt" id="message"></span>
                </label>
            </div>
            <div class="input-area clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                 <label for="username"> <span class="error-txt" id="passWordError"></span> </label>
                    <input type="password" class="" placeholder="New Password" name="psw" id="passWord"
					onclick="clearField('passWordError')" onkeypress="validatePasswordResetData()" required >
					 <label for="username"> <span class="error-txt" id="confPassWordError"></span> </label>
                    <input type="password" class="" placeholder="Confirm Password" name="confrmpsw" id="confrmpsw"
					onclick="clearField('confPassWordError')"  required >
                </div>
                <div class="pull-right show-pwrd">
					<span class="check-box"><input type="checkbox"
						id="showpasscheckbox" title="Show the password as plain text"
						onclick="convertPassWordToString('showpasscheckbox','passWord','confrmpsw')"></span>Show
					Password
				</div>
            </div>
            <div class="btn-holder">
                <button type="button" class="btn btn-primary" onclick="changedPassword()" name="CCO"
					id="CCO" value="RESETPASS">Reset Password</button>
            </div>
        </div>
        <!-- End passwordReset form -->


    </div>


    <!-- Footer -->
        <jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
    <!-- End Footer -->

</body>
</html>