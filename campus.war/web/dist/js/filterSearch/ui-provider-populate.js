//20161118 DJ c17-provider-criteria-based-filter-search Load the details for the provider filter search


$(document).ready(function(){	
	 
	 var catCode= $("#catCode").val();
	
	 
	alert("Test....");
	$.ajax({url : '../../PublicController',
		data : {
			CCO : 'LIST_ALL_COURSE_PROVIDERS',
			categoryCode:catCode
		},
		dataType : "json",
		success : function(response) {			
			getAjaxData(response);
		},
        error: function () {
            alert("error");
        }
	});
});

	function getAjaxData(response) {
		
		var totalCount = 0 ;
		
	/* 	var providers = $("#providers");
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(providers);
		}); */
		
		var providerChoice = $("#providerList");
		$.each(response.result, function(index, value) {			
			var res=value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			var z = data[2].toString();
			var logo="../../education/provider/logo/"+ y+ "/" + z;
			providerChoice.append('<li><a href="javascript:"><img height="100" width="100" src="'+logo +' " /> </a> </li>');
		});
		
		
		
		var providers = $("#providers");
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(providers);
		});
		
		/*var categories = $("#categoryName");
		$.each(response.categoryList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(categories);
		});*/
		
		
		var catCount = 0 ;
		var secondChoice = $("#select-item1");
		//secondChoice.find('li').remove();
		$.each(response.categoryList, function(index, value) {			
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input id="category'+x+'" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			catCount++;
		});
		totalCount+=catCount;
		$("#catCount").text(" " +catCount);
		
		var cpTypeCount = 0 ;
		var secondChoice = $("#select-cpType");
		//secondChoice.find('li').remove();
		$.each(response.cpTypeList, function(index, value) {			
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="cpType'+x+'" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			cpTypeCount++;
		});
		totalCount+=cpTypeCount;
		$("#cpTypeCount").text(" " +cpTypeCount);

		var majorCount = 0 ;
		var secondChoice = $("#select-major");
		//secondChoice.find('li').remove();
		$.each(response.majorList, function(index, value) {			
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="major'+x+'" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			majorCount++;
		});
		totalCount+=majorCount;
		$("#majorCount").text(" " +majorCount);
		

		var levelCount = 0 ;
		var secondChoice = $("#select-level");
		//secondChoice.find('li').remove();
		$.each(response.levelList, function(index, value) {			
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="level'+x+'" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			levelCount++;
		});
		totalCount+=levelCount;
		$("#levelCount").text(" " +levelCount);
		
		$("#totalCount").text(" " +totalCount);
		
		var districtList = $("#districtList");
		$.each(response.districtList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			var z = data[2].toString();
			$('<option>').val(y).text(z).appendTo(districtList);
		});
		
		
		 $('#addSearchData').on( 'click', function (event) {
			
			 alert("addSearchData click");
			 $( this ).val();			 
			 var selectAll = $('#selectAll').is(':checked');
			 var categoryAll = $('#categoryAll').is(':checked');
			 var cpTypeAll = $('#cpTypeAll').is(':checked');
			 var majorAll = $('#majorAll').is(':checked');
			 var levelAll = $('#levelAll').is(':checked');
			 
			 alert("totalCount"+ totalCount);
			 for(var x=1;x<=catCount;x++){
				 var category = $('#category'+x+'').is(':checked');
				 alert("category" + category);
				 
			 }
			 var catMap={ };
			 var category2 = $('#category2').is(':checked');		 
			 
			 
			 var searchData=0;
			 
			 if(selectAll==true){
				 catMap={'searchData':selectAll};				
				 alert("selectAll "); 
			 }else {
				 catMap={'categoryAll':categoryAll,
						 'cpTypeAll':cpTypeAll,
						 'majorAll':majorAll,
						 'levelAll':levelAll					 
						 
				 };
			 }
			 
			 
			 $.ajax({
					url : '../../PublicController',
					data : {
						searchData : JSON.stringify(catMap),
						CCO : 'LIST_FILTER_SEARCH_COURSE_PROVIDERS'
					},
					dataType : "json",
					success : function(response) {
						 alert("success add click");
					},
			        error: function () {
			            alert("error");
			        }
			 });
			 
			 
		 });

	}
	
	/*$('#addRow').keyup(function(){
		
		alert("addRow");
		var searchField = $('#search').val();
		
	});*/
	
	




