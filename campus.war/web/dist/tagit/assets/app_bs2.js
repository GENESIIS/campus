//20161206 PN c26-add-student-details: This JavaScript is to populate tagitUI with DB values.

var inputValues = "";

$(document).ready(function() {
	$.ajax({
		url : '../../StudentController',
		data : {
			CCO : 'GSL'
		},
		dataType : "json",
		success : function(response) {
			alert(response.result);
			var startBrc = "[";
			var endBrc = "]";
			var skills = "";
			
			$.each(response.result, function(index, value) {
				var res = value.toString();
				var data = res.split(",");
				var result = '{ "value": '+parseInt(data[0])+ ', "text": "' +data[1]+ '" , "continent": "A" },';
				skills = skills.concat(result);
			});
			startBrc = startBrc.concat(skills.slice(0, -1));
			endBrc = startBrc.concat(endBrc);
			inputValues = endBrc;
			alert(inputValues);
		},
		error : function(response) {
			alert("Error: "+response);
		}
	});
});

$('.example_objects_as_tags > > input').tagsinput({
  itemValue: 'value',
  itemText: 'text',
  typeahead: {
    source: function(query) {
      return $.parseJSON(inputValues);
    }
  }
});
$('.example_objects_as_tags > > input').tagsinput('add', { "value": 1 , "text": "Amsterdam"   , "continent": "Europe"    });
$('.example_objects_as_tags > > input').tagsinput('add', { "value": 4 , "text": "Washington"  , "continent": "America"   });
//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 7 , "text": "Sydney"      , "continent": "Australia" });
//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 10, "text": "Beijing"     , "continent": "Asia"      });
//$('.example_objects_as_tags > > input').tagsinput('add', { "value": 13, "text": "Cairo"       , "continent": "Africa"    });







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

//angular.module('AngularExample', ['bootstrap-tagsinput'])
//  .controller('CityTagsInputController',
//    function CityTagsInputController($scope, $http) {
//      // Init with some cities
//      $scope.cities = [
//        { "value": 1 , "text": "Amsterdam"   , "continent": "Europe"    },
//        { "value": 4 , "text": "Washington"  , "continent": "America"   },
//        { "value": 7 , "text": "Sydney"      , "continent": "Australia" },
//        { "value": 10, "text": "Beijing"     , "continent": "Asia"      },
//        { "value": 13, "text": "Cairo"       , "continent": "Africa"    }
//      ];
//
//      $scope.queryCities = function(query) {
//        return $http.get('dist/tagit/assets/cities.json');
//      };
//
//      $scope.getTagClass = function(city) {
//        switch (city.continent) {
//          case 'Europe'   : return 'label label-info';
//          case 'America'  : return 'label label-danger label-important';
//          case 'Australia': return 'label label-success';
//          case 'Africa'   : return 'label';
//          case 'Asia'     : return 'label label-warning';
//        }
//      };
//    }
//  );