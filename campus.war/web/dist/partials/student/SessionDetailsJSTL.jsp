<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.currentSessionUser == null}">
	not valid user!!
 
<%--  <jsp:include.directive file="http://beginnersbook.com/2013/11/jstl-curl-core-tag/"/> --%>
 <c:redirect url="http://beginnersbook.com"/>

	</c:if>
	

</body>
