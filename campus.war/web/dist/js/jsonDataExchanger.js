//20161121  DN C18-student-signup-without-using-third-party-application-dn 
//created the jsonDataExchanger.js in order to facilitate generalization all the JSON data  exchanging and ajax call.
//20161218 DN CAMP:18 enhanced the error option with many possible error options.
//20161218 DN CAMP:18 include the delaying and redirecting function to success:option depend on error code.



/**
 * jsonDataExchange method handles method uses to transfer the data to the server
 * asynchronously.
 * @author Dushantha DN
 * @param httpMethod method to be used in data transferring
 * @param transferPageUrl is the back end destination the request is meant to sent
 * @param commandCode Command Code for the Command Factory
 * @param dataCategory e.g. "jason" type of the data been sent
 */

function jsonDataExchange(jsonObject,httpMethod,transferPageUrl,commandCode,dataCategory){

	$.ajax({
		type: httpMethod,
		url : transferPageUrl,
		data:{
			jsonData : JSON.stringify(jsonObject),
			CCO : commandCode
		},
		dataType : dataCategory,
		success : function(response) {
			
			if(response['successCode']==='1'){
				setTimeout( function(){
					window.location.replace("studentLoginPage.jsp"); //this name may have to change depend on actual location of the page "Student Login"
					}, 3000);
				jQuery('#displayLabel').html("<h2>"+response['message']+"</h2>");
			} else{
				jQuery('#displayLabel').css('color','red').html("<h2>"+response['message']+"</h2>"); // if the account didn't createit's an error
			}
			
		},
		error : function(response,error,errorThrown) {
			
			  var msg = '';
		        if (response.status === 0) {
		            msg = 'Not connect.\n Verify Network.';
		        } else if (response.status == 404) {
		            msg = 'Requested page not found. [404]';
		        } else if (response.status == 500) {
		            msg = 'Internal Server Error [500].';
		        } else if (error === 'parsererror') {
		            msg = 'Requested JSON parse failed.';
		        } else if (error === 'timeout') {
		            msg = 'Time out error.';
		        } else if (error === 'abort') {
		            msg = 'Ajax request aborted.';
		        } else {
		            msg = 'Uncaught Error.\n' + response.responseText;
		        }
			jQuery('#displayLabel').css('color','red').html("<h2>"+msg+"</h2>");			
		}
	});	
}

