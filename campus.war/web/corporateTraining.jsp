<!-- 20161024 MM c5-corporate-training-landing-page Initialised -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campus.lk - Corporate Training</title>
</head>
<body>

<h1>Corporate Training</h1>

Programmes: 
<table style="border:2px;border-color: black; border-style: solid;">

	<c:forEach var="programme" items="${result.collection}">
		<tr>
			<c:forEach var="programmeAttrib" items="${programme}" varStatus="vs">
				<td>
				</td>
				<c:if test="${vs.index == 3}"> <%-- email --%>
					<td>
					image
						<a href="#">
							<img src="${programmeAttrib}"/>
						</a>
					</td>
				</c:if>
				
				<c:if test="${vs.index == 1}">		
					<td>
					name: 
						${programmeAttrib}
					</td>
				</c:if>
				
				<c:if test="${vs.index == 4}">		
					<td>
					description:
						${programmeAttrib}
					</td>
				</c:if>
				
				<c:if test="${vs.index == 5}">		
					<td>
					duration:
						${programmeAttrib}
					</td>
				</c:if>
				
				<c:if test="${vs.index == 12}">		
					<td>
					major: 
						${programmeAttrib}
					</td>
				</c:if>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

Course Providers offering Corporate Training programmes: 
<table style="border:2px;border-color: black; border-style: solid;">

	<c:forEach var="popularCourseProvider" items="${courseProvidersWithPopularCourses}">
		<tr>
			<c:forEach var="courseProviderAttrib" items="${popularCourseProvider}" varStatus="vs">
				<td>
				</td>
				<c:if test="${vs.index == 6}">
					<td>
					image
						<a href="#">
							<img src="${courseProviderAttrib}"/>
						</a>
					</td>
				</c:if>
				
				<c:if test="${vs.index == 2}">		
					<td>
					name: 
						${courseProviderAttrib}
					</td>
				</c:if>
				
				<c:if test="${vs.index == 6}">		
					<td>
					description: 
						${courseProviderAttrib}
					</td>
				</c:if>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

Course Providers whose programmes of Corporate Training category has received the most number of views: 
<table style="border:2px;border-color: black; border-style: solid;">

	<c:forEach var="courseProvider" items="${courseProviders}">
		<tr>
			<c:forEach var="courseProviderAttrib" items="${courseProvider}" varStatus="vs">
				<td>
				</td>
				<c:if test="${vs.index == 6}">
					<td>
					image
						<a href="#">
							<img src="${courseProviderAttrib}"/>
						</a>
					</td>
				</c:if>
				
				<c:if test="${vs.index == 1}">
					<td>
					short name: 
						${courseProviderAttrib}
					</td>
				</c:if>
				
				<c:if test="${vs.index == 2}">
					<td>
					name: 
						${courseProviderAttrib}
					</td>
				</c:if>
				
				<c:if test="${vs.index == 6}">		
					<td>
					description: 
						${courseProviderAttrib}
					</td>
				</c:if>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

</body>
</html>