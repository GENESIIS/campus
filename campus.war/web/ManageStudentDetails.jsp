<!-- 20161124 PN c26-add-student-details: INIT jsp page for manage user details input forms. -->
<!-- 20161126 PN c26-add-student-details: design error span and alert box. -->

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

    <!-- W3-Include -->
    <script src="dist/bower-components/w3/w3data.js"></script>

<!-- jQuery & Other js -->
<!--<script src="dist/bower-components/jquery/jquery.min.js"></script>-->
<script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap-3.3.7.min.js"></script>
<script src="dist/js/main.js"></script>
<script src="dist/js/institute/validation/validation.js"></script>
<script src="dist/js/student/student-helper.js"></script>
<style type="text/css">
.alert{
    display: none;
}
</style>
</head>
<body>

<!-- Button trigger Personal Details modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentSchoolEducationModal">
  School Details
</button>

<!-- Button trigger School Education modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentPersonalDetailsModal">
  Personal Education
</button>


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


<!-- Personal Details Modal -->
<div class="modal fade" id="studentPersonalDetailsModal" tabindex="-1" role="dialog" aria-labelledby="studentPersonalDetails" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="studentPersonalDetails">Personal Details</h4>
      </div>
      <div class="modal-body">
			...WIP..
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="saveSse" name="saveSse" onclick="addEducationDetails()">Save changes</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>