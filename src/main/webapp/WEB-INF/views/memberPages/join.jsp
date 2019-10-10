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
//유효성 검사
function check_form(){
	//replace 로 공백 제거
	var member_id = $("#member_id").val().replace(/\s|/gi,'');
	var member_pw = $("#member_pw").val().replace(/\s|/gi,'');
	var member_email = $("#member_email").val().replace(/\s|/gi,'');
	var member_nickName = $("#member_nickName").val().replace(/\s|/gi,'');
	
	alert(member_nickName);
	
	if(member_id==""){
		alert("아이디를 입력해주세요.");
		$("#member_id").focus();
		return false;
	}
	
	if(member_pw==""){
		alert("비밀번호를 입력해주세요.");
		$("#member_pw").focus();
		return false;
	}
	
	if(member_email==""){
		alert("이메일을 입력해주세요.");
		$("#member_email").focus();
		return false;
	}
	
	if(member_nickName==""){
		alert("닉네임을 입력해주세요.");
		$("#member_nickName").focus();
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
						<input type="text" class="form-control input-sm" id="member_id" name="member_id" placeholder="아이디">
						
						<input type="password" class="form-control input-sm" id="member_pw" name="member_pw" placeholder="비밀번호" style="margin-top: 10px;">
						
						<input type="text" class="form-control input-sm" id="member_email" name="member_email" placeholder="이메일" style="margin-top: 10px;">
						
						<input type="text" class="form-control input-sm" id="member_nickName" name="member_nickName" placeholder="닉네임" style="margin-top: 10px;">
					</fieldset>
					<input type="submit" class="btn btn-primary btn-block" value="회원가입" style="margin-top: 10px;">
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>