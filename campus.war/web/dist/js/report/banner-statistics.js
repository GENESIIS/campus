//20161227 DJ c52-report-banner-statistics-MP-dj  Banner stat report generation 
var pageSlotCode = 0;
$(document).ready(function() {
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'SEARCH_VIEW_BANNER_STATISTICS'
		},
		dataType : "json",
		success : function(response) {
			loadReportBannerStatisticsView(response);
		},
		error : function(jqXHR, exception) {
			errorCodeGeneration(jqXHR, exception);
		}
	});

	$('#pagelist').bind('change', function(event) {		
		var pageName = $('#pagelist').val();
		var pageCode = 0;
		
		var option = $('#pageName').find('option');
		for (var i = 0; i < option.length; i++) {
			$('#pageName').find('option')[i].outerHTML;
			if (option[i].text == pageName) {
				pageCode = option[i].attributes[0].value;
				break;
			}
		}

		$.ajax({
			url : '../../ReportController',
			data : {
				CCO : 'LIST_PAGE_WISE_PAGESLOTS',
				pageCode : pageCode
			},
			dataType : "json",
			success : function(response) {
				getPageWisePageSlots(response);
			},
			error : function(jqXHR, exception) {
				errorCodeGeneration(jqXHR, exception);
			}
		});

	});

	$('#pageSlotlist').bind('change', function(event) {	
		var pageSlotName = $('#pageSlotlist').val();		

		var option = $('#pageSlotName').find('option');
		for (var i = 0; i < option.length; i++) {
			$('#pageSlotName').find('option')[i].outerHTML;
			if (option[i].text == pageSlotName) {
				pageSlotCode = option[i].attributes[0].value;
				break;
			}
		}		
		
		$.ajax({
			url : '../../ReportController',
			data : {
				CCO : 'LIST_PAGESLOT_WISE_ADVERTISER',
				pageSlotCode : pageSlotCode
			},
			dataType : "json",
			success : function(response) {
				getPageSlotWiseAdvertiser(response);
			},
			error : function(jqXHR, exception) {
				errorCodeGeneration(jqXHR, exception);
			}
		});

	});
	
	$('#searchList').on('click', function(event) {
		loadResultSet(event);
	});
	
	$('#clearParam').on('click', function(event) {
		clearParameters(event);
	});	

});

function loadReportBannerStatisticsView(response) {
	var htmlstr = "";
	$.each(response.result, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#pageName').html(htmlstr);

}

function getPageWisePageSlots(response) {
	var htmlstr = "";
	$.each(response.pageSlots, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#pageSlotName').html(htmlstr);
}

/*function getPageSlotWiseBanners(response) {
	var htmlstr = "";
	$.each(response.bannerDetails, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option value="' + value[0] + '">' + value[1]
					+ '</option>';
		}
	});
	$('#bannerName').html(htmlstr);
}*/
function getPageSlotWiseAdvertiser(response) {
	var htmlstr = "";
	$.each(response.advertiserDetails, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
			+ '</option>';
		}
	});
	$('#bannerProviderName').html(htmlstr);
}

function loadResultSet(event){
	
	var pageName= $('#pagelist').val();
	var pageCode=0;
	var option = $('#pageName').find('option');
	for (var i = 0; i < option.length; i++) {
		$('#pageName').find('option')[i].outerHTML;
		if (option[i].text == pageName) {
			pageCode = option[i].attributes[0].value;
			break;
		}
	}
	
	var pageSlotName= $('#pageSlotlist').val();
	var pageSlotCode=0;
	var option = $('#pageSlotName').find('option');
	for (var i = 0; i < option.length; i++) {
		$('#pageSlotName').find('option')[i].outerHTML;
		if (option[i].text == pageSlotName) {
			pageSlotCode = option[i].attributes[0].value;
			break;
		}
	}
	
	var bannerProviderName= $('#bannerProviderList').val();
	var bannerProviderCode=0;
	var option = $('#bannerProviderName').find('option');
	for (var i = 0; i < option.length; i++) {
		$('#bannerProviderName').find('option')[i].outerHTML;
		if (option[i].text == bannerProviderName) {
			bannerProviderCode = option[i].attributes[0].value;
			break;
		}
	}
	
	var startDate= $('#startdate').val();
	var endDate= $('#enddate').val();
		
	
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'REPORT_BANNER_STATISTICS',
			pageCode : pageCode,
			pageSlotCode : pageSlotCode,
			bannerProviderCode : bannerProviderCode,
			startDate : startDate,
			endDate :endDate 
		},
		dataType : "json",
		success : function(response) {
			populateResultTable(response);
		},
		error : function(jqXHR, exception) {
			errorCodeGeneration(jqXHR, exception);			
		}
		});
	
}
function getCode(){
	
}

function populateResultTable(response) {
	//$('#resultPanel').show();
	//$('#resultSetDiv').hide();
	var programmeListTable = $("#tBody");
	programmeListTable.find('tr').remove();	;
	
	var totalResultCount=0;

	$.each(response.bannerStatDetails, function(index, value) {
		$('#resultSetDiv').show();
		if (value != null && value.length > 0) {
			totalResultCount ++;
			var code = value[0].toString();
			var name = value[1].toString();
			var des = value[2].toString();
						
			var tr = '<tr>' ;
			tr += '<td>' + totalResultCount + '</td>';
			tr += '<td>' + code  + '</td>';
			tr += '<td>' + name  + '</td>';
			tr += '<td>' + des  + '</td>';			
			programmeListTable.append(tr);	
		}

	});

}

function clearParameters(event){
	$('#pagelist').val(""); 	
	$('#pageSlotlist').val(""); 	
	$('#bannerProviderList').val(""); 	
	$('#fromDate').val(" "); 
	$('#toDate').val(" "); 	
}

function errorCodeGeneration(jqXHR, exception){
	var msg = '';
	   if (jqXHR.status === 0) {
         msg = 'Not connect.\n Verify Network.';
     } else if (jqXHR.status == 404) {
         msg = 'Requested page not found. [404]';
     } else if (jqXHR.status == 500) {
         msg = 'Internal Server Error [500].';
     } else if (exception === 'timeout') {
         msg = 'Time out error.';
     }  else {
         msg = 'Uncaught Error.\n' + jqXHR.responseText;
     }	        
 alert(msg);	
}

