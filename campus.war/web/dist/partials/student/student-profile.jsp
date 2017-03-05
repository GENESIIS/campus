<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 20161114 TR c25 start styling user profile page container  -->
<!-- 20161114 TR c25 page header - done  -->
<!-- 20161114 TR c25 profile-image-box - done  -->
<!-- 20170109 PN CAM-28: INIT the JSP file  -->
<!-- 20170109 PN CAM-28: modified textfield length with textfield attribute.  -->

<!-- 20170206 TR CAM-139: modified form html layout - Personal Details edit modal : added div structure  modal-input-field-->
<!-- 20170206 TR CAM-139: added modal-input-field css class to input field -->
<!-- 20170207 TR CAM-139: changed modal-header-title - Personal details into About Me -->
<!-- 20170207 TR CAM-139: modified html layout on About Me modal:Contact Details area - added div structure -->
<!-- 20170208 TR CAM-139: modified html layout on Professional Details modal - added div structure -->
<!-- 20170208 TR CAM-139: modified html layout on Education Details modal: school education tab - added div structure -->
<!-- 20170209 TR CAM-139: modified html layout on Education Details modal: Higher education tab - added div structure -->
<!-- 20170209 TR CAM-139: changed modal-footer layout on Education Details modal -->
<!-- 20170209 TR CAM-139: Added separate footers for school education and higher education tabs -->
<!-- 20170213 PN CAM-28: added data loading gif at the top of the page. added id name into full name header tag. -->
<!-- 20170213 PN CAM-28: modified UI element names to fix incorrect data loading on datatables -->
<!-- 20170213 PN CAM-28: modified Professional Details modal and Higher education tab by setting a hidden field to hold value for record ID. -->
<!-- 20170214 PN CAM-28: modified close buttons and clear button by adding on click functions to call, clear() methods. -->
<!-- 20170220 TR CAM-139: added jquery to fix modal scrolling issue -->
<!-- 20170220 TR CAM-139: Dropped all alert div into comment tag -->
<!-- 20170220 TR CAM-139: added new loading gif into page top (i/student/loading-1.gif) -->
<!-- 20170220 TR CAM-139: added new styles to gif loading area  -->
<!-- 20170220 TR CAM-139: modified styles in skills and interest widget  -->
<!-- 20170205 PN CAM-150: onkeypress="isCountryEmpty()" method called on sTown text field. -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="../../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/image-slides.css" rel="stylesheet">
    
    <!-- Data Table CSS -->
    <link href="../../datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../../datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">

	<!-- CheckBox CSS -->
	<link href="../../datatable/dataTables.checkboxes.css" rel="stylesheet" type="text/css">
	
	<!-- TagIt CSS-->
	<link href="../../css/tagit.css" rel="stylesheet" type="text/css">
	
    <!-- W3-Include -->
    <!--<script src="../../bower-components/w3/w3data.js"></script> -->

    <!-- jQuery & Other js -->
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
    
    <!-- Issue specific JS files. -->
    <script src="../../js/institute/validation/validation.js"></script>
    <script src="../../js/student/student-details-manipulation-helper.js"></script>
	<script src="../../js/student/student-details-helper.js"></script>
	<script src="../../js/student/ui-controller.js"></script>

	<!-- Data Table JS files -->
	<script src="../../datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

	<!-- CheckBox JS files -->
	<script src="../../datatable/dataTables.checkboxes.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../datatable/dataTables.checkboxes.min.js" type="text/javascript" charset="utf-8"></script>
	
	<!-- TagIt JS-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.20/angular.min.js"></script>
    <script src="../../tagit/bootstrap-tagsinput.min.js"></script>
    <script src="../../tagit/bootstrap-tagsinput-angular.min.js"></script>
   
</head>

<body>

<!-- Header-->
<header class="header col-lg-12 col-md-12 col-sm-12 clearfix">
    <div class="top">
        <div class="logo-brand">
            <h1 class="logo-txt">Campus.lk</h1>
        </div>
    </div>
    <div class="bottom">
        <div class="menu-bar">
            <div class="home pull-left">
                <a href="index.jsp" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">

                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="">All Courses</a></li>
                        <li><a href="">About Us</a></li>
                        <li><a href="">Contact Us</a></li>
                        <li><a href="dist/partials/student/student-dashboard.jsp">News</a></li>
                        <li><a href="">F & Q</a></li>
                        <li><a href="">Rss</a></li>
                    </ul>
                </div>
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <div class="bottom-menus" id="category-list">
                    <ul class="list-inline">
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" class="" value="LIST_CATEGORY_LANDING_PAGE">Pre School</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">School Education</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" class="" value="LIST_CATEGORY_LANDING_PAGE">Higher Education</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Corporate Training</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Vocational Training</button>
                            </form>
                        </li>
                        <li>
                            <form action="">
                                <button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE">Talent & Skill</button>
                            </form>
                        </li>
                    </ul>
                </div>
                <!-- End Course Category tabs -->
            </div>
            <div class="keyword-search pull-right">
                <div class="search-bar">
                    <input type="text" placeholder="Keyword Search">
                    <a href="javascript:" class="colr-white"></a>
                </div>
                <!-- End Keyword Search -->
                <div class="login-link">
                    <a href="javascript:">Login</a>

                </div>
            </div>
            <!-- End keyword search -->
        </div>
    </div>
</header>
<!--< End header -->

<div class="dashboard">
    <div class="stud-dashboard clearfix">
        <!-- CAM-137: Including data loading gif here. -->
        <div class="loading-gif" id="loading-gif-id">
            <img alt="" src="../../i/student/loading-1.gif">
        </div>
       <div class="prf-page-header col-md-12 col-lg-12 col-sm-12">
           <h1>| User Profile </h1>
       </div>
        <!-- End page header -->

        <div class="prf-page-container col-md-12 col-lg-12 col-sm-12 col-xs-12 clearfix">
            <div class="left-side col-md-3 col-lg-3 col-sm-12 clearfix">
                <div class="prf-pic-holder">
                    <div class="prf-image">
                        <img src="../../i/student/prf-pic.jpg" alt="Profile-Picture">
                    </div>
                    <!-- End profile image -->

                    <div class="prf-name">
                        <h2 id="fullname-hedding"></h2>
                    </div>
                    <!-- End profile name -->
                    <div class="follow-me">
                        <button class="btn-follow">Follow</button>
                    </div>
                    <!-- End follow-me button -->
                </div>
                <!-- end profile picture box -->

                <div class="short-info-holder">
                    <table class="tbl-left-info">
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name" id="td-value-aboutme"></td>
                        </tr>
                        <!-- End works at -->

                        <!-- End Notes -->
                    </table>
                </div>
            </div>
            <!-- End Left side panel -->

            <div class="right-side col-md-9 col-lg-9 col-sm-12 clearfix">
                <div class="widget w-about-me clearfix">
                    <div class="widget-header">
                        <label for="About">About</label>
                        <button class="editformdatabtn" data-toggle="modal" onclick="openPersonalDataModel()">Edit</button>
                    </div>
                    <!-- End widget header -->

                    <div class="widget-content clearfix">
                        <div class="w-left-panel col-sm-12 col-md-6 col-lg-6">
                            <table class="tbl-wgt-about">
                                <tr>
                                    <td class="td-name">User Name :</td>
                                    <td class="td-value" id="td-value-username"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">Full Name :</td>
                                    <td class="td-value" id="td-value-fullname"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">Birthday :</td>
                                    <td class="td-value" id="td-value-birthday"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">Gender :</td>
                                    <td class="td-value" id="td-value-gender"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">Email  :</td>
                                    <td class="td-value" id="td-value-email"></td>
                                </tr>
                            </table>
                        </div>
                        <!-- End about-me : left side -->

                        <div class="w-right-panel col-sm-12 col-md-6 col-lg-6">
                            <table class="tbl-wgt-about">
                                <tr>
                                    <td class="td-name">Country :</td>
                                    <td class="td-value" id="td-value-country"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">Town :</td>
                                    <td class="td-value" id="td-value-town"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">Address :</td>
                                    <td class="td-value" id="td-value-address"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">FB profile :</td>
                                    <td class="td-value" id="td-value-fbprofile"></td>
                                </tr>
                                <tr>
                                    <td class="td-name">Mobile NO :</td>
                                    <td class="td-value" id="td-value-mobileno"></td>
                                </tr>
                            </table>
                        </div>
                        <!-- End about-me : right side -->
                    </div>
                    <!-- End widget content -->
                </div>
                <!-- End Widget : ABOUT -->

                <div class="widgets-e">
                    <div class="pad-l-0 col-sm-12 col-md-6 col-lg-6">
                        <div class="widget w-experience">
                            <div class="widget-header">
                                <label for="">Experience</label>
                                <button class="editformdatabtn" data-toggle="modal" onclick="openProfExpDataModel()">Edit</button>
                            </div>

                            <div class="widget-content">
                                <ul class="ul-experience" id="li-std-experience">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--  End Experience Widget -->

                    <div class="pad-r-0 col-sm-12 col-md-6 col-lg-6">
                        <div class="widget w-education">
                            <div class="widget-header">
                                <label for="">Education</label>
                                <button class="editformdatabtn" data-toggle="modal" onclick="openEduDataModel()">Edit</button>
                            </div>
							<br>
                            <div class="widget-content">
                            	<span class="sub-top">- School Education</span>
                                <ul class="ul-education" id="li-std-schooledu">
                                </ul>
                                <br>
                                <span class="sub-top">- Higher Education</span>
                                <ul class="ul-education" id="li-std-higheredu">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- End Education Widget -->

                    <!-- End Activity Widget -->
                    
                      <div class="pad-l-0 col-sm-12 col-md-6 col-lg-6">
                        <div class="widget w-activity">
                            <div class="widget-header">
                                <label for="">Skills</label>
                            </div>
                            <br>
                            <div class="widget-content">
                            <!--<div id="studentSkillSaveStatus" name="studentSkillSaveStatus" class="alert alert-success"></div>-->
                                <div class="tagit-holder">
									<div class="tagit-block">
										<input type="text" class="tagit-in" id="studentSkills" name="studentSkills" placeholder="Type here"/>
									</div>
									<button class="btn btn-primary" onclick="addSkillDetails()">Save Changes</button>
								</div>
                            </div>

                        </div>
                    </div>
                     <!-- End Interest Widget -->
                    

                    <div class="pad-r-0 col-sm-12 col-md-6 col-lg-6">
                        <div class="widget w-skills">
                            <div class="widget-header">
                                <label for="">Interest</label>
                            </div>
                            <br>
                            <div class="widget-content">
                            <!--<div id="studentInterestSaveStatus" name="studentInterestSaveStatus" class="alert alert-success"></div>-->
								<div class="tagit-holder">
									<div class="tagit-block">
										<input type="text" class="tagit-in" id="studentInterests" name="studentInterests" placeholder="Type here"/>
									</div>
									<button class="btn btn-primary" onclick="addInterestsDetails()">Save Changes</button>
								</div>
                            </div>

                        </div>
                    </div>
                    <!-- End Skills Widget -->
                    
                </div>
            </div>
            <!-- End Right side -->


        </div>
        <!-- End page container -->
    </div>
</div>
<!-- End Dashboard  -->

<!-- Personal Details-->
<div class="modal modal-personal fade" id="studentPersonalDetailsModal" tabindex="-1" role="dialog" aria-labelledby="studentPersonalDetails" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="studentPersonalDetails"><label>About Me</label></h4>
      </div>
      <div class="modal-body">
      <!-- <div id="studentPersonalStatus" name="studentPersonalStatus" class="alert alert-success"></div> -->
      		<h2 class="sub-title-1">| Personal Details</h2>
      		<div class="well clearfix">
      		    <div class="show modal-input-field clearfix">
                    <label>First Name :</label>
                    <input type="text" name="sFullName" id="sFullName" onkeypress="return isLetter(event)" onkeypress="" onclick="clearField('sFullNameError')" maxlength="200">
                    <span id="sFullNameError" name="sFullNameError" style="color:red"></span><br>
			    </div>

			    <div class="show modal-input-field clearfix">
			        <label>Middle Name :</label>
                    <input type="text" name="sMiddleName" id="sMiddleName" onkeypress="return isLetter(event)" onclick="clearField('sMiddleNameError')" maxlength="200">
                    <span id="sMiddleNameError" name="sMiddleNameError" style="color:red"></span><br>
			    </div>

			    <div class="show modal-input-field clearfix">
			        <label>Last Name :</label>
                    <input type="text" name="sLastName" id="sLastName" onkeypress="return isLetter(event)" onclick="clearField('sLastNameError')" maxlength="200">
                    <span id="sLastNameError" name="sLastNameError" style="color:red"></span><br>
			    </div>

                <div class="show modal-input-field clearfix">
                    <label>Birth Date :</label>
                    <input type="date" name="sBirthDate" id="sBirthDate" onclick="clearField('sBirthDateError')"  onchange ="isPastfromNow('sBirthDate', 'sBirthDateError')">
                    <span id="sBirthDateError" name="sBirthDateError" style="color:red"></span><br>
                </div>

			    <div class="show modal-input-field clearfix">
			        <label class="pull-left l-h-26">Gender :</label>
                    <div class="btn-group-radio">
                        <input type="radio" name="gender" value="1"><span>Male</span>
                        <input type="radio" name="gender" value="0"><span>Female</span>
                    </div>
                </div>

			    <div class="show modal-input-field clearfix">
			        <label class="pull-left">About :</label>
                    <textarea type="text" name="sAboutMe" id="sAboutMe" onclick="clearField('sAboutMeError')" maxlength="200"></textarea>
                    <span id="sAboutMeError" name="sAboutMeError" style="color:red"></span><br>
			    </div>
			</div>
			<!-- End personal details area -->

            <br>
			<h2 class="sub-title-1">| Contact Details</h2>
			<div class="well">
			    <div class="show modal-input-field clearfix">
                    <label>Country :</label>
                    <input type="text" id="sCountry" name="sCountry" list="sCountryList" placeholder="-- Select Town --" onclick="clearField('sCountryError')"/>
                        <datalist name="sCountryList" id="sCountryList">
                        </datalist>
                    <input hidden type="text" id="sCountryCode" name="sCountryCode"/>
                    <span id="sCountryError" name="sCountryError" style="color:red"></span><br>
                </div>

                <div class="show modal-input-field clearfix">
                     <label>Town :</label>
                     <input type="text" id="sTown" name="sTown" list="sTownList" placeholder="-- Select Town --" onclick="clearField('sTownError')" onkeypress="isCountryEmpty()"/>
                     <datalist name="sTownList" id="sTownList"></datalist>
                     <input hidden type="text" id="sTownCode" name="sTownCode"/>
                     <span id="sTownError" name="sTownError" style="color:red"></span>
                </div>

                <div class="show modal-input-field clearfix">                 
                    <label>Address :</label>
                    <input type="text" name="sAddress" id="sAddress" onclick="clearField('sAddressError')" maxlength="200">
                    <span id="sAddressError" name="sAddressError" style="color:red"></span>
			    </div>

			    <div class="show modal-input-field clearfix">
                    <label>Mobile Number :</label>
                    <div class="input-group">
                        <span class="input-group-addon" id="countryCodePrefix"></span>
                        <input class ="phoneNum" type="text" name="sMobileNumber" id="sMobileNumber" onkeypress="return isNumber(event)" onclick="clearField('sMobileNumberError')" maxlength="20">
                    </div>
                    <span id="sMobileNumberError" name="sMobileNumberError" style="color:red"></span>
			    </div>

			    <div class="show modal-input-field clearfix">
                    <label>Home Number :</label>
                    <div class="input-group">
                    <span class="input-group-addon" id="countryCodePrefix"></span>
                    <input class ="phoneNum" type="text" name="sHomeNumber" id="sHomeNumber" onkeypress="return isNumber(event)" onclick="clearField('sHomeNumberError')" maxlength="20">
                    <span id="sHomeNumberError" name="sHomeNumberError" style="color:red"></span><br>
                    </div>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Email :</label>
                    <input type="text" name="sEmail" id="sEmail" onclick="clearField('sEmailError')" maxlength="200">
                    <span id="sEmailError" name="sEmailError" style="color:red"></span><br>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Facebook URL :</label>
                    <input type="text" name="sFacebookUrl" id="sFacebookUrl" onclick="clearField('sFacebookUrlError')" maxlength="200" placeholder="www.facebook.com/username">
                    <span id="sFacebookUrlError" name="sFacebookUrlError" style="color:red"></span><br>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Twitter URL :</label>
                    <input type="text" name="stwitterUrl" id="stwitterUrl" onclick="clearField('stwitterUrlError')" maxlength="200" placeholder="www.twitter.com/username">
                    <span id="stwitterUrlError" name="stwitterUrlError" style="color:red"></span><br>
			    </div>

			    <div class="show modal-input-field clearfix">
			        <label>LinkedIn URL :</label>
                    <input type="text" name="sLinkedInUrl" id="sLinkedInUrl" onclick="clearField('sLinkedInUrlError')" maxlength="200" placeholder="www.linkedin.com/username">
                    <span id="sLinkedInUrlError" name="sLinkedInUrlError" style="color:red"></span><br>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Instagram URL :</label>
                    <input type="text" name="sInstergramUrl" id="sInstergramUrl" onclick="clearField('sInstergramUrlError')" maxlength="200" placeholder="www.instagram.com/username">
                    <span id="sInstergramUrlError" name="sInstergramUrlError" style="color:red"></span><br>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>MySpace :</label>
                    <input type="text" name="smySpace" id="smySpace" onclick="clearField('smySpaceError')" maxlength="200" placeholder="www.myspace.com/username">
                    <span id="smySpaceError" name="smySpaceError" style="color:red"></span><br>
			    </div>

			    <div class="show modal-input-field clearfix">
			        <label>WhatsApp :</label>
                    <div class="input-group">
                    <span class="input-group-addon" id="countryCodePrefix"></span>
                    <input class ="phoneNum" type="text" name="sWhatsApp" id="sWhatsApp" onclick="clearField('sWhatsAppError')" onkeypress="return isNumber(event)" maxlength="20">
                    <span id="sWhatsAppError" name="sWhatsAppError" style="color:red"></span><br>
                    </div>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Viber :</label>
                    <div class="input-group">
                    <span class="input-group-addon" id="countryCodePrefix"></span>
                    <input class ="phoneNum" type="text" name="sViber" id="sViber" onclick="clearField('sViberError')" onkeypress="return isNumber(event)" maxlength="20">
                    <span id="sViberError" name="sViberError" style="color:red"></span><br>
                    </div>
                </div>
			</div>
			<!-- End Contact details area -->
	  </div>
	  <!-- End modal-body -->

	  <!-- Start Modal Footer -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-modal btn-close" onclick="clearPersonalDetailsForm()">Clear</button>
        <button type="button" class="btn btn-modal btn-close" data-dismiss="modal" onclick="clearPersonalDetailsForm()">Close</button>
        <button type="button" class="btn btn-modal btn-primary" id="" name="" onclick="addStudentPersonalDetails()">Save changes</button>
      </div>
      <!-- End Modal Footer -->

    </div>
  </div>
</div>
<!-- End personal details modal -->

<!-- School Education -->
<div class="modal fade" id="studentSchoolEducationModal" tabindex="-1" role="dialog" aria-labelledby="studentSchoolEducation" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4 class="modal-title" id="studentSchoolEducation">Education Details</h4>
      </div>
      <!-- End model header -->

      <div class="modal-body">

        <div class="well clearfix">
        <div id="exTab2" class="">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#1" data-toggle="tab">School Education</a></li>
                <li><a href="#2" data-toggle="tab">Higher Education</a></li>
            </ul>

            <div class="tab-content ">
                <div class="tab-pane active" id="1">
                    <p class="w-m-1">Please add you most highest completed school education details.</p>

                    <div class="content-body">
                        <!-- <div id="saveChangesStatus" name="saveChangesStatus" class="alert alert-success"></div> -->

                        <div class="show modal-input-field clearfix">
                            <label>Grade :</label>
                            <select id="sseQualification" name = "sseQualification" onchange="clearField('sseQualificationError')">
                                <option value="">--Select One--</option>
                            </select> <span id="sseQualificationError" name="sseQualificationError" style="color:red"></span>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>Subject Stream :</label>
                            <select id="sseStream" name = "sseStream" onchange="clearField('sseStreamError')">
                                <option value="">--Select One--</option>
                            </select> <span id="sseStreamError" name="sseStreamError" style="color:red"></span>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>Result :</label>
                            <select id="sseResult" name = "sseResult" onchange="clearField('sseResultError')">
                                <option value="">--Select One--</option>
                                <option value="1">Pass</option>
                                <option value="0">Fail</option>
                            </select> <span id="sseResultError" name="sseResultError" style="color:red"></span>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>Index No :</label>
                            <input type="text" name="sseIndexNo" id="sseIndexNo" onkeypress="return isNumber(event)" onclick="clearField('sseIndexNoError')" maxlength="20">
                            <span id="sseIndexNoError" name="sseIndexNoError" style="color:red"></span>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>School :</label>
                            <input type="text" name="sseSchool" id="sseSchool" onclick="clearField('sseSchoolError')" maxlength="200">
                            <span id="sseSchoolError" name="sseSchoolError" style="color:red"></span>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>Achieved on :</label>
                            <input type="date" name="" id="sseAchievedon" onclick="clearField('sseAchievedonError')">
                            <span id="sseAchievedonError" name="sseAchievedonError" style="color:red"></span>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>Medium :</label>
                            <select name="sseMedium" id="sseMedium" onchange="clearField('sseMediumError')">
                            <option value="">--Select One--</option>
                            </select><span name="sseMediumError" id="sseMediumError" style="color:red"></span>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>Country :</label>
                            <input type="text" id="sseCountry" name="sseCountry" list="sseCountryList" placeholder="-- Select Country --"/>
                            <datalist name="sseCountryList" id="sseCountryList"></datalist>
                        </div>

                        <div class="show modal-input-field clearfix">
                            <label>Description :</label>
                            <textarea rows="5" cols="40" name="sseDescription" id="sseDescription" maxlength="200"></textarea>
                        </div>                           
                    </div>
                    <!-- End content body: school education -->
                    
                    <div class="modal-footer sh-footer">
	                    <button type="button" class="btn btn-modal btn-close" onclick="clearSchoolEducationForm()">Clear</button>
	                    <button type="button" class="btn btn-modal btn-close" data-dismiss="modal" onclick="clearSchoolEducationForm()">Close</button>
	                    <button type="button" class="btn btn-modal btn-primary" id="saveSse" name="saveSse" onclick="addEducationDetails()">Save changes</button>
                    </div>
                    <!-- End School education footer -->
                </div>

                <div class="tab-pane" id="2">
	                <div class="content-body">
	                
		                <!-- <div id="saveChangesHigherEduStatus" name="saveChangesHigherEduStatus" class="alert alert-success"></div> -->
		                <input hidden name="highedu-id" id="highedu-id">
		                <div class="show modal-input-field clearfix">
			                <label>Institute of Study :</label>
			                <input type="text" name="instituteofStudy" id="instituteofStudy" onclick="clearField('instituteofStudyError')" maxlength="200">
			                <span id="instituteofStudyError" name="instituteofStudyError" style="color:red"></span><br>
		                </div>
		                
		                <div class="show modal-input-field clearfix">
			                <label>Affiliated Institute :</label>
			                <input type="text" name="affiliatedInstitute" id="affiliatedInstitute" onclick="clearField('affiliatedInstituteError')" maxlength="200">
			                <span id="affiliatedInstituteError" name="affiliatedInstituteError" style="color:red"></span>
		                </div>
		
						<div class="show modal-input-field clearfix">
			                <label>Area of study :</label>
			                <select id="areaofstudy" name = "areaofstudy" onchange="clearField('areaofstudyError')">
			                    <option value="">--Select One--</option>
			                </select> 
			                <span id="areaofstudyError" name="areaofstudyError" style="color:red"></span>
		                </div>
		               
		                <div class="show modal-input-field clearfix">
		                	<label>Award :</label>
			                <select id="award" name = "award" onchange="clearField('awardError')">
			                    <option value="">--Select One--</option>
			                </select> 
			                <span id="awardError" name="awardError" style="color:red"></span>
		                </div>
		                
		                <div class="show modal-input-field clearfix">
			                <label>Student ID (University Index) :</label>
			                <input type="text" name="studentId" id="studentId" onclick="clearField('studentIdError')" maxlength="200">
			                <span id="studentIdError" name="studentIdError" style="color:red"></span>
		                </div>
		                
		                <div class="show modal-input-field clearfix">
			                <label>GPA/Result :</label>
			                <input type="text" name="gpa" id="gpa" onclick="clearField('gpaError')" maxlength="2" onkeypress="return isNumber(event)">
			                <span id="gpa" name="gpa" style="color:red"></span>
		                </div>
						
						<div class="show modal-input-field clearfix">
			                <label>Commenced On :</label>
			                <input type="date" name="heCommencedOn" id="heCommencedOn" onclick="clearField('heCommencedOnError')" onchange ="checkDateRange('heCommencedOn','heCompletedOn','heCommencedOnError','heCompletedOnError')">
			                <span id="heCommencedOnError" name="heCommencedOnError" style="color:red"></span>
		                </div>
						
						<div class="show modal-input-field clearfix">
			                <label>Completed On :</label>
			                <input type="date" name="heCompletedOn" id="heCompletedOn" onclick="clearField('heCompletedOnError')" onchange ="checkDateRange('heCommencedOn','heCompletedOn','heCommencedOnError','heCompletedOnError')">
			                <span id="heCompletedOnError" name="heCompletedOnError" style="color:red"></span>
		                </div>
		
		                <div class="show modal-input-field clearfix">
			                <label>Medium :</label>
			                <select name="heMedium" id="heMedium" onchange="clearField('heMediumError')">
			                    <option value="">--Select One--</option>
			                </select><span name="heMediumError" id="heMediumError" style="color:red"></span>
		                </div>
		
		                <div class="show modal-input-field clearfix">
			                <label>Country :</label>
			                <input type="text" id="heCountry" name="heCountry" list="heCountryList" placeholder="-- Select Country --"/>
			                <datalist name="heCountryList" id="heCountryList"></datalist>
			                <input hidden id="heCountryvalue" name="heCountryvalue"/>
		                </div>
		
						<div class="show modal-input-field clearfix">
			                <label>Description :</label>
			                <textarea rows="5" cols="40" name="heDescription" id="heDescription" maxlength="200" ></textarea>
						</div>
		                <br/><br/>
		                
		                <div class="form-holder">
                        <form id="frm-hedu" action="/path/to/your/script" method="POST">

                            <table id="higherEdutbl"
                                class="table table-striped table-bordered dt-responsive nowrap"
                                cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th><input name="select_all" id="example-select-all" value="1" type="checkbox"></th>
                                        <th><b>Institute</b></th>
                                        <th><b>Certificate</b></th>
                                        <th><b>Result</b></th>
                                        <th><b>Medium</b></th>
                                        <th><b>Country</b></th>
                                        <th><b>Duration</b></th>
                                        <th><b>Description</b></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th></th>
                                        <th><b>Institute</b></th>
                                        <th><b>Certificate</b></th>
                                        <th><b>Result</b></th>
                                        <th><b>Medium</b></th>
                                        <th><b>Country</b></th>
                                        <th><b>Duration</b></th>
                                        <th><b>Description</b></th>
                                        <th></th>
                                    </tr>
                                </tfoot>
                            </table>
                            <p>
                                <button class="btn btn-modal btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete Selected</button>
                            </p>
                            <pre id="higherEdu-console"></pre>
                        </form>
                        </div> 
                        <br />                       	
		          	</div>
		          	<!-- End content body : higher education -->
		          	
					<div class="modal-footer sh-footer">
	                    <button type="button" class="btn btn-modal btn-close" onclick="clearHigherEducationForm()">Clear</button>
	                    <button type="button" class="btn btn-modal btn-close" data-dismiss="modal" onclick="clearHigherEducationForm()">Close</button>
	                    <button type="button" class="btn btn-modal btn-primary" id="saveHe" name="saveHe" onclick="addHigherEducationDetails()">Save changes</button>
                    </div>
                    <!-- End hogher education footer -->
                    
                </div>
                <!-- End higher education tab pane  -->
            </div>
        </div>
	  </div>
    </div>
    <!-- End modal-body -->
    <!-- End modal footer -->
  </div>
</div>
</div> 
<!-- End education details modal -->

<!-- Professional Details Modal -->
<div class="modal modal-professional fade" id="studentProfessionalDetailsModal" tabindex="-1" role="dialog" aria-labelledby="studentPersonalDetails" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="studentProfessionalDetails">Professional Experience</h4>
      </div>
      <div class="modal-body">
			<!-- <div id="pesaveChangesStatus" name="pesaveChangesStatus" class="alert alert-success"></div> -->
            <div class="well clearfix">
            	<input hidden name="proexp-id" id="proexp-id">
                <div class="show modal-input-field clearfix">
                    <label>Industry of the Organization :</label>
                    <select id="industryoftheOrganization" name = "industryoftheOrganization" onchange="clearField('industryoftheOrganizationError')">
                        <option value="">--Select One--</option>
                    </select>
                    <span id="industryoftheOrganizationError" name="industryoftheOrganizationError" style="color:red"></span>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Organization :</label>
                    <input type="text" name="organization" id="organization" onclick="clearField('organizationError')">
                    <span id="organizationError" name="organizationError" style="color:red"></span>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Job Category :</label>
                    <select id="jobCategory" name = "jobCategory" onchange="clearField('jobCategoryError')">
                        <option value="">--Select One--</option>
                    </select> <span id="jobCategoryError" name="jobCategoryError" style="color:red"></span>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Designation :</label>
                    <input type="text" name="designation" id="designation" onclick="clearField('designationError')">
                    <span id="designationError" name="designationError" style="color:red"></span><br>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Commenced on :</label>
                    <input type="date" name="commencedOn" id="commencedOn" onclick="clearField('commencedOnError')" onchange ="checkDateRange('commencedOn','completionOn','commencedOnError','completionOnError')">
                    <span id="commencedOnError" name="commencedOnError" style="color:red"></span>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Completion on :</label>
                    <input type="date" name="completionOn" id="completionOn" onclick="clearField('completionOnError')" onchange ="checkDateRange('commencedOn','completionOn','commencedOnError','completionOnError')">
                    <span id="completionOnError" name="completionOnError" style="color:red"></span>
                </div>

                <div class="show modal-input-field clearfix">
                    <label>Description :</label>
                    <textarea rows="5" cols="40" name="jobDescription" id="jobDescription" ></textarea>
                </div>
                <!-- End Input fields -->

                <div class="form-holder">
                    <form id="frm-example" action="/path/to/your/script" method="POST">
                        <table id="example" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th><input name="select_all" id="example-select-all" value="1" type="checkbox"></th>
                                    <th><b>Organization</b></th>
                                    <th><b>Industry</b></th>
                                    <th><b>Designation</b></th>
                                    <th><b>Category</b></th>
                                    <th><b>Duration</b></th>
                                    <th><b>Description</b></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th></th>
                                    <th><b>Organization</b></th>
                                    <th><b>Industry</b></th>
                                    <th><b>Designation</b></th>
                                    <th><b>Category</b></th>
                                    <th><b>Duration</b></th>
                                    <th><b>Description</b></th>
                                    <th></th>
                                </tr>
                            </tfoot>
                        </table>
                        <hr>
                        <p>
                            <button class="btn btn-danger btn-modal"><span class="glyphicon glyphicon-trash"></span> Delete Selected</button>
                        </p>
                        <pre id="example-console"></pre>
                    </form>
                </div>
                <br />
            </div>
	  </div>					
      <div class="modal-footer">
        <button type="button" class="btn btn-modal btn-close" onclick="clearProfessionalExpForm()">Clear</button>
        <button type="button" class="btn btn-modal btn-close" data-dismiss="modal" onclick="clearProfessionalExpForm()">Close</button>
        <button type="button" class="btn btn-modal btn-primary" id="saveJe" name="saveJe" onclick="addProfessionalExpForm()">Save changes</button>
      </div>
   </div>
  </div>
</div>
<!-- End prfessional details modal --> 

<!-- Footer -->
<footer>
    <div class="ft-top">
		
    </div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright � Campus.lk</label>
    </div>
</footer>

</body>
</html>