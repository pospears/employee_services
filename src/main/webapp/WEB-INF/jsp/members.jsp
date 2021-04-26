<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Exercise 1 (Members)</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<header style="height: 15px; background-color: #FF4B4B;"> </header>
		
	<div class="container">
		<form action="${pageContext.request.contextPath}/logout" method="POST" accept="application/json" contentType="application/json">
			
			<div class="form-group">
				<label>Member ${message}:</label> <input type="text" readonly="readonly" name="email" value="${user.email}"
					class="form-control" style="width: 60%;">
			</div>
			
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Sign Out</button>
			</div>
		</form>
		</div>
</body>
</html>