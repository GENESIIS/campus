<!-- 20161026 CM  c9-make inquiry for institute Created instituteInquiry.jsp -->
<!-- 20161027 CM  c9-make inquiry for institute Edited instituteInquiry.jsp -->
<!-- 20161028 CM  c9-make inquiry for institute Edited instituteInquiry.jsp -->
<!-- 20161028 CM  c9-make inquiry for institute added message attribute -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="dist/bower-components/jquery/jquery.min.js"></script>
<script src="dist/js/campus/validation/validation.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<!-- Javascript method for validate form submission. Handle duplicate form submission-->

<title>Institute Inquiry</title>
</head>
<body>
	<form method="POST" onsubmit="return (validateInstituteInquiryFileds())" action="InstituteController">
		
		<table align="center">
			<tr>
				<td><input type="hidden" value="1"
					name="courseProviderCode"> 
	<%-- 				<input type="hidden" value="${param.courseProviderCode}"
					name="courseProviderCode"> --%>
					<input type="hidden" value="1"
					name="studentCode">
			</tr>
			<tr>
				<td colspan="2"><h2>Institute Inquiry Form</h2></td>

			</tr>
			<tr>
				<td colspan="2"><div
						style="color: red; font-weight: normal !important;">
						<c:out value="${message }"></c:out>
					</div></td>

			</tr>
			<tr>
				<td>Full Name</td>
				<td><span id="fullNametbError" name="fullNametbError"
					style="color: red; font-weight: normal !important;"></span> <input
					type="text" name="fullname" id="fullname" value=""  /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><span id="emailtbError" name="emailtbError"
					style="color: red; font-weight: normal !important;"></span> <input
					type="text" name="email" id="email"  /></td>
			</tr>
			<tr>
				<td>Country Code</td>
				<td><span id="countryCodetbError" name="countryCodetbError"
					style="color: red; font-weight: normal !important;"></span> <input
					type="text" name="countryCode" id="countryCode" required /></td>
			</tr>
			<tr>
				<td>Area Code</td>
				<td><span id="areaCodetbError" name="areaCodetbError"
					style="color: red; font-weight: normal !important;"></span> <input
					type="text" name="areaCode" id="areaCode" required /></td>
			</tr>
			<tr>
				<td>Telephone Number</td>
				<td><span id="telephoneNumbertbError"
					name="telephoneNumbertbError"
					style="color: red; font-weight: normal !important;"></span> <input
					type="text" name="telNum" id="telNum" required /></td>
			</tr>
			<tr>
				<td>Inquiry Title</td>
				<td><input type="text" name="inquiryTitle" id="inquiryTitle"
					required /></td>
			</tr>
			<tr>
				<td>Inquiry</td>
				<td><textarea rows="15" cols="50" name="inquiry" id="inquiry"
						required></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><div class="g-recaptcha"
						data-sitekey="6LfDaQoUAAAAAJ9EWto6h6Dsd3TtQC1PcGFhc__c"></div></td>
			</tr>
			<tr>
				<td>
					<!-- <button type="button" onclick="addInstituteInquiry()">
					<i class="glyphicon glyphicon-floppy-disk"></i> Save
				</button> -->
					<button type="submit" name="CCO" id="CCO" value="SII"
						class="pure-button pure-button-primary">Send Inquiry</button>
				</td>
				<td></td>
			</tr>
		</table>
	</form>
</body>
</html>