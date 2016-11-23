<%-- 20161116 MM c2-integrate-google-banners INIT page --%>
<%-- 20161118 MM c2-integrate-google-banners Added code to properly loop banner collections and 
				added classes to image elements so they can be processed by JQuery code --%>
<%-- 20161120 MM c2-integrate-google-banners Minimised JSP code to write to display elements 
				from a collection of banner records. --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sample Page To Display Banners</title>

<style>
.rotating-item-wrapper {
	height: 180px;
	width: 200px;
	position: relative;
}
.rotating-item-wrapper img {
    left:0;
    opacity:0;
    position:absolute;
    top:0;
}
.rotating-item-wrapper img.banner-shown {
    opacity:1;
    transition: opacity 0.5s ease-in-out;
}
</style>

</head>
<body>
	<div class="rotating-item-wrapper"> 
		<c:forEach var="banner" items="${SLOT_BANNER_TEST_1}" varStatus="vs">
			<a href="${banner[7]}" target="_blank">
				<img data-timeout="${banner[5]}" data-banner-code="${banner[2]}" class="<c:if test="${vs.index == 0}">banner-shown</c:if> banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[11]}"/>"/>
			</a>
		</c:forEach>		
	</div>
	
	<div class="rotating-item-wrapper"> 
		<c:forEach var="banner" items="${BANNER_SLOT_2}" varStatus="vs">
			<a href="${banner[7]}" target="_blank">
				<img data-timeout="${banner[5]}" data-banner-code="${banner[2]}" class="<c:if test="${vs.index == 0}">banner-shown</c:if> banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[11]}"/>"/>
			</a>
		</c:forEach>		
	</div>
	<div class="rotating-item-wrapper"> 
		<c:forEach var="banner" items="${SLOT_BANNER_TEST_3}" varStatus="vs">
			<a href="${banner[7]}" target="_blank">
				<img data-timeout="${banner[5]}" data-banner-code="${banner[2]}" class="<c:if test="${vs.index == 0}">banner-shown</c:if> banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[11]}"/>"/>
			</a>
		</c:forEach>		
	</div>
<!-- WARNING: JQUERY 3.1.1 BANNER HANDLER CODE WILL NOT WORK WITH JQUERY 3.1.1. DISABLE IT ON PAGES WHERE BANNERS APPEAR -->
<script src="/dist/bower-components/jquery/jquery.min.js"></script>
<!-- <script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script> -->
<script src="/dist/js/banner/banner_handler.js"></script>

</body>
</html>