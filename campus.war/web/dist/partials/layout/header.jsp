<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161026 TR c1 added courses.jsp -->


<header class="header col-lg-12 col-md-12 col-sm-12 clearfix">
    <div class="top">
        <div class="logo-brand">
            <h1 class="logo-txt">Campus.lk</h1>
        </div>
    </div>
    <div class="bottom">
        <div class="menu-bar">
            <div class="home pull-left">
                <a href="javascript:" class="btn-home center-block"></a>
            </div>
            <!-- End home button -->
            <div class="menu-tabs clearfix">
                <!-- Main menu tabs -->
                <div class="top-menus">
                    <ul class="list-inline">
                        <li><a href="dist/partials/courses.jsp">All Courses</a></li>
                        <li><a href="dist/partials/topCourseProviders.jsp">All Course Provider</a></li>
                        <li><a href="dist/partials/about-us.jsp">About Us</a></li>
                        <li><a href="dist/partials/contact-us.jsp">Contact Us</a></li>
                        <li><a href="dist/partials/news.jsp">News</a></li>
                        <li><a href="dist/partials/f-and-q.jsp">F & Q</a></li>
                        <li><a href="dist/partials/rss.jsp">Rss</a></li>
                    </ul>
                </div>
                <!-- End Main menu tabs -->

                <!-- Course Category tabs -->
                <div class="bottom-menus">
                    <ul class="list-inline">
                        <li><a href="javascript:">Pre Education</a></li>
                        <li><a href="javascript:">School Education</a></li>
                        <li><a href="dist/partials/category/higher-education.jsp">Higher Education</a></li>
                        <li><a href="javascript:">Corporate Training</a></li>
                        <li><a href="javascript:">Vocational Training</a></li>
                        <li><a href="javascript:">Talent & Skill</a></li>
                        <li>
							<!-- add Programme Id here for test course preview -->
							<form method="Post" action="PublicController">
								<input type="hidden" name="programmeCode" value="1" />
								<button type="submit" name="CCO" id="CCO" value="VPD"
									class="btn btn-info navbar-btn">View Programme Details</button>

							</form>
						</li>
                    </ul>
                </div>
                <!-- End Course Category tabs -->
            </div>
            <div class="keyword-search pull-right">
                <div class="search-bar">
                    <input type="text" placeholder="Keyword Search">
                    <a href="javascript:" class="colr-white">Enter</a>
                </div>
                <!-- End Keyword Search -->
                <div class="login-link">
                    <a href="javascript:" class="colr-white">Login</a>

                </div>
            </div>
            <!-- End keyword search -->
        </div>
    </div>
</header>