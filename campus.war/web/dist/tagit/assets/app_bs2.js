//20161206 PN c26-add-student-details: This JavaScript is to populate tagitUI with DB values.
//		   PN c26-add-student-details: implemented addSkillDetails() Ajax method.

var extStudentSkills = [];

$(document).ready(function() {
	$.ajax({
		url : '../../StudentController',
		data : {
			CCO : 'GSL'
		},
		dataType : "json",
		success : function(response) {
			alert(response.result);
			var inputValues = createJsonObj(response.result);
			
			$('.example_objects_as_tags > > input').tagsinput({
				  itemValue: 'value',
				  itemText: 'text',
				  typeahead: {
				    source: function(query) {
				      return $.parseJSON(inputValues);
				    }
				  }
			});
			
			$.each(response.stskillCollection, function(index, value) {
				var res = value.toString();
				var data = res.split(",");
				extStudentSkills.push(parseInt(data[0]));
				$('.example_objects_as_tags > > input').tagsinput('add', { "value": parseInt(data[0]) , "text": data[1] , "continent": "A" });
			});
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
});


//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 1 , "text": "Amsterdam"   , "continent": "Europe"    });
//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 4 , "text": "Washington"  , "continent": "America"   });
//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 7 , "text": "Sydney"      , "continent": "Australia" });
//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 10, "text": "Beijing"     , "continent": "Asia"      });
//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 13, "text": "Cairo"       , "continent": "Africa"    });

/**
 * This method generates a Json object to pass into tag-it input.
 * @param response
 * @returns
 */
function createJsonObj(response){
	var inputValues = "";
	var startBrc = "[";
	var endBrc = "]";
	var elements = "";
	
	$.each(response, function(index, value) {
		var res = value.toString();
		var data = res.split(",");
		var result = '{ "value": '+parseInt(data[0])+ ', "text": "' +data[1]+ '" , "continent": "A" },';
		elements = elements.concat(result);
	});
	startBrc = startBrc.concat(elements.slice(0, -1));
	endBrc = startBrc.concat(endBrc);
	inputValues = endBrc;
	return inputValues;
}


/**
 * This method is to save student skills into the DB.
 * @returns
 */
function addSkillDetails() {
	var values = $("#studentSkills").val();
	var prevalues = extStudentSkills;
	
	$.ajax({
		type : "POST",
		url : '../../StudentController',
		data : {
			oldStudentSkills : prevalues.toString(),
			newStudentSkills : values,
			CCO : "ASS"
		},
		dataType : "json",
		success : function(data) {			
			alert(data);
//			if(data.studentPersonalStatus){	
//					if(data.studentPersonalStatus === "Unsuccessful."){
//						$("#studentPersonalStatus").addClass("alert alert-danger").text(data.pesaveChangesStatus).show();
//					}else if(data.studentPersonalStatus === "Invalid Information"){
//						$("#studentPersonalStatus").addClass("alert alert-danger").text("Invalid Information.").show();
//					}
//				clearPersonalDetailsForm();	
//				$("#studentPersonalStatus").addClass("alert alert-success").text(data.studentPersonalStatus).show();
//			}
		},
		error : function(e) {
			alert("Error " + e);
		}
	});
}



$('.example_tagclass > > input').tagsinput({
  tagClass: function(item) {
    switch (item.continent) {
      case 'Europe'   : return 'label label-info';
      case 'America'  : return 'label label-danger label-important';
      case 'Australia': return 'label label-success';
      case 'Africa'   : return 'label';
      case 'Asia'     : return 'label label-warning';
    }
  },
  itemValue: 'value',
  itemText: 'text',
  typeahead: {
    source: function(query) {
      return $.getJSON('dist/tagit/assets/cities.json');
    }
  }
});
$('.example_tagclass > > input').tagsinput('add', { "value": 1 , "text": "Amsterdam"   , "continent": "Europe"    });
$('.example_tagclass > > input').tagsinput('add', { "value": 4 , "text": "Washington"  , "continent": "America"   });
$('.example_tagclass > > input').tagsinput('add', { "value": 7 , "text": "Sydney"      , "continent": "Australia" });
$('.example_tagclass > > input').tagsinput('add', { "value": 10, "text": "Beijing"     , "continent": "Asia"      });
$('.example_tagclass > > input').tagsinput('add', { "value": 13, "text": "Cairo"       , "continent": "Africa"    });
