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
		 //$("#pageName").get(0).options.length = 0;
		//var pageName = $("#pageName");
		var htmlstr="";
		$.each(response.result, function(index, value) {
			if(value!=null && value.length>0){
			    htmlstr += '<option value="' + value[0] + '">' + value[1] + '</option>';
			}
			
		});	
		$('#pageName').html(htmlstr);
		
	/* 	$('#pagelist').bind('change', function(event) {
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
			
		}); */
	}	
	
	/* function getPageWisePageSlots(response) { 
		alert(" getPageWisePageSlots ");
		var pagecode=0;
		
	} */

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
		
		</div>
	</div>
	<!-- End Container - Top Providers list -->

	<!-- Footer -->
	<jsp:include page="/dist/partials/layout/footer.jsp" />
	<!--End  Footer -->

</body>

</html>