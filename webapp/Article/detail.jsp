<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
	<h3>게시글 상세</h3>
	<hr />
	<a href="http://localhost:9100/article/list">목록으로 이동</a>
	<hr />
		<c:choose>
			<c:when test="${ loginedUserName == null }">
				<a href="/member/showLoginForm.do">로그인</a>
			</c:when>
			
			<c:otherwise>
				${ loginedUserName }님 안녕하세요!
				<a href="/member/logout.do">로그아웃</a>			
			</c:otherwise>
		</c:choose>
	<hr />

	<div>
		번 호 : ${ article.idx }		 <br />
		등록일 : ${ article.regDate }	 <br />
		제 목 : ${ article.title }	 <br />
		내 용 : ${ article.body }		 <br />
		작성자 : ${ article.nickname } <br />
	</div>
	<hr />
	
	<!-- 해당 번호를 같이 쿼리스트링으로 넘긴다. -->
	<!-- 수정 기능의 경우 a테그로 처리가능하지만 삭제기능과 양식을 통일하고자 form테그로 처리 -->
	<!-- <a href="/article/showUpdateForm?idx=${ article.idx }">수정</a>  -->	
	<!-- 삭제 기능은 POST 메서드로 처리해야한다. -->
	<div>
		<form action="/article/showUpdateForm">
			<input type="hidden" name="idx" value="${ article.idx }"/>
			<input type="submit" value="수정"/>
		</form>
		<form action="/article/delete" method="POST">
			<input type="hidden" name="idx" value="${ article.idx }"/>
			<input type="submit" value="삭제"/>
		</form>
	</div>
	

</body>
</html>