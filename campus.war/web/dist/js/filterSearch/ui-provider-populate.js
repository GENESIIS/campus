//20161118 DJ c17-provider-criteria-based-filter-search Load the details for the provider filter search


$(document).ready(function(){
	alert("Test....");
	$.ajax({url : '../../PublicController',
		data : {
			CCO : 'LIST_ALL_COURSE_PROVIDERS'
		},
		dataType : "json",
		success : function(response) {			
			getAjaxData(response);
		}
	});
});

	function getAjaxData(response) {
		
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
		
		var html="";
		$.each(response.result, function(index, value) {			
			var res=value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			var z = data[2].toString();
			html+='<div>'+x+':'+y+'</div>';
			$("#provider-name").append(html);
			
		});
		
		
		
		var providers = $("#providers");
		$.each(response.result, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(providers);
		});
		
		var categories = $("#categoryName");
		$.each(response.categoryList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(categories);
		});

		var courseProviderType = $("#courseProviderType");
		$.each(response.cpTypeList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(courseProviderType);
		});

		var majorList = $("#majorList");
		$.each(response.majorList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(majorList);
		});		
		
		var levelList = $("#levelList");
		$.each(response.levelList, function(index, value) {
			var res = value.toString();
			var data = res.split(",");
			var x = data[0].toString();
			var y = data[1].toString();
			$('<option>').val(x).text(y).appendTo(levelList);
		});
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