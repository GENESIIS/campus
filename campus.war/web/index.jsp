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
	<h1>Hello World..!!</h1>

	<form action="PublicController" method="POST">
		<button type="submit" name="CCO" id="CCO" value="LCLP"
			class="pure-button pure-button-primary">Higher Education</button>
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
		</table>
		<input type="hidden" name="categoryId" id="categoryId" value="2" />
	</form>
</body>
</html>