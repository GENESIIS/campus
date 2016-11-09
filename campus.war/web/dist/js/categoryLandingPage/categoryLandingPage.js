/**
 * 20161109 MM c5-corporate-training-landing-page-MP Initialised file with getProgrammeData()
 * 20161109 MM c5-corporate-training-landing-page-MP Added code in (document).ready() function
 */

alert("entered script");

$(document).ready(function() {
	alert("docuement ")
	getProgrammeData();
});

function getProgrammeData() {
	alert("Inside getProgrammeData()");
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

			$.each(response, function(index, val) {
				alert("index:" + index + "--> " + val);
			});
		},
		error : function(response) {
			alert("Ajax Error");
		}
	});
}