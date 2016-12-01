//20161109 JH c7-higher-education-landing-page-MP ui-populate-helper.js created
//20161116 JH c7-higher-education-landing-page-MP change displayCategory() method
//20161117 JH c7-higher-education-landing-page-MP displayCategories() method created
//20161201 JH c39-add-course-provider added document ready function 

/**
 * This method is to load category details
 */

$( document ).ready(function() {
	getCategoryData() ;
});

window.categoryCollection = null;

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
				window.categoryCollection = response.result;

				displayCategories();
			}
		},
	});
}

function displayCategories() {
	var categoryCollectionList = window.categoryCollection;
	var singleCategoryElement = '';

	singleCategoryElement += '<ul class="list-inline" >';
	if (categoryCollectionList !== undefined & categoryCollectionList !== null) {
		$
				.each(
						categoryCollectionList,
						function(index, value) {
							singleCategoryElement += '<li><form action="PublicController" method="POST">';
							singleCategoryElement += '<button type="submit" name="CCO" id="CCO" class="btn btn-info navbar-btn" value="LIST_CATEGORY_LANDING_PAGE">'
									+ value[1] + '</button>';
							singleCategoryElement += '<input type="hidden" name="categoryId" id="categoryId" value="'
									+ value[0] + '" />';
							singleCategoryElement += '</form></li>';

						});
	}
	singleCategoryElement += '</ul>';
	var categoryNames = $("#category-list");
	categoryNames.html(singleCategoryElement);

}
