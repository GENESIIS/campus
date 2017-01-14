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
				<td>First Name *</td>
				<td><input type="text" name="firstname" id="firstName"
					maxlength="20" onchange="clearField('firstNameError')"
					value="${tutor.firstName}" /><span id="firstNameError"
					style="color: red"> </span></td>
			</tr>
			<tr>
				<td>Middle Name</td>
				<td><input type="text" name="middlename" id="middleName"
					maxlength="20" onchange="clearField('middleNameError')"
					value="${tutor.middleName}" /><span id="middleNameError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Last Name *</td>
				<td><input type="text" name="lastname" id="lastName"
					maxlength="20" onchange="clearField('lastNameError')"
					value="${tutor.lastName}" /><span id="lastNameError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Gender *</td>
				<td><c:choose>
						<c:when test="${tutor.gender == 1}">
							<input type="radio" name="gender" value="1" id="radioMale" checked> Male<br>
							<input type="radio" name="gender" value="2" id="radioFemale"> Female<br>
						</c:when> 
						<c:when test="${tutor.gender == 2}">
							<input type="radio" name="gender" value="1" id="radioMale"> Male<br>
							<input type="radio" name="gender" value="2" id="radioFemale" checked> Female<br>
						</c:when> 
						<c:otherwise>
							<input type="radio" name="gender" value="1" id="radioMale" checked> Male<br>
							<input type="radio" name="gender" value="2" id="radioFemale"> Female<br>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Experience</td>
				<td><textarea rows="10" cols="26" name="experience"
						id="experience" onchange="clearField('experienceError')" ><c:out value="${tutor.experience}"/></textarea><span
					id="experienceError" style="color: red"></span></td>
			</tr>
			<tr>
				<td>About Me </span></td>
				<td><textarea rows="10" cols="26" name="aboutMe" id="aboutMe"
						onchange="clearField('aboutMeError')"><c:out value="${tutor.description}"/></textarea><span
					id="aboutMeError" style="color: red"></td>
			</tr>
			<tr>
				<td><h2>Contact Info</h2></td>
			</tr>
			<tr>
				<td>Country *</td>
	<script type="text/javascript" src="js/tutor-helper.js"></script>
    <script type="text/javascript">
       testing();
    </script>
				<td><select name="countryDetails" id="countryDetails"
					onchange="clearField('countryError')">
						<option></option>
				</select><span id="countryError" style="color: red"></span></td>
			</tr>
			<tr>
				<td>Town*</td>
				<td><select name="townDetails" id="townDetails"
					onchange="clearField('townError')">
						<option></option>
				</select><span id="townError" style="color: red"></span></td>
			</tr>
			<tr>
				<td>Mobile *</td>
				<td><input type="text" name="mobileCountryCode"
					id="mobileCountryCode" maxlength="5"
					onchange="clearField('mobileError')" readonly
					value="${tutor.mobileCountryCode}" /><span id="mobileError"
					style="color: red"></span></td>

				<td><input type="text" name="mobileNetworkCode"
					id="mobileNetworkCode" maxlength="10"
					onchange="clearField('mobileNetworkError')"
					value="${tutor.mobileNetworkCode}" /><span id="mobileNetworkError"
					style="color: red"></span></td>

				<td></span> <input type="text" name="mobileNumber" id="mobileNumber"
					maxlength="11" onchange="clearField('mobileNumberError')"
					value="${tutor.mobileNumber}" /><span id="mobileNumberError"
					style="color: red"></td>
			</tr>
			<tr>
				<td>Land *</td>
				<td><input type="text" name="landCountryCode"
					id="landCountryCode" maxlength="5"
					onchange="clearField('landError')" readonly
					value="${tutor.landCountryCode}" /><span id="landError"
					style="color: red"></span></td>

				<td><input type="text" name="landAreaCode" id="landAreaCode"
					maxlength="10" onchange="clearField('landAreaCodeError')"
					value="${tutor.landAreaCode}" /><span id="landAreaCodeError"
					style="color: red"></span></td>

				<td><input type="text" name="landNumber" id="landNumber"
					maxlength="10" onchange="clearField('landNumberError')"
					value="${tutor.landNumber}" /><span id="landNumberError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Address Line 1 *</td>
				<td><input type="text" name="address1" id="address1"
					maxlength="30" onchange="clearField('address1Error')"
					value="${tutor.addressLine1}" /><span id="address1Error"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Address line 2 </span></td>
				<td><input type="text" name="address2" id="address2"
					maxlength="30" value="${tutor.addressLine2}" /></td>
			</tr>
			<tr>
				<td>Address line 3 </span></td>
				<td><input type="text" name="address3" id="address3"
					maxlength="30" value="${tutor.addressLine3}" /></td>
			</tr>

			<tr>
				<td>Web link</td>
				<td><input type="text" name="weblink" id="weblink"
					onchange="clearField('weblinkError')" value="${tutor.webLink}" /><span
					id="weblinkError" style="color: red"></span></td>
			</tr>
			<tr>
				<td>Facebook </span></td>
				<td><input type="text" name="facebook" id="facebook"
					onchange="clearField('facebookError')"
					value="${tutor.facebookLink}" /><span id="facebookError"
					style="color: red"></td>
			</tr>
			<tr>
				<td>LinkedIn</td>
				<td><input type="text" name="linkedin" id="linkedin"
					onchange="clearField('linkedInError')"
					value="${tutor.linkedInLink}" /><span id="linkedInError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Twitter</td>
				<td><input type="text" name="twitter" id="twitter"
					onchange="clearField('twitterError')"
					value="${tutor.twitterNumber}" /><span id="twitterError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Instagram</td>
				<td><input type="text" name="instagram" id="instagram"
					onchange="clearField('instagramError')"
					value="${tutor.instagramId}" /><span id="instagramError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Myspace</td>
				<td><input type="text" name="myspace" id="myspace"
					onchange="clearField('mySpaceError')" value="${tutor.mySpaceId}" /><span
					id="mySpaceError" style="color: red"></span></td>
			</tr>
			<tr>
				<td>WhatsApp</td>
				<td><input type="text" name="whatsapp" id="whatsapp"
					maxlength="20" onchange="clearField('whatsappError')"
					value="${tutor.whatsAppId}" /><span id="whatsappError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Viber</td>
				<td><input type="text" name="viber" id="viber" maxlength="20"
					onchange="clearField('viberError')" value="${tutor.viberNumber}" /><span
					id="viberError" style="color: red"></span></td>
			</tr>
			<tr>
				<td><h2>Account Info</h2></td>
			</tr>
			<tr>
				<td>Email *</td>
				<td><input type="text" name="email" id="email"
					onchange="clearField('emailError')" value="${tutor.emailAddress}" /><span
					id="emailError" style="color: red"></span></td>
			</tr>
			<tr>
				<td>Username *</td>
				<td><input type="text" name="username" id="username"
					maxlength="20" onchange="clearField('usernameError')"
					value="${tutor.username}" /><span id="usernameError"
					style="color: red"></span></td>
			</tr>
			<tr>
				<td>Password *</td>
				<td><input type="password" name="password" id="password"
					maxlength="20" onchange="clearField('passwordError')" /><span
					id="passwordError" style="color: red"></span></td>
			</tr>
			<tr>
				<td>Confirm Password *</td>
				<td><input type="password" name="confirmPassword"
					id="confirmPassword" maxlength="20"
					onchange="clearField('confirmPasswordError')" /><span
					id="confirmPasswordError" style="color: red"></span></td>
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