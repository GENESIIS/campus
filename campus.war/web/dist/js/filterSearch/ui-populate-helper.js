//20161030 PN this file contains all the functions to load the details for the filter search
//20161101 PN c11-criteria-based-filter-search implemented displayInstitute(), displayMajor(), displayLevel(), displayDistricts() and pad() method.
//20161102 PN c11-criteria-based-filter-search implemented getSelectedData(), getValueUsingParentTag() and addsearchData() method.
//20161102 PN c11-criteria-based-filter-search modified addsearchData() method to load data dynamically.
//20161109 PN c11-criteria-based-filter-search modified getAjaxData() and addsearchData() method to load data dynamically.
//20161110 PN c11-criteria-based-filter-search modified jQuery method to display searched data on dataTable 
//20161116 PN c11-criteria-based-filter-search adding reset functionality via check-box -WIP

/**
 * This method id to load category details
 */

$(document).ready(function() {
	$('#selectAll').attr('checked', false); // Unchecks it
	displayDetails();
	
	$('#selectAll').change(function() {
        if ($(this).prop('checked')) {
            displayDetails();
            var t = $('#example').DataTable();   
        	$.ajax({
    			url : '../../PublicController',
    			data : {
    				searchData : JSON.stringify(""),
    				CCO : 'GET_SEARCH_DATA'
    			},
    			dataType : "json",
    			success : function(response) {
    				var counter = 0;
    				t.clear().draw();
    				$.each(response.result, function(index, value) {
    					var res = value.toString();
    					var data = res.split(",");
    					counter++;
    					
    					t.row.add( [
    					            '<div class="provider-info">' +
    									'<a href="javascript:">' +
    										'<img src="../../dist/i/'+ data[6].toString().trim() +'" alt="'+ data[4].toString() +'" width="200" height="100">' +
    									'</a>' +
    								'</div>',
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
    								'</div>',
    								data[3].toString()
    					        ] ).draw( false );

    				});
    				$("#courseCount").text(" " +pad(counter, 2));
    			},
    			error : function(response) {
    				alert("Error: "+response);
    			}
    		});
      
        }
        else {
            
        }
    });
});

function displayDetails() {
	$.ajax({
		url : '../../PublicController',
		data : {
			CCO : 'LIST_CATEGORY_DATA'
		},
		dataType : "json",
		success : function(response) {
			alert(response);
			getAjaxData(response);
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
}

function getAjaxData(response) {	
 	var categories = $("#categoryName");
	categories.find('option').remove();
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(y).text(x).appendTo(categories);
	});
	
	var major = 0 ;
	var secondChoice = $("#select-item1");
	secondChoice.find('li').remove();
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		secondChoice.append('<li><a href="javascript:"><input name="major" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
		major++;
	});
	$("#majorCount").text(" " +pad(major, 2));
	
	var districtName = $("#districtName");
	districtName.find('option').remove();
	$.each(response.districtCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		var z = data[2].toString();
		$('<option>').val(z).text(x).appendTo(districtName);
	});

	var institueName = $("#institueName");
	institueName.find('option').remove();
	$.each(response.instituteCollection, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		var z = data[2].toString();
		$('<option>').val(z+"-"+y).text(x).appendTo(institueName);
	});
}

/**
 * This method id to load major details
 */
function displayMajor() {
	$('#selectAll').attr('checked', false); // Unchecks it
	var categoryCode = getSelectedData('categorylist', 'categoryName');
	
	$.get('../../PublicController', {
		categoryCode : categoryCode,
		CCO : 'LIST_MAJOR_DATA'
	}, function(response) {
		var major = 0 ;
		var secondChoice = $("#select-item1");
		secondChoice.find('li').remove();
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="major" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			major++;
		});
		$("#majorCount").text(" " +pad(major, 2));
	});	
	
}

/**
 * This method id to load CourseProvider details
 */
function displayCourseProvider() {
	$('#selectAll').attr('checked', false); // Unchecks it
	var categoryCode = getSelectedData('categorylist', 'categoryName');
	
	$.get('../../PublicController', {
		categoryCode : categoryCode,
		CCO : 'LIST_INSTITUTE_DATA'
	}, function(response) {
		var institueName = $("#institueName");
		institueName.find('option').remove();
		$.each(response.result, function(index, value) {
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
	$('#selectAll').attr('checked', false); // Unchecks it
	var categoryCode = getSelectedData('categorylist', 'categoryName');
	
	$.get('../../PublicController', {
		categoryCode : categoryCode,
		CCO : 'LIST_LEVEL_DATA'
	}, function(response) {
		var levelCount = 0 ;
		var secondChoice = $("#select-item2");
		secondChoice.find('li').remove();
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="level" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			levelCount++;
		});
		$("#levelCount").text(" " +pad(levelCount, 2));
	});

}

/**
 * This method id to load district details
 */
function displayDistricts() {
	$('#selectAll').attr('checked', false); // Unchecks it
	var instituteCode = getSelectedData('instituelist', 'institueName');
	
	$.get('../../PublicController', {
		instituteCode : instituteCode,
		CCO : 'LIST_DISTRICT_DATA'
	}, function(response) {
		var districtName = $("#districtName");
		districtName.find('option').remove();
		$.each(response.result, function(index, value) {
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


$(document).ready(function() {
	$('#selectAll').attr('checked', false); // Unchecks it
    var t = $('#example').DataTable();   
 
    $('#addRow').on( 'click', function () {
    	
    	var x = 'CATEGORY=' + getSelectedData('categorylist', 'categoryName') + '&';
    	var y = 'COURSEPROVIDER=' + getSelectedData('instituelist', 'institueName')	+ '&';
    	var z = 'MAJOR=' + getValueUsingParentTag('#select-item1 input:checked') + '&';

    	var a = 'DISTRICT=' + getSelectedData('districtlist', 'districtName');
    	var searchData = x+y+z+a;

    	$.ajax({
			url : '../../PublicController',
			data : {
				searchData : JSON.stringify(searchData),
				CCO : 'GET_SEARCH_DATA'
			},
			dataType : "json",
			success : function(response) {
				var counter = 0;
				t.clear().draw();
				$.each(response.result, function(index, value) {
					var res = value.toString();
					var data = res.split(",");
					counter++;
					
					t.row.add( [
					            '<div class="provider-info">' +
									'<a href="javascript:">' +
										'<img src="../../dist/i/'+ data[6].toString().trim() +'" alt="'+ data[4].toString() +'" width="200" height="100">' +
									'</a>' +
								'</div>',
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
								'</div>',
								data[3].toString()
					        ] ).draw( false );

				});
				$("#courseCount").text(" " +pad(counter, 2));
			},
			error : function(response) {
				alert("Error: "+response);
			}
		});
    } );
 
    // Automatically add a first row of data
    $('#addRow').click();
} );

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
