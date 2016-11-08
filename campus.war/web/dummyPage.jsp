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
<div align="center" >
		<form action="PublicController" method="post">
			<input type="text" name="category">
			<input type="text" name="pageNum">
			<button type="submit" name="CCO" value="LCP">SUBMIT</button>
		</form>
</div>
</body>
</html>