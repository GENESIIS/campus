<!-- 20170208 DJ  c138-add-basic-programme-MP-dj  Integrate basic ui with styled UI -->
<!-- 20170503 TR c146 Added jquery.easing.min.js   -->

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
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"	rel="stylesheet">
<!-- FontAwsome Styles-->
<link href="/dist/bower-components/faw/css/font-awesome.min.css" rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">

</head>
<body class="admin">

	<!-- Header-->
	<jsp:include page="/dist/partials/layout/header.jsp" />
	<!-- End Header-->

	<!-- Add Program Details -->
	<div class="add-program-screen clearfix">

		<div class="container course-registration">
            <div class="form-wrapper">
                <div class="row">
                    <h2 class="form-title">
                        <span class="fa fa-pencil" aria-hidden="true"></span>
                        Register a Course
                        <span class="form-intro">Please fill the form below to add a course</span>
                    </h2>
                </div>

                <form name="addCourseForm" id="programmeForm" class="form form-admin" method="post" action="AdminController">

                    <ul id="progressbar">
                        <li class="active">Course Details</li>
                        <li>Semester Details</li>
                        <li>Module Details</li>
                        <li>Intake Details</li>
                        <li>Finish</li>
                    </ul>
                    <!-- End ProgressBar -->

                    <fieldset>
                        <div class="show">
                        <div class="row clearfix">
                            <h4 class="col-xs-12 form-section-title">Course Details</h4>
                            <div class="form-group block-course-category col-sm-4">
                                <label for="course-category"><span class="compulsary-fld">*</span> Course Category <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                <input class="input-field" type="text" name="categoryList" id="categoryList" list="categoryName" placeholder="Choose a category" />
                                <input type="hidden" id="selectedCategory" name="selectedCategory"/>
                                <datalist id="categoryName">
                                </datalist>
                                <span class="ico-box">
                                        <span class="normal fa fa-tags" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>

                            </div>
                            <div class="form-group block-course-major col-sm-4">
                                <label for="course-major"><span class="compulsary-fld">*</span> Course Major <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                <input class="input-field" type="text" name="majorList" id="majorList" list="majorName" placeholder="Choose a major"/>
                                <input type="hidden" id="selectedMajor" name="selectedMajor"/>
                                <datalist id="majorName">
                                </datalist>
                                <span class="ico-box">
                                        <span class="normal fa fa-bookmark" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group block-course-level col-sm-4">
                                <label for="course-level"><span class="compulsary-fld">*</span> Course Level <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                 <input class="input-field" type="text" name="levelList" id="levelList" list="levelName" placeholder="Choose a level" size="25px" />
                                    <input type="hidden" id="selectedLevel" name="selectedLevel"/>
                                <datalist id="levelName">
                                </datalist>
                                <span class="ico-box">
                                        <span class="normal fa fa-industry" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="row clearfix">
                            <div class="form-group block-course-name col-sm-4">
                                <label for="course-name"><span class="compulsary-fld">*</span> Course Name <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                    <input name="courseName" type="text" class="form-control" id="course-name" placeholder="">
                                    <span class="ico-box">
                                        <span class="normal fa fa-graduation-cap" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group block-course-provider col-sm-4">
                                <label for="course-provider"><span class="compulsary-fld">*</span>Course Provider <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                    <input class="input-field"type="text" name="providerList" id="providerList" list="providerName" placeholder="Choose a Provider" />
                                    <input type="hidden" id="selectedProvider" name="selectedProvider"/>
                                    <datalist id="providerName">
                                </datalist>
                                    <span class="ico-box">
                                        <span class="normal fa fa-university" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group block-class-type col-sm-4">
                                <label for="class-type">Class Type <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                <input class="input-field" type="text" name="classTypelist" id="classTypelist" list="classTypeName" placeholder="Choose a class type"  size="25px"/>
                                <input type="hidden" id="selectedClassType" name="selectedClassType"/>
                            <datalist id="classTypeName">
                            </datalist>
                                    <span class="ico-box">
                                        <span class="normal fa fa-users" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="row clearfix">
                            <div class="col-sm-8">
                                <div class="form-group block-course-description">
                                    <label for="course-description">Course Description <span class="err-msg pull-right">Please enter valid information</span></label>
                                    <div class="input-wrapper">
                                        <textarea name="courseDescription" class="form-control" id="course-description" rows="9"></textarea>
                                        <span class="ico-box">
                                            <span class="normal fa fa-pencil-square-o" aria-hidden="true"></span>
                                            <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                            <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group block-course-duration">
                                    <label for="course-duration"><span class="compulsary-fld">*</span>Course Duration <span class="err-msg pull-right">Please enter valid information</span></label>
                                    <div class="input-wrapper">
                                        <input name="courseDuration" type="number" class="form-control" id="course-duration" placeholder=""  step="0.01">
                                        <span class="ico-box">
                                            <span class="normal fa fa-clock-o" aria-hidden="true"></span>
                                            <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                            <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="form-group block-course-commencement">
                                    <label for="commencement-date"><span class="compulsary-fld">*</span>Commencement Date <span class="err-msg pull-right">Please enter valid information</span></label>
                                    <div class="input-wrapper">
                                        <input name="commencementDate" type="date" class="form-control" id="commencement-date" onkeydown="return false" placeholder="">
                                        <span class="ico-box">
                                            <span class="normal fa fa-calendar-check-o" aria-hidden="true"></span>
                                            <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                            <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="form-group block-course-expiration">
                                    <label for="expiration-date"><span class="compulsary-fld">*</span>Expiration date <span class="err-msg pull-right">Please enter valid information</span></label>
                                    <div class="input-wrapper">
                                        <input name="expirationDate" type="date" class="form-control" id="expiration-date" onkeydown="return false" placeholder="">
                                        <span class="ico-box">
                                            <span class="normal fa fa-calendar-minus-o" aria-hidden="true"></span>
                                            <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                            <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row clearfix">
                            <div class="col-sm-8">
                                <label class="form-check-label">Course Current Status</label>
                                <div class="form-check form-check-inline col-xs-3">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio1" value="ACTIVE"> Active
                                    </label>
                                </div>
                                <div class="form-check form-check-inline col-xs-3">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="INACTIVE"> Inactive
                                    </label>
                                </div>
                                <div class="form-check form-check-inline col-xs-3">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="PENDING"> Pending
                                    </label>
                                </div>
                                <div class="form-check form-check-inline col-xs-3">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="ARCHIVE"> Archive
                                    </label>
                                </div>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>

                        <div class="row clearfix">
                            <h4 class="col-xs-12 form-section-title">Counselor Details</h4>
                            <div class="form-group block-counselor-name col-sm-4">
                                <label for="counselor-name"><span class="compulsary-fld">*</span>Counselor Name <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                    <input name="counselorName" type="text" class="form-control" id="counselor-name" placeholder="">
                                    <span class="ico-box">
                                        <span class="normal fa fa-user" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group block-counselor-tel col-sm-4">
                                <label for="counselor-tel"><span class="compulsary-fld">*</span>Counselor Telephone <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                    <input name="counselorTel" type="tel" class="form-control" id="counselor-tel" placeholder="">
                                    <span class="ico-box">
                                        <span class="normal fa fa-phone" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group block-counselor-email col-sm-4">
                                <label for="counselor-email"><span class="compulsary-fld">*</span>Counselor Email <span class="err-msg pull-right">Please enter valid information</span></label>
                                <div class="input-wrapper">
                                    <input name="counselorEmail" type="email" class="form-control" id="counselor-email" placeholder="">
                                    <span class="ico-box">
                                        <span class="normal fa fa-envelope" aria-hidden="true"></span>
                                        <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                        <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <input type="button" class="btn btn-register next pull-right" id="addProgramme" value="Save & Continue" onclick="addProgrammeDetails();" />
                        </div>
                        </div>
                        <input type="button" name="next" class="next action-button" value="Next" />
                    </fieldset>

                    <fieldset>
                        <div class="row clearfix">
                            <h4 class="col-xs-12 form-section-title">Semester Details</h4>
                            <div class="semester-reg-form">
                                <div class="top-panel-wrap">
                                    <div class="col-lg-4 col-md-4">
                                        <label>Semester Name/ Term <span class="err-msg pull-right">Please enter valid information</span></label>
                                        <input type="text" class="input-field" name="categoryList"  placeholder="Sprint Semester"/>
                                    </div>
                                    <div class="col-lg-3 col-md-3">
                                        <label>Year Number <span class="err-msg pull-right">Please enter valid information</span></label>
                                        <input type="text" class="input-field" name="categoryList"  placeholder="01"/>
                                    </div>
                                    <div class="col-lg-2 col-md-2">
                                        <label>Semester Number <span class="err-msg pull-right">Please enter valid information</span></label>
                                        <input type="text" class="input-field" name="categoryList"  placeholder="02"/>
                                    </div>
                                    <div class="col-lg-3 col-md-3">
                                        <label>Semester Status <span class="err-msg pull-right">Please enter valid information</span></label>
                                        <input type="text" class="input-field" name="categoryList"  placeholder="Active"/>
                                    </div>
                                    <div class="col-lg-12 col-md-12">
                                        <label>Course Description <span class="err-msg pull-right">Please enter valid information</span></label>
                                        <div class="input-wrapper">
                                            <textarea name="courseDescription" class="form-control" rows="9"></textarea>
                                            <span class="ico-box">
                                                <span class="normal fa fa-pencil-square-o" aria-hidden="true"></span>
                                                <span class="error fa fa-times fa-2x" aria-hidden="true"></span>
                                                <span class="success fa fa-check fa-2x" aria-hidden="true"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <input type="button" name="previous" class="previous action-button" value="Previous" />
                        <input type="button" name="next" class="next action-button" value="Next" />

                    </fieldset>

                    <fieldset>
                        3rd STEP
                        <input type="button" name="next" class="next action-button" value="Next" />
                        <input type="button" name="previous" class="previous action-button" value="Previous" />
                    </fieldset>

                    <fieldset>
                     4th STEP
                     <input type="button" name="next" class="next action-button" value="Next" />
                     <input type="button" name="previous" class="previous action-button" value="Previous" />
                    </fieldset>

                    <fieldset>
                     5th STEP
                     <input type="button" name="previous" class="previous action-button" value="Previous" />
                     </fieldset>
                </form>
            </div>
		</div>
		<!-- END course-registration -->
	</div>
	<!-- End Add Program Details -->

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>

    <!-- UI Controller js -->
    <script src="/dist/bower-components/jquery/jquery.easing.min.js"></script>
	<script src="/dist/js/main.js"></script>
	<script src="/dist/js/admin/ui-controller.js"></script>
	<script src="/dist/js/admin/ui-addProgramme-helper.js"></script>

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!-- End Footer -->
</body>
</html>