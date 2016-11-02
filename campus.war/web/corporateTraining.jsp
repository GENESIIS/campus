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

<h2>List of Corporate Programmes:</h2> 
<table style="border:2px;border-color: brown; border-style: solid;">

	<c:forEach var="programme" items="${programmeColl}">
	<c:set var="progCode" value="${programme[0]}"/>
		<tr>
			<td>
			</td>
			<td width="10%">					
				<a href="#">
<%-- 					<img src="${programme[3]}"/> --%>
					<img src="${contextDeployLogoPath}${programme[22]}&#47;${programme[16]}"/>
				</a>
			</td>
				
			<td width="30%">					 
				${programme[1]} </br>
				at ${programme[19]}
			</td>
			
			<td width="40%">					
				${programme[4]}
			</td>
			
			<td width="10%">					
			${programme[5]/365} years, 
			${(programme[5] % 365) / 30} Months, 
			${((programme[5] % 365) % 30)} Days				
			</td>
			
			<td width="10%">					
				${programme[17]}
			</td>
			
			<td>				
				<c:forEach var="town" items="${programmeCodeToTownListMap[progCode]}">
					${town} <br>
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>
</br>
</br>
</br>
<h2>Course Providers offering Corporate Training programmes:</h2> 
<table style="border:2px;border-color: brown; border-style: solid;">

	<c:forEach var="courseProv" items="${courseProviders}">
		<tr>
			<td>
			</td>
			<td width="10%">
				<a href="#">
<%-- 					<img src="${courseProv[6]}"/> --%>
					<img src="${contextDeployLogoPath}${courseProv[8]}&#47;${courseProv[6]}"/>
				</a>
			</td>
		
			<td width="10%">
				${courseProv[1]}
			</td>
				
			<td width="30%">
				${courseProv[2]}
			</td>
		
			<td width="50%">
				${courseProv[7]}
			</td>
		</tr>
	</c:forEach>
</table>
</br>
</br>
</br>

<h2>Course Providers whose programmes of Corporate Training category has received the most number of views:</h2> 
<table style="border:2px;border-color: brown; border-style: solid;">

	<c:forEach var="popularCourseProv" items="${courseProvidersWithPopularCourses}">
		<tr>
			<td>
			</td>
			<td width="10%">
				<a href="#">
<%-- 					<img src="${popularCourseProv[6]}"/> --%>
					<img src="${contextDeployLogoPath}${popularCourseProv[8]}&#47;${popularCourseProv[6]}"/>
				</a>
			</td>
		
			<td width="10%">
				${popularCourseProv[1]}
			</td>
				
			<td width="30%">
				${popularCourseProv[2]}
			</td>
		
			<td width="50%">
				${popularCourseProv[7]}
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>