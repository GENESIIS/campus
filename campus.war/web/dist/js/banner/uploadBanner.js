/**
 * 20170202 DN c131-admin-manage-banner-upload-banner-image-dn create the initial file
 * 20170203 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() method created and wip
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
			CCO:"DBPDOL", //Display Banner Page Data on Load
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


function getPreRequisitPageData(preRequistData){
	
	//set the advertiser list get all element collection from the data list
	var advertiserList=$('#advertiserList');
	
	// visually remove the <option> from the data list wrapper set
	advertiserList.find('option').remove();
	
	// this brings the collection that contains the Collection<collection<String>>
	//which sends from the server as an Array, then iterate over, the array gets the form 
	// {[a,b,..],[a1,b1,..],[a2,b2,..]}
	
	$.each(preRequistData.result,function(index,value){  
		var valueBoundToIndex = value.toString();
		var innerArrayAsString= valueBoundToIndex.split(",");
		
		var advertiserCode = innerArrayAsString[0].toString();
		var advertiser = innerArrayAsString[1].toString();		
		$('<option>').val(advertiser).text(advertiserCode).appendTo(advertiserList);
	});
	
	/*
	 * populate the pages data list with retrieved data from back end
	 */
	
	var pageListContainingTheBanner = $('#pageList');
	pageListContainingTheBanner.find('option').remove();
	
	$.each(preRequistData.bannerPages,function(index,value){
		var pageToboundIndex = value.toString();
		var innerArrayAsString= pageToboundIndex.split(",");
		
		var codeOfThePageContainingBanner = innerArrayAsString[0].toString();
		var pageContainingTheBanner = innerArrayAsString[1].toString();
		$('<option>').val(pageContainingTheBanner).text(codeOfThePageContainingBanner).appendTo(pageListContainingTheBanner);
		
	});
	
	
	
}