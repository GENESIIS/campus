<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161025 TR c1 landing page layout - done -->
<!-- 20161025 TR c1 main menu bar - done -->
<!-- 20161025 TR c1 main search bar - done -->
<!-- 20161025 TR c1 start styling category search area -->
<!-- 20161111 PN c1-campus-landing-page modified the UI to load categories from the DB values dynamically. -->
<!-- 20170112 DJ c123-general-filter-search-course-provider-MP-dj  searchFunction() Implementation. -->
<!-- 20170123 DJ c123-general-filter-search-course-provider-MP-dj  UI integration. -->
<!-- Bootstrap -->
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <div class="main-screen clearfix">
		<div class="content-panel clearfix">
			<div class="main-slider clearfix">
				<img src="dist/i/slide-1.jpg" alt="">
			</div>
			<div class="search-area clearfix">
				<div class="main-search-bar center-block clearfix">
					<div class="bar-holder center-block clearfix">
						<input id="course-search" class="center-block" type="text" name="course-search" placeholder="Search : Program, Course, or Career">
						<a class="panel-collapse-ico" href="#"><span class="glyphicon glyphicon-menu-down rotate"></span></a>
						<!-- End category boxes area -->
						<div class="filter-boxes clearfix">
				
							<div class="box-holder container-fluid center-block">
								<div class="row list-inline" name="mainCategoryList" id="mainCategoryList">	
									<div class="col-sm-10">		
										<label class="search-title">Search Options: </label>
										<label class="radio-inline">
											<input type="radio" name="courseOpt" value="CPROVIDER">Course Provider
										</label>
										<label class="radio-inline">
											<input type="radio" name="courseOpt" value="PROGRAMME">Program Title
										</label>
									</div>
									<div class="col-sm-2">
										<!-- <button type="submit" class="btn btn-search btn-small btn-block">Search</button> -->
										<button  type="button" id="addSearchData" name="addSearchData"
								class="btn btn-primary btn-block" onclick="searchFunction()">Apply Search</button>
									</div>			
								</div>
							</div>
	
						</div>
					</div>
				</div>
				<!-- End Main search bar -->
			</div>
		</div>
	</div> 
<!-- jQuery & Other js -->
<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="/dist/js/main.js"></script>
<script src="/dist/js/filterSearch.js"></script>
