<!-- 20161030 DJ  c6-list-available-institutes-on-the-view view more institutes -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campus.lk -Institutes</title>
<style>
div.container {
	width: 100%;
	border: 1px solid gray;
}

header,footer {
	padding: 1em;
	color: white;
	background-color: #59DFB9;
	clear: left;
	text-align: center;
}

article {
	margin-left: 170px;
	border-left: 1px solid gray;
	padding: 1em;
	overflow: hidden;
}

section {
	margin-left: 170px;
	border-left: 1px solid gray;
	padding: 1em;
	overflow: hidden;
}

</style>
<!-- Bootstrap & CSS Style-->
<link href="../bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<!-- W3-Include -->
<script src="http://www.w3schools.com/lib/w3data.js"></script>
<script type="text/javascript"
	src="../bower-components/jquery/jquery.min.js"></script>
<!-- <script src="../js/filterSearch/ui-populate-helper.js"></script> -->

<!--     Data Table CSS -->
<!-- <link href="../datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css"> -->
<!-- jQuery & Other js -->
<script src="../bower-components/bootstrap/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.3.js" type="text/javascript" charset="utf-8"></script>
<script src="../datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/main.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	alert(" test*****************");
	$.ajax({url : '../../PublicController',
		data : {
			CCO : 'LIST_ALL_COURSE_PROVIDERS'
		},
		dataType : "json",
		success : function(response) {			
			getAjaxData(response);
		}
	});
});

	function getAjaxData(response) {
		
	/* 	var providers = $("#providers");
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(providers);
		}); */
		
		 var t = $('#example').DataTable();
		
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			t.row.add(y).draw( false );
			
		});
		
		
		
		var providers = $("#providers");
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(providers);
		});
		
		var categories = $("#categoryName");
		$.each(response.categoryList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(categories);
		});

		var courseProviderType = $("#courseProviderType");
		$.each(response.cpTypeList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(courseProviderType);
		});

		var majorList = $("#majorList");
		$.each(response.majorList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(majorList);
		});

		var districtList = $("#districtList");
		$.each(response.districtList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			var z = data[2].toString();
			$('<option>').val(y).text(z).appendTo(districtList);
		});
		
		var levelList = $("#levelList");
		$.each(response.levelList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(levelList);
		});

	}
</script>
</head>
<body>
	<h2>More Course Providers View</h2>	
	<div class="container">

		<header>
			<div class="drop-holder">
				<input type="text" name="districtlist" id="districtlist"
					list="districtList" placeholder="-- Select District --" />
				<datalist id="districtList">
				</datalist>
			</div>
		</header>

		<section>
			<h4>All Course Providers</h4>
			<table>
				<tr>
					<td><label>All <span id="courseCount" name="courseCount"></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td><label class="flip">Category <span id="categoryName" name="categoryName"></span></label>
					 <a href="javascript:"><input type="checkbox"></a></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td><label class="flip">Course Provider Type <span id="courseProviderType"
							name="courseProviderType"></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>

				</tr>
				
				<tr>
					<td><label class="flip">Major <span id="majorList"
							name="majorList"></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>

				</tr>
				<tr>
					<td><label class="flip">Levels <span id="levelList"
							name="levelList"></span></label> <a href="javascript:"><input
							type="checkbox"></a></td>

				</tr>
				<tr>
					<td>
						<!-- <form action="PublicController" method="post">
							<input type="text" name="category">
							<button type="submit" name="CCO"
								value="LIST_ALL_COURSE_PROVIDERS">View More</button>
						</form> -->
						<form id="searchbox" action="">
							<input id="submit" type="submit" value="Search">
						</form>
					</td>
				</tr>
			</table>
		</section>

		<article id="example" >	
		
			<table	style="border: 2px; border-color: black; border-style: solid; width: 100%">
				<%-- <tr>
					<c:forEach var="provider" items="${result.collection}">
						<td
							style="border: 2px; border-color: blue; border-style: solid; width: 10%">
							<c:forEach var="pvAttribute" items="${provider}"
								varStatus="count">
								<c:choose>
									<c:when test="${count.index == 0}">
									</c:when>
									<c:when test="${count.index == 1}">
										<c:set var="prefix" value="${pvAttribute}" />
										<c:out value="${pvAttribute}" />
									</c:when>
									<c:otherwise>
										<c:set var="slash" value="/" />
										<td><c:out value="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" /></td>
										<img height="42" width="42"
											src="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</td>
						<td></td>
						<br>
					</c:forEach>
				</tr> --%>
				<!-- <tr>
				<td>
				<label>Listed Providers<span id="providers" name="providers"></span></label>
				</td>
				
				</tr> -->
			</table>
		</article>
		<footer>Copyright © Genesiis.com</footer>
	</div>

	<%-- <table
		style="border: 2px; border-color: black; border-style: solid; width: 100%">		
		<tr>
			<c:forEach var="provider" items="${result.collection}">
				<td
					style="border: 2px; border-color: blue; border-style: solid; width: 10%">
					<c:forEach var="pvAttribute" items="${provider}" varStatus="count">
						<c:choose>
							<c:when test="${count.index == 0}">
							</c:when>
							<c:when test="${count.index == 1}">
								<c:set var="prefix" value="${pvAttribute}" />
								<c:out value="${pvAttribute}" />
							</c:when>
							<c:otherwise>
								<c:set var="slash" value="/" />
								<td><c:out value="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" /></td>
								<img height="42" width="42"
									src="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}" />
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
				<td></td>
				<br>
			</c:forEach>
		</tr>
	</table> --%>
</body>
</html>