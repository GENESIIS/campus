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
    <style type="text/css">
	
		.clearfix::before, .clearfix::after {
			content: "";
			display: table;
			line-height: 0;
		}
		
		.main-screen .content-panel .search-area .main-search-bar .bar-holder input#course-search{
			border: medium none;
			color: #918a8a;
			font-size: 24px;
			line-height: 64px;
			padding-left: 30px;
			padding-right: 30px;
			width: 100%;
		}
		
		.main-screen .content-panel .search-area .main-search-bar .bar-holder {
			background: rgba(125, 125, 125, 0.5) none repeat scroll 0 0;
			display: block;
			padding: 13px;
			position: relative;
			top: 0;
			width: 80%;
		}
		.main-screen .content-panel .search-area .main-search-bar .bar-holder input[type="radio"] {
			width: auto!important;
		}
		
		.main-screen .content-panel .search-area .filter-boxes .box-holder{
			width: auto!important;
		}
		
		.main-screen .content-panel .search-area .main-search-bar {
			display: block;
		}
		
		.main-screen .content-panel .search-area {
			display: block;
			position: relative;
			z-index: 10;
		}
		
		.main-screen .content-panel .search-area .filter-boxes {
			background: #fff;
			padding: 0 15px 0;
		}

		.main-screen .content-panel .search-area .filter-boxes .box-holder {
			padding-top: 15px;
			padding-bottom: 15px;
			padding-right: 0;
			border-top: 1px solid #ccdae7;			
		}
		
		.panel-collapse-ico{
			color: #BDBDBD;
			position: absolute;
			top: 25px;
			right: 30px;
			height: 100%;
			display: block;
		}
			.panel-collapse-ico span{
				position: relative;

			}
			
		.rotate{
			-moz-transition: all 1s linear;
			-webkit-transition: all 1s linear;
			transition: all 1s linear;
		}
		

		.rotate.down{
			-moz-transform:rotate(-180deg);
			-webkit-transform:rotate(-180deg);
			transform:rotate(-180deg);
		}
		
		.search-title{
			padding-right: 35px;
		}
		
		.btn-search{
			color: #fff;
			font-size: 1.2em;
			background: #4889F1;
			border-radius: 0;
		}
		.btn-search:hover{
			color: #fff;
		}		
	</style> 
	
	<div class="main-screen clearfix">
		<div class="content-panel clearfix">
			<div class="main-slider clearfix">
				<img src="dist/i/slide-1.jpg" alt="">
			</div>
			<div class="search-area clearfix">
				<div class="main-search-bar center-block clearfix">
					<div class="bar-holder center-block clearfix">
						<input id="course-search" class="center-block" type="text" name="course-search" placeholder="Search : Program, Course, or Career">
						<a class="panel-collapse-ico" href="#"><span class="fa fa-angle-down fa-3x rotate"></span></a>
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
								class="btn btn-primary" onclick="searchFunction()">Apply Search</button>
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

	function searchFunction() {
		var keyWordString = " ";
		var selectedType = " ";
		keyWordString = $("#course-search").val();
		selectedType = $('input[name="courseOpt"]:checked').val();

		if (selectedType == 'CPROVIDER') {
			window.location
					.assign("/dist/partials/viewMoreCourseProviders.jsp?keyWord="
							+ keyWordString + "&selectedType=" + selectedType);
		} else {
			alert("Will load programes \n Will be implemented in cam-124");
		}
	}
</script>
