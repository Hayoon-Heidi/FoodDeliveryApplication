<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Registration</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/static/css/mycss.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<h1>Customer Registration</h1>
		<form:form action="${pageContext.request.contextPath}/customer/registered/"
		cssClass="form-horizontal" method="post" modelAttribute="customer">
		<div class="form-group">
			<label for="email" class="col-md-3 controllabel">Email</label>
			<div class="col-md-9">
				<form:input type="email" path="email" cssClass="form-control" required="true" placeholder="sample@430am.com"/>
			</div>
			<label for="password" class="col-md-3 controllabel">Password</label>
			<div class="col-md-9">
				<form:input type="password" path="password" cssClass="form-control" required="true"/>
			</div>
			<label for="firstName" class="col-md-3 controllabel">First Name</label>
			<div class="col-md-9">
				<form:input path="firstName" cssClass="form-control" required="true"/>
			</div>
			<label for="lastName" class="col-md-3 controllabel">Last Name</label>
			<div class="col-md-9">
				<form:input path="lastName" cssClass="form-control" />
			</div>
			<label for="address" class="col-md-3 controllabel">Address</label>
			<div class="col-md-9">
				<form:input path="address" cssClass="form-control" required="true"/>
			</div>
			<label for="phoneNumber" class="col-md-3 controllabel">Phone Number</label>
			<div class="col-md-9">
				<form:input type="text" pattern="\d{3}-\d{3,4}-\d{4}" size="11" path="phoneNumber" cssClass="form-control" placeholder="000-000-0000"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-9">
					<form:button cssClass="btn btnprimary">Submit</form:button>
			</div>
		</div>
		</form:form>
			
		
		
	</div>

</body>
</html>