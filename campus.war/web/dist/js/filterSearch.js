//20170202 DK CAM-124: Developed and integrate the UI
//20170215 DK CAM-124: Fixing UI issues mentioned by HF at MX phase, rtc 201702090950 HF 
	
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
		
		$('.main-screen').click(function(event) { 
			if(!$(event.target).closest(".bar-holder").length) {
				if($(".filter-boxes").is(":visible")) {
					$(".filter-boxes").hideUp("slow");
					$(".rotate").toggleClass("down")  
				}
			}        
		});
		
		
		/*
		 * General Filter search button click action.
		 * DJ dumani
		 */
		$('#addSearchData').click(function(e){
			var keyWordString = " ";
			var selectedType = " ";
			keyWordString = $("#course-search").val();
			selectedType = $('input[name="courseOpt"]:checked').val();

			if (selectedType == 'CPROVIDER') {
				alert("Will load course providers \n implemented in cam-123");				
			} else {
				window.location.assign("/dist/partials/courses.jsp?keyWord=" + keyWordString);
			}
			
			e.preventDefault();	

		});
		
	});
