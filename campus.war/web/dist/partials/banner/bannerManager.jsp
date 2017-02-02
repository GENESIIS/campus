<!-- 20170202 DN c131-admin-manage-banner-upload-banner-image-dn created the initial bannerManager.jsp page -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banner Manager</title>


<!-- Bootstrap & CSS Style-->
    <link href="../../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../bower-components/bootstrap/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/image-slides.css" rel="stylesheet">
    <link href="../../css/button-effect.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" media="all">

<!-- jQuery & Other js -->

<%-- IMPORTANT: /dist/js/banner/banner_handler.js AND /dist/bower-components/jquery/jquery.min.js (jQuery v2.2.2) 
	FILES *MUST* BE AVAILABLE TO ANY PAGE THAT CONTAINS BANNER CODE --%>
<%-- WARNING: BANNER HANDLER CODE WILL NOT WORK WITH JQUERY 3.1.1. DISABLE IT ON PAGES WHERE BANNERS APPEAR --%>
<script src="/dist/bower-components/jquery/jquery.min.js"></script>


</head>
<body>

	<div align="center">
		<div>
			<h1>Add Banner</h1>
		</div>

		<form id="banner-from">
			<div>
				Advertiser<br> <input id="page" name="page"
					list="advertiserList" class="text-field" type="text"
					placeholder="-- Select an Advertiser --">
				<datalist id="advertiserList" name="advertiserList"> </datalist>
				<input type="hidden" id="sAdvertiserCode" name="sAdvertiserCode" />
			</div>

			<br>
			<div>
				Page <br> <input id="page" name="page" list="pageList"
					class="text-field" type="text" placeholder="-- Select a Page --">
				<datalist id="pageList" name="pageList"> </datalist>
				<input type="hidden" id="sPageCode" name="sPageCode" />
			</div>
			<br> <br>
			<div>
				Advertising slot<br> <input id="slot" name="slot"
					list="slotList" class="text-field" type="text"
					placeholder="-- Select a Slot --">
				<datalist id="slotList" name="slotList"> </datalist>
				<input type="hidden" id="sSlotCode" name="sSlotCode" />
			</div>

			<br>

			<div>
				Duration <input id="duration" type='text'>
			</div>
			<label id="lblEnableBanner" name="lblEnableBanner">Enable</label> <input
				type="radio" name="bannerEnable" value="yes"> <label
				for="bannerEnable">Yes</label> <input type="radio"
				name="bannerDisable" value="No"> <label
				for="bannebannerDisablerEnable">No</label>
			<div></div>
			<div>
				<br> Start date <input type="date" name="startDate"
					id="startDate">
			</div>
			<br>
			<div>
				End date <input type="date" name="endtDate" id="endtDate">
			</div>
			<br>
			<div>
				URL <input id="advertSlot" type='text'>
			</div>
			<br>
			<!-- Form image submit -->

			<label>Upload Banner</label> <input type="file" id="file-select"
				name="file-select" accept="image/gif, image/jpeg, image/png">
			<br>
			<br>
			<button type="submit" id="uploadBbutton">Upload</button>
		</form>

	</div>
</body>
</html>