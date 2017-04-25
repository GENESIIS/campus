<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
</head>
<body>
	<header>
	<jsp:include
		page="/dist/partials/admin/AdminSessionDetails.jsp"></jsp:include> 
	 <jsp:include
		page="/dist/partials/layout/admin-header.jsp"></jsp:include></header>
	<div class="divTable" style="border: 1px solid #000;">
		<div class="divTableBody">
			<c:forEach var="userDataCollection2" items="${fn:split(action, ',')}">

				<c:if test="${userDataCollection2 == 'add_course_provider'}">
					<button type="button" id="add_course_provider"
						class="btn btn-default">Default</button>
				</c:if>

				<c:if test="${userDataCollection2 == 'update_course_provider'}">
					<button id="stu_dashbord_addghk">update</button>
					<button type="button" id="update_course_provider"
						class="btn btn-default">Default</button>
				</c:if>

				<c:if test="${userDataCollection2 == 'stu_dashbord_delete'}">
					<button id="stu_dashbord_addh">Student dashboard delete</button>
					<button type="button" class="btn btn-default">Default</button>
				</c:if>
			</c:forEach>

		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>