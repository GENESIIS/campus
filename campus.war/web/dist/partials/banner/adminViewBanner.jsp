<!-- 20170308 DN c81-admin-manage-banner-add-and-view-banner-dn mock up design completed -->
<!-- 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn add radio buttons to model
				  PENDING,EXPIRED banner status.

     20170309 DN c81-admin-manage-banner-add-and-view-banner-dn removed the style script and tbody
                  tag of the table from the jsp file. Add the Row #  <th> element
     20170314 DN c81-admin-manage-banner-add-and-view-banner-dn add id to the button 'Filter the Banner Button
	 20170315 DN c81-admin-manage-banner-add-and-view-banner-dn Admin Add New Banner and Admin add New Advertiser buttons are added.
	 20170322 DN c83-admin-manage-banner-update-banner-info-dn add extra div to display banner image when the page is reached to edit
	 			 banner record.Add jsp code to populate input fields brought from editing a banner record function from adminViewBanner.jsp page.
 -->
 <!-- 20170403 TR c87 Added common header -->
 <!-- 20170403 TR c87 Re-arranged page html layout with adding css classes -->


<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin View Banner</title>


<!-- Bootstrap & CSS Style -->
    <link href="/dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/dist/css/style.css" rel="stylesheet">
    <link href="../../css/image-slides.css" rel="stylesheet">

<!-- jQuery & Other js -->
	
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
	<script src="/dist/bower-components/w3/w3data.js"></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
	<script src="/dist/js/banner/adminViewBaner.js"></script>
	
</head>
<body>

<!-- Header-->
<header><jsp:include page="/dist/partials/layout/header.jsp"></jsp:include></header>
<!--< End header -->

<!-- pop-up window message -->
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

<!-- End of pop-up window message -->

<!-- Banner search - Page content -->
<div class="banner-search-screen clearfix">
	<div class="add-new-area clearfix">
	   <div class="col-lg-2 col-md-3 pull-right"><button class type="button" value="Search" id="adminAddNewBanner">Add New Banner</button></div>
	   <div class="col-lg-2 col-md-3 pull-right"><button type="button" value="Search" id="adminAddNewAdvertiser">Add New Advertiser</button></div>
	</div>
	<div class="filtering-area clearfix">
	    <div class="col-lg-12 col-md-12 col-sm-12">
	        <div class="date-picker-area col-lg-5 col-md-5 col-sm-12">
	            <div class="col-lg-6 col-md-6 col-sm-12">
                    <div id="startDateInfor" class="date-input">Filter Commence Date:<input type="date" name="startDate" id="startDate" onclick="clearField('startDateInfor')"></div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12">
                    <div id="endtDateInfor" class="date-input">Filter End Date:<input type="date" name="endtDate" id="endtDate" onclick="clearField('endtDateInfor')"></div>
                </div>
            </div>
            <div class="col-lg-7 col-md-7 col-sm-12">
                <div class="btn-radio col-lg-9 col-md-9 col-sm-10">
                    <div class="radio-block">
                        <span id="">Active</span>
                        <input type="radio" name="bannerStatus" value="1" id ="statusActive" checked="checked" >
                    </div>

                    <div class="radio-block">
                        <span id="">Inactive</span>
                        <input type="radio" name="bannerStatus" value="0" id="statusInactive">
                    </div>

                    <div class="radio-block">
                        <span id="">Pending</span>
                        <input type="radio" name="bannerStatus" value="2" id ="statusPending"  >
                    </div>

                    <div class="radio-block">
                        <span id="">Expired</span>
                        <input type="radio" name="bannerStatus" value="3" id ="statusExpired"  >
                    </div>
                </div>
                <div class="btn-b-filter col-lg-3 col-md-3 col-sm-12">
                    <button type="button" value="Search" id="filterBanners">Filter The Banners</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- Banner Records holder --%>
<div class="tbl-bg clearfix">
    <div class="banner-record-holder">
       <div class="col-lg-12 col-md-12 col-sm-12">
           <!-- banner records will be displayed here -->
           <table id='bannerDisplaytbl' width ="100%" style="max-height:800px;  overflow: auto">
                <!-- <thead><tr><th width="40%"></th><th width="40%"></th></tr></thead> -->
                <tbody></tbody>

           </table>
       </div>
    </div>
</div>
</body>
</html>