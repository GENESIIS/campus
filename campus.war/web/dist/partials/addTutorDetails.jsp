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
					<h2 id="message" style="color: red">${message}</h2>
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
						value="${tutorList[0]}" /><span id="firstNameError"
						style="color: red"> ${firstNameError} </span></td>
				</tr>
				<tr>
					<td>Middle Name</td>
					<td><input type="text" name="middlename" id="middleName"
						maxlength="20" onchange="clearField('middleNameError')"
						value="${tutorList[1]}" /><span id="middleNameError"
						style="color: red"> ${middleNameError} </span></td>
				</tr>
				<tr>
					<td>Last Name *</td>
					<td><input type="text" name="lastname" id="lastName"
						maxlength="20" onchange="clearField('lastNameError')"
						value="${tutorList[2]}" /><span id="lastNameError"
						style="color: red"> ${lastNameError} </span></td>
				</tr>
				<tr>
					<td>Gender *</td>
					<td><c:choose>
							<c:when test="${tutorList[3] == 1}">
								<input type="radio" name="gender" value="1" id="radioMale" checked> Male<br>
								<input type="radio" name="gender" value="2" id="radioFemale"> Female<br>
							</c:when> 
							<c:when test="${tutorList[3] == 2}">
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
							id="experience" onchange="clearField('experienceError')" ><c:out value="${tutorList[4]}"/></textarea><span
						id="experienceError" style="color: red"> ${experienceError} </span></td>
				</tr>
				<tr>
					<td>About Me </span></td>
					<td><textarea rows="10" cols="26" name="aboutMe" id="aboutMe"
							onchange="clearField('aboutMeError')"><c:out value="${tutorList[5]}"/></textarea><span
						id="aboutMeError" style="color: red"> ${aboutMeError} </td>
				</tr>
				<tr>
					<td><h2>Contact Info</h2></td>
				</tr>
				<tr>
					<td>Country *</td>
					<c:if test = "${tutorList[6] != null}"	>			
						<td>${tutorList[6]}</td>
						<td><input type="hidden" name="countryHidden" id="countryHidden" value="${tutorList[6]}" /></td>
					</c:if>
					<td><select name="countryDetails" id="countryDetails"
						onchange="clearField('countryError')">
							<option></option>
					</select><span id="countryError" style="color: red"> ${countryError} </span></td>
				</tr>
				<tr>
					<td>Town*</td>
					<c:if test = "${tutorList[7] != null }"	>			
						<td>${tutorList[7]}</td>
						<td><input type="hidden" name="townHidden" id="townHidden" value="${tutorList[8]}" /></td>
					</c:if>
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
						value="${tutorList[9]}" /><span id="mobileError"
						style="color: red"> ${mobileError} </span></td>
	
					<td><input type="text" name="mobileNetworkCode"
						id="mobileNetworkCode" maxlength="10"
						onchange="clearField('mobileNetworkError')"
						value="${tutorList[10]}" /><span id="mobileNetworkError"
						style="color: red"> ${mobileNetworkError} </span></td>
	
					<td></span> <input type="text" name="mobileNumber" id="mobileNumber"
						maxlength="11" onchange="clearField('mobileNumberError')"
						value="${tutorList[11]}" /><span id="mobileNumberError"
						style="color: red"> ${mobileNumberError} </td>
				</tr>
				<tr>
					<td>Land *</td>
					<td><input type="text" name="landCountryCode"
						id="landCountryCode" maxlength="5"
						onchange="clearField('landError')" readonly
						value="${tutorList[12]}" /><span id="landError"
						style="color: red"> ${landError} </span></td>
	
					<td><input type="text" name="landAreaCode" id="landAreaCode"
						maxlength="10" onchange="clearField('landAreaCodeError')"
						value="${tutorList[13]}" /><span id="landAreaCodeError"
						style="color: red"> ${landAreaCodeError} </span></td>
	
					<td><input type="text" name="landNumber" id="landNumber"
						maxlength="10" onchange="clearField('landNumberError')"
						value="${tutorList[14]}" /><span id="landNumberError"
						style="color: red"> ${landNumberError} </span></td>
				</tr>
				<tr>
					<td>Address Line 1 *</td>
					<td><input type="text" name="address1" id="address1"
						maxlength="30" onchange="clearField('address1Error')"
						value="${tutorList[15]}" /><span id="address1Error"
						style="color: red"> ${address1Error} </span></td>
				</tr>
				<tr>
					<td>Address line 2 </span></td>
					<td><input type="text" name="address2" id="address2"
						maxlength="30" value="${tutorList[16]}" /></td>
				</tr>
				<tr>
					<td>Address line 3 </span></td>
					<td><input type="text" name="address3" id="address3"
						maxlength="30" value="${tutorList[17]}" /></td>
				</tr>
	
				<tr>
					<td>Web link</td>
					<td><input type="text" name="weblink" id="weblink"
						onchange="clearField('weblinkError')" value="${tutorList[18]}" /><span
						id="weblinkError" style="color: red"> ${weblinkError} </span></td>
				</tr>
				<tr>
					<td>Facebook </span></td>
					<td><input type="text" name="facebook" id="facebook"
						onchange="clearField('facebookError')"
						value="${tutorList[19]}" /><span id="facebookError"
						style="color: red"> ${facebookError} </td>
				</tr>
				<tr>
					<td>LinkedIn</td>
					<td><input type="text" name="linkedin" id="linkedin"
						onchange="clearField('linkedInError')"
						value="${tutorList[20]}" /><span id="linkedInError"
						style="color: red"> ${linkedInError} </span></td>
				</tr>
				<tr>
					<td>Twitter</td>
					<td><input type="text" name="twitter" id="twitter"
						onchange="clearField('twitterError')"
						value="${tutorList[21]}" /><span id="twitterError"
						style="color: red"> ${twitterError} </span></td>
				</tr>
				<tr>
					<td>Instagram</td>
					<td><input type="text" name="instagram" id="instagram"
						onchange="clearField('instagramError')"
						value="${tutorList[22]}" /><span id="instagramError"
						style="color: red"> ${instagramError} </span></td>
				</tr>
				<tr>
					<td>Myspace</td>
					<td><input type="text" name="myspace" id="myspace"
						onchange="clearField('mySpaceError')" value="${tutorList[23]}" /><span
						id="mySpaceError" style="color: red"> ${mySpaceError} </span></td>
				</tr>
				<tr>
					<td>WhatsApp</td>
					<td><input type="text" name="whatsapp" id="whatsapp"
						maxlength="20" onchange="clearField('whatsappError')"
						value="${tutorList[24]}" /><span id="whatsappError"
						style="color: red"> ${whatsappError} </span></td>
				</tr>
				<tr>
					<td>Viber</td>
					<td><input type="text" name="viber" id="viber" maxlength="20"
						onchange="clearField('viberError')" value="${tutorList[25]}" /><span
						id="viberError" style="color: red"> ${viberError} </span></td>
				</tr>
				<tr>
					<td><h2>Account Info</h2></td>
				</tr>
				<tr>
					<td>Email *</td>
					<td><input type="text" name="email" id="email"
						onchange="clearField('emailError')" value="${tutorList[26]}" /><span
						id="emailError" style="color: red"> ${emailError} </span></td>
				</tr>
				<tr>
					<td>Username *</td>
					<td><input type="text" name="username" id="username"
						maxlength="20" onchange="clearField('usernameError')"
						value="${tutorList[27]}" /><span id="usernameError"
						style="color: red"> ${usernameError} </span></td>
				</tr>
				<tr>
					<td>Password *</td>
					<td><input type="password" name="password" id="password"
						maxlength="20" onchange="clearField('passwordError')" /><span
						id="passwordError" style="color: red"> ${passwordError} </span></td>
				</tr>
				<tr>
					<td>Confirm Password *</td>
					<td><input type="password" name="confirmPassword"
						id="confirmPassword" maxlength="20"
						onchange="clearField('confirmPasswordError')" /><span
						id="confirmPasswordError" style="color: red"> ${confirmPasswordError} </span></td>
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