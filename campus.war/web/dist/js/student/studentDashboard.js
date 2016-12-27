 /**
  * 20161227 MM c25-student-create-dashboard-MP - Initialised file with getProgrammeData()
  * 20161227 MM c25-student-create-dashboard-MP - Added assignContentToCarousel() function to create 
  * 			the carousel content with fetched programmes
  * 
  */ 

window.programmeCollectionFetched = null;
window.messageType = {INFO: 'INFO', ERROR: 'ERROR'};

$(document).ready(function() {
	getRecommendedProgrammes();
});

function getRecommendedProgrammes () {
	$.ajax({
		url : '/StudentController',
		method : 'POST',
		data : {
			'CCO' : 'LIST_STUDENT_RECOMMENDED_PROGRAMMES'
		},
		dataType : "json",
		async : false,
		success : function(response) {
			
			if (response !== undefined && response !== null) {
				window.programmeCollectionFetched = response.result;
				assignContentToCarousel();
				
			}			
		},
		error : function(response) {
			var messageList = [];
			messageList.push("There was an error retrieving data to display from the server.");
			messageList.push("Please check that you are connected to the Internet and that you are sending the correct data.");
			showMessages(messageList, window.messageType.ERROR);
		}
	});
	
};

function assignContentToCarousel() {
//	alert("inside assignContentToCaraousel()");
	
	var programmeCollectionFetched = window.programmeCollectionFetched;
	
	var carouselInnerDiv = $('.carousel-inner');
	var carouselItemsHtml = '';
	var currentProgramme = null;
	for (var i = 0; i < programmeCollectionFetched.length; i++) {
		
		currentProgramme = programmeCollectionFetched[i];
		
		carouselItemsHtml = '<div class="item active">\
	        <blockquote>\
	    <div class="row">\
	        <div class="col-sm-8 col-sm-offset-2">';
		carouselItemsHtml = '<label>' + currentProgramme[1] + '</label>';
		carouselItemsHtml = '<p>' + currentProgramme[4] + '</p>';
		carouselItemsHtml = '<small>' + 'CourseProvider' + '</small>';
		carouselItemsHtml = '</div>\
		    </div>\
			</blockquote>\
			</div>';
	}
	
	carouselInnerDiv.html(carouselItemsHtml);	
}