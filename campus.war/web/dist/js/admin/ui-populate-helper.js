/**
 * This Java script file used to help with admin functions
 * author JH
 * 20161201
 * 
 */

$(document).ready(function() {
	arrangeUI();
});
window.message = document.getElementById("usermessage").value;

function arrangeUI(){
	document.getElementById("logoPanel").style.display = "none";

}

function getProviderType(){

	var commandCode;
	var status = false;
	var radioValue = $("input[name='featured-oneoff']:checked").val();
	var statusValue = $("input[name='providerStatus']:checked").val();
	if (radioValue != "") {
		if (radioValue == "featured") {
			commandCode = "ADD_FEATURED_COURSE_PROVIDER";
		}
		if (radioValue == "one-off") {
			commandCode = "ADD_ONE_OFF_COURSE_PROVIDER";
		}
		if (radioValue == "Tutor") {
			commandCode = "ADD_ONE_OFF_COURSE_PROVIDER";
		}
		alert(commandCode);
		status = true;

	}else{
		window.messgae ="please select a course provider type";
	}
	var courseProviderStatus ;
	if(statusValue != ""){
		if(statusValue == "active"){
			courseProviderStatus = "active";
		}
		if(statusValue == "inactive"){
			courseProviderStatus = "inactive";
		}if(statusValue == "pending"){
			courseProviderStatus = "pending";
		}
		status = true;
	}else{
		document.getElementById("").innerHTML = "please select the ";
	}
	
	if (status == true) {
		alert("true");
		$
				.ajax({
					url : '/AdminController',
					method : 'POST',
					data : {
						'CCO' : commandCode
					},
					dataType : "json",
					async : false,
					success : function(response) {

						if (response !== undefined && response !== null) {
							window.message = response.userMessage;

							alert("success" + window.message);
							document.getElementById("usermessage").html = "success";
							// document.getElementById("logoPanel").style.display
							// = "block";
							// document.getElementById("basicForm").style.display
							// = "none";

						}
					},
				});
	}
}