<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>See All Reviews</title>
<link rel="shortcut icon"
	href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
		
		<div>
		<h3>${restaurant.restName} Menu List</h3>
			<div class="row">
			<img class="col-md-2 order-md-1 mb-2" src="<c:url value="/static/img/svg_icon.png"/>" width="60" height="60"/>
			<ul class="list col-md-4 order-md-2 mb-4">
			<li>ratings: ${ratings} <a href="#">See All Reviews</a></li>
			<li>${restaurant.address}</li>
			<li>Open ${restaurant.open} Close ${restaurant.close}</li>
			</ul>
			</div>
		</div>
		<hr />
		
	<div class="row">
		
		<c:choose>
		<c:when test="${reviewExist == true}">
			<c:forEach var="review" items="${reviewList}">
			<div class="col-md-6">
			<div class="card mb-4 shadow-sm" style="padding: 10px;">
					<h5>${review[0].rate}</h5>
					<p>
						${review[0].reviewContent}
					</p>
					<div>
					<c:forEach var="menu" items="${review}">
					<div class="card sm-2" style="width: fit-content;float: left;align-self: center;margin: 0 2px 0 2px;">${menu.menuName}</div>
					</c:forEach>
					</div>
			</div>
			</div>
			</c:forEach>
			
			<c:forEach var="review" items="${reviewList}">
			<table class="table table-striped table-bordered">
					<tr><td colspan="3">${review[0].orderId}</td></tr>
					<tr>
						<th>Review</th>
						<th>Rate</th>
						<th>Menu</th>
					</tr>
					<tr>
						<td>${review[0].reviewContent}</td>
						<td>${review[0].rate}</td>
						<c:forEach var="menu" items="${review}">
						<td>${menu.menuName}</td>
						</c:forEach>
					</tr>
			</table>
			</c:forEach>
			<table class="table table-striped table-bordered">
					
				<c:forEach var="review" items="${review}">
					<tr>
						<th>Review</th>
						<th>Rate</th>
						<th>Menu</th>
					</tr>
					<tr>
						<td>${review.reviewContent}</td>
						<td>${review.rate}</td>
						<td>${review.menuName}</td>
					</tr>
				</c:forEach>
	
			</table>
		</c:when>
		<c:otherwise>
			<h3>No Reviews Yet</h3>
			
		</c:otherwise>
		</c:choose>
		
	</div>
	<footer class="pt-4 my-md-5 pt-md-5 border-top">
		<small class="d-block mb-3 text-muted">© 2020 430AM</small>
	</footer>
	</div>



</body>
</html>