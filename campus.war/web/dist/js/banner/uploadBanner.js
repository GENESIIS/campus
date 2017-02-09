/**
 * 20170202 DN c131-admin-manage-banner-upload-banner-image-dn create the initial file
 * 20170203 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() method created and wip
 * 20170208 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() modified by adding code to populate
 *  page and course provider data list. 
 * 	20170209 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() changed to populate course provider/page list,
 * 				and getBannerSlotsMapedToThePAge() method implemented.  
 *              method populateDataList() implemented and refactor other code to reuse the said method when populating data lists.
 *              ajaxErorMessage() method refactor to provide better information. 	 	
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
	
	/*
	 * Initially populate the data list of all the advertisers
	 * who are featured course providers
	 * 
	 */
	populateDataList(preRequistData.result,'advertiserList');
	
	/*
	 * populate the pages data list with retrieved data from back end
	 */
	populateDataList(preRequistData.bannerPages,'pageList');
	
	// filter the advertiser on key input
	$('#advertiser').on('input',function(){
		var iskeyStrocksCompleted = false;
		var intrmediateTypedPageName = this.value;
		var page = $('#advertiserList option').
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
			$('#sAdvertiserCode').val(selectedPageCode);
			
		}
		
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
			
			// load the relevant page slot for page selected.
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
		dataType:"json",
		data:{
			CCO:"LSPBSLT", //LOAD SELECTED PAGE'S BANNER SLOTS
			selectedCodeOfThePage : selectedPageCode			
		},
		success: function(pageSlots){
			alert("INside the success call");
			populateDataList(pageSlots.result,"slotList");
			
		},
		error: function(pageSlots,error,errorThrown){
		var msg = ajaxErorMessage(pageSlots,error,errorThrown);
		displayLabelMessage('displayLabel','red',msg);
		
		}
		
	});
	
};


/**
 * Populates the data list, The data list to be populate must accept a response 
 * which is of the form {[a,b],[a1,b1],[a2,b2]} where first element being the code which
 * is the text of the <option> and second 
 * element being the value of the <option> part of the data list.
 * @param response what comes as the ajax success call
 * @param elementId data list 'id'.
 */

function populateDataList(responseAttribute,elementId){
	
	// get the  data list element wrapper set
	var elementWrapperSet = $('#'+elementId);
	// visually remove all the option element
	elementWrapperSet.find('option').remove();
	
	/* use the response and process the banner slot list
	* this brings the collection that contains the Collection<collection<String>>
	*which sends from the server as an Array, then iterate over, the array gets the form 
	* {[a,b],[a1,b1],[a2,b2]}
	*/
	$.each(responseAttribute,function(index,value){
		var record= value.toString();
		var recordArray =record.split(","); // convert the string to an Array
		
		var recordCode = recordArray[0].toString();
		var recordName = recordArray[1].toString();
		//create option list on the fly and attach to the data list
		$('<option>').val(recordName).text(recordCode).appendTo(elementWrapperSet);
	});
}

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
        msg = 'The XMLHttpRequest client has been created, but the open() method hasn\'t been called yet.';
    }else{
    	msg = error +": "+errorThrown;
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
