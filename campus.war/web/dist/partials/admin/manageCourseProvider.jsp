<!-- 20161121 JH c39-add-course-provider manageCourseProvider.jsp created -->
<!-- 20161122 JH c39-add-course-provider sample UI page to add course provider details wip -->
<!-- 20161123 JH c39-add-course-provider UI code modified -->
<!-- 20161202 JH c39-add-course-provider added missing input fields -->
<!-- 20161206 JH c39-add-course-provider create check boxes to account status -->
<!-- 20161208 JH c39-add-course-provider crev modifications -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="applicationStatusBean"
	class="com.genesiis.campus.validation.ApplicationStatusBean" />
<jsp:useBean id="accountTypeBean"
	class="com.genesiis.campus.validation.AccountTypeBean" />
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

<body onload="getCategoryData();">
	<!-- include Header-->
	<header class="header"> <jsp:include
		page="/dist/partials/layout/header.jsp"></jsp:include> </header>
	<!-- End Header -->


	<!-- Main Container - Higher-Education -->
	<div class="main-category clearfix">

		<!-- page inner header -->
		<div class="inner-header">
			<div class="category-name">
				<h1>| Register a Course Provider</h1>
			</div>
		</div>
		<!-- end inner header -->
		<form action="/AdminController" method="POST" id="basicForm">
			<!-- Page content -->


			<input type="hidden" name="CCO" id="CCO"
				value="ADD_FEATURED_COURSE_PROVIDER" />
			<div class="content-holder center-block clearfix">
				<div class="alert alert-danger" role="alert" id="errorMessage">
				</div>
				<!-- course filter panel : left side -->
				<div class="course-filter-panel">
					<h3>Basic Info</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<!-- select the course provider type -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Account Type :</h1>
								</div>
								<div class="col-name" style="width: 50%;">
									<!-- here we selects featured course provider account type as default and all other account
								types are disabled by default. This is due to current requirements.
								If we want to manage one-off course providers, then the only change need to be done is to
								enable to select one-off provider account type and call the command class CmdAddFeaturedProvider.java -->

									<!-- back end code is already completed to handle one-off course providers as well -->

									<c:forEach items="${accountTypeBean.values}" var="accountTypes">
										<c:if test="${accountTypes.typeValue == 1}">
											<input type="radio" name="courseProvider" id="courseProvider"
												value="${accountTypes.typeValue}" checked="checked"
												onchange="changeRequiredData('${accountTypes.typeValue}');" /> ${accountTypes} &nbsp;
										</c:if>
										<c:if test="${accountTypes.typeValue != 1}">
											<input type="radio" name="courseProvider" id="courseProvider"
												value="${accountTypes.typeValue}" disabled="disabled"
												onchange="changeRequiredData('${accountTypes.typeValue}');" /> ${accountTypes} &nbsp;
										</c:if>
									</c:forEach>
									<span id="errorCourseProvider"></span>
								</div>
							</li>
							<!-- end -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Name:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerName"
										id="providerName" size="50px;" onchange="normalValdation();"/><span id="errorProviderName">${errorProviderName }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Short Name:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="shortName"
										id="shortName" size="30px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Unique Prefix:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="uniquePrefix"
										id="uniquePrefix" size="30px;"
										onfocusout="providerPrefixValidation();" />
										<div id="errorPrefix"></div>
								</div>
							</li>
							<!-- end -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Company Profile:</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="8" cols="90" name="aboutMe"
										id="aboutMe"></textarea>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Special Features :</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="5" cols="90"
										name="specialFeatures" id="specialFeatures"></textarea>
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
										id="generalEmail" size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Course Inquiry Email :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="inquiryMail"
										id="inquiryMail" size="25px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Land Phone Area Code :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="areaCode" id="areaCode"
										size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Land Number 1 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="land1" id="land1"
										size="25px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Land Number 2 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="land2" id="land2"
										size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Fax Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="fax" id="fax"
										size="25px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Mobile Phone Network Code :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="networkCode"
										id="networkCode" size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Mobile Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="mobile" id="mobile"
										size="25px;" />
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
										size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Address Line 2 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="address2" id="address2"
										size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Address Line 3 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="address3" id="address3"
										size="50px;" />
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Country :</h1>
								</div>
								<div class="col-name" id="country-List"></div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Town :</h1>
								</div>
								<div class="col-name" id="town-List"></div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Web Link :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="webLink" id="webLink"
										size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Facebook URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="facebook" id="facebook"
										size="25px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">LinkedIn URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="linkdedIn"
										id="linkdedIn" size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Twitter URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="twitter" id="twitter"
										size="25px;" />
								</div>
							</li>
							<!-- end -->


							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Instagram URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="instagram"
										id="instagram" size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">MySpace URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="mySpace" id="mySpace"
										size="25px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">WhatsApp Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="whatsapp" id="whatsapp"
										size="25px;" />
								</div>
								<div class="col-name">
									<h1 class="pro-name">Viber Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="viber" id="viber"
										size="25px;" />
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
										id="expirationDate" size="50px;" />
								</div>
							</li>
							<!-- end -->


							<!-- select the course provider type -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Type :</h1>
								</div>
								<div class="col-name" id="providerTypeList"></div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Status :</h1>
								</div>
								<div class="col-name" style="width: 50%;">
									<c:forEach items="${applicationStatusBean.values}"
										var="applicationStatus">
										<input type="radio" name="providerStatus" id="providerStatus"
											value="${applicationStatus.statusValue}" /> ${applicationStatus}
									 </c:forEach>
									<!-- 
										<input type="radio" name="providerStatus" id="providerStatus"
											value="active" /> Active &nbsp; <input type="radio"
											name="providerStatus" id="providerStatus" value="inactive" />
										InActive &nbsp; <input type="radio" name="providerStatus"
											id="providerStatus" value="pending" /> Pending -->
								</div>
							</li>
							<!-- end -->

						</ul>
					</div>
					<!-- End filter result table -->
				</div>
				<!-- End left panel -->
				<br /> <br /> <input type="hidden" name="CCO" id="CCO" value="" />
				<!-- course filter panel : left side -->
				<div class="course-filter-panel" id="accountInfo">
					<h3 style="color: maroon;">Account Info</h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Name:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerPrivateName"
										id="providerPrivateName" size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Email:</h1>
								</div>
								<div class="col-name">
									<input class="input" type="email" name="providerEmail"
										id="providerEmail" size="50px;" />
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Username :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerUsername"
										id="providerUsername" size="50px;"
										onfocusout="providerUsernameValidation();" />
										<div id="errorUsername" name = "errorUsername"></div>
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
								<div class="col-name" style="width: 50%;">
									<h1 class="pro-name">
										<input type="radio" name="accountStatus" id="accountStatus"
											value="active" /> Active &nbsp; <input type="radio"
											name="accountStatus" id="accountStatus" value="inactive" />
										InActive &nbsp;
									</h1>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Description :</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="5" cols="90"
										name="accountDescription" id="accountDescription"></textarea>
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
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">

							<li class="course-info clearfix">
								<div class="col-name" style="width: 50%;">
									<input type="button" id="viewNext" value="Next"
										class="btn btn-lg btn-info" onclick="getProviderType();" />
								</div>
							</li>
							<!-- end -->
						</ul>
					</div>
					<!-- End left panel -->
		</form>

		<form action="/AdminController" method="POST">
			<!-- course filter panel : left side-->
			<div class="course-filter-panel" id="logoPanel">
				<h3>Course Provider Images</h3>
				<!-- Filter result table -->
				<div class="filter-result-table">
					<ul class="result-row">
						<li class="course-info clearfix" id="smallImg">
							<div class="col-name">
								<h1 class="pro-name">Logo image (Small) :</h1>
							</div>
							<div class="col-name">
								<input class="input" type="file" name="imageSmall"
									id="imageSmall" size="50px;" />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix" id="largeImg">
							<div class="col-name">
								<h1 class="pro-name">Logo image (Large) :</h1>
							</div>
							<div class="col-name">
								<input class="input" type="file" name="imageLarge"
									id="imageLarge" size="50px;" />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix" id="headerImg">
							<div class="col-name">
								<h1 class="pro-name">Header Image :</h1>
							</div>
							<div class="col-name">
								<input class="input" type="file" name="imageHeader"
									id="imageHeader" size="50px;" />
							</div>
						</li>
						<!-- end -->

						<li class="course-info clearfix" id="commonImg">
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
				<input type="submit" value="Add" class="btn btn-success" />
			</div>
			<!-- End left panel -->
		</form>
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
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="/dist/js/main.js"></script>

	<!-- W3-Include -->
	<script src="/dist/bower-components/w3/w3data.js"></script>

	<!-- custom javascript -->
	<script language="JavaScript" type="text/javascript"
		src="/dist/js/header/ui-populate-helper.js"></script>
		<script type="text/javascript" src="/dist/js/admin/course-provider-validator.js"></script>
	<script src="/dist/js/admin/load-provider-registration-page.js"></script>

</body>
</html>
