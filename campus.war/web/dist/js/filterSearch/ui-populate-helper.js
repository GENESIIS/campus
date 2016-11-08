//20161030 PN this file contains all the functions to load the details for the filter search
//20161101 PN c11-criteria-based-filter-search implemented displayInstitute(), displayMajor(), displayLevel(), displayDistricts() and pad() method.
//20161102 PN c11-criteria-based-filter-search implemented getSelectedData(), getValueUsingParentTag() and addsearchData() method.
//20161102 PN c11-criteria-based-filter-search modified addsearchData() method to load data dynamically.
/**
 * This method id to load category details
 */

function displayCategory() {
	alert("Inside displayCategory()");
	$.get('PublicController', {
		CCO : 'LIST_CATEGORY_DATA'
	}, function(response) {
		alert("response "+ response);
		getAjaxData(response);
	});
	
}

function getAjaxData(response) {
	alert("inside function(response)");
	var categories = $("#categoryName");
	categories.find('option').remove();
	var categoryData = response[result];
	var instituteData = response[instituteCollection];
	
//	alert("Inside categoryData loop");
	$.each(categoryData, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(categories);
//		alert("Printing data...=" + data);
//		alert("Printing x and y... x=" + x + ", y=" +y);
	});


	alert("Inside instituteData loop");
	$.each(instituteData, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		var z = data[2].toString();
		$('<option>').val(z).text(x).appendTo(districtName);
		alert("Printing data...=" + data);
		alert("Printing x,y and z... x=" + x + ", y=" +y + ", z=" +z);
	});
}

/**
 * This method id to load major details
 */
function displayMajor() {
	var categoryCode = getSelectedData('categorylist', 'categoryName');
	
	$.get('PublicController', {
		categoryCode : categoryCode,
		CCO : 'LIST_MAJOR_DATA'
	}, function(response) {
		var count = 0 ;
		var secondChoice = $("#select-item1");
		secondChoice.find('li').remove();
		$.each(response, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="major" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			count++;
		});
		$("#majorCount").text(" " +pad(count, 2));
	});	
	
}

/**
 * This method id to load CourseProvider details
 */
function displayCourseProvider() {
	var categoryCode = getSelectedData('categorylist', 'categoryName');
	
	$.get('PublicController', {
		categoryCode : categoryCode,
		CCO : 'LIST_INSTITUTE_DATA'
	}, function(response) {
		var institueName = $("#institueName");
		institueName.find('option').remove();
		$.each(response, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			var z = data[2].toString();
			$('<option>').val(z+"-"+y).text(x).appendTo(institueName);
		});
	});
	
	
}

/**
 * This method id to load level details
 */
function displayLevel() {
	var categoryCode = getSelectedData('categorylist', 'categoryName');
	
	$.get('PublicController', {
		categoryCode : categoryCode,
		CCO : 'LIST_LEVEL_DATA'
	}, function(response) {
		var count = 0 ;
		var secondChoice = $("#select-item2");
		secondChoice.find('li').remove();
		$.each(response, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="level" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			count++;
		});
		$("#levelCount").text(" " +pad(count, 2));
	});

}

/**
 * This method id to load district details
 */
function displayDistricts() {
	var instituteCode = getSelectedData('instituelist', 'institueName');
	
	$.get('PublicController', {
		instituteCode : instituteCode,
		CCO : 'LIST_DISTRICT_DATA'
	}, function(response) {
		var districtName = $("#districtName");
		districtName.find('option').remove();
		$.each(response, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			var z = data[2].toString();
			$('<option>').val(z).text(x).appendTo(districtName);
		});
	});
}


function displayDetailsOnLoad() {
	displayCategory();
//	displayCourseProvider();
//	displayMajor();
//	
//	displayDistricts();	
//	addsearchData();
}

function displayDetailsOnChange() {
	displayMajor();
	displayCourseProvider();
	displayDistricts();	
}

/**
 * This method id to format numbers in base 10 format.
 */
function pad(number, length) {
    var str = '' + number;
    while (str.length < length) {
        str = '0' + str;
    }
    return str;
}

/**
 * This method is to load programs according to the search criteria.
 * @returns
 */
function addsearchData(){
	var x = 'CATEGORY=' + getSelectedData('categorylist', 'categoryName') + '&';
	var y = 'COURSEPROVIDER=' + getSelectedData('instituelist', 'institueName')	+ '&';
	var z = 'MAJOR=' + getValueUsingParentTag('#select-item1 input:checked') + '&';

	var a = 'DISTRICT=' + getSelectedData('districtlist', 'districtName');
	var searchData = x+y+z+a;

	$.get('PublicController', {
		searchData : JSON.stringify(searchData),
		CCO : "GET_SEARCH_DATA"
	}, function(response) {
		var count = 0 ;
		var dataTable = $("#example");
		dataTable.find('tr:gt(0)').remove();
		$.each(response, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			dataTable.append('<tr>' +
					'<th>' +
					'<div class="provider-info">' +
						'<a href="javascript:">' +
						'<img src="dist/i/'+ data[6].toString().trim() +'" alt="'+ data[4].toString() +'" width="200" height="100">' +
						'</a>' +
					'</div>' +
				'</th>' +
				'<th>' +
					'<div class="result-box clearfix">' +
						'<div class="course-name">' +
							'<a href="javascript:">' + data[1].toString() +
							'<span class="provider-name">' + " @"+ data[5].toString() +
							'</span>' +
							'</a>' +
						'</div>' +
						'<div class="course-info">' +
							'<p>'+ data[2].toString() + '</p>' +
						'</div>' +
					'</div>' +
				'</th>' +
				'<th>' + data[3].toString() + '</th>' +
			'</tr>');
			count++;
		});
		$("#courseCount").text(" " +pad(count, 2));
	});
	
}

/**
 * This method is to get selected checkbox values
 * @param checkboxList
 * @returns
 */
function getValueUsingParentTag(checkboxList){
	var chkArray = [];
	
	/* look for all checkboes that have a parent id called 'checkboxlist' attached to it and check if it was checked */
	$(checkboxList).each(function() {
		chkArray.push($(this).val());
	});
	
	/* we join the array separated by the comma */
	var selected;
	selected = chkArray.join(',');
	
	return selected;
}

/**
 * This method is to get selected data from datalist
 * @param listname
 * @param elementName
 * @returns {String}
 */
function getSelectedData(listname, elementName) {
	var selectedValue = "";
	var val = document.getElementById(listname);

	if (val != null) {
		var data = val.value;
		var opts = document.getElementById(elementName).childNodes;
		for (var i = 0; i < opts.length; i++) {
			if (opts[i].value === data) {
				selectedValue = opts[i].text;
				break;
			}
		}
	}
	return selectedValue;
}
