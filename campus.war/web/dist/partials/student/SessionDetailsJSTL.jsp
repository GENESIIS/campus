<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- 20161228 AS CAM-20 SessionDetailsJSTL.jsp page created to maintain logged users session details.   -->
<!-- 20161229 AS CAM-20 logged Student privilege handling in jstl (interface and button action)  -->
<!-- 20170124 AS CAM-20 unwanted code comments removed. -->
<!-- 20170124 AS CAM-20 Popup message and URL redirection handled.  -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">
<link href="/dist/css/image-slides.css" rel="stylesheet">

<script src="/dist/js/header/ui-populate-helper.js"></script>
<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/js/image-slides.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/header/ui-populate-helper.js"></script>

<title></title>
</head>
<body>
<jsp:include page="/dist/partials/login/messagePopup.jsp"></jsp:include>
<jsp:include page="/dist/partials/login/loginPopup.jsp"></jsp:include>


<!-- 	checked session already created or not, if not redirect to login.jsp -->
	<c:if test="${sessionScope.currentSessionUser == null}">
		
		<script>	
	window.location.href = 'http://www.campus.dev:8080/dist/partials/error/error-content.jsp';
	//	$('#msg-popup').modal('show');
	
		</script>
		
<%-- 	<% response.sendRedirect("/index.jsp?showLogin=true"); %>  --%>
	</c:if>
	<!-- logged student details extract from collection of collection and assign to jstl var -->
		<c:if test="${sessionScope.currentUserData != null}">

    	<c:set var="col_stuCode" value="${sessionScope.currentUserData[0][0]}" scope="session" />
    	<c:set var="col_stuUsername" value="${sessionScope.currentUserData[0][1]}" scope="session" />
    	<c:set var="col_stuIndexNo" value="${sessionScope.currentUserData[0][2]}" scope="session" />
    	<c:set var="col_stuFirstName" value="${sessionScope.currentUserData[0][3]}" scope="session" />
    	<c:set var="col_stuMiddleName" value="${sessionScope.currentUserData[0][4]}" scope="session" />
    	<c:set var="col_stuLastName" value="${sessionScope.currentUserData[0][5]}" scope="session" />
    	<c:set var="col_stuDOB" value="${sessionScope.currentUserData[0][6]}" scope="session" />
    	<c:set var="col_stuGender" value="${sessionScope.currentUserData[0][7]}" scope="session" />
    	<c:set var="col_stuEmail" value="${sessionScope.currentUserData[0][8]}" scope="session" />
    	<c:set var="col_stuType" value="${sessionScope.currentUserData[0][9]}" scope="session" />
    	<c:set var="col_stuLandPhoneCountryCode" value="${sessionScope.currentUserData[0][10]}" scope="session" />
    	<c:set var="col_stuLandPhoneAreaCode" value="${sessionScope.currentUserData[0][11]}" scope="session" />
    	<c:set var="col_stuLandPhoneNo" value="${sessionScope.currentUserData[0][12]}" scope="session" />
    	<c:set var="col_stuMobilePhoneCountryCode" value="${sessionScope.currentUserData[0][13]}" scope="session" />
    	<c:set var="col_stuMobilePhoneNetworkCode" value="${sessionScope.currentUserData[0][14]}" scope="session" />
    	<c:set var="col_stuMobilePhoneNo" value="${sessionScope.currentUserData[0][15]}" scope="session" />
    	<c:set var="col_stuDescription" value="${sessionScope.currentUserData[0][16]}" scope="session" />
    	<c:set var="col_stuFacebookUrl" value="${sessionScope.currentUserData[0][17]}" scope="session" />
    	<c:set var="col_stuTwitterUrl" value="${sessionScope.currentUserData[0][18]}" scope="session" />
    	<c:set var="col_stuMySpaceUrl" value="${sessionScope.currentUserData[0][19]}" scope="session" />
    	<c:set var="col_stuLinkedInUrl" value="${sessionScope.currentUserData[0][20]}" scope="session" />
    	<c:set var="col_stuInstagramUrl" value="${sessionScope.currentUserData[0][21]}" scope="session" />
    	<c:set var="col_stuviberNumber" value="${sessionScope.currentUserData[0][22]}" scope="session" />
    	<c:set var="col_stuwhatsAppNumber" value="${sessionScope.currentUserData[0][23]}" scope="session" />
    	<c:set var="col_stuaddress1" value="${sessionScope.currentUserData[0][24]}" scope="session" />
    	<c:set var="col_stuaddress2" value="${sessionScope.currentUserData[0][25]}" scope="session" />
    	<c:set var="col_stuaddress3" value="${sessionScope.currentUserData[0][26]}" scope="session" />
    	<c:set var="col_stutown" value="${sessionScope.currentUserData[0][27]}" scope="session" />
    	<c:set var="col_stuuserType" value="${sessionScope.currentUserData[0][28]}" scope="session" />
    	<c:set var="col_stuaccountType" value="${sessionScope.currentUserData[0][29]}" scope="session" />
    	<c:set var="col_stulastLoggedInUserAgent" value="${sessionScope.currentUserData[0][30]}" scope="session" />
    	<c:set var="col_stulastLoggedInSessionid" value="${sessionScope.currentUserData[0][31]}" scope="session" />
    	<c:set var="col_stulastLoggedInDate" value="${sessionScope.currentUserData[0][32]}" scope="session" />
    	<c:set var="col_stulastLoggedInTime" value="${sessionScope.currentUserData[0][33]}" scope="session" />
    	<c:set var="col_stulastLoggedInIpAddress" value="${sessionScope.currentUserData[0][34]}" scope="session" />
    	<c:set var="col_stulastLoggedOutDate" value="${sessionScope.currentUserData[0][35]}" scope="session" />
    	<c:set var="col_stulastLoggedOutTime" value="${sessionScope.currentUserData[0][36]}" scope="session" />
    	<c:set var="col_stulastLoginAuthenticatedBy" value="${sessionScope.currentUserData[0][37]}" scope="session" />
    	
   </c:if>

 <c:set var="currentUrl" value="${pageContext.request.requestURI}" ></c:set>
<!-- logged student authenticated listed interfaces iteration -->
<c:if test="${sessionScope.currentUserData[1] != null }">
<c:set var="conVal" value="0" /> 
  <c:forEach var="userDataCollection" items="${sessionScope.currentUserData[1]}" varStatus="rowCount" begin="5" step="8">
		<c:set var="url" value="${userDataCollection}" /> 	

			<c:if test="${currentUrl == url}">
			<c:set var="conVal" value="1" /> 
			</c:if>
		</c:forEach>
		<c:if test="${conVal == 0}">
 			<% response.sendRedirect("/index.jsp"); %>
			</c:if>
<!-- iterating user authenticated button actions -->
			<c:forEach var="userDataCollection2" items="${sessionScope.currentUserData[1]}" varStatus="rowCount" begin="6" step="8">
				<c:set var="buttionAction" value="${userDataCollection2}" scope="request"/>	
				<c:set var="action" value="${action},${buttionAction}" scope="request"/>	
			</c:forEach>

		</c:if>

</body>
