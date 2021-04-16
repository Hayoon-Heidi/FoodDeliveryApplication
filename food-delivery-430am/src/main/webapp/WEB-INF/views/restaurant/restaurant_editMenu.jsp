<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Menu</title>
<link rel="shortcut icon" href="<c:url value="/static/img/svg_icon.png"/>" />
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<h1>Edit Menu - ${menu.menuName}</h1>
		<hr />
		
		<h3>Edit Menu</h3>
		<form:form action="${pageContext.request.contextPath}/restaurant/restaurant_editMenu" 
		cssClass="form-horizontal" method="post" modelAttribute="menu" enctype="multipart/form-data">
		
		<div class="form-group">
			<label for="menuId" class="col-md-3 control-label">ID</label>
			<div class="col-md-9">
				<form:input path="menuId" value="${menu.menuId}" cssClass="form-control" readonly="true"/>
			</div>
			<label for="menuImgFile" class="col-md-3 control-label">Menu Image</label>
            <div class="col-md-9">
            	<img src="<c:url value="${menu.menuImg}" />" style="width:80px;height:80px;"/>
            	<form:input path="menuImgFile" type="file" class=""/>
        	</div>
			<label for="menuName" class="col-md-3 control-label">Name</label>
			<div class="col-md-9">
				<form:input path="menuName" value="${menu.menuName}" cssClass="form-control" />
			</div>
			<label for="menuDesc" class="col-md-3 control-label">Description</label>
			<div class="col-md-9">
				<form:input path="menuDesc" value="${menu.menuDesc}" cssClass="form-control" />
			</div>
			<label for="menuPrice" class="col-md-3 control-label">Price</label>
			<div class="col-md-9">
				<form:input path="menuPrice" value="${menu.menuPrice}" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<form:button cssClass="btn btn=primary">Submit</form:button>
			</div>
		</div>
		
		</form:form>
	</div>
</body>
</html>