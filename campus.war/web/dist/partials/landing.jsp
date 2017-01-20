<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161025 TR c1 landing page layout - done -->
<!-- 20161025 TR c1 main menu bar - done -->
<!-- 20161025 TR c1 main search bar - done -->
<!-- 20161025 TR c1 start styling category search area -->
<!-- 20161111 PN c1-campus-landing-page modified the UI to load categories from the DB values dynamically. -->
<!-- 20170112 DJ c123-general-filter-search-course-provider-MP-dj  searchFunction() Implementation. -->

<div class="main-screen clearfix">
	<div class="content-panel clearfix">
		<div class="main-slider clearfix">
			<img src="dist/i/slide-1.jpg" alt="">
		</div>
		<!-- End Main slider -->
		<div class="search-area clearfix">
			<div class="main-search-bar center-block clearfix">
				<div class="bar-holder center-block clearfix">
					<input class="center-block" type="text" id="keyWord" name="keyWord"
						placeholder="Search : Program, Course, or Career   e.g: accounting, accountant ">
					<div align="center">
					  <div align="center">
						<input  type="radio" name="cpRadio" value="CPROVIDER"><span align="left" >Course Provider</span>						
						<input type="radio" name="cpRadio"	value="PROGRAMME"><span align="left">Programme</span>	
						</div>					
						<button  type="button" id="addSearchData" name="addSearchData"
								class="btn btn-primary" onclick="searchFunction()">Apply Search</button>
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

<script type="text/javascript">
function searchFunction(){
	var keyWordString=" ";
	var selectedType=" ";
	keyWordString=$("#keyWord").val();
	selectedType=$('input[name=cpRadio]:checked').val();
	
	if(selectedType=='CPROVIDER'){
		window.location.replace("/dist/partials/viewMoreCourseProviders.jsp?keyWord="+keyWordString+"&selectedType="+ selectedType);		
	}else {
		alert("Will load programes \n will implement in cam-124");
	}
}
</script>
