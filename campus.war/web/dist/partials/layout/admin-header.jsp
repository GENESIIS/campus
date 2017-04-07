
<!-- 20170407 AS c23-admin-login-logout-function-as -admin-header.jsp page created to sample.   -->   
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
<!-- 						<li><a href="/dist/partials/courses.jsp">All Courses</a></li> -->
<!-- 						<li><a href="/dist/partials/about-us.jsp">About Us</a></li> -->
<!-- 						<li><a href="/dist/partials/contactUs.jsp">Contact Us</a></li> -->
<!-- 						<li><a href="/dist/partials/news.jsp">News</a></li> -->
<!-- 						<li><a href="/dist/partials/f-and-q.jsp">F & Q</a></li> -->
<!-- 						<li><a href="/dist/partials/rss.jsp">RSS</a></li> -->
					</ul>
				</div>
				<!-- End Main menu tabs -->

				<!-- Course Category tabs -->
				<div class="bottom-menus" id="category-list"></div>
				<!-- End Course Category tabs -->
			</div>
			<div class="keyword-search pull-right">
				<div class="search-bar">
					<input type="text" placeholder="Keyword Search"> <a
						href="javascript:" class="colr-white"></a>
				</div>
				<!-- End Keyword Search -->

				<div class="login-link">
					<c:if test="${sessionScope.currentSessionUser == null}">
						<a class="btn btn-link colr-white" data-toggle="modal"
							data-target="#loginPopup"> Login </a>
					</c:if>
					<c:if test="${sessionScope.currentSessionUser != null}">
						<h3>Hi ${sessionScope.user}, Login successful.</h3>
						
						<input type="hidden" id="userCode" name="userCode"
							value="${sessionScope.userCode}" />

						<a class="btn btn-link colr-white" name="CCO"
							id="CCO" value="SLGOUT" onclick="studentLogout()">Logout</a>

					</c:if>
				</div>
			</div>
			<!-- End keyword search -->
		</div>
	</div>

</header>