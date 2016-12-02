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

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : '../../PublicController',
			data : {
				CCO : 'SEARCH_VIEW_COURSES_BY_COURSE_PROVIDER'
			},
			dataType : "json",
			success : function(response) {
				getAjaxProviderData(response);
			},
			error : function() {
				alert("error");
			}
		});
	});

	function getAjaxProviderData(response) {

		var providerName = $("#providerName");
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();

			$('<option>').val(x).text(y).appendTo(providerName);
		});
	}

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
		<div>
			<div>
				<div class="container">
					<form action="PublicController" method="POST">
						<fieldset>
							<legend align="left">Search criteria </legend>
							<div>
								Course Provider :<input type="text" name="providerlist"
									id="providerlist" list="providerName"
									placeholder="-- Select District --" />
								<datalist id="providerName">
								</datalist>
							</div>
							<div>
								Start Date : <input type="date" id="startdate" name="search">
							</div>
							<div>
								End Date : <input type="date" id="dendate" name="search">
							</div>
							<div>
								<button type="submit">Clear</button>
								<button type="submit" onclick="loadReportList(this)">Search
									List</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div ></div>
			<div>
				<div class="container">
					<h2>Result set</h2>
					<table class="table-responsive">
						<thead>
							<tr>
							    <th>#</th>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>Email</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- End Container - Top Providers list -->

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!--End  Footer -->

</body>
</html>