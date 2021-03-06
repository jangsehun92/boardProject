<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/commonPages/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/pagination.js"></script>
<script type="text/javascript">
window.onload = function() {
	var sort = $("#sort").val();
	$("#sort-"+sort).css('fontWeight','bold');

};
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body>
	<input type="hidden" id="category" value="${category }">
	<input type="hidden" id="totalPage" value="${pagination.totalPage }">
	<input type="hidden" id="startPage" value="${pagination.startPage }">
	<input type="hidden" id="endPage" value="${pagination.endPage }">
	<input type="hidden" id="page" value="${pagination.page }">
	<input type="hidden" id="query" value="${query }">
	<input type="hidden" id="sort" value="${sort }">

	<div class="container" style="margin-top: 50px">
		<div id="main">
		</div>
		<hr>
		<div>
			<form class="form-search" method="get" action="/articles/${category }">
				<div class="input-append" style="float:left" align="center">
					<a id="sort-id" href="/articles/${category }?sort=id&query=${query}" style="font: italic bold;">최신순</a>
					<a id="sort-likeCount" href="/articles/${category }?sort=likeCount&query=${query}">추천순</a>
					<a id="sort-replyCount" href="/articles/${category }?sort=replyCount&query=${query}">댓글순</a>
					<a id="sort-viewCount" href="/articles/${category }?sort=viewCount&query=${query}">조회순</a>
				</div>
				<div class="input-append" style="float:right">
					<input type="hidden" id="sort" name="sort" value="${sort }">
					<input type="text" id="query" name="query" class="span2 search-query">
					<button type="submit" class="btn">검색</button>
				</div>
			</form>
		</div>
		<table class="table table-hover">

			<thead>
				<tr>
					<td class="col-md-7"><b>제목</b></td>
					<td class="col-md-1" align="right"><b>추천</b></td>
					<td class="col-md-1" align="right"><b>작성자</b></td>
					<td class="col-md-1" align="right"><b>작성 날짜</b></td>
				</tr>
			</thead>

			<c:choose>
				<c:when test="${empty articleList}">
					<tr>
						<td colspan="4" align="center">--- 등록된 글이 없습니다 ---</td>
					</tr>
				</c:when>
				<c:otherwise>

					<c:forEach items="${articleList }" var="article">
						<tr>
							<td><a href="/article/${article.id }">${article.title } (${article.replyCount })</a></td>
							<td align="right">${article.likeCount }</td>
							<td align="right">${article.writer }</td>
							<td align="right">${article.regDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<hr>
		<c:if test="${!empty member}">
			<div style="float: right">
				<a href="/articles/${category }/create" class="btn btn-primary">글쓰기</a>
			</div>
		</c:if>
		
		<div>
			<nav aria-label="..." style="text-align: center;">
				<ul class="pagination" id="pagination">
				</ul>
			</nav>
		</div>

	</div>
</body>
</html>