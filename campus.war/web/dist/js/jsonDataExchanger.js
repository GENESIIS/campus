//20161121  DN C18-student-signup-without-using-third-party-application-dn 
//created the jsonDataExchanger.js in order to facilitate generalization all the JSON data  exchanging and ajax call.



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
			//alert(response.result);
			document.getElementById('messsage').innerHTML = response.result;
		},
		error : function(e) {
			 alert("Error " + e);
			console.log(e);
		}
	});	
}

