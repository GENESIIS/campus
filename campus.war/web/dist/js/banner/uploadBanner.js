/**
 * 20170202 DN c131-admin-manage-banner-upload-banner-image-dn create the initial file
 * 20170203 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() method created and wip
 * 20170208 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() modified by adding code to populate
 *  page and course provider data list. 
 * 	20170209 DN c131-admin-manage-banner-upload-banner-image-dn getPreRequisitPageData() changed to populate course provider/page list,
 * 				and getBannerSlotsMapedToThePAge() method implemented.  
 *              method populateDataList() implemented and refactor other code to reuse the said method when populating data lists.
 *              ajaxErorMessage() method refactor to provide better information. 
 *  20170213 DN c131-admin-manage-banner-upload-banner-image-dn add function to transfer the banner page information to back end
 *  20170215 DN c131-admin-manage-banner-upload-banner-image-dn Implement the logic to pass banner and banner information
 *              in 02 ajax calls to the server sendBannerPaageFieldInputs() is implemented
 *              #openModalUpload in onclick event a modal pops out to up load banner image.
 *   20170217 DN c131-admin-manage-banner-upload-banner-image-dn add variable urlMiniWebOrPage to the onclick event to dispatch the banner image	
 *   20170224 DN c131-admin-manage-banner-upload-banner-image-dn displayLabelMessage() method signature has been changed 
 *   			total message (error/success) displaying sequence changed in all ajax request calls.
 *   20170227 DN c131-admin-manage-banner-upload-banner-image-dn validateUploadBannerEmbedData() is created.
 */

/*
 * Include the validation.js to the uploadBanners.js
 */
var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

var selectedPageCode = '';
var selectedAdvertiserCode ='';
var selectedBannerSlotCode ='';
var adminControllerUrl = '../../../AdminController';
var bannerImageName="";

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
		type:"POST",
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
			displayLabelMessage('messagePopUp','displayLabel','red',msg);
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
		selectedAdvertiserCode = page;
		
		// populating associated page slots for the page
		
		if(iskeyStrocksCompleted){
			// get page code to a hidden field
			$('#sAdvertiserCode').val(selectedAdvertiserCode);
			
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
		
		// populating associated banner slots for the page
		
		if(iskeyStrocksCompleted){
			// get page code to a hidden field
			$('#sPageCode').val(selectedPageCode);
			
			// load the relevant page slot for page selected from data base.
			getBannerSlotsMapedToThePage(selectedPageCode);
			
			
		}
		
	});
	
// filter and display the selected Banner slots when user type in
	$('#slot').on('input',function(){
		var iskeyStrocksCompleted = false;
		var intrmediateTypedSlotName = this.value;
		var page = $('#slotList option').
								filter(function(){
									if($(this).val()===intrmediateTypedSlotName){
										iskeyStrocksCompleted = true;
										return $(this).val();
									}	
								}).text();
		selectedBannerSlotCode = page;
		
		// once the selection is made assign the code to the hidden field		
		if(iskeyStrocksCompleted){
			// get page code to a hidden field
			$('#sSlotCode').val(selectedBannerSlotCode);
			
		}
		
	});	

}

/**
 * method sends a request to the server with the code of the 
 * selected page and expects a list of page slots (banner slots)
 * attached to the selected page
 * @param selectedPageCode code of the selected page.
 */
function getBannerSlotsMapedToThePage(selectedPageCode){
	
	$.ajax({
		type:"POST",
		url:adminControllerUrl,
		dataType:"json",
		data:{
			CCO:"LSPBSLT", //LOAD SELECTED PAGE'S BANNER SLOTS
			selectedCodeOfThePage : selectedPageCode			
		},
		success: function(pageSlots){
			
			populateDataList(pageSlots.result,"slotList");
			
		},
		error: function(pageSlots,error,errorThrown){
		var msg = ajaxErorMessage(pageSlots,error,errorThrown);
		displayLabelMessage('messagePopUp','displayLabel','red',msg);
		
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
 * @author DJ,DN re engineered 
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
 * @param messagePopUpId the id of the modal popUp where the label is embedded.
 * @param cssColour required color theme for the message to be displayed
 * @param message the required message to be displayed
 */
function displayLabelMessage(messagePopUpId,labelid,cssColour,message){
	$('#'+messagePopUpId).modal('show'); // display the modal window
	jQuery('#'+labelid).css({'color':cssColour,'font-weight':'bold'}).html("<h2>"+message+"</h2>");
	
}

/**
 *  this event gets fired once the <I>upload </I> button of the banner page
 *  triggers and it collects all the information from the page and
 *  passes to server. 
 *  <b>Note:</b> It's a must to fill all the data mentioned specially the 
 *  radio buttons to retrieve correct selection other than the undefined 
 *  java script value.
 *  Method perform the front end validation before it commence the server 
 *  request ajax call.
 *  @author dushantha DN
 */
$(document).on('click','#uploadBbutton', function(event){
	
	var isValidationSuccess = false;
    
    $('#bannerModalClose').hide();
    
	/*
	 * Extracting Banner page data
	 */
    var banerImage=$('#file-select').prop('files')[0];
	var advertiserCode= $('#sAdvertiserCode').val();
	var codeOfSelectedPage= $('#sPageCode').val();
	var bannerSlotCode=$('#sSlotCode').val();
	var displayDusration= $('#duration').val();
	var banerToBeActive=$('input:radio[name=bannerEnable]:checked').val(); // validate for 'undefined'
	var bannerPublishingDate= $('#startDate').val();  // e.g bannerPublishingDate = "2017-02-14" 
	var bannerPublishingEndDate= $('#endtDate').val();
	var urlMiniWebOrPage = $('input:radio[name=urlspecifier]:checked').val();
	var urlToBeDirectedOnBannerClick=$('#bannerDispatchingUrl').val();
	
	// adding required  banner <form> fields to an array
	var BannerFieldInputValues =[];
	BannerFieldInputValues.push(advertiserCode);
	BannerFieldInputValues.push(codeOfSelectedPage);
	BannerFieldInputValues.push(bannerSlotCode);
	BannerFieldInputValues.push(displayDusration);
	BannerFieldInputValues.push(banerToBeActive);
	BannerFieldInputValues.push(bannerPublishingDate);
	BannerFieldInputValues.push(bannerPublishingEndDate);
	BannerFieldInputValues.push(urlMiniWebOrPage);
	BannerFieldInputValues.push(urlToBeDirectedOnBannerClick);
	
	//TODO run the validation check here for the page
	
	isValidationSuccess=true;
	
	if(isValidationSuccess){
		
		//if validation passes then create the form data
		var formData = new FormData();
		formData.append("file",banerImage);
		
		event.stopPropagation(); 
	    event.preventDefault(); 
		
	 // ajax call to transfer banner Image to server end
		$.ajax({
			type:"POST",
			dataType:"JSON",
			url : adminControllerUrl+"?CCO=UBIMBA", //UPLOAD BANNER IMAGE TO TEMP FOLDER
			data : formData,
			cache: false,
			contentType : false,
			processData : false,
			success: function(response){
				var cssColour='red';
				if(response['successCode']==1){
					BannerFieldInputValues.push(response['bannerImageName']); // adding the banner image name
					cssColour='green';
				}
				displayLabelMessage('bannerUploadPopUp','bannerDisplayLabel',cssColour,response['message']);
				
			},
			error: function(pageSlots,error,errorThrown){
				var msg = ajaxErorMessage(pageSlots,error,errorThrown);
				/*
				 * close the image uploading modal and opens the 
				 * message pop up modal and display the error
				 */
		
				$('#bannerUploadPopUp').modal('hide');
				displayLabelMessage('messagePopUp','displayLabel','red',msg);
				
				},
			complete: function(response,status){ 
				/*
				 * the close span will be shown : either fails or success
				 * user should have the ability to close the modal window
				 */
				$('#bannerModalClose').show();
				if(status=="success"){
					$('#uploadBbutton').prop('disabled',true);
					sendBannerPaageFieldInputs(BannerFieldInputValues,true);
				} else{
					
					// fire the data field clear function
					alert("fire the data field clear function");
					$(':input').val('');
				}
				
			}
		});
		
	}
	
	
});

function validateUploadBannerEmbedData(){
	var validationPass =false;
	
	if (!(isFieldFilled(isStringHasValiCharsAndLength($('#advertiser').val(),
			/^([a-zA-Z]+)([a-zA-Z]+){0,}$/g), "Advertiser Field",
			"advertiserInfor")))
		return validationPass;
	if (!(isFieldFilled(isStringHasValiCharsAndLength($('#page').val(),
			/^([a-zA-Z]+)([a-zA-Z]+){0,}$/g), "page Field", "pageInfor")))
		return validationPass;
	if(!(isFieldFilled(isStringHasValiCharsAndLength($('#slot').val(),
			/^([a-zA-Z]+)([a-zA-Z]+){0,}$/g), "page Field", "advertizingSlotInfor")))
		return validationPass;
	if(!(isFieldFilled(isStringHasValiCharsAndLength(),/^[0-9]+$/g,"displayDurationInfor")))
		return validationPass;
	
}


/**
 * sendBannerPaageFieldInputs method passes the input field values to server
 * side.
 * @param formFieldArray : all the fields which are on the bannerManager.jsp
 * 		  other than the banner image to be uploaded are contained.
 * @param elegibleToProceed : a boolean flag which depicts id the operation is safe
 *  	  to be executed. If false the banner data will not be passed to the back end.
 * @author dushantha DN
 * @since 20170216 v 1.0
 */
function sendBannerPaageFieldInputs(formFieldArray,elegibleToProceed ){
	
if(elegibleToProceed){
		
		//if validation passes then create the form data		
		var jasonBanner = {
				'advertiserCode' :	formFieldArray[0],
				'codeOfSelectedPage':formFieldArray[1],
				'bannerSlotCode':formFieldArray[2],
				"displayDusration":formFieldArray[3],
				"banerToBeActive":formFieldArray[4],
				"bannerPublishingDate":formFieldArray[5],		
				"bannerPublishingEndDate":formFieldArray[6]	,
				"urlMiniWebOrPage" :formFieldArray[7],
				"urlToBeDirectedOnBannerClick":formFieldArray[8],
				"bannerImageName":formFieldArray[9]
		};
		// ajax call to transfer data to server end
		
		$.ajax({
			type:"POST",
			dataType:"json",
			url : adminControllerUrl, 
			data : {
					CCO:"UFBCR",//UPLOAD FULL BANNER CREDENTIALS
				jsonData:JSON.stringify(jasonBanner)
			},
			//cache: false,
			//contentType : false,
			//processData : false,
			success: function(response){
				var cssColour='red';
				if(response['successCode']==1){
					cssColour='green';
				}
				$('#bannerUploadPopUp').modal('hide');
				displayLabelMessage('messagePopUp','displayLabel',cssColour,response['message']);	
				
			},
			error: function(pageSlots,error,errorThrown){
				var msg = ajaxErorMessage(pageSlots,error,errorThrown);
				$('#bannerUploadPopUp').modal('hide');
				displayLabelMessage('messagePopUp','displayLabel','red',msg);
				
				}
		});
		
	}
}

/**
 * this method fires and opens up the modal window which dedicats for
 * uploading the banner Image. The functionality has made available once
 * the document is ready
 * @author dushantha DN
 * @since 20170216 v 1.0
 */

$( document ).ready(function() {	
	$("#openModalUpload").click(function(e){
		$('#uploadBbutton').prop('disabled',false); // if dissabled make the button enabled
		$('#bannerUploadPopUp').modal('show');
		$('#bannerDisplayLabel').html(""); // clear message label content
		e.preventDefault();
	});
	
	
});

/**
 * this method clears the input field data 
 * placed on the current page
 */
$('#bannerPageClearField').on('click',function(){
	$(':input').val('');
});











