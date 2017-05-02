<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <!-- 20170406 AS c154-admin-privilege-handling-as -AdminSessionDetails.jsp page created to maintain logged users session details.   -->   
 <!-- 20170421 AS c154-admin-privilege-handling-as -AdminSessionDetails.jsp Session attribute name changed -->   
<!--  20170428 AS c155-admin-logout-function-as privilege handling page redirection script changed. -->
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

<c:if test="${sessionScope.currentSessionUsername == null}">
	<script>	
	var myURL = document.location.host;
	
 	location.replace('http://'+myURL + "/dist/partials/error/admin-error-content.jsp");
	</script>
</c:if>

<c:if test="${sessionScope.currentSessionUsername != null}">

    	<c:set var="col_adminCode" value="${sessionScope.currentUserData[0][0]}" scope="session" />
    	<c:set var="col_adminName" value="${sessionScope.currentUserData[0][1]}" scope="session" />
    	<c:set var="col_adminUsername" value="${sessionScope.currentUserData[0][2]}" scope="session" />
    	<c:set var="col_adminEmail" value="${sessionScope.currentUserData[0][3]}" scope="session" />
    	<c:set var="col_adminUserType" value="${sessionScope.currentUserData[0][4]}" scope="session" />
    	<c:set var="col_adminUserTypeString" value="${sessionScope.currentUserData[0][5]}" scope="session" />
    
    	
   </c:if>
	<!-- user valid pages handling -->
	<c:set var="currentUrl" value="${pageContext.request.requestURI}"></c:set>
	<!-- logged admin authenticated listed interfaces iteration -->
	<c:if test="${sessionScope.currentUserData[2] != null }">
		<c:set var="conVal" value="0" />
		<c:forEach var="userDataCollection"	items="${sessionScope.currentUserData[2]}" varStatus="rowCount"	begin="0" step="2"> 
		
			<c:set var="url" value="${userDataCollection}" />

			<c:if test="${currentUrl == url}">
				<c:set var="conVal" value="1" />
			</c:if>
			
		</c:forEach>
		<c:if test="${conVal == 0}">
			<script>
				var myURL = document.location.host;

				location.replace('http://' + myURL + "/dist/partials/admin/admin-dashboard.jsp");
			</script>
			
		</c:if>
		<!-- iterating user authenticated button actions -->
		<c:forEach var="userDataCollection2"
		
			items="${sessionScope.currentUserData[2]}" varStatus="rowCount"
			begin="1" step="2">

			<c:set var="buttionAction" value="${userDataCollection2}"
				scope="request" />
			<c:set var="action" value="${action},${buttionAction}"
				scope="request" />
		</c:forEach>

	</c:if>

</body>
</html>