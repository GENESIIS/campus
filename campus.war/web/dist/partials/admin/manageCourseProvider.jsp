<!-- 20161121 JH c39-add-course-provider-jh manageCourseProvider.jsp created -->


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

.main-category .content-holder .course-filter-panel .filtering-area .bottom ul>li
	{
	width: 16.66%;
	text-align: center;
	float: left;
}
</style>

<body>
	<!-- include Header-->
	<header class="header">
	 <jsp:include
		page="/dist/partials/layout/header.jsp"></jsp:include>
	 </header>
	<!-- End Header -->

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
								<input class="pro-name" type="text" name="providerName"
									id="providerName" required size="50px;" />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Short Name:</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="shortName"
									id="shortName" size="50px;" required />
							</div>
						</li>
						<!-- end -->
						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">About Me:</h1>
							</div>
							<div class="col-name">
								<textarea rows="10" cols="90" name="aboutMe" id="aboutMe"
									required></textarea>
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Special Features :</h1>
							</div>
							<div class="col-name">
								<textarea rows="7" cols="90" name="specialFeatures"
									id="specialFeatures" required></textarea>
							</div>
						</li>
						<!-- end -->
					</ul>
				</div>
				<!-- End filter result table -->

			</div>
			<!-- End left panel -->
<br/><br/>
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
								<input class="pro-name" type="text" name="generalEmail"
									id="generalEmail" required size="25px;" />
							</div>
							<div class="col-name">
								<h1 class="pro-name">Course Inquiry Email :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="inquiryMail"
									id="inquiryMail" size="25px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Land Number 1 :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="land1" id="land1"
									size="25px;" required />
							</div>
							<div class="col-name">
								<h1 class="pro-name">Land Number 2 :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="land2" id="land2"
									size="25px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Mobile Number :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="mobile" id="mobile"
									size="25px;" required />
							</div>
							<div class="col-name">
								<h1 class="pro-name">Fax Number :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="fax" id="fax"
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
								<input class="pro-name" type="text" name="address1"
									id="address1" size="50px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Address Line 2 :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="address2"
									id="address2" size="50px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Address Line 3 :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="address3"
									id="address3" size="50px;" required />
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Country :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="country" id="country"
									size="50px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Town :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="town" id="town"
									size="50px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Web Link :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="webLink" id="webLink"
									size="25px;" required />
							</div>
							<div class="col-name">
								<h1 class="pro-name">Facebook URL :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="facebook"
									id="facebook" size="25px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">LinkedIn URL :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="linkdedIn"
									id="linkdedIn" size="25px;" required />
							</div>
							<div class="col-name">
								<h1 class="pro-name">Twitter URL :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="twitter" id="twitter"
									size="25px;" required />
							</div>
						</li>
						<!-- end -->


						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">Instagram URL :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="instagram"
									id="instagram" size="25px;" required />
							</div>
							<div class="col-name">
								<h1 class="pro-name">MySpace URL :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="mySpace" id="mySpace"
									size="25px;" required />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix">
							<div class="col-name">
								<h1 class="pro-name">WhatsApp Number :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="whatsapp" id="whatsapp"
									whatsapp"" size="25px;" required />
							</div>
							<div class="col-name">
								<h1 class="pro-name">Viber Number :</h1>
							</div>
							<div class="col-name">
								<input class="pro-name" type="text" name="viber" id="viber"
									size="25px;" required />
							</div>
						</li>
						<!-- end -->
					</ul>
				</div>
				<!-- End filter result table -->
				<br/><br/>
				<!-- course filter panel : left side -->
				<div class="course-filter-panel">
					<h3 style="color: aqua;">Admin Info</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Expiration Date :</h1>
								</div>
								<div class="col-name">
									<input class="pro-name" type="text" name="expirationDate"
										id="expirationDate" required size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Type :</h1>
								</div>
								<div class="col-name">
									<input class="pro-name" type="text" name="providerType"
										id="providerType" size="50px;" required />
								</div>
							</li>
							<!-- end -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">About Me:</h1>
								</div>
								<div class="col-name">
									<textarea rows="10" cols="90" name="aboutMe" id="aboutMe"
										required></textarea>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Special Features :</h1>
								</div>
								<div class="col-name">
									<textarea rows="7" cols="90" name="specialFeatures"
										id="specialFeatures" required></textarea>
								</div>
							</li>
							<!-- end -->
						</ul>
					</div>
					<!-- End filter result table -->

				</div>
				<!-- End left panel -->

			</div>
			<!-- End left panel -->

		</div>
		<!-- End page content  -->
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