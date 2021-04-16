<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
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
		<h1>Write/See a Review</h1>
		<hr/>
		
	
		<c:choose>
		<c:when test="${reviewExist == true}">
			<table class="table table-striped table-bordered">
					
				<c:forEach var="review" items="${review}">
					<tr>
						<th>Review</th>
						<th>Rate</th>
					</tr>
					<tr>
						<td>${review.reviewContent}</td>
						<td>${review.rate}</td>
					</tr>
				</c:forEach>
	
			</table>
		</c:when>
		<c:otherwise>
			<h3>No Reviews Yet</h3>
			<form:form action="${pageContext.request.contextPath}/postReview/" method="post" modelAttribute="review">
				<table class="table table-striped table-bordered">
					<tr>
						<th>Review</th>
						<th>Rate</th>
					</tr>
					<tr>
						<form:input type="hidden" path="orderId" value="${orderId}" />
						<td><form:input type="text" path="reviewContent" placeholder="Leave your comment"/></td>
						<td><form:input type="number" path="rate" min="1" max="5" value="5"/></td>
					</tr>
				</table>
				<form:button class="btn btn-primary">Submit</form:button>
			</form:form>
		</c:otherwise>
		</c:choose>

		<footer class="pt-4 my-md-5 pt-md-5 border-top">
			<small class="d-block mb-3 text-muted">© 2020 430AM</small>
		</footer>
	</div>
	
			
</body>
</html>