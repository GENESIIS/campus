<!--20170421 DN c88-admin-manage-advertiser-add-new-advertiser-dn inital mock up of the issue has been developed  -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Manage Advertiser</title>

   <!-- Bootstrap & CSS Style -->
    <link href="/dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/dist/css/style.css" rel="stylesheet">
    <link href="../../css/image-slides.css" rel="stylesheet">
	
</head>

<body>

<!-- pop-up window message -->
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
    	
   <div align="center">
<!-- start of container div -->
    <div  >
    	<div>
        	<h1>Add Advertiser </h1>
    	</div>
    	<div>
    		<label>Advertiser Code</label> 
        	<input type="text">
        	<hr>
    	</div>      
      <div>
        <div id="courseProviderInfor" style ="color:#C70039;"></div><br>
    		<label>Course Provider</label>
    		<input id="courseProvider" name="page" value=""
							list="courseProviderList" class="text-field" type="text"
							placeholder="-- Select an Advertiser --" onclick="clearField('courseProviderInfor');">
			<datalist id="courseProviderList" name="courseProviderList"> </datalist>
			<input type="hidden" id="sCourseProviderCode" name="sCourseProviderCode" />
          		&nbsp; &nbsp;          
          <input type="button" value="Add New Course Provider">
         <hr>
      </div>
      <!--End CourseProvider list -->
      <div>
        <label>Advertiser Name</label>
        <input type="text" id="advertiserName">
         &nbsp; &nbsp;
        <label>Email </label>
        <input type="text" id="advertiserEmail">
      </div>     
      <!-- End Advertiser Name and Email Description -->
      &nbsp; &nbsp;
       <div >
       	 <label>Description</label>
         <textarea name="Text1" cols="40" rows="5" ></textarea>
         <hr>
      </div>
      <!-- End od Description div -->
      <div>
        <label>Land Phone Number</label>
        <input type="text" id="landCountryCode" maxlength="5" style="width:50px;">
        <input type="text" id="landAreaCode" maxlength="5" style="width:50px;">
        <input type="text" id="landPhoneNumber">
     &nbsp; &nbsp;
        <label>Mobile Phone Number</label>
        <input type="text" id="mobileCountryCode" maxlength="5" style="width:50px;">
        <input type="text" id="mobileAreaCode" maxlength="5" style="width:50px;">
        <input type="text" id="mobilePhoneNumber">
      </div><br><br>
        <!-- End of Land/Mobile Phone Number -->
       <div>
         <label> Adrees Line 1</label>
         <input type="text" id=""><br>
         <label> Adrees Line 2</label> 
         <input type="text" id=""> <br>
         <label> Adrees Line 3</label> 
         <input type="text" id=""> <br>
       </div>
        <br><br>
         <!-- End of Address lines -->
        <div>
	      <label>Country :</label><label id="countryError" style="color:#C70039;"></label>             
	      <input list="countryList" id="country" name="country" class="text-field" 
              type="text" placeholder="-- Select City --" onclick="clearField('countryError');" >
	      <datalist id="countryList" name="countryist">
	      </datalist>
		</div>
         <!-- End Country -->
       <div>
           <label>Town :</label><label id="townError" style="color:#C70039;"></label>
           <input id="town" name="town" list="townList" class="text-field" 
                   type="text" placeholder="-- Select Town --" onclick="clearField('townError')" >
           <datalist id="townList" name="townList">
           </datalist>
           <input type="hidden"  id="sTownCode" name="sTownCode"/>
       </div>
            <!-- End Town -->
            <hr>
            <div>
              <label>Active Status :</label>
              <div class="radio-block">
                 <span id="">Active</span>
                 <input type="radio" name="advertiserStatus" value="1" id ="statusActive" checked="checked" >
               </div>
               <div class="radio-block">
                 <span id="">Inactive</span>
                 <input type="radio" name="advertiserStatus" value="0" id="statusInactive">
               </div>
               <hr>
           </div>
             <!-- end of active status radio buttons -->
            <div>
              <input type="button" value="Create Advertiser" id="createAdvertiser">
              <input type="button" value="Clear Data" id= "clearData" >
              <input type="button" value="Edit Advertiser" id= "editAdvertiser" >
            </div>
             <!-- end of create clear edit buttons -->
    </div>
    <!-- end of container div -->
</div>

<!-- jQuery & Other js -->
	
    <script src="../../bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="../../bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
    
    <script src="../../js/main.js"></script>
    <script src="../../js/image-slides.js"></script>
	<script src="/dist/bower-components/w3/w3data.js"></script>
    <script src="/dist/js/institute/validation/validation.js"></script>
    <script src="/dist/js/jsonDataExchanger.js"></script>
	<script src="/dist/js/banner/adminMangeAdvertiser.js"></script>
</body>
</html>