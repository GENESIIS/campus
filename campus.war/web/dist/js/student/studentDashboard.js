 /**
  * 20161227 MM c25-student-create-dashboard-MP - Initialised file with getProgrammeData()
  * 20161227 MM c25-student-create-dashboard-MP - Added assignContentToCarousel() function to create 
  * 			the carousel content with fetched programmes
  * 20161227 MM c25-student-create-dashboard-MP - Fixed errors in composing the html string of 
  * 			programme items for the carousel; modified code to display CourseProvider's ShortName
  * 			for each programme result displayed in the carousel 
  * 20161228 MM c25-student-create-dashboard-MP - Added code to display the list of institutes sent 
  * 			by the server in the Ajax response
  * 20161229 MM c25-student-create-dashboard-MP - Added function that facilitates displaying of 
  * 			feedback messages to the user in case of error or absence of data
  * 20161230 MM c25-student-create-dashboard-MP - Added code to make Ajax call to retrieve data on 
  * 			student recent activities
  * 20170105 MM c25-student-dashboard-MP - Added code to hide recommended programmes and institutes sections from view 
  * 			when no data is available for them; disabled the display of section that shows recent activity
  * 
  */ 

window.programmeCollectionFetched = null;
window.instituteCollectionFetched = null;
window.courseProviderLogoPathFetched = null;
window.messageType = {INFO: 'INFO', ERROR: 'ERROR'};

$(document).ready(function() {
	getRecommendedProgrammes();
});

function showMessages(messages, msgType) {

	if (messages != undefined && messages != null && messages.length > 0) {
		var msgStyleClass = '';
		
		if (msgType === 'INFO') {
			msgStyleClass = 'alert-info';
		} else if (msgType === 'ERROR') {
			msgStyleClass = 'alert-danger';
		}
		
		var messagesHtml = '<div id="message-container" class="alert text-center ' + msgStyleClass + '" role="alert">';
		messagesHtml += '<span style="font-weight: 700">' + msgType + ': </span>';
		for (var i = 0; i < messages.length; i++) {
			messagesHtml += '<span>' + messages[i] + '</span></br>';
		}
		messagesHtml += '</div>';
		
		return messagesHtml;
	}	
}

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
//			alert("Success");
			
			if (response !== undefined && response !== null) {
				window.programmeCollectionFetched = response.result;
				window.instituteCollectionFetched = response.recommendedInstituteList;
				window.courseProviderLogoPathFetched = response.courseProviderLogoPath;
				assignContentToCarousel();
				constructInstituteListing();				
			}			
		},
		error : function(response) {
			alert("Ajax error");
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
		
	if (programmeCollectionFetched.length > 0) {
		var carouselInnerDiv = $('.carousel-inner');
		var carouselItemsHtml = '';
		var currentProgramme = null;
		
		for (var i = 0; i < programmeCollectionFetched.length; i++) {
			
			currentProgramme = programmeCollectionFetched[i];
			
			carouselItemsHtml += '<div class="item';
			if (i == 1) {
				carouselItemsHtml += ' active';
			}
			carouselItemsHtml += '">\
		        <blockquote>\
		    <div class="row">\
		        <div class="col-sm-8 col-sm-offset-2">';
			carouselItemsHtml += '<label>' + currentProgramme[1] + '</label>';
			carouselItemsHtml += '<p>' + currentProgramme[4] + '</p>';
			carouselItemsHtml += '<small>' + currentProgramme[17] + '</small>';
			carouselItemsHtml += '</div>\
			    </div>\
				</blockquote>\
				</div>';	
		}
		
		carouselInnerDiv.html(carouselItemsHtml);
	} else {
		$('.w-rec-courses').hide();
	}
}


function constructInstituteListing() {
//	alert("inside constructInstituteListing()");
	
	var instituteCollectionFetched = window.instituteCollectionFetched;
	var courseProviderLogoPath = window.courseProviderLogoPathFetched;
	
	var instituteULElement = $('div.rec-institute ul');
	
	if (instituteCollectionFetched.length > 0) {	
		var instituteListHtml = '';
		var currentInstitute = null;
		var courseProviderImage = null;
		var imageSuffix = '_small';
		var imageExtension = '.jpg';
		var imageSuffixAndExtension = imageSuffix + imageExtension;
		for (var i = 0; i < instituteCollectionFetched.length; i++) {
			
			currentInstitute = instituteCollectionFetched[i];
			var currentCourseProviderCode = currentInstitute[0];
			var currentCourseProviderShortName = currentInstitute[1];
			courseProviderImage = courseProviderLogoPath + '/' + currentCourseProviderCode + '/' + currentCourseProviderCode + imageSuffixAndExtension;
			
			instituteListHtml += '<li class="col-lg-12 col-sm-12 col-md-12">\
	        <div class="inst-logo col-lg-3 col-sm-12 col-md-4">\
				<img height="60px" width="180px" src="' + courseProviderImage + '" alt="' + currentCourseProviderShortName + ' logo">\
				</div>\
	        <a href="javascript:" class="col-lg-9 col-sm-12 col-md-8" data-institute-code="' + currentCourseProviderCode + '">' + currentCourseProviderShortName + ' - ' + currentInstitute[2] + '</a>\
	    </li>';
		}
		
		instituteULElement.html(instituteListHtml);	
	} else {
		$('.w-rec-institute').hide();
	}
	
//	getStudentActivities();
}

//function getStudentActivities () {
//	$.ajax({
//		url : '/StudentController',
//		method : 'POST',
//		data : {
//			'CCO' : 'LIST_STUDENT_RECENT_ACTIVITIES'
//		},
//		dataType : "json",
//		async : false,
//		success : function(response) {
//			
//			if (response !== undefined && response !== null) {
//				window.studentActivitiesFetched = response.result;
//			}			
//		},
//		error : function(response) {
//			alert("Ajax error");
//			var messageList = [];
//			messageList.push("There was an error retrieving data to display from the server.");
//			messageList.push("Please check that you are connected to the Internet and that you are sending the correct data.");
//			showMessages(messageList, window.messageType.ERROR);
//		}
//	});	
//};
//
//function constructActivityListing() {
//	
//	var studentActivities = window.studentActivitiesFetched;
//	
//	var activityULElement = $('div.widget-content ul.ul-activity');
//	var activityListHtml = '';
//
//	for (var i = 0; i < studentActivities.length; i++) {
//		
//		currentActivity = studentActivities[i];
//	
//		activityListHtml += '<li>Applied to BSc in Software Engineering at "Esoft Metro Campus" <br><span class="act-time">~ 20 March 2012 - Now </span></li>';
//	}
//	
//	activityULElement.html(instituteListHtml);	
//	
//	getStudentActivities();
//}