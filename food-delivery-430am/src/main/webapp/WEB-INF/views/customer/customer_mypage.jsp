<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Page</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/static/css/mycss.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>

	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
			<h2 class="my-0 mr-md-auto"><a href="${pageContext.request.contextPath}/findRestaurants">430AM Food Delivery</a></h2>
			
			<nav class="my-2 my-md-0 mr-md-3">
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/customer/customer_mypage/">${loggedInCustomer.firstName} ${loggedInCustomer.lastName}</a> 
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/logout">Log Out</a>
				<!--  <p>for debug ${loggedInCustomer.phoneNumber}</p>-->
				<!--  <p>for debug ${loggedInCustomer.currentAddress}</p>-->
			</nav>
	</div>  
		
	<div class="container">
		<h1>My Page</h1>
		<c:if test="${ message != null }">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>
		<a href="${pageContext.request.contextPath}/orderHistory" class="btn btn-md btn-primary">Order History</a>
		<table class="table table-striped table-bordered">
			
				<tr><th>Email</th><td>${loggedInCustomer.email}</td></tr>
				<tr><th>Password</th><td>${loggedInCustomer.password }</td></tr>
				<tr><th>FirstName</th><td>${loggedInCustomer.firstName }</td></tr>
				<tr><th>LastName</th><td>${loggedInCustomer.lastName }</td></tr>
				<tr><th>Address</th><td>${loggedInCustomer.address }</td></tr>
				<tr><th>PhoneNumber</th><td>${loggedInCustomer.phoneNumber }</td></tr>
			
		</table>
			
		<footer class="pt-4 my-md-5 pt-md-5 border-top">
			<small class="d-block mb-3 text-muted">© 2020 430AM</small>
		</footer>
		
	</div>

</body>
</html>