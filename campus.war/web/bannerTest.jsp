<%-- 20161116 MM c2-integrate-google-banners INIT page --%>
<%-- 20161118 MM c2-integrate-google-banners Added code to properly loop banner collections and 
				added classes to image elements so they can be processed by JQuery code --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sample Page To Display Banners</title>

<style>
#rotating-item-wrapper {
    position: relative;
    width: 980px;
    height: 347px;
}
.rotating-item {
    display: none;
    position: absolute;
    top: 0;
    left: 0;
}

</style>

</head>
<body>
<!-- &#92; -->
	<div id="rotating-item-wrapper"> 
		<c:forEach var="banner" items="${BANNER_SLOT_1}" varStatus="vs">
			<a href="#"><img data-banner-code="${banner[2]}" class="banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[11]}"/>"/></a>
		</c:forEach>		
	</div>
	
	<div id="rotating-item-wrapper"> 
		<c:forEach var="banner" items="${BANNER_SLOT_2}" varStatus="vs">
			<a href="#"><img data-banner-code="${banner[2]}" class="banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[11]}"/>"/></a>
		</c:forEach>		
	</div>
	<div id="rotating-item-wrapper"> 
		<c:forEach var="banner" items="${BANNER_SLOT_3}" varStatus="vs">
			<a href="#"><img data-banner-code="${banner[2]}" class="banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[11]}"/>"/></a>
		</c:forEach>		
	</div>

<script src="/dist/bower-components/jquery/jquery.min.js"></script>
<!-- <script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script> -->
<script src="/dist/js/banner/banner_handler.js"></script>

</body>
</html>