<!-- 20170112 PN CAM-72 INIT test page to display error/exception details to the user. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hi....!!!! I'm your default Error Page. Seems like something
		went wrong.</h1>
	<c:forEach var="errorDetails" items="${result.collection}">
		<c:out value="${errorDetails[0]}"></c:out><br>
		<c:out value="${errorDetails[1]}"></c:out><br>
		<c:out value="${errorDetails[2]}"></c:out><br>
		<c:out value="${errorDetails[3]}"></c:out><br>
		<c:out value="${errorDetails[4]}"></c:out><br>
		<c:out value="${errorDetails[5]}"></c:out><br>
		<c:out value="${errorDetails[6]}"></c:out><br>
	</c:forEach>

</body>
</html>