//20161030 PN this file contains all the functions to load the details for the filter search
//20161101 PN c11-criteria-based-filter-search implemented displayInstitute(), displayMajor(), displayLevel(), displayDistricts() and pad() method.
//20161102 PN c11-criteria-based-filter-search implemented getSelectedData(), getValueUsingParentTag() and addsearchData() method.

/**
 * This method id to load category details
 */
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

/**
 * This method id to load major details
 */
function displayMajor() {
	var val = document.getElementById("categorylist").value;
	var opts = document.getElementById('categoryName').childNodes;
	for (var i = 0; i < opts.length; i++) {
		if (opts[i].value === val) {
			var categoryCode = opts[i].text;
			if(categoryCode){
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
			break;
		}
	}
}

/**
 * This method id to load CourseProvider details
 */
function displayCourseProvider() {
	var val = document.getElementById("categorylist").value;
	var opts = document.getElementById('categoryName').childNodes;
	for (var i = 0; i < opts.length; i++) {
		if (opts[i].value === val) {
			var categoryCode = opts[i].text;
			if(categoryCode){
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
			break;
		}
	}
}

/**
 * This method id to load level details
 */
function displayLevel() {
	var val = document.getElementById("categorylist").value;
	var opts = document.getElementById('categoryName').childNodes;
	for (var i = 0; i < opts.length; i++) {
		if (opts[i].value === val) {
			var categoryCode = opts[i].text;
			if(categoryCode){
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
			break;
		}
	}
}

/**
 * This method id to load district details
 */
function displayDistricts() {
	$.get('PublicController', {
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
	displayMajor();
	displayCourseProvider();
	displayDistricts();	
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
	var searchData = x+y+z;

	$.ajax({
		type : "POST",
		url : 'PublicController',
		data : {
			searchData : JSON.stringify(searchData),
			CCO : "GET_SEARCH_DATA"
		},
		dataType : "json",
		success : function(data) {
			
		},
		error : function(e) {
			alert("Error " + e);
			console.log(e);
		}
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
	var val = document.getElementById(listname).value;
	var opts = document.getElementById(elementName).childNodes;
	for (var i = 0; i < opts.length; i++) {
		if (opts[i].value === val) {
			selectedValue = opts[i].text;
			break;
		}
	}
	return selectedValue;
}