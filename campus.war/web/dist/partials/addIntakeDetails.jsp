<!-- 20170504 DJ  c145-add-enhanced-programme-MP-dj  INT-add addIntakeDetails.jsp a temporary jsp till UI implementation. -->
<!-- 20170504 DJ  c145-add-enhanced-programme-MP-dj  add hard coded table with styles in addIntakeDetails.jsp a temporary jsp till UI implementation. -->
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
   <h1>Sample Intake page</h1>
</header>
  
<nav> 
</nav>

<article>

<table>
  <tr>
    <th>Intake Name</th>
    <th>Description</th>
    <th>Opening Date</th>
    <th>Closing Date</th>
    <th>Commencement Date</th>
    <th>Programme</th>
    <th>Fee</th>
    <th>Is Active</th>
  </tr>
  <tr>
     <td>Intake Name 1</td>
    <td>Intake Description 1</td>
    <td>System Date</td>
    <td>System Date</td>
    <td>System Date</td>
    <td>1</td>
    <td>2000</td>
    <td>Active</td>
  </tr>
  <tr>
    <td>Intake Name 2</td>
    <td>Intake Description 2</td>
    <td>System Date</td>
    <td>System Date</td>
    <td>System Date</td>
    <td>1</td>
    <td>2000</td>
    <td>Active</td>
  </tr>
  <tr>
    <td>Intake Name 3</td>
    <td>Intake Description 3</td>
    <td>System Date</td>
    <td>System Date</td>
    <td>System Date</td>
    <td>1</td>
    <td>2000</td>
    <td>Active</td>
  </tr>
  </table>

	<div>	
	<input align="middle" type="button" class="btn btn-register intake-save pull-right" id="addIntake" value="Add Intake"/>	
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