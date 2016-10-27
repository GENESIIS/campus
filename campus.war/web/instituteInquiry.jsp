<!-- 20161026 CM  c9-make inquiry for institute Created instituteInquiry.jsp -->
<!-- 20161027 CM  c9-make inquiry for institute Edited instituteInquiry.jsp -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
	<tr>
			<td>You are making an inquiry about ICBT</td>
			<td><label id="institueCode">1</label></td>
		</tr>
	<tr>
			<td colspan="2"><h2>Institute Inquiry Form</h2></td>
			
		</tr>
		<tr>
			<td>Full Name</td>
			<td><input type="text" name="fullname" id="fullname"/></td>
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
			<textarea rows="5" cols="20" name="inquiry" id="inquiry"></textarea>
			</td>
		</tr>
		<tr>
			<td><button type="button" onclick="makeInquiry()">
					<i class="glyphicon glyphicon-floppy-disk"></i> Save
				</button></td>
			<td></td>
		</tr>
	</table>
</body>
</html>