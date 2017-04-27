<!-- 20170427 DJ  c145-add-enhanced-programme-MP-dj  INT-add semester detail temporary jsp. -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>********************SEMESTER **************************</h1>


<div class="form-wrapper">
	<div>
		<div><input type="text" name="semesterName" id="semesterName" size="25px" /></div>
		<div>
		<select>
		  <option value="1">2015</option>
		  <option value="2">2017</option>
		  <option value="3">2018</option>
		  <option value="4">2019</option>
		</select>
		</div>
		<div>
		<select>
		  <option value="1">01</option>
		  <option value="2">02</option>
		  <option value="3">03</option>
		  <option value="4">04</option>
		</select>
		</div>
		<div>
		<select>
		  <option value="1">Active</option>
		  <option value="2">Inactive</option>		  
		</select>
		</div>
		<div>
		<textarea name="semesterDescription" class="form-control" id="semester-description" rows="9"></textarea>
		</div>
	</div>
	<div>
	<input type="button" class="btn btn-register pull-right" id="addProgramme" value="Add"/>
	</div>
</div>
</body>
</html>