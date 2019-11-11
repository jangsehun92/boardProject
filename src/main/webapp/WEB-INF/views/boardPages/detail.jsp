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
			articleId : "${resultMap.article.id}",
			content : $("#replyContent").val(),
	}
	
	$.ajax({
		url:"/reply",
		type:"post",
		contentType : "application/json; charset=UTF-8",
		dataType : "text",
		data: JSON.stringify(replyCreateRequest), 
		
		success:function(data){
			alert("댓글이 입력되었습니다.");
			replyList();
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	return false;
}

function replyList(){
	
	$.ajax({
		url:"/reply/${resultMap.article.id}",
		type:"GET",
		dataType: "JSON",
		success:function(data){
			if($("#noComments").length){
				$("#noComments").empty();
			}
			$("#replyList").empty();
			$("#replyContent").val("");
			
			if(data.length == 0){
				$("#replyList").append(
					"<li class='list-group-item'>"+
						"<div>"+
							"<div>"+
								"<div>"+
									"<div id='replyForm'>"+
										"<span>댓글이 없습니다.</span>"+
									"</div>"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</li>"
				);
			}
			$.each(data, function(index, value) {
				if(value.memberId == "${member.id}"){
					$("#replyList").append(
					"<li class='list-group-item'>"+
						"<div style='position: relative; height: 100%'>"+
							"<div>"+
								"<div>"+
									"<span>"+value.nickname+"</span><span class='text-muted'> | <small>"+uxin_timestamp(value.regDate)+"</small></span>"+
										"<div id='dropdownForm-"+value.id+"' style='float: right;'>"+
											"<div class='btn-group'>"+
												"<button type='button' class='btn btn-default btn-xs dropdown-toggle' data-toggle='dropdown' aria-expanded='false'>"+
													"<span class='caret'></span>"+
												"</button>"+
												"<ul class='dropdown-menu' role='menu'>"+
													"<li><a onClick='replyUpdateForm("+value.id+")'>수정</a></li>"+
													"<li><a onClick='deleteConfirm("+value.id+")'>삭제</a></li>"+
												"</ul>"+
										"</div>"+
								"</div>"+
							"<div id='replyForm-"+value.id+"' style='white-space : pre-wrap;height: 100%'><p>"+value.content+
							"</p></div>"+
							"</div>"+
								"<div id='updateForm-"+value.id+"' style='display: none;'>"+
									"<form method='post' action='/reply/"+value.id+"' onsubmit='return replyUpdate("+value.id+");'>"+
										"<input type='hidden' name='_method' value='PUT'>"+
										"<textarea id='replyContent-"+value.id+"' name='content' class='form-control z-depth-1' rows='3' maxlength='1000' placeholder='댓글을 입력해주세요.'>"+value.content+"</textarea>"+
										"<input type='submit' style='width:50%' class='btn btn-success' value='수정'>"+
										"<input type='button' style='width:50%' class='btn btn-primary' value='취소' onclick='replyForm("+value.id+")'>"+
 									"</form>"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</li>");
					$('.dropdown-toggle').dropdown();
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

function replyUpdateForm(id){
	var dropdownForm = $("#dropdownForm-"+id);
	var replyForm = $("#replyForm-"+id);
	var updateForm = $("#updateForm-"+id);
	
	replyForm.hide();
	dropdownForm.hide();
	updateForm.show();
	$("#replyContent-"+id).focus();
}

function replyForm(id){
	var dropdownForm = $("#dropdownForm-"+id);
	var replyForm = $("#replyForm-"+id);
	var updateForm = $("#updateForm-"+id);
	
	
	replyForm.show();
	dropdownForm.show();
	updateForm.hide();
	$("#replyForm-"+id).focus();
}

function replyUpdate(id){
	var replyUpdateRequest = {
			content : $("#replyContent-"+id).val(),
	}
	
	$.ajax({
		url:"/reply/"+id,
		type:"put",
		contentType : "application/json; charset=UTF-8",
		dataType : "text",
		data: JSON.stringify(replyUpdateRequest), 
		
		success:function(data){
			alert("댓글이 수정되었습니다.");
			replyList();
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
	return false;
}

function replyDelete(id){
	$.ajax({
		url:"/reply/"+id,
		type:"delete",
		success:function(entity){
			alert("댓글이 삭제되었습니다.");
			replyList();
		},
		error:function(request,status,error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
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

function deleteConfirm(id){
	if(confirm("삭제하시겠습니까?")){
		replyDelete(id);
	}else{
		return;
	}
}

</script>
<body>
<div class="container" style="margin-top: 50px">
	<input type="hidden" id ="category" value="${resultMap.article.category }">
	<div class="header">
		<h2>글보기</h2>
		<hr>
			<ul class="list-group">
				<li class="list-group-item">
					<div class="title">
						<h3>${resultMap.article.title }</h3>
					</div>
					<div class="row" >
						<div class="col-md-4" style="float: left">
							<span>${resultMap.article.writer } |</span> <span><small><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${resultMap.article.regDate }"/></small></span>
						</div>
						<div style="float: right">
							<span class="text-muted"><small>조회 | 댓글 | 추천 </small></span>
						</div>
					</div>
				</li>
				<li class="list-group-item">
					<div>
						<div id="board_content" style="white-space : pre-wrap;height: 100%">${resultMap.article.content }</div>
					</div>
				</li>
			</ul>
			<div class="row" style="margin-left: 0px; margin-right: 0px">
					<div style="float: left">
					<a href="/articles/${resultMap.article.category }" class="btn btn-primary">목록</a>
					</div>
					<div style="float: left">
					<c:if test="${member.id eq resultMap.article.memberId}">
						<input type="button" class="btn btn-primary" value="수정" onclick="location.href='/article/edit/${resultMap.article.id}'">
						<div style="float: left">
						<form method="post" action="/article/${resultMap.article.id }">
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
			  	<c:choose>
						<c:when test="${empty member}">
							<span>로그인을 하시면 댓글을 등록할 수 있습니다.</span>
						</c:when>
						<c:otherwise>
							<label>댓글</label>
								<div style="position: relative; height: 100%">
									<div>
									<form method="post" action="/reply" onsubmit="return replyCreate();">
										<textarea id="replyContent" name="content" class="form-control z-depth-1" rows="3" maxlength="1000" placeholder="댓글을 입력해주세요."></textarea>
										<input type="submit" class="btn btn-success" style="width:100%;" value="작성">
									</form>
									</div>
									<div>
									</div>
								</div>
					</c:otherwise>
				</c:choose>
			</div>
			
			<hr>
				<input type="button" class="btn btn-primary" value="새로고침" onclick="listConfirm();">
			<div>
				
				<ul class="list-group" id="replyList">
					
					<c:choose>
						<c:when test="${empty resultMap.replyList}">
							<li class="list-group-item" id="noComments">
								<div>
									<div>
										<div>	
											<div id="replyForm">
												<span>댓글이 없습니다.</span>
											</div>
										</div>
									</div>
								</div>
							</li>
						</c:when>
						
						<c:otherwise>
							<c:forEach items="${resultMap.replyList }" var="replyDto">
								<li class="list-group-item">
									<div style="position: relative; height: 100%">
										<div>
											<div>
												<span>${ replyDto.nickname}</span><span class="text-muted"> | <small><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${replyDto.regDate}"/></small></span>
													<c:if test="${member.id eq replyDto.memberId}">
													<div id="dropdownForm-${ replyDto.id}" style="float: right;">
														<div class="btn-group">
															<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
																<span class="caret"></span>
															</button>
															<ul class="dropdown-menu" role="menu">
																<li><a onClick="replyUpdateForm(${ replyDto.id})">수정</a></li>
																<li><a onClick="deleteConfirm(${ replyDto.id})">삭제</a></li>
															</ul>
														</div>
													</div>
													</c:if>
										<div id="replyForm-${replyDto.id }" style="white-space : pre-wrap;height: 100%"><p>${replyDto.content }</p></div>
										</div>
											<div id="updateForm-${replyDto.id }" style='display: none;'>
												<form method="post" action="/reply/${replyDto.id }" onsubmit="return replyUpdate(${replyDto.id });">
													<input type="hidden" name="_method" value="PUT">
													<textarea id="replyContent-${replyDto.id }" name="content" class="form-control z-depth-1" rows="3" maxlength="1000" placeholder="댓글을 입력해주세요.">${replyDto.content }</textarea>
													<input type="submit" style="width:50%" class="btn btn-success" value="수정">
													<input type="button" style="width:50%; float: left;" class="btn btn-primary" value="취소" onclick="replyForm(${replyDto.id })">
			 									</form>
											</div>
										</div>
									</div>
								</li>		
														
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

	</div>
</div>

</body>
</html>