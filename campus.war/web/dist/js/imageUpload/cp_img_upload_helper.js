/**
 * 20170221 PN CAM-48: INIT file to implement the javascript helping methods to cp image uploading functionality. 
 *             CAM-48: implemented setCPImgData(response) method to populate cp_img_type dropdown with DB values.
 *             CAM-48: implemented a jquery for cp_img_desc dropdown onchange method.
 */

var dataSet = null;
var courseProviderCode = null;
// A $( document ).ready() block.
$(document).ready(function() {
	displayImgDetails();

	//According to the selection of drop box, image description will be appear.
	$('#cp_img_type').on('change', function() {
		var sysConfCode = this.value;
		$.each(dataSet.result, function(index, val) {
			if (val[0] === sysConfCode) {
				$('#cp_img_desc').html(val[2]);
				Console.log($("#cp_img_desc option:selected").text());;
			}
		});
	})

});

/**
 * This method will be handle the data displaying on 'cp_img_type' dropdown
 * element and image box.
 * 
 * @returns
 */
function displayImgDetails() {
	$.ajax({
		url : '/AdminController',
		data : {
			CCO : 'GCPID'
		},
		dataType : "json",
		success : function(response) {
			if (response && response.result != "NO-DATA") {
				dataSet = response;
				courseProviderCode = response.courseProviderCode;
				setCPImgData(response);
			}
		},
		error : function(response) {
			alert("Error: " + response);
		}
	});
}

// This method will set details and populate cp_img_type select element.
function setCPImgData(response) {
	var cp_img_type = $("#cp_img_type");
	cp_img_type.find('option').remove();
	$('<option>').val("").text("--Select One--").appendTo(cp_img_type);
	$.each(response.result, function(index, value) {
		if (value[4] == 'cp_img') {
			var x = value[0].toString();
			var y = value[1].toString();
			$('<option>').val(x).text(y).appendTo(cp_img_type);
		}
	});
}
