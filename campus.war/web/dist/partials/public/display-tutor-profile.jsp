<!-- 20170330 DK c135 Creating the structure and the layout / UI Integration -->
<!-- 20170428 JH c135-public-display-tutor-profile added hidden input tutorCode to store post data, add id attributes to assign values wip,
					removed google+ social media link, removed span tag for tutor-title, added userMessage div to display user message, added tooltips for social media links -->
<!-- 20170508 JH c162-public-display-tutor-full-profile display tutor qualifications wip -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Campus.lk</title>

	<!-- Bootstrap Styles-->	
	<link href="/dist/bower-components/bootstrap/bootstrap.css"	rel="stylesheet">
	
	<!-- FontAwsome -->
	<link href="/dist/bower-components/fonts/faw/css/faw.css" rel="stylesheet">
	
	<!-- Layout Styles-->
	<link href="/dist/css/style.css" rel="stylesheet">

	<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
	<script src="/dist/js/main.js"></script>
	   
</head>
<body class="profile-display public">
<input type="hidden" name="tutorCode" id="tutorCode" value=${param.tutorCode }>

<!-- Header-->
<jsp:include page="/dist/partials/layout/header.jsp"></jsp:include>
<!-- End Header -->

<!-- START - Main-Content -->
<div class="container-fluid wrapper">
	<div class="title-section container">
		<div class="row">
			<div class="page-title col-sm-6">
				<h2><i class="fa fa-graduation-cap" aria-hidden="true"></i> Tutor Profile</h2>
			</div>
			<div class="page-nav col-sm-6">
				<ul class="breadcrumb pull-right">
					<li class="breadcrumb-item"><a href="/dist/partials/public/display-tutors.jsp">Tutors</a></li>
					<li class="breadcrumb-item active" id="list-breadcrumb-item"></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="alert alert-danger" id="userMessage" name="userMessage" style="display: none; visibility: hidden;"></div>
		</div>
	</div>
	
	<div class="about-section container">
		<div class="row">
			<div class="profile-img col-sm-2">
				<img class="img-responsive" src="" alt="Tutor profile image" id="tutorImage"/>
			</div>
			<div class="profile-info col-sm-10">
				<h2 class="section-title">About</h2>
				<div class="row">
					<div class="tutor-desc col-xs-12">
						<h3>
							<span class="tutor-name" id="fullName" name="fullName"></span>
						</h3>
					</div>
					<div class="social-list col-xs-12">
						<div class="row">
							<div class="col-sm-4 clearfix">
								<address id="address" name="address"><i class="fa fa-map-marker" aria-hidden="true"></i> </address>
							</div>
							<div class="col-sm-8 clearfix">
								<ul class="social-media list-unstyled list-inline pull-right">
									<li class="item1 list facebook"><a href="#" title="Facebook" id="facebookURL" data-toggle="tooltip" title="">
										<img src="/dist/i/public/display-tutor/social-media/facebook.png" alt="Facebook" /></a></li>
									<li class="item2 list twitter"><a href="#" title="Twitter" id="twitterURL" data-toggle="tooltip" title="">
										<img src="/dist/i/public/display-tutor/social-media/twitter.png" alt="Twitter" /></a></li>
									<li class="item4 list linkedin"><a href="#" title="Linkedin" id="linkedinURL" data-toggle="tooltip" title="">
										<img src="/dist/i/public/display-tutor/social-media/linkedin.png" alt="Linkedin" /></a></li>
									<li class="item5 list instagram"><a href="#" title="Instagram" id="instagramURL" data-toggle="tooltip" title="">
										<img src="/dist/i/public/display-tutor/social-media/instagram.png" alt="Instagram" /></a></li>
									<li class="item6 list whatsapp"><a href="javascript:" title="Whatsapp" id="whatsappNumber" data-toggle="tooltip" title="">
										<img src="/dist/i/public/display-tutor/social-media/whatsapp.png" alt="Whatsapp" /></a></li>
									<li class="item7 list viber"><a href="javascript:" title="Viber" id="viberNumber" data-toggle="tooltip" title="">
										<img src="/dist/i/public/display-tutor/social-media/viber.png" alt="Viber" /></a></li>
									<li class="item8 list myspace"><a href="#" title="Myspace" id="myspaceURL" data-toggle="tooltip" title="">
										<img src="/dist/i/public/display-tutor/social-media/myspace.png" alt="Myspace" /></a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="contact-list col-xs-12">
						<div class="row">
							<div class="tel col-md-3 col-sm-6 col-xs-12 clearfix" id="landPhoneNumber" alt="Land Phone number"><i class="fa fa-phone" aria-hidden="true"></i></div>
							<div class="mobile col-md-3 col-sm-6 col-xs-12 clearfix" id="mobilePhoneNubmer" alt="Mobile phone nubmer"><i class="fa fa-mobile-phone" aria-hidden="true"></i></div>
							<div class="email col-md-4 col-sm-6 col-xs-12 clearfix"><i class="fa fa-envelope-o" aria-hidden="true" alt="mail to"></i> <a href="mailto:" id="email"></a></div>
							<div class="web col-md-2 col-sm-6 col-xs-12 clearfix"><i class="fa fa-globe" aria-hidden="true" alt="web site"></i> <a href="javascript:" target="_blank"  id="webLink"></a></div>
						</div>
					</div>
				</div>						
			</div>
		</div>			
	</div>	
	<div class="main-content container">
		<div class="row">
			<div class="tutor-intro col-xs-12">
				<h2><span class="header-wrapper">Tutor Description</span></h2>
				<p id="tutorDescription"></p>
			</div>
		</div>
		<div class="row">
			<div class="qualifications-section content-section col-xs-12" id="qualificationDiv">
				<h2 class="section-title">Qualifications</h2>
				
				
				<!-- START - Repeater -->
				<div class="edu-content repeat-content col-xs-12">
					<h5>Master of Business Administration</h5>
					<span class="fa fa-bookmark" aria-hidden="true"></span>
					<span class="edu-level">Masters Level</span>
					<p>Nulla et quam vitae dui gravida bibendum. Pellentesque id viverra sapien. Sed ut nisl semper, viverra urna vel, efficitur sapien. Vestibulum nec magna at turpis commodo aliquet eu non sem.</p>
				</div>
				<!-- <div class="edu-content repeat-content col-xs-12">
					<h5>Master of Business Administration</h5>
					<span class="fa fa-bookmark" aria-hidden="true"></span>
					<span class="edu-level">Masters Level</span>
					<p>Nulla et quam vitae dui gravida bibendum. Pellentesque id viverra sapien. Sed ut nisl semper, viverra urna vel, efficitur sapien. Vestibulum nec magna at turpis commodo aliquet eu non sem.</p>
				</div> -->
				<!-- END - Repeater -->
			</div>
		</div>
		<div class="row">
			<div class="experience-section content-section col-xs-12">
				<h2 class="section-title">Experience</h2>
				<!-- START - Repeater -->
				<div class="experience-content repeat-content col-xs-12">
					<div class="job-title col-sm-4">
						<h5>Senior Software Engineer - UI</h5>
					</div>
					<div class="job-des col-sm-4">
						<h5>GENESIIS Software (Pvt) Ltd</h5>
					</div>	
					<div class="job-duration col-sm-4">
						<time datetime="2014-06-28">28 June 2014</time> - <time datetime="2014-06-28">28 June 2014</time>
					</div>							
				</div>
				<div class="experience-content repeat-content col-xs-12">
					<div class="job-title col-sm-4">
						<h5>Senior Software Engineer - UI</h5>
					</div>
					<div class="job-des col-sm-4">
						<h5>GENESIIS Software (Pvt) Ltd</h5>
					</div>	
					<div class="job-duration col-sm-4">
						<time datetime="2014-06-28">28 June 2014</time> - <time datetime="2014-06-28">28 June 2014</time>
					</div>							
				</div>
				<!-- END - Repeater -->
			</div>
		</div>
		<div class="row">
			<div class="employment-section content-section col-xs-12">
				<h2 class="section-title">Employment</h2>
				<!-- START - Repeater -->
				<div class="experience-content repeat-content col-xs-12">
					<h5>Genesiis Software (Pvt) Ltd</h5>						
				</div>
				<div class="experience-content repeat-content col-xs-12">
					<h5>Genesiis Software (Pvt) Ltd</h5>						
				</div>
				<div class="experience-content repeat-content col-xs-12">
					<h5>Genesiis Software (Pvt) Ltd</h5>						
				</div>
				<!-- END - Repeater -->
			</div>
		</div>
		<div class="row">
			<div class="courses-section content-section col-xs-12">
				<h2 class="section-title">Course</h2>
				
				<div class="courses-content repeat-content col-xs-12">							
					<div class="bootstrap-tagsinput">
						<!-- START - Repeater -->
						<span class="tag label label-info">SCJP 7 <i class="fa fa-check-square-o" aria-hidden="true"></i></span>
						<span class="tag label label-info">Scrum Master Certification <i class="fa fa-check-square-o" aria-hidden="true"></i></span>
						<span class="tag label label-info">Certified Information Systems Security Professional <i class="fa fa-check-square-o" aria-hidden="true"></i></span>
						<span class="tag label label-info">Magento - Front end Master Certification <i class="fa fa-check-square-o" aria-hidden="true"></i></span>
						<span class="tag label label-info">Angular JS 2 Certification <i class="fa fa-check-square-o" aria-hidden="true"></i></span>
						<span class="tag label label-info">Linux Network Administration <i class="fa fa-check-square-o" aria-hidden="true"></i></span>
						<span class="tag label label-info">Project Management Professional <i class="fa fa-check-square-o" aria-hidden="true"></i></span>
						<!-- END - Repeater -->
					</div>							
				</div>
				
			</div>
		</div>
	</div>
</div>
<!-- END - Main-Content -->


<!-- Footer -->
<footer>
    <div class="ft-top"></div>
    <div class="ft-bottom text-center">
        <label for="Copyright">Copyright @ Campus.lk</label>
    </div>
</footer>
<!-- End Footer -->

	<!-- custom javascript -->
	<script src="/dist/bower-components/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/bower-components/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/bower-components/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/bower-components/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	
	<!-- page load helper -->
	<script src="/dist/js/public/tutor-profile-helper.js" type="text/javascript"></script>
	
	<!-- error handling java script -->
	<script src="/dist/js/error-handling.js" type="text/javascript"></script>
</body>
</html>