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
	
	if(password==""){
		alert("비밀번호를 입력해주세요.");
		$("#password").focus();
		return false;
	}
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
				<form method="post" action="/member/changePassword" class="form-signup form-user panel-body" onsubmit="return check_form();">
					<fieldset>
						<input type="password" class="form-control input-sm" id="password" name="password" placeholder="비밀번호" style="margin-top: 10px;">
					</fieldset>
					<input type="submit" class="btn btn-primary btn-block" value="회원가입" style="margin-top: 10px;">
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>