<%-- 20161116 MM c2-integrate-google-banners INIT page --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sample Page To Display Banners</title>
</head>
<body>
	<div id="banner_slot_1"> 
	is banner slot 1 empty? ${empty BANNER_SLOT_1}
		<c:forEach var="banner" items="${BANNER_SLOT_1}">
			${banner}
		</c:forEach>
		${BANNER_SLOT_1}
	</div>
	<div id="banner_slot_2">  
	is banner slot 2 empty? ${empty BANNER_SLOT_2}
		<c:forEach var="banner" items="${BANNER_SLOT_2}">
			${banner}
		</c:forEach>
		${BANNER_SLOT_2}
	</div>
	<div id="banner_slot_3">  
	is banner slot 3 empty? ${empty BANNER_SLOT_3}
		<c:forEach var="banner" items="${BANNER_SLOT_3}">
			${banner}
		</c:forEach>
		${BANNER_SLOT_3}
	</div>
</body>
</html>