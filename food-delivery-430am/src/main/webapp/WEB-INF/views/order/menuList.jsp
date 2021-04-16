<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
<link rel="shortcut icon"
	href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/static/css/mycss.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  var index = 0;
  $("input[id^='qty']").each(function() {
	  $(this).change(function() {
		 if ($(this).val() > 0) {
			 var btn = $(this).next();
			 btn.attr('disabled', false);
		 } 
		 if ($(this).val() <= 0) {
			 var btn = $(this).next();
			 btn.attr('disabled', true);
		 } 
	  });
	  index++;
  });
  
  $('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	});
});
</script>
<style>

</style>
</head>
<body>
	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
			<h2 class="my-0 mr-md-auto"><a class="text-brown" href="${pageContext.request.contextPath}/findRestaurants">430AM Food Delivery</a></h2>
			
			<nav class="my-2 my-md-0 mr-md-3">
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/customer/customer_mypage/">${loggedInCustomer.firstName} ${loggedInCustomer.lastName}</a> 
				<a class="p-2 text-dark" href="${pageContext.request.contextPath}/logout">Log Out</a>
			</nav>
	</div>  

	<div class="container">
		
		<div>
		<h3>${restaurant.restName}</h3>
			<div style="display:flex; padding:10px;">
			<img style="width:100px; height:100px;" src="<c:url value="${restaurant.restImg}"/>" />
			<ul style="list-style:none;" class="list order-md-2 mb-4">
			
			<li>
				<c:choose>
				<c:when test="${starRating != null}">
					<span style="margin-right:0;" class="stars-container stars-${starRating}">★★★★★</span>
				</c:when>
				<c:otherwise>
					<span style="margin-right:0;" class="stars-container stars-0">★★★★★</span>
				</c:otherwise>
				</c:choose>
			${ratings} 
			<a hidden="true" href="${pageContext.request.contextPath}/seeReviews/?id=${restaurant.restId}">See All Reviews</a>
			</li>
			<li>${restaurant.address}</li>
			<li>Open ${restaurant.open} Close ${restaurant.close}</li>
			</ul>
			</div>
		</div>
		<hr />
		
	<div class="row">
		<div class="col-md-4 order-md-2 mb-4">
			<h4 class="d-flex justify-content-between align-items-center mb-3">
				<span class="text-muted">Your cart</span> <span
					class="badge badge-secondary badge-pill">${count}</span>
			</h4>
			<ul class="list-group mb-5">
			
				<c:forEach var="item" items="${cart}" varStatus="status">
				<li
					class="list-group-item d-flex lh-condensed" style="padding-left: .75rem;">
					<div class="float-left">
					<a class="text-muted" href="${pageContext.request.contextPath}/deleteCart/?index=${status.index}"><svg width="1.4em" height="1.4em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					  <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
					</svg></a>
					</div>
					<div class="d-flex lh-condensed justify-content-between" style="width:100%; padding-left:.75rem;">
					<div>
						<p hidden="true">${item.menuId}</p>
						<h6 class="my-0">${item.menuName}</h6>
						<small class="text-muted">${item.quantity}</small>
					</div> <span class="text-muted">$<fmt:formatNumber value="${item.subtotal}" pattern=".00"/></span>
					</div>
				</li>
				</c:forEach>
				
				<li class="list-group-item d-flex justify-content-between">
					<span>Total(CAD)</span> 
					<strong>
					<c:choose>
					<c:when test="${cartTotal != 0}">
						$<fmt:formatNumber value="${cartTotal}" pattern=".00"/>
					</c:when>
					<c:otherwise>$0.00</c:otherwise>
					</c:choose>
					</strong>
				</li>
			</ul>

			<c:if test="${cartTotal != 0}">
			<form:form action="${pageContext.request.contextPath}/checkout/"
				class="card p-2" method="post" modelAttribute="orderMenu">
				
				<button type="submit" class="btn btn-primary">Proceed Order</button>
				
			</form:form>
			</c:if>
		</div>
		
		<div class="col-md-8 order-md-1">
		
		<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">Menu</li>
		<li class="tab-link" data-tab="tab-2">Reviews</li>
		<li class="tab-link" data-tab="tab-3">Information</li>
		</ul>
		
		<div id="tab-1" class="tab-content current">
		
		<c:forEach var="restMenu" items="${restaurantMenuList}" varStatus="status">
		<div class="card mb-4 shadow-sm" style="padding: 10px;">
			<form:form action="${pageContext.request.contextPath}/addCart"
			method="post" modelAttribute="orderMenu">
				<div class="float-left" style="margin: 0.3rem 0;">
					<div style="display:flex;">
						<img style="width:80px; height:80px;" src="<c:url value="${restMenu.menuImg}"/>" />
						<ul style="list-style:none;margin-bottom: 0!important; padding-left: 1rem;" class="list order-md-2 mb-4">
						<li>						
						<h5>${restMenu.menuName}</h5>
						</li>
						<form:input path="menuId" type="hidden" value="${restMenu.menuId}" />
						<form:input path="menuName" type="hidden" value="${restMenu.menuName}"/>
						<li>
						<p class="text-muted" style="padding: 0 2px;margin-bottom: 0.5rem;"> ${restMenu.menuDesc}</p>
						</li>
						<li>
						<h6 style="margin-bottom:0;">$${restMenu.menuPrice}</h6>
						</li>
						<form:input path="menuPrice" type="hidden" value="${restMenu.menuPrice}"/>
						</ul>
					</div>
				</div>
				<div class="float-right" style="margin: 1.75rem 0;">
					<form:input id="qty${status.index}" type="number" path="quantity" value="0" min="0" max="99"/>
					<form:button id="addBtn${status.index}" class="btn btn-secondary" disabled="true">Add</form:button>
				</div>
			</form:form>	
		</div>
		</c:forEach>
		
		</div>
		<div id="tab-2" class="tab-content">
			<c:choose>
			<c:when test="${reviewExist == true}">
				<table class="table table-striped table-bordered">
						
					<c:forEach var="review" items="${reviewList}">
					<div>
					<div class="card mb-4 shadow-sm" style="padding: 10px;">
						<h5 class="text-brown"><span class="stars-container stars-${review[0].starRating}">★★★★★</span>${review[0].rate}</h5>
						<p style="padding-left:2px;">${review[0].reviewContent}</p>
						<div>
						<c:forEach var="menu" items="${review}">
						<div class="card sm-2 small text-brown menu-card">${menu.menuName}</div>
						</c:forEach>
						</div>
					</div>
					</div>
					</c:forEach>
		
				</table>
			</c:when>
			<c:otherwise>
				<h3>No Reviews Yet</h3>
			</c:otherwise>
			</c:choose>
		</div>
		<div id="tab-3" class="tab-content">
			<p>♨Open ${restaurant.open} Close ${restaurant.close}</p>
			<p>☎Tel ${restaurant.phoneNumber}</p>
			<p>☞Address ${restaurant.address}</p>
			<p>♬${restaurant.description}</p>
		</div>

		<!-- this table is for debugging -->
		<!-- remove hidden="true" to see this table -->
		<table class="table table-striped table-bordered" hidden="true">
			<tr>
				<th colspan="5">Order History From Entire Database</th>
			</tr>
			<tr>
				<th>Order ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
			<c:forEach var="orderedMenu" items="${orderedMenuList}">
			<tr>
				<td>${orderedMenu.orderId}</td>
				<td>${orderedMenu.menuName}</td>
				<td>${orderedMenu.menuPrice}</td>
				<td>${orderedMenu.quantity}</td>
				<td>${orderedMenu.subtotal}</td>
				
			</tr>
			</c:forEach>
			<c:forEach var="item" items="${cart}">
			<tr>
				<td>N/A</td>
				<td>${item.menuName}</td>
				<td>${item.menuPrice}</td>
				<td>${item.quantity}</td>
				<td>${item.subtotal}</td>
				
			</tr>
			</c:forEach>
		</table>
		<!-- this table is for debugging -->
		</div>
		
		
	</div>
	<footer class="pt-4 my-md-5 pt-md-5 border-top">
		<small class="d-block mb-3 text-muted">© 2020 430AM</small>
	</footer>
	</div>



</body>
</html>