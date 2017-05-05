<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 20170418 AS c154 -admin-error-content.jsp sample page created -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Content</title>
<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">
<link href="/dist/css/font-awesome.css" rel="stylesheet">

<script src="/dist/js/header/ui-populate-helper.js"></script>
<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/header/ui-populate-helper.js"></script>
</head>
<body>

</body>
<header>
	<jsp:include page="/dist/partials/layout/admin-header.jsp"></jsp:include>
		<jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>
	
</header>
<script type="text/javascript">
		$(window).scrollTop(0);
		$('#msg-popup').modal('show');
		
		setTimeout(
				function() {
					var myURL = document.location.host;
				//	window.location.href = 'http://www.campus.dev:8080/dist/partials/admin/admin-login.jsp'; //this name may have to change depend on actual location of the page "Student Login or public index page"
					location.replace('http://'+myURL + "/dist/partials/admin/admin-login.jsp");
				}, 5000);
	</script>
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	    <div class="msg-display-area text-center clearfix">
            <div class="display-msg">
               <label>Sorry, you are not allowed to view content.</label>
            </div>
	    </div>
	</div>

</html>