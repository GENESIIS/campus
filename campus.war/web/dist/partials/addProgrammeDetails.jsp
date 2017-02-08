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
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"	rel="stylesheet">
<!-- FontAwsome Styles-->
<link href="/dist/bower-components/faw/css/font-awesome.min.css" rel="stylesheet">

<link href="/dist/css/style.css" rel="stylesheet">

</head>
<body class="admin">
	<!-- Header-->
	<jsp:include page="/dist/partials/layout/header.jsp" />
	<!-- End Header-->
	<!-- Main Container - Contact-US -->
	<div class="top-providers-screen clearfix">

		
		<div class="container course-registration admin">
			
				<div class="form-wrapper">
				
					<div class="row">
						<h2 class="form-title">
							<span class="fa fa-pencil" aria-hidden="true"></span>
							Register a Course
							<span class="form-intro">Please fill the form below to add a course</span>
						</h2>
					</div>
			
					
					<form name="addCourseForm" id="programmeForm" class="form form-admin" method="post" action="">
					
						<div class="row clearfix">
							<h4 class="col-xs-12 form-section-title">1. Course Details</h4>
							<div class="form-group col-sm-4">
								<label for="course-category">Course Category <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<select name="courseCategory" id="course-category" class="form-control">
										<option selected>Choose a category</option>
										<option value="Pre Education">Pre Education</option>
										<option value="School Education">School Education</option>
										<option value="Higher Education">Higher Education</option>
										<option value="Corporate Training">Corporate Training</option>
										<option value="Talent & Skills">Talent & Skills</option>
									</select>
									<span class="ico-box">
										<span class="normal fa fa-tags" aria-hidden="true"></span>
										<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
										<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
									</span>
								</div>
								
							</div>
							<div class="form-group col-sm-4">
								<label for="course-major">Course Major <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<select name="courseMajor" id="course-major" class="form-control">
										<option selected>Choose a major</option>
										<option value="Civil Engineering">Civil Engineering</option>
										<option value="Information Technology">Information Technology</option>
										<option value="Safety & Security">Safety & Security</option>
										<option value="Bio Medical">Bio Medical</option>
										<option value="Sports Science">Sports Science</option>
									</select>
									<span class="ico-box">
										<span class="normal fa fa-bookmark" aria-hidden="true"></span>
										<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
										<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
									</span>
								</div>
							</div>
							<div class="form-group col-sm-4">
								<label for="course-level">Course Level <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<select name="courseLevel" id="course-level" class="form-control">
										<option selected>Choose a level</option>
										<option value="NVQ Level - 1">NVQ Level - 1</option>
										<option value="NVQ Level - 2">NVQ Level - 2</option>
										<option value="NVQ Level - 3">NVQ Level - 3</option>
										<option value="Certificate Level">Certificate Level</option>
										<option value="Diploma Level">Diploma Level</option>
										<option value="Associate Degree Level">Associate Degree Level</option>
										<option value="Degree Level">Degree Level</option>
										<option value="Masters Level">Masters Level</option>
									</select>
									<span class="ico-box">
										<span class="normal fa fa-industry" aria-hidden="true"></span>
										<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
										<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
									</span>
								</div>
							</div>
						</div>
						
						<div class="row clearfix">			
							<div class="form-group col-sm-4">
								<label for="course-name">Course Name <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<input name="courseName" type="text" class="form-control" id="course-name" placeholder="">
									<span class="ico-box">
										<span class="normal fa fa-graduation-cap" aria-hidden="true"></span>
										<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
										<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
									</span>
								</div>
							</div>
							<div class="form-group col-sm-4">
								<label for="course-provider">Course Provider <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<input name="courseProvider" type="text" class="form-control" id="course-provider" placeholder="">
									<span class="ico-box">
										<span class="normal fa fa-university" aria-hidden="true"></span>
										<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
										<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
									</span>
								</div>
							</div>
							<div class="form-group col-sm-4">
								<label for="class-type">Class Type <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<select name="classType" id="class-type" class="form-control">
										<option selected>Choose a class type</option>
										<option value="Individual">Individual</option>
										<option value="Group">Group</option>
									</select>
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
								<div class="form-group form-block-course-description">
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
								<div class="form-group">
									<label for="course-duration">Course Duration <span class="err-msg pull-right">Please enter valid information</span></label>
									<div class="input-wrapper">
										<input name="courseDuration" type="text" class="form-control" id="course-duration" placeholder="">
										<span class="ico-box">
											<span class="normal fa fa-clock-o" aria-hidden="true"></span>
											<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
											<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
										</span>
									</div>
								</div>
								<div class="form-group">
									<label for="commencement-date">Commencement Date <span class="err-msg pull-right">Please enter valid information</span></label>
									<div class="input-wrapper">
										<input name="commencementDate" type="date" class="form-control" id="commencement-date" placeholder="">
										<span class="ico-box">
											<span class="normal fa fa-calendar-check-o" aria-hidden="true"></span>
											<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
											<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
										</span>
									</div>
								</div>
								<div class="form-group">
									<label for="expiration-date">Expiration date <span class="err-msg pull-right">Please enter valid information</span></label>
									<div class="input-wrapper">
										<input name="expirationDate" type="date" class="form-control" id="expiration-date" placeholder="">
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
										<input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio1" value="Active"> Active
									</label>
								</div>
								<div class="form-check form-check-inline col-xs-3">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="Inactive"> Inactive
									</label>
								</div>
								<div class="form-check form-check-inline col-xs-3">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="Archived"> Archived
									</label>
								</div>
								<div class="form-check form-check-inline col-xs-3">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="Deleted"> Deleted
									</label>
								</div>
							</div>
							<div class="col-sm-4"></div>			
						</div>
						
						<div class="row clearfix">
							<h4 class="col-xs-12 form-section-title">2. Counselor Details</h4>
							<div class="form-group col-sm-4">
								<label for="counselor-name">Counselor Name <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<input name="counselorName" type="text" class="form-control" id="counselor-name" placeholder="">
									<span class="ico-box">
										<span class="normal fa fa-user" aria-hidden="true"></span>
										<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
										<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
									</span>
								</div>
							</div>
							<div class="form-group col-sm-4">
								<label for="counselor-tel">Counselor Telephone <span class="err-msg pull-right">Please enter valid information</span></label>
								<div class="input-wrapper">
									<input name="counselorTel" type="tel" class="form-control" id="counselor-tel" placeholder="">
									<span class="ico-box">
										<span class="normal fa fa-phone" aria-hidden="true"></span>
										<span class="error fa fa-times fa-2x" aria-hidden="true"></span>	
										<span class="success fa fa-check fa-2x" aria-hidden="true"></span>										
									</span>
								</div>
							</div>
							<div class="form-group col-sm-4">
								<label for="counselor-email">Counselor Email <span class="err-msg pull-right">Please enter valid information</span></label>
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
							  <button type="submit" class="btn btn-register pull-right">Add Course</button>
						</div>
						
					</form>
				
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