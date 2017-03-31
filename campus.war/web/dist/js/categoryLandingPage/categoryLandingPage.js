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
 * 20161125 MM c5-corporate-training-landing-page-MP Modified code related to error-message displaying to make it more 
 *													loosely-coupled with the rest of the code. Now allows dynamic backgrounds
 *													according to severity of the message to be diplayed.													
 * 20161125 MM c5-corporate-training-landing-page-MP Fixed issue of a paginator button being created at the end of the paginator
 * 													when the fraction part of numOfResults/numOfResultsPerPage is > 0.5 													
 * 20161125 MM c5-corporate-training-landing-page-MP Re-factored code vis-a-vis enableDisablePrevNextButtons() function and added 
 * 													other functions changeDisplayedResultsStatInfo() and 
 * 													adjustPaginatorOnChangeOfDisplayedResults() to display a message as to what 
 * 													part of the result set is currently displayed, upon clicking of a paginator 
 * 													button
 * 20161126 MM c5-corporate-training-landing-page-MP Modified code in constructProgrammeListing() function so that course provider
 * 													images are now considered to be in a directory named with the same value as 
 * 													for code of that course provider's record in the DB table. Also changed code 
 * 													to consider that image names have the format "{courseProviderCode}_small.jpg", 
 * 													and to load default course provider image when courseProvider is one-off.
 * 20161127 MM c5-corporate-training-landing-page-MP Modified code in changeDisplayedResultsStatInfo() to make the message about
 * 													the currently displayed result set suit special scenarios such as last 
 * 													page displaying only 1 programme and total result set containing only 1 item.
 * 20161130 MM c5-corporate-training-landing-page-MP Modified code to handle an issue related to filtering area getting distorted
 * 													upon having filtering items that have names that are longer than around 25 chars
 * 20161221 MM c5-corporate-training-landing-page-MP Changed the vertical alignment value for superscripted ordinal indicator of 
 * 													item in message displayed as to what result set is being displayed currently. 
 */

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
window.messageType = {INFO: 'INFO', ERROR: 'ERROR'};
window.oneOffProviderDetails = null;

$(document).ready(function() {
	$('#message-container').hide();
	getProgrammeData();
});

function showMessages(messages, msgType) {
	
	var msgStyleClass = '';
	
	if (msgType === 'INFO') {
		msgStyleClass = 'alert-info';
	} else if (msgType === 'ERROR') {
		msgStyleClass = 'alert-danger';
	}
	
	$('#message-container').remove();
	var messagesHtml = '<div id="message-container" class="alert text-center ' + msgStyleClass + '" role="alert">';
	if (messages != undefined && messages != null && messages.length > 0) {
		messagesHtml += '<span style="font-weight: 700">' + msgType + ': </span>';
		for (var i = 0; i < messages.length; i++) {
			messagesHtml += '<span>' + messages[i] + '</span></br>';
		}
		messagesHtml += '</div>';
		$('div.course-filter-panel > div.filtering-area > div.top').after(messagesHtml);
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
				window.messages = response.messages;
				window.oneOffProviderDetails = response.oneOffProviderDetails;
				
				window.programmeCollectionNarrowedDown = response.result;
				
				if (window.programmeCollectionFetched !== undefined && window.programmeCollectionFetched !== null && window.programmeCollectionFetched.length > 0) {
					constructLevelOrMajorMenu();
					createProgrammeListingAndPaginator();
					showMessages(window.messages, window.messageType.ERROR);
				} else {
					var messages = [];
					messages.push("It seems that no programmes are available for this category at the moment.");
					messages.push("Please check back soon...");
					showMessages(messages, window.messageType.INFO);
				}
			}			
		},
		error : function(response) {
			var messageList = [];
			messageList.push("There was an error retrieving data to display from the server.");
			messageList.push("Please check that you are connected to the Internet and that you are sending the correct data.");
			showMessages(messageList, window.messageType.ERROR);
		}
	});
}

function constructLevelOrMajorMenu(){

	var levelOrMajorCollection = window.levelOrMajorCollectionFetched;
	
	//Construct the Level/Major menu on top of programme displaying area
	var levelOrMajorHtmlFragment = '';
	var levelOrMajorName = '';
	var levelOrMajorNameAdjusted = '';
	var elipsis = '...';
	var lengthOfFilterItemText = 20;
	if (levelOrMajorCollection !== undefined && levelOrMajorCollection !== null && levelOrMajorCollection.length > 0) {
		levelOrMajorHtmlFragment += '<li class="major-or-level-menu-item major-or-level-menu-item-all" title="All Programmes">\
			<input class="major-or-level-checkbox major-or-level-checkbox-all" type="checkbox" checked/>All</li>';
		$.each(levelOrMajorCollection, function(index, val) {
			levelOrMajorName = levelOrMajorNameAdjusted = val[1];
			
			if (levelOrMajorNameAdjusted.length > lengthOfFilterItemText) {
				levelOrMajorNameAdjusted = levelOrMajorNameAdjusted.substring(0, lengthOfFilterItemText + 1) + elipsis;
			}
			levelOrMajorHtmlFragment += '<li class="major-or-level-menu-item" data-level-or-major-code="' + val[0] + '" title="' + levelOrMajorName + '">\
			<input class="major-or-level-checkbox"  data-level-or-major-code="' + val[0] + '" type="checkbox">'+ levelOrMajorNameAdjusted +'</li>';
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
	var currentlyDisplayedProgInfo = constructProgrammeListing(pageNum);
	var programmeResultStats = constructPaginator();
	var paginatorListElement = $('div.paginator-div > nav > ul.pagination');
	paginatorListElement.children().eq(1).addClass('active');
	adjustPaginatorOnChangeOfDisplayedResults(currentlyDisplayedProgInfo, programmeResultStats, pageNum);
}

function constructProgrammeListing(pageNum) {

	var programmeCollection = window.programmeCollectionNarrowedDown;
	var programmeCodeToTownListMap = window.programmeCodeToTownListMapFetched;
	var courseProviderLogoPath = window.courseProviderLogoPathFetched;	
	var numOfResultsPerPage = window.numOfResultsPerPageFetched;
	var oneOffProviderDetails = window.oneOffProviderDetails;
	var logoTypeAndExtension = "_small.jpg";
	var defaultImagePrefix = 'default';
	var defaultSmallLogo = courseProviderLogoPath + '/' + defaultImagePrefix + logoTypeAndExtension; 

	var indexIndicatorOfLastProg = null;
	var indexOfFirstProgNeeded = null;
	
	if (numOfResultsPerPage != undefined && numOfResultsPerPage != null && $.isNumeric(numOfResultsPerPage)) {
		indexIndicatorOfLastProg = numOfResultsPerPage * pageNum;
		indexOfFirstProgNeeded = indexIndicatorOfLastProg - numOfResultsPerPage;
	}
	
	var programmeCollectionForCurrentPage = null;
	
	if (programmeCollection !== undefined && programmeCollection !== null && programmeCollection.length > 0) {
		programmeCollectionForCurrentPage = programmeCollection.slice(indexOfFirstProgNeeded, indexIndicatorOfLastProg);
	}	
	
	//Construct the Programme listing 
	var programmesHtmlFragment = '';
	var programmeCode = null;
	var courseProviderCode = null;
	var imgAlterTextShortName = null;
	
	if (programmeCollectionForCurrentPage !== undefined && programmeCollectionForCurrentPage !== null && programmeCollectionForCurrentPage.length > 0) {
		$.each(programmeCollectionForCurrentPage, function(index, val) {
			programmeCode = val[0];			
			programmesHtmlFragment += '<li class="course-info clearfix">';
			programmesHtmlFragment += '<div class="col-name">';
			programmesHtmlFragment += '<a href=""><h1 class="pro-name">' + val[17] + '</h1></a>';
			programmesHtmlFragment += '<div class="pro-logo">';
			
			if (val[24] === oneOffProviderDetails.name) {		
				courseProviderCode = oneOffProviderDetails.code;
				imgAlterTextShortName = "Default Provider";
			} else {				
				courseProviderCode = val[11];	
				imgAlterTextShortName = val[17];
			}			
			var logoImgPath = courseProviderLogoPath + '\\' + courseProviderCode + '\\' + courseProviderCode + logoTypeAndExtension;
			
			programmesHtmlFragment += '<a href=""><img src="' + logoImgPath + '" alt="' + imgAlterTextShortName + ' logo" onerror="this.src = \'' + defaultSmallLogo + '\'"></a>';
			programmesHtmlFragment += '</div>';
			programmesHtmlFragment += '</div>';
			programmesHtmlFragment += '<div class="col-description">';
			programmesHtmlFragment += '<p>' + val[1] + '</p>';
			programmesHtmlFragment += '</div>';
			programmesHtmlFragment += '<div class="col-location">';
			
			var townListForCurrentProgramme = programmeCodeToTownListMap[programmeCode];
			
			$.each(townListForCurrentProgramme, function(i, currentTown) {
				programmesHtmlFragment += '<a href="javascript:">' + currentTown[1] + '</a></br>';
			});
			
			programmesHtmlFragment += '</div>';
			// Printing major/Level, entry requirements
			programmesHtmlFragment += '<div class="col-duration">';
			programmesHtmlFragment += '<label>' + val[22] + '<br><span>' + val[23] + '</span></label>';
			programmesHtmlFragment += '</div>';
			programmesHtmlFragment += '</li>';
		});					
	}
	
	var programmeListElement = $('div.filter-result-table').find('ul.result-row');
	programmeListElement.html(programmesHtmlFragment);	
	
	return {'firstProgramme': indexOfFirstProgNeeded + 1, 'lastProgramme': indexIndicatorOfLastProg};
}

function constructPaginator() {

	// Validate these fields to see if they are in the required format
	var programmeCollection = window.programmeCollectionNarrowedDown;
	var numOfResultsPerPage = window.numOfResultsPerPageFetched;
	
	var paginatorULElement = $('div.paginator-div > nav > ul.pagination');
	
	var totalNumOfResults = 0;
	var numOfPages = 0;
	
	if (programmeCollection.constructor === Array) {
		totalNumOfResults = programmeCollection.length;
	}
	
	if (totalNumOfResults > 0) {
		numOfPages = (totalNumOfResults % numOfResultsPerPage > 0) ? 
				parseInt((totalNumOfResults / numOfResultsPerPage) + 1) : 
					parseInt(totalNumOfResults / numOfResultsPerPage);
		
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
	
	var programmeResultStats = {'totalNumOfResults': totalNumOfResults, 'numOfPages': numOfPages};
	
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
			var currentlyDisplayedProgInfo = constructProgrammeListing(pageNumClicked);
			paginatorButtonToActivate.siblings('li.paginator-button').removeClass('active');
			paginatorButtonToActivate.addClass('active');
			adjustPaginatorOnChangeOfDisplayedResults(currentlyDisplayedProgInfo, programmeResultStats, pageNumClicked);
		}
	});
	
	return programmeResultStats;
}

function adjustPaginatorOnChangeOfDisplayedResults (currentlyDisplayedProgInfo, programmeResultStats, pageNumClicked) {
	changeDisplayedResultsStatInfo(currentlyDisplayedProgInfo, programmeResultStats.totalNumOfResults);
	enableDisablePrevNextButtons(pageNumClicked, programmeResultStats.numOfPages);
}

function changeDisplayedResultsStatInfo (currentlyDisplayedProgInfo, totalNumOfResults) {	
	var firstProgramme = currentlyDisplayedProgInfo.firstProgramme;
	var lastProgramme = currentlyDisplayedProgInfo.lastProgramme > totalNumOfResults ? 
			totalNumOfResults : currentlyDisplayedProgInfo.lastProgramme;
	
    var resultsStatInfoHtml = '<div class="panel panel-default" style="margin-top: 30px">\
    	<div class="panel-body">\
    	Displaying \
    	<span class="label label-default">'; 
    
    if (firstProgramme === lastProgramme) {
    	var firstProgrammeNumStr = firstProgramme.toString();
    	var firstProgNumLength = firstProgrammeNumStr.length;
    	var lastDigit = firstProgrammeNumStr.charAt(firstProgNumLength - 1);
    	var digitToSuffixMap = {
    			'0': 'th',
    	    	'1': 'st',
    	    	'2': 'nd',
    	    	'3': 'rd',
    	    	'4': 'th',
    	    	'5': 'th',
    	    	'6': 'th',
    	    	'7': 'th',
    	    	'8': 'th',
    	    	'9': 'th',
    	    	};
    	resultsStatInfoHtml += firstProgramme + '<sup style="vertical-align: middle; font-size: 0.8em">' + 
    	digitToSuffixMap[lastDigit] + '</sup>';
    	
    } else {
    	resultsStatInfoHtml += firstProgramme + ' - ' + lastProgramme;
    }
    
    resultsStatInfoHtml += '</span> out of\
    	<span class="label label-default">' + totalNumOfResults + '</span> programme';
    if (totalNumOfResults > 1) {
    	resultsStatInfoHtml += 's';
    }
    resultsStatInfoHtml += '</div></div>';	
    $('.programme-results-stat-info-div').html(resultsStatInfoHtml);
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
			// What if the code is null/empty/non-parsable
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
				// What if following is undefined, null, empty or non-parsable?
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
