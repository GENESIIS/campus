//20161109 JH c7-higher-education-landing-page-MP ui-populate-helper.js created
//20161116 JH c7-higher-education-landing-page-MP change displayCategory() method
//20161117 JH c7-higher-education-landing-page-MP displayCategories() method created

/**
 * This method is to load category details
 */

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

				alert(categoryCollection);
			displayCategories();
			}
		},
	});
}

function displayCategories() {
	var categoryCollectionList = window.categoryCollection;
	var singleCategoryElement = '';

	if (categoryCollectionList !== undefined & categoryCollectionList !== null) {
		$.each(categoryCollectionList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			alert(value);

			singleCategoryElement += '<li>' + value[1] + '</li>';
		});
	}

	var categoryNames = $("#category-list");
	categoryNames.html(singleCategoryElement);

}
