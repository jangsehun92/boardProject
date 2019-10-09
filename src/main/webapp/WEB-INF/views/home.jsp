<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/views/commonPages/header.jsp" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<body>
<div class="container" style="margin-top: 50px">
		<div style="margin-left: 5px">
		
			<div>
				<h2>프로젝트 소개</h2>
				<p>Spring + maven + oracle 기반 CRUD 게시판</p>
			</div>
			
			<div>
				<h4>Front-end</h4>
				<p>Bootstrop3</p>
				<p>jQuery3</p>
			</div>

			<div>
				<h4>Back-end</h4>
				<p>Language : JAVA 1.8</p>

				<p>WEB Server : Apache</p>
				<p>WAS Server : Tomcat 8</p>
				<p>Framework : Spring 5.1.3</p>
				<p>build tool : Maven 3.5.1</p>
				<p>ORM : mybatis 3.4.6</p>
				<p>DB : Oracle 11g</p>
			</div>
			
		</div>
	</div>
</body>
</html>
