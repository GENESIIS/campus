<!-- 20170418 c54-report-course-stats-MP-dj view Course Stats-->
<!-- 20170421 c54-report-course-stats-MP-dj add hidden attribute selectedProvider-->
<!-- 20170425 c54-report-course-stats-MP-dj set placeholder for course provider drop down-->
<!-- 20170425 c54-report-course-stats-MP-dj add 'errorProviderList' to set the error message on provider selection-->
<!-- 20170426 c54-report-course-stats-MP-dj add 'displayErrorMessage' to set the error message on provider selection-back end validation-->

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
<link href="/dist/datatable/dataTables.bootstrap.min.css"
	rel="stylesheet" type="text/css">
<style>
tbody {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td,th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
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
			<h2 class="page-topic-t1">| Course Stats</h2>
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
						<legend align="left">Search criteria : </legend>
						<div class="input-wrapper has-select">
						Course Provider :
							<input name="providerlist" id="providerlist" class="form-control" list="providerName" placeholder="-- Select Provider --">
							<datalist id="providerName"></datalist>
							 <label	id="errorProviderList" for=""></label>
							<input type="hidden" name="selectedProvider"	id="selectedProvider" value=""/>
						</div>			
						
						
						<div class="input-wrapper has-select" >
							Programme :
							 <input name="programmelist" id="programmelist"	class="form-control" list="programmeName" placeholder="-- Select Programme --" />
								<datalist id="programmeName"></datalist>
								<input type="hidden" name="selectedProgramme"	id="selectedProgramme" value=""/>
						</div>
						<div>
							Start Date : <input type="date" id="startdate" name="startdate" onkeydown="return false"> <label
								id="errorFromDate" for=""></label>
						</div>
						<div>
							End Date : <input type="date" id="enddate" name="enddate" onkeydown="return false"> <label
								id="errorToDate" for=""></label>
						</div>
						
						<div>
							<button type="submit" id="clearParam">Clear</button>
							<button type="submit" id="searchList">Search List</button>
							<label	id="displayErrorMessage" for=""></label>
						</div>
						
					</fieldset>
				</div>
			</div>
			<br>
			<div id="resultPanel">
            <div class="results-count col-md-12 col-lg-12 col-sm-12">
                <label id="totalResultsCount"  for=""></label>
            </div>

            <div  id="resultSetDiv" class="data-tbl col-md-12 col-lg-12 col-sm-12">
                <table class="display">					
						<thead>
							<tr>
								<th>#</th>
								<th>PROGNAME NAME</th>
								<th>VIEW</th>
								<th>INQUIRECOUNT</th>								 								
							</tr>
						</thead>
						<tbody id="tBody">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</div>
		<!-- End Container - Top Providers list -->


		<!-- jQuery & Other js -->
		<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"
			charset="utf-8"></script>
		<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"
			charset="utf-8"></script>
		<script src="/dist/js/main.js"></script>

		<script src="/dist/js/report/course-stats.js"></script>

		<!-- W3-Include -->
		<script src="../bower-components/w3/w3data.js"></script>

		<!-- Footer -->
		<jsp:include page="/dist/partials/layout/footer.jsp" />
		<!--End  Footer -->
</body>
</html>