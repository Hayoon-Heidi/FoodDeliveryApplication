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
					<h3>Hi ${loggedInCustomer.firstName}</h3>
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
			
			<div class="row justify-content-between" >
			<form:form action="${pageContext.request.contextPath}/sort" method="post" modelAttribute="sortRestaurant"
			class="col-md-6 mb-4">
				<div class="input-group">
				<form:select path="sortBy" class="custom-select form-control">
					<form:option type="submit" value="Sort By">Sort By</form:option>
					<form:option type="submit" value="Ratings">Ratings</form:option>
					<form:option type="submit" value="NumOfReviews">Number Of Reviews</form:option>
				</form:select>
				
				<span class="input-group-btn">
				<form:button type="submit" class="btn btn-secondary">Apply</form:button>
				</span>
				</div>
			</form:form>
			
			<form:form action="${pageContext.request.contextPath}/search" method="post" modelAttribute="restaurant"
			class="col-md-6 mb-4">
				<div class="input-group">
					<form:input path="restName" type="search" placeholder="Search restaurant name" class="form-control"/>
					<form:button type="submit" class="btn btn-secondary">Search</form:button>
				</div>
			</form:form>
			</div>
			
			<div class="row">
				
				
				<c:forEach var="restaurant" items="${restaurantList}" varStatus="status">
				<div class="col-md-6">
				<div class="card mb-4 shadow-sm" style="padding: 10px;">
					<div style="display:flex;">
						<img src="<c:url value="${restaurant.restImg}"/>" style="width:80px;height:80px;"/>
						<ul style="list-style:none;margin-bottom: 0!important; padding-left: 1rem;" class="list order-md-2 mb-4">
							<li>
							<h5 style="margin-bottom:0;"><a href="${pageContext.request.contextPath}/order/menuList/?id=${restaurant.restId}">${restaurant.restName}</a></h5>
							</li>
							<li>
							<p>
								<c:choose>
								<c:when test="${restaurant.starRating != null}">
									<span style="word-wrap: initial;margin-right: 0.1rem;" class="stars-container stars-${restaurant.starRating}">★★★★★</span>
									<small style="color:#c5a79f;">${restaurant.rating}</small><small style="color:#80808080;"> |</small><small> ${restaurant.numOfReviewsString}</small>
								</c:when>
								<c:otherwise>
									<span style="margin-right: 0.1rem;" class="stars-container stars-0">★★★★★</span>
									<small>${restaurant.numOfReviewsString}</small>
								</c:otherwise>
								</c:choose>
							</p>
							</li>
							<li>
							<p class="text-muted" style="padding-left:2px; font-size: 90%;">${restaurant.description}</p>
							</li>
						</ul>	
					</div>
						<p style="margin-bottom: 0; text-align: right;">${restaurant.catName}</p>
					
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