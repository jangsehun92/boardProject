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
	var password = $("#password").val().replace(/\s|/gi,'');
	var newPassword = $("#newPassword").val().replace(/\s|/gi,'');
	var newPasswordCheck = $("#newPasswordCheck").val().replace(/\s|/gi,'');
	
	if(password==""){
		alert("비밀번호를 입력해주세요.");
		$("#password").focus();
		return false;
	}

	
	if(newPassword==""){
		alert("새 비밀번호를 입력해주세요.");
		$("#newPassword").focus();
		return false;
	}
	

	if(!/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/.test(newPassword)) { 
        alert('비밀번호는 [소,대문자/숫자/특수문자] 조합으로 8~20자를 사용해야 합니다.'); 
        return false;
    }
	
	if(newPasswordCheck==""){
		alert("새 비밀번호 확인을 입력해주세요.");
		$("#password").focus();
		return false;
	}
	
	if(newPassword != newPasswordCheck){
		alert("새 비밀번호가 서로 다릅니다.");
		$("#newPassword").focus();
		return false;
	}
	
	var memberPasswordChangeRequest = {
			password : password,
			newPassword : newPassword
	}
	
	
	$.ajax({
		url:"/member/passwordChange",
		type:"post",
		//contentType : "application/json; charset=UTF-8",
		dataType : "text",
		//data: JSON.stringify(loginRequest), 
		data: memberPasswordChangeRequest,
		success:function(data){
			alert("비밀번호를 변경하였습니다. 다시 로그인 해주세요.");
			location.href="/"+data;	
		},
		error:function(request,status,error){
			if(request.status == '404'){
				alert("비밀번호 변경에 실패하였습니다. 다시 확인해 주세요.");
				location.href="/member/"+request.responseText;	
			}else{
				alert("알 수 없는 에러가 발생 하였습니다.");
			}
			//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	
	return false;
}

</script>
<div class="container" style="margin-top: 80px;">
		<div class="col-md-6 main-block-left" style="align-items: center;">
			<div class="panel panel-default" style="color: #ddd;" >
				<div class="panel-heading">
					<h5 class="panel-header" style="text-align: center;">
						비밀번호 변경
					</h5>
				</div>
				<form method="post" action="/member/passwordChange" class="form-signup form-user panel-body" onsubmit="return check_form();">
					<fieldset>
						<input type="password" class="form-control input-sm" id="password" name="password" placeholder="비밀번호" style="margin-top: 10px;">
						
						<input type="password" class="form-control input-sm" id="newPassword" name="newPassword" placeholder="새 비밀번호" style="margin-top: 10px;">
						
						<input type="password" class="form-control input-sm" id="newPasswordCheck" placeholder="새 비밀번호 확인" style="margin-top: 10px;">
					</fieldset>
					<input type="submit" class="btn btn-primary btn-block" value="변경" style="margin-top: 10px;">
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>