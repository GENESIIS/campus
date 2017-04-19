<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20170419 AS c154 -adminSearchTutor.jsp sample page created -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>

<link rel="stylesheet" type="text/css" href="/dist/bower-components/datatable/jquery.dataTables.css">
<script src="/dist/bower-components/datatable/jquery.dataTables.js"></script>

	<style type="text/css">
	#userMessage:empty {
  	 display: none;
	}
	</style>

</head>
<body>
	<header> <jsp:include
		page="/dist/partials/layout/admin-header.jsp"></jsp:include> <jsp:include
		page="/dist/partials/admin/AdminSessionDetails.jsp"></jsp:include> </header>

<!-- Main Container - Higher-Education -->
	<div class="admin-content clearfix">

		<!-- page inner header -->
		<div class="inner-header">
			<div class="heading">
				<h1>| Search Tutor</h1>
			</div>
		</div>
		<!-- end inner header -->
		
		<div class="content-holder center-block clearfix">
		<div class="alert alert-danger" id="userMessage"></div>
            <div class="tutors-table">
                <!-- Page content -->
                
					<div class="tab-holder clearfix">
						<ul class="nav nav-pills pull-right" role="tablist">
							<li class="" id="button-all"><a onclick="listAllTutors();">All</a></li>
							<li id="button-new"><a onclick="listTutorRequests();">New</a></li>
						</ul>
					</div>
					<table id="tutors-table" class="display compact" width="100%">
                        <thead>
                            <tr>
                            	<th></th>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Username</th>
                                <th>Email</th>
                                <th>Land Phone number</th>
                                <th>Mobile phone number</th>
                                <th>Town</th>
                                <th>Country</th>
                                <th>Approval status</th>
                                <th>Status</th>
                            </tr>
                            </tr>
                        </thead>
                    </table>
            </div>
		    <!-- End tutors table  -->
		</div>
        <!-- End content-holder  -->
	</div>
	<!-- End Admin-content  -->


	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>