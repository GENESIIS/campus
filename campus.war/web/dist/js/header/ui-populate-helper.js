/**
 * Created by tharaka on 11/18/2016.
 */
//20161109 JH c7-higher-education-landing-page-MP ui-populate-helper.js created
//20161116 JH c7-higher-education-landing-page-MP change displayCategory() method
//20161117 JH c7-higher-education-landing-page-MP displayCategories() method created
//20161125 JH c7-higher-education-landing-page-MP solve servlet path error in common header loading error
//20161205 JH c7-higher-education-landing-page-MP modify javascript function to load category data without onload function
//20170203 PN CAM-137: added landing page category set displaying JavaScript in here.
/**
 * This method is to load category details
 */

window.onload = function(){ 
	getCategoryData();
};

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
                    singleCategoryElement += '<li><form action="/PublicController" method="POST">';
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
    
    var count = 0 ;
	var mainCategoryList = $("#mainCategoryList");
	mainCategoryList.find('li').remove();
	$.each(categoryCollectionList, function(index, value) {
		mainCategoryList.append('<li>'+
                '<div class="category-box clearfix">'+
                '<div class="category-name text-center clearfix">'+
                    '<a href="javascript:">'+value[1].toString()+'</a>'+
                '</div>'+
                '<div class="course-info clearfix">'+
                    '<div class="image pull-left">'+
                        '<img class="center-block" src="../../education/general/category/image/'+value[0].toString()+'.jpg" alt="">'+
                    '</div>'+
                    '<div class="description pull-right">'+
                        '<p class="text-justify">'+value[2].toString().replace(/##/g , ",")+'</p>'+
                        '<a href="javascript:" class="pull-right">Show More >> </a>'+
                    '</div>'+
                '</div>'+
            '</div>'+
        '</li>');
		count++;
	});
}

/**
 * This method is to display available main categories within the Landing page.
 * @param response
 * @returns
 */
function displayCategoryDetails(response){
	
}