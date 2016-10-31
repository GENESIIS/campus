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
    
    
    <!-- 2 CSS files are required: -->
    <!--   * Tag-it's base CSS (jquery.tagit.css). -->
    <!--   * Any theme CSS (either a jQuery UI theme such as "flick", or one that's bundled with Tag-it, e.g. tagit.ui-zendesk.css as in this example.) -->
    <!-- The base CSS and tagit.ui-zendesk.css theme are scoped to the Tag-it widget, so they shouldn't affect anything else in your site, unlike with jQuery UI themes. -->
    <link href="dist/css/jquery.tagit.css" rel="stylesheet" type="text/css">
    <link href="dist/css/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">
    <!-- If you want the jQuery UI "flick" theme, you can use this instead, but it's not scoped to just Tag-it like tagit.ui-zendesk is: -->
    <!--   <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css"> -->

    <!-- jQuery and jQuery UI are required dependencies. -->
    <!-- Although we use jQuery 1.4 here, it's tested with the latest too (1.8.3 as of writing this.) -->
    <script src="dist/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="dist/js/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>

    <!-- The real deal -->
    <script src="dist/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
    
    
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


<!-- jQuery & Other js -->
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<script src="dist/bower-components/bootstrap/bootstrap.min.js"></script>
<script src="dist/js/app.js"></script>
<!--<script src="js/animation.js"></script>-->
<!--<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>-->
<script src="dist/js/filterSearch/ui-populate-helper.js"></script>
<!-- <script src="dist/js/filterSearch/jquery-1.11.1.js"></script> -->

	<script type="text/javascript">
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
	</script>
</body>
</html>