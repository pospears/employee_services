<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Exercise 1</title>
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
		<form action="${pageContext.request.contextPath}/login" method="POST" accept="application/json" contentType="application/json">
			<div class="form-group">
				<label>Email Address</label> <input type="text" name="email"
					class="form-control" style="width: 60%;">
			</div>
			<div class="form-group">
				<label>Password</label> <input type="password" name="password"
					class="form-control" style="width: 60%;">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Login</button>
				<button type="submit" class="btn btn-primary">Sign Up</button>
			</div>
		</form>
		<span style="color: red; font-weight: bold;">${message}</span>
		</div>
</body>
</html>