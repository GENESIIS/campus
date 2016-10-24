<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link href="dist/css/fshare.css" rel="stylesheet" type="text/css" />
<script src="dist/js/fshare.js" type="text/javascript"></script>
<script src="dist/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//             $('.quake-slider').quake({
		//                 effects: ['swirlFadeIn', 'swirlFadeOut', 'randomFade', 'linearPeal', 'linearPealReverse', 'swirlFadeIn', 'swirlFadeOut',
		//             'diagonalFade', 'blind', 'blindFade', 'explode', 'swirlFadeIn',
		//             'mixBarsFancy', 'blindFadeReverse', 'slideIn', 'slideInFancy', 'swirlFadeOut'],
		//                 thumbnails: true,
		//                 captionOpacity: '0.3',
		//                 captionOrientations: ['top', 'right', 'bottom', 'left']
		//             });

		$('#floating-bar').fshare({
			theme : 'compact',
			upperLimitElementId : 'upper-limit-element',
			lowerLimitElementId : 'lower-limit-element'
		});
	});
</script>


</head>
<body>
	<h1>Hello World..!!</h1>


	<div>

		<iframe
			src="https://www.facebook.com/plugins/share_button.php?href=http%3A%2F%2Ficehrm-hosted.gamonoid.com%2Flogin.php&layout=button&size=large&mobile_iframe=true&appId=539799292734817&width=72&height=28"
			width="72" height="28" style="border: none; overflow: hidden"
			scrolling="no" frameborder="0" allowTransparency="true"></iframe>
	</div>
	<div id="floating-bar"></div>
<br><br>
	<div>
		<a href="https://plus.google.com/share?url={URL}"
			onclick="javascript:window.open(this.href,
  '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"><img
			src="https://www.gstatic.com/images/icons/gplus-64.png"
			alt="Share on Google+" /></a>
	</div>

</body>
</html>