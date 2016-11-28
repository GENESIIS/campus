<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Bootstrap & CSS Style-->
    <link href="dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">

	<!--     Data Table CSS -->
    <link href="dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="dist/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
    
    <link href="dist/datatable/dataTables.checkboxes.css" rel="stylesheet" type="text/css">

<title>Insert title here</title>
</head>
<body>
<!-- jQuery & Other js -->
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.3.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>


<script src="dist/datatable/dataTables.checkboxes.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.checkboxes.min.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
//
//Updates "Select all" control in a data table
//
function updateDataTableSelectAllCtrl(table){
var $table             = table.table().node();
var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
var chkbox_select_all  = $('thead input[name="select_all"]', $table).get(0);

// If none of the checkboxes are checked
if($chkbox_checked.length === 0){
   chkbox_select_all.checked = false;
   if('indeterminate' in chkbox_select_all){
      chkbox_select_all.indeterminate = false;
   }

// If all of the checkboxes are checked
} else if ($chkbox_checked.length === $chkbox_all.length){
   chkbox_select_all.checked = true;
   if('indeterminate' in chkbox_select_all){
      chkbox_select_all.indeterminate = false;
   }

// If some of the checkboxes are checked
} else {
   chkbox_select_all.checked = true;
   if('indeterminate' in chkbox_select_all){
      chkbox_select_all.indeterminate = true;
   }
}
}

$(document).ready(function (){
// Array holding selected row IDs
var rows_selected = [];
var table = $('#example').DataTable({
   'ajax': 'https://api.myjson.com/bins/1us28',
   'columnDefs': [{
      'targets': 0,
      'searchable':false,
      'orderable':false,
      'width':'1%',
      'className': 'dt-body-center',
      'render': function (data, type, full, meta){
          return '<input type="checkbox">';
      }
   }],
   'order': [1, 'asc'],
   'rowCallback': function(row, data, dataIndex){
      // Get row ID
      var rowId = data[0];

      // If row ID is in the list of selected row IDs
      if($.inArray(rowId, rows_selected) !== -1){
         $(row).find('input[type="checkbox"]').prop('checked', true);
         $(row).addClass('selected');
      }
   }
});

// Handle click on checkbox
$('#example tbody').on('click', 'input[type="checkbox"]', function(e){
   var $row = $(this).closest('tr');

   // Get row data
   var data = table.row($row).data();

   // Get row ID
   var rowId = data[0];

   // Determine whether row ID is in the list of selected row IDs 
   var index = $.inArray(rowId, rows_selected);

   // If checkbox is checked and row ID is not in list of selected row IDs
   if(this.checked && index === -1){
      rows_selected.push(rowId);

   // Otherwise, if checkbox is not checked and row ID is in list of selected row IDs
   } else if (!this.checked && index !== -1){
      rows_selected.splice(index, 1);
   }

   if(this.checked){
      $row.addClass('selected');
   } else {
      $row.removeClass('selected');
   }

   // Update state of "Select all" control
   updateDataTableSelectAllCtrl(table);

   // Prevent click event from propagating to parent
   e.stopPropagation();
});

// Handle click on table cells with checkboxes
$('#example').on('click', 'tbody td, thead th:first-child', function(e){
   $(this).parent().find('input[type="checkbox"]').trigger('click');
});

// Handle click on "Select all" control
$('thead input[name="select_all"]', table.table().container()).on('click', function(e){
   if(this.checked){
      $('#example tbody input[type="checkbox"]:not(:checked)').trigger('click');
   } else {
      $('#example tbody input[type="checkbox"]:checked').trigger('click');
   }

   // Prevent click event from propagating to parent
   e.stopPropagation();
});

// Handle table draw event
table.on('draw', function(){
   // Update state of "Select all" control
   updateDataTableSelectAllCtrl(table);
});
 
// Handle form submission event 
$('#frm-example').on('submit', function(e){
   var form = this;

   // Iterate over all selected checkboxes
   $.each(rows_selected, function(index, rowId){
      // Create a hidden element 
      $(form).append(
          $('<input>')
             .attr('type', 'hidden')
             .attr('name', 'id[]')
             .val(rowId)
      );
   });

   // FOR DEMONSTRATION ONLY     
   
   // Output form data to a console     
   $('#example-console').text($(form).serialize());
   alert($(form).serialize());
   //console.log("Form submission", $(form).serialize());
    
   // Remove added elements
   $('input[name="id\[\]"]', form).remove();
    
   // Prevent actual form submission
   e.preventDefault();
});
});

</script>

<h3><a href="http://www.gyrocode.com/articles/jquery-datatables-checkboxes/">jQuery DataTables  Row selection using checkboxes</a></h3>
<a href="http://www.gyrocode.com/articles/jquery-datatables-checkboxes/">See full article on Gyrocode.com</a>
<hr><br>
    
<form id="frm-example" action="/path/to/your/script" method="POST">
    
<table id="example" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
   <thead>
      <tr>
         <th><input name="select_all" value="1" type="checkbox"></th>
         <th>Name</th>
         <th>Position</th>
         <th>Office</th>
         <th>Extn.</th>
         <th>Start date</th>
         <th>Salary</th>
      </tr>
   </thead>
   <tfoot>
      <tr>
         <th></th>
         <th>Name</th>
         <th>Position</th>
         <th>Office</th>
         <th>Extn.</th>
         <th>Start date</th>
         <th>Salary</th>
      </tr>
   </tfoot>
</table>
<hr>

<p>Press <b>Submit</b> and check console for URL-encoded form data that would be submitted.</p>

<p><button>Submit</button></p>

<b>Data submitted to the server:</b><br>
<pre id="example-console">
</pre>
</form>


</body>
</html>