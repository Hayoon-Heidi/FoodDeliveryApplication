<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restaurant Registration</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
	
		<h1>Restaurant Registration</h1>
		<form:form action="${pageContext.request.contextPath}/restaurant/restaurant_registration/"
		cssClass="form-horizontal" method="post" modelAttribute="restaurant">
		<div class="form-group">
			<label for="email" class="col-md-3 controllabel">Email</label>
			<div class="col-md-9">
				<form:input path="email" cssClass="form-control" />
			</div>
			<label for="password" class="col-md-3 controllabel">Password</label>
			<div class="col-md-9">
				<form:input path="password" cssClass="form-control" />
			</div>
			<label for="restName" class="col-md-3 controllabel">Restaurant Name</label>
			<div class="col-md-9">
				<form:input path="restName" cssClass="form-control" />
			</div>
			
			<label for="open" class="col-md-3 controllabel">Open</label>
			<div class="col-md-9">
				<form:input type="time" path="open" cssClass="form-control" placeholder="h:mm a"/>
			</div>
			
			<label for="close" class="col-md-3 controllabel">Close</label>
			<div class="col-md-9">
				<form:input type="time" path="close" cssClass="form-control" placeholder="h:mm a"/>
			</div>
			<label for="description" class="col-md-3 controllabel">Description of the Restaurant</label>
			<div class="col-md-9">
				<form:input path="description" cssClass="form-control" />
			</div>
						
			<label for="address" class="col-md-3 controllabel">Address</label>
			<div class="col-md-9">
				<form:input path="address" cssClass="form-control" />
			</div>
			<label for="phoneNumber" class="col-md-3 controllabel">Phone Number</label>
			<div class="col-md-9">
				<form:input path="phoneNumber" cssClass="form-control" />
			</div>
			<label for="category" class="col-md-3 controllabel">Category</label>
			<div class="col-md-9">
				<form:select path="category" id="category">
				 <form:option value="">Select a Category</form:option>
				 <c:forEach items="${category}" var="cat">
				 	<form:option value="${cat.catId}" item="${cat.catName}">${cat.catName}</form:option>
				 </c:forEach>
				</form:select>
			</div>
			<div class="col-md-offset-3 col-md-9">
					<form:button cssClass="btn btnprimary">Submit</form:button>
			</div>
		</div>
		</form:form>
			
		
		
	</div>

</body>
</html>