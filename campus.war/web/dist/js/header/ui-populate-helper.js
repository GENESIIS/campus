//20161109 JH c7-higher-education-landing-page-MP ui-populate-helper.js created
//20161116 JH c7-higher-education-lanidng-page-MP change displayCategory() me

/**
 * This method is to load category details
 */

window.programmeCollection = null;

function getCategoryData() {

	$.ajax({
		url : '/PublicController',
		method : 'POST',
		data : {
			'CCO' : 'LIST_CATEGORY_DATA'
		},
		dataType : "json",
		async : false,
		success : function(response) {

			if (response !== undefined && response !== null) {
				window.programmeCollection = response.result;

				alert(programmeCollection);
			}
		},
	});
}
