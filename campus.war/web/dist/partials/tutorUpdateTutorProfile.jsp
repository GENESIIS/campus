<!-- 20170227 c37-tutor-update-tutor-profile-cw - created using CAM-38 sample jsp file -->
<!-- 20170227 c37-tutor-update-tutor-profile-cw - removed tutor status details, add Javascript validations-->
<!-- 20170227 c37-tutor-update-tutor-profile-cw - modified password change items-->
<!-- 20170301 c37-tutor-update-tutor-profile-cw modified passwordOld to passwordFromDb -->
<!-- 20170302 CW c37-tutor-update-tutor-profile-cw modified validateTutorModifications method to validateTutorModificationsByTutor -->
<!-- 20170316 CW c37-tutor-update-tutor-profile-cw modify field max lengths to match database field max size -->
<!-- 20170324 CW c37-tutor-update-tutor-profile-cw modify old Password to Current Password & Confirm new Password to Confirm Password-->
<!-- 20170406 CW c37-tutor-update-tutor-profile-cw modified code hidden field into tutorCode -->
<!-- 20170407 CW c37-tutor-update-tutor-profile-cw removed password & passwordFromDb fields -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="applicationStatusBean"
	class="com.genesiis.campus.validation.ApplicationStatusBean" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View or Update Tutor Profile</title>
</head>
<body>
	<form action="/TutorController" method="post"
		onsubmit="return (validateTutorModificationsByTutor())">

		<c:forEach var="tutorList" items="${result.collection}">

			<c:set var="code" value="${tutorList[0]}"/>
			<c:set var="username" value="${tutorList[1]}"/>
			<c:set var="firstname" value="${tutorList[2]}"/>
			<c:set var="middlename" value="${tutorList[3]}"/>
			<c:set var="lastname" value="${tutorList[4]}"/>
			<c:set var="gender" value="${tutorList[5]}"/>
			<c:set var="email" value="${tutorList[6]}"/>
			<c:set var="landphonecountrycode" value="${tutorList[7]}"/>
			<c:set var="landphoneareacode" value="${tutorList[8]}"/>
			<c:set var="landphonenumber" value="${tutorList[9]}"/>
			<c:set var="mobilephonecountrycode" value="${tutorList[10]}"/>
			<c:set var="mobilephonenetworkcode" value="${tutorList[11]}"/>
			<c:set var="mobilephonenumber" value="${tutorList[12]}"/>
			<c:set var="description" value="${tutorList[13]}"/>
			<c:set var="experience" value="${fn:trim(tutorList[14])}"/>
			<c:set var="weblink" value="${fn:trim(tutorList[15])}"/>
			<c:set var="facebookurl" value="${tutorList[16]}"/>
			<c:set var="twitterurl" value="${tutorList[17]}"/>
			<c:set var="myspaceurl" value="${tutorList[18]}"/>
			<c:set var="linkedinurl" value="${tutorList[19]}"/>
			<c:set var="instagramurl" value="${tutorList[20]}"/>
			<c:set var="vibernumber" value="${tutorList[21]}"/>
			<c:set var="whatsappnumber" value="${tutorList[22]}"/>
			<c:set var="address1" value="${tutorList[23]}"/>
			<c:set var="address2" value="${tutorList[24]}"/>
			<c:set var="address3" value="${tutorList[25]}"/>
			<c:set var="town" value="${tutorList[26]}"/>
			<c:set var="towncode" value="${tutorList[27]}"/>
			<c:set var="usertype" value="${tutorList[28]}"/>
			<c:set var="countryname" value="${tutorList[29]}"/>
			<c:set var="isapproved" value="${tutorList[30]}"/>
			<c:set var="tutorstatus" value="${tutorList[31]}"/>

		</c:forEach>
		
		<input type="hidden" name="tutorCode" id="tutorCode" value="${code}"/>
		<input type="hidden" name="usernameOld" id="usernameOld" value="${username}"/>
		<input type="hidden" name="firstnameOld" id="firstnameOld" value="${firstname}"/>
		<input type="hidden" name="middlenameOld" id="middlenameOld" value="${middlename}"/>
		<input type="hidden" name="lastnameOld" id="lastnameOld" value="${lastname}"/>
		<input type="hidden" name="genderOld" id="genderOld" value="${gender}"/>
		<input type="hidden" name="emailOld" id="emailOld" value="${email}"/>
		<input type="hidden" name="landphonecountrycodeOld" id="landphonecountrycodeOld" value="${landphonecountrycode}"/>
		<input type="hidden" name="landphoneareacodeOld" id="landphoneareacodeOld" value="${landphoneareacode}"/>
		<input type="hidden" name="landphonenumberOld" id="landphonenumberOld" value="${landphonenumber}"/>
		<input type="hidden" name="mobilephonecountrycodeOld" id="mobilephonecountrycodeOld" value="${mobilephonecountrycode}"/>
		<input type="hidden" name="mobilephonenetworkcodeOld" id="mobilephonenetworkcodeOld" value="${mobilephonenetworkcode}"/>
		<input type="hidden" name="mobilephonenumberOld" id="mobilephonenumberOld" value="${mobilephonenumber}"/>
		<input type="hidden" name="descriptionOld" id="descriptionOld" value="${description}"/>
		<input type="hidden" name="experienceOld" id="experienceOld" value="${experience}"/>
		<input type="hidden" name="weblinkOld" id="weblinkOld" value="${weblink}"/>
		<input type="hidden" name="facebookurlOld" id="facebookurlOld" value="${facebookurl}"/>
		<input type="hidden" name="twitterurlOld" id="twitterurlOld" value="${twitterurl}"/>
		<input type="hidden" name="myspaceurlOld" id="myspaceurlOld" value="${myspaceurl}"/>
		<input type="hidden" name="linkedinurlOld" id="linkedinurlOld" value="${linkedinurl}"/>
		<input type="hidden" name="instagramurlOld" id="instagramurlOld" value="${instagramurl}"/>
		<input type="hidden" name="vibernumberOld" id="vibernumberOld" value="${vibernumber}"/>
		<input type="hidden" name="whatsappnumberOld" id="whatsappnumberOld" value="${whatsappnumber}"/>
		<input type="hidden" name="address1Old" id="address1Old" value="${address1}"/>
		<input type="hidden" name="address2Old" id="address2Old" value="${address2}"/>
		<input type="hidden" name="address3Old" id="address3Old" value="${address3}"/>
		<input type="hidden" name="townOld" id="townOld" value="${town}"/>
		<input type="hidden" name="towncodeOld" id="towncodeOld" value="${towncode}"/>
		<input type="hidden" name="usertypeOld" id="usertypeOld" value="${usertype}"/>
		<input type="hidden" name="countrynameOld" id="countrynameOld" value="${countryname}"/>
		<input type="hidden" name="isapprovedOld" id="isapprovedOld" value="${isapproved}"/>
		<input type="hidden" name="tutorstatusOld" id="tutorstatusOld" value="${tutorstatus}"/>

		<noscript>
			<div class="noscriptmsg" style="color:red">
				<h2>You don't have Javascript enabled. Please enable Javascript.</h2>
				
				<style type="text/css">
					#main-content { display:none; }
				</style>
			</div>
		</noscript>
		
		<div id="main-content">
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
					<td>First Name </td>
					<td><input type="text" name="firstname" id="firstName"
						maxlength="35" onchange="clearField('firstNameError')"
						value="${firstname}" /><span id="firstNameError"
							style="color: red"> ${firstNameError} </span></td>
				</tr>
	
				<tr>
					<td>Middle Name </td>
					<td><input type="text" name="middlename" id="middleName"
						maxlength="35" onchange="clearField('middleNameError')" value="${middlename}" />
						<span id="middleNameError" style="color: red"> ${middleNameError} </span></td>
				</tr>
				<tr>
					<td>Last Name </td>
					<td><input type="text" name="lastname" id="lastName"
						maxlength="35" onchange="clearField('lastNameError')"
						value="${lastname}" /><span id="lastNameError"
						style="color: red"> ${lastNameError} </span></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><c:if test="${gender == 1}">
							<input type="radio" name="gender" value="1" id="radioMale" checked> Male<br>
							<input type="radio" name="gender" value="2" id="radioFemale"> Female<br>
						</c:if> <c:if test="${gender == 2}">
							<input type="radio" name="gender" value="1" id="radioMale"> Male<br>
							<input type="radio" name="gender" value="2" id="radioFemale"
								checked> Female<br>
						</c:if></td>
				</tr>
				<tr>
					<td>Experience</td>
					<td><textarea rows="10" cols="26" name="experience"
							id="experience"><c:out value="${experience}" /></textarea><span
							id="experienceError" style="color: red"> ${experienceError} </span></td>
				</tr>
				<tr>
					<td>About Me </td>
					<td><textarea rows="10" cols="26" name="aboutMe" 
					id="aboutMe"><c:out value="${description}"/></textarea><span
					id="aboutMeError" style="color: red"> ${aboutMeError} </span></td>
				</tr>
				<tr>
					<td><h2>Contact Info</h2></td>
				</tr>
				<tr>
					<td>Country</td>
					<td>${countryname}</td>
	
					<td><span id="countryError"></span> <select
						name="countryDetails" id="countryDetails" onchange="clearField('countryError')">
							<option></option>
					</select><span id="countryError" style="color: red"> ${countryError} </span></td>
				</tr>
				<tr>
					<td>Town</td>
	
					<td>${town}</td>
					<td><select name="townDetails"
						id="townDetails" onchange="clearField('townError')">
							<option></option>
					</select><span id="townError" style="color: red"> ${townError} </span></td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td><input type="text"
						name="mobileCountryCode" id="mobileCountryCode" maxlength="10"
						onchange="clearField('mobileError')" readonly value="${mobilephonecountrycode}" />
						<span id="mobileError" style="color: red"> ${mobileError} </span></td>
	
					<td><input type="text"
						name="mobileNetworkCode" id="mobileNetworkCode" maxlength="10"
						onchange="clearField('mobileNetworkError')"	value="${mobilephonenetworkcode}" />
						<span id="mobileNetworkError" style="color: red"> ${mobileNetworkError} </span></td>
	
					<td><input type="text"
						name="mobileNumber" id="mobileNumber" maxlength="15"
						onchange="clearField('mobileNumberError')" value="${mobilephonenumber}" />
						<span id="mobileNumberError" style="color: red"> ${mobileNumberError} </span></td>
				</tr>
				<tr>
					<td>Land</td>
					<td><input type="text"
						name="landCountryCode" id="landCountryCode" maxlength="10"
						onchange="clearField('landError')" readonly value="${landphonecountrycode}" />
						<span id="landError" style="color: red"> ${landError} </span></td>
	
					<td><input type="text"
						name="landAreaCode" id="landAreaCode" maxlength="10"
						onchange="clearField('landAreaCodeError')" value="${landphoneareacode}" />
						<span id="landAreaCodeError" style="color: red"> ${landAreaCodeError} </span></td>
	
					<td><input type="text" name="landNumber" id="landNumber"
						maxlength="15" onchange="clearField('landNumberError')" value="${landphonenumber}" />
						<span id="landNumberError" style="color: red"> ${landNumberError} </span></td>
				</tr>
				<tr>
					<td>Address Line 1 </td>
					<td><input type="text" name="address1" id="address1"
						maxlength="50" onchange="clearField('address1Error')" value="${address1}" />
						<span id="address1Error" style="color: red"> ${address1Error} </span></td>
				</tr>
				<tr>
					<td>Address line 2 </td>
					<td><input type="text" name="address2" id="address2"
						maxlength="50" value="${address2}" /></td>
				</tr>
				<tr>
					<td>Address line 3 </td>
					<td><input type="text" name="address3" id="address3"
						maxlength="50" value="${address3}" /></td>
				</tr>
	
				<tr>
					<td>Web link </td>
					<td><input type="text" name="weblink" id="weblink" value="${weblink}" maxlength="200" />
					<span id="weblinkError" style="color: red"> ${weblinkError} </span></td>
				</tr>
				<tr>
					<td>Facebook </td>
					<td><input type="text" name="facebook" id="facebook" maxlength="200" 
						value="${facebookurl}" /><span id="facebookError"
						style="color: red"> ${facebookError} </span> </td>
				</tr>
				<tr>
					<td>LinkedIn </td>
					<td><input type="text" name="linkedin" id="linkedin" maxlength="100" 
						value="${linkedinurl}" /><span id="linkedInError"
						style="color: red"> ${linkedInError} </span></td>
				</tr>
				<tr>
					<td>Twitter </td>
					<td><input type="text" name="twitter" id="twitter" maxlength="100" 
						value="${twitterurl}" /><span id="twitterError"
						style="color: red"> ${twitterError} </span></td>
				</tr>
				<tr>
					<td>Instagram </td>
					<td><input type="text" name="instagram" id="instagram" maxlength="100"
						value="${instagramurl}" /><span id="instagramError"
						style="color: red"> ${instagramError} </span></td>
				</tr>
				<tr>
					<td>Myspace </td>
					<td><input type="text" name="myspace" id="myspace" maxlength="100"
						value="${myspaceurl}" /><span
						id="mySpaceError" style="color: red"> ${mySpaceError} </span></td>
				</tr>
				<tr>
					<td>WhatsApp </td>
					<td><input type="text" name="whatsapp" id="whatsapp"
						maxlength="20" value="${whatsappnumber}" /><span id="whatsappError"
							style="color: red"> ${whatsappError} </span></td>
				</tr>
				<tr>
					<td>Viber </td>
					<td><input type="text" name="viber" id="viber" maxlength="20" value="${vibernumber}" />
						<span id="viberError" style="color: red"> ${viberError} </span></td>
				</tr>
				<tr>
					<td><h2>Account Info</h2></td>
				</tr>
	
				<tr>
					<td>Email </td>
					<td><input type="text" name="email" id="email" maxlength="255" 
						onchange="clearField('emailError')" value="${email}" />
						<span id="emailError" style="color: red"> ${emailError} </span></td>
				</tr>
				<tr>
					<td>Username </td>
					<td><input type="text" name="username" id="username"
						maxlength="100" onchange="clearField('usernameError')" value="${username}" readonly />
						<span id="usernameError" style="color: red"> ${usernameError} </span></td>
				</tr>
				<tr>
					<td>Current Password </td>
					<td><input type="password" name="oldPassword" id="oldPassword"
						maxlength="50" onchange="clearField('oldPasswordError')"/>
						<span id="oldPasswordError" style="color: red"> ${oldPasswordError} </span></td>
				</tr>
				
				<tr>
					<td>New Password </td>
					<td><input type="password" name="newPassword" id="newPassword"
						maxlength="50" onchange="clearField('newPasswordError')"/>
						<span id="newPasswordError" style="color: red"> ${newPasswordError} </span></td>
				</tr>
				<tr>
					<td>Confirm Password </td>
					<td><input type="password" name="confirmPassword" id="confirmPassword" maxlength="50"
						onchange="clearField('confirmPasswordError')"/><span
						id="confirmPasswordError" style="color: red"> ${confirmPasswordError} </span></td>
				</tr>
	
				<tr>
					<td>
						<button type="submit" name="CCO" id="CCO" value="TUTOR_UPDATE_TUTOR"
							class="btn btn-info navbar-btn">Update</button>
					</td>
				</tr>
	
			</table>
		</div>
		
	</form>


	<script type="text/javascript"
		src="\dist\bower-components\jquery\jquery.min.js"></script>
	<script src="/dist/js/tutor-helper.js"></script>
	<script src="/dist/js/validator.js"></script>

</body>
</html>