<!-- 20161127 DJ  c51-report-courses-by-course-provider-MP-dj  search view for courses by course providers -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Campus.lk</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">



<script type="text/javascript">

	function loadReportList(event) {
		alert(event);

		var cpCode = $("#cpCode").val();
		$.ajax({
			url : '../../PublicController',
			data : {
				CCO : 'REPORT_COURSES_BY_COURSE_PROVIDER',
				providerCode : cpCode
			},
			dataType : "json",
			success : function(response) {
				alert("success");
				loadTable();

			},
			error : function() {
				alert("error");
			}
		});


	function loadTable() {
			$.each(response.resultSet, function(index, value) {
				var res = value.toString();
				var data = res.split(",");
			
			var r = new Array(), j = -1;
			for (var key = 0, size = data.length; key < size; key++) {
				r[++j] = '<tr><td>';
				r[++j] = data[key][0];
				r[++j] = '</td><td class="whatever1">';
				r[++j] = data[key][1];
				r[++j] = '</td><td class="whatever2">';
				r[++j] = data[key][2];
				r[++j] = '</td></tr>';
			}
			});
			$('#dataTable').html(r.join(''));

		}

	}
</script>

</head>
<body>
<!-- Header-->
	<jsp:include page="/dist/partials/layout/header.jsp" />	
	<!-- End Header -->

	<!-- Main Container - Contact-US -->
	<div class="top-providers-screen clearfix">
		<div class="inner-header">
			<div class="main-topic">
				<h1>Find your future with us</h1>
			</div>
		</div>
		<div class="page-topic">
			<h2 class="page-topic-t1">| Courses by Course Providers</h2>
		</div>

		<!-- image header -->
		<div class="inner-image-header">
			<div class="bg-image"></div>
		</div>
		<!-- END inner-image-header -->

		<!-- Contact Form -->
		<div class="provider-list-holder clearfix">
			<div class="top-list clearfix">
				<div class="search clearfix">
					<!-- <form action="PublicController" method="post"> -->
						<select name="providers">
							<option value="1">Course provider1</option>
							<option value="2">Course provider2</option>
							<option value="3">Course provider3</option>
						</select> <input type="date" name="search">
						 <select name='role'>
							<option value="${selected}" selected>${selected}</option>
							<c:forEach items="${result.collection}" var="role">
								<c:if test="${role != selected}">
									<option value="${role}">${role}</option>
								</c:if>
							</c:forEach>
						</select>
						<button type="submit" name="CCO" value="REPORT_COURSES_BY_COURSE_PROVIDER">Search</button>
						<button type="submit" onclick="loadReportList(this)">Search List</button>
					<!-- </form> -->
				</div>			
				<div class="search-result-view clearfix">
					<div class="table-view">
					<table id="#dataTable"></table>
					</div>
				</div>
				<div class="drop-holder">
						<input type="text" name="providerlist" id="providerlist"
							list="providerName" placeholder="-- Select District --" />
						<datalist id="providerName">
						</datalist>
					</div>
				<!-- <div class="district-col col-md-4 col-lg-4 pull-right">
					<div class="drop-holder">
						<input type="text" name="districtlist" id="districtlist"
							list="districtName" placeholder="-- Select District --" />
						<datalist id="districtName">
						</datalist>
					</div>
				</div> -->
				<!-- End find provider drop down -->			
				
			</div>
			<!-- End top-rated-list -->
		</div>
		<!-- END provider-list-holder -->
	</div>
	<!-- End Container - Top Providers list -->
<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!--End  Footer -->
	
</body>
</html>