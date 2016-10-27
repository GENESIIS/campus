<!-- c11-criteria-based-filter-search : designed a sample UI to implement filter search. -->
<!-- c11-criteria-based-filter-search : modified the JavaScript code to add addsearchData() method to pass data to the servlet. -->
<!-- 20161027 PN c11-criteria-based-filter-search : modified the JavaScript code to fix a bug encountered in passing data to the JSon -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link href="dist/css/jquery.tagit.css" rel="stylesheet" type="text/css">
<link href="dist/css/tagit.ui-zendesk.css" rel="stylesheet"	type="text/css">
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"	type="text/javascript" charset="utf-8"></script>
<script src="dist/js/tag-it.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	<h1>Filter Search</h1>

	<div>
		Text: <input type="text" id="txt_username" /> 
		<br /> <br /> <br /> 
		
		<select id="landingSelect">
			<option value="0" label="choose one">Category</option>
			<option value="&cid=1" label="landing 1">landing 1</option>
			<option value="&cid=2" label="landing 2">landing 2</option>
		</select>
		<br /><br />
		
		<select id="querySelct">
			<option value="0" label="choose one">choose one</option>
			<option value="&queryid=1" label="option 1">option 1</option>
			<option value="&queryid=2" label="option 2">option 2</option>
			<option value="&queryid=3" label="option 3">option 3</option>
			<option value="&queryid=4" label="option 4">option 4</option>
			<option value="&queryid=5" label="option 5">option 5</option>
			<option value="&queryid=6" label="option 6">option 6</option>
		</select> 
		


	</div>

	<div class="pane">
		<div class="ele">
			<input type="checkbox" value="X" name="Brand"> <label>Brand
				1</label> <input type="checkbox" value="Y" name="Brand"> <label>Brand
				2</label> <input type="checkbox" value="Z" name="Brand"> <label>Brand
				3</label> <input type="checkbox" value="1" name="Size"> <label>Size
				1</label> <input type="checkbox" value="2" name="Size"> <label>Size
				2</label> <input type="checkbox" value="3" name="Size"> <label>Size
				3</label>
		</div>
	</div>

	<h3>Single Input Field</h3>
	<input name="tags" id="mySingleField" value="Apple, Orange"
		disabled="true">
	<!-- only disabled for demonstration purposes -->
	<ul id="singleFieldTags"></ul>

	 <button onclick="addsearchData()">Click me</button> 

	<script type="text/javascript">
	var SourceUrl = "";
	var queryUrl = "";
	var landingUrl = "";
	var finalUrl = SourceUrl;
	var result = "";
	var sercval = "";
	var mySingleField = "";
	
		$(document).ready(function() {
			$('input[type="checkbox"]').on('change', function(e) {
				var data = {};
				var dataStrings = [];

				$('input[type="checkbox"]').each(function() {
					if (this.checked) {
						if (data[this.name] === undefined)
							data[this.name] = [];
						data[this.name].push(this.value);
					}
				});

				$.each(data, function(key, value) {
					dataStrings.push(key + "=" + value.join(','));
				});

				result = dataStrings.join('&');

				<!--
				$.post('/ajax-post-url/', data);
				-->
				if (history.pushState) {
					history.pushState(null, null, loc.pathname + '?' + data);
				}
			});
		});

		$(function() {
			var sampleTags = [ 'c++', 'java', 'php', 'coldfusion',
					'javascript', 'asp', 'ruby', 'python', 'c', 'scala',
					'groovy', 'haskell', 'perl', 'erlang', 'apl', 'cobol',
					'go', 'lua' ];
			//-------------------------------
			// Single field
			//-------------------------------
			$('#singleFieldTags').tagit({
				availableTags : sampleTags,
				// This will make Tag-it submit a single form value, as a comma-delimited field.
				singleField : true,
				singleFieldNode : $('#mySingleField')
			});
		});
		

		$(function() {	    
		  $('#querySelct').on('change', function () {
		      if($(this).val() == 0) {
		         queryUrl = "";
		      } else {
		          queryUrl = $(this).val();
		      }
		      return false;
		  });
		    
		    $('#landingSelect').on('change', function () {
		      if($(this).val() == 0) {
		         landingUrl = "";
		      } else {
		         landingUrl = $(this).val();
		      }
		     return false;
		  });
		});
		

		var tbv = $('#txt_username').val();
		sercval = "&sercval=" + tbv;

		var MSV = $('#mySingleField').val();
		mySingleField = "&mySingleField=" + MSV;

// 		function MakeUrl() {
// 			finalUrl = SourceUrl + queryUrl + landingUrl + result + sercval
// 					+ mySingleField;
// 			alert(finalUrl);
// 		}
		
		// Get data and sent to EmployeeController.java.
		function addsearchData() {
// 			alert(result);
// 			alert(queryUrl);
// 			alert(landingUrl);
			finalUrl = result + SourceUrl + queryUrl + landingUrl + sercval
			+ mySingleField;
// 			alert(finalUrl);
			
// 			var searchData = {
// 				"searchData" : finalUrl
// 			};
	
			var searchData = finalUrl;

			$.ajax({
				type : "POST",
				url : 'PublicController',
				data : {
					jsonData : JSON.stringify(searchData),
					CCO : "GET_SEARCH_DATA"
				},
				dataType : "json",
				success : function(data) {
// 					alert(data);
// 					if (data == "Details added successfully.") {
// 						clearAddemployeeform();
// 					}
				},
				error : function(e) {
					alert("Error " + e);
					console.log(e);
				}
			});
		}
	</script>
</body>
</html>