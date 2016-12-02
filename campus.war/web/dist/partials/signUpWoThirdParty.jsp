<!-- 20161119 DN C18-student-signup-without-using-third-party-application-dn  created the page SignUpWoThirdParty.jsp -->
<!-- 20161123 DN C18-student-signup-without-using-third-party-application-dn /dist/js/jsonDataExchanger.js included to the page -->
<!-- 20161123 DN C18-student-signup-without-using-third-party-application-dn tool tip information for mobile number introduced -->
<!-- 20161201 DN C18-student-signup-without-using-third-party-application-dn add a tool tip to mobile number field -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
 <script src="/dist/bower-components/w3/w3data.js"></script>
 <script src='https://www.google.com/recaptcha/api.js'></script>
<!--  <script src="/dist/js/institute/validation/validation.js"></script> -->

<title>SignUp Without Third Party</title>
</head>
<body id="containerBody" bgcolor="#97EAF4">
<div align ="center">
<form >
    <table>
    <tr>
      <td>
        <span >
        <h2>Sign Up </h2>
        <h3>Give Us Your Basic Detail </h3>
      </span>
      		<label id="displayLabel" style="color:#F39C12;" ></label>
        <div>
         <label for="input-firstName" id="firstNameLabel">First Name <span>*</span></label><label id="firstNameError" style="color:#FFFF00;"></label><br>
         <input type="text" id="firstName" name="firstName" onclick="clearField('firstNameError')">
        </div>
        <br>
        <div>
         <label for="input-lastName" id="lastNameLabel">Last Name <span>*</span></label><label id="lastNameError" style="color:#FFFF00;"></label><br>
         <input type="text" id="lastName" name="lastName" onclick="clearField('lastNameError')" >
        </div>
         <br>
        <div>
         <label for="input-gender" id ="genderLabel">Gender</label> <br><label id="genderError" style="color:#FFFF00;"></label><br>
         <input type="radio" name="gender" value="1" checked> Male<br>
        <input type="radio" name="gender" value="0"> Female<br>
        </div>
         <br>
         <div>
         <label for="eMail" id="emilAddressLabel">Email <span>*</span></label><label id="emailError" style="color:#FFFF00;"></label><br>
         <input type="text" id="emailAddress" name="emailAddress"  onclick="clearField('emailError')">
         </div>
         <br>
        <div>
        <label for="input-phoneNumber" id="contactNumberLabel">Mobile Number<span>*</span></label><label id="phoneError" style="color:#FFFF00;"></label><br>
        <input type="text" id="contactNumber" name="contactNumber"  onclick="clearField('phoneError')" data-toggle="tooltip" title="+94123445678 | 0094123456789 | 0777123456 formats are only accepted" >
        </div>
        <script>
			$(document).ready(function(){
			    $('[data-toggle="tooltip"]').tooltip();   
			});
		</script>

        <br>
        <div>
         <label for="input-pathway" id="pathwayLabel">What Are You Engaged With</label><label id="pathwayError" style="color:#FFFF00;"></label><br>
         <select id="pathway">
	          <option value="school" id="pathway0">School Education</option>
	          <option value="graduate" id="pathway1">Graduate</option>
	          <option value="masters" id="pathway2">Masters</option>
	          <option value="copTraining" id="pathway3">cooperate Training</option>
	          <option value="vocTraining" id="pathway4">vocational Training</option>
        </select>
        </div>
      </td>
      <td>
		<label><h2>Or SignUp Via</h2></label>
         
<!--           <div> -->
<!--             <img src="fbIcon.png" alt="faceBookIcon" > -->
<!--           </div> -->
<!--            <div> -->
<!--             <img src="googleplus.png" alt="googlePlusIcon" > -->
<!--           </div> -->
<!--           <div> -->
<!--             <img src="twitter_icon.png" alt="twitterIcon" > -->
<!--           </div> -->
<!--            <div> -->
<!--             <img src="linkedin_icon.png" alt="linkedInIcon" > -->
<!--           </div> -->
        
      </td>
    </tr>
    <tr>
      <td>
        <span >
        <h2 >Create a Username and Password</h2>
        User name:<span>*</span><br><label id="usernameLabel" ></label><label id="usernameError" style="color:#FFFF00;"></label><br>
      	<input type="text" name="username" id="userName" onclick="clearField('usernameError')"><br><br>
      	
     	 User password:<span>*</span><br><label id="passWordLabel" ></label><label id="passWordError" style="color:#FFFF00;"></label><br>
      	<input type="password" name="psw" id="passWord" onclick="clearField('passWordError')"><br>
      	<br>
     	 Confirm password:<span>*</span><br><label id="confPassWordLabel" ></label><label id="confPassWordError" style="color:#FFFF00;" ></label><br>
      	<input type="password" name="confrmpsw" id="confrmpsw" onclick="clearField('confPassWordError')">
      	</span> 
        <input type="checkbox" id="showpasscheckbox" title="Show the password as plain text" onclick="convertPassWordToString('showpasscheckbox','passWord','confrmpsw')" />
        <span>Show Password</span><br><br><br>
        <label id="policyConfirmLabel" ></label><label id="policyConfirmError" style="color:#FFFF00;" ></label><br>
        <input type="checkbox" id="policyConfirm"  />
        <span>I have read privercy policy and accept<br> the terms and the conditions </span><br><br>

     <button  type="button"  onclick=" return (sendSignUpCredentialsToBckEnd()); clearField('displayLabel');">Create Account</button>

      </td>
      <td>

      </td>
    </tr>
    </table>
</form>
</div>

<!-- jQuery & Other js -->
<script src="../bower-components/jquery/jquery-3.1.1.min.js"></script>
<script src="../bower-components/bootstrap/bootstrap.min.js"></script>
<script src="../js/main.js"></script>
<script src="/dist/js/institute/validation/validation.js"></script>
<script src="/dist/js/jsonDataExchanger.js"></script>
<script src="/dist/js/signUpWoThirdParty.js"></script>
</body>
</html>