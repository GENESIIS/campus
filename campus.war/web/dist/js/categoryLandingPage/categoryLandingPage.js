/**
 * 20161109 MM c5-corporate-training-landing-page-MP Initialised file with getProgrammeData()
 * 20161109 MM c5-corporate-training-landing-page-MP Added code in (document).ready() function
 * 20161110 MM c5-corporate-training-landing-page-MP Added code to display programme data retrieved
 * 20161110 MM c5-corporate-training-landing-page-MP Modified code to properly display the town list for each programme 
 */

//alert("entered script");

$(document).ready(function() {
	getProgrammeData();
});

function getProgrammeData() {
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			CCO : 'LIST_CATEGORY_PROGRAMMES',
			category : '3',
			pageNum : '1'
		},
		dataType : "json",
		async : false,
		success : function(response) {
			alert("Ajax Success");
			
			if (response !== undefined && response !== null) {
				
				//Construct the Level/Major menu on top of programme displaying area
				var levelOrMajorHtmlFragment = '';
				var levelOrMajorCollection = response.levelOrMajorCollection;
				var programmeCodeToTownListMap = response.programmeCodeToTownListMap;
				
				if (levelOrMajorCollection !== undefined && levelOrMajorCollection !== null) {
					levelOrMajorHtmlFragment += '<li><a href="javascript:">All</a></li>';
					$.each(levelOrMajorCollection, function(index, val) {
						levelOrMajorHtmlFragment += '<li><a href="javascript:">'+ val[1] +'</a></li>';
					});					
				}
				
				var levelOrMajorListElement = $('.filtering-area').find('ul.list-inline');
				levelOrMajorListElement.html(levelOrMajorHtmlFragment);		
				

				//Construct the Programme listing 
				var programmesHtmlFragment = '';
				var programmeCollection = response.result;
				
				if (programmeCollection !== undefined && programmeCollection !== null) {
					$.each(programmeCollection, function(index, val) {						
						programmesHtmlFragment += '<li class="course-info clearfix">';
						programmesHtmlFragment += '<div class="col-name">';
						programmesHtmlFragment += '<h1 class="pro-name">' + val[18] + '</h1>';
						programmesHtmlFragment += '<div class="pro-logo">';
						
						var logoImgPath = response.contextDeployLogoPath + val[22] + "/" + val[16];
						
						programmesHtmlFragment += '<img src=' + logoImgPath + ' alt="' + val[18] + ' logo">';
						programmesHtmlFragment += '</div>';
						programmesHtmlFragment += '</div>';
						programmesHtmlFragment += '<div class="col-description">';
						programmesHtmlFragment += '<p>' + val[1] + '</p>';
						programmesHtmlFragment += '</div>';
						programmesHtmlFragment += '<div class="col-location">';
						
						var townListForCurrentProgramme = programmeCodeToTownListMap[val[0]];
						
						$.each(townListForCurrentProgramme, function(i, currentTown) {
							programmesHtmlFragment += '<a href="javascript:">' + currentTown[1] + '</a></br>';
						});
						
						programmesHtmlFragment += '</div>';
						// Printing major/Level, entry requirements
						programmesHtmlFragment += '<div class="col-duration">';
						programmesHtmlFragment += '<label>' + val[23] + '<br><span>' + val[24] + '</span></label>';
						programmesHtmlFragment += '</div>';
						programmesHtmlFragment += '</li>';						
					});					
				}
				
				var programmeListElement = $('.filter-result-table').find('ul.result-row');
				programmeListElement.html(programmesHtmlFragment);	
			}
		},
		error : function(response) {
			alert("Ajax Error");
		}
	});
}