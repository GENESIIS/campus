 /**
  * 20161227 MM c25-student-create-dashboard-MP - Initialised file with getProgrammeData()
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