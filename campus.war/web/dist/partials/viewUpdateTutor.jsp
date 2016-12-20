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


		<c:forEach var="tutorList" items="${result.collection}">

			<c:set var="code" value="${tutorList[0]}" />
			<c:set var="username" value="${tutorList[1]}" />
			<c:set var="firstname" value="${tutorList[2]}" />
			<c:set var="middlename" value="${tutorList[3]}" />
			<c:set var="lastname" value="${tutorList[4]}" />
			<c:set var="gender" value="${tutorList[5]}" />
			<c:set var="email" value="${tutorList[6]}" />
			<c:set var="landphonecountrycode" value="${tutorList[7]}" />
			<c:set var="landphoneareacode" value="${tutorList[8]}" />
			<c:set var="landphonenumber" value="${tutorList[9]}" />
			<c:set var="mobilephonecountrycode" value="${tutorList[10]}" />
			<c:set var="mobilephonenetworkcode" value="${tutorList[11]}" />
			<c:set var="mobilephonenumber" value="${tutorList[12]}" />
			<c:set var="description" value="${tutorList[13]}" />
			<c:set var="experience" value="${tutorList[14]}" />
			<c:set var="weblink" value="${tutorList[15]}" />
			<c:set var="facebookurl" value="${tutorList[16]}" />
			<c:set var="twitterurl" value="${tutorList[17]}" />
			<c:set var="myspaceurl" value="${tutorList[18]}" />
			<c:set var="linkedinurl" value="${tutorList[19]}" />
			<c:set var="instagramurl" value="${tutorList[20]}" />
			<c:set var="vibernumber" value="${tutorList[21]}" />
			<c:set var="whatsappnumber" value="${tutorList[22]}" />
			<c:set var="address1" value="${tutorList[23]}" />
			<c:set var="address2" value="${tutorList[24]}" />
			<c:set var="address3" value="${tutorList[25]}" />
			<c:set var="town" value="${tutorList[26]}" />
			<c:set var="usertype" value="${tutorList[27]}" />

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
					<td>First Name <span id="firstNameError"></span></td>
					<td><input type="text" name="firstname" id="firstName"
						maxlength="20" onclick="clearField('firstNameError')"
						value="${firstname}" /></td>
				</tr>

				<tr>
					<td>Middle Name <span id="middleNameError"></span></td>
					<td><input type="text" name="middlename" id="middleName"
						maxlength="20" onclick="clearField('middleNameError')"
							value="${middlename}"  /></td>
				</tr>
				<tr>
					<td>Last Name <span id="lastNameError"></span></td>
					<td><input type="text" name="lastname" id="lastName"
						maxlength="20" onclick="clearField('lastNameError')"
							value="${lastname}"  /></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>
						<c:if test="${gender == 1}">
							<input type="radio" name="gender" value="1" id="radioMale"
							checked> Male<br> <input type="radio" name="gender" id="radioFemale"> Female<br>
						</c:if>
						
						<c:if test="${gender == 2}">
							<input type="radio" name="gender" value="1" id="radioMale"> Male<br> 
							<input type="radio" name="gender" id="radioFemale" checked> Female<br>
						</c:if>
						</td>
				</tr>
				<tr>
					<td>Experience <span id="experienceError"></span></td>
					<td><textarea rows="10" cols="26" name="experience"	id="experience" >
							<c:out value="${experience}"/></textarea></td>
				</tr>

				<tr>
					<td>About Me <span id="aboutMeError"></span></td>
					<td><textarea rows="10" cols="26" name="aboutMe" id="aboutMe"
							onclick="clearField('aboutMeError')" >
							<c:out value="${description}"/></textarea></td>
				</tr>
				<tr>
					<td><h2>Contact Info</h2></td>
				</tr>
				<tr>
					<td>Country</td>
					<td><span id="countryError"></span> <select
						name="countryDetails" id="countryDetails">
							<option></option>
					</select></td>
				</tr>
				<tr>
					<td>Town</td>
					<td><span id="townError"></span> <select name="townDetails"
						id="townDetails">
							<option></option>
					</select></td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td><span id="mobileError"></span> <input type="text"
						name="mobileCountryCode" id="mobileCountryCode" maxlength="4"
						onclick="clearField('mobileError')" readonly /></td>

					<td><span id="mobileError"></span> <input type="text"
						name="mobileNetworkCode" id="mobileNetworkCode" maxlength="11"
						onclick="clearField('mobileError')" /></td>

					<td><span id="mobileError"></span> <input type="text"
						name="mobileNumber" id="mobileNumber" maxlength="11"
						onclick="clearField('mobileError')" /></td>
				</tr>
				<tr>
					<td>Land</td>
					<td><span id="landError"></span> <input type="text"
						name="landCountryCode" id="landCountryCode" maxlength="4"
						onclick="clearField('mobileError')" readonly /></td>

					<td><span id="landError"></span> <input type="text"
						name="landAreaCode" id="landAreaCode" maxlength="11"
						onclick="clearField('mobileError')" /></td>

					<td><input type="text" name="landNumber" id="landNumber"
						maxlength="11" onclick="clearField('landError')" /></td>
				</tr>
				<tr>
					<td>Address Line 1 <span id="address1Error"></span></td>
					<td><input type="text" name="address1" id="address1"
						maxlength="30" onclick="clearField('address1Error')" /></td>
				</tr>
				<tr>
					<td>Address line 2 <span id="address2Error"></span></td>
					<td><input type="text" name="address2" id="address2"
						maxlength="30" /></td>
				</tr>
				<tr>
					<td>Address line 3 <span id="address3Error"></span></td>
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
					<td>WhatsApp <span id="whatsappError"></span></td>
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
					<td><input type="text" name="email" id="email"
						onclick="clearField('emailError')" /></td>
				</tr>
				<tr>
					<td>Username <span id="usernameError"></span></td>
					<td><input type="text" name="username" id="username"
						maxlength="50" onclick="clearField('usernameError')" /></td>
				</tr>
				<tr>
					<td>Password <span id="passwordError"></span></td>
					<td><input type="password" name="password" id="password"
						maxlength="20" onclick="clearField('passwordError')" /></td>
				</tr>
				<tr>
					<td>Confirm Password <span id="confirmPasswordError"></span></td>
					<td><input type="password" name="confirmPassword"
						id="confirmPassword" maxlength="20"
						onclick="clearField('confirmPasswordError')" /></td>
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