<!-- 20161030 DJ  c6-list-available-institutes-on-the-view view more institutes -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campus.lk -Institutes</title>
</head>
<body>
<h2>More institute View</h2>

	<table style="border: 2px; border-color: black; border-style: solid;">
		<tr>
			<th></th>
			<th>CODE</th>
			<th>NAME</th>			
		</tr>
		<c:forEach var="provider" items="${result.collection}">			
			<tr>
				<c:forEach var="pvAttribute" items="${provider}" varStatus="vs">
				<td><c:out value="${pvAttribute}" /></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>