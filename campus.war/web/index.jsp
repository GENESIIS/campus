<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-app="CampusApp">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus.lk</title>

    <!-- Bootstrap & CSS Style-->
    <link href="dist/bower-components/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="dist/css/style.css" rel="stylesheet">

<!--     Data Table CSS -->
<!--     <link href="dist/datatable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"> -->
<!--     <link href="dist/datatable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css"> -->
   
    
</head>
<body>

<!-- Header-->
<header ng-include="'dist/partials/layout/header.jsp'"></header>

<!-- Main Container  -->
<div ng-view>
</div>

<!-- Footer -->
<footer ng-include="'dist/partials/layout/footer.jsp'"></footer>

<!-- AngularJS -->
<script src="dist/bower-components/angular/angular.min.js"></script>
<script src="dist/bower-components/angular/angular-route.min.js"></script>

<!-- jQuery & Other js -->
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="dist/js/app.js"></script>
<script src="dist/js/filterSearch/ui-populate-helper.js"></script>

<!-- Data Table CSS-->
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.3.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/dataTables.responsive.min.js" type="text/javascript" charset="utf-8"></script>
<script src="dist/datatable/responsive.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function() {
	 $('#example').DataTable();
} );
</script>
</body>
</html>