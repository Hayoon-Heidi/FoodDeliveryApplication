<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Restaurant's Menu</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>

</head>
<body>

	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		<h1 class="my-0 mr-md-auto">${signedInUser.restName}</h1>
		
		<nav class="my-2 my-md-0 mr-md-3">
		
		
		<a class = "p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_mypage">Back to the mypage</a> 
		<a class = "p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_info/?id=${signedInUser.restId}">Show Info</a> 
		<a class="p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_menu/?id=${signedInUser.restId}">Manage Menu</a> 
			<a class="p-2 text-dark" href="${pageContext.request.contextPath}/logout">Log Out</a>
		<!--  <p>for debug ${loggedInCustomer.phoneNumber}</p>-->
		<!--  <p>for debug ${loggedInCustomer.currentAddress}</p>-->
	</nav>
	</div>

	<div class="container">
	
			<h1>Menu List</h1>

			<hr />
			<c:if test="${ message != null }" >
				<div class="alert alert-success" role="alert">${message}</div>
			 </c:if>			
				<table class="table table-striped table-bordered">
				
					<tr>
						<th>Menu Image</th>
						<th>Menu Name</th>
						<th>Menu Description</th>
						<th>Menu Price</th>
						<th>Edit</th>
						

					</tr>
					<c:forEach var="restMenu" items="${restaurantMenuList}">
						<tr>
							<td><img src="<c:url value="${restMenu.menuImg}" />" style="width:80px;height:80px;"/></td>
							<td>${restMenu.menuName}</td>
							<td>${restMenu.menuDesc}</td>
							<td>$ ${restMenu.menuPrice}</td>
							<td><a href="${pageContext.request.contextPath}/restaurant/restaurant_editMenu/?id=${restMenu.menuId}">Edit</a></td>
						</tr>
					</c:forEach>

				</table>

		<h3>Add Menu</h3>
		
			<form:form action="${pageContext.request.contextPath}/addMenu/" cssClass="form-horizontal"
			method="post" modelAttribute="restaurantMenu" enctype="multipart/form-data">

			<div class="form-group">
                <label for="menuImg" class="col-md-3 control-label">Please select an image</label>
                <div class="col-md-9">
                	<form:input path="menuImgFile" type="file" />
            	</div>
            </div>	
            
			<div class="form-group">
				<label for="menuName" class="col-md-3 control-label">Name</label>
				<div class="col-md-9">
					<form:input path="menuName" cssClass="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="menuDesc" class="col-md-3 control-label">Description</label>
				<div class="col-md-9">
					<form:input path="menuDesc" cssClass="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="menuPrice" class="col-md-3 control-label">Price</label>
				<div class="col-md-9">
					<form:input path="menuPrice" cssClass="form-control" />
				</div>
			</div>

			<div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
					<form:button cssClass="btn btn-primary">Submit</form:button>
				</div>
			</div>

		</form:form>
			
		</div>



</body>
</html>
