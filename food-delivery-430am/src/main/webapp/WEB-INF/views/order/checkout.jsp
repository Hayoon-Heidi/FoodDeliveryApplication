<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
<link rel="shortcut icon"
	href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/static/css/mycss.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>

</head>
<body>
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		<h2 class="my-0 mr-md-auto">
			<a href="${pageContext.request.contextPath}/findRestaurants">430AM
				Food Delivery</a>
		</h2>

		<nav class="my-2 my-md-0 mr-md-3">
			<a class="p-2 text-dark"
				href="${pageContext.request.contextPath}/customer/customer_mypage/">${loggedInCustomer.firstName}
				${loggedInCustomer.lastName}</a> <a class="p-2 text-dark"
				href="${pageContext.request.contextPath}/logout">Log Out</a>
		</nav>
	</div>

	<div class="container">

		<h1>Confirm Your Order</h1>

		<hr />

		<div class="row">
			<div class="col-md-4 order-md-2 mb-4">
			<h4 class="d-flex justify-content-between align-items-center mb-3">
				<span class="text-muted">Your cart</span> <span
					class="badge badge-secondary badge-pill">${count}</span>
			</h4>
			<ul class="list-group mb-3">
				<li class="list-group-item d-flex justify-content-between lh-condensed">
					<h5 class="text-mutes">${restaurant.restName}</h5>
				</li>
				<c:forEach var="item" items="${cart}">
				<li
					class="list-group-item d-flex justify-content-between lh-condensed">
					<div>
						<h6 class="my-0">${item.menuName}</h6>
						<small class="text-muted">${item.quantity}</small>
					</div> <span class="text-muted">$<fmt:formatNumber value="${item.subtotal}" pattern=".00"/></span>
				</li>
				</c:forEach>
				
				<li class="list-group-item d-flex justify-content-between">
					<span>Total(CAD)</span> 
					<strong>
					<c:choose>
					<c:when test="${cartTotal != null}">
						$<fmt:formatNumber value="${cartTotal}" pattern=".00"/>
					</c:when>
					<c:otherwise>$0.00</c:otherwise>
					</c:choose>
					</strong>
				</li>
			</ul>
			</div>
			
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Delivery address</h4>
				<div >
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="firstName">First name</label> <input type="text"
								class="form-control" id="firstName" placeholder="" value="${loggedInCustomer.firstName}"
								required="required">
							<div class="invalid-feedback">Valid first name is required.
							</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="lastName">Last name</label> <input type="text"
								class="form-control" id="lastName" placeholder="" value="${loggedInCustomer.lastName}"
								required="">
							<div class="invalid-feedback">Valid last name is required.
							</div>
						</div>
					</div>


					<div class="mb-3">
						<label for="address">Address</label> 
						<c:choose>  
						<c:when test="${ loggedInCustomer.currentAddress != null }">
							<input type="text" class="form-control" value="${loggedInCustomer.currentAddress}" readOnly="readOnly"/>
						</c:when> 
						<c:otherwise>
							<input type="text" class="form-control" value="${loggedInCustomer.address}"  readOnly="readOnly"/>
						</c:otherwise>
						</c:choose>
					</div>
					<div class="mb-3">
						<label for="phoneNumber">Phone Number</label> <input type="text"
							class="form-control" id="phoneNumber" placeholder="000-000-0000"
							value="${loggedInCustomer.phoneNumber}" required="">
						<div class="invalid-feedback">Please enter your shipping
							address.</div>
					</div>

					<hr class="mb-4">
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" id="save-info">
						<label class="custom-control-label" for="save-info">Save
							this information for next time</label>
					</div>
					<hr class="mb-4">

					<h4 class="mb-3">Payment</h4>

					<div class="d-block my-3">
						<div class="custom-control custom-radio">
							<input id="credit" name="paymentMethod" type="radio"
								class="custom-control-input" checked="" required=""> <label
								class="custom-control-label" for="credit">Credit card</label>
						</div>
						<div class="custom-control custom-radio">
							<input id="debit" name="paymentMethod" type="radio"
								class="custom-control-input" required=""> <label
								class="custom-control-label" for="debit">Debit card</label>
						</div>
						<div class="custom-control custom-radio">
							<input id="paypal" name="paymentMethod" type="radio"
								class="custom-control-input" required=""> <label
								class="custom-control-label" for="paypal">PayPal</label>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="cc-name">Name on card</label> <input type="text"
								class="form-control" id="cc-name" placeholder="">
							<small class="text-muted">Full name as displayed on card</small>
							<div class="invalid-feedback">Name on card is required</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="cc-number">Credit card number</label> <input
								type="text" class="form-control" id="cc-number" placeholder="">
							<div class="invalid-feedback">Credit card number is
								required</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3 mb-3">
							<label for="cc-expiration">Expiration</label> <input type="text"
								class="form-control" id="cc-expiration" placeholder="">
							<div class="invalid-feedback">Expiration date required</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="cc-cvv">CVC</label> <input type="text"
								class="form-control" id="cc-cvv" placeholder="">
							<div class="invalid-feedback">Security code required</div>
						</div>
					</div>
					<hr class="mb-4">
					<form:form action="${pageContext.request.contextPath}/checkoutSuccess/"
					class="card p-2" method="post" modelAttribute="orderMenu">
					<button class="btn btn-primary btn-lg btn-block" type="submit">Continue
						to checkout</button>
					</form:form>
				</div>
			</div>
		</div>

		<footer class="pt-4 my-md-5 pt-md-5 border-top">
				<small class="d-block mb-3 text-muted">© 2020 430AM</small>
		</footer>

	</div>



</body>
</html>