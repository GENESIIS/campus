<!--20170421 DN c88-admin-manage-advertiser-add-new-advertiser-dn inital mock up of the issue has been developed  -->
<!--20170502 DN c88-admin-manage-advertiser-add-new-advertiser-dn. The page iss added the xxxInfor elements -->

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
    		<label id="advertiserLabel">Advertiser Code</label> 
        	<input type="text" id='advertierCode'>
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
          		<label>Add New Course Provider</label>  
         <hr>
      </div>
      <!--End CourseProvider list -->
      <div>
      <div id="advertiserNameInfor" style ="color:#C70039;"></div><br>
        <label>Advertiser Name</label>
        <input type="text" id="advertiserName"  onclick="clearField('advertiserNameInfor');">
         &nbsp; &nbsp;
         <div id="emailInfor" style ="color:#C70039;"></div><br>
        <label>Email </label>
        <input type="text" id="advertiserEmail"  onclick="clearField('emailInfor');">
      </div>     
      <!-- End Advertiser Name and Email Description -->
      &nbsp; &nbsp;
       <div >
       <div id="descriptionInfor" style ="color:#C70039;"></div><br>
       	 <label>Description</label>
         <textarea id="courseProviderDescription" name="Text1" cols="40" rows="5" onclick="clearField('descriptionInfor');"></textarea>
         <hr>
      </div>
      <!-- End od Description div -->
      <div>
      	<div id="landPhoneInfor" style ="color:#C70039;"></div><br>
        <label>Land Phone Number</label>
        <input type="text" id="landCountryCode" maxlength="5" style="width:50px;" onclick="clearField('landPhoneInfor');">
        <input type="text" id="landAreaCode" maxlength="5" style="width:50px;" onclick="clearField('landPhoneInfor');">
        <input type="text" id="landPhoneNumber" onclick="clearField('landPhoneInfor');">
     &nbsp; &nbsp;
     	<div id="mobilePhoneInfor" style ="color:#C70039;"></div><br>
        <label>Mobile Phone Number</label>
        <input type="text" id="mobileCountryCode" maxlength="5" style="width:50px;" onclick="clearField('mobilePhoneInfor');">
        <input type="text" id="mobileAreaCode" maxlength="5" style="width:50px;" onclick="clearField('mobilePhoneInfor');">
        <input type="text" id="mobilePhoneNumber" onclick="clearField('mobilePhoneInfor');">
      </div><br><br>
        <!-- End of Land/Mobile Phone Number -->
       <div>
       <div id="addressInfor" style ="color:#C70039;"></div><br>
         <label> Address Line 1</label>
         <input type="text" id="address1" onclick="clearField('addressInfor');"><br>
         <label> Address Line 2</label> 
         <input type="text" id="address2" onclick="clearField('addressInfor');"> <br>
         <label> Address Line 3</label> 
         <input type="text" id="address3" onclick="clearField('addressInfor');" > <br>
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