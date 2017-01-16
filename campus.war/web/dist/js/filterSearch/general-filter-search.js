//DJ 20170116 c123-general-filter-search-course-provider-MP-dj Identify general filter search entities. 

$(document).ready(function() {
	//Identifies the search button action
	$('#addSearchData').on('click', function(event) {	
		var keyWordString=$("#keyWord").val();	
		var selectedType= $('input[name=cpRadio]:checked').val()
		
		$.ajax({
			url : '../../PublicController',
			data : {
				CCO : 'GENERAL_FILTER_SEARCH_COURSE_PROVIDERS',
				keyWordString : keyWordString,
				selectedType:selectedType
			},
			dataType : "json",
			success : function(response) {
				getProviderCodeList(response);
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
	
	//Populate course provider code List and pass code list for course provider filter search view.
	function getProviderCodeList(response) {
		var cpCodeList = response.codeList;
		//if (cpCodeList != null && cpCodeList.length > 0) {
			
			window.location.replace("/dist/partials/viewMoreCourseProviders.jsp?cpCodeList=" + cpCodeList);
			
		/*	$.ajax({
						url : '../../PublicController',
						data : {
							CCO : 'LIST_ALL_COURSE_PROVIDERS',
							cpCodeList : cpCodeList
						},
						dataType : "json",
						success : function(response) {
							getInitialPageResults(null, response);
							return true;
						},
						error : function(jqXHR, exception) {
							var msg = '';
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
					});*/
		//}
	}

});