<!-- 20161121 JH c39-add-course-provider manageCourseProvider.jsp created -->
<!-- 20161122 JH c39-add-course-provider sample UI page to add course provider details wip -->
<!-- 20161123 JH c39-add-course-provider UI code modified -->
<!-- 20161202 JH c39-add-course-provider added missing input fields -->
<!-- 20161206 JH c39-add-course-provider create check boxes to account status -->
<!-- 20161208 JH c39-add-course-provider crev modifications -->
<!-- 20170224 DK c58-admin-manage-course-provider-ui-dk  Integrating the UI development -->
<!-- 20170224 JH c141-add-course-provider-issue-improvements changed error message ids -->
<!-- 20170227 JH c141-add-course-provider-issue-improvements removed asterisk mark from short name   -->
<!-- 20170228 JH c141-add-course-provider-issue-improvements enable one-off provider functions, removed expiration date   -->
<!-- 20170301 JH c141-add-course-provider-issue-improvements import error-handling.js javaScript file -->
<!-- 20170316 JH c141-ui-integration-add-course-provider merged c141-add-course-provider and c58-manage-course-provider-ui-dk, change html element
				 names to match with the back end validation in general information, contact information wip-->
<!-- 20170320 JH c141-ui-integration-add-course-provider change html element names to match with the back end validation in 
				contact information, social media and admin info wip-->
<!-- 20170321 JH c141-ui-integration-add-course-provider display error messages on country selection wip -->
<!-- 20170323 JH c141-ui-integration-add-course-provider added error-handling javascript to hand, changed the default error title -->
<!-- 20170324 JH c141-ui-integration-for-add-course-provider added id elements to div tags to show error messages wip -->
<!-- 20170327 JH c141-ui-integration-for-add-course-provider added <a> tag to show short name errors -->
<!-- 20170328 JH c141-ui-integration-for-add-course-provider added internal styles for success messages, added missing ids for password, cPassword and accountStatus -->
<!-- 20170329 JH c141-ui-integration-for-add-course-provider changed phone number related ids to match with front end validation classes -->
<!-- 20170330 JH c141-ui-integration-for-add-course-provider call landPhoneNubmerHelper() on change of phone number fields wip, disabled area code inputs except
					land number one -->
<!-- 20170331 JH c141-ui-integration-for-add-course-provider changed the id of the datalist to countryresults -->
<!-- 20170402 JH c141-ui-integration-for-add-course-provider added error element for country list, added select tag for course provider types -->
<!-- 20170403 JH c141-ui-integration-for-add-course-provider removed onchange function from the country list, code changes to implement town list wip 
					added hidden inputs to store country and town -->
<!-- 20170404 JH c141-ui-integration-for-add-course-provider changed course provider type value to pass the enum value instead of the type value -->
<!-- 20170405 JH c141-ui-integration-for-add-course-provider added parameter registeredId to the "upload-logo-modal" modal to display registered id of the course provider on successful registration -->
<!-- 20170406 JH c141-ui-integration-for-add-course-provider added input max length values for phone number fields, span tags for contact number and weblink, added min max password hints -->
<!-- 20170407 JH c141-ui-integration-for-add-course-provider added a success message to display with the modal body when a course provider is registered -->
<!-- 20170417 JH c141-ui-integration-for-add-course-provider added name attribute and the value for weblink prefix -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="applicationStatusBean"
	class="com.genesiis.campus.validation.ApplicationStatusBean" />
<jsp:useBean id="accountTypeBean"
	class="com.genesiis.campus.validation.AccountTypeBean" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>
<!-- Page & Layout Styles -->
<link href="/dist/css/style.css" rel="stylesheet">
<!-- Bootstrap Styles -->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<!-- FontAwsome Font Styles -->
<link rel="stylesheet"
	href="/dist/bower-components/fav/css/font-awesome.min.css">
	<style type="text/css">
#userMessage:empty {
	display: none;
}

.has-success input,.has-success text-area,.has-success select {
	border: 1px solid #1eab37 !important;
}

.has-success .error-info {
	visibility: visible;
	z-index: 9999;
}
</style>
</head>

<body class="admin-page">
	<!-- include Header-->
	 <jsp:include
		page="/dist/partials/layout/header.jsp"></jsp:include> 
	<!-- End Header -->


	<!-- Main Container - Higher-Education -->
	<div class="main-category clearfix">

		<div class="container course-provider-registration admin">
			<div class="form-wrapper">
				<h2 class="form-title">
					<span class="fa fa-pencil" aria-hidden="true"></span> Register a
					Course Provider <span class="form-intro">Please fill the
						form below to add a course provider (Complete all sections)</span>
				</h2>

				<form class="form form-admin has-error-form" 
					 name="basicForm" id="basicForm" method="POST" action=""> 	

		<input type="hidden" name="CCO" id="CCO" value="ADD_FEATURED_COURSE_PROVIDER" />
					<div class="alert alert-danger fade in" id="userMessage"></div>

					<div class="accordion">

						<h4 class="form-section-title accordion-header" id="generalInfoSection">1. General
							Information</h4>
						<div class="accordion-body" id="generalInfoSectionDiv">
							<div class="row clearfix">								
								<div class="form-group col-sm-12 input-wrapper">
								<div>
								<label for="account-category" class="right-padding inline-info">
										<span class="mandatory">*</span> Account Category <a
										class="error-info" href="#" data-toggle="tooltip" id="errorCourseProvider"
										title="Error! " data-placement="top"></a>
									</label>
									</div>
								<c:forEach items="${accountTypeBean.values}" var="accountTypes">
								<label class="radio-inline radio-lbl">
										<c:if test="${accountTypes.typeValue == 1}">
											<input type="radio" name="courseProvider" id="courseProvider"
												value="${accountTypes}" checked="checked"
												onchange="changeRequiredData('${accountTypes}');" /> ${accountTypes} &nbsp;
										</c:if>
										<c:if test="${accountTypes.typeValue != 1}">
											<input type="radio" name="courseProvider" id="courseProvider"
												value="${accountTypes}" 
												onchange="changeRequiredData('${accountTypes}');" /> ${accountTypes} &nbsp;
										</c:if>
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group col-sm-4">
									<label for="course-provider"><span class="mandatory">*</span>
										Course Provider Name</label>
									<div class="input-wrapper" id="providerNameDiv">
										<input name="providerName" type="text" class="form-control"
											id="providerName"> <a
											class="error-info" href="#" data-toggle="tooltip" id="errorProviderName"
											title=""></a>
									</div>
								</div>
								<div class="form-group col-sm-4">
									<label for="shorten-name">Shorten Name</label>
									<div class="input-wrapper" id="shortNameDiv">
										<input name="shortName" id="shortName" type="text" class="form-control"
											id="shorten-name" placeholder=""> <a
											class="error-info" href="#" data-toggle="tooltip" id="errorShortName"
											title=""></a>
									</div>
								</div>
								<div class="form-group col-sm-4">
									<label for="unique-prefix"><span class="mandatory">*</span>	Unique Prefix</label>
									<div class="input-wrapper" id="uniquePrefixDiv" >
										<input name="uniquePrefix" type="text" class="form-control" id="uniquePrefix" placeholder="" onblur="providerPrefixValidation();"> 
										<a id="errorUniquePrefix" class="error-info" data-toggle="tooltip" title="Error! "></a>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="form-group col-sm-6">
									<label for="company-profile"><span class="mandatory">*</span>
										Company Profile</label>
									<div class="input-wrapper" id="aboutMeDiv">
										<textarea name="aboutMe" class="form-control"
											id="aboutMe" rows="4"></textarea>
										<a class="error-info" href="#" data-toggle="tooltip" id="errorAboutMe"
											title="Error! "></a>
									</div>
								</div>
								<div class="form-group col-sm-6">
									<label for="special-features">Special Features</label>
									<div class="input-wrapper" id="specialFeaturesDiv">
										<textarea name="specialFeatures" class="form-control"
											id="specialFeatures" rows="4"></textarea>
											<a class="error-info" href="#" data-toggle="tooltip" id="errorSpecialFeatures"
											title="Error! "></a>
									</div>
								</div>
							</div>
						</div>

						<h4 class="form-section-title accordion-header" id="contactInfoSection">2. Contact
							Information</h4>
						<div class="accordion-body" id="contactInfoSectionDiv">
							<div class="row clearfix">
								<div class="col-sm-4">
									<div class="row clearfix">
										<div class="col-xs-12">
											<div class="row clearfix">
											<div class="col-xs-6">
											<div>
												<label for="select-country"><span class="mandatory">*</span>
													Country</label>
														</div>
													<div class="input-wrapper has-select" id="country-List">
														<input name="countries" id="countries" class="form-control" list="countryresults">
														<datalist id="countryresults" class="select-country"></datalist>
														
														<a class="error-info" href="#" data-toggle="tooltip"
															title="Error! " id="errorSelectedCountry"></a>
														<input type="hidden" name="selectedCountry"	id="selectedCountry" value=""/>
													</div> 

											</div>
											<div class="col-xs-6">
													<label for="select-city"><span class="mandatory">*</span>
														City</label>
													<div class="input-wrapper has-select" id="town-List">
													<input name="towns" id="towns" class="form-control" list="townresults">
													<datalist id="townresults" class="select-city"></datalist>
													<a class="error-info" href="#" data-toggle="tooltip"
															title="Error! " id="errorSelectedTown"></a>
													<input type="hidden" name="selectedTown" id="selectedTown" value=""/>		
													</div>
												</div>
											</div>
										</div>
										<div class="col-xs-12">
											<div class="row clearfix">
												<div class="form-group col-xs-12">
													<label for="address-line-1"><span class="mandatory">*</span>
														Street Address - Line 1</label>
													<div class="input-wrapper" id="address1Div">
														<input name="address1" type="text"
															class="form-control" id="address1" placeholder="">
														<a class="error-info" href="#" data-toggle="tooltip" id="errorAddress1"
															title="Error! "></a>
													</div>
												</div>
												<div class="form-group col-xs-12">
													<label for="address-line-2">Street Address - Line 2</label>
													<div class="input-wrapper" id="address2Div">
														<input name="address2" type="text"
															class="form-control" id="address2" placeholder="">
														<a class="error-info" href="#" data-toggle="tooltip" id="errorAddress2"
															title="Error! "></a>
													</div>
												</div>
												<div class="form-group col-xs-12">
													<label for="address-line-3">Street Address - Line 3</label>
													<div class="input-wrapper" id="address3Div">
														<input name="address3" type="text"
															class="form-control" id="address3" placeholder="">
														<a class="error-info" href="#" data-toggle="tooltip" id="errorAddress3"
															title="Error! "></a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="row clearfix">
										<div class="form-group col-xs-12">
											<label for="land-no-1"><span class="mandatory">*</span>
												Land Number 1</label>
											<div class="input-wrapper input-group" id="land1Div">
												<input name="countryCode" type="text"
													class="col-xs-3 input-border-r disabled-input"
													id="countryCode" placeholder="" disabled>
												<input name="areaCode" type="text"
													class="col-xs-3 input-border-r" id="areaCode"
													placeholder="" onkeyup="landPhoneNubmerHelper();" maxlength="4" size="4">
												 <input name="land1" type="tel"  onkeyup="landPhoneNubmerHelper();"
													class="col-xs-6" id="land1" placeholder=""  maxlength="12" size="12">
												 <span class="phone-no-hint" id="landNumber1"></span>
												 <a
													class="error-info" href="#" data-toggle="tooltip" 
													title="Error! " id="errorLand1"></a>
											</div>
										</div>
										<div class="form-group col-xs-12">
											<label for="land-no-2">Land Number 2</label>
											<div class="input-wrapper input-group" id="land2Div">
												<input name="countryCode" type="text"
													class="col-xs-3 input-border-r disabled-input"
													id="countryCode" placeholder="" disabled>
												<input name="areaCode2" type="text"
													class="col-xs-3 input-border-r" id="areaCode2"
													placeholder="" disabled maxlength="4" size="4">
												<input name="land2" type="tel" onkeyup="landPhoneNubmerHelper();"
													class="col-xs-6" id="land2" placeholder=""  maxlength="12" size="12">
												 <span class="phone-no-hint" id="landNumber2"></spans>
												<a class="error-info" href="#" data-toggle="tooltip" 
													title="Error! " id="errorLand2"></a>
											</div>
										</div>
										<div class="form-group col-xs-12">
											<label for="mobile-number"><span class="mandatory">*</span>
												Mobile Number</label>
											<div class="input-wrapper input-group" id="mobileDiv">
												<input name="countryCode" type="text"
													class="col-xs-3 input-border-r disabled-input"
													id="countryCode" placeholder="" disabled>
												<input name="networkCode" type="text" onkeyup="landPhoneNubmerHelper();"
													class="col-xs-3 input-border-r" id="networkCode"
													placeholder="" maxlength="4" size="4"> 
												<input name="mobile"  onkeyup="landPhoneNubmerHelper();"
													type="tel" class="col-xs-6" id="mobile" placeholder=""  maxlength="12" size="12">
												<span class="phone-no-hint" id="lastMobileNumber"></span> <a
													class="error-info" href="#" data-toggle="tooltip" id="errorMobile"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-xs-12">
											<label for="fax-number">Fax Number</label>
											<div class="input-wrapper input-group" id="faxDiv">
												<input name="countryCode" type="text"
													class="col-xs-3 input-border-r disabled-input"
													id="countryCode" placeholder="" disabled> 
												<input name="areaCode2" type="text" maxlength="4" size="4"
													class="col-xs-3 input-border-r" id="areaCode2"
													placeholder="" disabled> <span class="phone-no-hint" id="lastFaxNumber"></span> 
												<input name="fax" type="tel" class="col-xs-6"  onkeyup="landPhoneNubmerHelper();"
													id="fax" placeholder="" maxlength="12" size="12">
												<a class="error-info" href="#" data-toggle="tooltip" id="errorFax"
													title="Error! "></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="row clearfix">
										<div class="form-group col-xs-12">
											<label for="general-email"><span class="mandatory">*</span>
												General Email</label>
											<div class="input-wrapper" id="generalEmailDiv">
												<input name="generalEmail" type="email" class="form-control"
													id="generalEmail" placeholder=""> <a
													class="error-info" href="#" data-toggle="tooltip" id="errorGeneralEmail"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-xs-12">
											<label for="inquiry-email"><span class="mandatory">*</span>
												Course Inquiry Email</label>
											<div class="input-wrapper" id="inquiryMailDiv">
												<input name="inquiryMail" type="email" class="form-control"
													id="inquiryMail" placeholder=""> <a
													class="error-info" href="#" data-toggle="tooltip" id="errorInquiryMail"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-xs-12">
											<label for="web-link">Web Link</label>
											<div class="input-wrapper input-group" id="webLinkDiv">
											<span class="input-group-addon" name="web-basic-addon" id="web-basic-addon" value="http://">http://</span>
											<input name="webLink" type="url" class="form-control"
													id="webLink" placeholder=""> 
													<a class="error-info" href="#" data-toggle="tooltip" id="errorWebLink"
													title="Error! "></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<h4 class="form-section-title accordion-header" id="socialMedialSection">3. Social
							Media Information</h4>
						<div class="accordion-body" id="socialMedialSectionDiv">
							<div class="row clearfix">
								<div class="col-sm-4 col-sm-offset-2">
									<div class="row">
										<div class="form-group col-sm-11 col-xs-12">
											<label for="facebook-url">Facebook URL</label>
											<div class="input-wrapper" id="facebookDiv">
												<input name="facebook" type="url" class="form-control"
													id="facebook" placeholder="">
													<a class="error-info" href="#" data-toggle="tooltip" id="errorFacebook" title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-11 col-xs-12">
											<label for="linkedin-url">LinkedIn URL</label>
											<div class="input-wrapper" id="linkdedInDiv">
												<input name="linkdedIn" type="url" class="form-control"
													id="linkdedIn" placeholder="">
													<a class="error-info" href="#" data-toggle="tooltip" id="errorLinkedIn"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-11 col-xs-12">
											<label for="twitter-url">Twitter URL</label>
											<div class="input-wrapper" id="twitterDiv">
												<input name="twitter" type="url" class="form-control"
													id="twitter" placeholder="">
													<a class="error-info" href="#" data-toggle="tooltip" id="errorTwitter"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-11 col-xs-12">
											<label for="instagram-url">Instagram URL</label>
											<div class="input-wrapper" id="instagramDiv">
												<input name="instagram" type="url" class="form-control"
													id="instagram" placeholder="">
													<a class="error-info" href="#" data-toggle="tooltip" id="errorInstagram"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-11 col-xs-12">
											<label for="myspace-url">MySpace URL</label>
											<div class="input-wrapper" id="mySpaceDiv">
												<input name="mySpace" type="url" class="form-control"
													id="mySpace" placeholder="">
													<a class="error-info" href="#" data-toggle="tooltip" id="errorMyspace"
													title="Error! "></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-5">
									<div class="row">
										<div class="form-group col-sm-9 col-xs-12 col-sm-offset-2">
											<label for="whatsapp-no">WhatsApp Number</label>
											<div class="input-wrapper" id="whatsappDiv">
												<input name="whatsapp" type="tel" class="form-control"
													id="whatsapp" placeholder="">
													<a class="error-info" href="#" data-toggle="tooltip" id="errorWhatsapp"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-9 col-xs-12 col-sm-offset-2">
											<label for="viber-no">Viber Number</label>
											<div class="input-wrapper" id="viberDiv">
												<input name="viber" type="tel" class="form-control"
													id="viber" placeholder="">
													<a class="error-info" href="#" data-toggle="tooltip" id="errorViber"
													title="Error! "></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<h4 class="form-section-title accordion-header" id="adminInfoSection">4. Admin Information</h4>
						<div class="accordion-body" id="adminInfoSectionDiv">
							<div class="row clearfix">
								<div class="form-group col-sm-12 text-center">
									<label for="course-provider-type"><span class="mandatory">*</span> Course Provider Type</label>
									<div class="input-wrapper has-select text-center" id="providerTypeList">
										<div class="inner-input-wrapper">
											<select id="selectedProviderType" name="selectedProviderType" ></select>											
											<a class="error-info" href="#" data-toggle="tooltip" id="errorProviderType"	title="Error! "></a>
										</div> 
									</div>
									
								</div>
							</div>
							<div class="row clearfix">			
								<div class="form-group col-sm-12 text-center center-block input-wrapper" id="providerStatusDiv">
									<label for="course-provider-status"	class="text-center center-block top-padding inline-info">
										<span class="mandatory">*</span> Course Provider Status 
										<a class="error-info" href="#" data-toggle="tooltip" id="errorProviderStatus" title="Error! "></a>
									</label>
									<c:forEach items="${applicationStatusBean.values}" var="applicationStatus">										
										<label class="radio-inline radio-lbl">
										<input type="radio" name="providerStatus"  id="providerStatus" value="${applicationStatus.statusValue}">${applicationStatus}</label>
									</c:forEach>									 
								</div>
							</div>
						</div>

						<h4 class="form-section-title accordion-header"  id="accountInfoSection">5. Account
							Information (Authorized Personnel)</h4>
						<div class="accordion-body" id="accountInfoSectionDiv">
							<div class="row clearfix">
								<div class="col-sm-6">
									<div class="row">
										<div class="form-group col-sm-8 col-sm-offset-2">
											<label for="full-name"><span class="mandatory">*</span>
												Full Name</label>
											<div class="input-wrapper" id="providerPrivateNameDiv">
												<input name="providerPrivateName" type="text" class="form-control"
													id="providerPrivateName"> <a
													class="error-info" href="#" data-toggle="tooltip" id="errorPrivateName"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-8 col-sm-offset-2">
											<label for="contract-no"><span class="mandatory">*</span>
												Contact Number</label>
											<div class="input-wrapper input-group" id="providerContactNumberDiv">
											<span class="input-group-addon" id="countryCode2"></span>
												<input name="providerContactNumber" type="text" class="form-control"
													id="providerContactNumber" placeholder="" maxlength="12" size="12"> <a
													class="error-info" href="#" data-toggle="tooltip" id="errorContactNumber"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-8 col-sm-offset-2">
											<label for="account-email"><span class="mandatory">*</span>
												Email</label>
											<div class="input-wrapper" id="providerEmailDiv">
												<input name="providerEmail" type="text" class="form-control"
													id="providerEmail" placeholder=""> <a
													class="error-info" href="#" data-toggle="tooltip" id="errorPrivateEmail"
													title="Error! "></a>
											</div>
										</div>
										<div class="form-group col-sm-8 col-sm-offset-2">
											<label for="account-desc">Description</label>
											<div class="input-wrapper" id="accountDescriptionDiv">
												<textarea name="accountDescription" class="form-control"
													id="accountDescription" rows="3"></textarea>
													<a class="error-info" href="#" data-toggle="tooltip" id="errorAccountDescription"
													title="Error! "></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="row">
										<div class="border-wrapper col-sm-12 center-block">
											<div class="row">
												<div class="form-group col-sm-8 col-sm-offset-2">
													<label for="login-name"><span class="mandatory">*</span>
														Username</label>
													<div class="input-wrapper" id="usernameDiv">
														<input name="providerUsername" type="text"
															class="form-control" id="providerUsername" placeholder="" onblur="providerUsernameValidation();">
														<a class="error-info" href="#" data-toggle="tooltip" id="errorUsername"
															title="Error! "></a>
													</div>
												</div>
												<div class="form-group col-sm-8 col-sm-offset-2">
													<label for="login-pwd"><span class="mandatory">*</span>
														Password</label>
													<div class="input-wrapper" id="providerPasswordDiv">
														<input name="providerPassword" type="password"
															class="form-control" id="providerPassword" placeholder="">
															<span class="phone-no-hint"> min:6  and max:100</span> 
														<a class="error-info" href="#" data-toggle="tooltip" id="errorProviderPassword"
															title="Error! "></a>
													</div>
												</div>
												<div class="form-group col-sm-8 col-sm-offset-2">
													<label for="confirm-pwd"><span class="mandatory">*</span>
														Confirm Password</label>
													<div class="input-wrapper" id="cProviderPasswordDiv">
														<input name="cProviderPassword" type="password"
															class="form-control" id="cProviderPassword" placeholder="">
														<a class="error-info" href="#" data-toggle="tooltip" id="errorCProviderPassword"
															title="Error! "></a>
													</div>
												</div>
												<div class="form-group col-sm-8 col-sm-offset-2 input-wrapper" id="accountStatusDiv">
												<label for="account-category"
													class="right-padding inline-info"> <span
													class="mandatory">*</span> Account Status
												</label> <a class="error-info" href="#"
													data-toggle="tooltip" id="errorStatus" title="Error! "></a>

												<div id="accountStatusList"></div>
											</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

			 	<div class="row clearfix">
				 	<!--
				 		<button type="button" class="btn btn-register pull-right">
				 			<i class="fa fa-times fa-3x" aria-hidden="true" onclick="saveCourseProvider();"></i> Add Course	provider
						</button>
					-->				
					<input type="button" id="viewNext" value="Proceed Next" class="btn btn-next pull-right" onclick="saveCourseProvider();" />
				</div> 

				</form>		
				<div id="upload-logo-modal" class="modal fade">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<div style="color:green;">
									<label>Course provider registered and the code is</label><input type="text" id="registeredId" name="registeredId" value="" readonly/>
								</div>							
								<button class="close" type="button" data-dismiss="modal">×</button>
								<h4 class="modal-title">Upload Course Provider Logo</h4>
							</div>
							<div class="modal-body">This is the test modal pop-up.</div>
							<div class="modal-footer"></div>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>
	<!-- End Main Container -->

	<!-- Footer -->
	<footer> <jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	</footer>

	<!-- jQuery & Other js -->
	<!--  <script src="/dist/bower-components/jquery/jquery.min.js"></script> -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/jquery-ui/jquery-ui.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="/dist/js/main.js"></script>

	<!-- W3-Include -->
	<script src="/dist/bower-components/w3/w3data.js"></script>

	<!-- custom javascript -->
	<!-- <script language="JavaScript" type="text/javascript" src="/dist/js/header/ui-populate-helper.js"></script> -->

	<script src="/dist/js/admin/course-provider-validator.js"></script>
	<script src="/dist/js/admin/load-provider-registration-page.js"></script>
	<script  src="/dist/js/error-handling.js"type="text/javascript"></script>

</body>
</html>