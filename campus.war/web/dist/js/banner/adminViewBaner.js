/**
 * 20170308 DN c81-admin-manage-banner-add-and-view-banner-dn initial adminViewBanner.js.
 * 			 implemented loadBanners()
 * 20170310 DN c81-admin-manage-banner-add-and-view-banner-dn populateBannerTable() implemented.
 *  		success: section of the ajax call of loadBanners() method has been modified. 
 * 20170313 DN c81-admin-manage-banner-add-and-view-banner-dn include a button to edit record in populateBannerTable()
 * 			and indeces  of the response array that retrieves the element has been changed. Multiple line comments are added 
 * 			to the same method. Add the "/" to the url variable populateBannerTable() method.
 * 
 */

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

var bannerArray ="";
var adminControllerUrl = '../../../AdminController';

$(document).ready(function(){
	
	loadBanners();
	
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
			
			if(response.successCode===1){
				
				if(response.result === "NO-DATA")
					displayLabelMessage('messagePopUp','displayLabel','green',"No records to display !");
				populateBannerTable(response.result,response.bannerWarPath);
			} else {
				displayLabelMessage('messagePopUp','displayLabel','green',response.message);
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
		
	//$("#bannerDisplaytbl tr").remove();
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
			var bannerActivateDate=aRow[8];
			var bannerDeactivateDate = aRow[9];
			var imgeNameComponent = aRow[1].split(".");
			
			/*
			 * path to the image is agreed to be '/education/banner/143/143.jpg etc
			 * thus if there is any change to the folder structure should result a change to
			 * bellow url path too.
			 */
			var url ="/"+bannerImageWarPath+"/"+imgeNameComponent[0]+"/"+imageName; // 
			
					var markUp = "<tr id='rowId"+rowNumber+"'><td>"+rowNumber+"</td><td> "+bannerCode+" From : "+bannerActivateDate+" |To : "+bannerDeactivateDate+" <br><br>";
					markUp = markUp +"<form  method='POST'>"+ //action='urltogo' should be specified when needs to edit the banner record.
										"<button type='submit' name='CCO' class='editRow' id='CCO' value='ADMEDTBNR'>Edit The Record</button>" +//ADMIN EDIT BANNER
										"<input type='hidden' id='bnrHidden"+rowNumber+"' name='bnrHidden"+rowNumber+"' value='"+bannerCode+"'>"+ 
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