<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Dashboard</title>
</head>
<body>
	Welcome to Admin Dashboard

	<!-- 	checked session already created or not, if not redirect to login.jsp -->
	<c:if test="${sessionScope.currentSessionUser == null}">
		<script>
			window.location.href = '/dist/partials/admin/admin-login.jsp';
		</script>
	</c:if>

	<div class="login-link">

		<c:if test="${sessionScope.currentSessionUser != null}">
			<h3>Hi ${sessionScope.user}, Login successful.</h3>

			<input type="hidden" id="userCode" name="userCode"
				value="${sessionScope.userCode}" />

			<a class="btn btn-link colr-white" name="CCO" id="CCO" value="ALGOUT"
				onclick="">Logout</a>

		</c:if>
	</div>

</body>
</html>