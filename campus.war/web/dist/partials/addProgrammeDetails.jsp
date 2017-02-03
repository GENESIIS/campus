<!-- 20161111 DJ  c6-list-available-institutes-on-the-view view top viewed/top rated course providers -->

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
</head>
<style type="text/css">
.main-category .content-holder .course-filter-panel .filter-result-table .course-info
	{
	display: block;
	padding-top: 3px;
	padding-bottom: 3px;
}
.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name
	{
	display: inline-block;
	width: 25%;
	float: left;
	border-right: 1px solid #e1e1e1;
}
.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name .pro-name
	{
	display: block;
	text-align: center;
	font-family: "Roboto", sans-serif;
	font-size: 20px;
	color: #193949;
	line-height: 0px;
	font-weight: 600;
}
.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name .input
	{
	display: block;
	text-align: center;
	font-family: "Roboto", sans-serif;
	font-size: 20px;
	color: gray;
	line-height: 0px;
	font-style: italic;
	font-weight: 100;
}
.main-category .content-holder .course-filter-panel .filter-result-table .course-info .col-name .textarea
	{
	font-family: "Roboto", sans-serif;
	font-size: 20px;
	color: gray;
	font-style: italic;
	font-weight: 100;
}
.main-category .content-holder .course-filter-panel .filtering-area .bottom ul>li
	{
	width: 16.66%;
	text-align: center; 
	float: left;
}
.error-message{
	color : red;
}
</style>
<body>
	<!-- Header-->
	<jsp:include page="/dist/partials/layout/header.jsp" />
	<!-- End Header-->
	<!-- Main Container - Contact-US -->
	<div class="top-providers-screen clearfix">
		<div class="inner-header">
			<div class="main-topic">
				<h1>Find your future with us</h1>
			</div>
		</div>
		<div class="page-topic">
			<h2 class="page-topic-t1">| Add a Course</h2>
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
						<legend align="left">Basic Info </legend>
						<div>
							Course Provider Name :<font color="red">*</font><input type="text"
								name="providerList" id="providerList" list="providerName"
								placeholder="-- Select Page --" />
							<datalist id="providerName">
							</datalist>
							<label id="errorProviderList" for=""></label>
						</div>
						<div>
							Course Name :<input type="text" id="" name="" />
						</div>
						<div>
							Course Details :<input type="text" id="" name="" />
						</div>
						<div>
							Course email :<input type="text" id="" name="" />
						</div>
						<div>
							Course Duration :<input type="text" id="" name="" />
						</div>
						<div>
							Course Category :<input type="text" name="categoryList" id="categoryList"
									list="categoryName" placeholder="-- Select Course Category --" />
								<datalist id="categoryName">
								</datalist>
						</div>
						<div>
							Course Major :<input type="text" name="majorList" id="majorList"
									list="majorName" placeholder="-- Select Course Major --" />
								<datalist id="majorName">
								</datalist>
						</div>
						<div>
							Course Category :<input type="text" name="levelList" id="levelList"
									list="levelName" placeholder="-- Select Course Level --" />
								<datalist id="levelName">
								</datalist>
						</div>
						<div>
							Course Category :<input type="text" name="classTypelist" id="classTypelist"
									list="classTypeName" placeholder="-- Select Course Class Type --" />
								<datalist id="classTypeName">
								</datalist>
						</div>
						<div>
							Counselor Name  :<input type="text" id="" name="" />
						</div>
						<div>
							Counselor Phone  :<input type="text" id="" name="" />
						</div>
						<div>
							Display Start Date  :<input type="date"
								id="fromDate" name="fromDate"  onkeydown="return false"> <label
								id="errorFromDate" for=""></label>
						</div>
						<div>
							Expiration Date  :<input type="date"
								id="fromDate" name="fromDate"  onkeydown="return false"> <label
								id="errorFromDate" for=""></label>
						</div>
						<div>
						<button type="submit" id="clearParam">Clear</button>
				        <button type="submit" id="searchList">Add</button>
						</div>
					</fieldset>
				</div>
			</div>
		</div>

		<!-- END provider-list-holder -->
	</div>
	<!-- End Container - Top Providers list -->
	
	<%-- <div class="main-category clearfix">
	<div class="content-holder center-block clearfix">
	<!-- course filter panel : left side -->
	<div class="course-filter-panel">
		<h3>Basic Info</h3>
		<!-- Filter result table -->
		<div class="filter-result-table">
			<ul class="result-row">
				<li class="course-info clearfix">
					<div class="col-name">
						<h1 class="pro-name">
							Course Provider Name: <span style="color: red;">*</span>
						</h1>
					</div>
					<div class="col-name">
						<input class="input" type="text" name="providerName"
							id="providerName" size="50px;" /><span id="errorProviderName"
							class="error-message">${errorProviderName }</span>
					</div>
				</li>
			</ul>
		</div>
	</div>
	</div>
	</div> --%>


	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
	<script src="/dist/js/admin/ui-addProgramme-helper.js"></script>
	
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!-- End Footer -->
</body>
</html>