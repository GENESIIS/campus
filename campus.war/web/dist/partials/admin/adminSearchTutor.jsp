<!-- c133-admin-list-tutors removed unwanted commented codes -->
<!-- 20170317 JH c134-admin-list-new-tutor-requests added new style .hide-value to hide the content of approval status column, imported AJAX error handling js -->
<!-- 20170321 TR c106- changed pagination button styles  -->
<!-- 20170321 TR c106- update new selected tab button styles ( All and New tabs )  -->
<!-- 20170321 TR c106- remove CDN links and added jquery.dataTables.css and jquery.dataTables.js  -->
<!-- 20170321 JH c134-admin-list-new-tutor-requests removed commented cdn files, imported error handling javascript file, added div to display error messages -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->

	<link href="/dist/css/style.css" rel="stylesheet">
	<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
		rel="stylesheet">
	
	<!-- Data Table CSS -->
	<link href="/dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/dist/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/dist/bower-components/datatable/jquery.dataTables.css">


	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script> 
	<script src="/dist/js/main.js"></script>
	<script src="/dist/bower-components/datatable/jquery.dataTables.js"></script>

	<style type="text/css">
	div:empty {
  	 display: none;
	}
	</style>
</head>


<body>
	<!-- include Header-->
	<header><jsp:include page="/dist/partials/layout/header.jsp"></jsp:include></header>
	<!-- End Header -->


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
	<footer> <jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	</footer>


	<!-- W3-Include -->
	<script src="/dist/bower-components/w3/w3data.js"></script>

	<!-- custom javascript -->
	<script src="/dist/js/admin/search-tutor-helper.js"></script>
	
	<!-- error handling javascript -->
	<script src="/dist/js/error-handling.js"></script>
	
	<script src="/dist/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>