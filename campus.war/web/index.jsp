<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>	
	<h1>LIST COURSE PROVIDER </h1>
	<h4>* Please enter category code for each category wise course provider list</h4>
	<!-- <a href="dummyPage.jsp">Click Here to Proceed</a> -->
	<form action="PublicController" method="post">
		<input type="text" name="category">
		<button type="submit" name="CCO" value="LIST_TOP_COURSE_PROVIDERS">TOP CP  LIST</button>
	</form>
</body>
</html>