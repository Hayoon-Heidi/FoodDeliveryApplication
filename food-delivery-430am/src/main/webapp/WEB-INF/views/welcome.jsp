<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
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
			<!--  <img class="my-0 mr-md-auto" width="30" height="30" src="<c:url value="/static/img/svg_icon.png"/>"/> -->
			<h2 class="my-0 mr-md-auto"><a href="${pageContext.request.contextPath}/findRestaurants">430AM Food Delivery</a></h2>
			
			<nav class="my-2 my-md-0 mr-md-3">
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/customer/customer_mypage/">${loggedInCustomer.firstName} ${loggedInCustomer.lastName}</a> 
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/logout">Log Out</a>
				<!--  <p>for debug ${loggedInCustomer.phoneNumber}</p>-->
				<!--  <p>for debug ${loggedInCustomer.currentAddress}</p>-->
			</nav>
		</div>  
		
		<div class="container">
			<c:if test="${ welcomeMessage != null }">
					<div class="alert alert-success" role="alert">${welcomeMessage}</div>
			</c:if>
			<c:if test="${ orderSuccessMessage != null }">
					<div class="alert alert-success justify-content-between row" role="alert">
					<div>${orderSuccessMessage} </div>
					<div><a href="${pageContext.request.contextPath}/orderHistory" style="font-weight: 600;color: darkcyan;">See Order History ></a> </div> </div>
			</c:if>
			
			<div >
			<form:form action="${pageContext.request.contextPath}/findRestaurants/"
				cssClass="card p-2" method="post" modelAttribute="customer">
				<div id="addressSearch" class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text">Find restaurants around</span>
					</div>
					<c:choose>  
					<c:when test="${ loggedInCustomer.currentAddress != null }">
						<form:input type="search" class="form-control" path="currentAddress" value="${loggedInCustomer.currentAddress}" />
					</c:when> 
					<c:otherwise>
						<form:input type="search" class="form-control" path="currentAddress" value="${loggedInCustomer.address}" />
					</c:otherwise>
					</c:choose>
						<div class="input-group-append">
							<form:button class="btn btn-secondary">Search</form:button>
						</div>
				</div>
			</form:form>
			</div>
			<br>
			
			<div class="row">
				<div class="col-md-4">
					<div class="card mb-4 shadow-sm" >
						<a href="${pageContext.request.contextPath}/order/order_restaurantList" class="card-large-text">View All</a>
					</div>
				</div>	
					
				<c:forEach var="cat" items="${categories}">
				<div class="col-md-4">
					<div class="card mb-4 shadow-sm" >
						<a href="${pageContext.request.contextPath}/order/order_restaurantList/?cat=${cat.catId}" class="card-large-text">${cat.catName}</a>
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