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


// Banner-rotation code 
var bannerSlotWrappers = $('.banner-wrapper');
var bannerSlotTimers = [];

bannerSlotWrappers.each(function(index){
	bannerSlotTimers.push(function (slotIndex) {
		var shownBanner = $(bannerSlotWrappers.get(slotIndex)).find('.banner-shown');
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
			bannerSlotTimers[slotIndex](slotIndex);
		}, t1 * 1000, slotIndex);
	});
});

$.each(bannerSlotTimers, function(index, value) {
	value(index);
});


// Event handler for sending DB add operation upon clicking of a banner
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


$(document).ready(function() {
	
	var bannerLoadingStatusIndicator = $('#areBannersDeliveredWithPage');	
	var areBannersLoadedWithPage = bannerLoadingStatusIndicator.val();
	
	
	if (areBannersLoadedWithPage == null || areBannersLoadedWithPage == 'false') {
		getBanners();
	}
});

//Event handler for sending Ajax request to fetch banners 
function getBanners() { 
	
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
				setBannersToPageSlots(response, pageName);
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

	// Bind event handler for click event to banner elements
	$('.banner').on('click', function(e) {
		e.preventDefault();
		var url = $($(this).parents('.banner-wrapper').find('img.banner-shown').get(0)).parents('a').attr('href');
		sendBannerStatisticsUpdateRequest($(this));
		window.open(url, '_blank');
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

