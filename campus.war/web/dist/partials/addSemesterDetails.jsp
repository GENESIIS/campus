<!-- 20170427 DJ  c145-add-enhanced-programme-MP-dj  INT-add semester detail temporary jsp. -->
<!-- 20170504 DJ  c145-add-enhanced-programme-MP-dj  add hard coded table with styles in semester detail temporary jsp. -->
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
<style>
div.container {
    width: 100%;
    border: 1px solid gray;
}

header, footer {
    padding: 1em;
    color: white;
    background-color: blue;
    clear: left;
    text-align: center;
}

nav {
    float: left;
    max-width: 160px;
    margin: 0;
    padding: 1em;
}

nav ul {
    list-style-type: none;
    padding: 0;
}
   
nav ul a {
    text-decoration: none;
}

article {
    margin-left: 170px;
    border-left: 1px solid gray;
    padding: 1em;
    overflow: hidden;
}
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>

<div class="container">

<header>
   <h1>Sample Semester page</h1>
</header>
  
<nav> 
</nav>

<article>
 <div>
		<div>
		<label for="module-semester">Semester Name/Term</label>
		<input type="text" name="semesterName" id="semesterName" size="25px" /></div>
		<div>
		<label for="module-semester">Year Number</label>
		<select>
		  <option value="1">2015</option>
		  <option value="2">2017</option>
		  <option value="3">2018</option>
		  <option value="4">2019</option>
		</select>
		</div>
		<div>
		<label for="module-semester">Semester Number</label>
		<select>
		  <option value="1">01</option>
		  <option value="2">02</option>
		  <option value="3">03</option>
		  <option value="4">04</option>
		</select>
		</div>		
		<div>
		<label for="module-semester">Semester Description</label>
		<textarea name="semesterDescription" class="form-control" id="semester-description" rows="9"></textarea>
		</div>
	</div>
<table>
  <tr>
    <th>Semester Name</th>
    <th>Description</th>
    <th>Year Number</th>
    <th>Semester Number</th>
    <th>Is Active</th>  
    <th>Programme</th>    
  </tr>
  <tr>
     <td> Name 1</td>
    <td>Semester Description 1</td>
    <td>1</td>
    <td>1</td>   
    <td>Active</td>
    <td>2</td>
  </tr>
  <tr>
   <td> Name 2</td>
    <td>Semester Description 2</td>
    <td>2</td>
    <td>2</td>   
    <td>Active</td>
    <td>2</td>
  </tr>
  <tr>
    <td> Name 3</td>
    <td>Semester Description 3</td>
    <td>3</td>
    <td>3</td>   
    <td>Active</td>
    <td>2</td>
  </tr>
 </table>
<div>
	<input type="button" class="btn btn-register semester-save pull-right" id="addSemester" value="Add Semester"/>
</div>
</article>
<footer>Copyright &copy; Campus.com</footer>
</div>
<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
	<script src="/dist/js/admin/ui-addProgramme-helper.js"></script>
</body>
</html>