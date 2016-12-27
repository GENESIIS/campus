//20161227 DJ c52-report-banner-statistics-MP-dj  Banner stat report generation 

$(document).ready(function() {
	$.ajax({
		url : '../../ReportController',
		data : {
			CCO : 'SEARCH_VIEW_BANNER_STATISTICS'
		},
		dataType : "json",
		success : function(response) {
			getAjaxProviderData(response);
		},
		error : function(jqXHR, exception) {
			var msg = 0;
			if (jqXHR.status === 0) {
				msg = 'Not connect.\n Verify Network.';
			} else if (jqXHR.status == 404) {
				msg = 'Requested page not found. [404]';
			} else if (jqXHR.status == 500) {
				msg = 'Internal Server Error [500].';
			} else if (exception === 'timeout') {
				msg = 'Time out error.';
			} else {
				msg = 'Internal error is occurred. Please try again.';
			}
			alert(msg);
		}
	});

	$('#pagelist').bind('change', function(event) {		
		var pageCode = $('#pagelist').val();

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
				var msg = 0;
				if (jqXHR.status === 0) {
					msg = 'Not connect.\n Verify Network.';
				} else if (jqXHR.status == 404) {
					msg = 'Requested page not found. [404]';
				} else if (jqXHR.status == 500) {
					msg = 'Internal Server Error [500].';
				} else if (exception === 'timeout') {
					msg = 'Time out error.';
				} else {
					msg = 'Internal error is occurred. Please try again.';
				}
				alert(msg);
			}
		});

	});

	$('#pageSlotlist').bind('change', function(event) {		
		var pageSlotCode = $('#pageSlotlist').val();
		$.ajax({
			url : '../../ReportController',
			data : {
				CCO : 'LIST_PAGESLOT_WISE_BANNER',
				pageSlotCode : pageSlotCode
			},
			dataType : "json",
			success : function(response) {
				getPageSlotWiseBanners(response);
			},
			error : function(jqXHR, exception) {
				var msg = 0;
				if (jqXHR.status === 0) {
					msg = 'Not connect.\n Verify Network.';
				} else if (jqXHR.status == 404) {
					msg = 'Requested page not found. [404]';
				} else if (jqXHR.status == 500) {
					msg = 'Internal Server Error [500].';
				} else if (exception === 'timeout') {
					msg = 'Time out error.';
				} else {
					msg = 'Internal error is occurred. Please try again.';
				}
				alert(msg);
			}
		});

	});
	
	$('#searchList').on('click', function(event) {
		loadResultSet(event);
	});

});

function getAjaxProviderData(response) {
	var htmlstr = "";
	$.each(response.result, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option value="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#pageName').html(htmlstr);

}

function getPageWisePageSlots(response) {
	var htmlstr = "";
	$.each(response.pageSlots, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option value="' + value[0] + '">' + value[1]
					+ '</option>';
		}

	});
	$('#pageSlotName').html(htmlstr);
}

function getPageSlotWiseBanners(response) {
	var htmlstr = "";
	$.each(response.bannerDetails, function(index, value) {
		if (value != null && value.length > 0) {
			htmlstr += '<option value="' + value[0] + '">' + value[1]
					+ '</option>';
		}
	});
	$('#bannerName').html(htmlstr);
}

