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
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/inko@1.1.0/inko.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body>
<script type="text/javascript">
//한 > 영 & 영 > 한 변환 자바스크립트 오픈소스 라이브러리
var inko = new Inko();

function check_form(){
	var email = $("#email").val().replace(/\s|/gi,'');
	var password = inko.ko2en($("#password").val().replace(/\s|/gi,''));
	
	if(email==""){
		alert("이메일을 입력해주세요.");
		$("#password").focus();
		return false;
	}
	
	if(password==""){
		alert("비밀번호를 입력해주세요.");
		$("#password").focus();
		return false;
	}
	
	var loginRequest = {
			email : email,
			password : password,
	}
	
	$.ajax({
		url:"/member/login",
		type:"post",
		//contentType : "application/json; charset=UTF-8",
		dataType : "text",
		//data: JSON.stringify(loginRequest), 
		data: loginRequest,
		success:function(data){
			if(data=='OK'){
				location.href="/";	
			}
		},
		error:function(request,status,error){
			if(request.status == '404'){
				alert(request.responseText);
			}
			if(request.status == '302'){
				location.href="/member/email";
			}
			//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	return false;
}
</script>

	<div class="container" style="margin-top: 80px; align-items: center;">
		<div class="col-md-6 main-block-center">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-header" style="text-align: center;">
						로그인
					</h5>
				</div>
				<form class="form-signup form-user panel-body" method="post" action="/member/login" onsubmit="return check_form();">
					<fieldset>
						<input type="text" class="form-control input-sm" id="email" name="email" placeholder="이메일">
						
						<input type="password" class="form-control input-sm" id="password" name="password" placeholder="비밀번호" style="margin-top: 10px;">
					</fieldset>
					
					<button class="btn btn-primary btn-block" type="submit" style="margin-top: 10px;">로그인</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>