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
<body>
<div align ="center">
<form >
    <table>
    <tr>
      <td>
        <span >
        <h2>Sign Up </h2>
        <h3>Give Us Your Basic Detail </h3>
      </span>
        <div>
         <label for="input-firstName" id="firstNameLabel">First Name <span>*</span></label>
         <input type="text" id="firstName" name="firstName" >
        </div>
        <br>
        <div>
         <label for="input-lastName" id="lastNameLabel">Last Name <span>*</span></label>
         <input type="text" id="lastName" name="lastName" >
        </div>
         <br>
        <div>
         <label for="input-gender" id ="genderLabel">Gender</label> <br>
         <input type="radio" name="gender" value="Male" checked> Male<br>
        <input type="radio" name="gender" value="Female"> Female<br>
        </div>
         <br>
         <div>
         <label for="eMail" id="emilAddressLabel">Email <span>*</span></label>
         <input type="text" id="emailAddress" name="emailAddress" >
         </div>
         <br>
        <div>
        <label for="input-phoneNumber" id="contactNumberLabel">Phone Number</label>
        <input type="text" id="contactNumber" name="contactNumber" >
        </div>
        <br>
        <div>
         <label for="input-pathway" id="pathwayLabel">Are you engaged with</label>
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
         
          <div>
            <img src="fbIcon.png" alt="faceBookIcon" >
          </div>
           <div>
            <img src="googleplus.png" alt="googlePlusIcon" >
          </div>
          <div>
            <img src="twitter_icon.png" alt="twitterIcon" >
          </div>
           <div>
            <img src="linkedin_icon.png" alt="linkedInIcon" >
          </div>
        
      </td>
    </tr>
    <tr>
      <td>
        <span >
        <h2 >Create a Username and Password</h2>
        User name:<br>
      	<input type="text" name="username" id="userName"><br>
     	 User password:<br>
      	<input type="password" name="psw" id="passWord">
      	<br>
     	 Confirm password:<br>
      	<input type="password" name="confrmpsw" id="confrmpsw">
      	</span> 
        <input type="checkbox" id="showpasscheckbox" title="Show the password as plain text" onclick="convertPassWordToString()" />
        <span>Show Password</span><br><br><br>
        <input type="checkbox" id="policyConfirm"  />
        <span>I have read privecy policy and accept<br> the terms and the conditions </span><br><br>

     <button  type="submit" name="CCO" id="CCO" value="FBTSA" onclick=" validateSignUpWoThirdPartyPageEmbedData();sendSignUpCredentialsToBckEnd();" >Create Account</button>

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
<script src="/dist/js/signUpWoThirdParty.js"></script>
</body>
</html>