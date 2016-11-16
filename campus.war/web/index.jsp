<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 20161024 AS c12-social-media-share-panel-as project initiated -->
<!-- 20161025 AS c12-social-media-share-panel-as side share panel and top share panel button and js created -->
<!-- 20161026 AS c12-social-media-share-panel-as linkedIn getlink function embedded   -->
<!-- 20161031 AS c12-social-media-share-panel-as panel links and scripts modified      -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link href="dist/css/contact-buttons.css" rel="stylesheet"
	type="text/css" />
<link href="dist/css/sharepanel.css" rel="stylesheet" type="text/css" />


<link rel="stylesheet"
	href="dist/bower-components/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="dist/bower-components/bootstrap/font-awesome.min.css">


</head>
<body>
	<h1>Hello World..!!</h1>

	<div id="fb-root"></div>
	<!-- 	facebook function script -->
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.8";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>


	<!-- twitter function script -->


	<script type='text/javascript'>
		!function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
					.test(d.location) ? 'http' : 'http';
			if (!d.getElementById(id)) {
				js = d.createElement(s);
				js.id = id;
				js.src = p + '://static1.twitcount.com/js/twitcount.js';
				fjs.parentNode.insertBefore(js, fjs);
			}
		}(document, 'script', 'twitcount_plugins');
	</script>

	<table align="center" style="border: 0px;">
		<tr>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		<tr>
		<tr>
			<td>
				<!-- Place this tag in your head or just before your close body tag. -->
				<script src="https://apis.google.com/js/platform.js" async defer></script>

				<!-- Place this tag where you want the share button to render. -->
				<div class="g-plus" data-action="share" data-annotation="bubble"></div>
			</td>
			<td><div class="fb-share-button" data-layout="button_count"
					data-size="small" data-mobile-iframe="true">
					<a class="fb-xfbml-parse-ignore" target="_blank"
						href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse">Share</a>
				</div></td>
			<td>
				 <a href="https://twitter.com/share"
				class="twitter-share-button" data-count="vertical">Tweet</a>



			</td>
			<td><script src="//platform.linkedin.com/in.js"
					type="text/javascript">
				lang: en_US
			</script> <script
					type="IN/Share" data-counter="right"></script></td>
		</tr>

	</table>


	</div>

	<script src="dist/bower-components/jquery/jquery.min.js"></script>
	<script src="dist/js/jquery.contact-buttons.js"></script>
	<script src="dist/js/share_panel.js"></script>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-36251023-1' ]);
		_gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>

</body>
</html>