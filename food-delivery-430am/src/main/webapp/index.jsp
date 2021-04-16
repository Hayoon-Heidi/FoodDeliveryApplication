<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>4:30AM Food Delivery</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/static/css/bootstrap.css" />"
	rel="stylesheet">	
<link href="<c:url value="/static/css/cover.css" />"
	rel="stylesheet">		
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body class="text-center">
<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
  
	
  <main role="main" class="inner cover">
  
  	<svg height="100" width="100">
		  <defs>
		    <linearGradient id="grad1" x1="0%" y1="0%" x2="100%" y2="0%">
		      <stop offset="0%"
		      style="stop-color:rgb(200,255,213);stop-opacity:1" />
		      <stop offset="100%"
		      style="stop-color:rgb(213,200,255);stop-opacity:1" />
		    </linearGradient>
		  </defs>
		  <ellipse cx="50" cy="50" rx="50" ry="50" fill="url(#grad1)" />
		  <text fill="#ffffff" font-size="30" font-family="Verdana"
		  x="16" y="62">4:30</text>
			Sorry, your browser does not support inline SVG.
	</svg>
		
    <h1 class="cover-heading">4:30AM Food Delivery</h1>
    <p class="lead">Meet Your Favorite Restaurants At Your Favorite Place</p>
    
    <div>
    <p class="lead">
    	For Customers<br>
      <a href="customer/registration" class="btn btn-lg btn-secondary">Sign Up</a>
      <a href="customer/customer_login" class="btn btn-lg btn-secondary">Sign In</a>
   	</p>
    </div>
    <div>
    <p class="lead">
    	For Restaurants<br>
      <a href="restaurant/restaurant_registration" class="btn btn-lg btn-secondary">Sign Up</a>
      <a href="restaurant/restaurant_login" class="btn btn-lg btn-secondary">Sign In</a>
  	</p>
  	</div>
  	
  </main>

  <footer class="mastfoot mt-auto">
    <div class="inner">
		<p class="mt-5 mb-3 text-muted">© 2020 430AM</p>    
	</div>
  </footer>
</div>

<!--   
<h2>430AM Food Delivery</h2>
<h4><a href="customer_registration">Register_Customer</a></h4>
<h4><a href="restaurant_registration">Register_Restaurant</a></h4>
<h4><a href="customer_login">Login</a></h4>
<h4><a href="restaurant_login">Login for Restaurants</a></h4> -->
</body>
</html>
