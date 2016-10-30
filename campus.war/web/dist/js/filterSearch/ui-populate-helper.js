//20161030 PN this file contains all the functions to load the details for the filter search

$(document).ready(function() {
	$.get('PublicController', {
		CCO : 'LIST_CATEGORY_DATA'
	}, function(response) {
		alert(response);
		var categories = $("#categoryName");
		categories.find('option').remove();
		$.each(response, function(index, value) {	
			var res = value.toString();
			var data = res.split(",");
			alert(data[0]+data[1]);
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(categories)
		});
	});

	$('#categoryName').change(function(event) {
		var categoryCode = $("categoryName").val();
		$.get('PublicController', {
			categoryCode : categoryCode,
			CCO : 'LIST_MAJOR_DATA'
		}, function(jsonResponse) {
			alert(response);
//			var select = $('#player');
//			select.find('option').remove();
//			$.each(jsonResponse, function(index, value) {
//				$('<option>').val(value).text(value).appendTo(select);
//			});
		});
	});
});
