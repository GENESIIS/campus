<%-- 20170209 MM c111-display-banners-on-jsp-load - Created file based on the bannerTest.jsp that 
				was available under c2-integrate-google-banners-MP --%>
<%-- 20170209 MM c111-display-banners-on-jsp-load - Added id html attribute to banner-slot divs --%>
<%-- 20170220 MM c127-display-banners-on-jsp-load-front-end - Changed the way JSP name is obtained 
				to use ${pageScope['javax.servlet.jsp.jspPage']} --%>
<%-- 20170220 MM c127-display-banners-on-jsp-load-front-end - Added hidden field 
				'areBannersDeliveredWithPage' to help determine by JQuery code whether to send an 
				Ajax call to fetch banners if the slots remain empty after page load --%>
<%-- 20170404 MM c117-display-banners-record-viewcount-back-end - Added test button to trigger 
				sending of Ajax requests for banner-view-stat-adding --%>
<%-- 20170502 MM c128-display-banners-record-viewcount-front-end - Added image element to aid testing 
				of when banners go out of view horizontally (when user may scroll to extreme left/right 
				of screen) --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sample Page To Display Banners</title>

<style>
/*IMPORTANT: THE FOLLOWING CSS CODE *MUST* BE AVAILABLE TO ANY PAGE THAT CONTAINS BANNER CODE*/
.banner-wrapper {
	height: 180px; /* will be removed at integration, allowing the slot-size to be specified on a per-slot basis */
	width: 200px;  /* will be removed at integration, allowing the slot-size to be specified on a per-slot basis */
	position: relative;
}
.banner-wrapper img {
    left:0;
    opacity:0;
    position:absolute;
    top:0;
}
.banner-wrapper img.banner-shown {
    opacity:1;
    transition: opacity 0.5s ease-in-out;
}
</style>

</head>
<body>

<%--IMPORTANT: FOR EACH "PAGESLOT" RECORD IN THE DB FOR THE "PAGE" RECORD THAT REPRESENTS 
	A JSP PAGE, THERE NEEDS TO BE A DIV HTML TAG (WITH CLASS "banner-wrapper") ON THAT 
	JSP WITH THE FOLLOWING STRUCTURE --%>
	<div class="banner-wrapper" id="SLOT_BANNER_TEST_1"> 
	<c:if test="${not empty SLOT_BANNER_TEST_1}">
		<c:choose>
			<c:when test="${fn:startsWith(SLOT_BANNER_TEST_1, '<script')}"> 
				${SLOT_BANNER_TEST_1}
			</c:when>
			<c:otherwise>			
				<c:forEach var="banner" items="${SLOT_BANNER_TEST_1}" varStatus="vs">
					<a href="${banner[7]}" target="_blank">
						<img data-timeout="${banner[5]}" data-banner-code="${banner[2]}" class="<c:if test="${vs.index == 0}">banner-shown</c:if> banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[10]}"/>"/>
					</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</c:if>		
	</div>	
<%--IMPORTANT: THE STRING "SLOT_BANNER_TEST_1" ABOVE MUST BE REPLACED WITH THE VALUE 
	GIVEN FOR "NAME" COLUMN IN THE RELATED "PAGESLOT" DB TABLE RECORD THAT REPRESENTS
	THIS BANNER SLOT --%>
	
	<div class="banner-wrapper" id="SLOT_BANNER_TEST_2"> 
	<c:if test="${not empty SLOT_BANNER_TEST_2}">
		<c:choose>
			<c:when test="${fn:startsWith(SLOT_BANNER_TEST_2, '<script')}"> 
				${SLOT_BANNER_TEST_2}
			</c:when>
			<c:otherwise>			
				<c:forEach var="banner" items="${SLOT_BANNER_TEST_2}" varStatus="vs">
					<a href="${banner[7]}" target="_blank">
						<img data-timeout="${banner[5]}" data-banner-code="${banner[2]}" class="<c:if test="${vs.index == 0}">banner-shown</c:if> banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[10]}"/>"/>
					</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</c:if>		
	</div>
	
	<div class="pull-right" style="margin-left: 400px">
		<img src="https://www.wired.com/wp-content/uploads/2015/09/google-logo-1200x630.jpg"/>	
	</div>
	
	<div class="banner-wrapper" id="SLOT_BANNER_TEST_3"> 
	<c:if test="${not empty SLOT_BANNER_TEST_3}">
		<c:choose>
			<c:when test="${fn:startsWith(SLOT_BANNER_TEST_3, '<script')}"> 
				${SLOT_BANNER_TEST_3}
			</c:when>
			<c:otherwise>			
				<c:forEach var="banner" items="${SLOT_BANNER_TEST_3}" varStatus="vs">
					<a href="${banner[7]}" target="_blank">
						<img data-timeout="${banner[5]}" data-banner-code="${banner[2]}" class="<c:if test="${vs.index == 0}">banner-shown</c:if> banner rotating-item" src="${bannerPath}\<c:out value="${banner[2]}"/>\<c:out value="${banner[10]}"/>"/>
					</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</c:if>		
	</div>
	
<!-- 	The name of the current page; this is required to be specified with the following mark-up -->
	<input type="hidden" id="pageName" value="<c:out value="${pageScope['javax.servlet.jsp.jspPage']}"/>">
	
<!-- 	A hidden input to hold a "flag" to indicate whether banner images were attempted to be loaded
		into this page via a DB call when the JSP page was being loaded -->
	<c:choose>
		<c:when test="${not empty areBannersDeliveredWithPage}">
			<c:choose>
				<c:when test="${areBannersDeliveredWithPage == true}">
					<c:set var="bannerLoadStatus" value="true"/>
				</c:when>
				<c:otherwise>
					<c:set var="bannerLoadStatus" value="false"/>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:set var="bannerLoadStatus" value="false"/>
		</c:otherwise>
	</c:choose>
	<input type="hidden" id="areBannersDeliveredWithPage" value="<c:out value="${bannerLoadStatus}"/>">
	
	<button id="bannerViewStatTestBtn">Click to trigger execution of banner-view-stat back-end code</button>
	
<%-- IMPORTANT: /dist/js/banner/banner_handler.js AND /dist/bower-components/jquery/jquery.min.js (jQuery v2.2.2) 
	FILES *MUST* BE AVAILABLE TO ANY PAGE THAT CONTAINS BANNER CODE --%>
<%-- WARNING: BANNER HANDLER CODE WILL NOT WORK WITH JQUERY 3.1.1. DISABLE IT ON PAGES WHERE BANNERS APPEAR --%>
<script src="/dist/bower-components/jquery/jquery.min.js"></script>
<!-- <script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script> -->
<script src="/dist/js/banner/banner_handler.js"></script>

</body>
</html>