<%@page
	import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@page import="java.awt.print.Printable"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		//allow access only if session exists

		Collection<Collection<String>> dataCollection = (Collection<Collection<String>>) session
				.getAttribute("currentUserData");
		String currentSessionUser = (String) session.getAttribute("user");
		String data = null;
		String userName = null;
		String sessionID = null;

		if (currentSessionUser == null) {
			response.sendRedirect("/dist/partials/login.jsp");
		} else {

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user"))
						userName = cookie.getValue();
					if (cookie.getName().equals("JSESSIONID"))
						sessionID = cookie.getValue();
				}
			}

		//	out.write(currentSessionUser);

			//loop the 	Collection<Collection<String>> dataCollection  and revtriew data
			for (Collection<String> collection : dataCollection) {
				int count = collection.size();
				Object[] array = collection.toArray();
				System.out.println("Set ******* Set"+collection);
// 				for (count = 0; count <= 1; count++) {
					for (String data4455 : collection) {
						System.out.println(data4455 +"	  -  	");
						
						//out.write((String) array[0]);
					}


			}

		}
	%>
	

</body>
</html>