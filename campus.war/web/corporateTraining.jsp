<!-- 20161024 MM c5-corporate-training-landing-page Initialised -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campus.lk - Corporate Training</title>
</head>
<body>

<h1>Corporate Training</h1>

<table>
<c:forEach var="programme" items="${programmeCollection}">
	<tr>
		<c:forEach var="programmeAttrib" items="${programme}" varStatus="vs">
			<td>
				<a hreg="#">
					<img src="${programmeAttrib}"/>
				</a>
			</td>
		</c:forEach>
	</tr>


</c:forEach>
<tr>
<h3>Who should attend?</h3>
</tr>
<tr>
Chairmen<br>
Managing Directors<br>
Chief Executive Officers<br>
Directors<br>
Company Secretaries<br>
Presidential advisors<br>
Ministerial advisers<br>
Board advisers<br>
Chief Financial Officers<br>
Board Members<br>
Heads of Department<br>
Directors of Human Resources<br>
Directors of Business Development<br>
</tr>
<tr>
<h3>Learning objectives</h3>
To create strategies which enable an organisation to achieve its vision, goals and objectives
To develop supporting systems and structures to underpin and ensure the success of all strategies
To have relevant measures of progress to enable action to be taken to overcome any challenges to the implementation of the strategies
</tr>
<tr style="margin-bottom: 20px">
<a href="#">More Details</a>
</tr>
<tr>
	<h2 style="color: blue">Strategic Planning</h3>	
</tr>
<tr>
<h3>Who should attend?</h3>
</tr>
<tr>
Chairmen<br>
Managing Directors<br>
Chief Executive Officers<br>
Directors<br>
Company Secretaries<br>
Presidential advisors<br>
Ministerial advisers<br>
Board advisers<br>
Chief Financial Officers<br>
Board Members<br>
Heads of Department<br>
Directors of Human Resources<br>
Directors of Business Development<br>
</tr>
<tr>
<h3>Learning objectives</h3>
To create strategies which enable an organisation to achieve its vision, goals and objectives<br>
To develop supporting systems and structures to underpin and ensure the success of all strategies<br>
To have relevant measures of progress to enable action to be taken to overcome any challenges to the implementation of the strategies<br>
To know how to generate commitment from all stakeholders to increase strength of support for the successful implementation of the strategic plan<br>
To identify and use the key approaches and methods of communication to ensure transparency and openness<br>
</tr>
<tr style="margin-bottom: 20px">
<a href="#">More Details</a>
</tr><tr>
	<h2 style="color: blue">Advanced Strategic Management</h3>	
</tr>
<tr>
<h3>Who should attend?</h3>
</tr>
<tr>
Chairmen<br>
Managing Directors<br>
Chief Executive Officers<br>
Directors<br>
Company Secretaries<br>
Presidential advisors<br>
Ministerial advisers<br>
Board advisers<br>
Chief Financial Officers<br>
Board Members<br>
Heads of Department<br>
Directors of Human Resources<br>
Directors of Business Development<br>
</tr>
<tr>
<h3>Learning objectives</h3>
To create strategies which enable an organisation to achieve its vision, goals and objectives<br>
To develop supporting systems and structures to underpin and ensure the success of all strategies<br>
To have relevant measures of progress to enable action to be taken to overcome any challenges to the implementation of the strategies<br>
To know how to generate commitment from all stakeholders to increase strength of support for the successful implementation of the strategic plan<br>
To identify and use the key approaches and methods of communication to ensure transparency and openness<br>
</tr>
<tr style="margin-bottom: 20px">
<a href="#">More Details</a>
</tr>
</table>

</body>
</html>