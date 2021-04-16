<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>(Test) Restaurant</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		<h1 class="my-0 mr-md-auto">Restaurant Information</h1>
		
		<nav class="my-2 my-md-0 mr-md-3" style="float:right">
		
		<a class = "p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_mypage">Back to the mypage</a> 
		<a class = "p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_info/?id=${signedInUser.restId}">Show Info</a> 
		<a class="p-2 text-dark" href="${pageContext.request.contextPath}/restaurant/restaurant_menu/?id=${signedInUser.restId}">Manage Menu</a> 
			<a class="p-2 text-dark" href="${pageContext.request.contextPath}/logout">Log Out</a>
		<!--  <p>for debug ${loggedInCustomer.phoneNumber}</p>-->
		<!--  <p>for debug ${loggedInCustomer.currentAddress}</p>-->
	</nav>
	</div>
	
	<div class="container">
		<c:if test="${ message != null }">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>
		<table class="table table-striped table-bordered">
		<tr><th>Image</th><td>
		<img src="<c:url value="${signedInUser.restImg}" />" style="width:80px;height:80px;"/>
		<form method="post" action='${pageContext.request.contextPath}/editRestInfo' enctype="multipart/form-data">
            <div class="form-group">
                <label for="file1">Please select an image</label>
                <input type="file" name="file1" id="file1">
            </div>
            <button type="submit" class="btn btn-success">Submit</button>
        </form></td>
        </tr>
		<tr>
			<th>Email</th>
			<td>${signedInUser.email}</td>
		</tr>
		<tr>
			<th>Password</th>
			<td>${signedInUser.password }</td>
		</tr>
		<tr>
			<th>Name</th>
			<td>${signedInUser.restName }</td>
		</tr>
		<tr>
			<th>Open</th>
			<td>${signedInUser.open }</td>
		</tr>
		<tr>
			<th>Close</th>
			<td>${signedInUser.close }</td>
		</tr>
		<tr>
			<th>Description</th>
			<td>${signedInUser.description }</td>
		</tr>
		<tr>
			<th>Address</th>
			<td>${signedInUser.address }</td>
		</tr>
		<tr>
			<th>PhoneNumber</th>
			<td>${signedInUser.phoneNumber }</td>
		</tr>
		<tr>
			<th>Category</th>
			<td>${signedInUser.category }</td>
		</tr>

	</table>
			
		
	</div>

</body>
</html>