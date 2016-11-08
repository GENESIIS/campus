<!-- 20161024 DN  c10-contacting-us-page test page for -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%@ page import="com.genesiis.xeno.entity.View,java.util.ArrayList" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Interim Page</title>
</head>
<body>
This page is expected to temporarily function as the Home page.

<h3 align="center">To trigger JSON request for Programme details:</h3>
<div align="center" >
		<form action="PublicController" method="POST">
			<input type="text" name="category">
			<input type="text" name="pageNum">
			<button type="submit" name="CCO" value="LCP">SUBMIT</button>
		</form>
</div>
<br><br><br>

<h3 align="center">To load JSP containing CourseProvider details:</h3>
<div align="center" >
	<form action="PublicController" method="POST">
		<table>
			<tr>
				<th>Code</th>
				<th>Name</th>
			</tr>
			<tr>
				<td>1</td>
				<td>School</td>
			</tr>
			<tr>
				<td>2</td>
				<td>Higher Education</td>
			</tr>
			<tr>
				<td>3</td>
				<td>Corporate Training</td>
			</tr>
		</table>
		<input type="text" name="categoryId" id="categoryId"/>
		<button type="submit" name="CCO" id="CCO" value="LIST_CATEGORY_LANDING_PAGE"
			class="pure-button pure-button-primary">Higher Education</button>
	</form>
</div>
</body>
</html>