<!-- 20170202 DN c131-admin-manage-banner-upload-banner-image-dn created the initial bannerManager.jsp page -->
<!-- 20170202 DN c131-admin-manage-banner-upload-banner-image-dn added modal div element to the document -->
<!-- 20170208 DN c131-admin-manage-banner-upload-banner-image-dn change the script to include uploadBanner.js -->
<!-- 20170208 DN c131-admin-manage-banner-upload-banner-image-dn removed unnecessary script /dist/js/header/ui-populate-helper.js-->
<!-- 20170216 DN c131-admin-manage-banner-upload-banner-image-dn  add the modal window to upload the banner image. -->
<!-- 20170216 DN c131-admin-manage-banner-upload-banner-image-dn add extra radio button to select link types-->
<!-- 20170227 DN c131-admin-manage-banner-upload-banner-image-dn add error <div> tags-->
<!-- 20170321 DN c131-admin-manage-banner-upload-banner-image-dn label is created that is attached to the radio buttons.  -->
<!-- 20170322 DN c83-admin-manage-banner-update-banner-info-dn add the jsp tag library to the page
				 included a div element that holds the banner image on the page.
				 Made upload banner visible depend on the situation. create update button.
	 20170324 DN c83-admin-manage-banner-update-banner-info-dn two hidden fields for capturing banner code and image name
	 			 have been inserted.
	 20170328 DN c83-admin-manage-banner-update-banner-info-dn. Element id 'bannerCode' made disable and visible id CCO is to ADMEDTBNR.

 -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
	<script src="/dist/bower-components/w3/w3data.js"></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
	<script src="/dist/js/banner/uploadBanner.js"></script>
</head>
<body>

<!--  logout-popup window message -->
<div class="modal fade" id="messagePopUp" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"> <b>System Message</b>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" id='systemMessageClose'>Close</span>
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


<!--  Banner Upload-pop-up window -->
<div class="modal fade" id="bannerUploadPopUp" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="login-dialog modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"> <b>Up Load the Banner</b>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" id='bannerModalClose'>Close</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-6">
							<label class="" id="bannerDisplayLabel"></label> <!-- messages to be displayed -->
							<br><br>
							<form action="">
								<input type="file" id="file-select"	name="file-select" accept="image/gif, image/jpeg, image/png">
								<input type="hidden"	value="xyz">
								<br>
								<button type="submit" id="uploadBbutton" disabled >Upload</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Banner Upload-pop-up window -->


<div class="panel panel-default">
      <div class="panel-heading" align="center"><h1>Add Banner</h1></div>
      <div class="panel-body"></div>
    </div>

	<div align="center">
		

		<form id="banner-from">			
			<div >
				<c:if test="${param.CCO eq 'ADMEDTBNR'}">
			     BannerCode :
					<input type="text" id="bannerCode" name="bannerCode" value="${param.bannerCode}" disabled >
				</c:if>
				<input type="hidden" id="bannerEditableImageName" name="bannerEditableImageName" value="${param.imageName}" >
				<br>Advertiser * &nbsp; 
				<div id="advertiserInfor" style ="color:#C70039;"></div><br>
				<c:choose>
					<c:when test="${param.CCO eq 'ADMEDTBNR' }">
						<input id="advertiser" name="page" value="${param.advertiserName }"
							list="advertiserList" class="text-field" type="text"
							placeholder="-- Select an Advertiser --" onclick="clearField('advertiserInfor');">
						<datalist id="advertiserList" name="advertiserList"> </datalist>
						<input type="hidden" id="sAdvertiserCode" name="sAdvertiserCode" value="${param.advertizerCode}" />
					</c:when>
					<c:otherwise>
					 	<input id="advertiser" name="page"
							list="advertiserList" class="text-field" type="text"
							placeholder="-- Select an Advertiser --" onclick="clearField('advertiserInfor');">
						<datalist id="advertiserList" name="advertiserList"> </datalist>
						<input type="hidden" id="sAdvertiserCode" name="sAdvertiserCode" />
					</c:otherwise>
				</c:choose>
			</div>
			<br>
			
			<c:if test="${param.CCO eq 'ADMEDTBNR' }">
				<div align="right" id="bannerImagediv01" >
				 Uploaded Banner :
				<img id='imageName01'src="${param.bannerUrl}" alt='banner-Image' style='width:200px;hight:60px'>
				</div>
			</c:if>
			
			<div>
				Page * &nbsp; 
				<div id="pageInfor" style ="color:#C70039;"></div><br>
				
				<c:choose>
					<c:when test="${param.CCO eq 'ADMEDTBNR' }">
						<input id="page" name="page" list="pageList" value="${param.pageName}"
							class="text-field" type="text" placeholder="-- Select a Page --" onclick="clearField('pageInfor');">
						<datalist id="pageList" name="pageList"> </datalist>
						<input type="hidden" id="sPageCode" name="sPageCode" value= "${param.pageCode}"/>
						<script>
							$('#page').trigger('input'); 
						</script>
					</c:when>
					<c:otherwise>
						<input id="page" name="page" list="pageList"
							class="text-field" type="text" placeholder="-- Select a Page --" onclick="clearField('pageInfor');">
						<datalist id="pageList" name="pageList"> </datalist>
						<input type="hidden" id="sPageCode" name="sPageCode" />
					</c:otherwise>
				</c:choose>
				
			</div>
			<br> <br>
			<div>
				Advertising slot * &nbsp; 
				<div id="advertizingSlotInfor" style ="color:#C70039;"></div><br>
				<c:choose>
					<c:when test="${param.CCO eq 'ADMEDTBNR'}">
						<input id="slot" name="slot" list="slotList" class="text-field" type="text" value="${param.pageSlotName}"
									placeholder="-- Select a Slot --" onclick="clearField('advertizingSlotInfor');">
							<datalist id="slotList" name="slotList"> </datalist>
							<input type="hidden" id="sSlotCode" name="sSlotCode" value="${param.pageSlotCode}" />
					</c:when>
					<c:otherwise>
						<input id="slot" name="slot" list="slotList" class="text-field" type="text"
								placeholder="-- Select a Slot --" onclick="clearField('advertizingSlotInfor');">
						<datalist id="slotList" name="slotList"> </datalist>
						<input type="hidden" id="sSlotCode" name="sSlotCode" />
					</c:otherwise>
				</c:choose>
				
			</div>
			<br>
			<div>
				Duration (Seconds) *&nbsp;
				<div id="displayDurationInfor" style ="color:#C70039;"></div><br>
				<input id="duration" type='text' onclick="clearField('displayDurationInfor');" value="${param.displayDuration}">
			</div>
			<br>
			<label id="lblEnableBanner" name="lblEnableBanner">Enable</label>
			<label for="bannerEnable" hidden='true'>true</label>
			 <input type="radio" name="bannerEnableStatus" id='bannerEnable' value="true" 
			 <c:if test="${param.bannerActiveInactiveState != 0 }">
			 	checked="checked"
			 </c:if>
			  >YES 			  
			 <label for="bannerDissable" hidden='true'>false</label>
			<input type="radio" name="bannerEnableStatus" id='bannerDissable' value="false"
			<c:if test="${param.bannerActiveInactiveState eq 0 }">
			 	checked="checked"
			 </c:if>
			 >NO
			<div></div>
			<div>
			 <div id="startDateInfor" style ="color:#C70039;"></div><br>
				<br> Banner Avtivation date* &nbsp; <input type="date" name="startDate"
					id="startDate" onclick="clearField('startDateInfor')" value="${param.bannerActivateDate}">
			</div>
			<br>
			<div>
			<div id="endtDateInfor" style ="color:#C70039;"></div><br>
				Banner deavtivation date * &nbsp;<input
					type="date" name="endtDate" id="endtDate"
					onclick="clearField('endtDateInfor');"
					value="${param.bannerDeactivateDate}">
			</div>
			<br><br>
			<div>
			<b>Select the URL or Resource to load when Banner is Selected </b><br><br>
			URL : &nbsp;&nbsp;
			<input type="radio" name="urlspecifier" value="1" id ="urlspecifierUrl"	<c:if test="${param.linkType eq 1}">checked="checked"</c:if>>
			&nbsp;&nbsp;
			Mini Web : &nbsp;&nbsp;
			<input type="radio" name="urlspecifier" value="2" id ="urlspecifierMiniWeb" <c:if test="${param.linkType eq 2}">checked="checked"</c:if>  >
			Page : &nbsp;&nbsp;
			&nbsp;&nbsp;
			<input type="radio" name="urlspecifier" value="0" id ="urlspecifierPage" <c:if test="${param.linkType eq 3}">checked="checked"</c:if>  >
			<br><br>
			<div id="urlInfor" style ="color:#C70039;"></div><br>			
				URL &nbsp;<input id="bannerDispatchingUrl" type='text' onclick="clearField('urlInfor');" value="${param.urlToNavigateClickingTheBaner}">
			</div>
			<br><br>
			<!-- Form image submit -->

			<label><b>Upload Banner</b></label> <br><br><br>
<!-- 			<button  id="openModalUpload" data-target="#bannerUploadPopUp" >Upload Banner</button> -->
			
			<button  id="openModalUpload" >
			<c:choose>
				<c:when test="${!empty param.bannerCode}">Change the Banner</c:when><c:otherwise>Upload the Banner</c:otherwise>
			</c:choose>
			</button>
			<button id="bannerPageClearField">Clear Page</button>
			<c:if test="${!empty param.bannerCode}">
				<button id="bannerRecordUpdate">Update the Record</button>
			</c:if>
		</form>





	</div>
</body>
</html>