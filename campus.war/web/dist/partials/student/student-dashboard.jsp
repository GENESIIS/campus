<!-- 20161114 TR c25 start styling user profile page container  -->
<!-- 20161114 TR c25 page header - done  -->
<!-- 20161114 TR c25 profile-image-box - done  -->
<!-- 20161203 MM c25-student-create-dashboard-MP Converted to JSP and converted 
					relative paths to absolute paths  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">

<!-- W3-Include -->
<!--<script src="../../bower-components/w3/w3data.js"></script> -->

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/image-slides.js"></script>

</head>

<body>
<jsp:include page="/dist/partials/student/SessionDetailsJSTL.jsp"></jsp:include>
	<%
		//allow access only if session exists
// 		String currentSessionUser = null;
// 		if (session.getAttribute("user") == null) {
// 			response.sendRedirect("login.jsp");
// 		} else
// 			currentSessionUser = (String) session.getAttribute("user");
// 		String userName = null;
// 		String sessionID = null;
// 		Cookie[] cookies = request.getCookies();
// 		if (cookies != null) {
// 			for (Cookie cookie : cookies) {
// 				if (cookie.getName().equals("user"))
// 					userName = cookie.getValue();
// 				if (cookie.getName().equals("JSESSIONID"))
// 					sessionID = cookie.getValue();
// 			}
// 		}
	%>
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
							<li><a href="dist/partials/student/student-dashboard.html">News</a></li>
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
									<button type="submit" name="CCO" id="CCO" class=""
										value="LIST_CATEGORY_LANDING_PAGE">Pre School</button>
								</form>
							</li>
							<li>
								<form action="">
									<button type="submit" name="CCO" id="CCO"
										value="LIST_CATEGORY_LANDING_PAGE">School Education</button>
								</form>
							</li>
							<li>
								<form action="">
									<button type="submit" name="CCO" id="CCO" class=""
										value="LIST_CATEGORY_LANDING_PAGE">Higher Education</button>
								</form>
							</li>
							<li>
								<form action="">
									<button type="submit" name="CCO" id="CCO"
										value="LIST_CATEGORY_LANDING_PAGE">Corporate Training</button>
								</form>
							</li>
							<li>
								<form action="">
									<button type="submit" name="CCO" id="CCO"
										value="LIST_CATEGORY_LANDING_PAGE">Vocational
										Training</button>
								</form>
							</li>
							<li>
								<form action="">
									<button type="submit" name="CCO" id="CCO"
										value="LIST_CATEGORY_LANDING_PAGE">Talent & Skill</button>
								</form>
							</li>
						</ul>
					</div>
					<!-- End Course Category tabs -->
				</div>
				<div class="keyword-search pull-right">
					<div class="search-bar">
						<input type="text" placeholder="Keyword Search"> <a
							href="javascript:" class="colr-white"></a>
					</div>
					<!-- End Keyword Search -->
					<div class="login-link">
						<a href="javascript:">Logout</a>
						<h3>
							Hi
							${sessionScope.user}, Login successful. </h3>
						<a href="/dist/partials/student/SessionDataLoader.jsp"> Session details checker </a>
						<br>
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
				<h1>| User Profile</h1>
			</div>
			<!-- End page header -->

			<div
				class="prf-page-container col-md-12 col-lg-12 col-sm-12 col-xs-12 clearfix">
				<div class="left-side col-md-3 col-lg-3 col-sm-12 clearfix">
					<div class="prf-pic-holder">
						<div class="prf-image">
							<img src="/dist/i/student/prf-pic.jpg" alt="Profile-Picture">
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
								<td class="field-name"><p>
										Senior Software Engineer at <span>Genesiis Software pvt
											Ltd.</span>
									</p></td>
							</tr>
							<!-- End works at -->

							<tr>
								<td class="cat-icon">i</td>
								<td class="field-name"><p>
										Studied at <span>ICBT Colombo Campus</span>
									</p></td>
							</tr>
							<!-- End studied at -->

							<tr>
								<td class="cat-icon">i</td>
								<td class="field-name"><p>
										Lives in <span>Colombo</span>
									</p></td>
							</tr>
							<!-- End lives in -->
							<tr>
								<td class="cat-icon">i</td>
								<td class="field-name"><p>
										From <span>Katugasthota, Kandy</span>
									</p></td>
							</tr>
							<!-- End from -->
							<tr>
								<td class="cat-icon">i</td>
								<td class="field-name"><p>
										Notes <br> <span class="sp-note">Lorem Ipsum is
											simply dummy text of the printing and typesetting industry.</span>
									</p></td>
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
							<button>Edit</button>
						</div>
						<!-- End widget header -->

						<div class="widget-content clearfix">
							<div class="w-left-panel col-sm-12 col-md-6 col-lg-6">
								<table class="tbl-wgt-about">
									<tr>
										<td class="td-name">First Name :</td>
										<td class="td-value">Kalana</td>
									</tr>
									<tr>
										<td class="td-name">Last Name :</td>
										<td class="td-value">Perera</td>
									</tr>
									<tr>
										<td class="td-name">City :</td>
										<td class="td-value">Kandy</td>
									</tr>
									<tr>
										<td class="td-name">Birthday :</td>
										<td class="td-value">Nov 24,1999</td>
									</tr>
									<tr>
										<td class="td-name">Website :</td>
										<td class="td-value">www.blabla.com</td>
									</tr>
								</table>
							</div>
							<!-- End about-me : left side -->

							<div class="w-right-panel col-sm-12 col-md-6 col-lg-6">
								<table class="tbl-wgt-about">
									<tr>
										<td class="td-name">User Name :</td>
										<td class="td-value">Kala</td>
									</tr>
									<tr>
										<td class="td-name">Email :</td>
										<td class="td-value">jamesbond@gmail.com</td>
									</tr>
									<tr>
										<td class="td-name">Country :</td>
										<td class="td-value">Sri Lanka</td>
									</tr>
									<tr>
										<td class="td-name">Interests :</td>
										<td class="td-value">Web Design, Java</td>
									</tr>
									<tr>
										<td class="td-name">Phone :</td>
										<td class="td-value">077 555 6666</td>
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
										<li>CEO <span class="drop-at">at</span> Mc.Dondon <br>
											<span class="drop-time">March 2012 - Now</span></li>
										<li>CEO <span class="drop-at">at</span> Mc.Dondon <br>
											<span class="drop-time">March 2012 - Now</span></li>
										<li>Web Developer <span class="drop-at">at</span>
											Genesiis Softwre <br> <span class="drop-time">March
												2012 - Now</span></li>
										<li>Web Designer <span class="drop-at">at</span> Genesiis
											Softwre <br> <span class="drop-time">March 2012 -
												Now</span></li>
									</ul>
								</div>
							</div>
						</div>
						<!--  End Experience Widget -->

						<div class="pad-r-0 col-sm-12 col-md-6 col-lg-6">
							<div class="widget w-education">
								<div class="widget-header">
									<label for="">Education</label>
									<button>Edit</button>
								</div>

								<div class="widget-content">
									<ul class="ul-education">
										<li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span>
											UCLA <br> <span class="drop-time">March 2012 -
												Now</span></li>
										<li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span>
											UCLA <br> <span class="drop-time">March 2012 -
												Now</span></li>
										<li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span>
											UCLA <br> <span class="drop-time">March 2012 -
												Now</span></li>
										<li>Bachelor’s Degree, E-Commerce <span class="drop-at">at</span>
											UCLA <br> <span class="drop-time">March 2012 -
												Now</span></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- End Education Widget -->

						<div class="pad-l-0 col-sm-12 col-md-6 col-lg-6">
							<div class="widget w-activity">
								<div class="widget-header">
									<label for="">Activity</label>
									<button>Edit</button>
								</div>

								<div class="widget-content">
									<ul class="ul-activity">
										<li>Change your user profile details <br> <span
											class="act-time">~ March 2012 - Now</span></li>
										<li>Added new article <br> <span class="act-time">~
												March 2012 - Now</span></li>
										<li>Change profile picture <br> <span
											class="act-time">~ March 2012 - Now</span></li>
										<li>Your setting is updated <br> <span
											class="act-time">~ March 2012 - Now</span></li>
									</ul>
								</div>

								<div class="widget-footer">
									<a href="javascript:">See All Activities >></a>
								</div>
							</div>
						</div>
						<!-- End Activity Widget -->

						<div class="pad-r-0 col-sm-12 col-md-6 col-lg-6">
							<div class="widget w-skills">
								<div class="widget-header">
									<label for="">Skills</label>
									<button>Edit</button>
								</div>

								<div class="widget-content">
									<ul class="ul-skills">
										<li>Photoshop
											<div class="progress">
												<div class="progress-bar bg-green w-90" role="progressbar"
													aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</li>
										<li>JAVA
											<div class="progress">
												<div class="progress-bar bg-blue w-50" role="progressbar"
													aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</li>
										<li>AngularJS
											<div class="progress">
												<div class="progress-bar bg-orange w-70" role="progressbar"
													aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</li>
										<li>Javascript
											<div class="progress">
												<div class="progress-bar bg-red w-100" role="progressbar"
													aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</li>
									</ul>
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

	<!-- Footer -->
	<!--<footer w3-include-html="../layout/footer.html"></footer>-->
	<footer>
		<div class="ft-top"></div>
		<div class="ft-bottom text-center">
			<label for="Copyright">Copyright © Campus.lk</label>
		</div>
	</footer>

</body>
</html>