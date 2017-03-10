/**
 * 20170309 AS CAM-142 sampleTesting.js created for testing 
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
			
				window.location = response.redirectURL;
			},
			
			complete: function(response, status) {
//				if(status==200){
//					
//				}else{
//				window.location = response.redirectURL;
//				}
			},
			error : function(e) {
				 alert("Error " + e);
				console.log(e);
			}
		});
	
}