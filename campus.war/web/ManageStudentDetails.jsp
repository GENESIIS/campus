<!-- 20161124 PN c26-add-student-details: INIT jsp page for manage user details input forms. -->
<!-- 20161126 PN c26-add-student-details: design error span and alert box. -->
<!-- 20161128 PN c26-add-student-details: design Professional Experience pop up form. -->
<!-- 20161129 PN c26-add-student-details: added onchange event for date range validations. -->
<!-- 20161203 PN c26-add-student-details: modified Personal Details model UI elements ids, onClick event. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <!-- Bootstrap & CSS Style-->
    <link href="dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="dist/css/style.css" rel="stylesheet">
    
    	<!--     Data Table CSS -->
    <link href="dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="dist/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
    
    <link href="dist/datatable/dataTables.checkboxes.css" rel="stylesheet" type="text/css">
    
    <!-- TagIt CSS-->
	<link href="dist/css/tagit.css" rel="stylesheet" type="text/css">
	
    <!-- W3-Include -->
    <script src="dist/bower-components/w3/w3data.js"></script>

<!-- jQuery & Other js -->
<!--<script src="dist/bower-components/jquery/jquery.min.js"></script>-->
<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="dist/js/main.js"></script>
<script src="dist/js/institute/validation/validation.js"></script>
<script src="dist/js/student/student-helper.js"></script>

<script src="dist/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>


<script src="dist/datatable/dataTables.checkboxes.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.checkboxes.min.js" type="text/javascript" charset="utf-8"></script>

<!-- TagIt JS-->
<!-- <link rel="stylesheet" href="bootstrap-2.3.2/css/bootstrap.min.css"> -->
<script src="dist/tagit/bootstrap-tagsinput.js"></script>
<link rel="stylesheet" href="dist/tagit/bootstrap-tagsinput.css">
<!-- <script src="bower_components/jquery/jquery.min.js"></script> -->
<!-- <script src="bower_components/google-code-prettify-lite/prettify.js"></script> -->
<!-- <script src="bootstrap-2.3.2/js/bootstrap.min.js"></script> -->
<script src="dist/tagit/typeahead.js"></script>






<style type="text/css">
.alert{
    display: none;
}
</style>
</head>
<body>

<!-- Button trigger School Education modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentPersonalDetailsModal">
  Personal Details
</button>

<!-- Button trigger Personal Details modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentSchoolEducationModal">
  School Details
</button>

<!-- Button trigger School Education modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentProfessionalDetailsModal">
  Professional Experience
</button>

<!-- Button trigger School Education modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentSkillDetailsModal">
  Student Skill
</button>


<!-- Personal Details-->
<div class="modal fade" id="studentPersonalDetailsModal" tabindex="-1" role="dialog" aria-labelledby="studentPersonalDetails" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
        <h4 class="modal-title" id="studentPersonalDetails">School Education</h4>
      </div>
      <div class="modal-body">
      <div id="studentPersonalStatus" name="studentPersonalStatus" class="alert alert-success"></div>
      		<h2>Personal Details</h2>
      		<div class="well">
      		First Name <input type="text" name="sFullName" id="sFullName" onkeypress="return isLetter(event)" onkeypress="" onclick="clearField('sFullNameError')">
			<span id="sFullNameError" name="sFullNameError" style="color:red"></span><br>
			
      		Middle Name <input type="text" name="sMiddleName" id="sMiddleName" onkeypress="return isLetter(event)" onclick="clearField('sMiddleNameError')">
			<span id="sMiddleNameError" name="sMiddleNameError" style="color:red"></span><br>
			
      		Last Name <input type="text" name="sLastName" id="sLastName" onkeypress="return isLetter(event)" onclick="clearField('sLastNameError')">
			<span id="sLastNameError" name="sLastNameError" style="color:red"></span><br>
      
      		Birth Date <input type="date" name="sBirthDate" id="sBirthDate" onclick="clearField('sBirthDateError')">
			<span id="sBirthDateError" name="sBirthDateError" style="color:red"></span><br>
			
			About <textarea type="text" name="sAboutMe" id="sAboutMe" onclick="clearField('sAboutMeError')"></textarea>
			<span id="sAboutMeError" name="sAboutMeError" style="color:red"></span><br>
			
			</div>
			<br>
			
			<h2>Contact Details</h2>
			<div class="well">
			
			Country
			<input type="text" id="sCountry" name="sCountry" list="sCountryList" placeholder="-- Select Town --"/>
			 	<datalist name="sCountryList" id="sCountryList">
			 	</datalist>
			<br/>
			
			Town
			 <input type="text" id="sTown" name="sTown" list="sTownList" placeholder="-- Select Town --" onclick="clearField('sTownError')"/>
			 	<datalist name="sTownList" id="sTownList">
			 	</datalist>
			<br/>
			<span id="sTownError" name="sTownError" style="color:red"></span><br>			
			
			Address<input type="text" name="sAddress" id="sAddress" onclick="clearField('sAddressError')">
			<span id="sAddressError" name="sAddressError" style="color:red"></span><br>
			
			Mobile Number
      		<div class="input-group">
      		<span class="input-group-addon" id="countryCodePrefix">+</span>
      		<input class ="phoneNum" type="text" name="sMobileNumber" id="sMobileNumber" onkeypress="" onclick="clearField('sMobileNumberError')">
			</div>
			<span id="sMobileNumberError" name="sMobileNumberError" style="color:red"></span><br>
			
			Home Number 
			<div class="input-group">
			<span class="input-group-addon" id="countryCodePrefix">+</span>
			<input class ="phoneNum" type="text" name="sHomeNumber" id="sHomeNumber" onkeypress="" onclick="clearField('sHomeNumberError')">
			<span id="sHomeNumberError" name="sHomeNumberError" style="color:red"></span><br>
			</div>
			
<!-- 			<div class="input-group"> -->
<!-- 			<span class="input-group-addon" id="countryCodePrefix">+</span> -->
<!--       		Other Number <input type="text" name="sOtherNumber" id="sOtherNumber" onkeypress="" onclick="clearField('sOtherNumberError')"> -->
<!-- 			<span id="sOtherNumberError" name="sOtherNumberError" style="color:red"></span><br> -->
<!--       		</div> -->
      		  		
			Email <input type="text" name="sEmail" id="sEmail" onclick="clearField('sEmailError')">
			<span id="sEmailError" name="sEmailError" style="color:red"></span><br>
      
      		Facebook URL <input type="text" name="sFacebookUrl" id="sFacebookUrl" onclick="clearField('sFacebookUrlError')">
			<span id="sFacebookUrlError" name="sFacebookUrlError" style="color:red"></span><br>
      
      		twitter URL <input type="text" name="stwitterUrl" id="stwitterUrl" onclick="clearField('stwitterUrlError')">
			<span id="stwitterUrlError" name="stwitterUrlError" style="color:red"></span><br>
			
			LinkedIn URL <input type="text" name="sLinkedInUrl" id="sLinkedInUrl" onclick="clearField('sLinkedInUrlError')">
			<span id="sLinkedInUrlError" name="sLinkedInUrlError" style="color:red"></span><br>
			
			Instergram URL <input type="text" name="sInstergramUrl" id="sInstergramUrl" onclick="clearField('sInstergramUrlError')">
			<span id="sInstergramUrlError" name="sInstergramUrlError" style="color:red"></span><br>
			
			mySpace <input type="text" name="smySpace" id="smySpace" onclick="clearField('smySpaceError')">
			<span id="smySpaceError" name="smySpaceError" style="color:red"></span><br>
			
			WhatsApp 
			<div class="input-group">
			<span class="input-group-addon" id="countryCodePrefix">+</span>
			<input class ="phoneNum" type="text" name="sWhatsApp" id="sWhatsApp" onclick="clearField('sWhatsAppError')">
			<span id="sWhatsAppError" name="sWhatsAppError" style="color:red"></span><br>
			</div>
			
			Viber 
			<div class="input-group">
			<span class="input-group-addon" id="countryCodePrefix">+</span>
			<input class ="phoneNum" type="text" name="sViber" id="sViber" onclick="clearField('sViberError')">
			<span id="sViberError" name="sViberError" style="color:red"></span><br>
			</div>
			
			</div>
			<br>			
	  </div>
      <div class="modal-footer">    
      	<button type="button" class="btn btn-secondary" onclick="">Clear</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="">Close</button>
        <button type="button" class="btn btn-primary" id="saveSse" name="saveSse" onclick="">Save changes</button>
      </div>
    </div>
  </div>
</div>


<!-- School Education -->
<div class="modal fade" id="studentSchoolEducationModal" tabindex="-1" role="dialog" aria-labelledby="studentSchoolEducation" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
        <h4 class="modal-title" id="studentSchoolEducation">School Education</h4>
      </div>
      <div class="modal-body">
      <div id="saveChangesStatus" name="saveChangesStatus" class="alert alert-success"></div>
			Grade  
			<select id="sseQualification" name = "sseQualification" onchange="clearField('sseQualificationError')">
				<option value="">--Select One--</option>
			</select> <span id="sseQualificationError" name="sseQualificationError" style="color:red"></span>
			<br/>
			Subject Stream
			<select id="sseStream" name = "sseStream" onchange="clearField('sseStreamError')">
				<option value="">--Select One--</option>
			</select> <span id="sseStreamError" name="sseStreamError" style="color:red"></span>
			<br/>
			Result
			<select id="sseResult" name = "sseResult" onchange="clearField('sseResultError')">
				<option value="">--Select One--</option>
				<option value="1">Pass</option>
				<option value="0">Fail</option>
			</select> <span id="sseResultError" name="sseResultError" style="color:red"></span>
			<br/>
			Index No <input type="text" name="sseIndexNo" id="sseIndexNo" onkeypress="return isNumber(event)" onclick="clearField('sseIndexNoError')">
			<span id="sseIndexNoError" name="sseIndexNoError" style="color:red"></span><br>
			
			School <input type="text" name="sseSchool" id="sseSchool" onclick="clearField('sseSchoolError')">
			<span id="sseSchoolError" name="sseSchoolError" style="color:red"></span><br>
			
			Achieved on <input type="date" name="" id="sseAchievedon" onclick="clearField('sseAchievedonError')">
			<span id="sseAchievedonError" name="sseAchievedonError" style="color:red"></span><br>
			
			Medium
			<select name="sseMedium" id="sseMedium" onchange="clearField('sseMediumError')">
				<option value="">--Select One--</option>
			</select><span name="sseMediumError" id="sseMediumError" style="color:red"></span>
			<br/>
			
			Country
			 <input type="text" id="sseCountry" name="sseCountry" list="sseCountryList" placeholder="-- Select Country --"/>
			 	<datalist name="sseCountryList" id="sseCountryList">
			 	</datalist>
			<br/>
			
			Description
			<textarea rows="5" cols="40" name="sseDescription" id="sseDescription" ></textarea>
			
	  </div>
      <div class="modal-footer">    
      	<button type="button" class="btn btn-secondary" onclick="clearSchoolEducationForm()">Clear</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="clearSchoolEducationForm()">Close</button>
        <button type="button" class="btn btn-primary" id="saveSse" name="saveSse" onclick="addEducationDetails()">Save changes</button>
      </div>
    </div>
  </div>
</div>


<!-- Professional Details Modal -->
<div class="modal fade" id="studentProfessionalDetailsModal" tabindex="-1" role="dialog" aria-labelledby="studentPersonalDetails" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
        <h4 class="modal-title" id="studentProfessionalDetails">Professional Experience</h4>
      </div>
      <div class="modal-body">			
			<div id="pesaveChangesStatus" name="pesaveChangesStatus" class="alert alert-success"></div>
			Industry of the Organization  
			<select id="industryoftheOrganization" name = "industryoftheOrganization" onchange="clearField('industryoftheOrganizationError')">
				<option value="">--Select One--</option>
			</select> <span id="industryoftheOrganizationError" name="industryoftheOrganizationError" style="color:red"></span>
			<br/>
			
			Organization <input type="text" name="organization" id="organization" onclick="clearField('organizationError')">
			<span id="organizationError" name="organizationError" style="color:red"></span><br>
			
			Job Category  
			<select id="jobCategory" name = "jobCategory" onchange="clearField('jobCategoryError')">
				<option value="">--Select One--</option>
			</select> <span id="jobCategoryError" name="jobCategoryError" style="color:red"></span>
			<br/>
			
			Designation <input type="text" name="designation" id="designation" onclick="clearField('designationError')">
			<span id="designationError" name="designationError" style="color:red"></span><br>
			
			Commenced on <input type="date" name="commencedOn" id="commencedOn" onclick="clearField('commencedOnError')">
			<span id="commencedOnError" name="commencedOnError" style="color:red"></span><br>
			
			Completion on <input type="date" name="completionOn" id="completionOn" onclick="clearField('completionOnError')" onchange ="checkDateRange('commencedOn','completionOn','commencedOnError','completionOnError')">
			<span id="completionOnError" name="completionOnError" style="color:red"></span><br>
			
			Description
			<textarea rows="5" cols="40" name="jobDescription" id="jobDescription" ></textarea>
			<br/><br/>
					<form id="frm-example" action="/path/to/your/script" method="POST">

						<table id="example"
							class="table table-striped table-bordered dt-responsive nowrap"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th><input name="select_all" value="1" type="checkbox"></th>
									<th><b>Industry</b></th>
									<th><b>Organization</b></th>
									<th><b>Category</b></th>
									<th><b>Designation.</b></th>
									<th><b>Duration</b></th>
									<th><b>Description</b></th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th></th>
									<th><b>Industry</b></th>
									<th><b>Organization</b></th>
									<th><b>Category</b></th>
									<th><b>Designation.</b></th>
									<th><b>Duration</b></th>
									<th><b>Description</b></th>
								</tr>
							</tfoot>			
						</table>
						<hr>
						<p>
							<button class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete Selected</button>
						</p>
						<pre id="example-console"></pre>
					</form>
					<br />
	  </div>					
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="clearProfessionalExpForm()">Clear</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="clearProfessionalExpForm()">Close</button>
        <button type="button" class="btn btn-primary" id="saveJe" name="saveJe" onclick="addProfessionalExpForm()">Save changes</button>
      </div>
   </div>
  </div>
</div>

<!-- Professional Details Modal -->
<div class="modal fade" id="studentSkillDetailsModal" tabindex="-1" role="dialog" aria-labelledby="studentSkillDetails" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
        <h4 class="modal-title" id="studentSkillDetails">Professional Experience</h4>
      </div>
      <div class="modal-body">

					<div>
						<input type="text" id="category" data-role="tagsinput" />
					</div>


	 </div>					
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="">Clear</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="">Close</button>
        <button type="button" class="btn btn-primary" id="saveJe" name="saveJe" onclick="">Save changes</button>
      </div>
   </div>
  </div>
</div>


<script>
$("#category").tagsinput({
	  typeahead: {
	    source: ['Amsterdam', 'Washington', 'Sydney', 'Beijing', 'Cairo']
	  },
	  freeInput: true
	});
	$("#category").on('itemAdded', function(event) {
	    setTimeout(function(){
	        $(">input[type=text]",".bootstrap-tagsinput").val("");
	    }, 1);
	});
</script>

</body>
</html>