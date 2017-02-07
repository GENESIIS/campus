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
						<div>
							<legend align="left">Basic Info </legend>
						</div>
						<div>
							Course Provider Name :<font color="red">*</font><input
								type="text" name="providerList" id="providerList"
								list="providerName" placeholder="-- Select Page --"   size="25px"/>
							<datalist id="providerName">
							</datalist>
							<label id="errorProviderList" for=""></label>
						</div>
						<div>
							Course Name :<font color="red">*</font><input type="text" id="courseName" name="courseName"   size="25px"/>
						</div>
						<div>
							Course Details :<input type="text" id="courseDetails" name="courseDetails"  size="25px" />
						</div>						
						<div>
							Email Address:<font color="red">*</font> 
							<input class="input" type="text" name="email"
										id="email" size="25px" placeholder="Enter a valid email address"/> <span
										id="errorEmail" class="error-message">${errorEmail }</span>
						</div>
						<div>
							Course Duration :<font color="red">*</font><input type="text" id="courseDuration" name="courseDuration"  size="25px"/>
						</div>
						<div>
							Course Category :<font color="red">*</font><input type="text" name="categoryList"
								id="categoryList" list="categoryName"
								placeholder="-- Select Course Category --"   size="25px"/>
							<datalist id="categoryName">
							</datalist>
						</div>
						<div>
							Course Major :<font color="red">*</font><input type="text" name="majorList" id="majorList"
								list="majorName" placeholder="-- Select Course Major --"   size="25px"/>
							<datalist id="majorName">
							</datalist>
						</div>
						<div>
							Course Level :<font color="red">*</font><input type="text" name="levelList" id="levelList"
								list="levelName" placeholder="-- Select Course Level --"   size="25px"/>
							<datalist id="levelName">
							</datalist>
						</div>
						<div>
							Course Class Type :<input type="text" name="classTypelist"
								id="classTypelist" list="classTypeName"
								placeholder="-- Select Course Class Type --"  size="25px"/>
							<datalist id="classTypeName">
							</datalist>
						</div>
						<div>
							Course Status :<br>
							<input type="radio" id="counselorStatus" name="counselorStatus" value="ACTIVE"><span>Active</span>
							<input type="radio" id="counselorStatus" name="counselorStatus" value="INACTIVE"><span>InActive</span>
							<input type="radio" id="counselorStatus" name="counselorStatus" value="PENDING"><span>Pending</span>
							<input type="radio" id="counselorStatus" name="counselorStatus" value="ARCHIVE"><span>Archive</span>
						</div>
						<div>
							Counselor Name :<font color="red">*</font><input type="text" id="counselorName" name="counselorName"  size="25px"/>
						</div>
						<div>
							Counselor Phone :<font color="red">*</font><input type="number" id="counselorPhone" name="counselorPhone"  size="25px" />
						</div>
						<div>
							Display Start Date :<input type="date" id="fromDate"
								name="fromDate" onkeydown="return false"> <label
								id="errorFromDate" for=""></label>
						</div>
						<div>
							Expiration Date :<input type="date" id="toDate" name="toDate"
								onkeydown="return false"> <label id="errorToDate"
								for=""></label>
						</div>
						<div>
							<button type="submit" id="clearParam">Clear</button>
							<button type="submit" id="addProgramme">Add</button>
						</div>
					</fieldset>
				</div>
			</div>
		</div>

		<!-- END provider-list-holder -->
	</div>
	<!-- End Container - Top Providers list -->
		
	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
	<script src="/dist/js/admin/ui-addProgramme-helper.js"></script>
	<script src="/dist/js/admin/ui-programme-validator.js"></script>
	
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!-- End Footer -->
</body>
</html>