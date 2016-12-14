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
		error : function() {
			alert("error");
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
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();	
		var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";		
		topRatedCProviders.append('<li><a href="javascript:"><img height="100" width="100" src="'+ logo + ' " /> </a> </li>');
	});
	
	var topViewedCProviders = $("#tVCProviders");
	$.each(response.result,	function(index, value) {
		var res = value.toString();
		var data = res.split(",");	
		var x = data[0].toString();		
		var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";		
		topViewedCProviders.append('<li><a href="javascript:"><img height="100" width="100" src="'+ logo + ' " /> </a> </li>');
		
	});	
}