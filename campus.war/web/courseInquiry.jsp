<!-- 20161027 AS  c8-make inquiry for course Created courseInquiry.jsp -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src='dist/js/institute/institute.helper.js'></script>
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table align="center">
	<tr>
			<td>You are making an inquiry about ICBT Bsc engineering </td>
			
		</tr>
	<tr>
			<td colspan="2"><h2>Course Inquiry Form</h2></td>
			
		</tr>
		<tr>
			<td>Full Name</td>
			<td><span
				id="fullNametbError" name="fullNametbError"
				style="color: red; font-weight: normal !important;"></span>
				<input type="text" name="fullname" id="fullname"/></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><span
				id="emailtbError" name="emailtbError"
				style="color: red; font-weight: normal !important;"></span>
				<input type="text" name="email" id="email"/></td>
		</tr>
		<tr>
			<td>Country Code</td>
			<td><span
				id="countryCodetbError" name="countryCodetbError"
				style="color: red; font-weight: normal !important;"></span>
				<input type="text" name="countryCode" id="countryCode"/></td>
		</tr>
		<tr>
			<td>Area Code</td>
			<td><span
				id="areaCodetbError" name="areaCodetbError"
				style="color: red; font-weight: normal !important;"></span>
				<input type="text" name="areaCode" id="areaCode"/></td>
		</tr>
		<tr>
			<td>Telephone Number</td>
			<td><span
				id="telephoneNumbertbError" name="telephoneNumbertbError"
				style="color: red; font-weight: normal !important;"></span>
				<input type="text" name="telNum" id="telNum"/></td>
		</tr>
		<tr>
			<td>Inquiry Title</td>
			<td><span
				id="inquiryTitletbError" name="inquiryTitletbError"
				style="color: red; font-weight: normal !important;"></span>
				<input type="text" name="inquiryTitle" id="inquiryTitle"/></td>
		</tr>
		<tr>
			<td>Inquiry</td>
			<td><span
				id="inquirytbError" name="inquirytbError"
				style="color: red; font-weight: normal !important;"></span>
			<textarea rows="25" cols="80" name="inquiry" id="inquiry"></textarea>
			<input type="hidden" value="7" name="programmeCode">
			</td>
		</tr>
		<tr>
			<td></td>
			<td><div class="g-recaptcha" data-sitekey="6LdsagoUAAAAALS1tjjqyHe-7EvIIJF1kaKo-Pmw"></div></td>
		</tr>
		<tr>
			<td></td>
			<td><button type="button" name="" onclick="sendCourseInquiry()">Submit </button></td>
		</tr>
	</table>
</body>
</html>