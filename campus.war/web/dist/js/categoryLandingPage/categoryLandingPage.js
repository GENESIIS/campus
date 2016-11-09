/**
 * 20161109 MM c5-corporate-training-landing-page-MP Initialised file with getProgrammeData()
 * 20161109 MM c5-corporate-training-landing-page-MP Added code in (document).ready() function
 */

//alert("entered script");

$(document).ready(function() {
	getProgrammeData();
});

function getProgrammeData() {
	$.ajax({
		url : '../../PublicController',
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
				
				if (levelOrMajorCollection !== undefined && levelOrMajorCollection !== null) {
					levelOrMajorHtmlFragment =+ '<li><a href="javascript:">All</a></li>';
					$.each(levelOrMajorCollection, function(index, val) {
						levelOrMajorHtmlFragment =+ '<li><a href="javascript:">'+ val[0] + ', '+ val[1] +'</a></li>';
					});					
				}
				
				var levelOrMajorListElement = $('.filtering-area').find('ul.list-inline');
				levelOrMajorListElement.html(levelOrMajorHtmlFragment);				
			}			
			
			$.each(response, function(index, val) {
				alert("index:" + index + "--> " + val);
			});
		},
		error : function(response) {
			alert("Ajax Error");
		}
	});
}