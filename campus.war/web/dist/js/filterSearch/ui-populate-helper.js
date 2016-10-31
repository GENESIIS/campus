//20161030 PN this file contains all the functions to load the details for the filter search

function displayCategory() {
	$.get('PublicController', {
		CCO : 'LIST_CATEGORY_DATA'
	}, function(response) {
		alert(response);
		var categories = $("#categoryName");
		categories.find('option').remove();
		$.each(response, function(index, value) {
			alert(value);
			select.append($('<option></option>').val(value).html(value));
		});
	});
}