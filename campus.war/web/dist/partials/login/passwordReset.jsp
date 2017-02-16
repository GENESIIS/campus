<!-- 20170216 TR c68 init passwordReset.jsp  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

</head>

<body>
    <header>
        <jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
    </header>

    <!-- Start passwordReset screen -->
    <div class="pwrd-reset-screen show clearfix">
        <div class="col-lg-5 col-sm-12 col-md-6 col-xs-12 clearfix">
            <h2 class="page-topic-t3">Reset  Your Password</h2>
            <h3 class="sub-topic-t1">Enter a new password for your account.</h3>
            <div class="lbl-error">
                <label for="error-msg" class="error-msg">
                    <span class="error-txt"></span>
                </label>
            </div>
            <div class="input-area clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <input type="password" class="" placeholder="New Password" required >
                    <input type="password" class="" placeholder="New Password" required >
                </div>
            </div>
            <div class="form-group btn-holder">
                <button type="button" class="btn btn-primary">PopUp</button>
            </div>
        </div>
    </div>


    <!-- Footer -->
        <jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
    <!-- End Footer -->

</body>
</html>