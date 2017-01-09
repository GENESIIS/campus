<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 20161114 TR c25 start styling user profile page container  -->
<!-- 20161114 TR c25 page header - done  -->
<!-- 20161114 TR c25 profile-image-box - done  -->
<!-- 20170109 PN CAM-28: INIT the JSP file  -->
<!-- 20170109 PN CAM-28: modified textfield length with textfield attribute.  -->

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
	<script src="../../js/student/student-details-helper.js"></script>
	
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
   
<style type="text/css">
.alert{
    display: none;
}
</style>    
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
                        <h2>Kalana Perera</h2>
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
                            <td class="field-name"><p>Senior Software Engineer at <span>Genesiis Software pvt Ltd.</span></p></td>
                        </tr>
                        <!-- End works at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Studied at <span>ICBT Colombo Campus</span></p></td>
                        </tr>
                        <!-- End studied at -->

                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Lives in <span>Colombo</span></p></td>
                        </tr>
                        <!-- End lives in -->
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>From <span>Katugasthota, Kandy</span></p></td>
                        </tr>
                        <!-- End from -->
                        <tr>
                            <td class="cat-icon">i</td>
                            <td class="field-name"><p>Notes <br> <span class="sp-note">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</span></p></td>
                        </tr>
                        <!-- End Notes -->
                    </table>
                </div>
            </div>
            <!-- End Left side panel -->

            <div class="right-side col-md-9 col-lg-9 col-sm-12 clearfix">
                <div class="widget w-about-me clearfix">
                    <div class="widget-header">
                        <label for="About">About</label>
                        <button data-toggle="modal" data-target="#studentPersonalDetailsModal">Edit</button>
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
                                <button>Edit</button>
                            </div>

                            <div class="widget-content">
                                <ul class="ul-experience">
                                    <li>CEO <span class="drop-at">at</span> Mc.Dondon <br><span class="drop-time">March 2012 - Now</span></li>
                                    <li>CEO <span class="drop-at">at</span> Mc.Dondon <br><span class="drop-time">March 2012 - Now</span></li>
                                    <li>Web Developer <span class="drop-at">at</span> Genesiis Softwre <br><span class="drop-time">March 2012 - Now</span></li>
                                    <li>Web Designer <span class="drop-at">at</span> Genesiis Softwre <br><span class="drop-time">March 2012 - Now</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--  End Experience Widget -->

                    <div class="pad-r-0 col-sm-12 col-md-6 col-lg-6">
                        <div class="widget w-education">
                            <div class="widget-header">
                                <label for="">Education</label>
                                <button data-toggle="modal" data-target="#studentSchoolEducationModal">Edit</button>
                            </div>

                            <div class="widget-content">
                                <ul class="ul-education">
                                    <li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span> UCLA <br><span class="drop-time">March 2012 - Now</span></li>
                                    <li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span> UCLA <br><span class="drop-time">March 2012 - Now</span></li>
                                    <li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span> UCLA <br><span class="drop-time">March 2012 - Now</span></li>
                                    <li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span> UCLA <br><span class="drop-time">March 2012 - Now</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- End Education Widget -->

<!--                     <div class="pad-l-0 col-sm-12 col-md-6 col-lg-6"> -->
<!--                         <div class="widget w-activity"> -->
<!--                             <div class="widget-header"> -->
<!--                                 <label for="">Activity</label> -->
<!--                                 <button>Edit</button> -->
<!--                             </div> -->

<!--                             <div class="widget-content"> -->
<!--                                 <ul class="ul-activity"> -->
<!--                                     <li>Change your user profile details <br><span class="act-time">~ March 2012 - Now</span></li> -->
<!--                                     <li>Added new article <br><span class="act-time">~ March 2012 - Now</span></li> -->
<!--                                     <li>Change profile picture <br><span class="act-time">~ March 2012 - Now</span></li> -->
<!--                                     <li>Your setting is updated <br><span class="act-time">~ March 2012 - Now</span></li> -->
<!--                                 </ul> -->
<!--                             </div> -->

<!--                             <div class="widget-footer"> -->
<!--                                 <a href="javascript:">See All Activities >></a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
                    <!-- End Activity Widget -->
                    
                      <div class="pad-l-0 col-sm-12 col-md-6 col-lg-6">
                        <div class="widget w-activity">
                            <div class="widget-header">
                                <label for="">Skills</label>
<!--                                 <button>Edit</button> -->
                            </div>

                            <div class="widget-content">
                            <div id="studentSkillSaveStatus" name="studentSkillSaveStatus" class="alert alert-success"></div>
                                <div class="example example_objects_as_tags">
									<div class="bs-docs-example">
										<input type="text" id="studentSkills" name="studentSkills" />
									</div>
									<button onclick="addSkillDetails()">Save Changes</button>
								</div>
                            </div>

                        </div>
                    </div>
                     <!-- End Interest Widget -->
                    

                    <div class="pad-r-0 col-sm-12 col-md-6 col-lg-6">
                        <div class="widget w-skills">
                            <div class="widget-header">
                                <label for="">Interest</label>
<!--                                 <button>Edit</button> -->
                            </div>

                            <div class="widget-content">
                            <div id="studentInterestSaveStatus" name="studentInterestSaveStatus" class="alert alert-success"></div>
								<div class="example example_objects_as_tags">
									<div class="bs-docs-example">
										<input type="text" id="studentInterests" name="studentInterests" />
									</div>
									<button onclick="addInterestsDetails()">Save Changes</button>
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
<div class="modal fade" id="studentPersonalDetailsModal" tabindex="-1" role="dialog" aria-labelledby="studentPersonalDetails" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="studentPersonalDetails"><label>Personal Details</label></h4>
      </div>
      <div class="modal-body">
      <div id="studentPersonalStatus" name="studentPersonalStatus" class="alert alert-success"></div>
      		<h2>Personal Details</h2>
      		<div class="well">
      		First Name <input type="text" name="sFullName" id="sFullName" onkeypress="return isLetter(event)" onkeypress="" onclick="clearField('sFullNameError')">
			<span id="sFullNameError" name="sFullNameError" style="color:red" maxlength="200"></span><br>
			
      		Middle Name <input type="text" name="sMiddleName" id="sMiddleName" onkeypress="return isLetter(event)" onclick="clearField('sMiddleNameError')">
			<span id="sMiddleNameError" name="sMiddleNameError" style="color:red" maxlength="200"></span><br>
			
      		Last Name <input type="text" name="sLastName" id="sLastName" onkeypress="return isLetter(event)" onclick="clearField('sLastNameError')">
			<span id="sLastNameError" name="sLastNameError" style="color:red" maxlength="200"></span><br>
      
      		Birth Date <input type="date" name="sBirthDate" id="sBirthDate" onclick="clearField('sBirthDateError')"  onchange ="isPastfromNow('sBirthDate', 'sBirthDateError')">
			<span id="sBirthDateError" name="sBirthDateError" style="color:red"></span><br>
			
			Gender
			<div class="input-group">
			<input type="radio" name="gender" value="1"> Male 
  			<input type="radio" name="gender" value="0"> Female<br>
  			</div>
			
			About <textarea type="text" name="sAboutMe" id="sAboutMe" onclick="clearField('sAboutMeError')"></textarea>
			<span id="sAboutMeError" name="sAboutMeError" style="color:red" maxlength="200"></span><br>
			
			</div>
			<br>
			
			<h2>Contact Details</h2>
			<div class="well">
			
			Country
			<input type="text" id="sCountry" name="sCountry" list="sCountryList" placeholder="-- Select Town --" onclick="clearField('sCountryError')"/>
			 	<datalist name="sCountryList" id="sCountryList">
			 	</datalist>
			<br/>
			<input hidden type="text" id="sCountryCode" name="sCountryCode"/>
			<span id="sCountryError" name="sCountryError" style="color:red"></span><br>
			
			Town
			 <input type="text" id="sTown" name="sTown" list="sTownList" placeholder="-- Select Town --" onclick="clearField('sTownError')"/>
			 	<datalist name="sTownList" id="sTownList">
			 	</datalist>
			<br/>
			<input hidden type="text" id="sTownCode" name="sTownCode"/>
			<span id="sTownError" name="sTownError" style="color:red"></span><br>			
			
			Address<input type="text" name="sAddress" id="sAddress" onclick="clearField('sAddressError')">
			<span id="sAddressError" name="sAddressError" style="color:red" maxlength="200"></span><br>
			
			Mobile Number
      		<div class="input-group">
      		<span class="input-group-addon" id="countryCodePrefix">+</span>
      		<input class ="phoneNum" type="text" name="sMobileNumber" id="sMobileNumber" onkeypress="return isNumber(event)" onclick="clearField('sMobileNumberError')" maxlength="10">
			</div>
			<span id="sMobileNumberError" name="sMobileNumberError" style="color:red"></span><br>
			
			Home Number 
			<div class="input-group">
			<span class="input-group-addon" id="countryCodePrefix">+</span>
			<input class ="phoneNum" type="text" name="sHomeNumber" id="sHomeNumber" onkeypress="return isNumber(event)" onclick="clearField('sHomeNumberError')" maxlength="10">
			<span id="sHomeNumberError" name="sHomeNumberError" style="color:red"></span><br>
			</div>
			     		  		
			Email <input type="text" name="sEmail" id="sEmail" onclick="clearField('sEmailError')">
			<span id="sEmailError" name="sEmailError" style="color:red" maxlength="200"></span><br>
      
      		Facebook URL <input type="text" name="sFacebookUrl" id="sFacebookUrl" onclick="clearField('sFacebookUrlError')">
			<span id="sFacebookUrlError" name="sFacebookUrlError" style="color:red" maxlength="200"></span><br>
      
      		twitter URL <input type="text" name="stwitterUrl" id="stwitterUrl" onclick="clearField('stwitterUrlError')">
			<span id="stwitterUrlError" name="stwitterUrlError" style="color:red" maxlength="200"></span><br>
			
			LinkedIn URL <input type="text" name="sLinkedInUrl" id="sLinkedInUrl" onclick="clearField('sLinkedInUrlError')">
			<span id="sLinkedInUrlError" name="sLinkedInUrlError" style="color:red" maxlength="200"></span><br>
			
			Instergram URL <input type="text" name="sInstergramUrl" id="sInstergramUrl" onclick="clearField('sInstergramUrlError')">
			<span id="sInstergramUrlError" name="sInstergramUrlError" style="color:red" maxlength="200"></span><br>
			
			mySpace <input type="text" name="smySpace" id="smySpace" onclick="clearField('smySpaceError')">
			<span id="smySpaceError" name="smySpaceError" style="color:red" maxlength="200"></span><br>
			
			WhatsApp 
			<div class="input-group">
			<span class="input-group-addon" id="countryCodePrefix">+</span>
			<input class ="phoneNum" type="text" name="sWhatsApp" id="sWhatsApp" onclick="clearField('sWhatsAppError')" onkeypress="return isNumber(event)" maxlength="10">
			<span id="sWhatsAppError" name="sWhatsAppError" style="color:red"></span><br>
			</div>
			
			Viber 
			<div class="input-group">
			<span class="input-group-addon" id="countryCodePrefix">+</span>
			<input class ="phoneNum" type="text" name="sViber" id="sViber" onclick="clearField('sViberError')" onkeypress="return isNumber(event)" maxlength="10">
			<span id="sViberError" name="sViberError" style="color:red"></span><br>
			</div>
			
			</div>
			<br>			
	  </div>
      <div class="modal-footer">    
      	<button type="button" class="btn btn-secondary" onclick="">Clear</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="">Close</button>
        <button type="button" class="btn btn-primary" id="" name="" onclick="addStudentPersonalDetails()">Save changes</button>
      </div>
    </div>
  </div>
</div>

<!-- School Education -->
<div class="modal fade" id="studentSchoolEducationModal" tabindex="-1" role="dialog" aria-labelledby="studentSchoolEducation" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="studentSchoolEducation">Education Details</h4>
      </div>
      <div class="modal-body">
      
	<div id="exTab2" class="">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#1" data-toggle="tab">School Education</a></li>
			<li><a href="#2" data-toggle="tab">Higher Education</a></li>
		</ul>

		<div class="tab-content ">
			<div class="tab-pane active" id="1">
				<div class="">
      				<div id="saveChangesStatus" name="saveChangesStatus" class="alert alert-success"></div>
			Grade  
			<select id="sseQualification" name = "sseQualification" onchange="clearField('sseQualificationError')">
				<option value="">--Select One--</option>
			</select> <span id="sseQualificationError" name="sseQualificationError" style="color:red"></span>
			<br/>
			Subject Stream
			<select id="sseStream" name = "sseStream" onchange="clearField('sseStreamError')">
				<option value="">--Select One--</option>
			</select> <span id="sseStreamError" name="sseStreamError" style="color:red"></span>
			<br/>
			Result
			<select id="sseResult" name = "sseResult" onchange="clearField('sseResultError')">
				<option value="">--Select One--</option>
				<option value="1">Pass</option>
				<option value="0">Fail</option>
			</select> <span id="sseResultError" name="sseResultError" style="color:red"></span>
			<br/>
			Index No <input type="text" name="sseIndexNo" id="sseIndexNo" onkeypress="return isNumber(event)" onclick="clearField('sseIndexNoError')">
			<span id="sseIndexNoError" name="sseIndexNoError" style="color:red" maxlength="20"></span><br>
			
			School <input type="text" name="sseSchool" id="sseSchool" onclick="clearField('sseSchoolError')">
			<span id="sseSchoolError" name="sseSchoolError" style="color:red" maxlength="200"></span><br>
			
			Achieved on <input type="date" name="" id="sseAchievedon" onclick="clearField('sseAchievedonError')">
			<span id="sseAchievedonError" name="sseAchievedonError" style="color:red"></span><br>
			
			Medium
			<select name="sseMedium" id="sseMedium" onchange="clearField('sseMediumError')">
				<option value="">--Select One--</option>
			</select><span name="sseMediumError" id="sseMediumError" style="color:red"></span>
			<br/>
			
			Country
			 <input type="text" id="sseCountry" name="sseCountry" list="sseCountryList" placeholder="-- Select Country --"/>
			 	<datalist name="sseCountryList" id="sseCountryList">
			 	</datalist>
			<br/>
			
			Description
			<textarea rows="5" cols="40" name="sseDescription" id="sseDescription" maxlength="200"></textarea>
			<br>
		<button type="button" class="btn btn-secondary" onclick="">Clear</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="">Close</button>
        <button type="button" class="btn btn-primary" id="saveSse" name="saveSse" onclick="addEducationDetails()">Save changes</button>	
	  			</div>
			</div>
			<div class="tab-pane" id="2">
				<h3>Higher Education</h3>
			</div>
		</div>
	</div>
     
      
	  </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>


<!-- Footer -->
<!--<footer w3-include-html="../layout/footer.jsp"></footer>-->
<footer>
    <div class="ft-top">

    </div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright © Campus.lk</label>
    </div>
</footer>

</body>
</html>