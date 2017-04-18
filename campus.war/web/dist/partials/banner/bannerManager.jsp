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
     20170330 DN c83-admin-manage-banner-update-banner-info-dn. Uploaded Banner : alt property set to "Yet No Image".  Removed the condition
     			 surrounded by the upload Banner element.
     			 Removed the condition check surrounded the "Banner Code" element.
     20170331 DN c83-admin-manage-banner-update-banner-info-dn changed the method argument to clearField('startDateInfor').
     			Banner activation and Deactivation field name typos corrected.
     			Add tool tip to URL field.
     20170405 DN c83-admin-manage-banner-update-banner-info-dn Enable radio button groups' values are changed to yes-- 1 no-- 0
          			add clearField('endtDateInfor') function to onclick event of the Banner deactivation date field
     20170407 DN c83-admin-manage-banner-update-banner-info-dn depend on the task to be perform either edit the banner or add new banner
          			add logic to display Add Banner or Edit Banner as the heading.
     20170417 DN c83-admin-manage-banner-update-banner-info-dn the bannerRecordUpdate element is included within script tags instead of jstl tags
          			the element appears when the record is to be edited.
 -->
 <!-- 20170418 TR c82 changed html structure -->
 <!-- 20170418 TR c82 added bootstrap grid classes -->
 <!-- 20170418 TR c82 update all inputs and text styles  -->

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


<div class="add-banner-screen">
    <div class="panel-heading" align="center">
        <h1>
            <c:choose>
                <c:when test="${param.CCO eq 'ADMEDTBNR' }">Edit Banner</c:when>
                <c:otherwise>Add Banner</c:otherwise>
            </c:choose>
        </h1>
    </div>

	<div>
		<form id="banner-from">			
			<div class="form-body clearfix">

			    <div class="col-lg-12 col-md-12 input-row">
                    <div class="col-lg-4 col-md-4">BannerCode :</div>
                    <div class="col-lg-8 col-md-8">
                        <input type="text" id="bannerCode" name="bannerCode" value="${param.bannerCode}" disabled >
                        <input type="hidden" id="bannerEditableImageName" name="bannerEditableImageName" value="${param.imageName}" >
                    </div>
                </div>
                <!-- End BannerCode input field -->

				<div class="col-lg-12 col-md-12 input-row">
				    <div class="col-lg-4 col-md-4">Advertiser : *</div>
				    <div class="col-lg-8 col-md-8">
                        <div id="advertiserInfor" style ="color:#C70039;"></div>
                        <c:choose>
                            <c:when test="${param.CCO eq 'ADMEDTBNR' }">
                                <input id="advertiser" name="page" value="${param.advertiserName }"
                                    list="advertiserList" class="text-field" type="text"
                                    placeholder="Select an Advertiser" onclick="clearField('advertiserInfor');">
                                <datalist id="advertiserList" name="advertiserList"> </datalist>
                                <input type="hidden" id="sAdvertiserCode" name="sAdvertiserCode" value="${param.advertizerCode}" />
                            </c:when>
                            <c:otherwise>
                                <input id="advertiser" name="page"
                                    list="advertiserList" class="text-field" type="text"
                                    placeholder="Select an Advertiser" onclick="clearField('advertiserInfor');">
                                <datalist id="advertiserList" name="advertiserList"> </datalist>
                                <input type="hidden" id="sAdvertiserCode" name="sAdvertiserCode" />
                            </c:otherwise>
                        </c:choose>
                    </div>
				</div>
				<!-- End Advertiser input field -->

                <div class="col-lg-12 col-md-12 input-row">
                    <div class="col-lg-4 col-md-4">Page : *</div>
                    <div class="col-lg-8 col-md-8">
                        <div id="pageInfor" style ="color:#C70039;"></div>
                        <c:choose>
                            <c:when test="${param.CCO eq 'ADMEDTBNR' }">
                                <input id="page" name="page" list="pageList" value="${param.pageName}"
                                    class="text-field" type="text" placeholder="Select a Page" onclick="clearField('pageInfor');">
                                <datalist id="pageList" name="pageList"> </datalist>
                                <input type="hidden" id="sPageCode" name="sPageCode" value= "${param.pageCode}"/>
                                <script>
                                    $('#page').trigger('input');
                                </script>
                            </c:when>
                            <c:otherwise>
                                <input id="page" name="page" list="pageList"
                                    class="text-field" type="text" placeholder="Select a Page" onclick="clearField('pageInfor');">
                                <datalist id="pageList" name="pageList"> </datalist>
                                <input type="hidden" id="sPageCode" name="sPageCode" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <!-- End Page input field -->

                <div class="col-lg-12 col-md-12 input-row">
                    <div class="col-lg-4 col-md-4">Advertising slot : *</div>
                    <div class="col-lg-8 col-md-8">
                        <div id="advertizingSlotInfor" style ="color:#C70039;"></div>
                        <c:choose>
                            <c:when test="${param.CCO eq 'ADMEDTBNR'}">
                                <input id="slot" name="slot" list="slotList" class="text-field" type="text" value="${param.pageSlotName}" placeholder="-- Select a Slot --" onclick="clearField('advertizingSlotInfor');">
                                <datalist id="slotList" name="slotList"> </datalist>
                                <input type="hidden" id="sSlotCode" name="sSlotCode" value="${param.pageSlotCode}" />
                            </c:when>
                            <c:otherwise>
                                <input id="slot" name="slot" list="slotList" class="text-field" type="text" placeholder="Select a Slot" onclick="clearField('advertizingSlotInfor');">
                                <datalist id="slotList" name="slotList"> </datalist>
                                <input type="hidden" id="sSlotCode" name="sSlotCode" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 input-row">
                    <div class="col-lg-4 col-md-4">Duration (Seconds) : *</div>
                    <div class="col-lg-8 col-md-8">
                        <div id="displayDurationInfor" style ="color:#C70039;"></div>
                        <input id="duration" type='text' onclick="clearField('displayDurationInfor');" value="${param.displayDuration}">
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 input-row">
                    <div class="col-lg-4 col-md-4"><label id="lblEnableBanner" name="lblEnableBanner">Enable :</label> </div>
                    <div class="col-lg-8 col-md-8 check-enable">
                        <label for="bannerEnable" hidden='true'>1</label>
                        <input type="radio" name="bannerEnableStatus" id='bannerEnable' value="1" <c:if test="${param.bannerActiveInactiveState != 0 }">
                        checked="checked"
                        </c:if>
                        ><span>YES</span>

                        <label for="bannerDissable" hidden='true'>0</label>
                        <input type="radio" name="bannerEnableStatus" id='bannerDissable' value="0" <c:if test="${param.bannerActiveInactiveState eq 0 }">
                        checked="checked"
                        </c:if>
                        ><span>NO</span>
                    </div>
                </div>
                <div class="col-lg-12 col-md-12 input-row">
                    <div class="col-lg-4 col-md-4">Banner Activation date : *</div>
                    <div class="col-lg-8 col-md-8">
                        <input type="date" name="startDate" id="startDate" onclick="clearField('startDateInfor')" value="${param.bannerActivateDate}">
                        <div id="startDateInfor" style ="color:#C70039;"></div>
                    </div>
                </div>

                <div class="col-lg-12 col-md-12 input-row">
                    <div class="col-lg-4 col-md-4">Banner Expire date : * </div>
                    <div class="col-lg-8 col-md-8">
                        <input type="date" name="endtDate" id="endtDate" onclick="clearField('startDateInfor');clearField('endtDateInfor');" value="${param.bannerDeactivateDate}">
                        <div id="endtDateInfor" style ="color:#C70039;"></div>
                    </div>
                </div>

			    <div class="col-lg-12 col-md-12 input-row" align="center">
			        <label class="sub-t1">Select the URL or Resource to load when Banner is Selected </label>
			    </div>

			    <div class="col-lg-12 col-md-12 input-row url-type" align="center">
			        <label>URL : </label>
                    <input type="radio" name="urlspecifier" value="1" id ="urlspecifierUrl"	<c:if test="${param.linkType eq 1}">checked="checked"</c:if>>
                    <label>Mini Web : </label>
                    <input type="radio" name="urlspecifier" value="2" id ="urlspecifierMiniWeb" <c:if test="${param.linkType eq 2}">checked="checked"</c:if>>
                    <label>Page : </label>
                    <input type="radio" name="urlspecifier" value="0" id ="urlspecifierPage" <c:if test="${param.linkType eq 3}">checked="checked"</c:if>>
			    </div>

                <div class="col-lg-12 col-md-12 input-row" align="center">
                    URL :
                    <input id="bannerDispatchingUrl" type='text' onclick="clearField('urlInfor');" value="${param.urlToNavigateClickingTheBaner}" data-toggle="tooltip" title="Please enter a valid Url / Mini  web address">
                    <div id="urlInfor" style ="color:#C70039;"></div>

                    <script>
                        $(document).ready(function(){
                            $('[data-toggle="tooltip"]').tooltip();
                        });
                    </script>
                </div>

			    <!-- Form image submit -->
			    <div class="col-lg-12 col-md-12 input-row" align="center"><label class="sub-t1">Upload Banner Preview</label></div>

                <div id="bannerImagediv01" class="col-lg-12 col-md-12 input-row" >
                    <div class="col-lg-4 col-md-4" align="right">Uploaded Banner :</div>
                    <div class="col-lg-8 col-md-8">
                        <img class="img-preview" id='imageName01'	src="${param.bannerUrl}"  alt='No Preview Image'>
                    </div>
                </div>

			    <div class="col-lg-12 col-md-12 input-row btn-area">
			        <div class="col-lg-6 col-md-6" align="right">
                        <button  id="openModalUpload" >
                            <c:choose>
                                <c:when test="${!empty param.bannerCode}">Change the Banner</c:when><c:otherwise>Upload the Banner</c:otherwise>
                            </c:choose>
                        </button>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <button id="bannerPageClearField">Clear Page</button>
                    </div>
                    <script>
                        if($('#bannerCode').val().length != 0){
                            $('#banner-from').append('<button id="bannerRecordUpdate">Update the Record</button>');
                        }
                    </script>
                </div>
            </div>
		</form>
	</div>
</div>
<!-- End add-banner-screen -->
</body>
</html>