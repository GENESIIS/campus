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

    <!--<link rel="stylesheet" href="css/angular-material.min.css" />-->
    <!--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->
    <!--<link href="css/jk-carousel.min.css" rel="stylesheet">-->
    
    
    <link href="dist/css/jquery.tagit.css" rel="stylesheet" type="text/css">
    <link href="dist/css/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">

</head>
<body>

<!-- Header-->
<header ng-include="'dist/partials/layout/header.jsp'"></header>

<!-- Main Container  -->
<div ng-view></div>

<!-- Footer -->
<footer ng-include="'dist/partials/layout/footer.jsp'"></footer>

<!-- AngularJS -->
<script src="dist/bower-components/angular/angular.min.js"></script>
<script src="dist/bower-components/angular/angular-route.min.js"></script>
<!--<script src="bower-components/angular/angular-scroll.js"></script>-->
<!--<script src="bower-components/angular/jk-carousel.min.js"></script>-->
<!--<script src="https://code.angularjs.org/1.5.0/angular-animate.min.js"></script>-->
<!--<script src="https://code.angularjs.org/1.5.0/angular-aria.min.js"></script>-->
<!--<script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.5/angular-material.min.js"></script>-->

    <!-- Although we use jQuery 1.4 here, it's tested with the latest too (1.8.3 as of writing this.) -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>

    <!-- The real deal -->
    <script src="dist/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
	<script src="dist/js/tag-it.min.js" type="text/javascript" charset="utf-8"></script>

<!-- jQuery & Other js -->
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="dist/js/app.js"></script>
<!--<script src="js/animation.js"></script>-->
<!--<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>-->
<script src="dist/js/filterSearch/ui-populate-helper.js"></script>
<!-- <script src="dist/js/filterSearch/jquery-1.11.1.js"></script> -->


</body>
</html>