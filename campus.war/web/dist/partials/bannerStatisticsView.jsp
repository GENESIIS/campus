<!-- 20161206 DJ c52-report-banner-statistics-MP-dj  search view for banner statistics-->

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
<style>
tbody {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}
td, th {
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
			<h2 class="page-topic-t1">| Banner Statistics</h2>
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
							<div>
								Page :<font color="red">*</font><input type="text" name="pagelist"
									id="pagelist" list="pageName"
									placeholder="-- Select Page --" />
								<datalist id="pageName">
								</datalist>
							</div>
							<div>
								Page Slot :<input type="text" name="pageSlotlist"
									id="pageSlotlist" list="pageSlotName"
									placeholder="-- Select Page Slot --" />
								<datalist id="pageSlotName">
								</datalist>
							</div>
							<!-- <div>
								Banner Name :<input type="text" name="bannerList"
									id="bannerList" list="bannerName"
									placeholder="-- Select Banner Name --" />
								<datalist id="bannerName">
								</datalist>
							</div> -->
							<div>
								Banner Provider :<input type="text" name="bannerProviderList"
									id="bannerProviderList" list="bannerProviderName"
									placeholder="-- Select Banner Provider --" />
								<datalist id="bannerProviderName">
								</datalist>
							</div>
							<div>
								From Date :<font color="red">*</font> <input type="date" id="fromDate" name="search">
							</div>
							<div>
								To Date :<font color="red">*</font> <input type="date" id="toDate" name="search">
							</div>
							<div>
								<button type="submit" id="clearParam">Clear</button>
								<button type="submit" id="searchList" >Search
									List</button>
							</div>
						</fieldset>					
				</div>
			</div>
			</br></br>
			</br></br>			 
			<div id="resultPanel">
            <div class="results-count col-md-12 col-lg-12 col-sm-12">
                <label id="totalResultsCount"  for=""></label>
            </div>

            <div  id="resultSetDiv" class="data-tbl col-md-12 col-lg-12 col-sm-12">
                <table class="display">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>CALLERPAGE </th>
                            <th>VIEWDATE</th>
                            <th>VIEWTIME</th>                            
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

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!--End  Footer -->
	<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/report/banner-statistics.js"></script>

<!-- <!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script> -->

</body>

</html>