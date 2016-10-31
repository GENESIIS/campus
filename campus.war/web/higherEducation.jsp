<!-- 20161025 JH c7-higher-education-landing-page higherEducationCourses.jsp created -->
<!-- 20161025 JH c7-higher-education-landing-page initial page design -->
<!-- 20161036 JH c7-higher-education-landing-page  page renamed as higherEducation.jsp -->
<!-- 20161031 JH c7-higher-education-landing-page display higher education programmes -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
table {
	border-collapse: collapse;
}
</style>
</head>
<body>

	<table width="90%;" align="center">
		<tr>
			<th width="80%;"></th>
			<th width="20%;"></th>
		</tr>
		<tr>

			<!-- higher education header -->
			<td><img src="Higher-Education.jpg" alt="Higher Education"
				height="250px;" width="250px;"> <label
				style="font-size: xx-large;">Higher Education </label></td>

			<!-- featured courses -->
			<td rowspan="2" height="300px;"><label>Featured Courses</label>
				<table width="100%">
					<tr>
						<th width="30%"></th>
						<th width="70%"></th>
					</tr>
					<tr height="100px;" style="border: 1px solid blue;">
						<td><img src="Higher-Education.jpg" alt="Higher Education"
							height="50px;" width="50px;"></td>
						<td><label style="font: large;">Description comes
								here</label></td>
					</tr>
					<tr height="100px;" style="border: 1px solid blue;">
						<td colspan="2">Course summary comes here

							<div>
								<a href="#">See more</a>
							</div>
						</td>

					</tr>
					<tr height="100px;" style="border: 1px solid blue;">
						<td colspan="2"><a href="#">place</a> &nbsp;&nbsp;&nbsp; <a
							href="#">major</a>&nbsp;&nbsp;&nbsp; <a href="#">duration</a></td>
					</tr>
				</table></td>



		</tr>
		<table width="1500px;">
			<tr>
				<th width="50px;">Number</th>
				<th width="150px;">Image</th>
				<th width="500px;">Institute</th>
				<th width="500px;">Programme</th>
				<th width="100px;">Location</th>
				<th width="100px;">Duration</th>
			</tr>
			<c:forEach var="programmeList" items="${result.collection}"
				varStatus="rowCount">
				<tr>
					<td><c:out value="${rowCount.index+1}" /></td>
					<td><c:out value="${programmeList[0] }" /></td>
					<td><img alt="<c:out value="${programmeList[2] }"/>" src=""></td>
					<td><c:out value="${programmeList[2] }"></c:out></td>
					<td><c:out value="${programmeList[2] }"></c:out></td>
					<td><c:out value="${programmeList[2] }"></c:out></td>
				</tr>
			</c:forEach>
		</table>

		<tr>
			<td>sjhfuereiu</td>
		</tr>
		<tr></tr>
	</table>

</body>
</html>