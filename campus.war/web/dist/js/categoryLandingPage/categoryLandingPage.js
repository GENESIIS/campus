/**
 * 20161109 MM c5-corporate-training-landing-page-MP Initialised file with getProgrammeData()
 * 20161109 MM c5-corporate-training-landing-page-MP Added code in (document).ready() function
 * 20161110 MM c5-corporate-training-landing-page-MP Added code to display programme data retrieved
 * 20161110 MM c5-corporate-training-landing-page-MP Modified code to properly display the town list for each programme 
 * 20161110 MM c5-corporate-training-landing-page-MP Implemented pagination controls for the programme list
 */

//alert("entered script");
window.programmeCollectionFetched = null;
window.levelOrMajorCollectionFetched = null;
window.programmeCodeToTownListMapFetched = null;
window.numOfPagesFetched = null;
window.pageNumFetched = null;
window.contextDeployCourseLogoPathFetched = null;
window.contextDeployLogoPathFetched = null;

$(document).ready(function() {
	getProgrammeData(1);
	constructLevelOrMajorMenu();
	constructProgrammeListing();
	constructPaginator();
});

function getProgrammeData(pageNum) {
	var categoryId = $('#categoryId').val(); // This element is expected to be present in the JSP
	// IMPORTANT: these values must be validated to be of numeric type before further steps are executed 
	categoryId = 3; // Hard-coded for the moment
	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			CCO : 'LIST_CATEGORY_PROGRAMMES',
			category : categoryId,
			pageNum : pageNum
		},
		dataType : "json",
		async : false,
		success : function(response) {
			alert("Ajax Success");
			
			if (response !== undefined && response !== null) {
				window.programmeCollectionFetched = response.result;
				window.levelOrMajorCollectionFetched = response.levelOrMajorCollection;
				window.programmeCodeToTownListMapFetched = response.programmeCodeToTownListMap;
				window.numOfPagesFetched = response.numOfPages;
				window.pageNumFetched = response.pageNum;	
				window.contextDeployCourseLogoPathFetched = response.contextDeployCourseLogoPath;
				window.contextDeployLogoPathFetched = response.contextDeployLogoPath;			
			}			
		},
		error : function(response) {
			alert("Ajax Error");
		}
	});
}

function constructLevelOrMajorMenu(){

	var levelOrMajorCollection = window.levelOrMajorCollectionFetched;
	
	//Construct the Level/Major menu on top of programme displaying area
	var levelOrMajorHtmlFragment = '';
	
	if (levelOrMajorCollection !== undefined && levelOrMajorCollection !== null) {
		levelOrMajorHtmlFragment += '<li class="major-or-level-menu-item major-or-level-menu-item-all"><a href="javascript:">All</a></li>';
		$.each(levelOrMajorCollection, function(index, val) {
			levelOrMajorHtmlFragment += '<li class="major-or-level-menu-item" data-level-or-major-code="' + val[0] + '"><a href="javascript:">'+ val[1] +'</a></li>';
		});					
	}
	
	var levelOrMajorListElement = $('.filtering-area').find('ul.list-inline');
	levelOrMajorListElement.html(levelOrMajorHtmlFragment);	
	
	levelOrMajorListElement.find('li.major-or-level-menu-item').on('click', function() {
		var codeOfLevelOrMajorClicked = $(this).attr('data-level-or-major-code');
		filterProgrammesOnLevelOrMajor(codeOfLevelOrMajorClicked);
	});
}

function constructProgrammeListing() {

	var programmeCollection = window.programmeCollectionFetched;
	var programmeCodeToTownListMap = window.programmeCodeToTownListMapFetched;
	var contextDeployLogoPath = window.contextDeployLogoPathFetched;
	
	//Construct the Programme listing 
	var programmesHtmlFragment = '';
	
	if (programmeCollection !== undefined && programmeCollection !== null) {
		$.each(programmeCollection, function(index, val) {						
			programmesHtmlFragment += '<li class="course-info clearfix">';
			programmesHtmlFragment += '<div class="col-name">';
			programmesHtmlFragment += '<a href=""><h1 class="pro-name">' + val[18] + '</h1></a>';
			programmesHtmlFragment += '<div class="pro-logo">';
			
			var logoImgPath = contextDeployLogoPath + val[22] + "/" + val[16];
			
			programmesHtmlFragment += '<a href=""><img src=' + logoImgPath + ' alt="' + val[18] + ' logo"></a>';
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

function constructPaginator() {

	// Construct the paginator
	var numOfPages = parseInt(window.numOfPagesFetched);
//	numOfPages = 2;
	var pageNumReceived = parseInt(window.pageNumFetched);
	
	if ((numOfPages !== undefined && numOfPages !== null) && 
			(pageNumReceived !== undefined && pageNumReceived !== null)) {
		
		var paginationHtml = '';
		paginationHtml += '<li class="disabled paginator-button paginator-button-previous">\
		      <a href="#">\
		      <span>\
		        <span aria-hidden="true">&laquo;</span>\
		      </span>\
		      </a>\
		    </li>';
		
		for (var i = 1; i <= numOfPages; i++) {
			paginationHtml += '<li class="paginator-button';
			
			if (i === pageNumReceived) {
				paginationHtml += ' active';
			}
			
			paginationHtml += '" data-page-number="'+ i +'">\
			      <a href="#">\
			      <span>' + i + '</span>\
			      </a>\
			    </li>';
		}

		paginationHtml +='<li class="paginator-button-next">\
		      <a href="#">\
		      <span>\
		        <span aria-hidden="true">&raquo;</span>\
		      </span>\
		      </a>\
		    </li>';
		
		var paginatorListElement = $('div.paginator-div > nav > ul.pagination');
		paginatorListElement.html(paginationHtml);					
	}
	
	paginatorListElement.find('li.paginator-button').on('click', function() {
		var pageNumClicked = $(this).attr('data-page-number');
		getProgrammeData(pageNumClicked);
	});
}

function filterProgrammesOnLevelOrMajor(levelOrMajorCode) {
	alert("Level or Major clicked! Code = " + levelOrMajorCode);
}