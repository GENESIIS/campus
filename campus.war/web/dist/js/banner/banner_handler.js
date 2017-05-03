/**
 * 20161118 MM c2-integrate-google-banners-MP Initialised file
 * 20161118 MM c2-integrate-google-banners-MP Put in code to rotate the banners in slots
 * 20161118 MM c2-integrate-google-banners-MP Included code to send Ajax request to update 
 * 				banner click statistics upon clicking of banners
 * 20161119 MM c2-integrate-google-banners-MP Fixed issue of banners being displayed slot 
 * 				after slot (not simultaneously)
 * 20161119 MM c2-integrate-google-banners-MP Made banners of a single slot display for 
 * 				only the period as specified in the DB for that banner
 * 20161120 MM c2-integrate-google-banners-MP Devices solution to display banners, of not 
 *				only a single slot, but all slots for only exactly the specified time in the 
 *				DB for each of those banners with an array of functions and setTimeout() JS function
 * 20161120 MM c2-integrate-google-banners-MP Fixed issue with event.target and $(this) 
 * 				returning the wrong url when sending trying to open the URL that the banner
 * 				has been set to to open in the DB
 * 20161120 MM c2-integrate-google-banners-MP Added warning on incompatibility with JQuery 3.1.1
 * 20161124 MM c2-integrate-google-banners-MP Added code so that the caller page is loaded dynamically
 * 				from data attributes of image tags that are displaying banners
 * 20161129 MM c2-integrate-google-banners-MP Modified code to get the anchor tag of the next banner
 * 				via the parent div tag, instead of the siblings() method, to rectify issue of a 
 * 				banner disappearing when it is the only one loaded for a slot
 * 20161217 MM c2-integrate-google-banners-MP Modified msg displayed when Ajax request results in error
 * 20170209 MM c111-display-banners-on-jsp-load Defined event handler that makes the Ajax request 
 				for banner data; assigned the banner data received from the server to applicable 
 				banner slot on the page 
 * 20170214 MM c127-display-banners-on-jsp-load-front-end Modified code in $.document.ready(...) to 
 				initiate the banner fetching process only in case of all banner slots in the page 
 				being without banner images
 * 20170220 MM c127-display-banners-on-jsp-load-front-end Added function to extract JSP name considering
 * 				it is present in a hidden field in the form as returned by JSTL code: 
 * 				${pageScope['javax.servlet.jsp.jspPage']} 	
 * 20170221 MM c127-display-banners-on-jsp-load-front-end Changed logic related to determining whether to
 * 				to send the Ajax request to fetch banners to use hidden field with id 
 * 				'areBannersDeliveredWithPage'	
 * 20170222 MM c127-display-banners-on-jsp-load-front-end Moved code that binds an event-handler to 'click' 
 * 				event of banner image elements to within document.ready(...). Made Ajax calls to fetch 
 * 				banner images and add banner statistics to have async = true. 				
 * 20170308 MM c127-display-banners-on-jsp-load-front-end Made changes to correctly identify the 
 * 				shown-banner to retrieve the banner-code to send banner-stat-update-request 				
 * 20170404 MM c117-display-banners-record-viewcount-back-end - Created test code to trigger execution of 
 * 				back-end code that persists banner-view-counts 				
 * 20170407 MM c117-display-banners-record-viewcount-back-end - Added code to track issue with NPE thrown 
 * 				when baner-view-stat persisting back-end code is triggered 			
 * 20170417 MM c128-display-banners-record-viewcount-front-end - Modified banner display code to dispatch 
 * 				banner-view-stat-update request each time a banner gets assigned the banner-shown class  			
 * 20170428 MM c128-display-banners-record-viewcount-front-end - Modified timer-related code to determine 
 * 				if an element is within view-port before view-stat-update request is sent; debugging is WIP		
 * 20170429 MM c128-display-banners-record-viewcount-front-end - Re-arranged code that sets timers for  
 * 				banner-rotation (into setBannerRotationTimes function) and binds events to banner images 
 * 				(into bindEventsToBanners function) to better organise code; added condition
 * 				to new setBannerRotationTimes() to only proceed with processing only if banners are there 
 * 				for a slot 
 * 20170503 MM c128-display-banners-record-viewcount-front-end - Modified setBannerRotationTimes() to 
 * 				prevent view-stat-update request from being sent when banners go out of view as result of 
 * 				horizontal scrolling (when scrolled to extreme left/right of screen)
 * 				 		
 */

// Hack to enable parameter passing for setInterval() method in IE9 and below
/*@cc_on
  // conditional IE < 9 only fix
  @if (@_jscript_version <= 9)
  (function(f){
     window.setTimeout=f(window.setTimeout);
     window.setInterval=f(window.setInterval);
  })(function(f){return function(c,t){var a=[].slice.call(arguments,2);return f(function(){c instanceof Function?c.apply(this,a):eval(c)},t)}});
  @end
@*/


/* WARNING: BANNER HANDLER CODE WILL NOT WORK WITH JQUERY 3.1.1. DISABLE IT ON PAGES WHERE BANNERS APPEAR 
 * THIS CODE PRODUCED EXPECTED BEHAVIOUR WITH JQUERY v2.2.2
 * */

$(document).ready(function() {
	
	var bannerLoadingStatusIndicator = $('#areBannersDeliveredWithPage');	
	var areBannersLoadedWithPage = bannerLoadingStatusIndicator.val();	
	
	if (areBannersLoadedWithPage == null || areBannersLoadedWithPage == 'false') {
		getBanners(setBannersToPageSlots);
	} else {
		bindEventsToBanners();
		
		setBannerRotationTimes();
	}
	
});

//Event handler for sending Ajax request to fetch banners 
function getBanners(callback) { 
	
	var pageName = getPageName();
	
	if (pageName != undefined && pageName != null) {		
		$.ajax({
			url : '/PublicController',
			method : 'POST',
			data : {
				'CCO' : 'LIST_BANNERS',
				'pageName' : pageName
			},
			dataType : "json",
			async : true,
			success : function(response) {			
				console.log('Ajax request made to fetch banner images succeeded');
				callback(response, pageName);
			},
			error : function(response) {			
				console.log('Ajax request made to fetch banner images failed.');
			}
		});
	}
}

// Assign banner content fetched from server to banner slots on the page
function setBannersToPageSlots(response) {
	var pageSlots = $('.banner-wrapper');
	var bannerPath = response.bannerPath;
	
	$.each(pageSlots, function(index, item) {
		var slotId = $(this).prop('id');
		
		var bannerCollectionForSlot = response[slotId];
		
		var pageSlotHtml = '';
		var classList = 'banner rotating-item';
		var fullClassList = '';
		$.each(bannerCollectionForSlot, function(count, banner) {
			fullClassList = (count == 0) ? 'banner-shown ' + classList : classList;
			pageSlotHtml += '<a href="' + banner[7] + '" target="_blank">';
			pageSlotHtml += '<img data-timeout="' + banner[5] + '" data-banner-code="' + banner[2] + '" class="' + fullClassList + '" src="' + bannerPath + '\\' + banner[2] + '\\' + banner[10] + '"/>';
			pageSlotHtml += '</a>';
		});
		$(this).html(pageSlotHtml);
	});

	bindEventsToBanners();
	
	setBannerRotationTimes();
}

//Binds various handlers for multiple events of banner images
function bindEventsToBanners() {
	// Bind event handler for click event to banner elements
	$('.banner').on('click', function(e) {
		e.preventDefault();
		var shownBanner = $($(this).parents('.banner-wrapper').find('img.banner-shown').get(0));
		var url = shownBanner.parents('a').attr('href');
		sendBannerStatisticsUpdateRequest(shownBanner);
		window.open(url, '_blank');
	}); 
	
	// Trigger click upon key-down on banner
	$('.banner').keydown(function(event){ 
	    var keyCode = (event.keyCode ? event.keyCode : event.which);   
	    if (keyCode == 13) {
	    	alert("banner keypress");
	        $('.banner').trigger('click');
	    }
	});
}

// Sets rotation times (for how long each will be visible) to banner images
function setBannerRotationTimes() {

	// Banner-rotation code 
	var bannerSlotWrappers = $('.banner-wrapper');
	var bannerSlotTimers = [];

	bannerSlotWrappers.each(function(index){
		bannerSlotTimers.push(function (slotIndex) {
			var shownBanner = $(bannerSlotWrappers.get(slotIndex)).find('.banner-shown');
			if (shownBanner.length > 0) {
				var t1 = shownBanner.data('timeout');      
				
				setTimeout(function () {
					var activeBanner = $(bannerSlotWrappers.get(slotIndex)).find('.banner-shown');
					activeBanner.removeClass('banner-shown');
					var anchor = activeBanner.parent().next();
					var nextBanner = null;
					if (anchor.length == 0) {
						nextBanner = activeBanner.parents('div.banner-wrapper').children('a').first().children('img');
					} else {
						nextBanner = anchor.children('img');
					}
					nextBanner.addClass('banner-shown');
					
					if (nextBanner.length > 0) {				
						// Send the banner-view-stat-update request only if banner-slot is in view 
						var top_of_element = nextBanner.offset().top;
						var bottom_of_element = nextBanner.offset().top + nextBanner.outerHeight();
					    var left_edge_of_element = nextBanner.offset().left;
					    var right_edge_of_element = nextBanner.offset().left + nextBanner.outerWidth();
					    
					    var bottom_of_screen = $(window).scrollTop() + $(window).height();
					    var top_of_screen = $(window).scrollTop();
					    var left_edge_of_screen = $(window).scrollLeft();
					    var right_edge_of_screen = $(window).scrollLeft() + $(window).width();
		
					    if((bottom_of_screen > top_of_element) && (top_of_screen < bottom_of_element) && 
					    		(right_edge_of_screen > left_edge_of_element) && (left_edge_of_screen < right_edge_of_element)){
					        console.log("Element is visible!");					
							var shownBannerCode = nextBanner.attr('data-banner-code');
							sendBannerViewStatUpdateRequest(shownBannerCode);
					    } else {
					    	console.log("Element is not visible. Not sending banner-rotation-view request");
					    }				    
					} else {
						console.log("The reference nextBanner is null!");
					}
					
					bannerSlotTimers[slotIndex](slotIndex);
				}, t1 * 1000, slotIndex);
			}
		});
	});

	$.each(bannerSlotTimers, function(index, value) {
		value(index);
	});
}

//Event handler for sending DB add operation upon clicking of a banner
function sendBannerStatisticsUpdateRequest(banner) { 
	
	var bannerCode = banner.attr('data-banner-code');
	var callerPage = getPageName();
	
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'ADD_BANNER_STAT',
			'banner' : bannerCode,
			'callerPage' : callerPage
		},
		dataType : "json",
		async : true,
		success : function(response) {			
			var operationStatus = response.operationStatus; 			
			if(operationStatus != undefined && operationStatus != null) {
				if (operationStatus === 'SUCCESS') {
					console.log('Banner-statistics successfully updated.');
				} else if (operationStatus === 'FAILURE') {
					console.log('Banner-statistics update failed.');
				}
			}
		},
		error : function(response) {			
			console.log('Ajax request to update statistics failed.');
		}
	});
}

//Send banner-view-stat update request
function sendBannerViewStatUpdateRequest(bannerCode) {	
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'ADD_BANNER_VIEW_STAT',
			'banner' : bannerCode
		},
		dataType : "json",
		async : true,
		success : function(response) {			
			var operationStatus = response.operationStatus; 			
			if(operationStatus != undefined && operationStatus != null) {
				if (operationStatus === 'SUCCESS') {
					console.log('Banner-view-statistics successfully updated.');
				} else if (operationStatus === 'FAILURE') {
					console.log('Attempt to update banner-view-statistics failed.');
				}
			}
		},
		error : function(response) {			
			console.log('Ajax request to update banner-view-statistics failed.');
		}
	});
}

// Get the current JSP page name
function getPageName() {
	
	var pageNameExtracted = $('input[type="hidden"]#pageName').val();
	var pageNameTokens = pageNameExtracted.split('.');
	var pageName = pageNameTokens[pageNameTokens.length - 1];
	pageNameTokens = pageName.split('_jsp@');
	pageName = pageNameTokens[0] + '.jsp';
	
	return pageName;
}