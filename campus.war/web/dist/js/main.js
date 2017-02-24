/*
 	* Created by Tharaka Rathnayake on 10/24/2016.
*/

// Removed w3IncludeHTML() 

// SlideToggle Controller

$(document).ready(function () {
	
    $(".flip").click(function () {
        $(this).next('.slideable').slideToggle("slow");
    });
        
    // Admin Forms - Accordion - Dimuthu Kalyanaratne 24022017
    $(".admin .accordion").accordion({		 
		active: 0,
		header: ".accordion-header",
		collapsible: false,
		heightStyle: "content", 
		autoHeight: false,
		navigation: true	
	});
    
    // Admin Forms - Bootstrap Tooltip - Dimuthu Kalyanaratne 24022017
    $('[data-toggle="tooltip"]').tooltip();  
    
});
