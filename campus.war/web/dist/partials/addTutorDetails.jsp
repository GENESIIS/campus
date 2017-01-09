<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Profile</title>
</head>
<body>
	<!-- 	onsubmit="return (validateTutorFileds())" -->
	<form action="/TutorController" method="post"
		onsubmit="return (validateTutorFileds())">
		
		<c:forEach var="tutorList" items="${tutor}">
			<c:set var="username" value="${tutorList[0]}" scope="session" />
			<c:set var="password" value="${tutorList[1]}" scope="session" />
		</c:forEach>
		
		
		<table align="center">
			<tr>
				<td>
					<h2 id="message">${message}</h2>
				</td>
			</tr>
			<tr>
				<td>
					<h2>Basic Info</h2>
				</td>
			</tr>
			<tr>
			<td>${username}</td>
			<td>${password}</td>
				<td>First Name * </td>
				<td><input type="text" name="firstname" id="firstName"
					maxlength="20" onchange="clearField('firstNameError')" /><span id="firstNameError" style="color: red" > </span></td>
			</tr>
			<tr>
				<td>Middle Name </td>
				<td><input type="text" name="middlename" id="middleName"
					maxlength="20" onchange="clearField('middleNameError')" /><span id="middleNameError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Last Name * </td>
				<td><input type="text" name="lastname" id="lastName"
					maxlength="20" onchange="clearField('lastNameError')" /><span id="lastNameError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Gender * </td>
				<td><input type="radio" name="gender" value="1" id="radioMale"
					checked> Male<br> <input type="radio" name="gender"
					value="2" id="radioFemale"> Female<br></td>
			</tr>
			<tr>
				<td>Experience </td>
				<td><textarea rows="10" cols="26" name="experience"
						id="experience" onchange="clearField('experienceError')"></textarea><span id="experienceError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>About Me </span></td>
				<td><textarea rows="10" cols="26" name="aboutMe" id="aboutMe"
						onchange="clearField('aboutMeError')"></textarea><span id="aboutMeError" style="color: red" ></td>
			</tr>
			<tr>
				<td><h2>Contact Info</h2></td>
			</tr>
			<tr>
				<td>Country * </td>
				<td> <select
					name="countryDetails" id="countryDetails" onchange="clearField('countryError')">
						<option></option>
				</select><span id="countryError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Town* </td>
				<td> <select name="townDetails"
					id="townDetails" onchange="clearField('townError')">
						<option></option>
				</select><span id="townError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Mobile * </td>
				<td>
				<input type="text"
					name="mobileCountryCode" id="mobileCountryCode" maxlength="4"
					onchange="clearField('mobileError')" readonly/><span id="mobileError" style="color: red" ></span></td>
			
				<td>
				<input type="text"
					name="mobileNetworkCode" id="mobileNetworkCode" maxlength="11"
					onchange="clearField('mobileNetworkError')"/><span id="mobileNetworkError" style="color: red" ></span></td>
	
				<td></span>
				<input type="text"
					name="mobileNumber" id="mobileNumber" maxlength="11"
					onchange="clearField('mobileNumberError')" /><span id="mobileNumberError" style="color: red" ></td>
			</tr>
			<tr>
				<td>Land * </td>
				<td>
				<input type="text"
					name="landCountryCode" id="landCountryCode" maxlength="4"
					onchange="clearField('landError')" readonly/><span id="landError" style="color: red" ></span></td>
			
				<td>
				<input type="text"
					name="landAreaCode" id="landAreaCode" maxlength="11"
					onchange="clearField('landAreaCodeError')" /><span id="landAreaCodeError" style="color: red" ></span></td>
		
				<td>
				<input type="text" name="landNumber" id="landNumber"
					maxlength="11" onchange="clearField('landNumberError')" /><span id="landNumberError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Address Line 1 * </td>
				<td><input type="text" name="address1" id="address1"
					maxlength="30" onchange="clearField('address1Error')" /><span id="address1Error" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Address line 2 </span></td>
				<td><input type="text" name="address2" id="address2"
					maxlength="30" /></td>
			</tr>
			<tr>
				<td>Address line 3 </span></td>
				<td><input type="text" name="address3" id="address3"
					maxlength="30" /></td>
			</tr>

			<tr>
				<td>Web link </td>
				<td><input type="text" name="weblink" id="weblink" onchange="clearField('weblinkError')" /><span id="weblinkError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Facebook </span></td>
				<td><input type="text" name="facebook" id="facebook" onchange="clearField('facebookError')" /><span id="facebookError" style="color: red" ></td>
			</tr>
			<tr>
				<td>LinkedIn </td>
				<td><input type="text" name="linkedin" id="linkedin" onchange="clearField('linkedInError')" /><span id="linkedInError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Twitter </td>
				<td><input type="text" name="twitter" id="twitter" onchange="clearField('twitterError')" /><span id="twitterError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Instagram </td>
				<td><input type="text" name="instagram" id="instagram" onchange="clearField('instagramError')" /><span id="instagramError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Myspace </td>
				<td><input type="text" name="myspace" id="myspace" onchange="clearField('mySpaceError')" /><span id="mySpaceError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>WhatsApp </td>
				<td><input type="text" name="whatsapp" id="whatsapp"
					maxlength="10" onchange="clearField('whatsappError')" /><span id="whatsappError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Viber </td>
				<td><input type="text" name="viber" id="viber" maxlength="10" onchange="clearField('viberError')" /><span id="viberError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td><h2>Account Info</h2></td>
			</tr>
			<tr>
				<td>Email * </td>
				<td><input type="text" name="email" id="email"
					onchange="clearField('emailError')" /><span id="emailError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Username * </td>
				<td><input type="text" name="username" id="username"
					maxlength="50" onchange="clearField('usernameError')" /><span id="usernameError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Password * </td>
				<td><input type="password" name="password" id="password"
					maxlength="20" onchange="clearField('passwordError')" /><span id="passwordError" style="color: red" ></span></td>
			</tr>
			<tr>
				<td>Confirm Password * </td>
				<td><input type="password" name="confirmPassword"
					id="confirmPassword" maxlength="20"
					onchange="clearField('confirmPasswordError')" /><span id="confirmPasswordError" style="color: red" ></span></td>
			</tr>

			<tr>
				<td>
					<button type="submit" name="CCO" id="CCO" value="ATPD"
						class="btn btn-info navbar-btn">Save</button>
				</td>
			</tr>
		</table>

	</form>
	
	
	<script type="text/javascript"
		src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/tutor-helper.js"></script>
	<script src="/dist/js/validator.js"></script>
	
</body>
</html>