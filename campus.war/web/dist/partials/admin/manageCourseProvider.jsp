<!-- 20161121 JH c39-add-course-provider manageCourseProvider.jsp created -->
<!-- 20161122 JH c39-add-course-provider sample UI page to add course provider details wip -->
<!-- 20161123 JH c39-add-course-provider UI code modified -->

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- custom javascript -->
<script src="/dist/js/header/ui-populate-helper.js"></script>

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
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
</style>

<body onload="getCategoryData()">
	<!-- include Header-->
	<header class="header"> <jsp:include
		page="/dist/partials/layout/header.jsp"></jsp:include> </header>
	<!-- End Header -->

	<form action="/AdminController" method="POST">
		<!-- Main Container - Higher-Education -->
		<div class="main-category clearfix">

			<!-- page inner header -->
			<div class="inner-header">
				<div class="category-image">
					<img src="/education/admin/images/courseProvider.jpg " alt="">
				</div>
				<div class="category-name">
					<h1>| Register a Course Provider</h1>
				</div>
			</div>
			<!-- end inner header -->

			<!-- Page content -->
			<div class="content-holder center-block clearfix">

				<!-- course filter panel : left side -->
				<div class="course-filter-panel">
					<h3>Basic Info</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Name:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerName"
										id="providerName" required size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Short Name:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="shortName"
										id="shortName" size="50px;" required />
								</div>
							</li>
							<!-- end -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">About Me:</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="8" cols="90" name="aboutMe"
										id="aboutMe" required></textarea>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Special Features :</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="5" cols="90"
										name="specialFeatures" id="specialFeatures" required></textarea>
								</div>
							</li>
							<!-- end -->
						</ul>
					</div>
					<!-- End filter result table -->

				</div>
				<!-- End left panel -->
				<br /> <br />
				<!-- course filter panel : left side -->
				<div class="course-filter-panel">
					<h3>Contact Info</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">General Email :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="generalEmail"
										id="generalEmail" required size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Course Inquiry Email :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="inquiryMail"
										id="inquiryMail" size="25px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Land Number 1 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="land1" id="land1"
										size="25px;" required />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Land Number 2 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="land2" id="land2"
										size="25px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Mobile Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="mobile" id="mobile"
										size="25px;" required />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Fax Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="fax" id="fax"
										size="25px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix"></li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Address Line 1 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="address1" id="address1"
										size="50px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Address Line 2 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="address2" id="address2"
										size="50px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Address Line 3 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="address3" id="address3"
										size="50px;" required />
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Country :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="country" id="country"
										size="50px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Town :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="town" id="town"
										size="50px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Web Link :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="webLink" id="webLink"
										size="25px;" required />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Facebook URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="facebook" id="facebook"
										size="25px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">LinkedIn URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="linkdedIn"
										id="linkdedIn" size="25px;" required />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Twitter URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="twitter" id="twitter"
										size="25px;" required />
								</div>
							</li>
							<!-- end -->


							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Instagram URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="instagram"
										id="instagram" size="25px;" required />
								</div>
								<div class="col-name">
									<h1 class="pro-name">MySpace URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="mySpace" id="mySpace"
										size="25px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">WhatsApp Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="whatsapp" id="whatsapp"
										size="25px;" required />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Viber Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="viber" id="viber"
										size="25px;" required />
								</div>
							</li>
							<!-- end -->
						</ul>
					</div>
					<!-- End filter result table -->
				</div>
				<!-- End left panel -->

				<br /> <br />
				<!-- course filter panel : left side -->
				<div class="course-filter-panel">
					<h3 style="color: navy;">Admin Info</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Expiration Date :</h1>
								</div>
								<div class="col-name">
									<input class="textarea" type="Date" name="expirationDate"
										id="expirationDate" required size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Type :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerType"
										id="providerType" size="50px;" required />
								</div>
							</li>
							<!-- end -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">
										<input type="radio" name="featured-oneoff"
											id="featured-oneoff" value="featured" /> Course Provider
									</h1>
								</div>
								<div class="col-name">
									<h1 class="pro-name">
										<input type="radio" name="featured-oneoff"
											id="featured-oneoff" value="one-off" /> One-Off Provider
									</h1>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Status :</h1>
								</div>
								<div class="col-name" style="width: 50%;">
									<h1 class="pro-name">
										<input type="radio" name="providerStatus" id="providerStatus"
											value="active" /> Active &nbsp; <input type="radio"
											name="providerStatus" id="providerStatus" value="inactive" />
										InActive &nbsp; <input type="radio" name="providerStatus"
											id="providerStatus" value="pending" /> Pending
									</h1>
								</div>
							</li>
							<!-- end -->
						</ul>
					</div>
					<!-- End filter result table -->
				</div>
				<!-- End left panel -->
				<br />
				<button id="viewNext"
					style="float: right; position: relative; right: 600px;"
					class="btn btn-lg btn-info">Next</button>
				<br />
				<!-- course filter panel : left side -->
				<div class="course-filter-panel">
					<h3 style="color: maroon;">Account Info</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Name:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="email" name="providerName"
										id="providerName" required size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Email:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="email" name="providerEmail"
										id="providerEmail" required size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Username :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerUsername"
										id="providerUsername" required size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Password :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="password" name="providerPassword"
										id="providerPassword" size="50px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Confirm Password :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="password" name="cProviderPassword"
										id="cProviderPassword" size="50px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Description :</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="5" cols="90"
										name="accountDescription" id="accountDescription" required></textarea>
								</div>
							</li>
							<!-- end -->
						</ul>
					</div>
					<!-- End filter result table -->

				</div>
				<!-- End left panel -->


				<!-- course filter panel : left side -->
				<div class="course-filter-panel">
					<h3>Course Provider Images</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Logo image (Small) :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="file" name="imageSmall"
										id="imageSmall" size="50px;" required />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Logo image (Large) :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="file" name="imageLarge"
										id="imageLarge" size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Header Image :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="file" name="imageHeader"
										id="imageHeader" size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Common Image :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="file" name="imageCommon"
										id="imageCommon" size="50px;" />
								</div>
							</li>
							<!-- end -->
						</ul>
					</div>
					<!-- End filter result table -->
					<input type="hidden" name="CCO" id="CCO"
						value="ADD_FEATURED_COURSE_PROVIDER" /> <input type="submit"
						value="Add" class="btn btn-success" />
				</div>
				<!-- End left panel -->

			</div>
			<!-- End page content  -->
	</form>
	</div>
	<!-- End Main Container -->

	<!-- Footer -->
	<footer> <jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	</footer>
	
	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
</body>
</html>
