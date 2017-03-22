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
 */

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

var bannerArray ="";
var adminControllerUrl = '../../../AdminController';

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
	$('#filterBanners').click(function(){
		$("#bannerDisplaytbl tr").remove();
		loadBanners();
	});
	
 /*
  * In the even where the Add New Banner button is clicked
  * the page will be directed to where the banners are added 
  * by the Admin
  */
	$('#adminAddNewBanner').click(function(){
		if (confirm('Proceed ?'))
		window.location.href="/dist/partials/banner/bannerManager.jsp";
		
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
	$.ajax({
		type:'POST',
		url:adminControllerUrl,
		data:{
			CCO					:"ADDISBNRS", //Admin Display Banners
			commencingDate		:$('#startDate').val(),
			cessationDate		:$('#endtDate').val(),
			activeInactiveStatus:$('input:radio[name=bannerStatus]:checked').val()
		},
		dataType:'json',
		success:function(response){
			// call the function that populates the table based on supplied data
			// if the server reply is a success
			var cssColour='red';
			if(response.successCode===1){
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
	var isStartDateEmpty =!isempty(startDate);
	var isEndDateEmpty =!isempty(endDate);
	
	
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
		
		displayLabelMessage('messagePopUp','displayLabel','red',"Fill in the End Date to Filter Records");
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
		
		// assigning all the banner records to the global variable.
		bannerArray = allBannerRecords;
		var bannerImageWarPath = bannerWarPath;
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
				
			/*
			 * path to the image is agreed to be '/education/banner/143/143.jpg etc
			 * thus if there is any change to the folder structure should result a change to
			 * bellow url path too.
			 */
			var url ="/"+bannerImageWarPath+"/"+imgeNameComponent[0]+"/"+imageName; // 
			
			var markUp = "<tr id='rowId"+rowNumber+"'><td>"+rowNumber+"</td><td>"+advertiserName+"<hr>"+
															bannerCode+" From : "+bannerActivateDate+" |To : "+bannerDeactivateDate+" <br><br>";
			markUp = markUp +"<form  method='POST' action='bannerManager.jsp'>"+ //action='urltogo' should be specified when needs to edit the banner record.
								"<button type='submit' name='CCO' class='editRow' id='CCO' value='ADMEDTBNR'>Edit The Record</button>" +//ADMIN EDIT BANNER
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
								"<input type='hidden' id='rowNumber"+rowNumber+"' name='rowNumber' value='"+rowNumber+"'>"+ 
								
								
								
							 "</form></td>";
			markUp = markUp +"<td><img id='bnnerImage"+rowNumber+"'src='"+url+"' alt='banner-Image' style='width:200px;hight:60px'></td></tr>" ;
			jQuery("table").css('overflow-x','auto');
			jQuery("table").append(markUp);
		
		});
	
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