<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commonPages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body>
<script type="text/javascript">
$(document).ready(function(){
	$('#email').css("ime-mode", "disabled");
	$('#password').css("ime-mode", "disabled");
	$('#passwordCheck').css("ime-mode", "disabled");
});
//유효성 검사
function check_form(){
	//replace 로 공백 제거
	var email = $("#email").val().replace(/\s|/gi,'');
	var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var password = $("#password").val().replace(/\s|/gi,'');
	var passwordCheck = $("#passwordCheck").val().replace(/\s|/gi,'');
	var nickname = $("#nickname").val().replace(/\s|/gi,'');
	
	if(email=="") {
		alert("이메일을 입력해주세요.");
		$("#email").focus();
		return false;
	}
	
	if (!emailCheck.test(email)) {
		alert("email 형식에 맞지않습니다.");
		return false;
	}
	
	if(password=="") {
		alert("비밀번호를 입력해주세요.");
		$("#password").focus();
		return false;
	}
	
	if(!/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/.test(password)) { 
        alert('비밀번호는 숫자와 영문자 조합으로 8~20자를 사용해야 합니다.'); 
        return false;
    }

	if(passwordCheck==""){
		alert("비밀번호를 재입력해주세요.");
		$("#passwordCheck").focus();
		return false;
	}
	
	if(password != passwordCheck){
		alert("비밀번호가 일치하지 않습니다.");
		$("#password").focus();
		return false;
	}
	
	if(nickname==""){
		alert("닉네임을 입력해주세요.");
		$("#nickname").focus();
		return false;
	}
}

</script>
<div class="container" style="margin-top: 80px;">
		<div class="col-md-6 main-block-left" style="align-items: center;">
			<div class="panel panel-default" style="color: #ddd;" >
				<div class="panel-heading">
					<h5 class="panel-header" style="text-align: center;">
						이메일 회원가입
					</h5>
				</div>
				<form method="post" action="/member/register" class="form-signup form-user panel-body" onsubmit="return check_form();">
					<fieldset>
						<input type="text" class="form-control input-sm" id="email" name="email" placeholder="이메일" maxlength="30" style="margin-top: 10px;">
						
						<input type="password" class="form-control input-sm" id="password" name="password" placeholder="비밀번호" style="margin-top: 10px;">
						
						<input type="password" class="form-control input-sm" id="passwordCheck" placeholder="비밀번호 확인" style="margin-top: 10px;">
						
						<input type="text" class="form-control input-sm" id="nickname" name="nickname" placeholder="닉네임" maxlength="10" style="margin-top: 10px;">
					</fieldset>
					<input type="submit" class="btn btn-primary btn-block" value="회원가입" style="margin-top: 10px;">
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>