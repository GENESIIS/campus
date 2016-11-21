<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutor Profile</title>
</head>
<body>
	<form action="TutorController" method="post">
	<table align="center">
	<tr>
	<td>
	<h2>Basic Info</h2>
	</td>
	</tr>
		<tr>
			<td>First Name</td>
			<td><input type="text" name="firstname" id="firstName"/></td>
		</tr>
		<tr>
			<td>Middle Name</td>
			<td><input type="text" name="middlename" id="middleName"/></td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><input type="text" name="lastname" id="lastName"/></td>
		</tr>
		<tr>
			<td>Gender</td>
			<td><input type="radio" name="gender" value="male" id="radioMale" checked>
				Male<br> <input type="radio" name="gender" value="female" id="radioFemale" >
				Female<br></td>
		</tr>
		<tr>
			<td>Experience</td>
			<td><textarea rows="10" cols="26" name="experience" id="experience"></textarea></td>
		</tr>
		<tr>
			<td>About Me</td>
			<td><textarea rows="10" cols="26" name="aboutMe" id="about_me"></textarea></td>
		</tr>
		<tr>
			<td><h2>Contact Info</h2></td>
		</tr>
		<tr>
			<td>Mobile</td>
			<td><input type="text" name="mobileNumber" id="mobile_number"/></td>
		</tr>
		<tr>
			<td>Home</td>
			<td><input type="text" name="landNumber" id="land_number"/></td>
		</tr>
		<tr>
			<td>Address 1</td>
			<td><input type="text" name="address1" id="address1"/></td>
		</tr>
		<tr>
			<td>Address 2</td>
			<td><input type="text" name="address2" id="address2"/></td>
		</tr>
		<tr>
			<td>Address 3</td>
			<td><input type="text" name="address3"  id="address3"/></td>
		</tr>
		<tr>
			<td>Town</td>
			<td><input type="text" name="town"  id="town"/></td>
		</tr>
		<tr>
			<td>Web link</td>
			<td><input type="text" name="weblink"  id="weblink"/></td>
		</tr>
		<tr>
			<td>Facebook</td>
			<td><input type="text" name="facebook" /></td>
		</tr>
		<tr>
			<td>LinkedIn</td>
			<td><input type="text" name="linkedin" /></td>
		</tr>
		<tr>
			<td>Twitter</td>
			<td><input type="text" name="twitter" /></td>
		</tr>
		<tr>
			<td>Instagram</td>
			<td><input type="text" name="instagram" /></td>
		</tr>
		<tr>
			<td>Myspace</td>
			<td><input type="text" name="myspace" /></td>
		</tr>
		<tr>
			<td>whatsApp</td>
			<td><input type="text" name="whatsapp" /></td>
		</tr>
		<tr>
			<td>Viber</td>
			<td><input type="text" name="viber" /></td>
		</tr>
		<tr>
			<td><h2>Account Info</h2></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
			<td>Username</td>
			<td><input type="text" name="username" /></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="text" name="password" /></td>
		</tr>
		<tr>
			<td>Confirm Password</td>
			<td><input type="text" name="confirmPassword" /></td>
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