<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/commonPages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board_detail</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

function replyCreate(){
	var content = $("#replyContent").val().replace(/\s|/gi,'');
	
	if(content==""){
		alert("댓글을 입력해주세요.");
		$("#replyContent").val("");
		$("#replyContent").focus();
		return false;
	}
	
	var replyCreateRequest = {
			articleId : "${article.id}",
			content : $("#replyContent").val(),
	}
	
	$.ajax({
		url:"/reply",
		type:"post",
		async: true,
		data: replyCreateRequest, 
		
		success:function(resultMap){
			if(resultMap.message == "ok"){
				replyList();
			}
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	return false;
}

function replyList(){
	var articleId = { 
		articleId : "${article.id}" 
	}
	
	$.ajax({
		url:"/reply",
		type:"GET",
		async: true,
		data: articleId, 

		success:function(resultMap){
			
			if(resultMap.message == "ok"){
			}
			
			if($("#noComments").length){
				$("#noComments").empty();
			}
			$("#replyList").empty();
			$("#replyContent").val("");
			
			values = resultMap.replyList;
			$.each(values, function(index, value) {
				if(value.memberId == "${member.id}"){
					$("#replyList").append("<li class='list-group-item'><span>"+value.nickname+"</span><span class='text-muted'> | <small>"+uxin_timestamp(value.regDate)+"</small></span>"+
							"<div style='float: right;''>"+
								"<div class='btn-group'>"+
								  "<button type='button' class='btn btn-default btn-xs dropdown-toggle' data-toggle='dropdown' aria-expanded='false'>"+
								  "<span class='caret'></span>"+
								  "</button>"+
								  "<ul class='dropdown-menu' role='menu'>"+
								    "<li><a href='#'>수정</a></li>"+
								    "<li><a href='#'>삭제</a></li>"+
								  "</ul>"+
								"</div>"+
						"</div>"+"<div style='white-space : pre-wrap;height: 100%'>"+value.content+"</div></li>");
				}else{
					$("#replyList").append("<li class='list-group-item'><span>"+value.nickname+"</span><span class='text-muted'> | <small>"+uxin_timestamp(value.regDate)+"</small></span>"+
											"<div style='white-space : pre-wrap;height: 100%'>"+value.content+"</div></li>");
				}
			});
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n\n"+"message:"+request.responseText+"\n\n"+"error:"+error);
		}
	});
	return false;
}

function uxin_timestamp(time){
	var date = new Date(time);
	var year = date.getFullYear();
	var month = "0" + (date.getMonth()+1);
	var day = "0" + date.getDate();
	var hour = "0" + date.getHours();
	var minute = "0" + date.getMinutes();
	//var second = "0" + date.getSeconds();
	return year + "-" + month.substr(-2) + "-" + day.substr(-2) + " " + hour.substr(-2) + ":" + minute.substr(-2);
}

function listConfirm(){
	if(confirm("새로고침 하시겠습니까?")){
		replyList();
	}else{
		return;
	}
}

</script>
<body>

<div class="container" style="margin-top: 50px">
	
	<div class="header">
		<h2>글보기</h2>
		<hr>
			<ul class="list-group">
			
				<li class="list-group-item">
					<div class="title">
						<h3>${article.title }</h3>
					</div>
					
					<div class="row" >
						<div class="col-md-4" style="float: left">
							<span>${article.writer } |</span> <span><small><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${article.regDate }"/></small></span>
						</div>
						<div style="float: right">
							<span class="text-muted"><small>조회 | 댓글 | 추천 </small></span>
						</div>
					</div>
				
				</li>
			<li class="list-group-item">
			
			<div>
				<div id="board_content" style="white-space : pre-wrap;height: 100%">${article.content }</div>
			</div>
			</li>
			</ul>
			<div class="row" style="margin-left: 0px; margin-right: 0px">
					<div style="float: left">
					<a href="/articles/${article.category }" class="btn btn-primary">목록</a>
					</div>
					<div style="float: left">
					<c:if test="${member.id eq article.memberId}">
						<input type="button" class="btn btn-primary" value="수정" onclick="location.href='/article/edit/${article.id}'">
						<div style="float: left">
						<form method="post" action="/article/${article.id }">
							<input type="hidden" name="_method" value="delete"/>
							<input type="submit" class="btn btn-primary" value="삭제">
						</form>
						</div>
					</c:if> 
					</div>
				<div style="float: right">
					<input type="button" class="btn btn-primary" value="추천">
					<input type="button" class="btn btn-primary" value="비추천">
				</div>
			</div>
			<hr>
			
			<div class="form-group shadow-textarea">
			  	<label>댓글</label>
			  	<form method="post" action="/reply" onsubmit="return replyCreate();">
			  		<textarea id="replyContent" name="content" class="form-control z-depth-1" rows="3" maxlength="1000" placeholder="댓글을 입력해주세요."></textarea>
			  		<input type="submit" class="pull-right btn btn-primary" value="작성">
			  	</form>
			  	<div>
			  	<input type="button" class="pull-left btn btn-primary" value="새로고침" onclick="listConfirm();">
			  	
			  	</div>
			</div>
			
			<div>
				<hr>
				<ul class="list-group" id="replyList">
					<c:choose>
						<c:when test="${empty articleList}">
							<li class="list-group-item" id="noComments">
								<div>
									<div><span>작성자</span><span class="text-muted"> | <small>날짜</small></span></div>
									<div style="float: right;">
										<div>
										 	<a href="#">수정</a><br>
										    <a href="#">삭제</a>
										</div>
										<div class="btn-group">
										  <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
										  <span class="caret"></span>
										  </button>
										  <ul class="dropdown-menu" role="menu">
										    <li><a href="#">수정</a></li>
										    <li><a href="#">삭제</a></li>
										  </ul>
										</div>
									</div>
									<!--  <span>댓글이 없습니다.</span> -->
									<div class="" style="white-space : pre-wrap;height: 100%">내용</div>
								</div>
							</li>
						</c:when>
						
						<c:otherwise>
							<!-- 
							<c:forEach items="${boardComment_list }" var="boardCommentDto">										
								<li class="list-group-item"><span>${ boardCommentDto.user_id}</span><span class="text-muted"> | <small><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardCommentDto.board_comment_regDate}"/></small></span>
									<div class="" style="white-space : pre-wrap;height: 100%">${ boardCommentDto.board_comment_content}</div>
								</li>
							</c:forEach>
							 -->	
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

	</div>
</div>

</body>
</html>