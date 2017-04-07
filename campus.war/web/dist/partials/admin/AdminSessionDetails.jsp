<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <!-- 20170406 AS c154-admin-privilege-handling-as -AdminSessionDetails.jsp page created to maintain logged users session details.   -->   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 	checked session already created or not, if not redirect to login.jsp -->

<c:if test="${sessionScope.currentSessionUser == null}">
	<script>	
	window.location.href = 'http://www.campus.dev:8080/dist/partials/error/error-content.jsp';
	
	</script>
</c:if>

<c:if test="${sessionScope.currentUserData != null}">

    	<c:set var="col_adminCode" value="${sessionScope.currentUserData[0][0]}" scope="session" />
    	<c:set var="col_adminName" value="${sessionScope.currentUserData[0][1]}" scope="session" />
    	<c:set var="col_adminUsername" value="${sessionScope.currentUserData[0][2]}" scope="session" />
    	<c:set var="col_adminEmail" value="${sessionScope.currentUserData[0][3]}" scope="session" />
    	<c:set var="col_adminUserType" value="${sessionScope.currentUserData[0][4]}" scope="session" />
    	<c:set var="col_adminUserTypeString" value="${sessionScope.currentUserData[0][5]}" scope="session" />
    
    	
   </c:if>

	<c:set var="currentUrl" value="${pageContext.request.requestURI}"></c:set>
	<!-- logged student authenticated listed interfaces iteration -->
	<c:if test="${sessionScope.currentUserData[1] != null }">
		<c:set var="conVal" value="0" />
		<c:forEach var="userDataCollection"
			items="${sessionScope.currentUserData[1]}" varStatus="rowCount"
			begin="5" step="8">
			<c:set var="url" value="${userDataCollection}" />

			<c:if test="${currentUrl == url}">
				<c:set var="conVal" value="1" />
			</c:if>
		</c:forEach>
		<c:if test="${conVal == 0}">
			<%
				response.sendRedirect("/index.jsp");
			%>
		</c:if>
		<!-- iterating user authenticated button actions -->
		<c:forEach var="userDataCollection2"
			items="${sessionScope.currentUserData[1]}" varStatus="rowCount"
			begin="6" step="8">
			<c:set var="buttionAction" value="${userDataCollection2}"
				scope="request" />
			<c:set var="action" value="${action},${buttionAction}"
				scope="request" />
		</c:forEach>

	</c:if>

</body>
</html>