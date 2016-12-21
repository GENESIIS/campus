<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		//allow access only if session exists
		String currentSessionUser = null;
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/dist/partials/login.jsp");
		} else
			currentSessionUser = (String) session.getAttribute("user");
		String data = null;
		String userName = null;
		String sessionID = null;
		String studentData = (String)session.getAttribute("currentUserData");
		Collection<Collection<String>> studentDataCollection =  null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					userName = cookie.getValue();
				if (cookie.getName().equals("JSESSIONID"))
					sessionID = cookie.getValue();
			}
		}
	%>
<%
	//loop the 	Collection<Collection<String>> dataCollection  and revtriew data
	
	studentDataCollection = new ArrayList<Collection<>>();
	for (Collection<String> collection : studentDataCollection) {
			Object[] array = collection.toArray();
			data = (String) array[0];

		}


%>

</body>
</html>