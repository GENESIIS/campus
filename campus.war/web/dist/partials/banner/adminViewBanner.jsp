<!-- 20170308 DN c81-admin-manage-banner-add-and-view-banner-dn mock up design completed -->
<!-- 20170309 DN c81-admin-manage-banner-add-and-view-banner-dn add radio buttons to model
				  PENDING,EXPIRED banner status.



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

<!-- popup window message -->
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


<div align="center">			 
    <div id="startDateInfor" style ="color:#C70039;"></div>
		Start date* &nbsp; <input type="date" name="startDate" id="startDate" onclick="clearField('startDateInfor')"> 
	    <div id="endtDateInfor" style ="color:#C70039;"></div>
		End date * &nbsp; <input type="date" name="endtDate" id="endtDate" onclick="clearField('endtDateInfor')">
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
   <input type="button" value="Search">
   <!-- banner records will be displayed here -->
   <table width="100%" border=1>
	   <tr>
	     <td>
	       From:2017-03-08 |To:2017-04-07
	       <br><br>
	       <button class="rowEditButon"> Edit</button>
	     </td>
	     <td>
	       <img id="bnnerImage"  src="/dist/i/higher-education/slider-bg-1.jpg"   alt="banner-Image" style="width:400px;hight:50px">
	       
	     </td>
	   </tr>
   </table>

</body>
</html>