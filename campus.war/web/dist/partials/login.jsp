<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Bootstrap & CSS Style-->
<link href="/dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="/dist/css/style.css" rel="stylesheet">

<!-- W3-Include -->
<script src="/dist/bower-components/w3/w3data.js"></script>
<script src='/dist/js/institute/validation/validation.js'></script>
<script src='/dist/js/login.js'></script>
</head>
<body>
<!-- Header-->
<header w3-include-html="/dist/partials/layout/header.jsp"></header>

	<div align="center">

		<table>
			<thead>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th>Login via</th>
			</thead>
			<tbody>
				<tr>
					<td><label class="" for="exampleInputEmail2">Email
							address</label></td>
					<td><input type="email" class="form-control"
						id="email" placeholder="Email address" required></td>
					<td></td>
					<td><a href="#" class="btn btn-fb"><i
							class="fa fa-facebook"></i> Facebook</a></td>
					<td></td>
				</tr>
				<tr>
					<td><label class="" for="exampleInputPassword2">Password</label></td>
					<td><input type="password" class="form-control"
						id="password" placeholder="Password" required></td>
					<td>Or</td>
					<td><i class="fa fa-twitter"></i> Twitter</a></td>

				</tr>
				<tr>
					<td></td>
					<td><a href="">Forget the password ?</a></td>
					<td></td>

					<td><i class=""></i> linked In</a></td>
				</tr>
				<tr>
					<td></td>
					<td><button class="btn btn-primary btn-block" type="button" name="CCO" id="CCO" value="SLOG" onclick="studentLogin()">Sign in</button></td>
					<td></td>

					<td><i class="fa fa-twitter"></i> Google+</a></td>
				</tr>
				<tr>
					<td></td>
					<td><label> <input type="checkbox" name="remember" value="true" > keep me
							logged-in
					</label></td>
					<td></td>

					<td><i class="fa fa-twitter"></i> Twitter</a></td>
				</tr>
				<tr>
					<td></td>
					<td>New here ? <a href="#"><b>Join Us</b></a></td>
					<td></td>

					<td></td>
				</tr>
			</tbody>
		</table>


	</div>
	
	<!-- Footer -->
<footer w3-include-html="/dist/partials/layout/footer.jsp"></footer>
<!-- jQuery & Other js -->
	<script src="/dist/bower-components/jquery/jquery.min.js"></script>
	<script src="/dist/bower-components/jquery/jquery-3.1.1.min.js"></script>
	<script src="/dist/bower-components/bootstrap/bootstrap.min.js"></script>
	<script src="/dist/js/main.js"></script>
</body>
</html>