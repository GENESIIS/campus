//20161111 PN c1-campus-landing-page implemented a method to display categories on landing page.
//20161115 PN c1-campus-landing-page added functional comments into the methods.
//20161115 PN c1-campus-landing-page: modified the category image path.
//20161214 PN CAM-1:displayCategories
//20170103 PN CAM-137: displayCategoryDetails(response) method modified by removing string split.

/**
 * This ajax call is to load categories on landing page loading.
 */
$(document).ready(function() {
		$.ajax({
			url : '/PublicController',
			data : {
				CCO : 'LIST_CATEGORY_DATA'
			},
			dataType : "json",
			success : function(response) {
				displayCategoryDetails(response);
			},
			error : function(response) {
				alert("Error: "+response);
			}
		});
});

/**
 * This method is to display available main categories within the Landing page.
 * @param response
 * @returns
 */
function displayCategoryDetails(response){
	var count = 0 ;
	var mainCategoryList = $("#mainCategoryList");
	mainCategoryList.find('li').remove();
	$.each(response.result, function(index, value) {
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