<!-- 20161124 PN c26-add-student-details: INIT jsp page for manage user details input forms. -->

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
<script src="dist/js/student/student-details-handler.js"></script>
</head>
<body>

<!-- Button trigger Personal Details modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentSchoolEducationModal">
  Personal Details
</button>

<!-- Button trigger School Education modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#studentPersonalDetailsModal">
  School Education
</button>


<!-- School Education -->
<div class="modal fade" id="studentSchoolEducationModal" tabindex="-1" role="dialog" aria-labelledby="studentSchoolEducation" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="studentSchoolEducation">School Education</h4>
      </div>
      <div class="modal-body">
			Grade 
			<select id="sseQualification" name = "sseQualification">
				<option value="-1">--Select One--</option>
			</select>
			<br/>
			Subject Stream
			<select id="sseStream" name = "sseStream">
				<option value="">--Select One--</option>
			</select>
			<br/>
			Result
			<select id="sseResult" name = "sseResult">
				<option value="">--Select One--</option>
				<option value="1">Pass</option>
				<option value="0">Fail</option>
			</select>
			<br/>
			Index No <input type="text" name="sseIndexNo" id="sseIndexNo" ><br>
			
			School <input type="text" name="sseSchool" id="sseSchool"><br>
			
			Achieved on <input type="text" name="sseAchievedon" id="sseAchievedon"><br>
			
			Medium
			<select name="sseMedium" id="sseMedium">
				<option value="">--Select One--</option>
			</select>
			<br/>
			
			Country
			 <input type="text" id="sseCountry" name="sseCountry" list="sseCountryList" placeholder="-- Select Country --" />
			 	<datalist name="sseCountryList" id="sseCountryList">
			 	</datalist> 
			<br/>
			
			Description
			<textarea rows="5" cols="10" name="sseDescription" id="sseDescription"></textarea>
			
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
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
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>