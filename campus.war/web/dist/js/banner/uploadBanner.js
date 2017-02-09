/**
 * 20170202 DN c131-admin-manage-banner-upload-banner-image-dn create the initial file
 * 20170203 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() method created and wip
 * 20170208 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() modified by adding code to populate
 *  page and course provider data list. 
 * 	20170209 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() changed to populate course provider/page list,
 * 				and getBannerSlotsMapedToThePAge() method implemented.  	 	
 */
var selectedPageCode = '';
var adminControllerUrl = '../../../AdminController';

$(document).ready(function(){
	displayBannerManagerPrerequistData();
});

/**
 * displayBannerManagerPrerequistData: function is meant to pass an ajax 
 * request to Servlet under the CCO DBPDOL-'Display Banner Page Data on Load' in order to extract
 * 
 */
function displayBannerManagerPrerequistData(){
	
	$.ajax({
		url:adminControllerUrl,
		data:{
			CCO:"DBPDOL", //Display Banner Page Data on Load
		},
		dataType:"json",
		success:function(preRequistData){
					getPreRequisitPageData(preRequistData);
		},
		error:function(preRequistData,error,errorThrown){
			var msg = ajaxErorMessage(preRequistData,error,errorThrown);
			displayLabelMessage('displayLabel','red',msg);
		},
		complete:function(){
			
		}
	});
}

/**
 * getPreRequisitPageData() sends the initial request to the server requesting
 * the list of Featured course providers who are eligible for displaying banners.
 * Eligible in the sense decided by the COURSEPROVIDER STATUS.
 * All the pages which are designated for banner publishing will be loaded too.
 * Upon selection of a page, available banner slots will be populated too.
 * @param preRequistData 
 * 
 */
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
	
	
	// get the selected Page code
	$('#page').on('input',function(){
		var iskeyStrocksCompleted = false;
		var intrmediateTypedPageName = this.value;
		var page = $('#pageList option').
								filter(function(){
									if($(this).val()===intrmediateTypedPageName){
										iskeyStrocksCompleted = true;
										return $(this).val();
									}	
								}).text();
		selectedPageCode = page;
		
		// populating associated page slots for the page
		
		if(iskeyStrocksCompleted){
			// get page code to a hidden field
			$('#sPageCode').val(selectedPageCode);
			getBannerSlotsMapedToThePAge(selectedPageCode);
			
		}
		
	});
	
}

/**
 * method sends a request to the server with the code of the 
 * selected page and expects a list of page slots (banner slots)
 * attached to the selected page
 * @param selectedPageCode code of the selected page.
 */
function getBannerSlotsMapedToThePAge(selectedPageCode){
	
	$.ajax({
		url:adminControllerUrl,
		dataType:'jason',
		data:{
			CCO:"LSPBSLT", //LOAD SELECTED PAGE'S BANNER SLOTS
			selectedPageCode:selectedPageCode,			
		},
		success:function(pageSlots){
			
			
			
		},
		error:function(pageSlots,error,errorThrown){
			
		var msg = ajaxErorMessage(pageSlots,error,errorThrown);
		displayLabelMessage('displayLabel','red',msg);
		}
		
	});
	
	
	
	
};

/**
 * ajaxErorMessage
 * @author DJ
 * @param response response that comes from the server
 * @error error  error message comes from the server when ajax call fails
 * @errorThrown actual error thrown once ajax response fails
 */

function ajaxErorMessage(response,error,errorThrown){
	  var msg = '';
    if (response.status === 0) {
        msg = 'Not connect.\n Verify Network.';
        
    } else if (response.status == 404) {
        msg = 'Requested page not found. [404]';
    } else if (response.status == 500) {
        msg = 'Internal Server Error [500].';
    } else if (error === 'parsererror') {
        msg = 'Requested JSON parse failed.';
    } else if (error === 'timeout') {
        msg = 'Time out error.';
    } else if (error === 'abort') {
        msg = 'Ajax request aborted.';
    } else {
        msg = 'Uncaught Error.\n' + response.responseText;
    }
    return msg;
}

/**
 * displayLabelMessage(): displays an user define message text in the a modal window
 * This message either can be an error or success message.
 * @author dushantha DN
 * @param cssColour required color theme for the message to be displayed
 * @param message the required message to be displayed
 */

function displayLabelMessage(labelid,cssColour,message){
	$('#messagePopUp').modal('show'); // display the modal window
	jQuery('#'+labelid).css({'color':cssColour,'font-weight':'bold'}).html("<h2>"+message+"</h2>");
	
}
