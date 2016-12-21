<!-- 20161127 DJ  c51-report-courses-by-course-provider-MP-dj  search view for courses by course providers -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">

<!--     Data Table CSS -->
<link href="/dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- Header-->
	<jsp:include page="/dist/partials/layout/header.jsp" />
	<!-- End Header -->

	<!-- Main Container - Contact-US -->
	<div class="top-providers-screen clearfix">
		<div class="inner-header">
			<div class="main-topic">
				<h1>Find your future with us</h1>
			</div>
		</div>
		<div class="page-topic">
			<h2 class="page-topic-t1">| Courses by Course Providers</h2>
		</div>

		<!-- image header -->
		<div class="inner-image-header">
			<div class="bg-image"></div>
		</div>
		<!-- END inner-image-header -->
		<div>
			<div>
				<div class="container">					
						<fieldset>
							<legend align="left">Search criteria </legend>
							<!-- <div class="courseProvider ">
								Course Provider :<input type="text" name="providerlist"
									id="providerlist" list="providerName"
									placeholder="-- Select District --" />
								<datalist id="providerName">
								</datalist>
							</div> -->
							<div class="drop-holder">
								<input type="text" name="providerlist" id="providerlist"
									list="providerName" placeholder="-- Select District --" />
								<datalist id="providerName">
								</datalist>
							</div>
							<div>
								Status : 
                                <input type="radio" name="status" value="ACTIVE"> Active
  								<input type="radio" name="status" value="INACTIVE"> Inactive
							</div>
							<div>
								Start Date : <input type="date" id="startdate" name="search">
							</div>
							<div>
								End Date : <input type="date" id="enddate" name="search">
							</div>
							<div>
								<button type="submit" id="clearParam" >Clear</button>
								<button type="submit" id="searchList" >Search
									List</button>
							</div>
						</fieldset>					
				</div>
			</div>
			</br></br>			 
			 <div >
                <h1>Result set</h1>
                <div >
                <table id="table" class="table-responsive">						
							<!-- <tr>
							   <td>Item ID</td>
                               <td>Item Name</td>
							</tr>	 -->				
						
					</table>
                
               <!--  <ul id="coursesList" class="list-inline clearfix">

				</ul> -->
                </div>
                </div>
			</div>
			</br></br>		
		</div>	
	<!-- End Container - Top Providers list -->
	
		
<!-- jQuery & Other js -->
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"
		charset="utf-8"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"
		charset="utf-8"></script>
	<script src="/dist/js/main.js"></script>

    <script src="/dist/js/report/courses-by-provider.js"></script>

	<!-- W3-Include -->
	<script src="../bower-components/w3/w3data.js"></script>

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!--End  Footer -->

</body>
</html>