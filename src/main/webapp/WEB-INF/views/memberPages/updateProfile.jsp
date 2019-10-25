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
	var email = $("#email").val().replace(/\s|/gi,'');
	
	if(email==""){
		alert("이메일을 입력해주세요.");
		$("#email").focus();
		return false;
	}

}

</script>
<div class="container" style="margin-top: 80px;">
		<div class="col-md-6 main-block-left" style="align-items: center;">
			<div class="panel panel-default" style="color: #ddd;" >
				<div class="panel-heading">
					<h5 class="panel-header" style="text-align: center;">
						회원 정보 수정
					</h5>
				</div>
				<form method="post" action="/member/profile" class="form-signup form-user panel-body" onsubmit="return check_form();">
					<input type="hidden" name="number" value="${member.number }">
					<fieldset>
						이메일 주소
						<input type="text" class="form-control input-sm" id="email" name="email" placeholder="이메일" value="${member.email }" style="margin-top: 10px;" >
						닉네임
						<input type="text" class="form-control input-sm" id="nickname" name="nickname" placeholder="닉네임" value="${member.nickname }" style="margin-top: 10px;">
					</fieldset>
					<input type="submit" class="btn btn-primary btn-block" value="정보 수정" style="margin-top: 10px;">
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>