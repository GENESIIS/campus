
<!-- 20170407 AS c23-admin-login-logout-function-as -admin-header.jsp page created to sample.   -->
<!-- 20170419 AS c154-removed unwanted UI elements and jstl functions   -->  
<!-- 20170421 AS c154-admin-privilege-handling-as -AdminSessionDetails.jsp Session attribute name changed -->     
<!-- 20170427 AS CAM-155-admin-logout-function-as- logout button ajax call function modified  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<script src='/dist/js/admin/admin-login.js'></script>
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
					
					<c:if test="${sessionScope.currentSessionUsername != null}">
						<h3>Hi ${sessionScope.user}, Login successful.</h3>
						
						<input type="hidden" id="userCode" name="userCode"
							value="${sessionScope.userCode}" />

						<a class="btn btn-link colr-white" name="CCO"
							id="CCO" value="ALGOUT" onclick="ALogout()">Logout</a>

					</c:if>
				</div>
			</div>
			<!-- End keyword search -->
		</div>
	</div>

</header>