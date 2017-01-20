/**
 * DJ 20161123 c6-list-available-institutes-on-the-view  Load top rated /top view course providers
 */

$(document).ready(function() {
		
	$.ajax({
		url : '../../PublicController',
		data : {
			CCO : 'LIST_TOP_COURSE_PROVIDERS'			
		},
		dataType : "json",
		success : function(response) {			
			 getAjaxData(response);
		},
		error : function(jqXHR, exception) {			
			var msg = '';
			if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 500) {
	            msg = 'Internal Server Error [500].';
	        }  else if (exception === 'timeout') {
	            msg = 'Time out error.';
	        } else {
	            msg = 'Internal error is occurred. Please try again.';
	        }          
	        alert(msg);
		}
	});
});

function getAjaxData(response) {
	
	if(response.tRCProviders!=undefined && response.tRCProviders!=null && response.tRCProviders.length==0)	{
		$("#toprate").hide();	
	}
	if(response.result !=undefined && response.result!=null && response.result.length==0)	{
		$("#topview").hide();	
	}
	var topRatedCProviders = $("#tRCProviders");
	$.each(response.tRCProviders,	function(index, value) {
		if(value!=null && value.length>0){
		var x = value[0].toString();	
		var y = value[1].toString();	
		var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";		
		 topRatedCProviders.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' " title="' + y +'"/></div> </a></div> </li>');
		}
	
	});
	
	var topViewedCProviders = $("#tVCProviders");
	$.each(response.result,	function(index, value) {
		if(value!=null && value.length>0){	
		var x = value[0].toString();	
		var y = value[1].toString();	
		var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";	
		topViewedCProviders.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' " title="' + y +'"/></div> </a></div> </li>');
		}
	});	
}