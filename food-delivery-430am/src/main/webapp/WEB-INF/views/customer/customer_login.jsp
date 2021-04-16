<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/static/css/mycss.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
<style>
	.form-signin {
    width: 100%;
    max-width: 330px;
    padding: 15px;
    margin: auto;
    font-size: 16px;
}
.form-control{
	font-size: 16px;
}
body {
	font-size: 30px;
    display: flex;
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
    background-color: #f5f5f5;
    margin: 0;
    font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    text-align: left;
    background-color: #fff;
}
svg{
	margin: 20px;
}
</style>
</head>
<body class="text-center">

	<form:form action="${pageContext.request.contextPath}/customer/customer_login/"
	class="form-signin" method="post" modelAttribute="customer">
	
	<c:if test="${ registerSuccess != null }">
	<div class="alert alert-success" role="alert">${registerSuccess}</div>
	</c:if>

	<svg height="100" width="100">
	  <defs>
	    <linearGradient id="grad1" x1="0%" y1="0%" x2="100%" y2="0%">
	      <stop offset="0%"
	      style="stop-color:rgb(255,255,0);stop-opacity:1" />
	      <stop offset="100%"
	      style="stop-color:rgb(255,0,0);stop-opacity:1" />
	    </linearGradient>
	  </defs>
	  <ellipse cx="50" cy="50" rx="50" ry="50" fill="url(#grad1)" />
	  <text fill="#ffffff" font-size="30" font-family="Verdana"
	  x="16" y="62">4:30</text>
		Sorry, your browser does not support inline SVG.
	</svg>
	
	<h1>Customer Login</h1>
	<c:if test="${ message != null }">
		<div class="alert alert-danger" role="alert">${message}</div>
	</c:if>
	
		<label for="email" class="sr-only">Email</label>
		<form:input type="email" path="email" cssClass="form-control" placeholder="Email address" required="true" autofocus="true"/>
		
		<label for="password" class="sr-only">Password</label>
		<form:input type="password" path="password" class="form-control" placeholder="Password" required="true"/>
	
	
		
		<form:button class="btn btn-lg btn-primary btn-block">Login</form:button>
		<p class="mt-5 mb-3 text-muted">© 2020 430AM</p>
	
	</form:form>


</body>
</html>