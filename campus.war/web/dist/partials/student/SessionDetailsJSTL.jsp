<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.currentSessionUser == null}">
	<% response.sendRedirect("/dist/partials/login.jsp"); %>
	</c:if>
		<c:if test="${sessionScope.currentUserData != null}">
<%-- 	<c:choose> --%>
<%--     <c:when test="${sessionScope.currentUserData != null && sessionScope.currentSessionUser != null}"> --%>
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

			<c:forEach var="userDataCollection2" items="${sessionScope.currentUserData[1]}" varStatus="rowCount" begin="6" step="8">
				<c:set var="buttionAction" value="${userDataCollection2}" scope="request"/>	
				<c:set var="action" value="${action},${buttionAction}" scope="request"/>	
			</c:forEach>

		</c:if>

</body>
