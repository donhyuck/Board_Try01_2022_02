<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>
<!-- 아래의 경우 서블릿을 거치지 않고 처리된다. 게시글 등록 페이지로 넘어가는 기능을 구현한다. -->
<!-- <a href="http://localhost:9100/Article/addForm.jsp">글쓰기</a>  -->
<!-- 목록에서 원하는 인덱스로 들어가고자 할때 <a href="/article/detail?idx=${ article.idx }"> -->
	<h3>게시물 목록</h3>
	<!-- 팝업창 / 쿠키정보가 없으면 뜬다-->
	<c:if test="${ popupYn == null }">
		<h5>=== 팝업창 === </h5>
	</c:if>
	
	<!-- jsp조각페이지 재사용 -->
	<%@ include file="header.jspf" %>
		
	<a href="http://localhost:9100/Member/addForm.jsp">회원가입</a>
	<a href="http://localhost:9100/article/showAddForm">글쓰기</a>
	<hr />

	<c:forEach items="${ articleList }" var="article">
		<div>
			번 호 : ${ article.idx }   		<br />
			작성일 : ${ article.regDate }		<br />
 			<a href="/article/detail?idx=${ article.idx }">제 목 : ${ article.title }</a> <br />
			내 용 : ${ article.body }			<br />
			작성자 : ${ article.nickname }	<br />
		</div>
		<hr />
	</c:forEach>

</body>
</html>