<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>(Test) Customers</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<h1>(Test) Customers</h1>
		<c:if test="${ message != null }">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>
		<table class="table table-striped table-bordered">
			<tr>
				<th>Id</th>
				<th>Email</th>
				<th>Password</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Address</th>
				<th>PhoneNumber</th>
			</tr>
			<c:forEach var="customer" items="${customerList}">
				<tr>
					<td>${customer.id }</td>
					<td>${customer.email}</td>
					<td>${customer.password }</td>
					<td>${customer.firstName }</td>
					<td>${customer.lastName }</td>
					<td>${customer.address }</td>
					<td>${customer.phoneNumber }</td>
				</tr>
			</c:forEach>
		</table>
			
		
		
	</div>

</body>
</html>