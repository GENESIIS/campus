<!-- 20161121 JH c39-add-course-provider manageCourseProvider.jsp created -->
<!-- 20161122 JH c39-add-course-provider sample UI page to add course provider details wip -->
<!-- 20161123 JH c39-add-course-provider UI code modified -->
<!-- 20161202 JH c39-add-course-provider added missing input fields -->
<!-- 20161206 JH c39-add-course-provider create check boxes to account status -->
<!-- 20161208 JH c39-add-course-provider crev modifications -->
<!-- 20170224 JH c141-add-course-provider-issue-improvements changed error message ids -->
<!-- 20170227 JH c141-add-course-provider-issue-improvements removed asterisk mark from short name   -->

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
<link href="/dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">

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
		<form action="/dist/partials/admin/courseProviderManagement.jsp" method="POST" id="basicForm">
			<!-- Page content -->

				<input type="hidden" name="generatedId" id="generatedId" value=""/>
			<div class="content-holder center-block clearfix">
					<div class="alert alert-danger" role="alert" id="userMessage" name="userMessage">
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
									<h1 class="pro-name">Account Type : <span style="color: red;">*</span></h1>
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
									<span id="errorCourseProvider" class="error-message"></span>
								</div>
							</li>
							<!-- end -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Name: <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerName"
										id="providerName" size="50px;" /><span id="errorProviderName"
										class="error-message">${errorProviderName }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">
										Unique Name: <span style="color: red;">*</span>
									</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="uniquePrefix"
										id="uniquePrefix" size="30px;"
										onblur="providerPrefixValidation();" /> <span
										id="errorUniquePrefix" class="error-message">${errorUniquePrefix }</span>
									<span id="prefixMessage" style="color: blue;"></span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">
										Short Name:
									</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="shortName"
										id="shortName" size="30px;" /> <span id="errorShortName"
										class="error-message">${errorShortName }</span>
								</div>
							</li>
							<!-- end -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">
										Company Profile: <span style="color: red;">*</span>
									</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="8" cols="90" name="aboutMe"
										id="aboutMe"></textarea>
									<span id="errorAboutMe" class="error-message">${errorAboutMe }</span>
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
									<span id="errorSpecialFeatures" class="error-message">${errorSpecialFeatures }</span>
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
									<h1 class="pro-name">General Email : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="generalEmail"
										id="generalEmail" size="25px;" /> <span
										id="errorGeneralEmail" class="error-message">${errorGeneralEmail }</span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">Course Inquiry Email : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="inquiryMail"
										id="inquiryMail" size="25px;" /> <span id="errorInquiryMail"
										class="error-message">${errorInquiryMail }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Country : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name" id="country-List"></div> <span
								id="errorSelectedCountry" class="error-message">${errorSelectedCountry }</span>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Town : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name" id="town-List"></div> <span
								id="errorSelectedTown" class="error-message">${errorSelectedTown }</span>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Land Phone Area Code : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="areaCode" id="areaCode"
										onkeyup="landPhoneNubmerHelper();" size="25px;" /> <span
										id="errorAreaCode" class="error-message number-helper">${errorAreaCode }</span>
										<span>Ex: 11, 31, 81, .....</span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">Land Phone 1 : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="land1" id="land1"
										size="25px;" onkeyup="landPhoneNubmerHelper();" /> <span
										id="errorLand1" class="error-message number-helper">${errorLand1 }</span> <span
										id="landNumber1" style="color: blue;" class="number-helper"></span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Land Phone 2 :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="land2" id="land2"
										size="25px;" onkeyup="landPhoneNubmerHelper();" /> <span
										id="errorLand2" class="error-message number-helper">${errorLand2 }</span> <span
										id="landNumber2" style="color: blue;" class="number-helper"></span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">Fax Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="fax" id="fax"
										size="25px;" /> <span id="errorFax"
										class="error-message number-helper">${errorFax }</span>
										<span
										id="faxNumber" style="color: blue;" class="number-helper"></span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Mobile Phone Network Code : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="networkCode"
										id="networkCode" size="25px;"
										onkeyup="landPhoneNubmerHelper();" /> <span
										id="errorNetworkCode" class="error-message number-helper">${errorNetworkCode }</span>
										<span>Ex: 77, 71, 72, .....</span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">Mobile Number : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="mobile" id="mobile"
										size="25px;" onkeyup="landPhoneNubmerHelper();" /> <span
										id="errorLastMobileNumber" class="error-message number-helper">${errorMobile }</span>
									<span id="lastMobileNumber" style="color: blue;" class="number-helper"></span>
								</div>

							</li>
							<!-- end -->

							<li class="course-info clearfix"></li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Address Line 1 : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="address1" id="address1"
										size="50px;" /> <span id="errorAddress1"
										class="error-message">${errorAddress1 }</span>
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
									<h1 class="pro-name">Web Link :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="webLink" id="webLink"
										size="25px;" /> <span id="errorWebLink" class="error-message">${errorWebLink }</span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">Facebook URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="facebook" id="facebook"
										size="25px;" />. <span id="errorFacebook"
										class="error-message">${errorFacebook }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">LinkedIn URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="linkdedIn"
										id="linkdedIn" size="25px;" /> <span id="errorLinkedIn"
										class="error-message">${errorLinkedIn }</span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">Twitter URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="twitter" id="twitter"
										size="25px;" /> <span id="errorTwitter" class="error-message">${errorTwitter }</span>
								</div>
							</li>
							<!-- end -->


							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Instagram URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="instagram"
										id="instagram" size="25px;" /> <span id="errorInstagram"
										class="error-message">${errorInstagram }</span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">MySpace URL :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="mySpace" id="mySpace"
										size="25px;" /> <span id="errorMyspace" class="error-message">${errorMyspace }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">WhatsApp Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="whatsapp" id="whatsapp"
										size="25px;" /> <span id="errorWhatsapp"
										class="error-message">${errorWhatsapp }</span>
								</div>
								<div class="col-name">
									<h1 class="pro-name">Viber Number :</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="viber" id="viber"
										size="25px;" /> <span id="errorViber" class="error-message">${errorViber }</span>
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
									<h1 class="pro-name">
										<span style="color: red;">*</span>
									</h1>
								</div>
								<div class="col-name">
									<input type="radio" name="publishProgram" id="publishProgram"
										value="1" checked="checked" onclick="publishPrograms();" />
									Will Publish Programs
								</div>
								<div class="col-name">
									<input type="radio" name="publishProgram" id="publishProgram"
										value="0" onclick="publishPrograms();" /> Will Not publish
									Programs
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix" id="expire-date">
								<div class="col-name">
									<h1 class="pro-name">Expiration Date :</h1>
								</div>
								<div class="col-name">
									<input class="textarea" type="Date" name="expirationDate"
										id="expirationDate" size="50px;" /> <span
										id="errorExpiration" class="error-message">${errorExpiration }</span>
								</div>
							</li>
							<!-- end -->


							<!-- select the course provider type -->
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Type : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name" id="providerTypeList"></div> <span
								id="errorProviderType" class="error-message">${errorProviderStatus }</span>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Course Provider Status : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name" style="width: 50%;">
									<c:forEach items="${applicationStatusBean.values}"
										var="applicationStatus">
										<input type="radio" name="providerStatus" id="providerStatus"
											value="${applicationStatus.statusValue}" /> ${applicationStatus}
									 </c:forEach>
									<span id="errorProviderStatus" class="error-message">${errorProviderStatus }</span>
								</div>
							</li>
							<!-- end -->

						</ul>
					</div>
					<!-- End filter result table -->
				</div>
				<!-- End left panel -->
				<br /> <br /> <input type="hidden" name="CCO" id="CCO" value="ADD_FEATURED_COURSE_PROVIDER" />
				<!-- course filter panel : left side -->
				<div class="course-filter-panel" id="accountInfo">
					<h3 style="color: maroon;">Account Info | Authorized Person </h3>
					<!-- Filter result table -->
					<div class="filter-result-table">
						<ul class="result-row">
							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Name : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerPrivateName"
										id="providerPrivateName" size="50px;" /> <span
										id="errorPrivateName" class="error-message">${errorPrivateName }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Contact Number : <span style="color: red;">*</span>
									</h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerContactNumber"
										id="providerContactNumber" size="50px;" /> <span
										id="errorContactNumber" class="error-message">${errorContactNumber }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Email : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="email" name="providerEmail"
										id="providerEmail" size="50px;" /> <span
										id="errorPrivateEmail" class="error-message">${errorProviderEmail }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Username : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="text" name="providerUsername"
										id="providerUsername" size="50px;"
										onblur="providerUsernameValidation();" /> <span
										id="errorUsername" class="error-message">${errorProviderUsername }</span>
										<span id="usernameMessage" style="color: blue;"></span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Password : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="password" name="providerPassword"
										id="providerPassword" size="50px;" required /> <span
										id="errorProviderPassword" class="error-message">${errorProviderPassword }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Confirm Password : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name">
									<input class="input" type="password" name="cProviderPassword"
										id="cProviderPassword" size="50px;" required /> <span
										id="errorCProviderPassword" class="error-message">${errorCProviderPassword }</span>
								</div>
							</li>
							<!-- end -->

							<li class="course-info clearfix"><div class="col-name">
									<h1 class="pro-name">Account status : <span style="color: red;">*</span></h1>
								</div>
								<div class="col-name" style="width: 50%;">
									<h1 class="pro-name" id="accountStatusList">		
									</h1>
									<span id="errorStatus" class="error-message">${errorStatus }</span>
								</div></li>
							<!-- end -->

							<li class="course-info clearfix">
								<div class="col-name">
									<h1 class="pro-name">Description :</h1>
								</div>
								<div class="col-name">
									<textarea class="textarea" rows="5" cols="90"
										name="accountDescription" id="accountDescription"></textarea>
									<span id="errorAccountDescription" class="error-message">${errorAccountDescription }</span>
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
										class="btn btn-lg btn-info" onclick="saveCourseProvider();" />
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
<!-- 	<script language="JavaScript" type="text/javascript"
		src="/dist/js/header/ui-populate-helper.js"></script> -->
		<script type="text/javascript" src="/dist/js/admin/course-provider-validator.js"></script>
	<script src="/dist/js/admin/load-provider-registration-page.js"></script>

</body>
</html>
