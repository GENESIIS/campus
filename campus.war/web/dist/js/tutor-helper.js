/**
 * 20161122 CM c36-add-tutor-information INIT tutor-helper.jsp
 */
$(document).ready(function() {

	displayDetails();
	
});


function displayDetails() {
	alert("hii");
	$.ajax({
		url : 'TutorController',
		data : {
			CCO : 'LIST_TOWN_DATA'
		},
		dataType : "json",
		success : function(response) {
			getAjaxData(response);
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
}

function getAjaxData(response) {	
	var categories = $("#townDetails");
	categories.find('option').remove();
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(categories);
	});
}