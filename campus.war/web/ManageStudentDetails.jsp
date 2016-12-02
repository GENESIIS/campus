<!-- 20161124 PN c26-add-student-details: INIT jsp page for manage user details input forms. -->
<!-- 20161126 PN c26-add-student-details: design error span and alert box. -->
<!-- 20161128 PN c26-add-student-details: design Professional Experience pop up form. -->
<!-- 20161129 PN c26-add-student-details: added onchange event for date range validations. -->

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

<!-- School Education -->
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
      		First Name <input type="text" name="" id="" onkeypress="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
      		Middle Name <input type="text" name="" id="" onkeypress="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
      		Last Name <input type="text" name="" id="" onkeypress="" onclick="">
			<span id="" name="" style="color:red"></span><br>
      
      		Birth Date <input type="date" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			About <textarea type="text" name="" id="" onclick=""></textarea>
			<span id="" name="" style="color:red"></span><br>
			
			</div>
			<br>
			
			<h2>Contact Details</h2>
			<div class="well">
      		
      		Mobile Number<input type="text" name="" id="" onkeypress="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			Home Number <input type="text" name="" id="" onkeypress="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
      		Other Number <input type="text" name="" id="" onkeypress="" onclick="">
			<span id="" name="" style="color:red"></span><br>
      
      		Address<input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			Town
			 <input type="text" id="" name="" list="" placeholder="-- Select Town --"/>
			 	<datalist name="" id="">
			 	</datalist>
			<br/>
      
      		Facebook URL <input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
      
      		twitter URL <input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			LinkedIn URL <input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			Instergram URL <input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			mySpace <input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			WhatsApp <input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
			Viber <input type="text" name="" id="" onclick="">
			<span id="" name="" style="color:red"></span><br>
			
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

</body>
</html>