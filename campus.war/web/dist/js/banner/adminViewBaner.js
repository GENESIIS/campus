/**
 * 20170308 DN c81-admin-manage-banner-add-and-view-banner-dn initial adminViewBanner.js.
 * 			 implemented loadBanners()
 *  
 */

var theNewScript = document.createElement("script");
theNewScript.type = "text/javascript";
theNewScript.src = "../../dist/js/institute/validation/validation.js";

$(document).ready(function(){
	
	loadBanners();
	
});


function loadBanners(){
	
	$.ajax({
		type:'POST',
		url:'',
		data:{
			CCO					:"DABNRS", //Display All Banners
			commencingDate		:$('#startDate').val(),
			cessationDate		:$('#endtDate').val(),
			activeInactiveStatus:$('input:radio[name=bannerStatus]:checked').val()
		},
		dataType:'json',
		success:function(allBanners){
			
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