/**
 * 20161028 PN c11-criteria-based-filter-search INIT the class. This has all the helping methods to populate UI elements from the DB values.
 */

function displayCategories(){
	$.ajax({
		type : "POST",
		url : 'PublicController',
		data : {
			//jsonData : JSON.stringify(searchData),
			CCO : "LIST_CATEGORY_DATA"
		},
		dataType : "json",
		success : function(data) {
				alert(data);
//				 $("p").append(" <b>Appended text</b>.");
				 $("p").append('<div class="select-item row-fluid"><a href="javascript:"><input name="Category" type="checkbox"></a><label>School Education</label></div>');
				 
				 
				 
				 
				 
//				if (data == "Details added successfully.") {
//					clearAddemployeeform();
//				}
		},
		error : function(e) {
			alert("Error " + e);
			console.log(e);
		}
	});
}


function test() {
	
//$(document).ready(function() {
//	$('#sports').change(function(event) {
		//var sports = $("select#sports").val();
		$.get('PublicController', {
			CCO : "LIST_CATEGORY_DATA"
		}, function(response) {
//			var select = $('#Category');
//			select.find('#dropItem1').remove();
//			$.each(response, function(index, value) {
//				$('<option>').val(value).text(value).appendTo(select);
//			});
			alert(response);			
			var table = $('<table></table>');
            var counter = 0;
            $(checkboxlistItems).each(function () {
                table.append($('<tr></tr>').append($('<td></td>').append($('<input>').attr({
                    type: 'checkbox', name: 'chklistitem', value: this.Value, id: 'chklistitem' + counter
                })).append(
                $('<label>').attr({
                    for: 'chklistitem' + counter++
                }).text(this.Name))));
            });
 
            $('#dropItem1').append(table);
			
			
//			var table = $('<table></table>');
//            var counter = 0;
//            $(checkboxlistItems).each(function () {
//                table.append($('<tr></tr>').append($('<td></td>').append($('<input>').attr({
//                    type: 'checkbox', name: 'chklistitem', value: this.Value, id: 'chklistitem' + counter
//                })).append(
//                $('<label>').attr({
//                    for: 'chklistitem' + counter++
//                }).text(this.Name))));
//            });
// 
//            $('#dvCheckBoxListControl').append(table);
			
		});
//	});
//});
}

//function PopulateCheckBoxList() {
//    $.ajax({
//        type: "POST",
//        url: 'PublicController',
//        contentType: "application/json; charset=utf-8",
//        data: "{}",
//        dataType: "json",
//        success: AjaxSucceeded,
//        error: AjaxFailed
//    });
//}
//function AjaxSucceeded(result) {
//    BindCheckBoxList(result);
//}
//function AjaxFailed(result) {
//    alert('Failed to load checkbox list');
//}
//function BindCheckBoxList(result) {
//
//    var items = JSON.parse(result.d);
//    CreateCheckBoxList(items);
//}
//function CreateCheckBoxList(checkboxlistItems) {
//    var table = $('<table></table>');
//    var counter = 0;
//    $(checkboxlistItems).each(function () {
//        table.append($('<tr></tr>').append($('<td></td>').append($('<input>').attr({
//            type: 'checkbox', name: 'chklistitem', value: this.Value, id: 'chklistitem' + counter
//        })).append(
//        $('<label>').attr({
//            for: 'chklistitem' + counter++
//        }).text(this.Name))));
//    });
//
//    $('#dvCheckBoxListControl').append(table);
//}