//20161118 DJ c17-provider-criteria-based-filter-search Load the details for the provider filter search
//20161124 DJ c17-provider-criteria-based-filter-search Identified front end input selections 
//20161221 DJ c17-provider-criteria-based-filter-search Identified checkbox tick untick on level/major/providertype 

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
			getInitialPageResults(catCode,response);
		},
		error : function(jqXHR, exception) {			
			var msg = '';
	        if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 500) {
	            msg = 'Internal Server Error [500].';
	        } else if (exception === 'parsererror') {
	            msg = 'Requested JSON parse failed.';
	        } else if (exception === 'timeout') {
	            msg = 'Time out error.';
	        } else if (exception === 'abort') {
	            msg = 'Ajax request aborted.';
	        } else {
	            msg = 'Uncaught Error.\n' + jqXHR.responseText;
	        }	        
	        alert(msg);
		}
	});
		
/*	
	$("#filterAZ").click(function(){
		alert(" radio button click filterA-Z");
		
		var res = response.result.sort(function(a,b){
		    if(a.Name < b.Name) return -1;
		    if(a.Name > b.Name) return 1;
		    return 0;
		});	
	});
	
	$("#filterZA").click(function(){
		alert(" radio button click filterZ-A");
	});*/
});

function getInitialPageResults(catCode,response) {

	var totalCount = 0;

	var providerChoice = $("#providerList");
	$.each(response.result,	function(index, value) {
						var res = value.toString();
						var data = res.split(",");
						var x = data[0].toString();
						var y = data[1].toString();
						
						var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";						
						//providerChoice.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' "/></div><div class="provider-name text-center"><h2>'+y+'</h2> </div> </a></div> </li>');
						providerChoice.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' "  title="'+ y +'" /></div> </a></div> </li>');
						
					});



	var catCount = 0;
	var eduCategoryList = $("#select-category");
	eduCategoryList.find('li').remove();
	$.each(response.categoryList, function(index, value) {
		
		var x = value[0].toString();
		var y = value[1].toString();
		if(x==catCode){
			eduCategoryList.append('<li><a href="javascript:"><input class="categoryClass" onclick="categoryClick(this)" id="category' + x	+ '" type="checkbox" value="' + x + '" checked="checked"></a>' + y + '</li>');
		}else{
			eduCategoryList.append('<li><a href="javascript:"><input class="categoryClass" onclick="categoryClick(this)" id="category' + x	+ '" type="checkbox" value="' + x + '"></a>' + y + '</li>');
		}
		catCount++;
	});
	totalCount += catCount;
	$("#catCount").text(" " + catCount);
	
	$("#cpTypeDiv").hide();
	$("#majorDiv").hide();
	$("#levelDiv").hide();


	var htmlstr="";
	$.each(response.districtList, function(index, value) {
		if(value!=null && value.length>0){
			htmlstr += '<option val="' + value[0] + '">' + value[2] + '</option>';
		}		
	});		
	$('#districtName').html(htmlstr);

	
	$('#addSearchData').on('click', function(event) {
		$(this).val();
		var cpTypeAll = $('#cpTypeAll').is(':checked');
		var majorAll = $('#majorAll').is(':checked');
		var levelAll = $('#levelAll').is(':checked');
		var districtName = $('#districtlist').val();
		var districtCode=0;
		
		
		var option=$('#districtName').find('option');
		for(var i=0; i<option.length;i++){
			$('#districtName').find('option')[i].outerHTML;
			if(option[i].text ==districtName){				
				districtCode=option[i].attributes[0].value;
				break;
			}
		}		
		
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
				populateFilterSearchResults(response);
			},
			error : function(jqXHR, exception) {
				var msg = '';
				if (jqXHR.status === 0) {
		            msg = 'Not connect.\n Verify Network.';
		        } else if (jqXHR.status == 404) {
		            msg = 'Requested page not found. [404]';
		        } else if (jqXHR.status == 500) {
		            msg = 'Internal Server Error [500].';
		        } else if (exception === 'parsererror') {
		            msg = 'Requested JSON parse failed.';
		        } else if (exception === 'timeout') {
		            msg = 'Time out error.';
		        } else if (exception === 'abort') {
		            msg = 'Ajax request aborted.';
		        } else {
		            msg = 'Uncaught Error.\n' + jqXHR.responseText;
		        }	        
		        alert(msg);
			}
		});

	});	

}

function populateFilterSearchResults(response) {	
	var providerChoice = $("#providerList");
	providerChoice.find('li').remove();
	$.each(response.result,	function(index, value) {
		if(value!=null && value.length>0){
			var x = value[0].toString();
			var y = value[1].toString();
			var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";	
			providerChoice.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' "/></div><div class="provider-name text-center"><h2>'+y+'</h2> </div> </a></div> </li>');
		}
	});
	if(response.result!=null && response.result.length===0){
		providerChoice.append("No course providers for selected criteria");		
	}
	
}

function categoryClick(event){
	
	var catCode=0;
	var categorySelection = $('#select-category').find('.categoryClass:checked');	
	for (var i = 0; i < categorySelection.length; i++) {
		var code = categorySelection[i].value;
		if(code==event.value){
			catCode=code;
		}else{
			categorySelection[i].checked=false;			
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
			populateCategoryWiseTypes(response);
		},
		error : function(jqXHR, exception) {
			var msg = '';
	        if (jqXHR.status === 0) {
	            msg = 'Not connect.\n Verify Network.';
	        } else if (jqXHR.status == 404) {
	            msg = 'Requested page not found. [404]';
	        } else if (jqXHR.status == 500) {
	            msg = 'Internal Server Error [500].';
	        } else if (exception === 'parsererror') {
	            msg = 'Requested JSON parse failed.';
	        } else if (exception === 'timeout') {
	            msg = 'Time out error.';
	        } else if (exception === 'abort') {
	            msg = 'Ajax request aborted.';
	        } else {
	            msg = 'Uncaught Error.\n' + jqXHR.responseText;
	        }	        
	        alert(msg);
		}
	});
}

function populateCategoryWiseTypes(response){
	
	$("#cpTypeDiv").hide();
	$("#majorDiv").hide();
	$("#levelDiv").hide();
	
	var cpTypeCount = 0;
	var secondChoice = $("#select-cpType");
	secondChoice.find('li').remove();
	$.each(	response.cpTypeList,function(index, value) {
		$("#cpTypeDiv").show();
		if(value!=null && value.length>0){
			var x = value[0].toString();
			var y = value[1].toString();
			secondChoice.append('<li><a href="javascript:"><input class="cpTypeClass" id="cpType'+ x + '" type="checkbox" value="'	+ x	+ '"></a>' + y + '</li>');
		}
	cpTypeCount++;
	});	
	$('#cpTypeCount').text(" " +cpTypeCount);
	
	var majorCount = 0;
	var secondChoice = $("#select-major");
	secondChoice.find('li').remove();
	$.each(response.majorList, function(index, value) {
		$("#majorDiv").show();
		if(value!=null && value.length>0){
			var x = value[0].toString();
			var y = value[1].toString();
			secondChoice.append('<li><a href="javascript:"><input class="majorClass" id="major' + x + '" type="checkbox" value="' + x + '"></a>' + y + '</li>');
		}
		majorCount++;
	});
	$('#majorCount').text(" " +majorCount);
	
	var levelCount = 0;
	var secondChoice = $("#select-level");
	secondChoice.find('li').remove();
	$.each(response.levelList, function(index, value) {
		$("#levelDiv").show();
		if(value!=null && value.length>0){			
			var x = value[0].toString();
			var y = value[1].toString();
			secondChoice.append('<li><a href="javascript:"><input class="levelClass" id="level' + x + '" type="checkbox" value="' + x + '"></a>' + y + '</li>');			
		}
		levelCount++;
	});
	$("#levelCount").text(" " +levelCount);
	
	//Course provider type check implementation
	$('#cpTypeAll').on('click', function(event) {
		var cpTypeObj = $("#select-cpType").find('.cpTypeClass');
		if ($(this).is(":checked")) {
			for (var i = 0; i < cpTypeObj.length; i++) {
				$("#select-cpType").find('.cpTypeClass')[i].checked = true;
			}
		}else{
			for (var i = 0; i < cpTypeObj.length; i++) {
				$("#select-cpType").find('.cpTypeClass')[i].checked = false;
			}
			
		}
	});
	$("#select-cpType").find('.cpTypeClass').on('click', function(event) {
		if ($(this).is(":checked")){
			var cpTypeObj = $("#select-cpType").find('.cpTypeClass');
			var checkedCount=0;
			for (var i = 0; i < cpTypeObj.length; i++) {
				if($("#select-cpType").find('.cpTypeClass')[i].checked == true){					
					checkedCount ++;
				}
			}
			if(checkedCount==cpTypeObj.length){				
				$('#cpTypeAll').prop('checked',true);
			}			
		}else{			
			$('#cpTypeAll').prop('checked',false);
		}		
	});
	
//Major check implementation	
	$('#majorAll').on('click', function(event) {
		var majorObj = $("#select-major").find('.majorClass');
		if ($(this).is(":checked")) {
			for (var i = 0; i < majorObj.length; i++) {
				$("#select-major").find('.majorClass')[i].checked = true;
			}
		}else{
			for (var i = 0; i < majorObj.length; i++) {
				$("#select-major").find('.majorClass')[i].checked = false;
			}
			
		}
	});
	
	$("#select-major").find('.majorClass').on('click', function(event) {
		if ($(this).is(":checked")){
			var majorObj = $("#select-major").find('.majorClass');
			var checkedCount=0;
			for (var i = 0; i < majorObj.length; i++) {
				if($("#select-major").find('.majorClass')[i].checked == true){					
					checkedCount ++;
				}
			}
			if(checkedCount==majorObj.length){				
				$('#majorAll').prop('checked',true);
			}			
		}else{			
			$('#majorAll').prop('checked',false);
		}		
	});
	
//Level check Implementation
	$('#levelAll').on('click', function(event) {
		var levelObj = $("#select-level").find('.levelClass');
		if ($(this).is(":checked")) {
			for (var i = 0; i < levelObj.length; i++) {
				$("#select-level").find('.levelClass')[i].checked = true;
			}
		}else{
			for (var i = 0; i < levelObj.length; i++) {
				$("#select-level").find('.levelClass')[i].checked = false;
			}
			
		}
	});
	
	$("#select-level").find('.levelClass').on('click', function(event) {
		if ($(this).is(":checked")){
			var levelObj = $("#select-level").find('.levelClass');
			var checkedCount=0;
			for (var i = 0; i < levelObj.length; i++) {
				if($("#select-level").find('.levelClass')[i].checked == true){					
					checkedCount ++;
				}
			}
			if(checkedCount==levelObj.length){				
				$('#levelAll').prop('checked',true);
			}			
		}else{			
			$('#levelAll').prop('checked',false);
		}		
	});
}





