//20161118 DJ c17-provider-criteria-based-filter-search Load the details for the provider filter search
//20161124 DJ c17-provider-criteria-based-filter-search Identified front end input selections 

$(document).ready(function() {

	var catCode = $("#catCode").val();
	$.ajax({
		url : '../../PublicController',
		data : {
			CCO : 'LIST_ALL_COURSE_PROVIDERS',
			categoryCode : catCode
		},
		dataType : "json",
		success : function(response) {
			getAjaxData(response);
		},
		error : function() {
			alert("error");
		}
	});
});

function getAjaxData(response) {

	var totalCount = 0;

	var providerChoice = $("#providerList");
	$.each(response.result,	function(index, value) {
						var res = value.toString();
						var data = res.split(",");
						var x = data[0].toString();
						var y = data[1].toString();
						var z = data[2].toString();
						var logo = "../../education/provider/logo/" + x + "/"+ x + ".png";
						providerChoice.append('<li><a href="javascript:"><img height="100" width="100" src="'+ logo + ' " /> </a> </li>');
					});

	var providers = $("#providers");
	$.each(response.result, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		$('<option>').val(x).text(y).appendTo(providers);
	});

	var catCount = 0;
	var secondChoice = $("#select-category");
	secondChoice.find('li').remove();
	$.each(response.categoryList, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		secondChoice.append('<li><a href="javascript:"><input class="categoryClass" onclick="categoryClick()" id="category' + x	+ '" type="checkbox" value="' + x + '"></a>' + y + '</li>');
		catCount++;
	});
	totalCount += catCount;
	$("#catCount").text(" " + catCount);

	var cpTypeCount = 0;
	var secondChoice = $("#select-cpType");
	secondChoice.find('li').remove();
	$.each(	response.cpTypeList,function(index, value) {
	var res = value.toString();
	var data = res.split(",");
	var x = data[0].toString();
	var y = data[1].toString();
	secondChoice.append('<li><a href="javascript:"><input class="cpTypeClass" id="cpType'+ x + '" type="checkbox" value="'	+ x	+ '"></a>' + y + '</li>');
	cpTypeCount++;
	});
	totalCount += cpTypeCount;
	$("#cpTypeCount").text(" " + cpTypeCount);

	var majorCount = 0;
	var secondChoice = $("#select-major");
	secondChoice.find('li').remove();
	$.each(response.majorList, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		secondChoice.append('<li><a href="javascript:"><input class="majorClass" id="major' + x + '" type="checkbox" value="' + x + '"></a>' + y + '</li>');
		majorCount++;
	});
	totalCount += majorCount;
	$("#majorCount").text(" " + majorCount);

	var levelCount = 0;
	var secondChoice = $("#select-level");
	secondChoice.find('li').remove();
	$.each(response.levelList, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		secondChoice.append('<li><a href="javascript:"><input class="levelClass" id="level' + x + '" type="checkbox" value="' + x + '"></a>' + y + '</li>');
		levelCount++;
	});
	totalCount += levelCount;
	$("#levelCount").text(" " + levelCount);

	$("#totalCount").text(" " + totalCount);

	var districtName = $("#districtName");
	$.each(response.districtList, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var x = data[0].toString();
		var y = data[1].toString();
		var z = data[2].toString();
		$('<option>').val(y).text(z).appendTo(districtName);
	});

	
	
	$('#categoryClass').on('click', function(event) {
		alert("categoryClass " );
	});
	
	
	
	$('#addSearchData').on('click', function(event) {
		$(this).val();
		var selectAll = $('#selectAll').is(':checked');
		var categoryAll = $('#categoryAll').is(':checked');
		var cpTypeAll = $('#cpTypeAll').is(':checked');
		var majorAll = $('#majorAll').is(':checked');
		var levelAll = $('#levelAll').is(':checked');
		var districtCode = $('#districtlist').val();
		
		//Category checks	
		var categorySelection = $('#select-category').find('.categoryClass:checked');
		var categoryCodes = [];
		for (var i = 0; i < categorySelection.length; i++) {
			var code = categorySelection[i].value;
			categoryCodes.push(code);
		}
			
		//Course Provider Type checks	
		var cpTypeSelection = $('#select-cpType').find('.cpTypeClass:checked');
		var cpTypeCodes = [];
		for (var i = 0; i < cpTypeSelection.length; i++) {
			var code = cpTypeSelection[i].value;
			cpTypeCodes.push(code);
		}

		//Major checks	 		 
		var majorSelection = $('#select-major').find('.majorClass:checked');
		var majorCodes = [];
		for (var i = 0; i < majorSelection.length; i++) {
			var code = majorSelection[i].value;
			majorCodes.push(code);
		}

		//Level checks			 
		var levelSelection = $('#select-level').find('.levelClass:checked');
		var levelCodes = [];
		for (var i = 0; i < levelSelection.length; i++) {
			var code = levelSelection[i].value;
			levelCodes.push(code);
		}

		var mainAreasMap = 0;
		// var selectAllMap=0;			 
		// if(selectAll==true){
		// selectAllMap={'searchData':selectAll};				
		//alert("selectAll "); 
		// }else {
		mainAreasMap = {
			'cpTypeAll' : cpTypeAll,
			'majorAll' : majorAll,
			'levelAll' : levelAll

		};		

		$.ajax({
			url : '../../PublicController',
			data : {
				categoryCodes : categoryCodes,
				districtCode : districtCode,
				mainAreasMap : mainAreasMap,
				cpTypeCodes : cpTypeCodes,
				majorCodes : majorCodes,
				levelCodes : levelCodes,
				CCO : 'LIST_FILTER_SEARCH_COURSE_PROVIDERS'
			},
			dataType : "json",
			success : function(response) {
				alert("success add click");
				populateAjaxResponse(response);
			},
			error : function() {
				alert("error");
			}
		});

	});	

}

function populateAjaxResponse(response) {	
	alert("populateAjaxResponse");
	
	var totalCount = 0;
	var providerChoice = $("#providerList");
	providerChoice.find('li').remove();
	$.each(response.result,	function(index, value) {
	var res = value.toString();
	var data = res.split(",");
	var x = data[0].toString();
	var y = data[1].toString();
	var z = data[2].toString();
	var logo = "../../education/provider/logo/" + x + "/"+ x + ".png";
	providerChoice.append('<li><a href="javascript:"><img height="100" width="100" src="'+ logo + ' " /> </a> </li>');
	});
	
}

function categoryClick(){
	alert("categoryClick");
	var catCode=0;
	var categorySelection = $('#select-category').find('.categoryClass:checked');	
	for (var i = 0; i < categorySelection.length; i++) {
		var code = categorySelection[i].value;
		if(code>0){
			catCode=code;	
		}		
	}
	
	$.ajax({
		url : '../../PublicController',
		data : {
			categoryCode:catCode,
			CCO : 'LIST_FILTER_SEARCH_CATEGORY_TYPES'
		},
		dataType : "json",
		success : function(response) {
			alert("success category click ");			
		},
		error : function() {
			alert("error");
		}
	});
	
	
}





