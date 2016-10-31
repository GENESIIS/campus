//20161030 PN this file contains all the functions to load the details for the filter search

function displayCategory() {
	$.get('PublicController', {
		CCO : 'LIST_CATEGORY_DATA'
	}, function(response) {
		var categories = $("#categoryName");
		categories.find('option').remove();
		$.each(response, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(y).text(x).appendTo(categories);
		});
	});
}

function displayMajor() {
	var val = document.getElementById("categoryist").value;
	var opts = document.getElementById('categoryName').childNodes;
	for (var i = 0; i < opts.length; i++) {
		if (opts[i].value === val) {
			// An item was selected from the list!
			// yourCallbackHere()
			alert(opts[i].text);
			var categoryCode = opts[i].text;
			$( '<div id="outer">Outer Div Content<div id="inner">Inner Div Content</div></div>' ).appendTo( '#dropItem1' );
//			$.get('PublicController', {
//				categoryCode : categoryCode,
//				CCO : 'LIST_MAJOR_DATA'
//			}, function(response) {
//				alert(response);	
//				var dropItem1 = $("#dropItem1");
//				$.each(response, function(index, value) {
//					  // create a new div element 
//					  // and give it some content 
//					$( '<div class="select-item row-fluid"><a href="javascript:"><input name="Category" type="checkbox"></a><label>Pre </label></div>' ).appendTo('#dropItem1');
//				});
//			});
			break;
		}
	}
	
//	var dropItem1 = $("#dropItem1");
	
}

function addRow(dropItem1) {
	  // create a new div element 
	  // and give it some content 
	  var newDiv = document.createElement("div"); 
	  var newContent = document.createTextNode("Hi there and greetings!"); 
	  newDiv.appendChild(newContent); //add the text node to the newly created div. 

	  // add the newly created element and its content into the DOM 
	  var currentDiv = document.getElementById("div1"); 
	  document.body.insertBefore(newDiv, currentDiv);
}

function removeRow(input) {
    document.getElementById('content').removeChild( input.parentNode );
}

function displayDistricts() {
	$.get('PublicController', {
		CCO : 'LIST_DISTRICT_DATA'
	}, function(response) {
		var categories = $("#categoryName");
		categories.find('option').remove();
		$.each(response, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(y).text(x).appendTo(categories);
		});
	});
}

