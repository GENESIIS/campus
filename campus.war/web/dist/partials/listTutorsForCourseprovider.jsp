<!-- 20170420 c159-courseprovider-accept-tutor-request-cw - created addTutorEmployment jsp file-->
<!-- 20170421 c159-courseprovider-accept-tutor-request-cw - testing coding to get the courseProviderCode-->
<!-- 20170424 c159-courseprovider-accept-tutor-request-cw - remove un wanted scriptlets & add cpCode field -->
<!-- 20170425 c159-courseprovider-accept-tutor-request-cw - Add Reject/Remove to the table -->
<!-- 20170425 c159-courseprovider-accept-tutor-request-cw - remove Remove button & Add Save button -->
<!-- 20170427 c159-courseprovider-accept-tutor-request-cw - Add datatable source paths -->
<!-- 20170427 c159-courseprovider-accept-tutor-request-cw - Remove Reject/Remove, Approve from the table & add Change Status & changed 
				CCO value of the button from APPROVE_REMOVE_EMPLOYMENT TO SAVE_EMPLOYMENT_STATUS_CP -->
<!-- 20170427 c159-courseprovider-accept-tutor-request-cw - Removed tutorCodeTable hidden variable -->
<!-- 20170428 c159-courseprovider-accept-tutor-request-cw - Add courseprovidercode hidden variable -->




<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Tutor Employment details</title>

	<!--     Data Table CSS -->
<link href="/dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/dist/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.13/js/jquery.dataTables.js"></script>

</head>
<body>
	<div class="container" align="center">
		<c:forEach var="cpCodeList" items="${result.collection}">		
			<c:set var="cpcode" value="${cpCodeList[0]}"/>		
		</c:forEach>
				
		<input type="hidden" name="courseprovidercode" id="courseprovidercode" value="${cpcode}"/>
	
		<header align="center">
			<h1>View Tutor List</h1>
		</header>

		<div align="center">
			<form action="/TutorController" method="POST">
				<h1>View Tutor Details</h1>
				<tr>
					<td>
						<h2 id="tablemessage" style="color: red">${tablemessage}</h2>
					</td>
				</tr>
				
				<input type="hidden" name="cpCode" id="cpCode" value="${param.courseProviderCode}"/>
								
				<div>
					<table id="Tutors" width="80%">
						<tr>
							<th></th>
							<th>Name</th>
							<th>Gender</th>
							<th>Email</th>
							<th>Land Number</th>
							<th>Mobile Number</th>
							<th>Status</th>
							<th>Change Status</th>
							<th></th>
						</tr>
					</table>
					<div style="text-align:right">  
    					<button type="submit" name="CCO" id="CCO" value="SAVE_EMPLOYMENT_STATUS_CP" class="pure-button pure-button-primary" align = right>Save</button>
    					<input type="hidden" name="maxSequence" id="maxSequence"/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/courseprovider/tutorList.js"></script>
	<script src="/dist/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/dist/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>