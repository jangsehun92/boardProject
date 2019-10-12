<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body>
<div class="container" style="margin-top: 50px">
	<div class="row">
		<div class="form-group has-error">
			<div class="number font-red"> 
				<h1 style="color: red;">${error.STATUS_CODE }</h1> 
			</div>
			
			<div class="details">
				<h3>${error.MESSAGE }</h3>
				<p>
					<a href="/">[HOME]</a>으로 돌아가기
				</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>