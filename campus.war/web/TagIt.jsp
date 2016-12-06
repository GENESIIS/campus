<!-- 20161206 PN INIT TagIt.jsp class for test bootstrap tagIt example. -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Bootstrap Tags Input</title>
    <meta name="robots" content="index, follow" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="dist/tagit/bootstrap-tagsinput.css">
    <style>
    .accordion { margin-top:-19px; }
    </style>
  </head>
  <body>
    <div class="container">
      <section id="examples">

        <div class="example example_objects_as_tags">
          <h3>Objects as tags</h3>
          <p>
            Instead of just adding strings as tags, bind objects to your tags. This makes it possible to set id values in your input field's value, instead of just the tag's text.
          </p>
          <div class="bs-docs-example">
            <input type="text" />
          </div>
          <table class="table table-bordered table-condensed"><thead><tr><th>statement</th><th>returns</th></tr></thead><tbody><tr><td><code>$("input").val()</code></td><td><pre class="val"><code data-language="javascript"></code></pre></td></tr><tr><td><code>$("input").tagsinput('items')</code></td><td><pre class="items"><code data-language="javascript"></code></pre></td></tr></tbody></table>
        </div>

        <div class="example example_tagclass">
          <h3>Categorizing tags</h3>
          <p>
            You can set a fixed css class for your tags, or determine dynamically by providing a custom function.
          </p>
          <div class="bs-docs-example">
            <input type="text" />
          </div>
          <table class="table table-bordered table-condensed"><thead><tr><th>statement</th><th>returns</th></tr></thead><tbody><tr><td><code>$("input").val()</code></td><td><pre class="val"><code data-language="javascript"></code></pre></td></tr><tr><td><code>$("input").tagsinput('items')</code></td><td><pre class="items"><code data-language="javascript"></code></pre></td></tr></tbody></table>
        </div>
      </section>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.20/angular.min.js"></script>
    <script src="dist/tagit/bootstrap-tagsinput.min.js"></script>
    <script src="dist/tagit/bootstrap-tagsinput-angular.min.js"></script>
    <script src="dist/tagit/assets/app_bs2.js"></script>
    <script src="dist/tagit/assets/app.js"></script>
  </body>
</html>

