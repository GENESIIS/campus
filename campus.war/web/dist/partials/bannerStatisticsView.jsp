<!-- 20161206 DJ c52-report-banner-statistics-MP-dj  search view for banner statistics-->

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

<!--     Data Table CSS -->
<link href="/dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/dist/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
<!-- Bootstrap js -->
<script src="/dist/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/dist/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/dist/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/dist/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>

<script type="text/javascript">
	$(document).ready(function() {		
		$.ajax({
			url : '../../ReportController',
			data : {
				CCO : 'SEARCH_VIEW_BANNER_STATISTICS'
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
		 $("#pageName").get(0).options.length = 0;
		var pageName = $("#pageName");
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();

			$('<option>').val(x).text(y).appendTo(pageName);
		});	
		
		$('#pagelist').bind('change', function(event) {
			alert(" click ");
			var pageCode=0;
			
			$.ajax({
				url : '../../ReportController',
				data : {
					CCO : 'LIST_PAGEWISE_PAGESLOTS',
					pageCode:pageCode
				},
				dataType : "json",
				success : function(response) {
					getPageWisePageSlots(response);
				},
				error : function() {
					alert("error");
				}
			});
			
		});
	}	
	
	function getPageWisePageSlots(response) { 
		alert(" getPageWisePageSlots ");
		var pagecode=0;
		
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
			<h2 class="page-topic-t1">| Banner Statistics</h2>
		</div>

		<!-- image header -->
		<div class="inner-image-header">
			<div class="bg-image"></div>
		</div>
		<!-- END inner-image-header -->
		<div>
			<div>
				<div class="container">					
						<fieldset>
							<legend align="left">Search criteria </legend>
							<div>
								Page :<input type="text" name="pagelist"
									id="pagelist" list="pageName"
									placeholder="-- Select Page --" />
								<datalist id="pageName">
								</datalist>
							</div>
							<div>
								Page Slot :<input type="text" name="pageSlotlist"
									id="pageSlotlist" list="pageSlotName"
									placeholder="-- Select Page Slot --" />
								<datalist id="pageSlotName">
								</datalist>
							</div>
							<div>
								Banner Name :<input type="text" name="bannerList"
									id="bannerList" list="bannerName"
									placeholder="-- Select Banner Name --" />
								<datalist id="bannerName">
								</datalist>
							</div>
							<div>
								Start Date : <input type="date" id="startdate" name="search">
							</div>
							<div>
								End Date : <input type="date" id="enddate" name="search">
							</div>
							<div>
								<button type="submit">Clear</button>
								<button type="submit" id="searchList" >Search
									List</button>
							</div>
						</fieldset>					
				</div>
			</div>
			</br></br>
			<div>
				<div class="container">
					<h2>Result set</h2>
					<table id="table" class="table-responsive">
						<thead>
							<tr>
							   <th data-field="id">Item ID</th>
                               <th data-field="name">Item Name</th>
							</tr>
						</thead>
						<tbody id="tbody">
						</tbody>
					</table>
				</div>
			</div>
			
			
			<div class="">
					<table id="example"
						class="table table-striped table-bordered dt-responsive nowrap"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Item ID</th>
								<th>Item Name</th>								
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Item ID</td>
								<td>Item Name</td>								
							</tr>
						</tbody>
					</table>
				</div>
		</div>
	</div>
	<!-- End Container - Top Providers list -->

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!--End  Footer -->

</body>

</html>