<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commonPages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<body>
	<div class="container" style="margin-top: 80px; align-items: center;">
		<div class="row">
		<div class="form-group">
			<div class="number font-red"> 
				<h1 style="color: red;">이메일 인증을 완료해 주세요!</h1> 
			</div>
			
			<div class="details">
				<h3>이메일 정보 : ${email }</h3>
				<p>
					<a href="/member/resendEmail/${email }">인증 이메일 재발송</a>
				</p>
			</div>
		</div>
	</div>
	</div>
</body>
</html>