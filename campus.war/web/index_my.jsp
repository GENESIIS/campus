<!-- 20161111 JH c7-higher-education-landing-page-MP testing sample for bootstrap carousal -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap & CSS Style-->
<link href="dist/bower-components/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<title>Insert title here</title>


<!-- jQuery & Other js -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.carousel-inner>.item>img,.carousel-inner>.item>a>img {
	width: 70%;
	margin: auto;
}
</style>
</head>
<body>
	<h1>Hello World..!!</h1>

	<form action="PublicController" method="POST">
		<button type="submit" name="CCO" id="CCO"
			value="LIST_CATEGORY_LANDING_PAGE"
			class="pure-button pure-button-primary">Higher Education</button>
		<table>
			<tr>
				<th>Code</th>
				<th>Name</th>
			</tr>

			<tr>
				<td>1</td>
				<td>School</td>
			</tr>
			<tr>
				<td>2</td>
				<td>Higher Education</td>
			</tr>
		</table>
		<input type="hidden" name="categoryId" id="categoryId" value="2" />
	</form>


	<div class="container">
		<br>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">

				<c:forEach var="featuredInstitute" items="${featuredInstitutes}"
					varStatus="loopCount">
					<c:choose>
						<c:when test="${loopCount.count == 1 }">
							<div class="item active">
								<img src="${featuredInstitute[16] }" alt="Chania" width="460"
									height="345">
								<div class="carousel-caption">
									<h3>${featuredInstitute[2] }</h3>
									<p>
										<c:out value="${featuredInstitute[4]}"></c:out>
									</p>
								</div>
							</div>


						</c:when>
						<c:when test="${loopCount.count != 1 }">
							<div class="item">
								<img src="${featuredInstitute[16] }" alt="Chania" width="460"
									height="345">
								<div class="carousel-caption">
									<h3>${featuredInstitute[2] }</h3>
									<p>
										<c:out value="${featuredInstitute[4]}"></c:out>
									</p>
								</div>
							</div>
						</c:when>
					</c:choose>

				</c:forEach>

			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</body>
</html>