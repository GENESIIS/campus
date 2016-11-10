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
<h2>More Course Providers View</h2>

	<table
		style="border: 2px; border-color: black; border-style: solid; width: 100%">		
		<tr>
			<c:forEach var="provider" items="${result.collection}">
				<td
					style="border: 2px; border-color: blue; border-style: solid; width: 10%">
					<c:forEach var="pvAttribute" items="${provider}" varStatus="count">
						<c:choose>
							<c:when test="${count.index == 0}">
							</c:when>
							<c:when test="${count.index == 1}">
								<c:set var="prefix" value="${pvAttribute}" />
								<c:out value="${pvAttribute}" />
							</c:when>
							<c:otherwise>
								<c:set var="slash" value="/" />
								<%-- <td><c:out value="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" /></td> --%>
								<img height="42" width="42"
									src="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
				<td></td>
				<br>
			</c:forEach>
		</tr>
	</table>
</body>
</html>