/**
 * 20170308 DN c81-admin-manage-banner-add-and-view-banner-dn initial adminViewBanner.js.
 * 			 implemented loadBanners()
 * 20170310 DN c81-admin-manage-banner-add-and-view-banner-dn populateBannerTable() implemented.
 *  		success: section of the ajax call of loadBanners() method has been modified. 
 */

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

var bannerArray ="";

$(document).ready(function(){
	
	loadBanners();
	
});

/**
 * loadBanners() method sends the request to the server wrapping the 
 * filtering credentials in a ajax call. The method then populates the
 * the table with extracted records from the repository.
 * If none of the criteria information has been sent to the backend,
 * the default selected field values will be extracted from the front end
 * and all the banner records for active advertisers will be populated.
 */
function loadBanners(){
	
	$.ajax({
		type:'POST',
		url:'',
		data:{
			CCO					:"ADDISBNRS", //Admin Display Banners
			commencingDate		:$('#startDate').val(),
			cessationDate		:$('#endtDate').val(),
			activeInactiveStatus:$('input:radio[name=bannerStatus]:checked').val()
		},
		dataType:'json',
		success:function(response){
			// call the function that populates the table based on supplied data
			if(response.result.length===0)
				displayLabelMessage('messagePopUp','displayLabel','green',"No records to display !");
			populateBannerTable(response.result,response.bannerWarPath);
			
		},
		error:function(allBanners,error,errorThrown){
			var msg = ajaxErorMessage(pageSlots,error,errorThrown);
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
 * populateBannerTable() intend for populating the banner record table
 * placed in the page dynamically. 
 * @param allBannerRecords : is a array of arrays that comes from the 
 * 		  server. The inner array contains data of single row which is
 * 		  extracted from the repository.
 * @returns null if the Array of array is 
 */
function populateBannerTable(allBannerRecords,bannerWarPath){
	
	if(allBannerRecords.length === 0){
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
			
			var BannerCode = aRow[0];
			var imageName =aRow[1];
			var bannerActivateDate=aRow[1];
			var bannerDeactivateDate = null;
			var url =bannerImageWarPath+"/"+imageName;
			var markUp = "<tr id='rowId"+rowNumber+"'><td> From : "+bannerActivateDate+" |To : "+bannerDeactivateDate+" <br><br>";
					markUp = markUp +"<form  method='POST'>"+ //action='urltogo' should be specified when needs to edit the banner record.
										"<input type='submit' name="" class='editRow' id='CCO' value='ADMEDTBNR'>" +//ADMIN EDIT BANNER
										"<input type='hidden' id='bnrHidden"+rowNumber+"' name='bnrHidden"+rowNumber+"' value='"+BannerCode+"'>"+ 
									 "</form></td>";
					markUp = markUp +"<td><img id='bnnerImage"+rowNumber+"'src='"+url+"' alt='banner-Image' style='width:200px;hight:60px'></td></tr>" ;
				
			jQuery("table tbody").append(markUp);
			
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


