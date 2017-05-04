/**
 * 20170308 DN c81-admin-manage-banner-add-and-view-banner-dn initial adminViewBanner.js.
 * 			 implemented loadBanners()
 * 20170310 DN c81-admin-manage-banner-add-and-view-banner-dn populateBannerTable() implemented.
 *  		success: section of the ajax call of loadBanners() method has been modified. 
 * 20170313 DN c81-admin-manage-banner-add-and-view-banner-dn include a button to edit record in populateBannerTable()
 * 			and indeces  of the response array that retrieves the element has been changed. Multiple line comments are added 
 * 			to the same method. Add the "/" to the url variable populateBannerTable() method.
 * 20170314 DN c81-admin-manage-banner-add-and-view-banner-dn add comments to the document ready function and add a click
 * 			event to the '#filterBanners' button.
 * 			change loadBanners() success call error and information handling by adding css color
 * 			validateDisplayingBanners()  validating method has been implemented.
 * 20170315 DN c81-admin-manage-banner-add-and-view-banner-dn loadBanners() is added the validation method.
 * 			validateDisplayingBanners() method is fully implemented.
 * 			populateBannerTable() method where the table rows are dynamically created the Advertiser Name is added.
 * 			Document ready function is changed to navigate to add banners page when the adminAddNewBanner button is clicked.
 * 20170322 DN c83-admin-manage-banner-update-banner-info-dn populateBannerTable() has been added hidden fields to capture 
 * 			the request parameters representing banner records  
 * 20170323 DN c83-admin-manage-banner-update-banner-info-dn add two variables pageName , pageCode to extract two columns 
 * 			to be passed to the editing jsp and insert analogous hidden input names.
 * 20170403 DN c86-admin-manage-banner-search-banner-dn.The method loadBanners() is added a field for the object to be sent to the server  
 * 			via the ajax call, called bannerCode.
 * 			include a banner code validation logic to the method validateDisplayingBanners() to avoid patterns other than 123.
 * 20170405 DN c83-admin-manage-banner-update-banner-info-dn The method validateDisplayingBanners() modified to change the message End Date 
 * 			to Filter Records --> Start Date to Filter Records.
 * 20170404 TR c87 Removed rowNumber column from banner search table
 * 20170405 TR c87 modified populateBannerTable() function and added div structure to tr(row-block) 
 * 20170406 DN c86-admin-manage-banner-search-banner-dn. An global variable  displayBannerCount is added and method  populateBannerTable(allBannerRecords,bannerWarPath)
 * 			is modified to pass the total number of records to the adminViewBanner.jsp for displaying.
 * 20170407 DN c83-admin-manage-banner-update-banner-info-dn the error that initially admin view banner doesn't get loaded at first 
 * 			loading resolved using response.successCode === undefined loadBanners().
 * 			When the radio button selection changes the banners with exerted filter condition will be listed- New Requirement by TW
 * 20170418 DN c86-admin-manage-banner-search-banner-dn. The function that selecting / de-selecting the check box on 'Select All'check box change has been coded
 * 			Added on click function to activate / deactivate banner records and implemented passSelectedBannersToServer().
 * 20170419 DN c86-admin-manage-banner-search-banner-dn. implemented toggle show of activate and Deactivate banners buttons based on the selected radio button

 * 20170420 DN c83-admin-manage-banner-update-banner-info-dn . onclick event on adminAddNewBanner element opens a page on new tab -initialized.
 * 20170420 DN c86-admin-manage-banner-search-banner-dn. the success property of the method passSelectedBannersToServer() user message has been added.
 * 			inactiveBanner on click if no records are selected add an exist statement.
 *  20170504 DN 83-admin-manage-banner-update-banner-info-dn. The field tool tip comment have been enabled by implementing an event trigger.
 * 20170504 DN c86-admin-manage-banner-search-banner-dn. $(document).on('click','#activeBanner',function(event) included the return null section to give the control
 * 			to the caller if none of the records exists for activation.
 * 			The method chcekAndSwitchStatus(checkBoxId) is implemented.$(document).on('click','#inactiveBanner',function(event)) and $(document).on('click','#activeBanner',function(event))
 * 			has been modified to include the method chcekAndSwitchStatus(checkBoxId).
 * 			Merged with c86-admin-manage-banner-search-banner-dn. so that the changes that has been done in above branch is available in current branch for QA to test the corrected QA comments.
 */

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

var bannerArray ="";
var adminControllerUrl = '../../../AdminController';
var displayBannerCount =0;

$(document).ready(function(){
	
	/*
 * When the page gets loded the Banner credentials
 * for all the active users are loaded, without a filtering
 * criteria been applied.
 */
	loadBanners();
/*
 * when the user fires a click event on the button the system
 * loads the data (banner credentials accordingly).
 */	
	$('#filterBanners').click(function(event){
		//$("#bannerDisplaytbl tr").remove();
		event.preventDefault();
		loadBanners();
	});
	
 /*
  * In the even where the Add New Banner button is clicked
  * the page will be directed to where the banners are added 
  * by the Admin
  */
	$('#adminAddNewBanner').click(function(){
		//if (confirm('Do You Want to Proceed ?'))		
		window.open("/dist/partials/banner/bannerManager.jsp", '_blank');
	});
	
	/**
	 * on change the radio-block class all the banners that is
	 * having the filter criteria according to the radio button selection
	 * will be displayed.
	 */
	$('.radio-block').change(function(event){
		event.preventDefault();
		loadBanners();
		if($('input:radio[name=bannerStatus]:checked').val()=='1'){
			$('#inactiveBanner').show();
			$('#activeBanner').hide();
		} else{ // going to get inactive banners then need the Activate button to be displayed
			$('#activeBanner').show();
			$('#inactiveBanner').hide();
		}
			
		
	});
});


/**
 * loadBanners() method sends the request to the server wrapping the 
 * filtering credentials in a ajax call. The method then populates the
 * the table with extracted records from the repository.
 * If none of the criteria information has been sent to the back-end,
 * the default selected field values will be extracted from the front end
 * and all the banner records for active advertisers will be populated.
 */
function loadBanners(){
	if(!validateDisplayingBanners())
		return false;
	
	if($('input:radio[name=bannerStatus]:checked').val()=='1'){
		$('#inactiveBanner').show();
		$('#activeBanner').hide();
	}else { // going to get inactive banners then need the Activate button to be displayed
		$('#activeBanner').show();
		$('#inactiveBanner').hide();
	}
	
	$.ajax({
		type:'POST',
		url:adminControllerUrl,
		data:{
			CCO					:"ADDISBNRS", //Admin Display Banners
			commencingDate		:$('#startDate').val(),
			cessationDate		:$('#endtDate').val(),
			activeInactiveStatus:$('input:radio[name=bannerStatus]:checked').val(),
			bannerCode			:$('#bannerCodeFilter').val()
		},
		dataType:'json',
		success:function(response){
			// call the function that populates the table based on supplied data
			// if the server reply is a success
			var cssColour='red';
			if(response.successCode===1 ||response.successCode === undefined ){
				cssColour='green';
				if(response.result === "NO-DATA"||response.result.length===0)
					displayLabelMessage('messagePopUp','displayLabel',cssColour,response.message);
				populateBannerTable(response.result,response.bannerWarPath);
			} else {
				displayLabelMessage('messagePopUp','displayLabel',cssColour,response.message);
			}
			
		},
		error:function(allBanners,error,errorThrown){
			var msg = ajaxCallErorMessage(allBanners,error,errorThrown);
			displayLabelMessage('messagePopUp','displayLabel','red',msg);
		},
		complete: function(response,status){
			if(status=="success"){
				// once the call is success
			} else{
				// if it is not success
			}
		}
		
	});
	
}

/**
 * validateDisplayingBanners() validates the input fields of
 * bannerManger.jsp and returns false if any one of the fields are not confirming to the requirement.<br><br>
 *<i><u> Validation Criterion</u></i>
 * <p>
 * &#9678; The validation gets  passed if the both <i>Filtering commencing date</i><br>
 *         and the <i>filtering ending date</i> have not got values.<br>
 * &#9678; If the either fields has got a value then the method requests to provide<br>
 *         due value for the empty field<br>
 * &#9678; If both are provided values then the method checks for the validity of the values as a date,<br>
 * &#9678; If not confirming to a valid date validation fails.<br>
 * &#9678; If the <i>Filtering commencing date</i> &gt; <i>filtering ending date</i>, then,<br>
 *         the validation fails. <br>eg Filtering commencing date : 2017-03-01 <br>
 *         							filtering ending date     : 2017-02-25 <br>
 *   
 * </p>
 * @returns {Boolean} if all the validation criteria have been met then the method returns <b> true</b><br>
 * 					  else returns a <b>false</b>
 */
function validateDisplayingBanners(){
	var validationPass =false;
	
	var startDate = $('#startDate').val();
	var endDate   = $('#endtDate').val();
	var bannerCode=  $('#bannerCodeFilter').val().replace(/\s+/g, '');
	var isStartDateEmpty =!isempty(startDate);
	var isEndDateEmpty =!isempty(endDate);
	var isBannerCodeEmpty = !isempty(bannerCode);
	
	// test if the banner code is filled if so, it should be valid accepted formatted number
	// e.g. legal but NOT accepted in format -123, -.123, +.230, 0.345, .345
	// if the banner code has not been filled then, execution path
	// has been considered as a legal path.\d+[d,f,D,F]{0,1}
	
	if(!isBannerCodeEmpty){
		if(isNaN(Number(bannerCode))){ // content is not a number
			displayLabelMessage('messagePopUp','displayLabel','red',"Entered Banner Code is not a valid Number!");
			return validationPass;
		}else if(isPatternMatch(/^[-\+]{0,1}\d*\.\d+[d,f,D,F]{0,1}$/g,bannerCode)){ 
			/*
			 *  at this moment patterns 
			 *  +12.123,-12.123, +12.235D
			 *  +.123,-.123, +.235D
			 *  .456f, .456
			 *  12.231D, 12.235
			 *  have been selected
			 */ 
				displayLabelMessage('messagePopUp','displayLabel','red',"Entered Banner Code is not in a Valid Format !");
				return validationPass;
		} else if (isPatternMatch(/^[-\+]\d+[d,f,D,F]{0,1}$/g,bannerCode)){
			
			/*
			 * +12, -12, +12D, -12d, -12F,-12f
			 * patterns are captured
			 */
			displayLabelMessage('messagePopUp','displayLabel','red',"Entered Banner Code is not in a Valid Format !");
			return validationPass;
		} else if (isPatternMatch(/^\d+[d,f,D,F]$/g,bannerCode)){
			
			/*
			 * 123D, 123d, 123F,123f patterns are captured.
			 */
			displayLabelMessage('messagePopUp','displayLabel','red',"Entered Banner Code is not in a Valid Format !");
			return validationPass;
		}
	 }		
	
	
	if(!isStartDateEmpty){
		if(!isEndDateEmpty){
			
			// test if either date is  actually a date
			 if(!(isDate(startDate) & isDate(endDate))){				 
				 displayLabelMessage('messagePopUp','displayLabel','red',"Please enter valid dates ");
				 return validationPass;
			 }
			 validationPass = (compareDates(startDate,endDate,"-")<0)?!validationPass:validationPass;
			 
			 if(!validationPass){ // date comparison failed 
				 
				 displayLabelMessage('messagePopUp','displayLabel','red',"Start Date Must < End Date");
				 return validationPass;
			 } else{
				 return validationPass;
			 }
		} else{	
			
			displayLabelMessage('messagePopUp','displayLabel','red',"Fill in the End Date to Filter Records");
			return validationPass;
		}
	} else if(!isEndDateEmpty){ // start date is empty but end date is filled
		
		displayLabelMessage('messagePopUp','displayLabel','red',"Fill in the Start Date to Filter Records");
		return validationPass;
	} else{ // both date fields are empty then it's not required to validate
		
		validationPass = true;
	}
		return validationPass;	
}




/**
 * populateBannerTable() intend for populating the banner record table
 * placed in the page dynamically. 
 * @param allBannerRecords : is a array of arrays that comes from the 
 * 		  server. The inner array contains data of single row which is
 * 		  extracted from the repository. If the array does not contain any data
 * 		  it holds "NO-DATA" string.
 * @returns null if the Array of array is empty else the interested table will be
 * 			populated.
 */
function populateBannerTable(allBannerRecords,bannerWarPath){
	
	if(allBannerRecords === "NO-DATA"){
		return null;
	}
	
		$('tbody tr').remove(); // if already exist remove <tr> elements within the table body
		// assigning all the banner records to the global variable.
		bannerArray = allBannerRecords;
		var bannerImageWarPath = bannerWarPath;
		displayBannerCount =allBannerRecords.length;
		
		/*
		 * allBannerRecords forms a structure similar to bellow
		 * [[a1,b1,c1],[a2,b2,c2],[a3,b3,c3],...,[an,bn,cn]].
		 * number of elements e.g: n, will be the number of rows in the
		 * populating table
		 * rowNumber ranges from 0  to (allBannerRecords.length -1)
		 */	
		jQuery.each(allBannerRecords,function(rowNumber, aRow){
					
			/*
			 * go to a record level [a1,b1,c1] where a1,b1,c1
			 * belongs to column values of the record
			 */ 	
			
			var bannerCode = aRow[0];
			var imageName =aRow[1];
			var displayDuration =aRow[2];
			var linkType = aRow[3];
			var urlToNavigateClickingTheBaner = aRow[4];
			var pageSlotCode =aRow[5];
			var pageSlotName = aRow[6];
			var bannerActiveInactiveState = aRow[7];
			var bannerActivateDate=aRow[8];
			var bannerDeactivateDate = aRow[9];
			var advertizerCode = aRow[10];
			var imgeNameComponent = aRow[1].split(".");
			var advertiserName = aRow[11];
			var pageName = aRow[12];
			var pageCode = aRow[13];
				
			/*
			 * path to the image is agreed to be '/education/banner/143/143.jpg etc
			 * thus if there is any change to the folder structure should result a change to
			 * bellow url path too.
			 */
			var url ="/"+bannerImageWarPath+"/"+imgeNameComponent[0]+"/"+imageName; // 
			
			var markUp = "<tr class='row-block clearfix' id='rowId"+rowNumber+"'>" +
				"<td class='banner-info'><div class='banner-code'>Banner Code: "+bannerCode+"</div><div class='ad-info'><div class='dates'><label>From : "+bannerActivateDate+"</label><br><label>To : "+bannerDeactivateDate+"</label></div><div class='advertiser-name'>"+advertiserName+"</div></div>";
			markUp = markUp +"<form  method='POST' action='bannerManager.jsp'>"+
								"<div class='btn-edit-record'><button type='submit' name='CCO' class='editRow' id='CCO' value='ADMEDTBNR'>Edit The Record</button></div>" +//ADMIN EDIT BANNER
								"<input type='hidden' id='bannerCode"+rowNumber+"' name='bannerCode' value='"+bannerCode+"'>"+ 
								"<input type='hidden' id='imageName"+rowNumber+"' name='imageName' value='"+imageName+"'>"+ 
								"<input type='hidden' id='displayDuration"+rowNumber+"' name='displayDuration' value='"+displayDuration+"'>"+ 
								"<input type='hidden' id='linkType"+rowNumber+"' name='linkType' value='"+linkType+"'>"+ 
								"<input type='hidden' id='urlToNavigateClickingTheBaner"+rowNumber+"' name='urlToNavigateClickingTheBaner' value='"+urlToNavigateClickingTheBaner+"'>"+ 
								"<input type='hidden' id='pageSlotCode"+rowNumber+"' name='pageSlotCode' value='"+pageSlotCode+"'>"+ 								
								"<input type='hidden' id='pageSlotName"+rowNumber+"' name='pageSlotName' value='"+pageSlotName+"'>"+
								"<input type='hidden' id='bannerActiveInactiveState"+rowNumber+"' name='bannerActiveInactiveState' value='"+bannerActiveInactiveState+"'>"+ 
								"<input type='hidden' id='bannerActivateDate"+rowNumber+"' name='bannerActivateDate' value='"+bannerActivateDate+"'>"+ 
								"<input type='hidden' id='bannerDeactivateDate"+rowNumber+"' name='bannerDeactivateDate' value='"+bannerDeactivateDate+"'>"+ 
								"<input type='hidden' id='advertizerCode"+rowNumber+"' name='advertizerCode' value='"+advertizerCode+"'>"+ 								
								"<input type='hidden' id='advertiserName"+rowNumber+"' name='advertiserName' value='"+advertiserName+"'>"+ 
								"<input type='hidden' id='advertizerCode"+rowNumber+"' name='advertizerCode' value='"+advertizerCode+"'>"+ 
								"<input type='hidden' id='bannerUrl"+rowNumber+"' name='bannerUrl' value='"+url+"'>"+ 
								"<input type='hidden' id='pageName"+rowNumber+"' name='pageName' value='"+pageName+"'>"+
								"<input type='hidden' id='pageCode"+rowNumber+"' name='pageCode' value='"+pageCode+"'>"+
								"<input type='hidden' id='rowNumber"+rowNumber+"' name='rowNumber' value='"+rowNumber+"'>"+

							 "</form><div class='delete-check'><input class='check-one-by-one' type='checkbox' id='"+bannerCode+"'><label for='"+bannerCode+"'></label></div></td>";
			markUp = markUp +"<td class='banner-img'><div class='img-sample'><img id='bnnerImage"+rowNumber+"'src='"+url+'?'+Math.random()+"' alt='banner-Image'></div></td></tr>";
			jQuery("table").css('overflow-x','auto');
			jQuery("table").append(markUp);
			 
		});
	
	$('#bannerViewRecodsCount').html(displayBannerCount);
}


/**
 * ajaxCallErorMessage
 * @author DJ,DN re engineered 
 * @param response response that comes from the server
 * @error error  error message comes from the server when ajax call fails
 * @errorThrown actual error thrown once ajax response fails
 */
function ajaxCallErorMessage(response,error,errorThrown){
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
 * on click event the bannerCodeFilter element will be cleared
 */
$(document).on('click','#bannerCodeFilter',function(event){
	event.preventDefault();
	$('#bannerCodeFilter').val('');
});

/**
 * this function initiate the filtering the banners once the 
 * input field is entered
 */
$(document).on('keypress','#bannerCodeFilter',function(event){
	//event.preventDefault();
	if(event.keyCode==13)
		loadBanners();

});

/**
 * getSelectedBanners Method returns the id value of the<br>
 * checked check boxes
 * @author dushantha DN
 * @returns Array consisiting ids of the selected check boxes
 */
function getSelectedBanners(){
	var selectedIds=$('.check-one-by-one:checked').map(function(){
		 return this.id;
	}).get();
	
	return selectedIds;
} 


/**
 * This click event allows all the selected banners to change its
 * status from active to inactive status. It acts as a bulk inactivation
 * function
 * @author dushantha DN
 */
$(document).on('click','#inactiveBanner',function(event){
	event.preventDefault();
 // dedicated function call to capture all the selected records.
	var selectedBanners =getSelectedBanners(); 
	if(selectedBanners.length===0){
		var msg = "None of the records have been selected. Please select one or more records ";
		displayLabelMessage('messagePopUp','displayLabel','red',msg);
		return null;
	}
		
 // chcek the select all check box and unchek it
	chcekAndSwitchStatus('all-delete');
 // ajax call to transfer the data to server 
	passSelectedBannersToServer("DACT_BNR",selectedBanners,adminControllerUrl);
});




/**
 * This click event allows all the selected banners to change its
 * status from inactive to active status. It acts as a bulk inactivation
 * function
 * @author dushantha DN
 */
$(document).on('click','#activeBanner',function(event){
	event.preventDefault();
 // dedicated function call to capture all the selected records.
	var selectedBanners =getSelectedBanners(); 
	if(selectedBanners.length===0){
		var msg = "None of the records have been selected. Please select one or more records ";
		displayLabelMessage('messagePopUp','displayLabel','red',msg);
		return null;
	}
	
  // chcek the select all check box and unchek it
	chcekAndSwitchStatus('all-delete');
 // ajax call to transfer the data to server 
	passSelectedBannersToServer("ACT_BNR",selectedBanners,adminControllerUrl);
});

/**
 * This function is intended to check the status<br>
 * of the check box of which the id is passed via<br>
 *  the element id given by parameter: checkBoxId and <br>
 *  invert what ever the current status to opposit.<br>
 *  i.e if checked --> unchecked <br>
 *  if unchecked --> checked.
 * @param checkBoxId : element id e.g 'all-delete'.
 */
function chcekAndSwitchStatus(checkBoxId){
	var checkBox = $('#'+checkBoxId);
	var isChcked = checkBox.is(':checked');
	if(isChcked){
		checkBox.prop('checked',!isChcked);
	}
}



/**
 * method passes the selected banner codes to the server <br>
 * and change the banner state according to the command string
 * Then it updates the banner record table accordingly
 * @author dushantha DN
 * @param 
 * command command String whcih either activate or deactvate the banner
 * @param
 * selectedBanners : It is an array containnig all the banner codes (ids of the check boxes)
 * @param
 * urlToDirect : It is the url of the servlet or the destination to where the request passes
 * @return 
 * 	method is a mutator function and does not return anything.
 */
function passSelectedBannersToServer(command,selectedBanners,urlToDirect){
	$.ajax({
		type:'POST',
		url:urlToDirect,
		data:{
			CCO					:command,
			selectedBannerCode	:JSON.stringify(selectedBanners)
		},
		dataType:'json',
		success:function(response){
			// call the function that populates the table based on supplied data
			// if the server reply is a success
			var cssColour='red';
			if(response.successCode===1 ||response.successCode === undefined ){
				cssColour='green';
				if(response.result === "NO-DATA"||response.result.length===0)
					displayLabelMessage('messagePopUp','displayLabel',cssColour,response.message);
					loadBanners();
			} else {
				displayLabelMessage('messagePopUp','displayLabel',cssColour,response.message);
			}
		},
		error:function(allBanners,error,errorThrown){
			var msg = ajaxCallErorMessage(allBanners,error,errorThrown);
			displayLabelMessage('messagePopUp','displayLabel','red',msg);
		}
		
	});
}

/**
 * The event trigers and select all the banner records by selecting<br>
 *  all the check boxes each belongs to a record.
 *  If the check box is deselected then the individual check box for each
 *  Record will be unselected
 */
$(document).on('change','#all-delete',function(event){
	//if all-delete selected select all records else deselect all
	var isCheckBoxSelected = $('#all-delete').is(':checked');
	$('.check-one-by-one').prop('checked',isCheckBoxSelected);		
});

/**
 * this event triggers and manages the tool tips 
 * available on the page.
 */
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
