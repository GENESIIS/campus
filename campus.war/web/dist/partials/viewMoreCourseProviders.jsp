<!-- 20161030 DJ  c6-list-available-institutes-on-the-view view more institutes -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campus.lk -Institutes</title>
<style>
div.container {
	width: 100%;
	border: 1px solid gray;
}

header,footer {
	padding: 1em;
	color: white;
	background-color: #59DFB9;
	clear: left;
	text-align: center;
}

article {
	margin-left: 170px;
	border-left: 1px solid gray;
	padding: 1em;
	overflow: hidden;
}

article {
	margin-left: 170px;
	border-left: 1px solid gray;
	padding: 1em;
	overflow: hidden;
}
</style>
</head>
<body>
	<h2>More Course Providers View</h2>	
	<div class="container">

		<header>
			<select id="selectedCat" name="selectedCat">
			   <option value="0">--Category--</option>
				<c:forEach var="catOuterTypes" items="${categoryList}">
					<c:forEach var="catTypes" items="${catOuterTypes}"
						varStatus="count">
						<c:if test="${count.index == 0}">
							<c:set var="catCode" value="${catTypes}" />
						</c:if>
						<c:if test="${count.index == 1}">
							<c:set var="catName" value="${catTypes}" />
						</c:if>
					</c:forEach>
					<option value="${catCode}">${catName}</option>
				</c:forEach>
			</select>
			<!-- <select>
				<option value="1">--Category--</option>
				<option value="2">Higher Education</option>
				<option value="3">School Education</option>
				<option value="4">Corporate Training</option>
			</select> 
			<select>
			  <option value="1">--Course Provider Type--</option>
				<option value="2">University</option>
				<option value="3">Private Institute</option>
				<option value="4">Individual Tutor</option>
			</select> -->
			<select>
			  <option value="1">--District--</option>
				<option value="2">University</option>
				<option value="3">Private Institute</option>
				<option value="4">Individual Tutor</option>
			</select>
			<!-- <select>
			  <option value="1">--Major--</option>
				<option value="2">University</option>
				<option value="3">Private Institute</option>
				<option value="4">Individual Tutor</option>
			</select>
			<select>
			  <option value="1">--Level--</option>
				<option value="2">University</option>
				<option value="3">Private Institute</option>
				<option value="4">Individual Tutor</option>
			</select> -->
		</header>

		<section>
			<h4>All Course Providers</h4>
			<table>
				<tr>
					<td><label>All <span id="courseCount"
							name="courseCount"></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>
				</tr>
				<tr>
					<td><label class="flip">Category <span id=""
							name=""></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>

				</tr>
				<tr>
					<td><label class="flip">Course Provider Type <span id=""
							name=""></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>

				</tr>
				
				<tr>
					<td><label class="flip">Major <span id="majorCount"
							name="majorCount"></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>

				</tr>
				<tr>
					<td><label class="flip">Levels <span id=""
							name=""></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>

				</tr>
				<tr>
					<td>
						<!-- <form action="PublicController" method="post">
							<input type="text" name="category">
							<button type="submit" name="CCO"
								value="LIST_ALL_COURSE_PROVIDERS">View More</button>
						</form> -->
						<form id="searchbox" action="">
							<input id="submit" type="submit" value="Search">
						</form>
					</td>
				</tr>
			</table>
		</section>

		<article>
			<table
				style="border: 2px; border-color: black; border-style: solid; width: 100%">
				<tr>
					<c:forEach var="provider" items="${result.collection}">
						<td
							style="border: 2px; border-color: blue; border-style: solid; width: 10%">
							<c:forEach var="pvAttribute" items="${provider}"
								varStatus="count">
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
		</article>
		<footer>Copyright © Genesiis.com</footer>
	</div>

	<%-- <table
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
								<td><c:out value="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" /></td>
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
	</table> --%>
</body>
</html>