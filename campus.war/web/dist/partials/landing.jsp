<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161025 TR c1 landing page layout - done -->
<!-- 20161025 TR c1 main menu bar - done -->
<!-- 20161025 TR c1 main search bar - done -->
<!-- 20161025 TR c1 start styling category search area -->
<!-- 20161111 PN c1-campus-landing-page modified the UI to load categories from the DB values dynamically. -->
<!-- 20170112 DJ c123-general-filter-search-course-provider-MP-dj  searchFunction() Implementation. -->

<script type="text/javascript">

function searchFunction(){
	alert("searchFunction");
	var keyWordString=" ";
	$.ajax({
		url : '../../PublicController',
		data : {
			CCO : 'FILTER_SEARCH_COURSE_PROVIDERS',
			keyWordString : keyWordString
		},
		dataType : "json",
		success : function(response) {
			getProviderCodeList(response);
		},
		error : function(jqXHR, exception) {			
			var msg = '';
			if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 500) {
	            msg = 'Internal Server Error [500].';
	        }  else if (exception === 'timeout') {
	            msg = 'Time out error.';
	        } else {
	            msg = 'Internal error is occurred. Please try again.';
	        }	        
	        alert(msg);
		}
	});
	
	function getProviderCodeList(response){
		
		$.each(response.codeList,	function(index, value) {
			var res = value;
			
		});
		var catCode = $("#catCode").val();
		$.ajax({
			url : '../../PublicController',
			data : {
				CCO : 'LIST_ALL_COURSE_PROVIDERS',
				categoryCode : catCode
			},
			dataType : "json",
			success : function(response) {
				getInitialPageResults(catCode,response);
			},
			error : function(jqXHR, exception) {			
				var msg = '';
				if (jqXHR.status === 0) {
		            msg = 'Not connect.\n Verify Network.';
		        } else if (jqXHR.status == 404) {
		            msg = 'Requested page not found. [404]';
		        } else if (jqXHR.status == 500) {
		            msg = 'Internal Server Error [500].';
		        }  else if (exception === 'timeout') {
		            msg = 'Time out error.';
		        } else {
		            msg = 'Internal error is occurred. Please try again.';
		        }	        
		        alert(msg);
			}
		});
		
	}
}

</script>

<div class="main-screen clearfix">
	<div class="content-panel clearfix">
		<div class="main-slider clearfix">
			<img src="dist/i/slide-1.jpg" alt="">
		</div>
		<!-- End Main slider -->
		<div class="search-area clearfix">
			<div class="main-search-bar center-block clearfix">
				<div class="bar-holder center-block clearfix">
					<input class="center-block" type="text"
						placeholder="Search : Program, Course, or Career   e.g: accounting, accountant ">
					<div align="center">
					  <div align="center">
						<input  type="radio" name="courseProvider" value="CP"><span align="left" >Course Provider</span>						
						<input type="radio" name="courseProvider"	value="P"><span align="left">Programme</span>	
						</div>					
						<button  type="button" id="addSearchData" name="addSearchData"
								class="btn btn-primary" onclick="searchFunction()">Apply Search</button>													
						<form action="PublicController" method="POST">
							<button type="submit" name="CCO" id="CCO"
								value="FILTER_SEARCH_COURSE_PROVIDERS"
								class="pure-button pure-button-primary">Search</button>
						</form>
					</div>
				</div>
			</div>
			<!-- End Main search bar -->

			<div class="filter-boxes clearfix">
				<div class="box-holder center-block">
					<ul class="list-inline" name="mainCategoryList"
						id="mainCategoryList">




					</ul>
				</div>

			</div>
			<!-- End category boxes area -->
		</div>
	</div>

	<div class="banner-panel pull-right clearfix">Advertise Here</div>
</div>
<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
	<script src="/dist/js/general-filter-search.js"></script>
	<script src="/dist/js/filterSearch/ui-provider-populate.js"></script>
	
	<!-- W3-Include -->
	<script src="/dist/bower-components/w3/w3data.js"></script>
