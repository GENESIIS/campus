<!-- 20161024 DN  c10-contacting-us-page test page for -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%@ page import="com.genesiis.xeno.entity.View,java.util.ArrayList" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Contact Us</title>
</head>
<body>
<div align="center" >
<form>
<table>
	<tr>
		<td>
			<table>
					<tr>
					<td colspan=2> <h1>Contact Us</h1> <h3>(Feel Free to talk to us)</h3></td>
					</tr>
					<tr>
						<td><label for="first-name">First name *</label></td>
						<td><label for="last-name">Last name *</label></td>
					</tr>
					<tr>		
						<td><input type="text" name="first-name" value="" size="40"  required /></td>
						<td><input type="text" name="last-name" value="" size="40"  required /></td>

					</tr>
					<tr>
						<td><label for="email">Email *</label></td>
						<td><label for="tel">Phone number</label></td>		
					</tr>
					
					<tr>
						<td><input type="text" name="email" value="" size="40" required /></td>
						<td><input type="text" name="tel" value="" size="40"  required /></td>
					</tr>
					<tr>
						<td><label for="subject">Subject *</label></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<select name="subject"  required>
								<option value="">Select</option>
								<option value="Course inquery">Course inquery</option>
								<option value="Complain">Complain</option>
								<option value="Feedback on this website">Feedback on this website</option>
								<option value="Other">Other</option>
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
						<td><br><label for="human"> <h4>We need to verify you are human *</h4> </label></td>
						<td></td>
					</tr>
					<tr>
						<td><label>How much is 10 - 4 ? </label> <input type="text" name="human" size="40"  required/></td>
						<td></td>
					</tr>
					<tr>
						<td><br><input type="submit" value="Send Message"/></td>
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
	</tr>
</table>
</form>
</div>
</body>
</html>