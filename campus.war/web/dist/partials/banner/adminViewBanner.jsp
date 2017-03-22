<!-- 20170308 DN c81-admin-manage-banner-add-and-view-banner-dn mock up design completed -->
<!-- 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn add radio buttons to model
				  PENDING,EXPIRED banner status.

     20170309 DN c81-admin-manage-banner-add-and-view-banner-dn removed the style script and tbody
                  tag of the table from the jsp file. Add the Row #  <th> element
     20170314 DN c81-admin-manage-banner-add-and-view-banner-dn add id to the button 'Filter the Banner Button
	 20170315 DN c81-admin-manage-banner-add-and-view-banner-dn Admin Add New Banner and Admin add New Advertiser buttons are added.
	 20170322 DN c83-admin-manage-banner-update-banner-info-dn add extra div to display banner image when the page is reached to edit
	 			 banner record.
 -->


<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin View Banner</title>


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
	<script src="/dist/js/banner/adminViewBaner.js"></script>
	
</head>
<body>

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
<div align="center">			 
	<div>
	   <button type="button" value="Search" id="adminAddNewBanner">Add New Banner</button> &nbsp; &nbsp; &nbsp;
	   <button type="button" value="Search" id="adminAddNewAdvertiser">Add New Advertiser</button>
	</div>
    <div id="startDateInfor" style ="color:#C70039;"></div>
		Filter Commence Date* &nbsp; <input type="date" name="startDate" id="startDate" onclick="clearField('startDateInfor')"> 
	    <div id="endtDateInfor" style ="color:#C70039;"></div>
		Filter End Date * &nbsp; <input type="date" name="endtDate" id="endtDate" onclick="clearField('endtDateInfor')">
	</div>
	<br><br>
	&nbsp; &nbsp; &nbsp;
   <span id="">List <b>Active</b> Banners</span>
   <input type="radio" name="bannerStatus" value="1" id ="statusActive" checked="checked" > 
   &nbsp; &nbsp; &nbsp;<span id="">List <b>Inactive</b> Banners</span>
   <input type="radio" name="bannerStatus" value="2" id ="statusInactive"  > 
    &nbsp; &nbsp; &nbsp;<span id="">List <b>Pending</b> Banners</span>
   <input type="radio" name="bannerStatus" value="3" id ="statusPending"  > 
    &nbsp; &nbsp; &nbsp;<span id="">List <b>Expired</b> Banners</span>
   <input type="radio" name="bannerStatus" value="4" id ="statusExpired"  > 
   <br><br>
   <button type="button" value="Search" id="filterBanners">Filter The Banners</button>
   
   <br><br>
   <!-- banner records will be displayed here -->
   <table id='bannerDisplaytbl' width ="100%" style="max-height:800px;  overflow: auto">
	   	<thead>
	            <tr>
	            	<th align="center" width="20%">Row # </th>
	                <th width="40%%">Banner Information</th>
	                <th width="40%%">Banner Image</th>
	            </tr>
	     </thead>
	     <tbody></tbody>

   </table>

</body>
</html>