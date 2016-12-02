/**
 * This Java script file used to help with admin functions author JH 20161201
 * 
 */

$(document).ready(function() {
	arrangeUI();
});
var message = document.getElementById("usermessage").value;

function arrangeUI() {
	document.getElementById("logoPanel").style.display = "none";

}

function getProviderType() {

	var status = false;
	var radioValue = $("input[name='featured-oneoff']:checked").val();
	var statusValue = $("input[name='providerStatus']:checked").val();
//	if (radioValue != "") {
//		if (radioValue == "featured") {
//			commandCode = "ADD_FEATURED_COURSE_PROVIDER";
//		}
//		if (radioValue == "one-off") {
//			commandCode = "ADD_ONE_OFF_COURSE_PROVIDER";
//		}
//		alert(commandCode);
//
//	} else {
//		messgae = "please select a course provider type";
//	}
	
	var form = $('#basicForm');
	var formDate = $(form).serialize();
		$.ajax({
			url : '/AdminController',
			method : 'POST',
			data : formData ,
			dataType : "json",
			async : false,
			success : function(response) {

				if (response !== undefined && response !== null) {
				//	message = response.userMessage;
					var registerId = response.result;
					
					alert("success"  + registerId);
					document.getElementById("usermessage").html = message;
					// document.getElementById("logoPanel").style.display
					// = "block";
					// document.getElementById("basicForm").style.display
					// = "none";

				}
			},
		});
}