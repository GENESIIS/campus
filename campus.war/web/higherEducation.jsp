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
			<td rowspan="2" height="300px;"><label><b>Featured Institutes</b></label>
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
		<table width="1600px;">
			<tr>
				<th width="50px;">Number</th>
				<th width="150px;">Image</th>
				<th width="100px;">Prefix</th>
				<th width="250px;">Institute</th>
				<th width="350px;">Programme</th>
				<th width="500px;">Description</th>
				<th width="100px;">Location</th>
				<th width="100px;">Duration</th>
			</tr>
			<c:forEach var="programmeList" items="${result.collection}"
				varStatus="rowCount">
				<tr>
					<td width="50px;"><c:out value="${rowCount.index+1}" /></td>
					<td width="150px;" align="center">
					<a href="#"><img alt="" src="Higher-Education.jpg" width="75px;" height="75px;"></a><c:out value="${programmeList[0] }"></c:out>
					</td>
					<td width="100px;" align="center"><c:out value="${programmeList[3] }"></c:out></td>
					<td width="250px;" align="center"><c:out value="${programmeList[4] }"></c:out></td>
					<td width="350px;" align="center"><c:out value="${programmeList[5] }"></c:out></td>
					<td width="500px;" align="center"><c:out value="${programmeList[6] }"></c:out></td>
					<td width="100px;" align="center"><c:out value="${programmeList[7] }"></c:out></td>
					<td width="100px;" align="center"><c:out value="${programmeList[8] }"></c:out></td>
				</tr>
			</c:forEach>

		<c:forEach var="featuredInstitute" items="${featuredInstitutes}">
				<tr >

		
					<!-- featured courses -->
					<td  height="500px;"><label><b>Featured
								Institutes</b></label>
						<table width="500%" style="padding-left: 10 px;">
							<tr>
								<th width="30%"></th>
								<th width="70%"></th>
							</tr>
							<tr height="100px;" style="border: 1px solid blue; ">
								<td style="position: relative;left: 10px;"><img src="${featuredInstitute[15] }" alt="Higher Education"
									height="75px;" width="75px;"><c:out value="${featuredInstitute[0] }"></c:out></td>
								<td  style="position: relative;left: 10px;"><h3 style="color:blue; font-stretch: wider;"><c:out value="${featuredInstitute[1] }"></c:out></h3>
								<h4><c:out value="${featuredInstitute[2] }"></c:out></h4>
								</td>
							</tr>
							<tr height="100px;" style="border: 1px solid blue;">
								<td colspan="2"  style="position: relative;left: 10px;">
									<p style="color: gray;"> Institute description : 
										<c:out value="${featuredInstitute[3] }"></c:out>
									</p>
									<div align="right" style="position: relative; right: 20px;">
										<a href="#" style="color: blue;">See more</a>
									</div>
								</td>

							</tr>
							<tr height="100px;" style="border: 1px solid blue;">
							
							<tr></tr>
							<tr></tr>
							<tr></tr>
							
							<!-- 	<td colspan="2" align="left"><a href="#">place</a> &nbsp;&nbsp;&nbsp; 
								<a
									href="#">major</a>&nbsp;&nbsp;&nbsp; 
									<a href="#">duration</a></td>  -->
							</tr>
						</table></td>



				</tr>

			</c:forEach>
		</table>

		<tr></tr>
	</table>

</body>
</html>