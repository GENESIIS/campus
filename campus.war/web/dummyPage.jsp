
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
Please Enter category number
<div align="center" >
	<!-- 	<form action="PublicController" method="post">
			<input type="text" name="category">
			<button type="submit" name="CCO" value="LAAI">SUBMIT</button>
		</form> -->
		<form action="PublicController" method="post">
			<input type="text" name="category">
			<button type="submit" name="CCO" value="TCPL">TOP CP lIST</button>
		</form>
</div>
</body>
</html>