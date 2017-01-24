//20161118 DJ c17-provider-criteria-based-filter-search Load the details for the provider filter search
//20161124 DJ c17-provider-criteria-based-filter-search Identified front end input selections 
//20161221 DJ c17-provider-criteria-based-filter-search Identified checkbox tick untick on level/major/providertype 
//20170117 DJ c123-general-filter-search-course-provider-MP-dj Identify general filter search action
//20170123 DJ c123-general-filter-search-course-provider-MP-dj Add courseProviderLogoPath and default image path

var cpCodeList="";
var keyWordString="";
var selectedType="";
var generalSearchFlag="";

$(document).ready(function() {	

	var sPageURL = window.location.search.substring(1);
	// Get input parameters of general filter search.
	if (sPageURL != null && sPageURL != "") {
		var sURLVariables = sPageURL.split('&');
		for (var i = 0; i < sURLVariables.length; i++) {
			var sParameterName = sURLVariables[i].split('=');
			if (sParameterName[0] == 'keyWord') {
				keyWordString = sParameterName[1];
			} else if (sParameterName[0] == 'selectedType') {
				selectedType = sParameterName[1];
			}
		}
    //Retrieve course provider code list according to search parameters.
		$.ajax({
			url : '../../PublicController',
			data : {
				CCO : 'GENERAL_FILTER_SEARCH_COURSE_PROVIDERS',
				keyWordString : keyWordString,
				selectedType : selectedType
			},
			dataType : "json",
			success : function(response) {
				cpCodeList = response.codeList;
				generalSearchFlag = "TRUE";
				getProviderCodeList(cpCodeList, generalSearchFlag);
			},
			error : function(jqXHR, exception) {
				errorCodeGeneration(jqXHR, exception);
			}
		});

	} else {
		//path-Course provider filter search
		cpCodeList = "";
		generalSearchFlag = "FALSE";
		getProviderCodeList(cpCodeList, generalSearchFlag);
	}	
});

//Retrieve related course providers for filter search view.
function getProviderCodeList(cpCodeList,generalSearchFlag) {
	 
	 var catCode = $("#catCode").val();
		$.ajax({
			url : '../../PublicController',
			data : {
				CCO : 'LIST_ALL_COURSE_PROVIDERS',
				categoryCode : catCode,
				cpCodeList:cpCodeList,
				generalSearchFlag:generalSearchFlag
			},
			dataType : "json",
			success : function(response) {
				getInitialPageResults(catCode,response);
			},
			error : function(jqXHR, exception) {
				errorCodeGeneration(jqXHR, exception);
			}
		});
}

//Populating course provider filter search view.
function getInitialPageResults(catCode,response) {

	var totalCount = 0;
	var courseProviderLogoPathFetched = response.courseProviderLogoPath;
	var providerChoice = $("#providerList");
	$.each(response.result,	function(index, value) {						
						var x = value[0].toString();
						var y = value[1].toString();
						var providerLogo = "/" + courseProviderLogoPathFetched + "/" + x + "/" + x + "_large.jpg";						
				        var providerLogoTest = "/" + courseProviderLogoPathFetched + "/default_large.jpg";						
						//var defaultLogo ="/" + courseProviderLogoPathFetched + "/default_large.jpg";	
						//var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";						
						//var logo1 = "../../education/provider/logo/default_large.jpg";						
						//providerChoice.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' "/></div><div class="provider-name text-center"><h2>'+y+'</h2> </div> </a></div> </li>');
						providerChoice.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+providerLogo+'" onerror="this.src = \'' +providerLogoTest+ '\'"></div> </a></div> </li>');
												
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

	/*
	 * Identify the search button click
	 * */
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
				errorCodeGeneration(jqXHR, exception);
			}
		});

	});	

}

//Populate search results in the page body
function populateFilterSearchResults(response) {	
	var providerChoice = $("#providerList");
	var courseProviderLogoPathFetched = response.courseProviderLogoPath;
	providerChoice.find('li').remove();
	providerChoice.text("");
	$.each(response.result,	function(index, value) {
		if(value!=null && value.length>0){
			var x = value[0].toString();
			var y = value[1].toString();
			var providerLogo = "/" + courseProviderLogoPathFetched + "/" + x + "/" + x + "_large.jpg";
			var logo = "../../education/provider/logo/" + x + "/"+ x + "_large.jpg";	
			//providerChoice.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' "/></div><div class="provider-name text-center"><h2>'+y+'</h2> </div> </a></div> </li>');
			  providerChoice.append('<li class="col-md-3 col-lg-3 col-sm-4"><div class="item-holder"><a href="javascript:"><div class="provider-logo text-center"><img height="100" width="100" src="'+ logo + ' "  title="'+ y +'" /></div> </a></div> </li>');
			
		}
	});
	if(response.result!=null && response.result.length===0){		
		providerChoice.text("No course providers for selected criteria");		
	}
	
}

//Identify the category click
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
			errorCodeGeneration(jqXHR, exception);
		}
	});
}

//Populate category wise provider types,levels and majors
function populateCategoryWiseTypes(response){
	
	$("#cpTypeDiv").hide();
	$("#majorDiv").hide();
	$("#levelDiv").hide();
	
	var cpTypeCount = 0;
	var secondChoice = $("#select-cpType");
	secondChoice.find('li').remove();
	$.each(	response.cpTypeList,function(index, value) {
		$("#cpTypeDiv").show();
		$('#cpTypeAll').prop('checked',false);
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
		$('#majorAll').prop('checked',false);
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
		$('#levelAll').prop('checked',false);
		if(value!=null && value.length>0){			
			var x = value[0].toString();
			var y = value[1].toString();
			secondChoice.append('<li><a href="javascript:"><input class="levelClass" id="level' + x + '" type="checkbox" value="' + x + '"></a>' + y + '</li>');			
		}
		levelCount++;
	});
	$("#levelCount").text(" " +levelCount);
	
	//Course provider type all check implementation
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
	
	//Course provider type check implementation
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
	
//Major all check implementation	
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
	
//Major check Implementation
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
	
//Level all check Implementation
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
	
//Level check Implementation
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

/**
 * This method errorCodeGeneration() manipulate with errors.
 */
function errorCodeGeneration(jqXHR, exception){
	var msg = '';
	   if (jqXHR.status === 0) {
         msg = 'Not connect.\n Verify Network.';
     } else if (jqXHR.status == 404) {
         msg = 'Requested page not found. [404]';
     } else if (jqXHR.status == 500) {
         msg = 'Internal Server Error [500].';
     } else if (exception === 'timeout') {
         msg = 'Time out error.';
     }  else {
         msg = 'Uncaught Error.\n' + jqXHR.responseText;
     }	        
 alert(msg);	
}





