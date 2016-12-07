<!-- 20161206 PN INIT TagIt.jsp class for test bootstrap tagIt example. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Student Skills and Interests.</title>
    <meta name="robots" content="index, follow" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />    
    <link rel="stylesheet" href="dist/bower-components/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="dist/tagit/bootstrap-tagsinput.css">
    <style>
    .accordion { margin-top:-19px; }
    </style>
  </head>
  <body>
    <div class="container">
      <section id="examples">
      <h3>Skills</h3>     
        <div class="example example_objects_as_tags">
          <div class="bs-docs-example">
            <input type="text" id="studentSkills" name="studentSkills"/>
          </div>
				<button onclick="addSkillDetails()">Save Skills </button>
		</div>
		
		<br><br>
		
		<h3>Interests</h3>     
        <div class="example example_objects_as_tags">
          <div class="bs-docs-example">
            <input type="text" id="studentInterests" name="studentInterests"/>
          </div>
				<button onclick="addInterestsDetails()">Save Interests </button>
		</div>
		
      </section>
    </div>

    <script src="dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.20/angular.min.js"></script>
    <script src="dist/tagit/bootstrap-tagsinput.min.js"></script>
    <script src="dist/tagit/bootstrap-tagsinput-angular.min.js"></script>
    <script src="dist/tagit/assets/app_bs2.js"></script>
    <script src="dist/tagit/assets/app.js"></script>
  </body>
</html>

