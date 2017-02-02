/**
 * 20170202 DN c131-admin-manage-banner-upload-banner-image-dn create the initial file
 * 
 */
$(document).redy(function(){
	
	displayBannerManagerPrerequistData();
	
});

/**
 * displayBannerManagerPrerequistData: function is meant to pass an ajax 
 * request to Servlet under the CCO DBPDOL-'Display Banner Page Data on Load' in order to extract
 * 
 */
function displayBannerManagerPrerequistData(){
	
	$.ajax({
		url:"../../../AdminController",
		data:{
			CCO:"DBPDOL",
		},
		dataType:"json",
		success:function(preRequistData){
			getPreRequisitPageData(preRequistData);
		},
		error:function(){
			
		},
		complete:function(){
			
		}
	});
	
	
	
}