<!-- 20170202 DN c131-admin-manage-banner-upload-banner-image-dn created the initial bannerManager.jsp page -->
<!-- 20170202 DN c131-admin-manage-banner-upload-banner-image-dn added modal div element to the document -->
<!-- 20170208 DN c131-admin-manage-banner-upload-banner-image-dn change the script to include uploadBanner.js -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banner Manager</title>


<!-- Bootstrap & CSS Style-->
    <link href="../../bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/image-slides.css" rel="stylesheet">

<!-- jQuery & Other js -->
	<script src="/dist/js/header/ui-populate-helper.js"></script>
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
	<script src="/dist/js/banner/uploadBanner.js"></script>
	<script src="/dist/bower-components/w3/w3data.js"></script>
</head>
<body>

<!--  logout-popup window message -->
<div class="modal fade" id="messagePopUp" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"> <b>System Message</b>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">Close</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-6">
							<label class="" id="displayLabel"></label> <!-- messages to be displayed -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<div class="panel panel-default">
      <div class="panel-heading" align="center"><h1>Add Banner</h1></div>
      <div class="panel-body"></div>
    </div>

	<div align="center">
		

		<form id="banner-from">
			<div>
				Advertiser &nbsp; 
				<input id="page" name="page"
					list="advertiserList" class="text-field" type="text"
					placeholder="-- Select an Advertiser --">
				<datalist id="advertiserList" name="advertiserList"> </datalist>
				<input type="hidden" id="sAdvertiserCode" name="sAdvertiserCode" />
			</div>

			<br>
			<div>
				Page &nbsp; 
				<input id="page" name="page" list="pageList"
					class="text-field" type="text" placeholder="-- Select a Page --">
				<datalist id="pageList" name="pageList"> </datalist>
				<input type="hidden" id="sPageCode" name="sPageCode" />
			</div>
			<br> <br>
			<div>
				Advertising slot &nbsp; <input id="slot" name="slot"
					list="slotList" class="text-field" type="text"
					placeholder="-- Select a Slot --">
				<datalist id="slotList" name="slotList"> </datalist>
				<input type="hidden" id="sSlotCode" name="sSlotCode" />
			</div>

			<br>

			<div>
				Duration &nbsp;<input id="duration" type='text'>
			</div>
			<label id="lblEnableBanner" name="lblEnableBanner">Enable</label> <input
				type="radio" name="bannerEnable" value="yes"> <label
				for="bannerEnable">Yes</label> <input type="radio"
				name="bannerDisable" value="No"> <label
				for="bannebannerDisablerEnable">No</label>
			<div></div>
			<div>
				<br> Start date &nbsp; <input type="date" name="startDate"
					id="startDate">
			</div>
			<br>
			<div>
				End date &nbsp;<input type="date" name="endtDate" id="endtDate">
			</div>
			<br>
			<div>
				URL &nbsp;<input id="advertSlot" type='text'>
			</div>
			<br>
			<!-- Form image submit -->

			<label>Upload Banner</label> 
			<input type="file" id="file-select"	name="file-select" accept="image/gif, image/jpeg, image/png">
			<input type="hidden"	value="xyz">
			<br>
			<button type="submit" id="uploadBbutton">Upload</button>
		</form>

	</div>
</body>
</html>