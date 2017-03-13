/**
 * 20170309 AS CAM-142 sampleTesting.js created for testing 
 * 20170313 AS CAM-142 ajaxTesting response modify
 */

function ajaxTesting(){
	var testData = "testing data";
	
	var jsonData = {
			"testData" : testData
		};
		$.ajax({

			url : '/StudentController',
			method : 'POST',
			data : {
				jsonData : JSON.stringify(jsonData),
				CCO : "TEST_AJAX"
				
			},
			dataType : "json",
			success : function(response) {
				
			if(response.redirectURL != null){
				window.location = response.redirectURL;
			}else{
				
			}
			
			},
			
			complete: function(response, status) {

			},
			error : function(e) {
				 alert("Error " + e);
				console.log(e);
			}
		});
	
}