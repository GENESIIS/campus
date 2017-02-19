//20170202 DK CAM-124: Developed and integrate the UI
//20170215 DK CAM-124: Fixing UI issues mentioned by HF at MX phase, rtc 201702090950 HF 
//20170216 DK CAM-124: Include JS Autoclear function to clear course search field as placeholder attribute doesn't work on previous versions of IE
//20170216 DK CAM-124: Modified JS code to focus event of the course search field
//20170216 DK CAM-124: Modified JS code to submit data on pressing enter key
//20170216 DK CAM-124: Modified JS code to validate empty strings for the course search input
//20170217 DK CAM-124: Modified JS code to clear text field and assign default placeholder text when click outside of the textbox

$( document ).ready(function() {
	
		/*
		 	* Course search panel related JS
		 	* DK Dimuthu Kalyanaratne
		*/
		
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
		
		
		/*
		 	* Course filter search panel open for the focus event of the course search input
		 	* DK Dimuthu Kalyanaratne
		*/		
		
		$("#course-search").focus(function(event){
			if( $(".filter-boxes").is(':visible')){

					$(".filter-boxes").hideUp("slow");
					$(".rotate").toggleClass("down")  ; 
	
			}else{
				$(".filter-boxes").showDown("slow");
				$(".rotate").toggleClass("down")  ; 
			}		
			return false;
		});
		
		
		/*
		 	* Course filter search panel open for the click event of drop down arrow
		 	* DK Dimuthu Kalyanaratne
		*/
		
		$(".panel-collapse-ico").click(function(event){
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
				$("#course-search").val('Search : Program, Course, or Career');  
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
			
			var courseSearchTxt = $("#course-search").val();
			
			//if( courseSearchTxt != "Search : Program, Course, or Career"){
			if( $("#searchFcuntion").val() != "filterSearch"){
							
				var keyWordString = " ";
				var selectedType = " ";
				
				keyWordString = $("#course-search").val();
				selectedType = $('input[name="courseOpt"]:checked').val();
	
				if (selectedType == 'CPROVIDER') {
					alert("Will load course providers \n implemented in cam-123");				
				} else {
					window.location.assign("/dist/partials/courses.jsp?keyWord=" + keyWordString);
				}

				
			}else{
					
				e.preventDefault();
				return false;
				
			}

		});
		
		
		/*
		 	* Submit form data pn "Enter"
		 	* DK Dimuthu Kalyanaratne
		*/

		$("input#course-search").on('keyup', function (e) {
			
			if(e.which == 13) {
				
				if(!this.value == "" || !this.value == "Search : Program, Course, or Career"){
					
					var keyWordString = " ";
					var selectedType = " ";
					
					keyWordString = $("#course-search").val();
					selectedType = $('input[name="courseOpt"]:checked').val();
		
					if (selectedType == 'CPROVIDER') {
						alert("Will load course providers \n implemented in cam-123");				
					} else {
						window.location.assign("/dist/partials/courses.jsp?keyWord=" + keyWordString);
					}
				
				}else{
					
					e.preventDefault();
				    return false;
					
				}	
				
			}
							
		});


		

		/*
		 	* Auto clear for course search field
		 	* DK Dimuthu Kalyanaratne
		*/
		
		$("input#course-search").focus(function() {		  
			 if(this.value == "Search : Program, Course, or Career") {
				 $(this).val("");
			 }
		}).blur(function() {
			if (this.value == "") {
				$(this).val("Search : Program, Course, or Career");
			}
		});
		
		
		//Set default check as programme title 
		$("input[name=courseOpt][value='PROGRAMME']").prop("checked",true);

		
	});
