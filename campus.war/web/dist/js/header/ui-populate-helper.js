//20161109 JH c7-higher-eduction-landing-page-MP 
//20161109 JH c7-higher-eduction-landing-page-MP 

/**
 * This method is to load category details
 */
function displayCategory() {
    alert("Hello! I am an alert box!");
	$.get('PublicController', {
		CCO : 'LIST_LEVEL_DATA'
	}, function(response) {
		var result = $("#categoryName");
	
	
	});
}