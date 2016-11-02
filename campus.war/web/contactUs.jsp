<!-- 20161024 DN  c10-contacting-us-page test page for -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
<script src="dist/js/contactUs.js"></script>
<meta charset="utf-8">
<title>Contact Us</title>
</head>
<body>
<div align="center" >

	<h3 style="color: red">
		<c:out value="${requestScope.message}"></c:out>
	</h3>
<form method="post"  name="contactUsForm" action="PublicController" >
	
<table>
	<tr>
		<td>
			<table>
					<tr>
					<td colspan=2> <h1>Contact Us</h1> <h3>(Feel Free to talk to us)</h3></td>
					</tr>
					<tr>
						<td><label for="firstName">First name *</label></td>
						<td><label for="lastName">Last name *</label></td>
					</tr>
					<tr>		
						<td><input type="text" name="firstName" value="" size="40"  required /></td>
						<td><input type="text" name="lastName" value="" size="40"  required /></td>

					</tr>
					<tr>
						<td><label for="emailAddress">Email *</label></td>
						<td><label for="contactNumber">Phone number</label></td>		
					</tr>
					
					<tr>
						<td><input type="email" name="emailAddress" value="" size="40" required /></td>
						<td><input type="text" name="contactNumber" value="" size="40"   /></td>
					</tr>
					<tr>
						<td><label for="subject">Subject *</label></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<select name="subject"  required>
								<option value="">Select</option>
								<option value="courseInquery">Course inquiry</option>
								<option value="complain">Complain</option>
								<option value="feedbackOnThisWebsite">Feedback on this web site</option>
								<option value="other">Other</option>
							</select>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td><label for="message">Message *</label></td>
						<td></td>
					</tr>
					<tr>
						<td><textarea name="message" cols="70" rows="10"  required></textarea></td>
						<td></td>
					</tr>
					<tr>
						<td><br><label for="humanTest"> <h4>We need to verify you are human *</h4> </label></td>
						<td></td>
					</tr>
					<tr>
						<td><label>How much is 10 - 4 ? </label> <input type="text" name="humanTest" size="40"  required/></td>
						<td></td>
					</tr>
					<tr>
						<td><br><button type="submit" name="CCO" id="CCO" value="FBTSA"  onclick="validateForm()">Submit Query</button></td> <!-- FEED BACK TO SUPER ADMIN -->
						<td></td>
					</tr>
				</table>		
		
		</td>
		<td>		
			<table>
				<tr>
					<td><h3>Our Address</h3></td>					
				</tr>
				<tr> 
					<td>Genesiis Software ltd<br>
					park Street <br>
					Colombo 07<br>
					Sri Lanka <br>
					<h4> TP: +94 112 223 156</h4>
					</td>
				</tr>
				<tr>
					<td>Google map</td>
				</tr>			
			</table>		
		</td>
		<td colspan="1">
		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d4710.182831426904!2d79.8563480241412!3d6.9173791392598565!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae2596c8f0d9dd1%3A0xd40004ef11f25f9c!2sGenesiis+Software+(Pvt)+Ltd!5e0!3m2!1sen!2slk!4v1477976478999"
 width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
		
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>