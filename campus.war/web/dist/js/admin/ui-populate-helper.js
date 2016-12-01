/**
 * This Java script file used to help with admin functions
 * author JH
 * 20161201
 * 
 */

$(document).ready(function() {
	arrangeUI();
});
window.message = null;

function arrangeUI(){
	document.getElementById("logoPanel").style.display = "none";

}

function getProviderType(){

	var commandCode;
	var radioValue = $("input[name='featured-oneoff']:checked").val();
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
							document.getElementById("usermessage").html(window.message);
						//	document.getElementById("logoPanel").style.display = "block";
						//	document.getElementById("basicForm").style.display = "none";
							
						}
					},
				});
	}

}