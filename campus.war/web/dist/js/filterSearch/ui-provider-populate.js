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
				
		
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			var z = data[2].toString();
			var logo="../../education/provider/logo/"+ y+ "/" + z;
			var row = $('<tr>');
			
			    row.append($('<td>').html(x));
			    row.append($('<td>').html(y));
			    row.append($('<td>').html(logo));
			    row.append($('<td>').html('<img height="42" width="42" src="'+logo +' " />'));
			    
			    
			   /*  <img height="42" width="42"
					src="${contextDeployLogoPath}${prefix}${slash}${pvAttribute}"/> */
			
			$('#ptable').append(row);
		});	
		
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
			secondChoice.append('<li><a href="javascript:"><input name="category" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			catCount++;
		});
		totalCount+=catCount;
		$("#catCount").text(" " +catCount);
		

		/*var courseProviderType = $("#courseProviderType");
		$.each(response.cpTypeList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(courseProviderType);
		});*/
		
		var cpTypeCount = 0 ;
		var secondChoice = $("#select-cpType");
		//secondChoice.find('li').remove();
		$.each(response.cpTypeList, function(index, value) {			
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="cpType" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			cpTypeCount++;
		});
		totalCount+=cpTypeCount;
		$("#cpTypeCount").text(" " +cpTypeCount);

		/*var majorList = $("#majorList");
		$.each(response.majorList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(majorList);
		});	*/
		var majorCount = 0 ;
		var secondChoice = $("#select-major");
		//secondChoice.find('li').remove();
		$.each(response.majorList, function(index, value) {			
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="major" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
			majorCount++;
		});
		totalCount+=majorCount;
		$("#majorCount").text(" " +majorCount);
		
	/*	var levelList = $("#levelList");
		$.each(response.levelList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(levelList);
		});
		*/
		var levelCount = 0 ;
		var secondChoice = $("#select-level");
		//secondChoice.find('li').remove();
		$.each(response.levelList, function(index, value) {			
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			secondChoice.append('<li><a href="javascript:"><input name="level" type="checkbox" value="'+ x +'"></a>' + y + '</li>');
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

	}