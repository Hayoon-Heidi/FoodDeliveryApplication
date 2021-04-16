<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order History</title>
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
			</nav>
		</div>  
		
		<div class="container">	
			<div class="row">
				
				<c:forEach var="orderedMenus" items="${orderedMenuList}" varStatus="status">
				<div class="col-md-4">
				<div class="card mb-4 shadow-sm" style="padding: 10px;">
					<h5 hidden="true">Order ID: ${orderedMenus[0].orderId}</h5>
					<h5>${orderedRestList[status.index].restName}</h5>
					<p>${orderedList[status.index].orderedTimeString}</p>
					<p>${orderedList[status.index].orderStatus}</p>
					<c:forEach var="menus" items="${orderedMenus}">
					<div class="card sm-2 small menu-card" style="align-self: unset;">${menus.menuName}</div>
					</c:forEach>
					<p><a href="${pageContext.request.contextPath}/customer/review/?id=${orderedMenus[0].orderId}">Write a Review</a></p>
				</div>
				</div>
				</c:forEach>
				
			</div>
			
			<footer class="pt-4 my-md-5 pt-md-5 border-top">
			<small class="d-block mb-3 text-muted">© 2020 430AM</small>
			</footer>
		</div>
		
</body>
</html>