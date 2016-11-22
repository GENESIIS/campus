/**
 * 20161109 MM c5-corporate-training-landing-page-MP Initialised file with getProgrammeData()
 * 20161109 MM c5-corporate-training-landing-page-MP Added code in (document).ready() function
 * 20161110 MM c5-corporate-training-landing-page-MP Added code to display programme data retrieved
 * 20161110 MM c5-corporate-training-landing-page-MP Modified code to properly display the town list for each programme 
 * 20161110 MM c5-corporate-training-landing-page-MP Implemented pagination controls for the programme list
 * 20161111 MM c5-corporate-training-landing-page-MP Re-factored code in getProgrammeData() into several methods 
 * 20161111 MM c5-corporate-training-landing-page-MP Modified code in constructProgrammeListing() so that specific list of 
 *													programmes applicable for the currently clicked paginator button is selected
 *													and used for construction of programme list 
 * 20161111 MM c5-corporate-training-landing-page-MP Modified code to highlight currently selected element in paginator (while 
 * 													handling issue related to the same) 
 * 20161111 MM c5-corporate-training-landing-page-MP Modified code to enable and disable previous 
 * 													and next buttons in the  paginator controls 
 * 													according to the selected button
 * 20161112 MM c5-corporate-training-landing-page-MP Modified code to expect a filterType field in the JSON sent from server
 * 													which is used to identify what type of items are displayed in filtering 
 * 													menu (Majors for CorporateTraining category and Levels for others)
 * 20161112 MM c5-corporate-training-landing-page-MP Added "filtering" code so that when a major/level item from the menu
 * 													is clicked, the programme list and paginator buttons are re-created for 
 * 													based on that particular major/level 
 * 20161114 MM c5-corporate-training-landing-page-MP Removed reference to a property that is no longer included in the JSON 
 * 													object sent from server. Changed name of a different property to match
 * 													changes at server side code.  													
 * 20161115 MM c5-corporate-training-landing-page-MP Modified code to change filter elements to checkboxes and implemented
 * 													feature of allowing selection of multiple filter check-boxes 													
 * 20161116 MM c5-corporate-training-landing-page-MP Changed style of displayed error messages.													
 * 20161122 MM c5-corporate-training-landing-page-MP Modified code to prevent filtering menu from being shown when there are 
 * 													no returned programme records and to show a message when to the user then 													
 *
 */

//alert("entered script");

// IMPORTANT: Validate these values to see if they are in the expected format wherever they are used 
window.categoryCode = $('#categoryCode').val(); // This element is expected to be present in the JSP
window.programmeCollectionFetched = null;
window.programmeCollectionNarrowedDown = null;
window.levelOrMajorCollectionFetched = null;
window.programmeCodeToTownListMapFetched = null;
window.numOfResultsPerPageFetched = null;
window.courseProviderLogoPathFetched = null;
window.filterType = null;
window.messages = null;

$(document).ready(function() {
	$('#message-container').hide();
	getProgrammeData();
});

function showMessages(messages) {
	var messagesHtml = '';
	if (messages != undefined && messages != null && messages.length > 0) {
		messagesHtml += '<span style="font-weight: 700">ERROR: </span>';
		for (var i = 0; i < messages.length; i++) {
			messagesHtml += '<span>' + messages[i] + '</span></br>';
		}
		$('#message-container').html(messagesHtml).show();
	} else {
		$('#message-container').hide();
	}
}
function getProgrammeData() {
	// IMPORTANT: these values must be validated to be of numeric type before further steps are executed 
	var categoryIdentifierString = $('#categoryIdentifierString').val(); // This element is expected to be present in the JSP
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'LIST_CATEGORY_PROGRAMMES',
			'category' : window.categoryCode,
			'categoryIdentifierString' : categoryIdentifierString
		},
		dataType : "json",
		async : false,
		success : function(response) {
//			alert("Ajax Success");
			
			if (response !== undefined && response !== null) {
				window.programmeCollectionFetched = response.result;
				window.levelOrMajorCollectionFetched = response.levelOrMajorCollection;
				window.programmeCodeToTownListMapFetched = response.programmeCodeToTownListMap;
				window.numOfResultsPerPageFetched = response.numOfResultsPerPage;
				window.courseProviderLogoPathFetched = response.courseProviderLogoPath;
				window.filterType = response.filterType;	
				
				window.programmeCollectionNarrowedDown = response.result;
				window.messages = response.messages;
				
				if (window.programmeCollectionFetched !== undefined && window.programmeCollectionFetched !== null && window.programmeCollectionFetched.length > 0) {
					constructLevelOrMajorMenu();
					createProgrammeListingAndPaginator();
					showMessages(window.messages);
				} else {
					var messages = [];
					messages.push("It seems that no programmes are available for this category at the moment.");
					messages.push("Please check back soon...");
					showMessages(messages);
				}
			}			
		},
		error : function(response) {
			var messageList = [];
			messageList.push("There was an error retrieving data to display from the server.");
			messageList.push("Please check that you are connected to the Internet and that you are sending the correct data.");
			showMessages(messageList);
		}
	});
}

function constructLevelOrMajorMenu(){

	var levelOrMajorCollection = window.levelOrMajorCollectionFetched;
	
	//Construct the Level/Major menu on top of programme displaying area
	var levelOrMajorHtmlFragment = '';
	
	if (levelOrMajorCollection !== undefined && levelOrMajorCollection !== null && levelOrMajorCollection.length > 0) {
		levelOrMajorHtmlFragment += '<li class="major-or-level-menu-item major-or-level-menu-item-all">\
			<input class="major-or-level-checkbox major-or-level-checkbox-all" type="checkbox" checked/>All</li>';
		$.each(levelOrMajorCollection, function(index, val) {
			levelOrMajorHtmlFragment += '<li class="major-or-level-menu-item" data-level-or-major-code="' + val[0] + '">\
			<input class="major-or-level-checkbox"  data-level-or-major-code="' + val[0] + '" type="checkbox">'+ val[1] +'</li>';
		});					
	}
	
	var levelOrMajorListElement = $('div.course-filter-panel > div.filtering-area').find('ul.list-inline');
	levelOrMajorListElement.html(levelOrMajorHtmlFragment);	
	
	levelOrMajorListElement.find('input.major-or-level-checkbox').on('click', function() {
		var checkedInputs = null;
		if ($(this).hasClass('major-or-level-checkbox-all')) {
			if ($(this).is(':checked')) {
				$(this).parents('ul.list-inline').find('input.major-or-level-checkbox').not($(this)).prop('checked', false);
				checkedInputs = $(this).parents('ul.list-inline').find('input[type="checkbox"]:checked');
				filterProgrammesOnLevelOrMajor(checkedInputs);
			} else {
				$(this).prop('checked', true);
			}
		} else {
			if ($(this).is(':checked')) {
				$(this).parents('ul.list-inline').find('input.major-or-level-checkbox-all').prop('checked', false);
				checkedInputs = $(this).parents('ul.list-inline').find('input[type="checkbox"]:checked');
				filterProgrammesOnLevelOrMajor(checkedInputs);
			} else {
				checkedInputs = $(this).parents('ul.list-inline').find('input[type="checkbox"]:checked');
				if (checkedInputs.length > 0) {
					filterProgrammesOnLevelOrMajor(checkedInputs);
				} else {
					$(this).prop('checked', true);
				}
			}
		}
	});
}

function createProgrammeListingAndPaginator() {
	var pageNum = 1;
	constructProgrammeListing(pageNum);
	var paginatorDetails = constructPaginator();
	var paginatorListElement = $('div.paginator-div > nav > ul.pagination');
	paginatorListElement.children().eq(1).addClass('active');
	enableDisablePrevNextButtons(pageNum, paginatorDetails.numOfPages);
}

function constructProgrammeListing(pageNum) {

	var programmeCollection = window.programmeCollectionNarrowedDown;
	var programmeCodeToTownListMap = window.programmeCodeToTownListMapFetched;
	var courseProviderLogoPath = window.courseProviderLogoPathFetched;
	
	var numOfResultsPerPage = window.numOfResultsPerPageFetched;
	
	var indexIndicatorOfLastProg = numOfResultsPerPage * pageNum;
	var indexOfFirstProgNeeded = indexIndicatorOfLastProg - numOfResultsPerPage;
	
	var programmeCollectionForCurrentPage = programmeCollection.slice(indexOfFirstProgNeeded, indexIndicatorOfLastProg);	
	
	//Construct the Programme listing 
	var programmesHtmlFragment = '';
	
	if (programmeCollectionForCurrentPage !== undefined && programmeCollectionForCurrentPage !== null && programmeCollectionForCurrentPage.length > 0) {
		$.each(programmeCollectionForCurrentPage, function(index, val) {						
			programmesHtmlFragment += '<li class="course-info clearfix">';
			programmesHtmlFragment += '<div class="col-name">';
			programmesHtmlFragment += '<a href=""><h1 class="pro-name">' + val[18] + '</h1></a>';
			programmesHtmlFragment += '<div class="pro-logo">';
			
			var logoImgPath = courseProviderLogoPath + '\\' + val[22] + '\\' + val[16];
			
			programmesHtmlFragment += '<a href=""><img src="' + logoImgPath + '" alt="' + val[18] + ' logo"></a>';
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
	
	var programmeListElement = $('div.filter-result-table').find('ul.result-row');
	programmeListElement.html(programmesHtmlFragment);	
	
}

function constructPaginator() {

	// Validate these fields to see if they are in the required format
	var programmeCollection = window.programmeCollectionNarrowedDown;
	var numOfResultsPerPage = window.numOfResultsPerPageFetched;
	
	var totalNumOfResults = programmeCollection.length;
	var numOfPages = 0;
	
	if (totalNumOfResults > 0) {
		numOfPages = (totalNumOfResults % numOfResultsPerPage > 0) ? 
				parseInt(((totalNumOfResults / numOfResultsPerPage) + 1).toFixed(0)) : 
					parseInt((totalNumOfResults / numOfResultsPerPage).toFixed(0));
		
		var paginatorULElement = $('div.paginator-div > nav > ul.pagination');
		
		// Construct the paginator
		if (numOfPages !== undefined && numOfPages !== null) {
			
			var paginationHtml = '';
			paginationHtml += '<li class="paginator-button paginator-button-previous">\
			      <a>\
			      <span>\
			        <span aria-hidden="true">&laquo;</span>\
			      </span>\
			      </a>\
			    </li>';
			
			for (var i = 1; i <= numOfPages; i++) {
				paginationHtml += '<li class="paginator-button';
				
				paginationHtml += '" data-page-number="'+ i +'">\
				      <a>\
				      <span>' + i + '</span>\
				      </a>\
				    </li>';
			}

			paginationHtml +='<li class="paginator-button paginator-button-next">\
			      <a>\
			      <span>\
			        <span aria-hidden="true">&raquo;</span>\
			      </span>\
			      </a>\
			    </li>';

			paginatorULElement.html(paginationHtml);					
		}		
	}
	
	paginatorULElement.find('li.paginator-button').on('click', function() {
		if (!$(this).hasClass('disabled')) {
		
			var paginatorButtonToActivate = null;
			var pageNumClicked = null;			
			if ($(this).hasClass('paginator-button-previous')) {								
				paginatorButtonToActivate = $(this).siblings('.active').prev('.paginator-button');		
			} else if ($(this).hasClass('paginator-button-next')) {		
				paginatorButtonToActivate = $(this).siblings('.active').next('.paginator-button');					
			} else {	
				paginatorButtonToActivate = $(this);		
			}
			pageNumClicked = paginatorButtonToActivate.attr('data-page-number');
			constructProgrammeListing(pageNumClicked);
			paginatorButtonToActivate.siblings('li.paginator-button').removeClass('active');
			paginatorButtonToActivate.addClass('active');
			enableDisablePrevNextButtons(pageNumClicked, numOfPages);
		}
	});
	
	return {'totalNumOfResults': totalNumOfResults, 'numOfPages': numOfPages};
}

function enableDisablePrevNextButtons(pageNumClicked, numOfPages) {
	var currentPageNum = parseInt(pageNumClicked);
	var paginatorButtons = $('div.paginator-div > nav > ul.pagination > li.paginator-button');
	
	var prevButton = paginatorButtons.filter('.paginator-button-previous');
	if (currentPageNum == 1) {
		prevButton.addClass('disabled');
	} else {
		prevButton.removeClass('disabled');
	}
	
	var nextButton = paginatorButtons.filter('.paginator-button-next');
	if (currentPageNum == numOfPages) {
		nextButton.addClass('disabled');
	} else {
		nextButton.removeClass('disabled');
	}
	
	paginatorButtons.not('.disabled').css('cursor', 'pointer');
}

function filterProgrammesOnLevelOrMajor(levelOrMajorCheckboxList) {
	// What if the parameterList is empty or null...
	var levelOrMajorElementAllCheckbox = levelOrMajorCheckboxList.filter('input.major-or-level-checkbox-all');
	if (levelOrMajorElementAllCheckbox.length > 0) {
		window.programmeCollectionNarrowedDown = JSON.parse(JSON.stringify(window.programmeCollectionFetched));
	} else {
		var levelOrMajorCodeArray = [];
		var levelOrMajorCode = null;
		$.each(levelOrMajorCheckboxList, function(index, levelOrMajorCheckbox) {
			levelOrMajorCode = $(levelOrMajorCheckbox).attr('data-level-or-major-code');
			// What if the code is null/empty/non-parseable
			levelOrMajorCodeArray.push(parseInt(levelOrMajorCode));
		});
		var programmeCollection = window.programmeCollectionFetched;
		var programmeCollectionNarrowedDown = [];
		var elementIndex = window.filterType == 'Major' ? 12 : 14;
		var currentProgramme = null;
		
		for (var i = 0; i < programmeCollection.length; i++) {
			currentProgramme = programmeCollection[i];
			var currentLevelOrMajorCode = null;
			for (var j = 0; j < levelOrMajorCodeArray.length; j++) {
				currentLevelOrMajorCode = levelOrMajorCodeArray[j];
				// What if following is undefined, null, empty or non-parseable?
				if (parseInt(currentProgramme[elementIndex]) === currentLevelOrMajorCode) {
					programmeCollectionNarrowedDown.push(JSON.parse(JSON.stringify(currentProgramme)));
					break;
				}
			}
		}
		
		window.programmeCollectionNarrowedDown = programmeCollectionNarrowedDown;		
	}
	
	createProgrammeListingAndPaginator();
}
