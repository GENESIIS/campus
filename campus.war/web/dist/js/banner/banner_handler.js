/**
 * 20161118 MM c2-integrate-google-banners-MP Initialised file
 * 20161118 MM c2-integrate-google-banners-MP Put in code to rotate the banners in slots
 * 20161118 MM c2-integrate-google-banners-MP Included code to send Ajax request to update 
 * 				banner click statistics upon clicking of banners
 */

$(window).load(function() { //start after HTML, images have loaded
 
    var InfiniteRotator =
    {
        init: function()
        {
            //initial fade-in time (in milliseconds)
            var initialFadeIn = 100;
 
            //interval between items (in milliseconds)
            var itemInterval = 2500;
 
            //cross-fade time (in milliseconds)
            var fadeTime = 250;
 
            //count number of items
            var numberOfItems = $('.rotating-item').length;
 
            //set current item
            var currentItem = 0;
 
            //show first item
            $('.rotating-item').eq(currentItem).fadeIn(initialFadeIn);
 
            //loop through the items
            var infiniteLoop = setInterval(function(){
                $('.rotating-item').eq(currentItem).fadeOut(fadeTime);
 
                if(currentItem == numberOfItems -1){
                    currentItem = 0;
                }else{
                    currentItem++;
                }
                $('.rotating-item').eq(currentItem).fadeIn(fadeTime);
 
            }, itemInterval);
        }
    };
 
    InfiniteRotator.init();    
    
    $('.banner').on('click', function() {
//    	alert('a banner was clicked.');
    	sendBannerStatisticsUpdateRequest($(this));
    });
 
});


// Event handler for sending DB add operation upon clicking of a banner
function sendBannerStatisticsUpdateRequest(banner) {
	// IMPORTANT: these values must be validated to be of numeric type before further steps are executed 
	
	var bannerCode = banner.attr('data-banner-code');
	
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'ADD_BANNER_STAT',
			'banner' : bannerCode,
			'callerPage' : 'bannerStatus.jsp'
		},
		dataType : "json",
		async : false,
		success : function(response) {
//			alert("Ajax Success");
			
//			alert(response.result);
			
			
		},
		error : function(response) {			
			alert("Ajax Error");
		}
	});
}

