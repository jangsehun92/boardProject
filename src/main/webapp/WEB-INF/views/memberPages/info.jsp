<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commonPages/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>profile</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/pagination.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body>

<div class="container" style="margin-top: 80px;">

		<div class="main-block-center" style="align-items: center;">
			<div class="panel panel-default" style="color: #2e6da4; margin-bottom: 0px" >
				<div class="panel-heading">
					<div class="row">
						<div class="com-md-6">
							<h5 class="panel-header" style="text-align: center; ">
							${memberResponse.nickname }
							</h5>
						</div>
					</div>
				</div>
				<div class="panel-body" style="text-align: left;">
					<span>작성한 글 갯수</span>
				</div>
			</div>
				<c:if test="${memberResponse.email eq member.email}">
					<div>
						<span class="pull-left"><a class="btn btn-default" href="/member/edit" role="button">정보수정</a></span>
						<span class="pull-right"><a class="btn btn-default" href="/member/logout" role="button">로그아웃</a></span>
					</div>
				</c:if>
			<div style="margin-top: 40px">
				<span>작성한 글 내역</span>
			</div>
			<table class="table table-hover">	
						<thead>
							<tr>
								<td class="col-md-3"><b>제목</b></td> 
								<td class="col-md-6" align="right"><b>작성자</b></td>
								<td class="col-md-1" align="right"><b>작성 날짜</b></td> 
							</tr>
						</thead>
					<c:choose>
						<c:when test="${empty articleList}">
							<tr>
								<td colspan="4" align="center">
							 	--- 등록된 글이 없습니다 ---
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${articleList }" var="article">
								<tr>
									<td><a href="/article/${article.id }">${article.title }</a></td>
									<td align="right">${article.writer }</td>
									<td align="right">${article.regDate }</td>
								</tr>
							</c:forEach>		
						</c:otherwise>
					</c:choose>
					</table>
		</div>
		<div>
			<nav aria-label="..." style="text-align: center;">
					<ul class="pagination" id="pagination"> </ul>
			</nav>
		</div>
	</div>
</body>
</html>