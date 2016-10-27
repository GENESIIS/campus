<!-- 20161027 AS  c8-make inquiry for course Created courseInquiry.jsp -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='https://www.google.com/recaptcha/api.js'></script>
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
			<td><input type="text" name="fname" id="fullname"/></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="email" id="email"/></td>
		</tr>
		<tr>
			<td>Country Code</td>
			<td><input type="text" name="countryCode" id="countryCode"/></td>
		</tr>
		<tr>
			<td>Area Code</td>
			<td><input type="text" name="areaCode" id="areaCode"/></td>
		</tr>
		<tr>
			<td>Telephone Number</td>
			<td><input type="text" name="telNum" id="telNum"/></td>
		</tr>
		<tr>
			<td>Inquiry Title</td>
			<td><input type="text" name="inquiryTitle" id="inquiryTitle"/></td>
		</tr>
		<tr>
			<td>Inquiry</td>
			<td>
			<textarea rows="25" cols="80" name="inquiry" id="inquiry"></textarea>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><div class="g-recaptcha" data-sitekey="6LfDaQoUAAAAAJ9EWto6h6Dsd3TtQC1PcGFhc__c"></div></td>
		</tr>
		<tr>
			<td></td>
			<td><button type="submit" name="" >Submit </button></td>
		</tr>
	</table>
</body>
</html>