//DJ 20170116 c123-general-filter-search-course-provider-MP-dj Identify general filter search entities. 

$(document).ready(function() {
	
	$('#addSearchData').on('click', function(event) {
		
		$(this).val();
			var keyWordString=" ";
		$.ajax({
			url : '../../PublicController',
			data : {
				CCO : 'FILTER_SEARCH_COURSE_PROVIDERS',
				keyWordString : keyWordString
			},
			dataType : "json",
			success : function(response) {
				
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

});