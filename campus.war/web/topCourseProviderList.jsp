<!-- 20161030 DJ  c6-list-available-institutes-on-the-view view top course providers -->

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
<h2>Top View</h2>

	<table style="border: 2px; border-color: black; border-style: solid;">
		<tr>		
			<th>CODE</th>
			<th>NAME</th>	
			<img src="uom.PNG" />					
		</tr>
		<tr>
		<td>
			<a href=" #">
			 <img src="education/provider/logo/uom/uom.PNG"/>
			</a>
		</td>
		</tr>
		<c:forEach var="provider" items="${result.collection}">			
			<tr>
				<c:forEach var="pvAttribute" items="${provider}" varStatus="count">			
				<c:choose>
					 <c:when test="${count.index == 0}">
					 </c:when>
					 <c:when test="${count.index == 1}">
					 <c:set var="prefix" value="${pvAttribute}" />
					     <td><c:out value="${pvAttribute}"/></td>
					 </c:when>
					 <c:otherwise>
						<td><c:out value="${contextDeployLogoPath}${prefix}${pvAttribute}" /></td>
					 </c:otherwise>					
				 </c:choose>
				 
				<!--  <td>
					image
					<a href="#">
					<img src="/images/uom.PNG" />
					</a>
				</td> -->
				
				<%-- <td><c:out value="${pvAttribute}" /></td> --%>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	<h2>Top Rated</h2>

	<table style="border: 2px; border-color: black; border-style: solid;">
		<tr>
			<th></th>
			<th>CODE</th>
			<th>NAME</th>			
		</tr>
		<c:forEach var="provider" items="${tRCProviders}">			
			<tr>
				<c:forEach var="pvAttribute" items="${provider}" varStatus="vs">				
				<c:if test="${vs.index == 1}">
				<td>
					image
						<a href="#">
							<img src="${pvAttribute}"/>
						</a>
				</td>
				</c:if>
				<td><c:out value="${pvAttribute}" /></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	<br>

	<form action="PublicController" method="post">
		<input type="text" name="category">
		<button type="submit" name="CCO" value="ACPL">View More</button>
	</form>
	<!-- <form action="PublicController" method="post">
		<input type="hidden" id="hdCategory" name="hdCategory"
			value="hiddenValue"> <input type="submit">
	</form> -->
	<!-- <div> <a href="viewMoreInstitute.jsp">View More</a></div> -->
</body>
</html>