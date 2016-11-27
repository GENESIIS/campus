<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161026 TR c1 added courses.html -->
<!-- 20161109 JH c7 renamed to header.jsp -->
<!-- 20161110 JH c7 css styles changes -->
<!-- 20161117 JH c7 load header categories from json object -->
<!-- 20161118 JH c7 header page css style changes -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
						<li><a href="dist/partials/courses.jsp">All Courses</a></li>
						<li><a href="dist/partials/about-us.jsp">About Us</a></li>
						<li><a href="dist/partials/contact-us.jsp">Contact Us</a></li>
						<li><a href="dist/partials/news.jsp">News</a></li>
						<li><a href="dist/partials/f-and-q.jspl">F & Q</a></li>
						<li><a href="dist/partials/rss.jsp">Rss</a></li>
					</ul>
				</div>
				<!-- End Main menu tabs -->

				<!-- Course Category tabs -->
				<div class="bottom-menus" id="category-list">
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
					<a href="javascript:" class="colr-white">Login</a>

				</div>
			</div>
			<!-- End keyword search -->
		</div>
	</div>
</header>