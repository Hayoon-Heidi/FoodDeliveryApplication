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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#hide").click(function() {
			$("table").hide();
		});
		$("#show").click(function() {
			$("table").show();
		});
		
		
	});
	
</script>

<style>

.btn btn-primary:hover + .hide {
	display: block;
}

</style>
</head>
<body>


	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		<h1 class="my-0 mr-md-auto">${loggedInRest.restName}</h1>
		
		<nav class="my-2 my-md-0 mr-md-3">
		<a class = "p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_info/?id=${loggedInRest.restId}">Show Info</a> 
		<a class="p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_menu/?id=${loggedInRest.restId}">Manage Menu</a> 
			<a class="p-2 text-dark" href="${pageContext.request.contextPath}/logout">Log Out</a>
		<!--  <p>for debug ${loggedInCustomer.phoneNumber}</p>-->
		<!--  <p>for debug ${loggedInCustomer.currentAddress}</p>-->
	</nav>
	</div>

	<div class="container">
		<h3 >Deleted Orders</h3>
		<div class="container"
			style="widht: 80%; height: 500px; border-style: solid;  border-radius: 10px; border-color: grey; padding: 5px;">
			<div class="row"
				style="width: 30%; height: 100%; overflow: auto; margin-top: 5px; margin-left: 5px; margin-right: auto; float: left;">

				<c:forEach var="orderList" items="${orderList}" varStatus="status" >
					<div class="card mb-4  shadow-sm" style = "padding : 5px;">


					<form:form action="${pageContext.request.contextPath}/restaurant/restaurant_deleted"
								method="post" modelAttribute="order">
						<h5>
						<a href="${pageContext.request.contextPath}/restaurant/order_deleted/?id=${orderList.orderId}">Order ID: ${orderList.orderId}</a>
						</h5>
						<p>Ordered Time: ${orderList.orderedTimeStringNew}</p>	
							
							<form:input path="orderId" type="hidden" value="${orderList.orderId}"/>
							<form:input path="orderStatus" type="hidden" value="${orderList.orderStatus}"/>
							<button type = "button" id="statusBtn${orderList.orderId}" class="btn btn-primary" disabled>${orderList.orderStatus}</button>
							<p class="hide" style="display:none">If you want to change the order status, click the button</p>
					</form:form>
					</div>
				</c:forEach>

			</div>

			<div class="row"
				style="width: 70%; heigth: 100%; float: right; margin-top: 5px;">
				<div class="col-md-8 order-md-1">

					<p class="information">Click order id to check the information.</p>

					<div class="container" style = "display: none;">



						<hr />

						<div class="row" >
							<div class="col-md-8">
								<table class="table table-striped table-bordered">
									<c:forEach var="orderedMenuList" items="${orderedMenuList}">
									<h1>Order Number: ${orderedMenuList.orderid}</h1>
									<tr>
										<th>Menu Name</th>
										<th>Quantity</th>
										<th>Price</th>
									</tr>


										<tr>
											<td>${orderedMenuList.menuName}($
												${orderedMenuList.menuPrice})</td>
											<td>${orderedMenuList.quantity}</td>
											<td>${orderedMenuList.subtotal}</td>
										</tr>
									</c:forEach>
								</table>



							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<a class = "p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_mypage">Back to the mypage</a> 
</div>


</body>
</html>