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
		<div class="col-md-6 main-block-center">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-header" style="text-align: center;">
						아이디 로그인
					</h5>
				</div>
				<form class="form-signup form-user panel-body">
					<fieldset>
						<input type="text" class="form-control input-sm" id="user_id" name="user_id" placeholder="아이디">
						
						<input type="password" class="form-control input-sm" id="user_pw" name="uesr_pw" placeholder="비밀번호" style="margin-top: 10px;">
					</fieldset>
					
					<button class="btn btn-primary btn-block" type="submit" style="margin-top: 10px;">로그인</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>