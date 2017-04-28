<!-- 20170428 DJ  c145-add-enhanced-programme-MP-dj  INT-add addModuleDetails.jsp a temporary jsp till UI implementation. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"	rel="stylesheet">
<!-- FontAwsome Styles-->
<link href="/dist/bower-components/faw/css/font-awesome.min.css" rel="stylesheet">

<link href="/dist/css/style.css" rel="stylesheet">
</head>
<body>
<h1>********************Module **************************</h1>


<div class="form-wrapper">
	<div>
		<div>
		<label for="module-semester">Semester Name/Term</label>
			<select>
			  <option value="1">Semester 1</option>
			  <option value="2">Semester 2</option>
			  <option value="3">Semester 3</option>
			  <option value="4">Semester 4</option>
			</select>
		</div>
		<div>
		<label for="module-tutor">Tutor Name</label>
			<select>
			  <option value="1">Tutor 1</option>
			  <option value="2">Tutor 2</option>
			  <option value="3">Tutor 3</option>
			  <option value="4">Tutor 4</option>
			</select>
		</div>
		<div>		
			<label for="module-name">Module Name</label>
			<input type="text" name="moduleName" id="moduleName" size="25px" />
		</div>
		<div>
			<label for="module-code">Module Code</label>
			<input type="text" name="moduleCode" id="moduleCode" size="25px" />
		</div>
		<div>
			<label for="module-credit">Module Credit</label>
			<input type="text" name="moduleCredit" id="moduleCredit" size="25px" />
		</div>
		<div>
		    <label for="module-status">Module Status</label>
		    <label class=""><input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio1" value="ACTIVE"> Active</label>
			<label class=""><input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="INACTIVE"> Inactive</label>
		</div>
		<div>
		    <label for="module-status">Compulsory Status</label>
		    <label class=""><input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio1" value="ACTIVE"> Compulsory</label>
			<label class=""><input class="form-check-input" type="radio" name="courseStatus" id="inlineRadio2" value="INACTIVE"> Optional</label>		
		</div>
		<div>
		<label for="module-status">Module Description</label>
		<textarea name="moduleDescription" class="form-control" id="module-description" rows="9"></textarea>
		</div>
	</div>
	<div>
	<input type="button" class="btn btn-register module-save pull-right" id="addModule" value="Add"/>
	</div>
</div>
<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
	<script src="/dist/js/admin/ui-addProgramme-helper.js"></script>
</body>
</html>