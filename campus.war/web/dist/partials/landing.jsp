<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- 20161025 TR c1 start style header bottom part -->
<!-- 20161025 TR c1 landing page layout - done -->
<!-- 20161025 TR c1 main menu bar - done -->
<!-- 20161025 TR c1 main search bar - done -->
<!-- 20161025 TR c1 start styling category search area -->
<!-- 20161111 PN c1-campus-landing-page modified the UI to load categories from the DB values dynamically. -->
<!-- 20170112 DJ c123-general-filter-search-course-provider-MP-dj  searchFunction() Implementation. -->
<!-- Bootstrap -->
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    
    
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
				<!-- <div class="bar-holder center-block clearfix">
						<input id="course-search" class="center-block" type="text" placeholder="Search : Program, Course, or Career">
						<a class="panel-collapse-ico" href="#"><span class="fa fa-angle-down fa-3x rotate"></span></a>
						
						<div class="filter-boxes clearfix">
				
							<div class="box-holder container-fluid center-block">
								<div class="row list-inline" name="mainCategoryList" id="mainCategoryList">	
									<div class="col-sm-10">		
										<label class="search-title">Search Options: </label>
										<label class="radio-inline">
											<input type="radio" name="courseOpt" value="courseProvider">Course Provider
										</label>
										<label class="radio-inline">
											<input type="radio" name="courseOpt" value="programTitle">Program Title
										</label>
									</div>
									<div class="col-sm-2">
										<button type="submit" class="btn btn-search btn-small btn-block">Search</button>
									</div>			
								</div>
							</div>
	
						</div>
					</div>
			</div> -->
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
	$( document ).ready(function() {	
		$(".filter-boxes").css({"display": "none", "opacity": "0"}); 
		
	    'use strict';
		// Sort us out with the options parameters
		var getAnimOpts = function (a, b, c) {
				if (!a) { return {duration: 'normal'}; }
				if (!!c) { return {duration: a, easing: b, complete: c}; }
				if (!!b) { return {duration: a, complete: b}; }
				if (typeof a === 'object') { return a; }
				return { duration: a };
			},
			getUnqueuedOpts = function (opts) {
				return {
					queue: false,
					duration: opts.duration,
					easing: opts.easing
				};
			};
		// Declare our new effects
		$.fn.showDown = function (a, b, c) {
			var slideOpts = getAnimOpts(a, b, c), fadeOpts = getUnqueuedOpts(slideOpts);
			$(this).hide().css('opacity', 0).slideDown(slideOpts).animate({ opacity: 1 }, fadeOpts);
		};
		$.fn.hideUp = function (a, b, c) {
			var slideOpts = getAnimOpts(a, b, c), fadeOpts = getUnqueuedOpts(slideOpts);
			$(this).show().css('opacity', 1).slideUp(slideOpts).animate({ opacity: 0 }, fadeOpts);
		};
		
		$("#course-search, .panel-collapse-ico").click(function(event){
			if( $(".filter-boxes").is(':visible')){

					$(".filter-boxes").hideUp("slow");
					$(".rotate").toggleClass("down")  ; 
	
			}else{
				$(".filter-boxes").showDown("slow");
				$(".rotate").toggleClass("down")  ; 
			}		
			return false;
		});
		
		$(document).click(function(event) { 
			if(!$(event.target).closest(".search-area").length) {
				if($(".filter-boxes").is(":visible")) {
					$(".filter-boxes").hideUp("slow");
					$(".rotate").toggleClass("down")  
				}
			}        
		});
		
	});
	</script>
<script type="text/javascript">
function searchFunction(){
	var keyWordString=" ";
	var selectedType=" ";
	keyWordString=$("#keyWord").val();
	selectedType=$('input[name=cpRadio]:checked').val();
	
	if(selectedType=='CPROVIDER'){
		window.location.assign("/dist/partials/viewMoreCourseProviders.jsp?keyWord="+keyWordString+"&selectedType="+ selectedType);		
	}else {
		alert("Will load programes \n will implement in cam-124");
	}
}
</script>
