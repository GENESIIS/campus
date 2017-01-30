//20161227 DJ c52-report-banner-statistics-MP-dj  Banner stat report generation 

$(document).ready(function() {
	/*
	 * This ajax call load the initial search interface for banner statistic report generation.
	 */
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
	
	/*
	 * Identify selected page and load page slots for particular page. 
	 */

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


	/*
	 * Identify selected page slot and advertisers for particular page slot. 
	 */
	$('#pageSlotlist').bind('change', function(event) {		
		var pageSlotName = $('#pageSlotlist').val();
		var pageSlotCode = 0;

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
	
	/*
	 * validate toDate>from date
	 */	
	$('#toDate').on('click', function(event) {		
		var fromDate= $('#fromDate').val();		
		document.getElementById("toDate").setAttribute("min", fromDate);	
	});
	/*
	 * trigger Search button click. 
	 */
	$('#searchList').on('click', function(event) {
		loadResultSet(event);
	});
	
	/*
	 * trigger Clear button click. 
	 */
	$('#clearParam').on('click', function(event) {
		clearParameters(event);
	});	

});

/*
 * This method loadReportBannerStatisticsView() populate data for  initial search interface for banner statistic report generation.
 */
function loadReportBannerStatisticsView(response) {		
	$('#resultPanel').hide();
	var htmlstr = "";
	$.each(response.result, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#pageName').html(htmlstr);
	
	$('#pageSlotlist').prop("disabled", true);
	$('#bannerProviderList').prop("disabled", true);

}

function getPageWisePageSlots(response) {
	$('#pageSlotlist').prop("disabled", false);
	$('#errorPageList').text("");
	var htmlstr = "";
	$.each(response.pageSlots, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#pageSlotName').html(htmlstr);
}


function getPageSlotWiseAdvertiser(response) {
	$('#bannerProviderList').prop("disabled", false);
	var htmlstr = "";
	$.each(response.advertiserDetails, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option val="' + value[0] + '">' + value[1]
			+ '</option>';
		}
	});
	$('#bannerProviderName').html(htmlstr);
}

/*
 * This method loadResultSet() identify input parameters for report search.
 */
function loadResultSet(event){
	//$('#resultSetDiv').hide();
	$('#resultPanel').hide();
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
	//Validate Page list
	if(!pageCode>0){
		$('#errorPageList').text("Please select a page");
		document.getElementById('errorPageList').style.color = "red";
		return false;
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
	
	
	var regex=new RegExp("([0-9]{4}[-](0[1-9]|1[0-2])[-]([0-2]{1}[0-9]{1}|3[0-1]{1})|([0-2]{1}[0-9]{1}|3[0-1]{1})[-](0[1-9]|1[0-2])[-][0-9]{4})");
	
	var fromDate= $('#fromDate').val();    
    if(!regex.test(fromDate)){
    	$('#errorFromDate').text("Please enter valid From Date");
		document.getElementById('errorFromDate').style.color = "red";
		return false;
    }else{
    	$('#errorFromDate').text("");
    }
    
    
    var toDate= $('#toDate').val();
    if(!regex.test(toDate)){
    	$('#errorToDate').text("Please enter valid To Date");
		document.getElementById('errorToDate').style.color = "red";
		return false;
    }else{
    	$('#errorToDate').text("");
    }    
   
	if (fromDate> toDate) {		
		$('#errorToDate').text("Invalid Date Range! From Date cannot be after To Date!");
		document.getElementById('errorToDate').style.color = "red";
		return false;
	}
	
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'REPORT_BANNER_STATISTICS',
			pageCode : pageCode,
			pageSlotCode : pageSlotCode,
			bannerProviderCode : bannerProviderCode,
			fromDate : fromDate,
			toDate :toDate 
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

/**
 * This method populateResultTable() manipulate search data list to a display table.
 */
function populateResultTable(response) {
	$('#displayErrorMessage').show();
	var errorMessageList = $("#displayErrorMessage");
	errorMessageList.find('li').remove();
	$.each(response.message, function(index, value) {
		if (value != null && value.length > 0) {
			var msg = value.toString();
			errorMessageList.append("<li>"+msg+"</li>");
		}		
	});
	
	$('#resultPanel').show();
	$('#resultSetDiv').hide();
	var programmeListTable = $("#tBody");
	programmeListTable.find('tr').remove();
	
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
	if (totalResultCount > 0) {
		$('#totalResultsCount').text("Result Count " +totalResultCount);
	} else {
		$('#totalResultsCount').text(
				"No results found for selected search criteria");
	}

}

/**
 * This method clearParameters() clear load form.
 */
function clearParameters(event){
	$('#resultPanel').hide();
	$('#pagelist').val(""); 	
	$('#pageSlotlist').val(""); 	
	$('#bannerProviderList').val(""); 
	$('#pageSlotlist').prop("disabled", true);
	$('#bannerProviderList').prop("disabled", true);
	$('#fromDate').val(" "); 
	$('#toDate').val(" ");
	$('#errorPageList').text("");
	$('#errorFromDate').text("");
	$('#errorToDate').text("");	
	$('#displayErrorMessage').hide();	
}
/**
 * This method errorCodeGeneration() manipulate with errors.
 */
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

