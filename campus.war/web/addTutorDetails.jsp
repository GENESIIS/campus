<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Profile</title>
<script type="text/javascript"
	src="\dist\bower-components\jquery\jquery.min.js"></script>
<script src="/dist/js/tutor-helper.js"></script>
<script src="/dist/js/validator.js"></script>
</head>
<body>
<!-- 	onsubmit="return (validateTutorFileds())" -->
	<form action="TutorController" method="post">
		<table align="center">
			<tr>
				<td>
					<h2>Basic Info</h2>
				</td>
			</tr>
			<tr>
				<td>First Name <span id="firstNameError"></span></td>
				<td><input type="text" name="firstname" id="firstName"
					maxlength="20" /></td>
			</tr>
			<tr>
				<td>Middle Name <span id="middleNameError"></span></td>
				<td><input type="text" name="middlename" id="middleName"
					maxlength="20" /></td>
			</tr>
			<tr>
				<td>Last Name <span id="lastNameError"></span></td>
				<td><input type="text" name="lastname" id="lastName"
					maxlength="20" /></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><input type="radio" name="gender" value="1" id="radioMale"
					checked> Male<br> <input type="radio" name="gender"
					value="2" id="radioFemale"> Female<br></td>
			</tr>
			<tr>
				<td>Experience <span id="experienceError"></span></td>
				<td><textarea rows="10" cols="26" name="experience"
						id="experience"></textarea></td>
			</tr>
			<tr>
				<td>About Me <span id="aboutMeError"></span></td>
				<td><textarea rows="10" cols="26" name="aboutMe" id="aboutMe"></textarea></td>
			</tr>
			<tr>
				<td><h2>Contact Info</h2></td>
			</tr>
			<tr>
				<td>Country</td>
				<td>
				<select name="countryDetails" id="countryDetails" onchange="">
						<option></option>
				</select>
				</td>
			</tr>
			<tr>
				<td>Town</td>
				<td><select name="townDetails" id="townDetails">
						<option></option>
				</select></td>
			</tr>
			<tr>
				<td>Mobile</td>
				<td>Country Code<span id="mobCoutryCodeError"></span> <input
					type="text" name="mobileCountryCode" id="mobileCountryCode"
					maxlength="4" /> Network Code<span id="mobileNetworkCodeError"></span>
					<input type="text" name="mobileNetworkCode" id="mobileNetworkCode"
					maxlength="3" /> Phone Number <span id="mobileError"></span><input
					type="text" name="mobileNumber" id="mobileNumber" maxlength="7" />
				</td>
			</tr>
			<tr>
				<td>Home <span id="landError"></span></td>
				<td>Country Code<span id="landCoutryCodeError"></span> <input
					type="text" name="landCountryCode" id="landCountryCode"
					maxlength="4" /> Area Code<span id="landAreaCodeError"></span><input
					type="text" name="landAreaCode" id="landAreaCode" maxlength="3" />
					Phone Number<span id="landError"></span><input type="text"
					name="landNumber" id="landNumber" maxlength="7" /></td>
			</tr>
			<tr>
				<td>Address 1 <span id="address1Error"></span></td>
				<td><input type="text" name="address1" id="address1"
					maxlength="30" /></td>
			</tr>
			<tr>
				<td>Address 2 <span id="address2Error"></span></td>
				<td><input type="text" name="address2" id="address2"
					maxlength="30" /></td>
			</tr>
			<tr>
				<td>Address 3 <span id="address3Error"></span></td>
				<td><input type="text" name="address3" id="address3"
					maxlength="30" /></td>
			</tr>
			
			<tr>
				<td>Web link <span id="weblinkError"></span></td>
				<td><input type="text" name="weblink" id="weblink" /></td>
			</tr>
			<tr>
				<td>Facebook <span id="facebookError"></span></td>
				<td><input type="text" name="facebook" id="facebook" /></td>
			</tr>
			<tr>
				<td>LinkedIn <span id="linkedInError"></span></td>
				<td><input type="text" name="linkedin" id="linkedin" /></td>
			</tr>
			<tr>
				<td>Twitter <span id="twitterError"></span></td>
				<td><input type="text" name="twitter" id="twitter" /></td>
			</tr>
			<tr>
				<td>Instagram <span id="instagramError"></span></td>
				<td><input type="text" name="instagram" id="instagram" /></td>
			</tr>
			<tr>
				<td>Myspace <span id="mySpaceError"></span></td>
				<td><input type="text" name="myspace" id="myspace" /></td>
			</tr>
			<tr>
				<td>whatsApp <span id="whatsappError"></span></td>
				<td><input type="text" name="whatsapp" id="whatsapp"
					maxlength="10" /></td>
			</tr>
			<tr>
				<td>Viber <span id="viberError"></span></td>
				<td><input type="text" name="viber" id="viber" maxlength="10" /></td>
			</tr>
			<tr>
				<td><h2>Account Info</h2></td>
			</tr>
			<tr>
				<td>Email <span id="emailError"></span></td>
				<td><input type="text" name="email" id="email" /></td>
			</tr>
			<tr>
				<td>Username <span id="usernameError"></span></td>
				<td><input type="text" name="username" id="username"
					maxlength="10" /></td>
			</tr>
			<tr>
				<td>Password <span id="passwordError"></span></td>
				<td><input type="password" name="password" id="password"
					maxlength="20" /></td>
			</tr>
			<tr>
				<td>Confirm Password <span id="confirmPasswordError"></span></td>
				<td><input type="password" name="confirmPassword"
					id="confirmPassword" maxlength="20" /></td>
			</tr>

			<tr>
				<td>
					<button type="submit" name="CCO" id="CCO" value="ATPD"
						class="btn btn-info navbar-btn">Save</button>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>