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

function comment_insert(){
	var comment_content = $("#board_comment_content").val().replace(/\s|/gi,'');
	
	if(comment_content==""){
		alert("댓글을 입력해주세요.");
		$("#board_comment_content").val("");
		$("#board_comment_content").focus();
		return false;
	}
	
	var boardCommentDto = {
			board_no : $("#board_comment_board_no").val(),
			board_comment_group : 0,
			user_id : $("#board_comment_user_id").val(),
			board_comment_content : $("#board_comment_content").val()
	}
	
	$.ajax({
		url:"/board/comment/insert",
		type:"post",
		data: boardCommentDto, 
		success:function(boardCommentDto){
			
			if($("#noComments").length){
				$("#noComments").remove();
			}
			
			$("#board_comment_content").val("");
			$("#comment_list").append("<li class='list-group-item'><span>"+boardCommentDto.user_id+"</span><span class='text-muted'> | <small>"+uxin_timestamp(boardCommentDto.board_comment_regDate)+"</small></span>"+
										"<div style='white-space : pre-wrap;height: 100%'>"+boardCommentDto.board_comment_content+"</div></li>");
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	return false;
}

function comment_list(){
	
	var board_no = { 
			board_no : $("#board_comment_board_no").val() 
	}
	
	$.ajax({
		url:"/board/comment/list",
		type:"post",
		data: board_no, 
		
		success:function(resultMap){
			
			if(!$("#noComments").length){
				$("#comment_list").empty();
			}
			
			values = resultMap.commentList;
			
			$.each(values, function(index, value) {
				$("#board_comment_content").val("");	
				$("#comment_list").append("<li class='list-group-item'><span>"+value.user_id+"</span><span class='text-muted'> | <small>"+uxin_timestamp(value.board_comment_regDate)+"</small></span>"+
											"<div style='white-space : pre-wrap;height: 100%'>"+value.board_comment_content+"</div></li>");
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

</script>
<body>
<input type="hidden" name="category" id="category" value="${boardDto.board_category }">

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
			  	<form method="post" action="board_comment_insert" onsubmit="return comment_insert();">
			  		<input id="board_comment_board_no" type="hidden" name="board_no" value="">
			  		<input id="board_comment_user_id" type="hidden" name="user_id" value="tester">
			  		<textarea id="board_comment_content" name="board_comment_content" class="form-control z-depth-1" id="exampleFormControlTextarea6" rows="3" maxlength="1000" placeholder="댓글을 입력해주세요."></textarea>
			  		<input type="button" class="pull-left btn btn-primary" value="새로고침" onclick="comment_list();">
			  		<input type="submit" class="pull-right btn btn-primary" value="작성">
			  	<br>
			  	</form>
			</div>
			
			<hr>
			<!-- 
			<div>
				<ul class="list-group" id="comment_list">
					<c:choose>
						<c:when test="${empty boardComment_list}">
							<li class="list-group-item" id="noComments">
								<span>댓글이 없습니다.</span>
							</li>
						</c:when>
						
						<c:otherwise>
							<c:forEach items="${boardComment_list }" var="boardCommentDto">										
								<li class="list-group-item"><span>${ boardCommentDto.user_id}</span><span class="text-muted"> | <small><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardCommentDto.board_comment_regDate}"/></small></span>
									<div class="" style="white-space : pre-wrap;height: 100%">${ boardCommentDto.board_comment_content}</div>
								</li>
							</c:forEach>		
						</c:otherwise>
					</c:choose>
				</ul>
			</div> -->

	</div>
</div>

</body>
</html>